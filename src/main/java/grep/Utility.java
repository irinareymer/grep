package main.java.grep;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

   private boolean lineReader(String line){
        boolean res = false;
        boolean matches = false;
        boolean contain = false;
        if (insens) {
            line = line.toUpperCase();
            word = word.toUpperCase();
        }
        if (regex){
            Pattern p = Pattern.compile(word);
            Matcher m = p.matcher(line);
            matches = m.find();
        }
        else contain = line.contains(word);
        if (matches || contain) res = true;
        if (inver) res = !res;
        return res;
   }

    public List<String> fileReader() throws IOException {
        ArrayList<String> res = new ArrayList<>();
        try (BufferedReader buff = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = buff.readLine()) != null) {
                if (lineReader(line))
                    res.add(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return res;
    }

}