/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vtsengclubvocallearn;

import java.awt.Checkbox;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import trung.dao.GradeDAO;
import trung.dao.UnitDAO;
import trung.dao.WordDAO;
import trung.dto.GradeDTO;
import trung.dto.UnitDTO;
import trung.dto.WordDTO;

/**
 *
 * @author trung
 */
public class TestMenu extends javax.swing.JFrame {

    GradeDAO gradeDao = new GradeDAO();
    UnitDAO unitDao = new UnitDAO();
    WordDAO wordDao = new WordDAO();

    ArrayList<WordDTO> words = new ArrayList<>();
    ArrayList<UnitDTO> units = new ArrayList<>();
    ArrayList<JCheckBox> checkBoxGroup = new ArrayList<>();
    
    public static ArrayList<WordDTO> selectedWords = new ArrayList<>();

    VTSEngClubVocalLearn main;

    /**
     * Creates new form TestMenu
     */
    public TestMenu() {
        initComponents();
        startup();
    }

    public void startup() {
        //Set cbGrade listener
        cbGrades.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    removeAllWord();
                    loadUnitsByGradeSEQ(VTSEngClubVocalLearn.GRADE_LIST.get(cbGrades.getSelectedIndex()).getSEQ());
                }
            }
        });

        //Set cbUnits listener
        cbUnits.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    removeAllWord();
                    loadWordCheckBox(units.get(cbUnits.getSelectedIndex()).getSEQ());
                }
            }
        });

        //Load cbGrade
        cbGrades.removeAllItems();
        for (GradeDTO dto : VTSEngClubVocalLearn.GRADE_LIST) {
            cbGrades.addItem("Grade: " + dto.getNumber());
        }
    }

    //get selected words
    public void getSelectedWords() {
        selectedWords.clear();
        
        for (int i = 0; i < jpWord.getComponentCount(); i++) {
            JCheckBox cb = (JCheckBox) jpWord.getComponent(i);
            if (cb.isSelected()) {
                selectedWords.add(words.get(i));
            }
        }
    }

    //Load word checkbox
    public void loadWordCheckBox(int seq) {
        words = VTSEngClubVocalLearn.WORD_LIST.getWordsByUnitSEQ(seq, VTSEngClubVocalLearn.WBG_LIST);

        jpWord.setLayout(new GridLayout(words.size(), 1));

        checkBoxGroup.clear();

        for (WordDTO dto : words) {
            JCheckBox cb = new JCheckBox(dto.getName());
            checkBoxGroup.add(cb);
            jpWord.add(cb);
        }
    }

    public void removeAllWord() {
        jpWord.removeAll();
        jpWord.updateUI();
    }

    public void loadUnitsByGradeSEQ(int seq) {
        cbUnits.removeAllItems();

        units = VTSEngClubVocalLearn.UNIT_LIST.getUnitsByGradeSEQ(seq);
        for (UnitDTO dto : units) {
            cbUnits.addItem("Unit " + dto.getNumber() + ": " + dto.getName());
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

        cbGrades = new javax.swing.JComboBox<>();
        cbUnits = new javax.swing.JComboBox<>();
        jspWord = new javax.swing.JScrollPane();
        jpWord = new javax.swing.JPanel();
        btnStart = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cbGrades.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbUnits.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jpWordLayout = new javax.swing.GroupLayout(jpWord);
        jpWord.setLayout(jpWordLayout);
        jpWordLayout.setHorizontalGroup(
            jpWordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 513, Short.MAX_VALUE)
        );
        jpWordLayout.setVerticalGroup(
            jpWordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
        );

        jspWord.setViewportView(jpWord);

        btnStart.setText("Start");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspWord)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbGrades, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbUnits, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnStart)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbGrades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbUnits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspWord, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStart)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed

        getSelectedWords();
        
        if (selectedWords.size() > 3) {

            this.setEnabled(false);

            Test test = new Test(); 
            test.setParent(this);
            test.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "At least 4 words");
        }        
    }//GEN-LAST:event_btnStartActionPerformed

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
            java.util.logging.Logger.getLogger(TestMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStart;
    private javax.swing.JComboBox<String> cbGrades;
    private javax.swing.JComboBox<String> cbUnits;
    private javax.swing.JPanel jpWord;
    private javax.swing.JScrollPane jspWord;
    // End of variables declaration//GEN-END:variables
}
