package org.htw.prog2.lfa;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class LFAImage {

    BufferedImage img;
    double sum;

    public LFAImage(File imagefile) throws IOException {
        try{
            img = ImageIO.read(imagefile);
        }
        catch(IOException e){
            throw new IOException("couldn't open picture" + imagefile);
        }
    }

    private int[] checkCoords(int[] coords){
        if(coords.length == 4){
            for(int i = 0; i < coords.length; i++){
                if(coords[i] < 0){
                  coords[i] = 0;
              }
            }
        }else{
            throw new ArrayIndexOutOfBoundsException("array length !4 wrong coordinateform");
        }
        return coords;
    }

    public double getAverageIntensity(int[] coords) {

        checkCoords(coords);

            int x0 = coords[0];
            int y0 = coords[1];
            int x1 = coords[2];
            int y1 = coords[3];

            double sumr = 0, sumg = 0, sumb = 0;
            int w = x1 - x0;
            int h = y1 - y0;


            for (int x = x0; x <= x1; x++) {
                for (int y = y0; y <= y1; y++) {
                    Color pixel = new Color(img.getRGB(x, y));
                    sumr += pixel.getRed();
                    sumg += pixel.getGreen();
                    sumb += pixel.getBlue();
                }
            }
            int num = (w + 1) * (h + 1);
            sum = (256 - (sumr / num)) + (256 - (sumg / num)) + (256 - (sumb / num));
        return sum;
    }
/*
    public double getAverageIntensityCorrectedhelper(int[] coords, int border) {

        int[] above = Arrays.copyOf(coords, coords.length);
        int[] underneath = Arrays.copyOf(coords, coords.length);


            double sumtest = 0;
            double sumtest2 = 0;

            int x0 = coords[0];
            int y0 = coords[1];
            int x1 = coords[2];
            int y1 = coords[3];

            double sumr = 0, sumg = 0, sumb = 0;
            int w = x1 - x0;
            int h = y1 - y0;


            for (int x = x0; x <= x1; x++) {
                for (int y = y0; y <= y1; y++) {
                    Color pixel = new Color(img.getRGB(x, y));
                    sumr += pixel.getRed();
                    sumg += pixel.getGreen();
                    sumb += pixel.getBlue();
                }
            }
            int num = (w + 1) * (h + 1);
            sumtest = (256 - (sumr / num)) + (256 - (sumg / num)) + (256 - (sumb / num));


        int x0a = above[0];
        int y0a = above[1];
        int x1a = above[2];
        int y1a = above[3];

        int x0u = underneath[0];
        int y0u = underneath[1];
        int x1u = underneath[2];
        int y1u = underneath[3];

        int ynew0a = y0a - border;
        int ynew1u = y1u + border;

        above[1] = ynew0a;
        above[3] = y0a;
        underneath[1] = y1u;
        underneath[3] = ynew1u;

        checkCoords(above);
        checkCoords(underneath);

        double sumar = 0, sumag = 0, sumab = 0;
        int wa = (x1a - x0a)+(x1u - x0u);
        int ha = (y1a - y0a)+(y1u - y0u);

        for (int x = x0a; x <= x1a; x++) {
            for (int y = y0a; y <= y1a; y++) {
                Color pixel = new Color(img.getRGB(x, y));
                sumar += pixel.getRed();
                sumag += pixel.getGreen();
                sumab += pixel.getBlue();
            }
        }
        double sumur = 0, sumug = 0, sumub = 0;
        for (int x = x0u; x <= x1u; x++) {
            for (int y = y0u; y <= y1u; y++) {
                Color pixel = new Color(img.getRGB(x, y));
                sumur += pixel.getRed();
                sumug += pixel.getGreen();
                sumub += pixel.getBlue();
            }
        }
        int numa = (wa + 2) * (ha + 2);
        sumtest2 = (256 - (sumar / numa)) + (256 - (sumag / numa)) + (256 - (sumab / numa));








        return sumtest - sumtest2;
    }
 */
    public double getAverageIntensityCorrected(int[] coords, int border) {

        double sum1;
        double test1;
        double test2;

        int[] above = Arrays.copyOf(coords, coords.length);
        int[] underneath = Arrays.copyOf(coords, coords.length);

        checkCoords(coords);


        sum1 = getAverageIntensity(coords);


        // + coords

            int y0a = above[1];

            int ynew0a = y0a - border;

            above[1] = ynew0a;
            above[3] = y0a-1;

        checkCoords(above);
           test1 =  getAverageIntensity(above);

        // - coords

            int y1u = underneath[3];

            int ynew1u = y1u+1 + border;

            underneath[1] = y1u+1;
            underneath[3] = ynew1u;

        checkCoords(underneath);
           test2 = getAverageIntensity(underneath);

           return sum1-((test1+test2)/2);

    }

    public BufferedImage getImage() {
        return img;
    }
}
