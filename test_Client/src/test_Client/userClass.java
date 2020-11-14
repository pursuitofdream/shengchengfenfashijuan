package test_Client;

import java.io.Serializable;


public class userClass implements Serializable{
    private String userid;
    private String userpass;
    public String getUserid() {return userid;}
    public void setUserid(String userid) {this.userid = userid;}
    public String getUserpass() {return userpass;}
    public void setUserpass(String userpass) {this.userpass = userpass;}
    public userClass() {}
    public userClass(String userid, String userpass) {
        this.userid = userid;
        this.userpass = userpass;
    }
    
}
