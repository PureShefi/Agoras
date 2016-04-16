import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Neat Team on 4/16/2016.
 */
public class AgoraFeed extends ParseObject { //ownerID, Text
    public void setOwnerID(String ownerID) { this.put("OwnerID",ownerID);}
    public String getOwnerID() {return this.getString("OwnerID");}
    public void setUserID(String userID) { this.put("UserID",userID);}
    public String getUserID() {return this.getString("UserID");}

}