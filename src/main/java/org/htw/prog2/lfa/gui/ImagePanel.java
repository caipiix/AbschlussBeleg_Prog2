package org.htw.prog2.lfa.gui;

import org.htw.prog2.lfa.Configuration;
import org.htw.prog2.lfa.LFAImage;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class ImagePanel extends JPanel {

    private LFAImage img;
    private final JPanel imagePanel;
    private Configuration config;


    public ImagePanel() {
        imagePanel = new JPanel();
        imagePanel.setSize(31, 78);
    }

    public void setImage(LFAImage image) {
        img = image;
    }

    public LFAImage getImage() {
        return img;
    }

    public void setConfig(Configuration configuration) {
        config = configuration;
    }

    public void reformCoordsTop(int[] coords) {
        int y0a = coords[1];
        int ynew0a = y0a - config.getBorderWidth();
        coords[1] = ynew0a;
        coords[3] = y0a - 1;
        LFAImage.checkCoords(coords);
    }

    public void reformCoordsBot(int[] coords) {
        int y1u = coords[3];
        int ynew1u = y1u + 1 + config.getBorderWidth();
        coords[1] = y1u + 1;
        coords[3] = ynew1u;
        LFAImage.checkCoords(coords);
    }

    @Override
    public void paintComponent(Graphics g) {

        int x = super.getWidth() / 2;
        int y = ((super.getHeight() / 2) - imagePanel.getHeight() / 2);

        if (img == null && config == null) {
            g.setColor(Color.WHITE);
            g.fillRect(x, y, 31, 78);
            g.setColor(Color.BLACK);
            g.drawString("N/A", ((super.getWidth() / 2)+5), getHeight() / 2);
        } else if (img != null && config == null) {
            g.drawImage(img.getImage(), x, y, 31, 78, this);
        } else if (img == null) {
            if (config.isBorderEnabled()) {
                g.setColor(Color.WHITE);
                g.fillRect(x, y, 31, 78);

                g.setColor(Color.BLUE);
                int[] configu = config.getControlCoordinates();
                int[] testu = config.getTestCoordinates();

                // Configuration
                g.drawRect(x + configu[0], y + configu[1], (configu[2] - configu[0]), (configu[3] - configu[1]));

                // Above Configuration
                g.setColor(Color.BLACK);
                int[] aboveconfig = Arrays.copyOf(configu, configu.length);
                reformCoordsTop(aboveconfig);
                g.drawRect(x + aboveconfig[0], y + aboveconfig[1], aboveconfig[2] - aboveconfig[0], aboveconfig[3] - aboveconfig[1]);

                // Underneath Configuration
                int[] underneathconfig = Arrays.copyOf(configu, configu.length);
                reformCoordsBot(underneathconfig);
                g.drawRect(x + underneathconfig[0], y + underneathconfig[1], underneathconfig[2] - underneathconfig[0], underneathconfig[3] - underneathconfig[1]);

                // Test
                g.drawRect(x + testu[0], y + testu[1], (testu[2] - testu[0]), (testu[3] - testu[1]));

                // Above Test
                int[] abovetest = Arrays.copyOf(testu, testu.length);
                reformCoordsTop(abovetest);
                g.drawRect(x + abovetest[0], y + abovetest[1], abovetest[2] - abovetest[0], abovetest[3] - abovetest[1]);

                //Underneath Test
                int[] underneathtest = Arrays.copyOf(testu, testu.length);
                reformCoordsBot(underneathtest);
                g.drawRect(x + underneathtest[0], y + underneathtest[1], underneathtest[2] - underneathtest[0], underneathtest[3] - underneathtest[1]);

            } else {
                g.setColor(Color.WHITE);
                g.fillRect(x, y, 31, 78);

                g.setColor(Color.BLUE);
                int[] configu = config.getControlCoordinates();
                int[] testu = config.getTestCoordinates();
                g.drawRect(x + configu[0], y + configu[1], (configu[2] - configu[0]), (configu[3] - configu[1]));
                g.drawRect(x + testu[0], y + testu[1], (testu[2] - testu[0]), (testu[3] - testu[1]));
            }
        } else {
            if (config.isBorderEnabled()) {
                g.setColor(Color.WHITE);
                g.fillRect(x, y, 31, 78);

                g.setColor(Color.BLUE);
                int[] configu = config.getControlCoordinates();
                int[] testu = config.getTestCoordinates();
                g.drawImage(img.getImage(), x, y, 31, 78, this);

                // Configuration
                g.drawRect(x + configu[0], y + configu[1], (configu[2] - configu[0]), (configu[3] - configu[1]));

                // Above Configuration
                g.setColor(Color.BLACK);
                int[] aboveconfig = Arrays.copyOf(configu, configu.length);
                reformCoordsTop(aboveconfig);
                g.drawRect(x + aboveconfig[0], y + aboveconfig[1], aboveconfig[2] - aboveconfig[0], aboveconfig[3] - aboveconfig[1]);

                // Underneath Configuration
                int[] underneathconfig = Arrays.copyOf(configu, configu.length);
                reformCoordsBot(underneathconfig);
                g.drawRect(x + underneathconfig[0], y + underneathconfig[1], underneathconfig[2] - underneathconfig[0], underneathconfig[3] - underneathconfig[1]);

                // Test
                g.drawRect(x + testu[0], y + testu[1], (testu[2] - testu[0]), (testu[3] - testu[1]));

                // Above Test
                int[] abovetest = Arrays.copyOf(testu, testu.length);
                reformCoordsTop(abovetest);
                g.drawRect(x + abovetest[0], y + abovetest[1], abovetest[2] - abovetest[0], abovetest[3] - abovetest[1]);

                //Underneath Test
                int[] underneathtest = Arrays.copyOf(testu, testu.length);
                reformCoordsBot(underneathtest);
                g.drawRect(x + underneathtest[0], y + underneathtest[1], underneathtest[2] - underneathtest[0], underneathtest[3] - underneathtest[1]);


            } else {
                g.setColor(Color.WHITE);
                g.fillRect(x, y, 31, 78);

                g.setColor(Color.BLUE);
                int[] configu = config.getControlCoordinates();
                int[] testu = config.getTestCoordinates();
                g.drawImage(img.getImage(), x, y, 31, 78, this);
                g.drawRect(x + configu[0], y + configu[1], (configu[2] - configu[0]), (configu[3] - configu[1]));
                g.drawRect(x + testu[0], y + testu[1], (testu[2] - testu[0]), (testu[3] - testu[1]));
            }
        }
    }
}
