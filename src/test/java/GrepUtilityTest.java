package test.java;

import main.java.grep.Main;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GrepUtilityTest {
    private Path emptyTestFilePath = Paths.get("src","main","resources","emptyTestFile.txt");
    private String emptyFileAddress = emptyTestFilePath.toString();
    private Path testFilePath = Paths.get("src","main","resources","testFile.txt");
    private String fileAddress = testFilePath.toString();

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private PrintStream out = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(out);
    }


    @Test
    public void grepEmptyFile(){
        Main.main(new String[]{"smth", emptyFileAddress});
        assertEquals("", outContent.toString());
    }

    @Test
    public void grepWord(){
        Main.main(new String[]{"smth", fileAddress});
        assertEquals("smth 1234"+ System.lineSeparator()+
                              "234 smth" + System.lineSeparator(),
                outContent.toString());
    }

    @Test
    public void grepRegex(){
        Main.main(new String[]{"-r","(s+)", fileAddress});
        assertEquals("smth 1234"+ System.lineSeparator()+
                              "234 smth" + System.lineSeparator()+
                              "sSs"+ System.lineSeparator()+
                              "class" + System.lineSeparator(),
                outContent.toString());
    }

    @Test
    public void grepInsens(){
        Main.main(new String[]{"-i","smth", fileAddress});
        assertEquals("smth 1234"+ System.lineSeparator()+
                              "234 smth" + System.lineSeparator()+
                              "SmTh"+ System.lineSeparator(),
                outContent.toString());
    }

    @Test
    public void grepInvert(){
        Main.main(new String[]{"-v","smth", fileAddress});
        assertEquals("sSs"+ System.lineSeparator()+
                              "" + System.lineSeparator()+
                              "SmTh" + System.lineSeparator()+
                              "class" + System.lineSeparator()+
                              ";" + System.lineSeparator(),
                outContent.toString());
    }

    @Test
    public void grepInvertRegex(){
        Main.main(new String[]{"-v", "-r","(s+)", fileAddress});
        assertEquals("" + System.lineSeparator()+
                              "SmTh" + System.lineSeparator()+
                              ";" + System.lineSeparator(),
                outContent.toString());
    }

    @Test
    public void grepInvertInsensWord(){
        Main.main(new String[]{"-v","-i","smth", fileAddress});
        assertEquals("sSs"+ System.lineSeparator()+
                              "" + System.lineSeparator()+
                              "class" + System.lineSeparator()+
                              ";" + System.lineSeparator(),
                outContent.toString());
    }

}
