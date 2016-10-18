import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by kurtmccann on 10/17/16.
 */
public class GroceryCounter
{
    HashMap<String, Integer> priceCountList = new HashMap<String, Integer>();
    int totalCount = 0;

    public void addPriceCount(String price)
    {
        if(priceCountList.containsKey(price))
        {
            priceCountList.put(price, priceCountList.get(price) + 1);
        }
        else
        {
            priceCountList.put(price, 1);
        }
        totalCount++;
    }

    public void printPriceCountList()
    {
        int counter=0;
        for(String price: priceCountList.keySet())
        {
            String right = String.format("%1$-10s %2$10s", "Price: ", price);
            String left = String.format("%1$-10s %2$10s", "Seen: ", priceCountList.get(price) + " times");
            String total = right + "          " + left;
            System.out.println(total);

            counter++;
            if(counter < priceCountList.size())
            {
                String leftUnderline = String.format("%1$-15s", "---------------------");
                String rightUnderline = String.format("%1$-15s", "---------------------");
                String totalUnderline = leftUnderline + "          " + rightUnderline;
                System.out.println(totalUnderline);
            }
            else
            {
                System.out.println();
            }
        }
    }

    public int getTotalCount()
    {
        return this.totalCount;
    }

}
