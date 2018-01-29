/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vtsengclubvocallearn;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import trung.dao.GradeDAO;
import trung.dao.UnitDAO;
import trung.dao.WordDAO;
import trung.dto.GradeDTO;
import trung.dto.UnitDTO;
import trung.dto.WordByGradeDTO;
import trung.dto.WordDTO;
import util.MyUtil;

/**
 *
 * @author Trung
 */
public class AddWordForm extends javax.swing.JFrame {

    Dictionary dic;
    
    Media media;
    MediaPlayer soundMediaPlayer;
    MediaPlayer exMediaPlayer;

    BufferedImage bufferImage;
    JLabel picLabel;
    JPanel jpImage;
    File imageDes, soundDes, exDes, preSound;

    GradeDAO gradeDao = new GradeDAO();
    UnitDAO unitDao = new UnitDAO();
    ArrayList<GradeDTO> grades = new ArrayList<>();
    ArrayList<UnitDTO> units = new ArrayList<>();

    String inputErrors = "";

    /**
     * Creates new form Add
     */
    public AddWordForm() {
        initComponents();
        firstStartup();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
    }

    public void firstStartup() {
        //Tạo loại file đc chọn cho file choosen
        fcImage.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "png"));
        fcSound.setFileFilter(new FileNameExtensionFilter("Sound files", "MP3", "wav"));

        //Lấy dữ liệu grade
        loadGrades();

    }
    
    public void setDictionary(Dictionary parent) {
        this.dic = parent;
    }

    public void saveWord() {
        WordDTO wDto = new WordDTO();
        WordByGradeDTO wbgDto = new WordByGradeDTO();
        WordDAO wDao = new WordDAO();
        if (checkInput()) {
            wDto.setName(txtWord.getText());
            wDto.setSpelling(txtSpelling.getText());
            wDto.setSpellingSrc(soundDes.getPath());
            wDto.setPartsOfSpeech(cbPartsOfSpeech.getSelectedItem().toString());
            wDto.setImageSrc(imageDes.getPath());
            wDto.setMeaning(taMeaning.getText());
            wbgDto.setExample(taExample.getText());
            wbgDto.setExampleSrc(exDes.getPath());
            wbgDto.setUnitSEQ(units.get(cbUnit.getSelectedIndex()).getSEQ());
            wDao.saveNewWord(wDto, wbgDto);
            JOptionPane.showMessageDialog(this, "Input successful");
        } else {
            JOptionPane.showMessageDialog(this, inputErrors, "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setSound() {

        if (soundMediaPlayer != null) {
            soundMediaPlayer.stop();
            btnSoundPlay.setText("Play");

        }

        //Lấy file đc chọn
        preSound = fcSound.getSelectedFile();

        //Tạo địa chỉ mới cho file đc chọn
        soundDes = new File("src\\resources\\sound\\" + preSound.getName());

        //Set label
        lbSoundSrc.setText(preSound.getName());

        try {
            //Copy file cũ sang địa chỉ mới
            Files.copy(preSound.toPath(), soundDes.toPath(), StandardCopyOption.REPLACE_EXISTING);

            //Nạp nhạc vào media và mediaplayer
            media = new Media(soundDes.toURI().toString());
            soundMediaPlayer = new MediaPlayer(media);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void playSound() {
        if (soundMediaPlayer.getStatus() == MediaPlayer.Status.READY || soundMediaPlayer.getStatus() == MediaPlayer.Status.STOPPED) {
            soundMediaPlayer.play();
            btnSoundPlay.setText("Stop");
        } else {
            soundMediaPlayer.stop();
            btnSoundPlay.setText("Play");
        }
    }

    public void stopSound() {
        if (soundMediaPlayer != null) {
            soundMediaPlayer.stop();
        }
    }

    private void setImage() { //can xem lai ve viec dat ten cho image
        //Lấy file từ file chooser
        File image = fcImage.getSelectedFile();

        //Tạo địa chỉ mới cho file
        imageDes = new File("src\\resources\\image\\" + image.getName());

        //set label
        lbImageSrc.setText(image.getName());

        try {
            //Nạp hình vào
            Image originImage = ImageIO.read(image);

            //Resize hình theo đúng tỉ lệ, lấy chiều rộng cho chiều dài 600
            Double newWeight = 300.0 / ((double) originImage.getHeight(this) / (double) originImage.getWidth(this));

            //Resize hình
            BufferedImage resizeImage = MyUtil.createResizedCopy(originImage, newWeight.intValue(), 300, true);

            //Lưu hình vào file mới
            ImageIO.write(resizeImage, "jpg", imageDes);

            //Lưu hình mới vào file hình để preview
            bufferImage = ImageIO.read(imageDes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void previewImage() {
        //chuyển hình sang dạng label
        picLabel = new JLabel(new ImageIcon(bufferImage));

        jpImage = new JPanel();

        //cho label vào panel mới
        jpImage.add(picLabel);

        //paint lại panel vs hình
        jpImage.repaint();

        //show hình
        JOptionPane.showMessageDialog(this, jpImage);
    }

    private void loadGrades() {
        grades = gradeDao.getAllGrade();
        cbGrade.removeAllItems();
        for (GradeDTO dto : grades) {
            cbGrade.addItem("Grade " + dto.getNumber());
        }
    }

    private void loadUnitByGradeSEQ(int gradeSEQ) {
        cbUnit.removeAllItems();
        units = unitDao.getAllUnitByGradeSEQ(gradeSEQ);
        for (UnitDTO dto : units) {
            cbUnit.addItem("Unit " + dto.getNumber() + ": " + dto.getName());
        }
    }

    private void setExSound() {
        if (exMediaPlayer != null) {
            exMediaPlayer.stop();
            btnExPlay.setText("Play");
        }

        preSound = fcSound.getSelectedFile();
        exDes = new File("src\\resources\\sound\\" + preSound.getName());

        lbExSrc.setText(preSound.getName());
        try {
            Files.copy(preSound.toPath(), exDes.toPath(), StandardCopyOption.REPLACE_EXISTING);

            media = new Media(exDes.toURI().toString());
            exMediaPlayer = new MediaPlayer(media);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void playEx() {
        if (exMediaPlayer.getStatus() == MediaPlayer.Status.READY || exMediaPlayer.getStatus() == MediaPlayer.Status.STOPPED) {
            exMediaPlayer.play();
            btnExPlay.setText("Stop");
        } else {
            exMediaPlayer.stop();
            btnExPlay.setText("Play");
        }
    }

    public void stopEx() {
        if (exMediaPlayer != null) {
            exMediaPlayer.stop();
        }
    }

    public boolean checkInput() {
        WordDAO wordDao = new WordDAO();
        boolean result = true;
        inputErrors = "";

        //Check name
        //check null name
        if (txtWord.getText().isEmpty()) {
            result = false;
            inputErrors = inputErrors + "Input word\n";
        } //check special character
        else if (!txtWord.getText().matches("^[A-z]+$")) {
            System.out.println("word: '" + txtWord.getText() + "'");
            System.out.println("Match? " + txtWord.getText().matches("/^[A-z]+$/"));
            result = false;
            inputErrors = inputErrors + "Word contain only alphabet character\n";
        } //check unique name
        else if (!wordDao.isUniqueName(txtWord.getName())) {
            result = false;
            inputErrors = inputErrors + "Word has been added\n";
        }

        //check spelling
        //check null spelling
        if (txtSpelling.getText().isEmpty()) {
            result = false;
            inputErrors = inputErrors + "Input spelling\n";
        }

        //check spelling sound
        //check not choose
        if (lbSoundSrc.getText().isEmpty()) {
            result = false;
            inputErrors = inputErrors + "Choose spelling sound\n";
        }

        //check image
        //check not choose
        if (lbImageSrc.getText().isEmpty()) {
            result = false;
            inputErrors = inputErrors + "Choose image\n";
        }

        //check meaning
        //check null meaning
        if (taMeaning.getText().isEmpty()) {
            result = false;
            inputErrors = inputErrors + "Input meaning\n";
        }

        //check example
        //check null example
        if (taExample.getText().isEmpty()) {
            result = false;
            inputErrors = inputErrors + "Input example\n";
        }

        //check ex sound
        //check not choose
        if (lbExSrc.getText().isEmpty()) {
            result = false;
            inputErrors = inputErrors + "Choose example sound\n";
        }

//        taError.setText(inputErrors);
        return result;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fcSound = new javax.swing.JFileChooser();
        fcImage = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtWord = new javax.swing.JTextField();
        txtSpelling = new javax.swing.JTextField();
        btnAddSound = new javax.swing.JButton();
        cbPartsOfSpeech = new javax.swing.JComboBox<>();
        btnAddImage = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        taMeaning = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taExample = new javax.swing.JTextArea();
        btnAddEx = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbGrade = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cbUnit = new javax.swing.JComboBox<>();
        btnSoundPlay = new javax.swing.JButton();
        lbSoundSrc = new javax.swing.JLabel();
        lbImageSrc = new javax.swing.JLabel();
        btnImagePreview = new javax.swing.JButton();
        lbExSrc = new javax.swing.JLabel();
        btnExPlay = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnOk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(600, 500));

        jLabel1.setText("Word");

        jLabel8.setText("Spelling");

        jLabel9.setText("Sound");

        jLabel10.setText("Parts of speech");

        jLabel11.setText("Image");

        jLabel12.setText("Meaning");

        btnAddSound.setText("...");
        btnAddSound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSoundActionPerformed(evt);
            }
        });

        cbPartsOfSpeech.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Noun", "Pronoun", "Adjective", "Verb", "Adverb", "Preposition", "Conjunction", "Interjection" }));

        btnAddImage.setText("...");
        btnAddImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddImageActionPerformed(evt);
            }
        });

        taMeaning.setColumns(20);
        taMeaning.setRows(5);
        jScrollPane2.setViewportView(taMeaning);

        jLabel2.setText("Example");

        taExample.setColumns(20);
        taExample.setRows(5);
        jScrollPane1.setViewportView(taExample);

        btnAddEx.setText("...");
        btnAddEx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddExActionPerformed(evt);
            }
        });

        jLabel3.setText("Example sound");

        jLabel4.setText("Grade");

        cbGrade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbGrade.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbGradeItemStateChanged(evt);
            }
        });

        jLabel5.setText("Unit");

        cbUnit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnSoundPlay.setText("Play");
        btnSoundPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSoundPlayActionPerformed(evt);
            }
        });

        btnImagePreview.setText("Preview");
        btnImagePreview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImagePreviewActionPerformed(evt);
            }
        });

        btnExPlay.setText("Play");
        btnExPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExPlayActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnOk.setText("Ok");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSpelling)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lbImageSrc)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnAddImage)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnImagePreview))
                                    .addComponent(cbPartsOfSpeech, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lbSoundSrc)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnAddSound)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnSoundPlay)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtWord)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbGrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lbExSrc)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnAddEx)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnExPlay)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnOk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtWord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtSpelling, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(btnAddSound)
                    .addComponent(btnSoundPlay)
                    .addComponent(lbSoundSrc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbPartsOfSpeech, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(btnAddImage)
                    .addComponent(lbImageSrc)
                    .addComponent(btnImagePreview))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lbExSrc)
                    .addComponent(btnAddEx)
                    .addComponent(btnExPlay))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(cbGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnOk))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 624, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 498, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddSoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSoundActionPerformed
        switch (fcSound.showOpenDialog(this)) {
            case JFileChooser.APPROVE_OPTION:
                setSound();
                break;
        }
    }//GEN-LAST:event_btnAddSoundActionPerformed

    private void btnAddImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddImageActionPerformed
        switch (fcImage.showOpenDialog(this)) {
            case JFileChooser.APPROVE_OPTION:
                setImage();
                break;
        }
    }//GEN-LAST:event_btnAddImageActionPerformed

    private void btnAddExActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddExActionPerformed
        switch (fcSound.showOpenDialog(this)) {
            case JFileChooser.APPROVE_OPTION:
                setExSound();
                break;
        }
    }//GEN-LAST:event_btnAddExActionPerformed

    private void cbGradeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbGradeItemStateChanged
        if (cbGrade.getSelectedIndex() > -1) {
            loadUnitByGradeSEQ(grades.get(cbGrade.getSelectedIndex()).getSEQ());
        }
    }//GEN-LAST:event_cbGradeItemStateChanged

    private void btnSoundPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSoundPlayActionPerformed
        playSound();
    }//GEN-LAST:event_btnSoundPlayActionPerformed

    private void btnImagePreviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImagePreviewActionPerformed
        previewImage();
    }//GEN-LAST:event_btnImagePreviewActionPerformed

    private void btnExPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExPlayActionPerformed
        playEx();
    }//GEN-LAST:event_btnExPlayActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        saveWord();
        this.dispose();
    }//GEN-LAST:event_btnOkActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        dic.setEnabled(true);
    }//GEN-LAST:event_formWindowClosed

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
            java.util.logging.Logger.getLogger(AddWordForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddWordForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddWordForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddWordForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddWordForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddEx;
    private javax.swing.JButton btnAddImage;
    private javax.swing.JButton btnAddSound;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnExPlay;
    private javax.swing.JButton btnImagePreview;
    private javax.swing.JButton btnOk;
    private javax.swing.JButton btnSoundPlay;
    private javax.swing.JComboBox<String> cbGrade;
    private javax.swing.JComboBox<String> cbPartsOfSpeech;
    private javax.swing.JComboBox<String> cbUnit;
    private javax.swing.JFileChooser fcImage;
    private javax.swing.JFileChooser fcSound;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbExSrc;
    private javax.swing.JLabel lbImageSrc;
    private javax.swing.JLabel lbSoundSrc;
    private javax.swing.JTextArea taExample;
    private javax.swing.JTextArea taMeaning;
    private javax.swing.JTextField txtSpelling;
    private javax.swing.JTextField txtWord;
    // End of variables declaration//GEN-END:variables
}
