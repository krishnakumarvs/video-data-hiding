/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import General.Configuration;
import General.Nimbus;
import db.Dbcon;
import java.io.File;
import java.sql.ResultSet;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Jithinpv
 */
public class Home extends javax.swing.JFrame {

    public static String name;
    public static String path = "";
    public static long size;
    File coverFile;

    /**
     * Creates new form Home
     */
    public Home() {
        Nimbus.loadLoogAndFeel();
        initComponents();
        this.setLocationRelativeTo(null);
        loadIcons();
        loadLastSendFiles();
        loadLastReceivedFiles();
        Configuration.setIconOnLabel("securiyuBack.jpg", main_label);
    }

    private void loadLastReceivedFiles() {
        String query = "SELECT trans.*, usert.user_name , history.encryption_algorithm_id, algo.algorithm_name, history.cipher_file_size,history.cipher_file_name,history.password, history.encryption_file_type "
                + " FROM tbl_transactions AS trans , "
                + " tbl_user_details AS usert ,"
                + " tbl_file_process_history AS history,"
                + " tbl_encrption_algorithms AS algo"
                + " WHERE history.history_id = trans.history_id"
                + " AND trans.sender_id = usert.user_id"
                + " AND algo.algorithm_id = history.encryption_algorithm_id"
                + " AND trans.received_id =" + Login.logged_in_user_id;
        ResultSet select = new Dbcon().select(query);
        try {
            last_receive_sender_1.setVisible(false);
            last_receive_arrow_1.setVisible(false);
            last_receive_icon_1.setVisible(false);

            last_receive_sender_2.setVisible(false);
            last_receive_arrow_2.setVisible(false);
            last_receive_icon_2.setVisible(false);

            last_receive_sender_3.setVisible(false);
            last_receive_arrow_3.setVisible(false);
            last_receive_icon_3.setVisible(false);

            last_receive_sender_4.setVisible(false);
            last_receive_arrow_4.setVisible(false);
            last_receive_icon_4.setVisible(false);

            last_receive_sender_5.setVisible(false);
            last_receive_arrow_5.setVisible(false);
            last_receive_icon_5.setVisible(false);

            if (select.next()) {
                // last item
                last_receive_sender_1.setVisible(true);
                last_receive_arrow_1.setVisible(true);
                last_receive_icon_1.setVisible(true);

                Configuration.setDefaultFileIcon(new File(Configuration.videoPool + select.getString("cipher_file_name")), last_receive_icon_1);
                last_receive_sender_1.setText(select.getString("user_name"));

                if (select.next()) {
                    // second last item
                    last_receive_sender_2.setVisible(true);
                    last_receive_arrow_2.setVisible(true);
                    last_receive_icon_2.setVisible(true);

                    Configuration.setDefaultFileIcon(new File(Configuration.videoPool + select.getString("cipher_file_name")), last_receive_icon_2);
                    last_receive_sender_2.setText(select.getString("user_name"));

                    if (select.next()) {
                        // third last item
                        last_receive_sender_3.setVisible(true);
                        last_receive_arrow_3.setVisible(true);
                        last_receive_icon_3.setVisible(true);

                        Configuration.setDefaultFileIcon(new File(Configuration.videoPool + select.getString("cipher_file_name")), last_receive_icon_3);
                        last_receive_sender_3.setText(select.getString("user_name"));

                        if (select.next()) {
                            // second last item
                            last_receive_sender_4.setVisible(true);
                            last_receive_arrow_4.setVisible(true);
                            last_receive_icon_4.setVisible(true);

                            Configuration.setDefaultFileIcon(new File(Configuration.videoPool + select.getString("cipher_file_name")), last_receive_icon_4);
                            last_receive_sender_4.setText(select.getString("user_name"));

                            if (select.next()) {
                                // second last item
                                last_receive_sender_5.setVisible(true);
                                last_receive_arrow_5.setVisible(true);
                                last_receive_icon_5.setVisible(true);

                                Configuration.setDefaultFileIcon(new File(Configuration.videoPool + select.getString("cipher_file_name")), last_receive_icon_5);
                                last_receive_sender_5.setText(select.getString("user_name"));
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadLastSendFiles() {
        String query = "SELECT trans.*, usert.user_name , history.encryption_algorithm_id, algo.algorithm_name, history.cipher_file_size,history.cipher_file_name,history.password, history.encryption_file_type "
                + " FROM tbl_transactions AS trans , "
                + " tbl_user_details AS usert ,"
                + " tbl_file_process_history AS history,"
                + " tbl_encrption_algorithms AS algo"
                + " WHERE history.history_id = trans.history_id"
                + " AND trans.received_id = usert.user_id"
                + " AND algo.algorithm_id = history.encryption_algorithm_id"
                + " AND trans.sender_id =" + Login.logged_in_user_id
                + " ORDER BY trans.transaction_id DESC";
        System.out.println(query);
        ResultSet select = new Dbcon().select(query);
        try {
            last_send_icon_1.setVisible(false);
            last_send_arrow_1.setVisible(false);
            last_send_receiver_1.setVisible(false);

            last_send_icon_2.setVisible(false);
            last_send_arrow_2.setVisible(false);
            last_send_receiver_2.setVisible(false);

            last_send_icon_3.setVisible(false);
            last_send_arrow_3.setVisible(false);
            last_send_receiver_3.setVisible(false);

            last_send_icon_4.setVisible(false);
            last_send_arrow_4.setVisible(false);
            last_send_receiver_4.setVisible(false);

            last_send_icon_5.setVisible(false);
            last_send_arrow_5.setVisible(false);
            last_send_receiver_5.setVisible(false);

            if (select.next()) {
                // last send file
                System.out.println(select.getString("cipher_file_name"));
                last_send_icon_1.setVisible(true);
                last_send_arrow_1.setVisible(true);
                last_send_receiver_1.setVisible(true);
                Configuration.setDefaultFileIcon(new File(Configuration.videoPool + select.getString("cipher_file_name")), last_send_icon_1);
                last_send_receiver_1.setText(select.getString("user_name"));

                if (select.next()) {
                    // second last send file
                    System.out.println(select.getString("cipher_file_name"));
                    last_send_icon_2.setVisible(true);
                    last_send_arrow_2.setVisible(true);
                    last_send_receiver_2.setVisible(true);
                    Configuration.setDefaultFileIcon(new File(Configuration.videoPool + select.getString("cipher_file_name")), last_send_icon_2);
                    last_send_receiver_2.setText(select.getString("user_name"));

                    if (select.next()) {
                        // third last send file
                        System.out.println(select.getString("cipher_file_name"));
                        last_send_icon_3.setVisible(true);
                        last_send_arrow_3.setVisible(true);
                        last_send_receiver_3.setVisible(true);
                        Configuration.setDefaultFileIcon(new File(Configuration.videoPool + select.getString("cipher_file_name")), last_send_icon_3);
                        last_send_receiver_3.setText(select.getString("user_name"));

                        if (select.next()) {
                            // second last send file
                            System.out.println(select.getString("cipher_file_name"));
                            last_send_icon_4.setVisible(true);
                            last_send_arrow_4.setVisible(true);
                            last_send_receiver_4.setVisible(true);
                            Configuration.setDefaultFileIcon(new File(Configuration.videoPool + select.getString("cipher_file_name")), last_send_icon_4);
                            last_send_receiver_4.setText(select.getString("user_name"));

                            if (select.next()) {
                                // second last send file
                                System.out.println(select.getString("cipher_file_name"));
                                last_send_icon_5.setVisible(true);
                                last_send_arrow_5.setVisible(true);
                                last_send_receiver_5.setVisible(true);
                                Configuration.setDefaultFileIcon(new File(Configuration.videoPool + select.getString("cipher_file_name")), last_send_icon_5);
                                last_send_receiver_5.setText(select.getString("user_name"));
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadIcons() {
        Configuration.setIconOnLabel("Untitled-2.png", last_send_icon_1);
        Configuration.setIconOnLabel("Untitled-2.png", last_send_icon_2);
        Configuration.setIconOnLabel("Untitled-2.png", last_send_icon_3);
        Configuration.setIconOnLabel("Untitled-2.png", last_send_icon_4);
        Configuration.setIconOnLabel("Untitled-2.png", last_send_icon_5);
        Configuration.setIconOnLabel("Untitled-2.png", last_receive_icon_1);

        Configuration.setIconOnLabel("Untitled-1.png", last_send_arrow_1);
        Configuration.setIconOnLabel("Untitled-1.png", last_send_arrow_2);
        Configuration.setIconOnLabel("Untitled-1.png", last_send_arrow_3);
        Configuration.setIconOnLabel("Untitled-1.png", last_send_arrow_4);
        Configuration.setIconOnLabel("Untitled-1.png", last_send_arrow_5);
        Configuration.setIconOnLabel("Untitled-1.png", last_receive_arrow_1);
        Configuration.setIconOnLabel("Untitled-1.png", last_receive_arrow_2);
        Configuration.setIconOnLabel("Untitled-1.png", last_receive_arrow_3);
        Configuration.setIconOnLabel("Untitled-1.png", last_receive_arrow_4);
        Configuration.setIconOnLabel("Untitled-1.png", last_receive_arrow_5);

        Configuration.setIconOnLabel("Untitled-2.png", jLabel10);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu3 = new javax.swing.JMenu();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jLabel1 = new javax.swing.JLabel();
        last_send_icon_1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        last_send_receiver_1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        next_button = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        last_send_arrow_1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        file_name_label = new javax.swing.JLabel();
        file_size_label = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        file_format_text = new javax.swing.JLabel();
        last_send_icon_2 = new javax.swing.JLabel();
        last_send_arrow_2 = new javax.swing.JLabel();
        last_send_receiver_2 = new javax.swing.JLabel();
        last_send_icon_3 = new javax.swing.JLabel();
        last_send_arrow_3 = new javax.swing.JLabel();
        last_send_receiver_3 = new javax.swing.JLabel();
        last_send_icon_4 = new javax.swing.JLabel();
        last_send_arrow_4 = new javax.swing.JLabel();
        last_send_receiver_4 = new javax.swing.JLabel();
        last_send_icon_5 = new javax.swing.JLabel();
        last_send_arrow_5 = new javax.swing.JLabel();
        last_send_receiver_5 = new javax.swing.JLabel();
        last_receive_sender_1 = new javax.swing.JLabel();
        last_receive_arrow_1 = new javax.swing.JLabel();
        last_receive_icon_1 = new javax.swing.JLabel();
        last_receive_sender_2 = new javax.swing.JLabel();
        last_receive_arrow_2 = new javax.swing.JLabel();
        last_receive_icon_2 = new javax.swing.JLabel();
        last_receive_sender_3 = new javax.swing.JLabel();
        last_receive_arrow_3 = new javax.swing.JLabel();
        last_receive_icon_3 = new javax.swing.JLabel();
        last_receive_sender_4 = new javax.swing.JLabel();
        last_receive_arrow_4 = new javax.swing.JLabel();
        last_receive_icon_4 = new javax.swing.JLabel();
        last_receive_sender_5 = new javax.swing.JLabel();
        last_receive_arrow_5 = new javax.swing.JLabel();
        last_receive_icon_5 = new javax.swing.JLabel();
        main_label = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        jMenu3.setText("jMenu3");

        jMenu4.setText("File");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("Edit");
        jMenuBar2.add(jMenu5);

        jMenu6.setText("jMenu6");

        jMenu8.setText("jMenu8");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Last send files");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 11, 178, -1));

        last_send_icon_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        last_send_icon_1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(last_send_icon_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 69, 50, 50));

        jButton1.setText("Previous encyptions");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 406, 168, -1));

        last_send_receiver_1.setForeground(new java.awt.Color(255, 255, 255));
        last_send_receiver_1.setText("Recever name");
        getContentPane().add(last_send_receiver_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 81, 115, 30));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(267, 11, 14, 431));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(546, 46, 15, 396));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Select Cover File");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 11, 233, -1));

        jLabel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(436, 107, 58, 50));

        jButton2.setText("CLEAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 382, -1, -1));

        next_button.setText("NEXT");
        next_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                next_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(next_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(433, 382, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Receiving Data");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 11, 132, -1));
        getContentPane().add(last_send_arrow_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 81, 72, 30));

        jButton4.setText("HISTORY");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(667, 381, -1, -1));

        jButton5.setText("Browse");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 71, 207, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("File Name");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 168, 249, 24));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("File Size");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 249, 249, 24));

        file_name_label.setForeground(new java.awt.Color(255, 255, 255));
        file_name_label.setText("No file selected");
        getContentPane().add(file_name_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 198, 249, -1));

        file_size_label.setForeground(new java.awt.Color(255, 255, 255));
        file_size_label.setText("0 Kb");
        getContentPane().add(file_size_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 279, 249, -1));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("File Format");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 321, 249, 24));

        file_format_text.setForeground(new java.awt.Color(255, 255, 255));
        file_format_text.setText("No file selected");
        getContentPane().add(file_format_text, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 351, 249, 20));

        last_send_icon_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        last_send_icon_2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(last_send_icon_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 137, 50, 50));
        getContentPane().add(last_send_arrow_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 149, 72, 30));

        last_send_receiver_2.setForeground(new java.awt.Color(255, 255, 255));
        last_send_receiver_2.setText("Recever name");
        getContentPane().add(last_send_receiver_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 149, 115, 30));

        last_send_icon_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        last_send_icon_3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(last_send_icon_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 205, 50, 50));
        getContentPane().add(last_send_arrow_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 217, 72, 30));

        last_send_receiver_3.setForeground(new java.awt.Color(255, 255, 255));
        last_send_receiver_3.setText("Recever name");
        getContentPane().add(last_send_receiver_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 217, 115, 30));

        last_send_icon_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        last_send_icon_4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(last_send_icon_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 273, 50, 50));
        getContentPane().add(last_send_arrow_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 285, 72, 30));

        last_send_receiver_4.setForeground(new java.awt.Color(255, 255, 255));
        last_send_receiver_4.setText("Recever name");
        getContentPane().add(last_send_receiver_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 285, 115, 30));

        last_send_icon_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        last_send_icon_5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(last_send_icon_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 341, 50, 50));
        getContentPane().add(last_send_arrow_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 353, 72, 30));

        last_send_receiver_5.setForeground(new java.awt.Color(255, 255, 255));
        last_send_receiver_5.setText("Recever name");
        getContentPane().add(last_send_receiver_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 353, 115, 30));

        last_receive_sender_1.setForeground(new java.awt.Color(255, 255, 255));
        last_receive_sender_1.setText("Sender name");
        getContentPane().add(last_receive_sender_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(565, 85, 94, 29));

        last_receive_arrow_1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(last_receive_arrow_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(665, 84, 80, 30));

        last_receive_icon_1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(last_receive_icon_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(763, 74, 50, 50));

        last_receive_sender_2.setForeground(new java.awt.Color(255, 255, 255));
        last_receive_sender_2.setText("Sender name");
        getContentPane().add(last_receive_sender_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(565, 146, 94, 29));

        last_receive_arrow_2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(last_receive_arrow_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(665, 145, 80, 30));

        last_receive_icon_2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(last_receive_icon_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(763, 135, 50, 50));

        last_receive_sender_3.setForeground(new java.awt.Color(255, 255, 255));
        last_receive_sender_3.setText("Sender name");
        getContentPane().add(last_receive_sender_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(565, 207, 94, 29));

        last_receive_arrow_3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(last_receive_arrow_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(665, 206, 80, 30));

        last_receive_icon_3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(last_receive_icon_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(763, 196, 50, 50));

        last_receive_sender_4.setForeground(new java.awt.Color(255, 255, 255));
        last_receive_sender_4.setText("Sender name");
        getContentPane().add(last_receive_sender_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(565, 268, 94, 29));

        last_receive_arrow_4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(last_receive_arrow_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(665, 267, 80, 30));

        last_receive_icon_4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(last_receive_icon_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(763, 257, 50, 50));

        last_receive_sender_5.setForeground(new java.awt.Color(255, 255, 255));
        last_receive_sender_5.setText("Sender name");
        getContentPane().add(last_receive_sender_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(565, 336, 94, 29));

        last_receive_arrow_5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(last_receive_arrow_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(665, 335, 80, 30));

        last_receive_icon_5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(last_receive_icon_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(763, 325, 50, 50));

        main_label.setText("jLabel2");
        getContentPane().add(main_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(-6, -6, 860, 470));

        jMenu1.setText("Profile");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });

        jMenuItem5.setText("View");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu10.setText("Options");

        jMenuItem3.setText("Change password");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem3);

        jMenuItem4.setText("Logout");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem4);

        jMenuBar1.add(jMenu10);

        jMenu9.setText("Transactions");

        jMenuItem1.setText("Encryption");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem1);

        jMenuItem2.setText("Decryption");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem2);

        jMenuBar1.add(jMenu9);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void next_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_next_buttonActionPerformed
        // TODO add your handling code here:

        try {
            if (coverFile != null) {
                String newFileName = FilenameUtils.getName(coverFile.getPath()) + "_" + System.currentTimeMillis() + "." + FilenameUtils.getExtension(coverFile.getPath());
                FileUtils.copyFile(coverFile, new File(Configuration.videoPool + newFileName));
                Dbcon dbcon = new Dbcon();
                int ins = dbcon.insert("insert into tbl_file_process_history(user_id,cover_file,cover_file_size,cover_file_name)values('" + Login.logged_in_user_id + "','" + newFileName + "','" + Home.size + "','" + Home.name + "')");

                if (ins > 0) {
                    ResultSet select = new Dbcon().select("select max(history_id) from tbl_file_process_history");
                    if (select.next()) {
                        this.dispose();
                        String history_id_string = select.getString(1);
                        int history_id = Integer.parseInt(history_id_string);
                        InputContent inputContent = new InputContent(history_id, coverFile);
                        inputContent.setVisible(true);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_next_buttonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        EncryptHistory encryptHistory = new EncryptHistory();
        encryptHistory.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        ReceiveHistory receiveHistory = new ReceiveHistory();
        receiveHistory.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Vedio files", "avi", "mov", "3gp", "mp4", "mpg");

        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            coverFile = chooser.getSelectedFile();
            path = chooser.getSelectedFile().getPath();

            if (FilenameUtils.getExtension(path).equals("avi")
                    || FilenameUtils.getExtension(path).equals("mov")
                    || FilenameUtils.getExtension(path).equals("3gp")
                    || FilenameUtils.getExtension(path).equals("mp4")
                    || FilenameUtils.getExtension(path).equals("mpg")) {
            } else {
                JOptionPane.showMessageDialog(rootPane, "Please choose a video file");
                return;
            }

            jLabel10.setText(chooser.getSelectedFile().getName());
            name = chooser.getSelectedFile().getName();
            System.out.println("You chose to open this file: "
                    + path);
            size = (chooser.getSelectedFile().length()) / 1024;
            jLabel8.setVisible(true);
            file_name_label.setVisible(true);
            file_size_label.setVisible(true);
            jLabel24.setVisible(true);
            file_format_text.setVisible(true);
            jLabel25.setVisible(true);
            next_button.setEnabled(true);
            file_name_label.setText(name);
            file_size_label.setText(size + " Kb");

            file_format_text.setText(FilenameUtils.getExtension(path));

            try {
                Configuration.setDefaultFileIcon(chooser.getSelectedFile(), jLabel10);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        next_button.setEnabled(false);
        jLabel8.setVisible(false);
        file_name_label.setVisible(false);
        file_size_label.setVisible(false);
        jLabel24.setVisible(false);
        file_format_text.setVisible(false);
        jLabel25.setVisible(false);
    }//GEN-LAST:event_formWindowOpened

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        EncryptHistory encryptHistory = new EncryptHistory();
        encryptHistory.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        ReceiveHistory receiveHistory = new ReceiveHistory();
        receiveHistory.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed
    private void clear() {
        file_name_label.setText("No file selected");
        file_size_label.setText("0 Kb");
        file_format_text.setText("No file selected");
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jLabel8.setVisible(false);
        file_name_label.setVisible(false);
        file_size_label.setVisible(false);
        jLabel24.setVisible(false);
        file_format_text.setVisible(false);
        jLabel25.setVisible(false);
        next_button.setEnabled(false);
        jLabel10.setIcon(null);
        jLabel10.setText(null);
        Configuration.setIconOnLabel("Untitled-2.png", jLabel10);

    }//GEN-LAST:event_jButton2ActionPerformed

private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed

    this.dispose();
    ChangePassword changePassword = new ChangePassword();
    changePassword.setVisible(true);
    // TODO add your handling code here:
}//GEN-LAST:event_jMenuItem3ActionPerformed

private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
    this.dispose();
    Login login = new Login();
    login.setVisible(true);
    // TODO add your handling code here:
}//GEN-LAST:event_jMenuItem4ActionPerformed

private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
    this.dispose();
    Profile profile = new Profile();
    profile.setVisible(true);
    // TODO add your handling code here:
}//GEN-LAST:event_jMenuItem5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Home().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel file_format_text;
    private javax.swing.JLabel file_name_label;
    private javax.swing.JLabel file_size_label;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel last_receive_arrow_1;
    private javax.swing.JLabel last_receive_arrow_2;
    private javax.swing.JLabel last_receive_arrow_3;
    private javax.swing.JLabel last_receive_arrow_4;
    private javax.swing.JLabel last_receive_arrow_5;
    private javax.swing.JLabel last_receive_icon_1;
    private javax.swing.JLabel last_receive_icon_2;
    private javax.swing.JLabel last_receive_icon_3;
    private javax.swing.JLabel last_receive_icon_4;
    private javax.swing.JLabel last_receive_icon_5;
    private javax.swing.JLabel last_receive_sender_1;
    private javax.swing.JLabel last_receive_sender_2;
    private javax.swing.JLabel last_receive_sender_3;
    private javax.swing.JLabel last_receive_sender_4;
    private javax.swing.JLabel last_receive_sender_5;
    private javax.swing.JLabel last_send_arrow_1;
    private javax.swing.JLabel last_send_arrow_2;
    private javax.swing.JLabel last_send_arrow_3;
    private javax.swing.JLabel last_send_arrow_4;
    private javax.swing.JLabel last_send_arrow_5;
    private javax.swing.JLabel last_send_icon_1;
    private javax.swing.JLabel last_send_icon_2;
    private javax.swing.JLabel last_send_icon_3;
    private javax.swing.JLabel last_send_icon_4;
    private javax.swing.JLabel last_send_icon_5;
    private javax.swing.JLabel last_send_receiver_1;
    private javax.swing.JLabel last_send_receiver_2;
    private javax.swing.JLabel last_send_receiver_3;
    private javax.swing.JLabel last_send_receiver_4;
    private javax.swing.JLabel last_send_receiver_5;
    private javax.swing.JLabel main_label;
    private javax.swing.JButton next_button;
    // End of variables declaration//GEN-END:variables
}
