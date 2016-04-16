import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Neat Team on 4/16/2016.
 */
@ParseClassName("AgoraData")
public class AgoraData extends ParseObject{
    public void setAgoraID(String AgoraID) { this.put("AgoraID",AgoraID);}
    public String getAgoraID() {return this.getString("AgoraID");}
    public void setUserID(String userID) { this.put("UserID",userID);}
    public String getUserID() {return this.getString("UserID");}

}