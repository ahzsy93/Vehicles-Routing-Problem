/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * welcome.java
 *
 * Created on July 21, 2018, 11:12:12 PM
 */
package pkg;

import java.awt.Desktop;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javax.swing.JLabel;

/**
 *
 * @author Ali
 */
class VrpData {

    public ArrayList<Candidate> tmpCandidates;
    public ArrayList<Store> tmpStores;

    // New Methods added - Vehicle Analysis
    public VrpData() {
        tmpCandidates = new ArrayList<>();
        tmpStores = new ArrayList<>();
    }

    public int getTotalTrucks(Candidate solution) {
        return solution.routeTable.size();
    }

    public ArrayList<Integer> getTruckPath(Candidate solution, int truckNumber) {
        return solution.routeTable.get(truckNumber - 1).route;
    }

    public double getTruckDistance(Candidate solution, int truckNumber) {
        return solution.routeTable.get(truckNumber - 1).routeFitness;
    }

    public double getTruckDailyCost(Candidate solution, int truckNumber) {
        return solution.routeTable.get(truckNumber - 1).routeFitness * 30;
    }

    // Store Analysis
    public int getStoreDemand(int storeNumber) {
        return tmpStores.get(storeNumber - 1).demand;
    }

    public int getTruckFulfillStore(Candidate solution, int storeNumber) {
        int truckNumber = 0;
        outer:
        for (Route tmp : solution.routeTable) {
            truckNumber++;
            for (Integer store : tmp.route) {
                if (store == storeNumber) {
                    break outer;
                }
            }
        }
        return truckNumber;
    }
     public String getMaintenanceCost(Candidate solution, int months) {
        return 20000 * solution.routeTable.size() * months + "";
    }

    public String getVariableCost(Candidate solution, int days) {
        return Double.parseDouble(getTotalDistance(solution)) * 30 * days + "";
    }

    public String getTotalDistance(Candidate solution) {
        double distance = 0;
        for (Route tmp : solution.routeTable) {
            distance += tmp.routeFitness;
        }
        return distance + "";
    }

    public ArrayList<Candidate> readFromFile() {

        try {
            Candidate tmpCandidate;
            Store tmpStore;
            FileInputStream fin = new FileInputStream("solutions.phy");
            ObjectInputStream ois = new ObjectInputStream(fin);

            FileInputStream finstores = new FileInputStream("stores.phy");
            ObjectInputStream oisstores = new ObjectInputStream(finstores);
      
            int i=0;
            while(true) {
                i++;
                tmpCandidate = (Candidate) ois.readObject();
                tmpCandidates.add(tmpCandidate);
                if(i==10) break;
            }
            i=0;
            while(true) {
                i++; 
                tmpStore = (Store) oisstores.readObject();
                tmpStores.add(tmpStore);
                if(i==601) break;
            }
            ois.close();

            return tmpCandidates;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

public class welcome extends javax.swing.JFrame {

    private javax.swing.JFrame aboutFrame;
    private JLabel name1 = new JLabel();
    private JLabel name2 = new JLabel();
    private JLabel name3 = new JLabel();
    private Font n1 = new Font("Arial", Font.BOLD, 15);
    private Font n2 = new Font("Tahoma", Font.BOLD, 15);
    /**
     * Creates new form welcome
     */
    public welcome() {
        initComponents();
        aboutFrame = new javax.swing.JFrame();
		aboutFrame.setSize(400, 200);
		aboutFrame.setUndecorated(false);
		aboutFrame.setResizable(false);
		aboutFrame.setLocationRelativeTo(null);
		aboutFrame.setLayout(null);
		name1.setText("برمجة وتصميم");
                name1.setBounds(150, 20, 100, 30);
                name1.setFont(n1);
                name2.setText("محمد محسن علي الصيوان");
                name2.setBounds(100, 60, 200, 40);
                name2.setFont(n2);
                name3.setText("علي حسن زاهده");
                name3.setBounds(120, 100, 200, 40);
                name3.setFont(n2);
                aboutFrame.add(name1);
                aboutFrame.add(name2);
                aboutFrame.add(name3);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        about = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Vehicle Route Problem Solution");

        jButton1.setText("Route Evaluation");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Best Available Routes");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Trucks Analysis");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Stores Analysis");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Report Generation");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("ادخال البيانات إلى الخوارزمية الذكية لتحليلها وإظهار النتائج");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("إظهار أفضل الطرق المقترحة من خرج الخوارزمية");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("تحليل أفضل مسار تتبعه شاحنة ما");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("تحليل أسرع شاحنة للوصول إلى متجر وتخديمه");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("طباعة تقرير بأفضل 10 حلول مقترحة");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Genetic algorithm");

        about.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        about.setText("About");
        about.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(159, 159, 159)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(242, 242, 242)
                                .addComponent(jLabel7)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(about)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jLabel6))
                .addGap(29, 29, 29)
                .addComponent(about)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        bestroutes b = new bestroutes();
        b.main();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        VrpVarForm o = new VrpVarForm();
        o.main();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        TrucksAnalysis t = new TrucksAnalysis();
        t.main();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        StoreAnalysis s = new StoreAnalysis();
        s.main();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
         ReportGeneration r = new ReportGeneration();
        r.main();
        File file = new File("Report.pdf");
        try{
            Desktop.getDesktop().open(file);
            }
            catch(IOException e)
            {
                
            }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void aboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutActionPerformed
        // TODO add your handling code here:
        aboutFrame.setVisible(true);
    }//GEN-LAST:event_aboutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String [] args) {
                /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(bestroutes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(bestroutes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(bestroutes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(bestroutes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new welcome().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton about;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}
