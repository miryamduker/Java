package com.company.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class CacheUnitLayout  extends JPanel implements ActionListener {

    private CacheUnitView gui;
    private JButton		StatisticsBtn;
    private JButton		LoadRequestBtn;
    private JTextArea output;
    private String		filePath;
    private boolean		newReq = false;
    private boolean		newStat = false;
    private String newInput="ggg";

//    private JButton		aboutBtn;

    public CacheUnitLayout(CacheUnitView gui) {
        this.gui = gui;
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        LoadRequestBtn = new JButton("Load a Request");
        StatisticsBtn = new JButton("Statistics");
        LoadRequestBtn.addActionListener(this);
        StatisticsBtn.addActionListener(this);

//        output = new JLabel(20,30);
        output = new JTextArea( 20,20);
        output.setBorder(border);
//        output.setVisible(true);
        //output.setEnabled(false);
        output.setEditable(false);
//        output.setBackground(Color.black);
        Font  f1  = new Font(Font.SERIF, Font.PLAIN,  20);
        output.setFont(f1);


//        aboutBtn = new JButton("About");
//        aboutBtn.addActionListener(this);

        add(LoadRequestBtn);
        add(StatisticsBtn);
//        add(aboutBtn,BorderLayout.PAGE_START);
       add(output,BorderLayout.PAGE_START);

    }

    //@SuppressWarnings("static-access")
    @Override
    public void actionPerformed(ActionEvent e) {

//        if(e.getSource() == aboutBtn)
//        {
//            JOptionPane.showMessageDialog(null, "This project was made by Miryan Duker and Miri Shulman copy this project....", "About this project", JOptionPane.INFORMATION_MESSAGE);
//        }
//        else {
            output.setText("I'm happy to see you....???\n");
            if(e.getSource() == LoadRequestBtn) {
                JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(new File("src/resources"));
                FileNameExtensionFilter f = new FileNameExtensionFilter("TEXT FILES", "txt","text"); // here you choose which files to show
                fc.setFileFilter(f);
                int i = fc.showOpenDialog(this);
                if(i == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    filePath = file.getPath();
                    gui.updateUIData(filePath);
                }
            }
            if(e.getSource() == StatisticsBtn) {
//                setInput("load statics...\n");
                System.out.println("after load statics");
//                setInput(newInput);
                System.out.println("after new input: "+newInput);
                gui.updateUIData("getStatistics");
            }
    }

    public CacheUnitView getGui() { return(gui); }
    public void setGui(CacheUnitView gui) { this.gui = gui; }
    public JButton getStatisticsBtn() { return(StatisticsBtn); }
    public void setStatisticsBtn(JButton statisticsBtn) { StatisticsBtn = statisticsBtn; }
    public JButton getLoadRequestBtn() { return(LoadRequestBtn); }
    public void setLoadRequestBtn(JButton loadRequestBtn) { LoadRequestBtn = loadRequestBtn; }
    public String getFilePath() {
        return(filePath);
    }
//    public void setFilePath(String filePath) {this.filePath = filePath; }
//    public JTextArea getInput() { return(output); }

    public void setInput(String input) {
        System.out.println(output);
        System.out.println("YYYYYYYYYYY");
        System.out.println("ggggg");
//        for (int i =0; i<5; i++){output.append(input);}
        output.setBackground(Color.cyan);
        newInput=input;
//        System.out.println("input:"+input+" new:"+newInput);
        output.setText(newInput);
        output.append(input);
//        this.output.append("\n");
//        this.output.updateUI();
    }

//    public boolean isNewReq() {
//        return newReq;
//    }
//
//    public void setNewReq(boolean newReq) {
//        this.newReq = newReq;
//    }
//
//    public boolean isNewStat() {
//        return newStat;
//    }
//
//    public void setNewStat(boolean newStat) {
//        this.newStat = newStat;
//    }



}
