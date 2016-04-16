import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Neat Team on 4/16/2016.
 */
@ParseClassName("Market") //ItemName, ItemPicture, ItemID,
public class Market extends ParseObject {
    public void setItemName(String ItemName) { this.put("ItemName",ItemName);}
    public String getItemName() {return this.getString("ItemName");}
    public void setItemPicture(String pic) { this.put("ItemPicture",pic);}
    public String getItemPicture() {return this.getString("ItemPicture");}
    public void setItemID(String id) { this.put("ItemID",id);}
    public String getItemID() {return this.getString("ItemID");}

}