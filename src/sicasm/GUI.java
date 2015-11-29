/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicasm;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Ahmed
 */
public class GUI extends javax.swing.JFrame {

    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainPanel = new javax.swing.JPanel();
        ToolBar = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        TabBar = new javax.swing.JToolBar();
        ObjectButton = new javax.swing.JButton();
        LayeredPane = new javax.swing.JLayeredPane();
        ObjectPanel = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        ObjText = new javax.swing.JTextArea();
        SRCPanel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        SRCText = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        ListText = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        SaveAll = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SicASM Editor v1.0");

        ToolBar.setFloatable(false);
        ToolBar.setRollover(true);
        ToolBar.setRequestFocusEnabled(false);

        jButton1.setText("jButton1");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ToolBar.add(jButton1);

        TabBar.setFloatable(false);
        TabBar.setRollover(true);
        TabBar.setAlignmentY(0.5F);
        TabBar.setPreferredSize(new java.awt.Dimension(100, 25));
        TabBar.setRequestFocusEnabled(false);

        ObjectButton.setText("Object Code");
        ObjectButton.setFocusable(false);
        ObjectButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ObjectButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ObjectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ObjectButtonActionPerformed(evt);
            }
        });
        TabBar.add(ObjectButton);

        LayeredPane.setPreferredSize(new java.awt.Dimension(680, 400));

        ObjectPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 255), new java.awt.Color(153, 153, 255)));

        ObjText.setEditable(false);
        ObjText.setColumns(20);
        ObjText.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ObjText.setRows(5);
        jScrollPane2.setViewportView(ObjText);

        jTabbedPane2.addTab("Object Code", jScrollPane2);

        javax.swing.GroupLayout ObjectPanelLayout = new javax.swing.GroupLayout(ObjectPanel);
        ObjectPanel.setLayout(ObjectPanelLayout);
        ObjectPanelLayout.setHorizontalGroup(
            ObjectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
        );
        ObjectPanelLayout.setVerticalGroup(
            ObjectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
        );

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        SRCText.setColumns(20);
        SRCText.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        SRCText.setRows(5);
        SRCText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                SRCTextFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(SRCText);

        jTabbedPane1.addTab("Source Code", jScrollPane1);

        ListText.setEditable(false);
        ListText.setColumns(20);
        ListText.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ListText.setRows(5);
        jScrollPane4.setViewportView(ListText);

        jTabbedPane1.addTab("List File", jScrollPane4);

        javax.swing.GroupLayout SRCPanelLayout = new javax.swing.GroupLayout(SRCPanel);
        SRCPanel.setLayout(SRCPanelLayout);
        SRCPanelLayout.setHorizontalGroup(
            SRCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
            .addGroup(SRCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE))
        );
        SRCPanelLayout.setVerticalGroup(
            SRCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
            .addGroup(SRCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout LayeredPaneLayout = new javax.swing.GroupLayout(LayeredPane);
        LayeredPane.setLayout(LayeredPaneLayout);
        LayeredPaneLayout.setHorizontalGroup(
            LayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ObjectPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(LayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(SRCPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        LayeredPaneLayout.setVerticalGroup(
            LayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LayeredPaneLayout.createSequentialGroup()
                .addContainerGap(156, Short.MAX_VALUE)
                .addComponent(ObjectPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(LayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(SRCPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        LayeredPane.setLayer(ObjectPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        ObjectPanel.getAccessibleContext().setAccessibleParent(LayeredPane);
        LayeredPane.setLayer(SRCPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        SRCPanel.getAccessibleContext().setAccessibleParent(LayeredPane);

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(LayeredPane, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
            .addComponent(TabBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                .addComponent(ToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LayeredPane, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TabBar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenu1.setText("File");
        jMenu1.add(jSeparator1);

        SaveAll.setText("Save All");
        SaveAll.setContentAreaFilled(false);
        jMenu1.add(SaveAll);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void SRCTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SRCTextFocusGained
        ObjectPanel.setVisible(false);
    }//GEN-LAST:event_SRCTextFocusGained

    private void ObjectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ObjectButtonActionPerformed
        ObjectPanel.setVisible(!ObjectPanel.isVisible());
    }//GEN-LAST:event_ObjectButtonActionPerformed

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
                try{
                    //UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
                    //UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
                    //UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                }catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex){
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane LayeredPane;
    private javax.swing.JTextArea ListText;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JTextArea ObjText;
    private javax.swing.JButton ObjectButton;
    private javax.swing.JPanel ObjectPanel;
    private javax.swing.JPanel SRCPanel;
    private javax.swing.JTextArea SRCText;
    private javax.swing.JMenuItem SaveAll;
    private javax.swing.JToolBar TabBar;
    private javax.swing.JToolBar ToolBar;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    // End of variables declaration//GEN-END:variables
}
