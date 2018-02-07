import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * Reading and analyzing data from a CSV file using CSVParser
 * (Apache Commons CSV)
 * 
 * @author Davide Nastri 
 * @version 1/22/2018
 */
public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        for (CSVRecord record : parser) {
            // Getting "Exports" column
            String export = record.get("Exports");
            // Checking if it contains exportOfInterest
            if (export.contains(exportOfInterest)) {
                // If so, write down the "Country" from that row
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    }
    public String countryInfo(CSVParser parser, String country) {
        String countryInfo = "NOT FOUND";
        String currentCountry;
        for (CSVRecord record : parser) {
            currentCountry = record.get("Country");
            //System.out.println("Country: " + country);
            //System.out.println("Current Country: " + currentCountry);
            if (currentCountry.contains(country)) {
                countryInfo = country + ": " + record.get("Exports") + ": " + 
                record.get("Value (dollars)");
            }
        }
        return countryInfo;
    }
    public String listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        String currentCountry;
        String result = "No country found";
        String currentExportItems;
        for (CSVRecord record: parser) {
            currentCountry = record.get("Country");
            currentExportItems = record.get("Exports");
            if (currentExportItems.contains(exportItem1) && currentExportItems.contains(exportItem2)) {
                if (result.contains("No country found")) {
                    result = currentCountry + ", ";
                } else {
                    result += currentCountry + ", ";
                }
            }
        }
        if (!result.contains("No country found")) {
            //System.out.println("Removing last comma from result");
            result = result.substring(0, result.length() - 2);
        }
        return result;
    }
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int numberOfExporters = 0;
        String currentExportItems;        
        for (CSVRecord record : parser) {
            currentExportItems = record.get("Exports");
            //System.out.println("Country: " + country);
            //System.out.println("Current Country: " + currentCountry);
            if (currentExportItems.contains(exportItem)) {
                numberOfExporters++;
            }
        }
        
        return numberOfExporters;
    }
    public void testContryInfo() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Testing contryInfo() with value Germany\n");
        System.out.println("Expected values:");
        String expectedValues = "Germany: motor vehicles, machinery, chemicals, computer and electronic products, electrical equipment, pharmaceuticals, metals, transport equipment, foodstuffs, textiles, rubber and plastic products: $1,547,000,000,000";
        System.out.println(expectedValues + "\n");
        System.out.println("Returned values:");
        String germanyInfo = countryInfo(parser, "Germany");
        System.out.println(germanyInfo + "\n");
        if (germanyInfo.contains(expectedValues)) {
            System.out.println("Test passed\n");
        } else {
            System.out.println("Test not passed\n");
        }
        // Reset with parser = fr.getCSVParser();
    }
    public void testNumberOfExporters() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Testing testNumberOfExporters() with value: gold\n");
        System.out.println("Expected values:");
        int expectedValue = 30;
        System.out.println(expectedValue + "\n");
        System.out.println("Returned values:");
        int goldCountriesNumber = numberOfExporters(parser, "cocoa");
        System.out.println(goldCountriesNumber + "\n");
        if (goldCountriesNumber == expectedValue) {
            System.out.println("Test passed\n");
        } else {
            System.out.println("Test not passed\n");
        }  
    }   
    public void testListExportersTwoProducts() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Testing testListExportersTwoProducts() with values: gold, diamonds\n");
        System.out.println("Expected values:");
        String expectedValues = "Armenia, Congo (Democratic Republic of the), Ghana, Guinea, Namibia, South Africa";
        System.out.println(expectedValues + "\n");
        System.out.println("Returned values:");
        String goldDiamondsCountries = listExportersTwoProducts(parser, "cotton", "flowers");
        System.out.println(goldDiamondsCountries + "\n");
        if (goldDiamondsCountries.contains(expectedValues)) {
            System.out.println("Test passed\n");
        } else {
            System.out.println("Test not passed\n");
        }        
    }
    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record: parser ) {
            String str = record.get("Value (dollars)");
            if (str.length() > amount.length()) {
                System.out.print(record.get("Country")+" ");
                System.out.println(str);
            }
        }
    }
    public void testbigExporters() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }
}
