import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Neat Team on 4/16/2016.
 */
@ParseClassName("User")
public class User extends ParseObject {
    public void setUsername(String uname) { this.put("username",uname);}
    public String getUsername() {return this.getString("username");}
    public void setPassword(String pass) { this.put("password",pass);}
    public String getPassword() {return this.getString("password");}


}