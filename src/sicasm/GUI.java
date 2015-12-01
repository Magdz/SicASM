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
public final class GUI extends javax.swing.JFrame {

    ReadWriteFile file;
    /**
     * Creates new form GUI
     */
    public GUI() {
        file = new ReadWriteFile();
        Theme();
        initComponents();
    }

    private void Theme(){
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
    }

    public void setListText(String ListText) {
        this.ListText.setText(ListText);
    }

    public void setObjText(String ObjText) {
        this.ObjText.setText(ObjText);
    }

    public void setSRCText(String SRCText) {
        this.SRCText.setText(SRCText);
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
        RunButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        RunSave = new javax.swing.JButton();
        TabBar = new javax.swing.JToolBar();
        ObjectButton = new javax.swing.JButton();
        LayeredPane = new javax.swing.JLayeredPane();
        ObjectPanel = new javax.swing.JPanel();
        ObjTab = new javax.swing.JTabbedPane();
        ObjScroll = new javax.swing.JScrollPane();
        ObjText = new javax.swing.JTextArea();
        SRCPanel = new javax.swing.JPanel();
        Tabs = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        SRCText = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        ListText = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        LoadSRC = new javax.swing.JMenuItem();
        SaveSRCCode = new javax.swing.JMenuItem();
        SaveListFile = new javax.swing.JMenuItem();
        SaveObjProgram = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        SaveAll = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SicASM Editor v1.0");

        ToolBar.setFloatable(false);
        ToolBar.setRollover(true);
        ToolBar.setRequestFocusEnabled(false);

        RunButton.setText("Run");
        RunButton.setFocusable(false);
        RunButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        RunButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        RunButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RunButtonActionPerformed(evt);
            }
        });
        ToolBar.add(RunButton);
        ToolBar.add(jSeparator2);

        RunSave.setText("Run & Save All");
        RunSave.setFocusable(false);
        RunSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        RunSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        RunSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RunSaveActionPerformed(evt);
            }
        });
        ToolBar.add(RunSave);

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
        ObjScroll.setViewportView(ObjText);

        ObjTab.addTab("Object Code", ObjScroll);

        javax.swing.GroupLayout ObjectPanelLayout = new javax.swing.GroupLayout(ObjectPanel);
        ObjectPanel.setLayout(ObjectPanelLayout);
        ObjectPanelLayout.setHorizontalGroup(
            ObjectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ObjTab, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
        );
        ObjectPanelLayout.setVerticalGroup(
            ObjectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ObjTab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
        );

        Tabs.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Tabs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabsMouseClicked(evt);
            }
        });

        SRCText.setColumns(20);
        SRCText.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        SRCText.setRows(5);
        SRCText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                SRCTextMousePressed(evt);
            }
        });
        SRCText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SRCTextKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(SRCText);

        Tabs.addTab("Source Code", jScrollPane1);

        ListText.setEditable(false);
        ListText.setColumns(20);
        ListText.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ListText.setRows(5);
        ListText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                SRCTextMousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(ListText);

        Tabs.addTab("List File", jScrollPane4);

        javax.swing.GroupLayout SRCPanelLayout = new javax.swing.GroupLayout(SRCPanel);
        SRCPanel.setLayout(SRCPanelLayout);
        SRCPanelLayout.setHorizontalGroup(
            SRCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
            .addGroup(SRCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Tabs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE))
        );
        SRCPanelLayout.setVerticalGroup(
            SRCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
            .addGroup(SRCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Tabs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE))
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

        LoadSRC.setText("Load Source Code");
        LoadSRC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadSRCActionPerformed(evt);
            }
        });
        jMenu1.add(LoadSRC);

        SaveSRCCode.setText("Save Source Code");
        SaveSRCCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveSRCCodeActionPerformed(evt);
            }
        });
        jMenu1.add(SaveSRCCode);

        SaveListFile.setText("Save List File");
        SaveListFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveListFileActionPerformed(evt);
            }
        });
        jMenu1.add(SaveListFile);

        SaveObjProgram.setText("Save Object Program");
        SaveObjProgram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveObjProgramActionPerformed(evt);
            }
        });
        jMenu1.add(SaveObjProgram);
        jMenu1.add(jSeparator1);

        SaveAll.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        SaveAll.setText("Save All");
        SaveAll.setContentAreaFilled(false);
        SaveAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveAllActionPerformed(evt);
            }
        });
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

    private void ObjectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ObjectButtonActionPerformed
        ObjectPanel.setVisible(!ObjectPanel.isVisible());
        SRCText.getCaret().setVisible(false);
    }//GEN-LAST:event_ObjectButtonActionPerformed

    private void RunButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RunButtonActionPerformed
        SicASM.run(SRCText.getText());
        Tabs.setSelectedIndex(1);
        ObjectPanel.setVisible(true);
        ObjectPanel.show(true);
    }//GEN-LAST:event_RunButtonActionPerformed

    private void SRCTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SRCTextKeyPressed
        ObjectPanel.setVisible(false);
        ObjectPanel.repaint();
    }//GEN-LAST:event_SRCTextKeyPressed

    private void SRCTextMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SRCTextMousePressed
        ObjectPanel.setVisible(false);
        SRCText.getCaret().setVisible(true);
        ObjectPanel.repaint();
    }//GEN-LAST:event_SRCTextMousePressed

    private void TabsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabsMouseClicked
        ObjectPanel.setVisible(false);
        ObjectPanel.repaint();
    }//GEN-LAST:event_TabsMouseClicked

    private void RunSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RunSaveActionPerformed
        RunButtonActionPerformed(evt);
        this.file.setFileName("SRCFILE");
        this.file.writeFile(SRCText.getText());
        this.file.setFileName("LISFILE");
        this.file.writeFile(ListText.getText());
        this.file.setFileName("OBJFILE");
        this.file.writeFile(ObjText.getText());
        this.file.setFileName("DEVF2");
        this.file.writeFile(ObjText.getText());
        
    }//GEN-LAST:event_RunSaveActionPerformed

    private void SaveAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveAllActionPerformed
        this.file.setFileName("SRCFILE");
        this.file.writeFile(SRCText.getText());
        this.file.setFileName("LISFILE");
        this.file.writeFile(ListText.getText());
        this.file.setFileName("OBJFILE");
        this.file.writeFile(ObjText.getText());
        this.file.setFileName("DEVF2");
        this.file.writeFile(ObjText.getText());
    }//GEN-LAST:event_SaveAllActionPerformed

    private void SaveObjProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveObjProgramActionPerformed
        this.file.setFileName("OBJFILE");
        this.file.writeFile(ObjText.getText());
        this.file.setFileName("DEVF2");
        this.file.writeFile(ObjText.getText());
    }//GEN-LAST:event_SaveObjProgramActionPerformed

    private void SaveListFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveListFileActionPerformed
        this.file.setFileName("LISFILE");
        this.file.writeFile(ListText.getText());
    }//GEN-LAST:event_SaveListFileActionPerformed

    private void LoadSRCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadSRCActionPerformed
        this.file.setFileName("SRCFILE");
        SRCText.setText(this.file.readFile());
    }//GEN-LAST:event_LoadSRCActionPerformed

    private void SaveSRCCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveSRCCodeActionPerformed
        this.file.setFileName("SRCFILE");
        this.file.writeFile(SRCText.getText());
    }//GEN-LAST:event_SaveSRCCodeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane LayeredPane;
    private javax.swing.JTextArea ListText;
    private javax.swing.JMenuItem LoadSRC;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JScrollPane ObjScroll;
    private javax.swing.JTabbedPane ObjTab;
    private javax.swing.JTextArea ObjText;
    private javax.swing.JButton ObjectButton;
    private javax.swing.JPanel ObjectPanel;
    private javax.swing.JButton RunButton;
    private javax.swing.JButton RunSave;
    private javax.swing.JPanel SRCPanel;
    private javax.swing.JTextArea SRCText;
    private javax.swing.JMenuItem SaveAll;
    private javax.swing.JMenuItem SaveListFile;
    private javax.swing.JMenuItem SaveObjProgram;
    private javax.swing.JMenuItem SaveSRCCode;
    private javax.swing.JToolBar TabBar;
    private javax.swing.JTabbedPane Tabs;
    private javax.swing.JToolBar ToolBar;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
