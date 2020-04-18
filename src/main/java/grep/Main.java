package main.java.grep;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

    @Option(name = "-v", usage = "inverts the filter condition")
    private boolean inver;

    @Option(name = "-i", usage = "case insensitive")
    private boolean insens;

    @Option(name = "-r", usage = "instead of a word, sets the regex for searching", forbids = "-i")
    private boolean regex;

    @Argument(required = true, usage = "word for searching")
    private String word;

    @Argument(required = true, usage = "file to filter", index = 1)
    private File file;

    public static void main(String[] args) {
        new Main().commandLineArgument(args);
    }

    private void commandLineArgument(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("Correct input: [-v] [-i] [-r] word inputname.txt");
            System.exit(1);
        }

        Utility grep = new Utility(inver, insens, regex, word, file);
        try{
            List<String> res = grep.fileReader();
            for (String line: res)
                System.out.println(line);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

    }

}
