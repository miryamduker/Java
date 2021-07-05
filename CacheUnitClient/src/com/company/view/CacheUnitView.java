package com.company.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CacheUnitView extends JPanel implements ActionListener {

    private PropertyChangeSupport support;
    private JButton		StatisticsBtn;
    private JButton		LoadRequestBtn;
    private JTextArea output;
    private String		filePath;
    private  Border border;
    private String json = null;

    public CacheUnitView(){
        super();
        support = new PropertyChangeSupport(this);
        border = BorderFactory.createLineBorder(Color.BLACK);
        ImageIcon upload = new ImageIcon("src/resources/upload.png");
        ImageIcon statisticImage = new ImageIcon("src/resources/statistics.png");
        Image image1= upload.getImage();
        Image image2= statisticImage.getImage();
        Image goodSizeLoadImg= image1.getScaledInstance(20,20, Image.SCALE_SMOOTH);
        Image goodSizeStatisticImg= image2.getScaledInstance(20,20, Image.SCALE_SMOOTH);
        LoadRequestBtn = new JButton("Load a Request",new ImageIcon(goodSizeLoadImg));
        StatisticsBtn = new JButton("Statistics", new ImageIcon(goodSizeStatisticImg));
        LoadRequestBtn.addActionListener(this);
        StatisticsBtn.addActionListener(this);


        output = new JTextArea( 10,40);
        output.setBorder(border);
        output.setEditable(false);
        output.setText("Welcome to our MMU\nTo upload a request please press on the left button\nTo see the statistics please press on the right button");
        Font  f1  = new Font(Font.DIALOG,Font.PLAIN, 18);
        output.setFont(f1);

        add(LoadRequestBtn);
        add(StatisticsBtn);

    }

    /**
     * starts the gui, runs it as a thread
     */
    public void start(){

        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {

                JPanel buttons = new JPanel();
                buttons.add(LoadRequestBtn);
                buttons.add(StatisticsBtn);
                JFrame frame = new JFrame("CacheUnitUI");
                frame.setLocation(600,200);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(buttons);
                frame.getContentPane().add(output,BorderLayout.PAGE_START);
                frame.pack();
                frame.setVisible(true);


            }
        });
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl){
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(java.beans.PropertyChangeListener pcl){
        support.removePropertyChangeListener(pcl);
    }

    public static String readFileAsString(String file)throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        output.setText("");
        if (e.getSource() == LoadRequestBtn) {
            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File("src/resources"));
            FileNameExtensionFilter f = new FileNameExtensionFilter("TEXT FILES", "txt", "text"); // here you choose which files to show
            fc.setFileFilter(f);
            int i = fc.showOpenDialog(this);
            if (i == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                filePath = file.getPath();

                try {
                    json = readFileAsString(filePath);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                this.updateUIData(json);
            }
        }
        if (e.getSource() == StatisticsBtn) {
            support.firePropertyChange("t",null, "getStatistics");
        }
    }

    public <T> void updateUIData(T t){
        if(t.equals("getStatistics") || t.equals(json)){
            support.firePropertyChange("t",null, t);
        }
        else
        {
            output.setText((String) t);
        }

    }

}

