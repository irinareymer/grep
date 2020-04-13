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
    public void grepWord(){
        Main.main(new String[]{"qw", fileAddress});
        assertEquals("qw 1234"+ System.lineSeparator()+
                              "234 qw" + System.lineSeparator(),
                     outContent.toString());
    }
    //todo
}
