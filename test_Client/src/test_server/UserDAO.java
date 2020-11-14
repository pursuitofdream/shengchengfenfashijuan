package test_server;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {
    private static Connection con;
    private static Statement stm;
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test1029?zeroDateTimeBehavior=convertToNull","root","Gjt147369456");
            stm=con.createStatement();
        } catch (ClassNotFoundException ex) {Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);} 
        catch (SQLException ex) {Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);}
    }
    public static Connection getCon() {return con;}
    public static void setCon(Connection con) {UserDAO.con = con;}
    public static Statement getStm() {return stm;}
    public static void setStm(Statement stm) {UserDAO.stm = stm;} 
}
