import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of CSVMax here.
 * 
 * @author Davide Nastri
 * @version 1/23/2017
 */
public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser) {
    CSVRecord largestSoFar = null;
    for (CSVRecord currentRow : parser) {
            if (largestSoFar == null) {
                largestSoFar = currentRow;
            } else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
                if (currentTemp > largestTemp) {
                    largestSoFar = currentRow;
                }
            }
        }
    return largestSoFar;
    }
    public void testHottestInDay(){
        FileResource fr = new FileResource("data/2015/weather-2015-01-01.csv");
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("Hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("TimeEST"));
    }
    public CSVRecord hottestInManyDays() {
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            if (largestSoFar == null) {
                largestSoFar = currentRow;
            } else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
                if (currentTemp > largestTemp) {
                    largestSoFar = currentRow;
                }
            }
        }
        return largestSoFar;
    }
    public void testHottestInManyDays(){
        CSVRecord largest = hottestInManyDays();
        System.out.println("Hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("TimeEST"));
    }
}
