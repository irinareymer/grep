package main.java.grep;


import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;

public class Main {

    @Option(name = "-v", usage = "inverts the filter condition")
    private boolean inver;

    @Option(name = "-i", usage = "case insensitive")
    private boolean insens;

    @Option(name = "-r", usage = "instead of a word, sets the regex for searching")
    private boolean regex;

    @Argument(required = true, usage = "word for searching")
    private String word;

    @Argument(required = true, usage = "file to filter", index = 1)
    private File file;


    public static void main(String[] args) {
        new Main().CommandLineArgument(args);
    }

    private void CommandLineArgument(String[] args){
            CmdLineParser parser = new CmdLineParser(this);
            try{
                parser.parseArgument(args);
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
    }

    //Utility grep = new Utility()
}
