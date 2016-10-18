import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Created by kurtmccann on 10/18/16.
 */
public class GroceryCounterTest
{
    @Test
    public void addPriceCountTest()
    {
        GroceryCounter groceryCounter = new GroceryCounter();

        groceryCounter.addPriceCount("3.50");
        groceryCounter.addPriceCount("4.25");
        groceryCounter.addPriceCount("3.50");
        groceryCounter.addPriceCount("4.25");
        groceryCounter.addPriceCount("4.25");
        int actual = groceryCounter.priceCountList.get("3.50");

        assertEquals(2, actual);

    }
}
