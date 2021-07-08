package org.htw.prog2.lfa;

public class FormatException extends Exception {

    String line;
    String error;

    public FormatException(String line, String error) {
        this.line = line;
        this.error = error;
    }

    public String getLine() {
        return line;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return "Wrong format in line '" + line +"': " + error +".";
    }
}
