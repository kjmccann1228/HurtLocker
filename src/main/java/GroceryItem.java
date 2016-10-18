import java.util.ArrayList;

/**
 * Created by kurtmccann on 10/17/16.
 */
public class GroceryItem
{

    String name;
    String price;
    String type;
    String expirationDate;

    public GroceryItem(String name, String price, String type, String expirationDate)
    {
        this.name = name;
        this.price = price;
        this.type = type;
        this.expirationDate = expirationDate;
    }

    public String getType()
    {
        return this.type;
    }

    public String getName()
    {
        return this.name;
    }

    public String getPrice()
    {
        return this.price;
    }

    public String getExpirationDate()
    {
        return this.expirationDate;
    }

}
