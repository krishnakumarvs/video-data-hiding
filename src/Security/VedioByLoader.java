package Security;

import Model.FileBitPack;
import Model.TextBitPack;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.zip.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class VedioByLoader {

    public static final String VERSION = "2.0.0";
    public static final byte[] VERSION_BYTE = {'2', '0', '0'};
    public static final int OFFSET_JPG = 3;
    public static final int OFFSET_PNG = 42;
    public static final int OFFSET_GIF_BMP_TIF = 32;
    public static final short HEADER_LENGTH = 15 * 4;
    public static final byte UUM = 0;
    public static final byte UUF = 1;
    public static final byte UEM = 2;
    public static final byte UEF = 3;
    public static final byte CUM = 4;
    public static final byte CUF = 5;
    public static final byte CEM = 6;
    public static final byte CEF = 7;
    private static Cipher cipher;
    private static SecretKeySpec spec;
    private static String masterExtension, message;
    private static AboutFrame about = new AboutFrame();
    private static File masterFile;
    private static byte features;
    private static int inputFileSize;
    private static int i, j, inputOutputMarker, messageSize, tempInt;
    private static short compressionRatio = 0, temp;
    private static byte byte1, byte2, byte3, byteArrayIn[];
    private static ByteArrayOutputStream byteOut;

    private VedioByLoader() {
        System.out.println("Steganograph " + VERSION + " ready...");
    }

    public static String getMessage() {
        return message;
    }

    public static boolean embedMessage(TextBitPack textBitPack) {
        File coverFile = textBitPack.getCoverFile();
        File outputFile = textBitPack.getOutputFile();
        String msg = textBitPack.getMsg();
        int compression = textBitPack.getCompression();
        String password = textBitPack.getPassword();

        messageSize = msg.length();

        if (compression != -1) {

            if (compression < 0) {
                compression = 0;
            }
            if (compression > 9) {
                compression = 9;
            }

            if (password == null) {
                features = CUM;
            } else {
                features = CEM;
            }
        } else {
            if (password == null) {
                features = UUM;
            } else {
                features = UEM;
            }
        }

        try {
            byteOut = new ByteArrayOutputStream();

            byte[] messageArray = msg.getBytes();
            messageSize = messageArray.length;
            inputFileSize = (int) coverFile.length();


            byteArrayIn = new byte[inputFileSize];

            DataInputStream in = new DataInputStream(new FileInputStream(coverFile));
            in.read(byteArrayIn, 0, inputFileSize);
            in.close();

            String fileName = coverFile.getName();
            masterExtension = fileName.substring(fileName.length() - 3, fileName.length());

            if (masterExtension.equalsIgnoreCase("jpg")) {

                byteOut.write(byteArrayIn, 0, OFFSET_JPG);
                inputOutputMarker = OFFSET_JPG;
            } else if (masterExtension.equalsIgnoreCase("png")) {

                byteOut.write(byteArrayIn, 0, OFFSET_PNG);
                inputOutputMarker = OFFSET_PNG;
            } else {

                byteOut.write(byteArrayIn, 0, OFFSET_GIF_BMP_TIF);
                inputOutputMarker = OFFSET_GIF_BMP_TIF;
            }


            byte tempByte[] = new byte[4];
            for (i = 24, j = 0; i >= 0; i -= 8, j++) {
                tempInt = inputFileSize;
                tempInt >>= i;
                tempInt &= 0x000000FF;
                tempByte[j] = (byte) tempInt;
            }

            embedBytes(tempByte);

            byteOut.write(byteArrayIn, inputOutputMarker, inputFileSize - inputOutputMarker);
            inputOutputMarker = inputFileSize;

            writeBytes(VERSION_BYTE);

            writeBytes(new byte[]{features});

            if (features == CUM || features == CEM) {
                ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                ZipOutputStream zOut = new ZipOutputStream(arrayOutputStream);
                ZipEntry entry = new ZipEntry("MESSAGE");
                zOut.setLevel(compression);
                zOut.putNextEntry(entry);

                zOut.write(messageArray, 0, messageSize);
                zOut.closeEntry();
                zOut.finish();
                zOut.close();

                messageArray = arrayOutputStream.toByteArray();
                compressionRatio = (short) ((double) messageArray.length / (double) messageSize * 100.0);
                messageSize = messageArray.length;
            }

            writeBytes(new byte[]{(byte) compressionRatio});

            if (features == UEM || features == CEM) {
                Cipher cipher = Cipher.getInstance("DES");
                SecretKeySpec spec = new SecretKeySpec(password.substring(0, 8).getBytes(), "DES");
                cipher.init(Cipher.ENCRYPT_MODE, spec);
                messageArray = cipher.doFinal(messageArray);
                messageSize = messageArray.length;
            }

            tempByte = new byte[4];
            for (i = 24, j = 0; i >= 0; i -= 8, j++) {
                tempInt = messageSize;
                tempInt >>= i;
                tempInt &= 0x000000FF;
                tempByte[j] = (byte) tempInt;
            }

            writeBytes(tempByte);

            writeBytes(messageArray);

            DataOutputStream out = new DataOutputStream(new FileOutputStream(outputFile));

            byteOut.writeTo(out);
            out.close();
        } catch (EOFException e) {
            e.printStackTrace();
        } catch (Exception e) {
            message = "Oops!!\nError: " + e.toString();
            e.printStackTrace();
            return false;
        }
        message = "Message embedded successfully in file '" + outputFile.getName() + "'.";
        return true;
    }

    public static String retrieveMessage(Encryption info, String password) throws Exception {
        String messg = null;
        features = info.getFeatures();


        masterFile = info.getFile();
        byteArrayIn = new byte[(int) masterFile.length()];

        DataInputStream in = new DataInputStream(new FileInputStream(masterFile));
        in.read(byteArrayIn, 0, (int) masterFile.length());
        in.close();

        messageSize = info.getDataLength();

        if (messageSize <= 0) {
            message = "Unexpected size of message: 0.";
            return ("#FAILED#");
        }

        byte[] messageArray = new byte[messageSize];
        inputOutputMarker = info.getInputMarker();
        readBytes(messageArray);


        if (features == CEM || features == UEM) {
            password = password.substring(0, 8);
            byte passwordBytes[] = password.getBytes();
            cipher = Cipher.getInstance("DES");
            spec = new SecretKeySpec(passwordBytes, "DES");
            cipher.init(Cipher.DECRYPT_MODE, spec);
            messageArray = cipher.doFinal(messageArray);
            messageSize = messageArray.length;
        }


        if (features == CUM || features == CEM) {
            ByteArrayOutputStream by = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(by);

            ZipInputStream zipIn = new ZipInputStream(new ByteArrayInputStream(messageArray));
            zipIn.getNextEntry();
            byteArrayIn = new byte[1024];
            while ((tempInt = zipIn.read(byteArrayIn, 0, 1024)) != -1) {
                out.write(byteArrayIn, 0, tempInt);
            }

            zipIn.close();
            out.close();
            messageArray = by.toByteArray();
            messageSize = messageArray.length;
        }

        messg = new String(Encryption.byteToCharArray(messageArray));

        message = "Message size: " + messageSize + " B";
        return messg;
    }

    public static boolean embedFile(FileBitPack fileBitPack) {

        File coverFile = fileBitPack.getCovelFile();
        File outputFile = fileBitPack.getOutputFile();
        File dataFile = fileBitPack.getDataFile();
        int compression = fileBitPack.getCompression();
        String password = fileBitPack.getPassword();

        messageSize = (int) dataFile.length();

        if (password != null && password.length() < 8) {
            message = "Password should be minimum of 8 Characters";
            return false;
        }

        if (compression != 0) {

            if (compression < 0) {
                compression = 0;
            }
            if (compression > 9) {
                compression = 9;
            }

            if (password == null) {
                features = CUF;
            } else {
                features = CEF;
            }
        } else {
            if (password == null) {
                features = UUF;
            } else {
                features = UEF;
            }
        }

        inputFileSize = (int) coverFile.length();
        try {
            byteOut = new ByteArrayOutputStream();


            byteArrayIn = new byte[inputFileSize];

            DataInputStream in = new DataInputStream(new FileInputStream(coverFile));
            in.read(byteArrayIn, 0, inputFileSize);
            in.close();

            String fileName = coverFile.getName();
            masterExtension = fileName.substring(fileName.length() - 3, fileName.length());

            if (masterExtension.equalsIgnoreCase("jpg")) {
                byteOut.write(byteArrayIn, 0, OFFSET_JPG);
                inputOutputMarker = OFFSET_JPG;
            } else if (masterExtension.equalsIgnoreCase("png")) {

                byteOut.write(byteArrayIn, 0, OFFSET_PNG);
                inputOutputMarker = OFFSET_PNG;
            } else {

                byteOut.write(byteArrayIn, 0, OFFSET_GIF_BMP_TIF);
                inputOutputMarker = OFFSET_GIF_BMP_TIF;
            }


            byte tempByte[] = new byte[4];
            for (i = 24, j = 0; i >= 0; i -= 8, j++) {
                tempInt = inputFileSize;
                tempInt >>= i;
                tempInt &= 0x000000FF;
                tempByte[j] = (byte) tempInt;
            }

            embedBytes(tempByte);


            byteOut.write(byteArrayIn, inputOutputMarker, inputFileSize - inputOutputMarker);
            inputOutputMarker = inputFileSize;


            writeBytes(VERSION_BYTE);


            writeBytes(new byte[]{features});


            byte[] fileArray = new byte[messageSize];
            in = new DataInputStream(new FileInputStream(dataFile));
            in.read(fileArray, 0, messageSize);
            in.close();

            if (features == CUF || features == CEF) {
                ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                ZipOutputStream zOut = new ZipOutputStream(arrayOutputStream);
                ZipEntry entry = new ZipEntry(dataFile.getName());
                zOut.setLevel(compression);
                zOut.putNextEntry(entry);
                zOut.write(fileArray, 0, messageSize);
                zOut.closeEntry();
                zOut.finish();
                zOut.close();


                fileArray = arrayOutputStream.toByteArray();
                compressionRatio = (short) ((double) fileArray.length / (double) messageSize * 100.0);
                messageSize = fileArray.length;
            }


            writeBytes(new byte[]{(byte) compressionRatio});


            if (features == UEF || features == CEF) {
                Cipher cipher = Cipher.getInstance("DES");
                SecretKeySpec spec = new SecretKeySpec(password.substring(0, 8).getBytes(), "DES");
                cipher.init(Cipher.ENCRYPT_MODE, spec);
                fileArray = cipher.doFinal(fileArray);
                messageSize = fileArray.length;
            }

            tempByte = new byte[4];
            for (i = 24, j = 0; i >= 0; i -= 8, j++) {
                tempInt = messageSize;
                tempInt >>= i;
                tempInt &= 0x000000FF;
                tempByte[j] = (byte) tempInt;
            }

            writeBytes(tempByte);


            writeBytes(fileArray);

            DataOutputStream out = new DataOutputStream(new FileOutputStream(outputFile));
            byteOut.writeTo(out);
            out.close();
        } catch (EOFException e) {
        } catch (Exception e) {
            message = "Oops!!\nError: " + e.toString();
            e.printStackTrace();
            return false;
        }

        message = "File '" + dataFile.getName() + "' embedded successfully in file '" + outputFile.getName() + "'.";
        return true;
    }

    public static File retrieveFile(Encryption info, String password, boolean overwrite, String retreivedFileName) throws Exception {
        File dataFile = null;
        features = info.getFeatures();
        masterFile = info.getFile();
        byteArrayIn = new byte[(int) masterFile.length()];

        DataInputStream in = new DataInputStream(new FileInputStream(masterFile));
        in.read(byteArrayIn, 0, (int) masterFile.length());
        in.close();

        messageSize = info.getDataLength();
        byte[] fileArray = new byte[messageSize];
        inputOutputMarker = info.getInputMarker();
        readBytes(fileArray);

        if (messageSize <= 0) {
            message = "Unexpected size of embedded file: 0.";
            return null;
        }


        if (features == CEF || features == UEF) {
            password = password.substring(0, 8);
            byte passwordBytes[] = password.getBytes();
            cipher = Cipher.getInstance("DES");
            spec = new SecretKeySpec(passwordBytes, "DES");
            cipher.init(Cipher.DECRYPT_MODE, spec);
            fileArray = cipher.doFinal(fileArray);
            messageSize = fileArray.length;
        }


        if (features == CUF || features == CEF) {
            ByteArrayOutputStream by = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(by);

            ZipInputStream zipIn = new ZipInputStream(new ByteArrayInputStream(fileArray));
            ZipEntry entry = zipIn.getNextEntry();
            //dataFile = new File(Configuration.retreivedFilePoolLocation + entry.getName());
            dataFile = new File(retreivedFileName);


            byteArrayIn = new byte[1024];
            while ((tempInt = zipIn.read(byteArrayIn, 0, 1024)) != -1) {
                out.write(byteArrayIn, 0, tempInt);
            }

            zipIn.close();
            out.close();
            fileArray = by.toByteArray();
            messageSize = fileArray.length;
        }

        info.setDataFile(dataFile);
        if (dataFile.exists() && !overwrite) {
            message = "File Exists";
            return null;
        }

        DataOutputStream out = new DataOutputStream(new FileOutputStream(dataFile));
        out.write(fileArray, 0, fileArray.length);
        out.close();


        message = "Retrieved file size: " + messageSize + " B";
        return dataFile;
    }

    public static long getMaxAllowedDataSize(File file) {
        long maxAllowedDataSize = 0;
        long length = file.length();
        maxAllowedDataSize = length;
        return maxAllowedDataSize;
    }

    public static long getMaxAllowedFileSize(File file) {
        long maxAllowedDataSize = 0;
        long length = file.length() / 4;
        maxAllowedDataSize = length;
        return maxAllowedDataSize;
    }

    private static void embedBytes(byte[] bytes) {
        int size = bytes.length;

        for (int i = 0; i < size; i++) {
            byte1 = bytes[i];
            for (int j = 6; j >= 0; j -= 2) {
                byte2 = byte1;
                byte2 >>= j;
                byte2 &= 0x03;

                byte3 = byteArrayIn[inputOutputMarker];
                byte3 &= 0xFC;
                byte3 |= byte2;
                byteOut.write(byte3);
                inputOutputMarker++;
            }
        }
    }

    private static void writeBytes(byte[] bytes) {
        int size = bytes.length;

        for (int i = 0; i < size; i++) {
            byteOut.write(bytes[i]);
            inputOutputMarker++;
        }
    }

    private static void retrieveBytes(byte[] bytes) {
        int size = bytes.length;

        for (int i = 0; i < size; i++) {
            byte1 = 0;
            for (int j = 6; j >= 0; j -= 2) {
                byte2 = byteArrayIn[inputOutputMarker];
                inputOutputMarker++;

                byte2 &= 0x03;
                byte2 <<= j;
                byte1 |= byte2;
            }
            bytes[i] = byte1;
        }
    }

    private static void readBytes(byte[] bytes) {
        int size = bytes.length;

        for (int i = 0; i < size; i++) {
            bytes[i] = byteArrayIn[inputOutputMarker];
            inputOutputMarker++;
        }
    }

    public static void showAboutDialog() {
        about.setDisplayed(true);
    }

    public static void updateUserInterface() {
        SwingUtilities.updateComponentTreeUI(about);
    }

    public static void showHelpDialog() {
        new HTMLFrame("file:" + System.getProperty("user.dir")
                + System.getProperty("file.separator") + "help.html", false);
    }

    private static class AboutFrame extends JFrame {

        private final Color GREEN = Color.green;
        private final Color YELLOW = Color.yellow;
        private final Color BLACK = Color.black;
        private JLabel lblTitle, lblImage, lblName, lblName1, lblEmail, lblPhone;
        private JLabel filler1, filler2, filler3;
        private GridBagLayout gbl;
        private GridBagConstraints gbc;

        public void setDisplayed(boolean choice) {
            setVisible(choice);
        }
    }
}

class SteganoInformation_old {

    private File file;
    private File dataFile = null;
    private String starter;
    private String version;
    private byte features;
    private short compressionRatio;
    private int dataLength, temp;
    private boolean isEster = false;
    private byte byteArray[], name[], byte1, byte2;
    private int inputMarker, i, j;

    public File getFile() {
        return file;
    }

    public int getInputMarker() {
        return inputMarker;
    }

    public File getDataFile() {
        return dataFile;
    }

    public String getVersion() {
        return version;
    }

    public byte getFeatures() {
        return features;
    }

    public short getCompressionRatio() {
        return compressionRatio;
    }

    public int getDataLength() {
        return dataLength;
    }

    public boolean isEster() {
        return isEster;
    }

    public void setDataFile(File dataFile) {
        this.dataFile = dataFile;
    }

    private void retrieveBytes(byte[] bytes, byte[] array, int marker) {
        byteArray = array;
        inputMarker = marker;

        int size = bytes.length;

        for (i = 0; i < size; i++) {
            byte1 = 0;
            for (j = 6; j >= 0; j -= 2) {
                byte2 = byteArray[inputMarker];
                inputMarker++;

                byte2 &= 0x03;
                byte2 <<= j;
                byte1 |= byte2;
            }
            bytes[i] = byte1;
        }
    }

    private void retrieveBytes(byte[] bytes) {
        int size = bytes.length;

        for (i = 0; i < size; i++) {
            byte1 = 0;
            for (j = 6; j >= 0; j -= 2) {
                byte2 = byteArray[inputMarker];
                inputMarker++;

                byte2 &= 0x03;
                byte2 <<= j;
                byte1 |= byte2;
            }
            bytes[i] = byte1;
        }
    }

    private void readBytes(byte[] bytes, byte[] array, int marker) {
        byteArray = array;
        inputMarker = marker;

        int size = bytes.length;

        for (i = 0; i < size; i++) {
            bytes[i] = byteArray[inputMarker];
            inputMarker++;
        }
    }

    private void readBytes(byte[] bytes) {
        int size = bytes.length;

        for (i = 0; i < size; i++) {
            bytes[i] = byteArray[inputMarker];
            inputMarker++;
        }
    }

    public static char[] byteToCharArray(byte[] bytes) {
        int size = bytes.length, i;
        char[] chars = new char[size];
        for (i = 0; i < size; i++) {
            bytes[i] &= 0x7F;
            chars[i] = (char) bytes[i];
        }
        return chars;
    }

    public SteganoInformation_old(File file) {
        this.file = file;
        isEster = false;

        if (!file.exists()) {
            starter = null;
            return;
        }

        if (file.getName().equals("Sec#x&y")) {
            isEster = true;
            return;
        }

        byteArray = new byte[(int) file.length()];
        try {
            DataInputStream in = new DataInputStream(new FileInputStream(file));
            in.read(byteArray, 0, (int) file.length());
            in.close();
        } catch (Exception e) {
            starter = null;
            return;
        }


        name = new byte[4];

        String fileName = file.getName();
        String fileExtension = fileName.substring(fileName.length() - 3, fileName.length());

        if (fileExtension.equalsIgnoreCase("jpg")) {
            inputMarker = VedioByLoader.OFFSET_JPG;
        } else if (fileExtension.equalsIgnoreCase("png")) {
            inputMarker = VedioByLoader.OFFSET_PNG;
        } else {
            inputMarker = VedioByLoader.OFFSET_GIF_BMP_TIF;
        }

        retrieveBytes(name, byteArray, inputMarker);
        dataLength = 0;
        for (i = 24, j = 0; i >= 0; i -= 8, j++) {
            temp = name[j];
            temp &= 0x000000FF;
            temp <<= i;
            dataLength |= temp;
        }
        inputMarker = dataLength;

        if (dataLength < 0 || dataLength > file.length()) {
            starter = "Invalid";
            return;
        } else {
            starter = "stegnograph";
        }


        byte versionArray[] = new byte[3];
        readBytes(versionArray, byteArray, inputMarker);
        char[] versionTemp = byteToCharArray(versionArray);
        char[] ver = new char[5];
        for (i = 0, j = 0; i < 5; i++) {
            if (i == 1 || i == 3) {
                ver[i] = '.';
            } else {
                ver[i] = versionTemp[j++];
            }
        }

        version = new String(ver);


        name = new byte[1];
        readBytes(name);
        features = name[0];


        readBytes(name);
        name[0] &= 0x7F;
        compressionRatio = name[0];


        name = new byte[4];
        readBytes(name);
        dataLength = 0;
        for (i = 24, j = 0; i >= 0; i -= 8, j++) {
            temp = name[j];
            temp &= 0x000000FF;
            temp <<= i;
            dataLength |= temp;
        }
    }

    public boolean isValid() {
        if (starter.equals("stegnograph")) {
            return true;
        } else {
            return false;
        }
    }
}

class HTMLFrame extends JFrame implements HyperlinkListener {

    JEditorPane editorPane;
    JScrollPane scrollPane;

    public HTMLFrame(String startURL, boolean isOnline) {
        super("Help - Steganograph " + VedioByLoader.VERSION + "");

        editorPane = new JEditorPane();
        editorPane.setEditable(false);
        if (isOnline) {
            editorPane.setBackground(Color.white);
            setTitle("Steganograph " + VedioByLoader.VERSION + " ");
        }

        editorPane.addHyperlinkListener(this);
        scrollPane = new JScrollPane(editorPane);

        try {
            editorPane.setPage(startURL);
            getContentPane().add(scrollPane);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Oops!! Error\n" + e, "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(d.width, d.height);
        setVisible(true);
    }

    public void hyperlinkUpdate(HyperlinkEvent e) {
        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            try {
                editorPane.setPage(e.getURL());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Oops!! Error\n" + ex, "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
