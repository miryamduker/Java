package com.company.util;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class CLI implements Runnable{

    private Scanner in;
    private PrintWriter out;
    private PropertyChangeSupport support;

    /**
     * constructor
     * @param in
     * @param out
     */
    public CLI(InputStream in, OutputStream out){
        this.support = new PropertyChangeSupport(this);
        this.in = new Scanner(in);
        this.out = new PrintWriter(out);
    }



    public void addPropertyChangeListener(PropertyChangeListener observer){
        support.addPropertyChangeListener(observer);
    }

    public void removePropertyChangeListener(PropertyChangeListener observer){
        support.removePropertyChangeListener(observer);
    }
    public void write(String string){
        out.println(string);
        out.flush();
    }

    @Override
    public void run() {
        write("Please enter your command: (START/SHUTDOWN)");
        String input = in.nextLine();
        while (!input.equalsIgnoreCase("Exit")){
            if (input.equalsIgnoreCase("START") || input.equalsIgnoreCase("SHUTDOWN"))
                support.firePropertyChange("input",null ,input);
            else  write("Not a valid command");
            input = in.nextLine();
        }
        support.firePropertyChange("input",null,"SHUTDOWN");
        in.close();

    }
}
