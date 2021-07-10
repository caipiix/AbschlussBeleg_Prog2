package org.htw.prog2.lfa.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    public MainFrame() {
        super("Covid-19 Analysis Tool");
        setSize(400,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initMenuBar();
        //pack();

    }

    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem about = new JMenuItem("About");
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "HIV-Diagnostik-Tool für Proteomdaten.");
            }
        });

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        fileMenu.add(about);
        fileMenu.add(exit);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    private void attemptAnalysis() {
    }
}
