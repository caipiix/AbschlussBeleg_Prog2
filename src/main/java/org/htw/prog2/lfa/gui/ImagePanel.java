package org.htw.prog2.lfa.gui;

import org.htw.prog2.lfa.Configuration;
import org.htw.prog2.lfa.LFAImage;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {

    LFAImage image;
    Configuration configuration;

    public ImagePanel() {
        super();
        Dimension size = new Dimension(31,78);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
    }

    public void setImage(LFAImage image) {
        this.image = image;
    }

    public LFAImage getImage() {
        return image;
    }

    public void setConfig(Configuration config) {
        this.configuration = config;
    }

    public void paintComponent(Graphics g) {
    }
}
