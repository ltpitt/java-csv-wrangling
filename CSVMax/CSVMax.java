import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * A few CSV methods to get out weather data from several files.
 * 
 * @author Davide Nastri
 * @version 1/23/2017
 */
public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser) {
    CSVRecord largestSoFar = null;
    for (CSVRecord currentRow : parser) {
        largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
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
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }
    public void testHottestInManyDays(){
        CSVRecord largest = hottestInManyDays();
        System.out.println("Hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("TimeEST"));
    }
    public CSVRecord getLargestOfTwo(CSVRecord currentRow, CSVRecord largestSoFar) {
        if (largestSoFar == null) {
                largestSoFar = currentRow;
            } else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
                if (currentTemp > largestTemp) {
                    largestSoFar = currentRow;
                }
            }
        return largestSoFar;
    }
    public CSVRecord getSmallestOfTwoT(CSVRecord currentRow, CSVRecord smallestSoFar) {
        if (smallestSoFar == null) {
                smallestSoFar = currentRow;
            } else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
                if (currentTemp < smallestTemp && currentTemp != -9999) {
                    smallestSoFar = currentRow;
                }
            }
        return smallestSoFar;
    }
    public CSVRecord coldestHourInFile(CSVParser parser) {
    CSVRecord smallestSoFar = null;
    for (CSVRecord currentRow : parser) {
        smallestSoFar = getSmallestOfTwoT(currentRow, smallestSoFar);
        }
    return smallestSoFar;
    }        
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        //System.out.println("Coldest temperature was " + smallest.get("TemperatureF"));
        System.out.println("Coldest temperature was " + smallest.get("TemperatureF") + " at " + smallest.get("DateUTC"));        
    }
    public double averageTemperatureInFile(CSVParser parser) {
        double temperatureSum = 0.0;
        int temperatureCount = 0;
        for (CSVRecord currentRow : parser) {
            temperatureSum += Double.parseDouble(currentRow.get("TemperatureF"));
            temperatureCount++;
            }
        return temperatureSum / temperatureCount;
    }        
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        double averageTemperature = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature was " + averageTemperature);        
    }
    public String fileWithColdestTemperature() {
        String coldestTemperatureFileName = null;
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            smallestSoFar = getSmallestOfTwoT(currentRow, smallestSoFar);            
            if (currentRow == smallestSoFar) {
                coldestTemperatureFileName = f.getName();
            }
        }
        return coldestTemperatureFileName;
    }
    public void testFileWithColdestTemperature(){
        FileResource fr = new FileResource();
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature was " + smallest.get("TemperatureF"));
        //System.out.println("Coldest temperature was " + smallest.get("TemperatureF") + " at " + smallest.get("TimeEST")); 
        //System.out.println("All the temperatures on the coldest day were:");
        //for (CSVRecord row : fr.getCSVParser()) {
        //    System.out.println(row.get("TemperatureF") + " at " + row.get("TimeEST"));
        //}
    }
    public void testColdestTemperatureInFiles() {
        String coldestDay = fileWithColdestTemperature();
        System.out.println("Coldest day was: " + coldestDay);
    }
    public CSVRecord getSmallestOfTwoH(CSVRecord currentRow, CSVRecord smallestSoFar) {
        double currentHum;
        if (smallestSoFar == null) {
                smallestSoFar = currentRow;
            } else {
                try{
                    currentHum = Double.parseDouble(currentRow.get("Humidity"));
                    }catch(NumberFormatException ex){
                    return smallestSoFar;
                    }
                double smallestHum = Double.parseDouble(smallestSoFar.get("Humidity"));
                if (currentHum == smallestHum) {
                    return smallestSoFar;
                } else if (currentHum < smallestHum) {
                    smallestSoFar = currentRow;
                }
            }
        return smallestSoFar;
    }   
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord smallestSoFar = null;
        for (CSVRecord currentRow : parser) {
            smallestSoFar = getSmallestOfTwoH(currentRow, smallestSoFar);
            }
        return smallestSoFar;        
    }
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest humidity in file: " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            smallestSoFar = getSmallestOfTwoH(currentRow, smallestSoFar);
        }
        return smallestSoFar;
    }
    public void testLowestHumidityInManyFiles(){
        CSVRecord smallest = lowestHumidityInManyFiles();
        System.out.println("Lowest humidity in file: " + smallest.get("Humidity") + " at " + smallest.get("DateUTC"));
    }
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double temperatureSum = 0.0;
        int temperatureCount = 0;
        for (CSVRecord currentRow : parser) {
            if (Double.parseDouble(currentRow.get("Humidity")) >= value) {
                temperatureSum += Double.parseDouble(currentRow.get("TemperatureF"));
                temperatureCount++;
            }
            }
        return temperatureSum / temperatureCount;    
    }
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureWithHighHumidityInFile(parser, 80);
        if ( Double.isNaN(average) ){
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println("Average Temp when high Humidity is " + average);
        }
    }
}
