package org.htw.prog2.lfa.gui;

import org.htw.prog2.lfa.Configuration;
import org.htw.prog2.lfa.FormatException;
import org.htw.prog2.lfa.LFAImage;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame {


    private JLabel intensityratiotext = new JLabel("Intensity ratio: ");
    private JLabel intensityratio = new JLabel("N/A");
    private JLabel ratiocutofftext = new JLabel("Ratio cutoff: ");
    private JLabel ratiocutoff = new JLabel("N/A");
    private JLabel resulttext = new JLabel("Result: ");
    private JLabel result = new JLabel("N/A");
    private JLabel backgroundcorrectiontext = new JLabel("Background correction?");
    private JCheckBox backgroundcorrection;
    private Configuration config;
    private LFAImage image;
    private ImagePanel lfa;




    public MainFrame() {
        super("Covid-19 Analysis Tool");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        setSize(250,150);


        initMenuBar();
        initMainWindow();
    }

    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem loadConfiguration = new JMenuItem("Load configuration");

        loadConfiguration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setFileFilter(new FileNameExtensionFilter("ConfigFile only (*.txt)","txt"));

                if (fc.showOpenDialog(getParent()) == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    try {
                        config = new Configuration(file);
                        lfa.setConfig(config);
                        backgroundcorrection.setEnabled(true);
                        revalidate();
                        attemptAnalysis();
                    } catch (IOException | FormatException z) {
                        JOptionPane.showMessageDialog(null, z.getMessage());
                    }
                    ratiocutoff.setText(String.valueOf(config.getRatio()));

                }

            }

        });

        JMenuItem loadLfaImage = new JMenuItem("Load LFA image");
        loadLfaImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setFileFilter(new FileNameExtensionFilter("LFA-File only (*.png)","png"));

                if (fc.showOpenDialog(getParent()) == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    try{
                            image = new LFAImage(file);
                            lfa.setImage(image);
                            repaint();
                            attemptAnalysis();

                    }catch(IOException z){
                        JOptionPane.showMessageDialog(null, z.getMessage());
                    }
                }
            }
        });

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        fileMenu.add(loadConfiguration);
        fileMenu.add(loadLfaImage);
        fileMenu.add(exit);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    private void initMainWindow(){
        lfa = new ImagePanel();
        add(lfa, BorderLayout.CENTER);

        JPanel tb = new JPanel();
        tb.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        c.gridx = 0;
        c.gridy++;
        c.anchor = GridBagConstraints.WEST;
        tb.add(intensityratiotext,c);
        c.gridx = 15;
        tb.add(intensityratio,c);

        c.gridx = 0;
        c.gridy++;
        c.anchor = GridBagConstraints.WEST;
        tb.add(ratiocutofftext,c);
        c.gridx = 15;
        tb.add(ratiocutoff,c);

        c.gridx = 0;
        c.gridy++;
        c.anchor = GridBagConstraints.WEST;
        tb.add(resulttext,c);
        c.gridx = 15;
        tb.add(result,c);

        c.gridx = 0;
        c.gridy++;
        c.anchor = GridBagConstraints.WEST;
        tb.add(backgroundcorrectiontext,c);
        c.gridx = 15;
        tb.add(backgroundcorrection = new JCheckBox(),c);
        backgroundcorrection.setEnabled(false);
        backgroundcorrection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateBackCorrection();
            }
        });

        add(tb, BorderLayout.WEST);


    }

    private void updateBackCorrection(){
        config.setBorderEnabled(backgroundcorrection.isSelected());
        attemptAnalysis();
        revalidate();
        repaint();
    }

    private void attemptAnalysis() {
        if(config != null && image != null) {
            if (backgroundcorrection.isSelected()) {
                double intensecorrected = (image.getAverageIntensityCorrected(config.getTestCoordinates(), config.getBorderWidth()) / image.getAverageIntensityCorrected(config.getControlCoordinates(), config.getBorderWidth()));
                intensityratio.setText(String.format("%.2f", intensecorrected));
                if (intensecorrected > config.getRatio()) {
                    result.setForeground(Color.GREEN);
                    result.setText("Positive");
                } else if (intensecorrected < config.getRatio()) {
                    result.setForeground(Color.RED);
                    result.setText("Negativ");
                }
            } else {
                double intense = (image.getAverageIntensity(config.getTestCoordinates()) / image.getAverageIntensity(config.getControlCoordinates()));
                intensityratio.setText(String.format("%.2f", intense));
                if (intense > config.getRatio()) {
                    result.setForeground(Color.GREEN);
                    result.setText("Positive");
                } else if (intense < config.getRatio()) {
                    result.setForeground(Color.RED);
                    result.setText("Negativ");
                }
            }
        }
    }
}
