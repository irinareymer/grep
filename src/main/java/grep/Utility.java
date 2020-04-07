package main.java.grep;

import java.io.File;

public class Utility {
    private boolean inver;
    private boolean insens;
    private boolean regex;
    private String word;
    private File file;

    public Utility(boolean inver, boolean insens, boolean regex, String word, File file){
        this.inver = inver;
        this.insens = insens;
        this.regex = regex;
        this.word = word;
        this.file = file;
    }

    //private boolean askedActions()

    //public String fileReader(File file, String word){
        //if (file.canRead())
    //}

}
