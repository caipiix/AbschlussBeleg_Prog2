package org.htw.prog2.lfa;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class LFAImage {

    private final BufferedImage img;

    public LFAImage(File imagefile) throws IOException {
        try{
            img = ImageIO.read(imagefile);
        }
        catch(IOException e){
            throw new IOException("couldn't open picture" + imagefile);
        }
    }

    public static void checkCoords(int[] coords){
        if(coords.length == 4){
            for(int i = 0; i < coords.length; i++){
                if(coords[i] < 0){
                  coords[i] = 0;
              }
            }
        }else{
            throw new ArrayIndexOutOfBoundsException("array length !4 wrong form of coordinates");
        }
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
        return (256 - (sumr / num)) + (256 - (sumg / num)) + (256 - (sumb / num));
    }


    public double getAverageIntensityCorrected(int[] coords, int border) {

        double sum1;
        double test1;
        double test2;

        int[] above = Arrays.copyOf(coords, coords.length);
        int[] underneath = Arrays.copyOf(coords, coords.length);

        sum1 = getAverageIntensity(coords);


        // + coords

            int y0a = above[1];

            int ynew0a = y0a - border;

            above[1] = ynew0a;
            above[3] = y0a-1;

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
