/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vtsengclubvocallearn;

import java.awt.Image;
import java.io.File;
import java.util.Map;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import trung.dao.WordDAO;
import trung.dto.GradeDTO;
import trung.entities.GradeList;
import trung.dto.UnitDTO;
import trung.entities.UnitList;
import trung.dto.WordByGradeDTO;
import trung.entities.WordByGradeList;
import trung.dto.WordDTO;
import trung.entities.WordList;

/**
 *
 * @author Trung
 */
public class Dictionary extends javax.swing.JFrame {

    final JFXPanel fxPanel = new JFXPanel();
    
    final DefaultListModel wordListModel = new DefaultListModel();

    /**
     * Creates new form Dictionary
     */
    public Dictionary() {
        initComponents();

        startup();
    }
    
    public void startup() {
        //Thiet lap moi truong fx
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
            }
        });
        
        //Load all list
        loadAllList();
        
        //Set jlWord model
        jlWords.setModel(wordListModel);

        //Set jlWords listener
        jlWords.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    showWordDetail();
                }
            }
        });
    }

    public void showWordDetail() {
        WordDTO wDto = VTSEngClubVocalLearn.WORD_LIST.get(jlWords.getSelectedIndex());
        WordByGradeDTO wbgDto = VTSEngClubVocalLearn.WBG_LIST.getWBGByWordSEQ(wDto.getSEQ());
        UnitDTO uDto = VTSEngClubVocalLearn.UNIT_LIST.getUnitBySEQ(wbgDto.getUnitSEQ());
        GradeDTO gDto = VTSEngClubVocalLearn.GRADE_LIST.getGradeBySEQ(uDto.getGradeSEQ());
        lbDWord.setText(wDto.getName());
        lbDPartsOfSpeech.setText(wDto.getPartsOfSpeech());
        lbDSpelling.setText(wDto.getSpelling());

        try {
            File imageSrc = new File(wDto.getImageSrc());
            Image bufferImage = ImageIO.read(imageSrc);
            ImageIcon icon = new ImageIcon(bufferImage);
            lbImage.setSize(icon.getIconWidth(), icon.getIconHeight());
            lbImage.setIcon(icon);
            lbImage.updateUI();
        } catch (Exception e) {
            e.printStackTrace();
        }

        lbDGrade.setText("Grade: " + VTSEngClubVocalLearn.GRADE_LIST.get(VTSEngClubVocalLearn.UNIT_LIST.get(wbgDto.getUnitSEQ()).getGradeSEQ()).getNumber());
        lbDUnit.setText("Unit " + uDto.getNumber() + ": " + uDto.getName());
        taDMeaning.setText(wDto.getMeaning());
    }

    public void loadAllList() {
        wordListModel.removeAllElements();
        
        for (WordDTO dto : VTSEngClubVocalLearn.WORD_LIST) {
            wordListModel.addElement(dto.getName());
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

        jPanel1 = new javax.swing.JPanel();
        lbDWord = new javax.swing.JLabel();
        lbDPartsOfSpeech = new javax.swing.JLabel();
        lbDSpelling = new javax.swing.JLabel();
        btnDPlay = new javax.swing.JButton();
        lbImage = new javax.swing.JLabel();
        lbDGrade = new javax.swing.JLabel();
        lbDUnit = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taDMeaning = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        btnSearchWord = new javax.swing.JButton();
        btnShowAddForm = new javax.swing.JButton();
        btnUpdateWord = new javax.swing.JButton();
        btnDeleteWord = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlWords = new javax.swing.JList<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("VTSEngClub Distionary");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Meaning"));

        lbDWord.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbDWord.setText("Word");

        lbDPartsOfSpeech.setText("parts of speech");

        lbDSpelling.setText("spelling");

        btnDPlay.setText("Play");

        lbImage.setBackground(new java.awt.Color(153, 153, 153));
        lbImage.setBorder(new javax.swing.border.MatteBorder(null));
        lbImage.setPreferredSize(new java.awt.Dimension(300, 300));

        lbDGrade.setText("Grade");

        lbDUnit.setText("Unit");

        taDMeaning.setBackground(new java.awt.Color(204, 204, 204));
        taDMeaning.setColumns(20);
        taDMeaning.setRows(5);
        jScrollPane2.setViewportView(taDMeaning);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDWord, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbDSpelling, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(btnDPlay))
                            .addComponent(lbDPartsOfSpeech)
                            .addComponent(lbDGrade)
                            .addComponent(lbDUnit))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbDWord, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbDSpelling, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDPlay))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbDPartsOfSpeech, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbDGrade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbDUnit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Word"));

        btnSearchWord.setText("Search");

        btnShowAddForm.setText("New");
        btnShowAddForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowAddFormActionPerformed(evt);
            }
        });

        btnUpdateWord.setText("Update");

        btnDeleteWord.setText("Delete");

        jlWords.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jlWords);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btnShowAddForm, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpdateWord, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDeleteWord, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane1)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearchWord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchWord))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnShowAddForm)
                    .addComponent(btnUpdateWord)
                    .addComponent(btnDeleteWord))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE))
        );

        jMenuBar1.setPreferredSize(new java.awt.Dimension(66, 30));

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnShowAddFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowAddFormActionPerformed
        AddWordForm addWordForm = new AddWordForm();
        addWordForm.setDictionary(this);
        addWordForm.setVisible(true);
        this.setEnabled(false);
    }//GEN-LAST:event_btnShowAddFormActionPerformed

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
            java.util.logging.Logger.getLogger(Dictionary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dictionary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dictionary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dictionary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dictionary().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDPlay;
    private javax.swing.JButton btnDeleteWord;
    private javax.swing.JButton btnSearchWord;
    private javax.swing.JButton btnShowAddForm;
    private javax.swing.JButton btnUpdateWord;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> jlWords;
    private javax.swing.JLabel lbDGrade;
    private javax.swing.JLabel lbDPartsOfSpeech;
    private javax.swing.JLabel lbDSpelling;
    private javax.swing.JLabel lbDUnit;
    private javax.swing.JLabel lbDWord;
    private javax.swing.JLabel lbImage;
    private javax.swing.JTextArea taDMeaning;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
