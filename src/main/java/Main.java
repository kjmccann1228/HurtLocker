import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static org.junit.Assert.assertEquals;

public class Main {

    public String readRawDataToString() throws Exception{
        Path path = Paths.get(ClassLoader.getSystemResource("RawData.txt").toURI());
        return new String(readAllBytes(get(path.toUri())));
    }

    public static void main(String[] args) throws Exception{
        Main main = new Main();
        String input = main.readRawDataToString();

        JerksonParser jerksonParser = new JerksonParser();
        GroceryFormatter groceryFormatter = new GroceryFormatter();
        List<String> itemList = jerksonParser.parseJerksonObjects(input);
        for(String fullFoodObjectAsString : itemList)
        {
            List<String> listOfAllKeyValuePairsForFoodItem = jerksonParser.parseKeyValuePairs(fullFoodObjectAsString);
            List<List<String>> itemKeyValuePairsArray = jerksonParser.getArrayOfFoodPropertiesAndValues(listOfAllKeyValuePairsForFoodItem);
            GroceryItem groceryItem = jerksonParser.groceryItemCreator(itemKeyValuePairsArray);
            groceryFormatter.addGrocery(groceryItem);
        }
        System.out.println("THE LENGTH ISSSSSSS " +groceryFormatter.getGroceryListLength());
        //groceryFormatter.getFormattedGroceryList();
        //groceryFormatter.printGroceryList();
        groceryFormatter.printGroceryList();
    }
}
