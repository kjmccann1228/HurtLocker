import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kurtmccann on 10/17/16.
 */
public class JerksonParserTest
{

    @Test
    public void parseJerksonObjectsSuccessTest()
    {
        String input = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016##naMe:Cookies;price:2.25;type:Food%expiration:1/25/2016##naMe:CoOkieS;price:2.25;type:Food*expiration:1/25/2016##naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##naMe:COOkieS;price:2.25;type:Food;expiration:1/25/2016##NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##naMe:MilK;price:1.23;type:Food!expiration:4/25/2016##naMe:apPles;price:0.25;type:Food;expiration:1/23/2016##naMe:apPles;price:0.23;type:Food;expiration:5/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food;expiration:1/04/2016##naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food@expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food@expiration:2/25/2016##naMe:MiLK;priCe:;type:Food;expiration:1/11/2016##naMe:Cookies;price:2.25;type:Food;expiration:1/25/2016##naMe:Co0kieS;pRice:2.25;type:Food;expiration:1/25/2016##naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##naMe:COOkieS;Price:2.25;type:Food;expiration:1/25/2016##NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##naMe:MilK;priCe:;type:Food;expiration:4/25/2016##naMe:apPles;prIce:0.25;type:Food;expiration:1/23/2016##naMe:apPles;pRice:0.23;type:Food;expiration:5/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food^expiration:1/04/2016##";
        JerksonParser jerksonParser = new JerksonParser();
        List<String> jsObjects = jerksonParser.parseJerksonObjects(input);
        String actual = jsObjects.get(1);
//        System.out.println("The size: " + jsObjects.size());
//        System.out.println(jsObjects.get(1));
        String expected = "naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##";
        assertEquals(expected, actual);
    }

    @Test
    public void parseKeyValuePairsTest()
    {
        String input ="naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016##";
        JerksonParser jerksonParser = new JerksonParser();
        List<String> jsObjects = jerksonParser.parseKeyValuePairs(input);
        String actual = jsObjects.get(1);

//        for(String s: jsObjects)
//        {
//            System.out.println(s);
//        }
//        System.out.println("The size: " + jsObjects.size());
//        System.out.println(jsObjects.get(1));
        String expected = "price:3.23";
        assertEquals(expected, actual);
    }

    @Test
    public void parseKeyValuePairsMissingTypeTest()
    {
        String input ="Name:Milk;Price:3.23;type:;expiration:1/24/2016##";
        JerksonParser jerksonParser = new JerksonParser();
        List<String> jsObjects = jerksonParser.parseKeyValuePairs(input);
        String actual = jsObjects.get(2);

//        for(String s: jsObjects)
//        {
//            System.out.println(s);
//        }
//        System.out.println("The size: " + jsObjects.size());
//        System.out.println(jsObjects.get(1));
        String expected = "type:";
        assertEquals(expected, actual);
    }

    @Test
    public void isolateKeyFromValueTest()
    {
        String input ="price:3.23";
        JerksonParser jerksonParser = new JerksonParser();
        List<String> jsObjects = jerksonParser.isolateKeyFromValue(input);


//        for(String s: jsObjects)
//        {
//            System.out.println("!!!   " + s);
//        }
//        System.out.println("The size: " + jsObjects.size());

        String actual = jsObjects.get(1);
        String expected = "3.23";
        assertEquals(expected, actual);
    }


    @Test
    public void isolateKeyFromValueGetKeyWithMissingValueTest()
    {
        String input ="type:";
        JerksonParser jerksonParser = new JerksonParser();
        List<String> jsObjects = jerksonParser.isolateKeyFromValue(input);


//        for(String s: jsObjects)
//        {
//            System.out.println("!!!   " + s);
//        }
//        System.out.println("The size: " + jsObjects.size());

        String actual = jsObjects.get(0);
        String expected = "type";
        assertEquals(expected, actual);
    }

    @Test
    public void isolateKeyFromValueGetValueWithMissingValueTest()
    {
        String input ="type:";
        JerksonParser jerksonParser = new JerksonParser();
        List<String> jsObjects = jerksonParser.isolateKeyFromValue(input);

//        for(String s: jsObjects)
//        {
//            System.out.println("!!!   " + s);
//        }
//        System.out.println("The size: " + jsObjects.size());

        int actual = jsObjects.size();
        int expected = 1;
        assertEquals(expected, actual);
    }

//    public List<List<String>> getArrayOfFoodPropertiesAndValues(List<String> input)
//    {
//        List<List<String>> toReturn = new ArrayList<List<String>>();
//        for(String string : input)
//        {
//            List<String> propAndVal = isolateKeyFromValue(string);
//            toReturn.add(propAndVal);
//        }
//        return toReturn;
//    }

    @Test
    public void getArrayOfFoodPropertiesAndValuesTest()
    {
        String inputString = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016##naMe:Cookies;price:2.25;type:Food%expiration:1/25/2016##naMe:CoOkieS;price:2.25;type:Food*expiration:1/25/2016##naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##naMe:COOkieS;price:2.25;type:Food;expiration:1/25/2016##NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##naMe:MilK;price:1.23;type:Food!expiration:4/25/2016##naMe:apPles;price:0.25;type:Food;expiration:1/23/2016##naMe:apPles;price:0.23;type:Food;expiration:5/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food;expiration:1/04/2016##naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food@expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food@expiration:2/25/2016##naMe:MiLK;priCe:;type:Food;expiration:1/11/2016##naMe:Cookies;price:2.25;type:Food;expiration:1/25/2016##naMe:Co0kieS;pRice:2.25;type:Food;expiration:1/25/2016##naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##naMe:COOkieS;Price:2.25;type:Food;expiration:1/25/2016##NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##naMe:MilK;priCe:;type:Food;expiration:4/25/2016##naMe:apPles;prIce:0.25;type:Food;expiration:1/23/2016##naMe:apPles;pRice:0.23;type:Food;expiration:5/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food^expiration:1/04/2016##";
        JerksonParser jerksonParser = new JerksonParser();
        List<String>itemList = jerksonParser.parseJerksonObjects(inputString);
        String item = itemList.get(0);
        System.out.println(item);
        List<String> itemKeyValuePairs = jerksonParser.parseKeyValuePairs(item);
        System.out.println(itemKeyValuePairs);
        List<String> wtf = jerksonParser.isolateKeyFromValue(itemKeyValuePairs.get(3));
        System.out.println("wtf" + wtf);
        List<List<String>> itemKeyValuePairsArray = jerksonParser.getArrayOfFoodPropertiesAndValues(itemKeyValuePairs);
        System.out.println(itemKeyValuePairsArray);
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("expiration");
        expected.add("1/25/2016");
        assertEquals(itemKeyValuePairsArray.get(3),expected);
    }

    @Test
    public void groceryItemCreatorTest()
    {
        String inputString = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016##naMe:Cookies;price:2.25;type:Food%expiration:1/25/2016##naMe:CoOkieS;price:2.25;type:Food*expiration:1/25/2016##naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##naMe:COOkieS;price:2.25;type:Food;expiration:1/25/2016##NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##naMe:MilK;price:1.23;type:Food!expiration:4/25/2016##naMe:apPles;price:0.25;type:Food;expiration:1/23/2016##naMe:apPles;price:0.23;type:Food;expiration:5/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food;expiration:1/04/2016##naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food@expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food@expiration:2/25/2016##naMe:MiLK;priCe:;type:Food;expiration:1/11/2016##naMe:Cookies;price:2.25;type:Food;expiration:1/25/2016##naMe:Co0kieS;pRice:2.25;type:Food;expiration:1/25/2016##naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##naMe:COOkieS;Price:2.25;type:Food;expiration:1/25/2016##NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##naMe:MilK;priCe:;type:Food;expiration:4/25/2016##naMe:apPles;prIce:0.25;type:Food;expiration:1/23/2016##naMe:apPles;pRice:0.23;type:Food;expiration:5/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food^expiration:1/04/2016##";
        JerksonParser jerksonParser = new JerksonParser();
        List<String>itemList = jerksonParser.parseJerksonObjects(inputString);
        System.out.println(itemList);
        String item = itemList.get(0);
        System.out.println(item);
        List<String> itemKeyValuePairs = jerksonParser.parseKeyValuePairs(item);
        System.out.println(itemKeyValuePairs);
        List<String> wtf = jerksonParser.isolateKeyFromValue(itemKeyValuePairs.get(3));
        System.out.println("wtf" + wtf);
        List<List<String>> itemKeyValuePairsArray = jerksonParser.getArrayOfFoodPropertiesAndValues(itemKeyValuePairs);
        System.out.println(itemKeyValuePairsArray);
        GroceryItem actualGrocery = jerksonParser.groceryItemCreator(itemKeyValuePairsArray);
        String actual = actualGrocery.getType();
        //GroceryItem expected = new GroceryItem("Milk", "3.23", "Food", "1/25/2016");
        //ArrayList<String> expected = new ArrayList<String>();
        //expected.add("expiration");
        //expected.add("1/25/2016");
        assertEquals("Food", actual);
    }

    @Test
    public void groceryItemCreatorErrorTest()
    {
        String inputString = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016##naMe:Cookies;price:2.25;type:Food%expiration:1/25/2016##naMe:CoOkieS;price:2.25;type:Food*expiration:1/25/2016##naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##naMe:COOkieS;price:2.25;type:Food;expiration:1/25/2016##NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##naMe:MilK;price:1.23;type:Food!expiration:4/25/2016##naMe:apPles;price:0.25;type:Food;expiration:1/23/2016##naMe:apPles;price:0.23;type:Food;expiration:5/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food;expiration:1/04/2016##naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food@expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food@expiration:2/25/2016##naMe:MiLK;priCe:;type:Food;expiration:1/11/2016##naMe:Cookies;price:2.25;type:Food;expiration:1/25/2016##naMe:Co0kieS;pRice:2.25;type:Food;expiration:1/25/2016##naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##naMe:COOkieS;Price:2.25;type:Food;expiration:1/25/2016##NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##naMe:MilK;priCe:;type:Food;expiration:4/25/2016##naMe:apPles;prIce:0.25;type:Food;expiration:1/23/2016##naMe:apPles;pRice:0.23;type:Food;expiration:5/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food^expiration:1/04/2016##";
        JerksonParser jerksonParser = new JerksonParser();
        List<String>itemList = jerksonParser.parseJerksonObjects(inputString);
        System.out.println(itemList);
        String item = "naMe:MiLK;priCe:;type:Food;expiration:1/11/2016##";
        System.out.println(item);
        List<String> itemKeyValuePairs = jerksonParser.parseKeyValuePairs(item);
        System.out.println(itemKeyValuePairs);
        List<String> wtf = jerksonParser.isolateKeyFromValue(itemKeyValuePairs.get(3));
        System.out.println("wtf" + wtf);
        List<List<String>> itemKeyValuePairsArray = jerksonParser.getArrayOfFoodPropertiesAndValues(itemKeyValuePairs);
        System.out.println(itemKeyValuePairsArray);
        GroceryItem actualGrocery = jerksonParser.groceryItemCreator(itemKeyValuePairsArray);
        String actual = actualGrocery.getName();
        //GroceryItem expected = new GroceryItem("Milk", "3.23", "Food", "1/25/2016");
        //ArrayList<String> expected = new ArrayList<String>();
        //expected.add("expiration");
        //expected.add("1/25/2016");
        assertEquals("ERROR", actual);
    }

    @Test
    public void isMilkTest()
    {
        JerksonParser jerksonParser = new JerksonParser();
        String input = "MiLk";
        assertTrue(jerksonParser.isMilk(input));
    }

    @Test
    public void isntMilkTest()
    {
        JerksonParser jerksonParser = new JerksonParser();
        String input = "Oil!";
        assertFalse(jerksonParser.isMilk(input));
    }

    @Test
    public void isBreadTest()
    {
        JerksonParser jerksonParser = new JerksonParser();
        String input = "breAD";
        assertTrue(jerksonParser.isBread(input));
    }

    @Test
    public void isntBreadTest()
    {
        JerksonParser jerksonParser = new JerksonParser();
        String input = "Sand!";
        assertFalse(jerksonParser.isBread(input));
    }

    @Test
    public void isApplesTest()
    {
        JerksonParser jerksonParser = new JerksonParser();
        String input = "ApPLs";
        assertTrue(jerksonParser.isApples(input));
    }

    @Test
    public void isntApplesTest()
    {
        JerksonParser jerksonParser = new JerksonParser();
        String input = "Appples";
        assertFalse(jerksonParser.isApples(input));
    }

    @Test
    public void isCookiesTest()
    {
        JerksonParser jerksonParser = new JerksonParser();
        String input = "C0oKIES";
        assertTrue(jerksonParser.isCookies(input));
    }

    @Test
    public void isntCookiesTest()
    {
        JerksonParser jerksonParser = new JerksonParser();
        String input = "c4okies";
        assertFalse(jerksonParser.isCookies(input));
    }



}
