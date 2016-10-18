import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

/**
 * Created by kurtmccann on 10/17/16.
 */
public class JerksonParser
{
    public List<String> parseJerksonObjects(String input)
    {
        Pattern p = Pattern.compile(".*?##");
        Matcher m = p.matcher(input);

        List<String> jerksonObjects = new ArrayList<String>();
        while(m.find())
        {
            jerksonObjects.add(m.group());
        }


        return jerksonObjects;
    }

    public List<String> parseKeyValuePairs(String input)    //split on symbols that are not letter, number, '.'
    {                                                       // ':', o

        Pattern p = Pattern.compile("(\\w)*:(\\w)*(\\.|/)?(\\w)*/?(\\w)*");
        Matcher m = p.matcher(input);

        List<String> keyValuePairs = new ArrayList<String>();
        while(m.find())
        {
            keyValuePairs.add(m.group());
        }
        return keyValuePairs;
    }

    public List<String> isolateKeyFromValue(String input)
    {
        Pattern p = Pattern.compile("(\\w)+\\.*/*(\\w)*/*(\\w)*");
        Matcher m = p.matcher(input);
        List<String> keyAndValue = new ArrayList<String>();
        while(m.find())
        {
            keyAndValue.add(m.group());
        }
        return keyAndValue;
    }

    public List<List<String>> getArrayOfFoodPropertiesAndValues(List<String> input)
    {
        List<List<String>> toReturn = new ArrayList<List<String>>();
        for(String string : input)
        {
            List<String> propAndVal = isolateKeyFromValue(string);
            toReturn.add(propAndVal);
        }
        return toReturn;
    }

    public GroceryItem groceryItemCreator(List<List<String>> keyValuePairs)
    {
        try
        {
            String name = formatFoodName(keyValuePairs.get(0).get(1));
            String price = keyValuePairs.get(1).get(1);
            String type = keyValuePairs.get(2).get(1);
            String expirationDate = keyValuePairs.get(3).get(1);

            GroceryItem groceryItem = new GroceryItem(name, price, type, expirationDate);
            return groceryItem;
        }
        catch(Exception e)
        {
            return new GroceryItem("ERROR", "", "", "");
        }
    }

    public String formatFoodName(String name)
    {
        if(isMilk(name)){return "Milk";}
        if(isBread(name)){return "Bread";}
        if(isApples(name)){return "Apples";}
        if(isCookies(name)){return "Cookies";}
        else{return "Error";}
    }



    public boolean isMilk(String input)
    {
        {
            Pattern p = Pattern.compile("[mM][iI][lL][kK]");
            Matcher m = p.matcher(input);
            if(m.matches())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    public boolean isBread(String input)
    {
        {
            Pattern p = Pattern.compile("[bB][rR][eE][aA][dD]");
            Matcher m = p.matcher(input);
            if(m.matches())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    public boolean isApples(String input)
    {
        {
            Pattern p = Pattern.compile("[aA][pP]{2}[lL][eE][sS]");
            Matcher m = p.matcher(input);
            if(m.matches())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    public boolean isCookies(String input)
    {
        {
            Pattern p = Pattern.compile("[cC][oO0][oO0][kK][iI][eE][sS]");
            Matcher m = p.matcher(input);
            if(m.matches())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

}
