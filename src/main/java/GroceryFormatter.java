import java.util.*;

/**
 * Created by kurtmccann on 10/17/16.
 */
public class GroceryFormatter
{
    ArrayList<GroceryItem> groceryList = new ArrayList<GroceryItem>();

    public void printGroceryList()
    {
        int counter = 1;
        for(GroceryItem gi : groceryList)
        {
            System.out.println(counter + " Item: " + gi.getName() + " , Price: " + gi.getPrice() + " , Expiration Date: " + gi.getExpirationDate()+ " , Type: " + gi.getType());
            counter++;
        }
    }

    TreeSet<String> groceryNameCategories = new TreeSet<String>();

    public void getGroceryCategories()
    {
        for(GroceryItem groceryItem : groceryList)
        {
            groceryNameCategories.add(groceryItem.getName());
        }
    }

    public void printPriceCounts()
    {
        String errors = "";
        for(String groceryName : groceryNameCategories)
        {
            GroceryCounter groceryCounter = new GroceryCounter();
            for(GroceryItem groceryItem : groceryList)
            {
                if(groceryName.equals(groceryItem.getName()))
                {
                    groceryCounter.addPriceCount(groceryItem.getPrice());
                }
            }
            if(groceryName.equals("ERROR"))
            {

                String left = String.format("%1$-10s %2$10s", "Errors", "");
                String right = String.format("%1$-10s %2$10s", "Seen: ", groceryCounter.getTotalCount() + " times");
                String total = left + "          " + right;
                errors += total;
            }
            else
            {
                String left = String.format("%1$-10s %2$10s", "Name: ", groceryName);
                String right = String.format("%1$-10s %2$10s", "Seen: ", groceryCounter.getTotalCount() + " times");
                String total = left + "          " + right;
                System.out.println(total);
                String leftUnderline = String.format("%1$-15s", "=====================");
                String rightUnderline = String.format("%1$-15s", "=====================");
                String totalUnderline = leftUnderline + "          " + rightUnderline;
                System.out.println(totalUnderline);
                groceryCounter.printPriceCountList();
            }
        }
        System.out.println(errors);
    }

    public void addGrocery(GroceryItem groceryItem)
    {
        groceryList.add(groceryItem);
    }

    public int getGroceryListLength()
    {
        return this.groceryList.size();
    }

    Map<String, Integer>  itemCounts = new HashMap<String, Integer>();

    public void setItemCount()
    {
        for(GroceryItem groceryItem: groceryList)
        {
            if(!itemCounts.containsKey(groceryItem.getName()))
            {
                itemCounts.put(groceryItem.getName(), 0);
            }
            else
            {
                itemCounts.put(groceryItem.getName(), itemCounts.get(groceryItem.getName()) +1);
            }
        }
    }

    public void printItemCounts()
    {
        int counter = 1;
        for(String itemName : itemCounts.keySet())
        {
            System.out.println(counter + ") " +  itemName + ": Count=" + itemCounts.get(itemName));
            counter++;
        }
    }

    public void printGroceryCategories()
    {
        for(String groceryName : groceryNameCategories)
        {
            System.out.println(groceryName);
        }
    }
}
