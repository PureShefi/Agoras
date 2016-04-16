import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Neat Team on 4/16/2016.
 */
public class Agoras extends ParseObject{
    public void setName(String name) { this.put("Name",name);}
    public String getName() {return this.getString("Name");}
    public void setColor(String color) { this.put("Color",color);}
    public String getColor() {return this.getString("Color");}

}
