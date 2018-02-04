import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of BabyNames here.
 * 
 * @author Davide Nastri
 * @version 1/30/2018
 */
public class BabyNames {
    public void printNames() {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            System.out.println("Name " + rec.get(0) + 
                               " Gender " + rec.get(1) +
                               " Num Born " + rec.get(2));
        }
    }
    public void readOneFile(int year) {
        String fname = "data/yob" + year + ".txt";
        FileResource fr = new FileResource(fname);
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord rec : parser) {
            String name = rec.get(0);
            String gender = rec.get(1);
        }
    }
}
