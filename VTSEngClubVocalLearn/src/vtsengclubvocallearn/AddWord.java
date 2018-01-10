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
import trung.dto.GradeDTO;
import trung.dto.UnitDTO;
import trung.dto.WordDTO;
import util.MyUtil;

/**
 *
 * @author Trung
 */
public class AddWord extends javax.swing.JPanel {

    File music;
    Media media;
    MediaPlayer mediaPlayer;
    BufferedImage bufferImage;
    JLabel picLabel;
    JPanel jpImage;
    File imageDes;
    GradeDAO gradeDao = new GradeDAO();
    UnitDAO unitDao = new UnitDAO();
    ArrayList<GradeDTO> grades = new ArrayList<>();
    ArrayList<UnitDTO> units = new ArrayList<>();

    /**
     * Creates new form AddWord
     */
    public AddWord() {
        initComponents();
        firstStartup();
    }

    public void firstStartup() {

        txtWord.setText("");
        txtSpelling.setText("");
        taMeaning.setText("");
        cbPartsOfSpeech.setSelectedIndex(0);

        fcImage.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "png"));
        fcSound.setFileFilter(new FileNameExtensionFilter("Sound files", "MP3", "wav"));
        fcExample.setFileFilter(new FileNameExtensionFilter("Sound files", "MP3", "wav"));

        music = new File("D:\\music.mp3");
        media = new Media(music.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        
        loadGrades();
    }

    public WordDTO getInfo() {
        WordDTO result = new WordDTO();

        result.setName(txtWord.getText());
        result.setSpelling(txtSpelling.getText());
        result.setSpellingSrc(music.getPath());
        result.setPartsOfSpeech(cbPartsOfSpeech.getSelectedItem().toString());
        result.setImageSrc(imageDes.getPath());
        result.setMeaning(taMeaning.getText());
        result.setExample(taExample.getText());
//        result.setExSoundSrc(TOOL_TIP_TEXT_KEY);
        result.setGradeSEQ(grades.get(cbGrade.getSelectedIndex()).getSEQ());
        result.setUnitSEQ(units.get(cbUnit.getSelectedIndex()).getSEQ());
        
        return result;
    }

    private void setSound() {
        mediaPlayer.stop();
        btnSoundPlay.setText("Play");

        File sound = fcSound.getSelectedFile();
        File des = new File("src\\resources\\sound\\" + sound.getName());

        lbSoundSrc.setText(sound.getName());
        try {
            Files.copy(sound.toPath(), des.toPath(), StandardCopyOption.REPLACE_EXISTING);

            music = new File(des.getPath());
            media = new Media(music.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void playSound() {
        if (mediaPlayer.getStatus() == MediaPlayer.Status.READY || mediaPlayer.getStatus() == MediaPlayer.Status.STOPPED) {
            mediaPlayer.play();
            btnSoundPlay.setText("Stop");
        } else {
            mediaPlayer.stop();
            btnSoundPlay.setText("Play");
        }
    }

    public void stopSound() {
        mediaPlayer.stop();
    }

    private void setImage() { //can xem lai ve viec dat ten cho image
        File image = fcImage.getSelectedFile();
        imageDes = new File("src\\resources\\image\\" + image.getName());
        
        lbImageSrc.setText(image.getName());

        
        try {
            Image originImage = ImageIO.read(image);
            
            Double newWeight = 600.0 * ((double)originImage.getHeight(this) / (double)originImage.getWidth(this));
            
            BufferedImage resizeImage = MyUtil.createResizedCopy(originImage, 600, newWeight.intValue(), true);
            
            ImageIO.write(resizeImage, "jpg", imageDes);
                            
            bufferImage = ImageIO.read(imageDes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void previewImage() {
        picLabel = new JLabel(new ImageIcon(bufferImage));
        jpImage = new JPanel();
        jpImage.add(picLabel);
        jpImage.repaint();

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
        fcExample = new javax.swing.JFileChooser();
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
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbGrade = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cbUnit = new javax.swing.JComboBox<>();
        btnSoundPlay = new javax.swing.JButton();
        lbSoundSrc = new javax.swing.JLabel();
        lbImageSrc = new javax.swing.JLabel();
        btnImagePreview = new javax.swing.JButton();

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

        jButton1.setText("...");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtWord)
                                    .addComponent(txtSpelling)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbImageSrc)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnAddImage)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnImagePreview)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(52, 52, 52))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbPartsOfSpeech, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbSoundSrc)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnAddSound)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnSoundPlay)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1)
                                .addGap(52, 52, 52))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbGrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtWord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtSpelling, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(btnAddSound)
                    .addComponent(btnSoundPlay)
                    .addComponent(lbSoundSrc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbPartsOfSpeech, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(btnAddImage)
                    .addComponent(lbImageSrc)
                    .addComponent(btnImagePreview))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(cbGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
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

    private void btnSoundPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSoundPlayActionPerformed
        playSound();
    }//GEN-LAST:event_btnSoundPlayActionPerformed

    private void btnImagePreviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImagePreviewActionPerformed
        previewImage();
    }//GEN-LAST:event_btnImagePreviewActionPerformed

    private void cbGradeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbGradeItemStateChanged
        if (cbGrade.getSelectedIndex() > -1) {
            loadUnitByGradeSEQ(grades.get(cbGrade.getSelectedIndex()).getSEQ());
        }
    }//GEN-LAST:event_cbGradeItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddImage;
    private javax.swing.JButton btnAddSound;
    private javax.swing.JButton btnImagePreview;
    private javax.swing.JButton btnSoundPlay;
    private javax.swing.JComboBox<String> cbGrade;
    private javax.swing.JComboBox<String> cbPartsOfSpeech;
    private javax.swing.JComboBox<String> cbUnit;
    private javax.swing.JFileChooser fcExample;
    private javax.swing.JFileChooser fcImage;
    private javax.swing.JFileChooser fcSound;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbImageSrc;
    private javax.swing.JLabel lbSoundSrc;
    private javax.swing.JTextArea taExample;
    private javax.swing.JTextArea taMeaning;
    private javax.swing.JTextField txtSpelling;
    private javax.swing.JTextField txtWord;
    // End of variables declaration//GEN-END:variables
}
