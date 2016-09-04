/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import General.Configuration;
import db.Dbcon;
import java.io.File;
import java.sql.ResultSet;

import javax.swing.JFileChooser;
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
        initComponents();
        this.setLocationRelativeTo(null);
        loadIcons();
        loadLastSendFiles();
        loadLastReceivedFiles();
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
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
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

        jLabel1.setText("Last send files");

        last_send_icon_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        last_send_icon_1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton1.setText("Previous encyptions");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        last_send_receiver_1.setText("Recever name");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Select Cover File");

        jLabel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton2.setText("CLEAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        next_button.setText("NEXT");
        next_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                next_buttonActionPerformed(evt);
            }
        });

        jLabel11.setText("Receiving Data");

        jButton4.setText("HISTORY");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Browse");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel8.setText("File Name");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel24.setText("File Size");

        file_name_label.setText("No file selected");

        file_size_label.setText("0 Kb");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel25.setText("File Format");

        file_format_text.setText("No file selected");

        last_send_icon_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        last_send_icon_2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        last_send_receiver_2.setText("Recever name");

        last_send_icon_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        last_send_icon_3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        last_send_receiver_3.setText("Recever name");

        last_send_icon_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        last_send_icon_4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        last_send_receiver_4.setText("Recever name");

        last_send_icon_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        last_send_icon_5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        last_send_receiver_5.setText("Recever name");

        last_receive_sender_1.setText("Sender name");

        last_receive_arrow_1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        last_receive_icon_1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        last_receive_sender_2.setText("Sender name");

        last_receive_arrow_2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        last_receive_icon_2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        last_receive_sender_3.setText("Sender name");

        last_receive_arrow_3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        last_receive_icon_3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        last_receive_sender_4.setText("Sender name");

        last_receive_arrow_4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        last_receive_icon_4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        last_receive_sender_5.setText("Sender name");

        last_receive_arrow_5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        last_receive_icon_5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jMenu1.setText("Profile");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 71, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(last_send_icon_2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(last_send_arrow_2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(last_send_receiver_2, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(last_send_icon_1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(last_send_arrow_1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(last_send_receiver_1, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(last_send_icon_3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(last_send_arrow_3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(last_send_receiver_3, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(last_send_icon_4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(last_send_arrow_4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(last_send_receiver_4, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(last_send_icon_5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(last_send_arrow_5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(last_send_receiver_5, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 83, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(next_button)
                        .addGap(46, 46, 46))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                            .addComponent(file_name_label, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                            .addComponent(file_size_label, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                            .addComponent(file_format_text, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jButton4))))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(last_receive_sender_2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(last_receive_arrow_2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(last_receive_icon_2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(last_receive_sender_1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(last_receive_arrow_1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(last_receive_icon_1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(last_receive_sender_3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(last_receive_arrow_3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(last_receive_icon_3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(last_receive_sender_4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(last_receive_arrow_4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(last_receive_icon_4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(last_receive_sender_5, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(last_receive_arrow_5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(last_receive_icon_5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(last_send_icon_1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(last_send_receiver_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(last_send_arrow_1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(last_send_icon_2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(last_send_receiver_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(last_send_arrow_2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(last_send_icon_3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(last_send_receiver_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(last_send_arrow_3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(last_send_icon_4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(last_send_receiver_4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(last_send_arrow_4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(last_send_icon_5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(last_send_receiver_5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(last_send_arrow_5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(13, 13, 13))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(file_name_label, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(file_size_label, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(file_format_text, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(next_button))
                        .addGap(37, 37, 37)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(last_receive_sender_1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(last_receive_arrow_1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(last_receive_icon_1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(last_receive_sender_2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(last_receive_arrow_2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(last_receive_icon_2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(last_receive_sender_3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(last_receive_arrow_3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(last_receive_icon_3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(last_receive_sender_4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(last_receive_arrow_4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(last_receive_icon_4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(last_receive_sender_5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(last_receive_arrow_5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(last_receive_icon_5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(49, 49, 49))
        );

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
        this.dispose();
        Profile profile = new Profile();
        profile.setVisible(true);
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
            jLabel10.setText(chooser.getSelectedFile().getName());
            name = chooser.getSelectedFile().getName();
            System.out.println("You chose to open this file: "
                    + path);
            size = (chooser.getSelectedFile().length()) / 1024;
            next_button.setEnabled(true);
            file_name_label.setText(name);
            file_size_label.setText(size + "Kb");

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
        clear();
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
    private javax.swing.JButton next_button;
    // End of variables declaration//GEN-END:variables
}
