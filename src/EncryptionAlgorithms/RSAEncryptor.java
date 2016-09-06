/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EncryptionAlgorithms;

/**
 *
 * @author kakes
 */
import General.Configuration;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;

/**
 * @author JavaDigest
 * 
 */
public class RSAEncryptor {

    /**
     * String to hold name of the encryption algorithm.
     */
    public static final String ALGORITHM = "RSA";
    /**
     * String to hold the name of the private key file.
     */
    public static final String PRIVATE_KEY_FILE = Configuration.rsaKeys + "rsa_private.key";
    /**
     * String to hold name of the public key file.
     */
    public static final String PUBLIC_KEY_FILE = Configuration.rsaKeys + "rsa_public.key";

    /**
     * Generate key which contains a pair of private and public key using 1024
     * bytes. Store the set of keys in Prvate.key and Public.key files.
     * 
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void generateKey() {
        try {
            System.out.println("generating key");
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
            keyGen.initialize(1024);
            final KeyPair key = keyGen.generateKeyPair();
            
            File privateKeyFile = new File(PRIVATE_KEY_FILE);
            File publicKeyFile = new File(PUBLIC_KEY_FILE);

            // Create files to store public and private key
            if (privateKeyFile.getParentFile() != null) {
                privateKeyFile.getParentFile().mkdirs();
            }
            privateKeyFile.createNewFile();
            
            if (publicKeyFile.getParentFile() != null) {
                publicKeyFile.getParentFile().mkdirs();
            }
            publicKeyFile.createNewFile();

            // Saving the Public key in a file
            ObjectOutputStream publicKeyOS = new ObjectOutputStream(
                    new FileOutputStream(publicKeyFile));
            publicKeyOS.writeObject(key.getPublic());
            publicKeyOS.close();

            // Saving the Private key in a file
            ObjectOutputStream privateKeyOS = new ObjectOutputStream(
                    new FileOutputStream(privateKeyFile));
            privateKeyOS.writeObject(key.getPrivate());
            privateKeyOS.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public static String encodeData(byte[] imagebytearray) {
        return Base64.encode(imagebytearray);
    }
    
    public static byte[] decodeData(String idatastring) {
        return Base64.decode(idatastring);
    }

    /**
     * The method checks if the pair of public and private key has been generated.
     * 
     * @return flag indicating if the pair of keys were generated.
     */
    public static boolean areKeysPresent() {
        
        File privateKey = new File(PRIVATE_KEY_FILE);
        File publicKey = new File(PUBLIC_KEY_FILE);
        
        if (privateKey.exists() && publicKey.exists()) {
            return true;
        }
        return false;
    }

    /**
     * Encrypt the plain text using public key.
     * 
     * @param text
     *          : original plain text
     * @param key
     *          :The public key
     * @return Encrypted text
     * @throws java.lang.Exception
     */
    public static String encrypt(String text, PublicKey key) {
        String cipherText = "Could not encrpt";
        try {
            // get an RSA cipher object and print the provider
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            // encrypt the plain text using the public key
            cipher.init(Cipher.ENCRYPT_MODE, key);
            cipherText = cipher.doFinal(text.getBytes()).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodeData(text.getBytes());
    }

    /**
     * Decrypt text using private key.
     * 
     * @param text
     *          :encrypted text
     * @param key
     *          :The private key
     * @return plain text
     * @throws java.lang.Exception
     */
    public static String decrypt(String text, PrivateKey key) {
        
        String dectyptedText = "Could not decrypt";
        try {
            // get an RSA cipher object and print the provider
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            // decrypt the text using the private key
            cipher.init(Cipher.DECRYPT_MODE, key);
            dectyptedText = cipher.doFinal(text.getBytes()).toString();
            
        } catch (BadPaddingException e) {
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return new String(decodeData(text));
    }

    /**
     * Test the EncryptionUtil
     */
    private static String bytesToString(byte[] encrypted) {
        String test = "";
        for (byte b : encrypted) {
            test += Byte.toString(b);
        }
        return test;
    }
    
    public static void main(String[] args) {
        try {
            // Check if the pair of keys are present else generate those.
            if (!areKeysPresent()) {
                // Method generates a pair of keys using the RSA algorithm and stores it
                // in their respective files
                generateKey();
            }
            
            final String originalText = "Sample message kakes tk";
            ObjectInputStream inputStream = null;


            // Encrypt the string using the public key
            inputStream = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
            final PublicKey publicKey = (PublicKey) inputStream.readObject();
            String ciphhhh = encrypt(originalText, publicKey);
            
            System.out.println("Encroyted to");
            System.out.println(ciphhhh);
            //System.out.println(bytesAsString);

            
            
            inputStream = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
            final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
            final String plainText = decrypt(ciphhhh, privateKey);
            System.out.println("Decrypted: " + plainText);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
