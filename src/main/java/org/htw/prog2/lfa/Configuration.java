package org.htw.prog2.lfa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Configuration {

    static int[] controlcoords = null;
    static int[] testcoords = null;
    static double ratio = 0;
    int border = 10;
    boolean enabled = false;

    public Configuration(File configfile) throws IOException, FormatException {
        BufferedReader reader = new BufferedReader(new FileReader(configfile));

        String line;
        while ((line = reader.readLine()) != null){
            String[] sline = line.split(" ");
            if(sline[0].equals("control")){
                String[] temp = line.split(" ");
                if(temp.length == 5) {
                    controlcoords = new int[temp.length-1];
                    for (int i = 1; i < temp.length; i++) {
                        if(temp[i].matches("(\\d+)")){
                            controlcoords[i-1] = Integer.parseInt(temp[i]);
                        }else{
                            throw new FormatException(line,"one of the coordinates could not be parsed as an integer");
                        }
                    }
                }else{
                    throw new FormatException(line,"wrong number of fields (expected 5: control)");
                }
                continue;
            }
            if(sline[0].equals("test")){
                String[] temp = line.split(" ");
                if(temp.length == 5) {
                    testcoords = new int[temp.length-1];
                    for (int i = 1; i < temp.length; i++) {
                        if(temp[i].matches("(\\d+)")){
                            testcoords[i-1] = Integer.parseInt(temp[i]);
                        }else{
                            throw new FormatException(line,"one of the coordinates could not be parsed as an integer");
                        }
                    }
                }else{
                    throw new FormatException(line,"wrong number of fields (expected 5: test <upper left x> <upper left y> <lower right x> <lower right y>)");
                }
                continue;
            }
            if(sline[0].equals("ratio")){
                String[] temp = line.split(" ");
                if(temp.length == 2) {
                    try {
                        ratio = Double.parseDouble(temp[1]);
                    } catch (NumberFormatException e) {
                        throw new FormatException(line, "ratio could not be parsed as a double");
                    }
                }else{
                    throw new FormatException(line,"wrong number of fields (expected 2: ratio <ratio>)");
                }
            }else{
                throw new FormatException(line,"unknown name '" + sline[0] + "'");
            }
        }
        reader.close();
    }


    public int[] getControlCoordinates() {
        return controlcoords;
    }

    public int[] getTestCoordinates() {
        return testcoords;
    }

    public double getRatio() {
        return ratio;
    }

    public boolean isBorderEnabled() {
        return enabled;
    }

    public void setBorderEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getBorderWidth() {
        return border;
    }
}
