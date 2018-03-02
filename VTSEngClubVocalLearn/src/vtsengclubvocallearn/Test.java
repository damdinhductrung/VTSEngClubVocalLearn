/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vtsengclubvocallearn;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import trung.dto.WordDTO;

/**
 *
 * @author Trung
 */
public class Test extends javax.swing.JFrame {

    JPanel imageQues = new JPanel(new GridLayout(1, 1));
    JLabel lbImage = new JLabel();

    ArrayList<WordDTO> testWordsList = TestMenu.selectedWords;
    ArrayList<TestWord> quesWordsList = new ArrayList<>();

    JPanel meaningQues = new JPanel(new GridLayout(1, 1));
    JLabel lbMeaning = new JLabel();

    final int NUM_OF_TEST_PER_WORD = 2;
    int PERCENT_PER_QUES = Math.round(100 / (testWordsList.size() * 2));
    int testWordIndex = 0;
    int rightButtonNumber = 0;
    
    int countright = 0;
    
    TestMenu parent;

    /**
     * Creates new form Test
     */
    public Test() {
        initComponents();
        startup();
    }

    public void startup() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //Set Image for imageQues
        imageQues.add(lbImage);

        //Set Meaning for meaningQues
        lbMeaning.setFont(new Font("Tahoma", Font.PLAIN, 100));
        meaningQues.add(lbMeaning);

        makeRandomQues();

        next();
//        try {
//            File imageSrc = new File(wDto.getImageSrc());
//            Image bufferImage = ImageIO.read(imageSrc);
//            ImageIcon icon = new ImageIcon(bufferImage);
//            lbImage.setSize(icon.getIconWidth(), icon.getIconHeight());
//            lbImage.setIcon(icon);
//            lbImage.updateUI();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
    
    public void setParent(TestMenu parent) {
        this.parent = parent;
    }

    public void next() {
        switch (quesWordsList.get(testWordIndex).getTestType()) {
            case TestWord.IMAGE_QUES:
                System.out.println("image");
                setImageQuesAndAns();
                break;
            case TestWord.MEANING_QUES:
                System.out.println("meaning");
                setMeaningQuesAndAns();
                break;
        }

        testWordIndex++;
    }

    public void setImageQuesAndAns() {
        ArrayList<WordDTO> wrongAns = makeAns();

        //Set image
        try {
            jpQuestion.removeAll();

            TestWord rightWord = quesWordsList.get(testWordIndex);
            File imageSrc = new File(rightWord.getImageSrc());
            Image bufferImage = ImageIO.read(imageSrc);
            ImageIcon icon = new ImageIcon(bufferImage);
            lbImage.setSize(icon.getIconWidth(), icon.getIconHeight());
            lbImage.setIcon(icon);
            jpQuestion.add(imageQues);
            jpQuestion.updateUI();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setButtons(wrongAns);
    }

    public void setMeaningQuesAndAns() {
        ArrayList<WordDTO> wrongAns = makeAns();

        jpQuestion.removeAll();
        TestWord tw = quesWordsList.get(testWordIndex);

        lbMeaning.setText(tw.getMeaning());
        jpQuestion.add(meaningQues);
        jpQuestion.updateUI();

        setButtons(wrongAns);
    }

    public void setButtons(ArrayList<WordDTO> wrongAns) {
        int wrongIndex = 0;
        rightButtonNumber = new Random().nextInt(4);
        for (int i = 0; i < 4; i++) {
            JButton button = (JButton) jpButtons.getComponent(i);
            if (i == rightButtonNumber) {
                Integer seq = quesWordsList.get(testWordIndex).getSEQ();
                button.setText(quesWordsList.get(testWordIndex).getName());
            } else {
                button.setText(wrongAns.get(wrongIndex).getName());
                wrongIndex++;
            }
        }
    }

    public void makeRandomQues() {

        for (WordDTO dto : testWordsList) {
            TestWord tw1 = new TestWord(dto.getSEQ(), dto.getName(), dto.getImageSrc(), dto.getMeaning(), TestWord.IMAGE_QUES);
            quesWordsList.add(tw1);

            TestWord tw2 = new TestWord(dto.getSEQ(), dto.getName(), dto.getImageSrc(), dto.getMeaning(), TestWord.MEANING_QUES);
            quesWordsList.add(tw2);
        }

        Collections.shuffle(quesWordsList);
    }

    public ArrayList<WordDTO> makeAns() {
        ArrayList<WordDTO> result = new ArrayList<>(testWordsList);
//        Collections.copy(result, testWordsList);

        for (int j = 0; j < result.size(); j++) {
            if (result.get(j).getSEQ() == quesWordsList.get(testWordIndex).getSEQ()) {
                result.remove(j);
                break;
            }
        }

        Collections.shuffle(result);

        return result;
    }

    public void check(int ansButtonNumber) {
        ansButtonNumber--;

        if (ansButtonNumber == rightButtonNumber) {
            countright++;
            System.out.println("right");
            jpButtons.setBackground(Color.green);
        } else {
            System.out.println("wrong");
            jpButtons.setBackground(Color.red);
        }
        
        System.out.println("ques size: " + quesWordsList.size());
        System.out.println("ques index: " + testWordIndex);
        if (testWordIndex < quesWordsList.size()) {
            next();
            processBarIncrease();
        } else {
            JOptionPane.showMessageDialog(this, "Right " + countright + "/" + quesWordsList.size() + " question");
        }

    }

    public void processBarIncrease() {
        int add = ((100 - jpb.getValue()) / (quesWordsList.size() - (testWordIndex - 1)));
        jpb.setValue(jpb.getValue() + add);
    }

    class TestWord {

        public final static int IMAGE_QUES = 1;
        public final static int MEANING_QUES = 2;

        private int SEQ;
        private String name;
        private String imageSrc;
        private String meaning;
        private int testType;

        public TestWord() {
        }

        public TestWord(int SEQ, String name, String imageSrc, String meaning, int testType) {
            this.SEQ = SEQ;
            this.name = name;
            this.imageSrc = imageSrc;
            this.meaning = meaning;
            this.testType = testType;
        }

        public int getSEQ() {
            return SEQ;
        }

        public void setSEQ(int SEQ) {
            this.SEQ = SEQ;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImageSrc() {
            return imageSrc;
        }

        public void setImageSrc(String imageSrc) {
            this.imageSrc = imageSrc;
        }

        public String getMeaning() {
            return meaning;
        }

        public void setMeaning(String meaning) {
            this.meaning = meaning;
        }

        public int getTestType() {
            return testType;
        }

        public void setTestType(int testType) {
            this.testType = testType;
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jpButtons = new javax.swing.JPanel();
        btnA = new javax.swing.JButton();
        btnB = new javax.swing.JButton();
        btnC = new javax.swing.JButton();
        btnD = new javax.swing.JButton();
        jpQuestion = new javax.swing.JPanel();
        jpb = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        btnA.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnA.setText("jButton1");
        btnA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAActionPerformed(evt);
            }
        });

        btnB.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnB.setText("jButton1");
        btnB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBActionPerformed(evt);
            }
        });

        btnC.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnC.setText("jButton1");
        btnC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCActionPerformed(evt);
            }
        });

        btnD.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnD.setText("jButton1");
        btnD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpButtonsLayout = new javax.swing.GroupLayout(jpButtons);
        jpButtons.setLayout(jpButtonsLayout);
        jpButtonsLayout.setHorizontalGroup(
            jpButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnA, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnB, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jpButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpButtonsLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnC, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnD, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jpButtonsLayout.setVerticalGroup(
            jpButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnA, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnB, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(132, Short.MAX_VALUE))
            .addGroup(jpButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpButtonsLayout.createSequentialGroup()
                    .addGap(132, 132, 132)
                    .addGroup(jpButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnC, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnD, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jpQuestion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jpQuestion.setLayout(new javax.swing.BoxLayout(jpQuestion, javax.swing.BoxLayout.LINE_AXIS));

        jpb.setForeground(new java.awt.Color(51, 255, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpQuestion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 287, Short.MAX_VALUE)
                .addComponent(jpButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAActionPerformed
        check(1);
    }//GEN-LAST:event_btnAActionPerformed

    private void btnBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBActionPerformed
        check(2);
    }//GEN-LAST:event_btnBActionPerformed

    private void btnCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCActionPerformed
        check(3);
    }//GEN-LAST:event_btnCActionPerformed

    private void btnDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDActionPerformed
        check(4);
    }//GEN-LAST:event_btnDActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        parent.setEnabled(true);
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
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Test().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnA;
    private javax.swing.JButton btnB;
    private javax.swing.JButton btnC;
    private javax.swing.JButton btnD;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jpButtons;
    private javax.swing.JPanel jpQuestion;
    private javax.swing.JProgressBar jpb;
    // End of variables declaration//GEN-END:variables
}
