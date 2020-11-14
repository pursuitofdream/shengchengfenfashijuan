package test_server;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import test_Client.teacher_Obj;
import test_Client.userClass;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class LoginServer extends JFrame {

    private JTextArea edit1;
    private Container container;
    private JScrollPane scroll1;
    private Font font1;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerSocket server;
    private Socket connection;
    private StringBuffer showMessage;

    public LoginServer() {
        super("登录服务器");
        container = getContentPane();
        font1 = new Font("宋体", Font.PLAIN, 18);
        showMessage = new StringBuffer();
        edit1 = new JTextArea();
        edit1.setFont(font1);
        scroll1 = new JScrollPane(edit1);
        scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        container.add(scroll1, BorderLayout.CENTER);
        setSize(800, 600);
        setVisible(true);
    }

    public static void main(String args[]) {
        LoginServer w = new LoginServer();
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.runServer();
    }

    public void runServer() {
        try {
            server = new ServerSocket(12345, 10);
            while (true) {
                waitForConnection();
                getStreams();
                processConnection();

            }
        } catch (IOException ex) {
            Logger.getLogger(LoginServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private void getStreams() {
        try {
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());
            showMessage.append("连接完正，获取相应的输入输出流.....\n");
            edit1.setText(showMessage.toString());

        } catch (IOException ex) {
            Logger.getLogger(LoginServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void waitForConnection() {
        showMessage.append("服务正在等待连接....\n");
        edit1.setText(showMessage.toString());
        try {
            connection=server.accept();
            System.out.println(connection);
        } catch (IOException ex) {Logger.getLogger(LoginServer.class.getName()).log(Level.SEVERE, null, ex);} 
        showMessage.append("连接完正，获取相应的输入输出流.....\n");
        edit1.setText(showMessage.toString());

    }
    Date tmpdate=null;
    private void processConnection() {
        try {
            teacher_Obj tmpUser = (teacher_Obj) input.readObject();
            if(tmpUser.getTc_type().equals("denglu")){
            	showMessage.append("用户名:" + tmpUser.getTc_id() +",密码："+tmpUser.getTc_password()+ "\n");
                edit1.setText(showMessage.toString());
                Statement stm=UserDAO.getStm();
                ResultSet rs=stm.executeQuery("select count(*) as counter from t_table where t_id='"+tmpUser.getTc_id()+"' and t_password='"+tmpUser.getTc_password()+"'");
                rs.first();
                int k=rs.getInt("counter");
                if(k>0) {
                    ResultSet rs2=stm.executeQuery("SELECT * FROM t_table where t_id='"+tmpUser.getTc_id()+"'");
                    rs2.first();
                    String tn=rs2.getString("t_name");
                    System.out.println(tn);
                    rs2.close();
                    output.writeObject(tn);
                }    
                else
                    output.writeObject("Error");
                output.flush();
                output.close();
            }
            if(tmpUser.getTc_type().equals("zhuce")){
            	showMessage.append(tmpUser.getTc_type()+"\n");
                edit1.setText(showMessage.toString());
                Statement stm=UserDAO.getStm();
                String tmpyear=tmpUser.getTc_year();
                String tmpmouth=tmpUser.getTc_mouth();
                String tmpday=tmpUser.getTc_day();
                if(Integer.parseInt(tmpmouth)<10)
                	tmpmouth="0"+tmpmouth;
                if(Integer.parseInt(tmpday)<10)
                	tmpday="0"+tmpday;
                String sqll="insert into t_table values('"+tmpUser.getTc_id()+"','"+tmpUser.getTc_name()+"','"+tmpUser.getTc_password()+"','"+tmpyear+"-"+tmpmouth+"-"+tmpday+"','"+tmpUser.getTc_sex()+"','"+tmpUser.getTc_phone()+"','"+tmpUser.getTc_memo()+"')";
                int k=stm.executeUpdate(sqll);//产生的值是影响的行数
                if(k>0) 
                	output.writeObject("successful");
                else 
                	output.writeObject("error");
                String delid="delete from tid where id='"+tmpUser.getTc_id()+"'";
                int delk=stm.executeUpdate(delid);
                if(delk>0)
                	System.out.println("Delete ID Successful");
                else
                	System.out.println("Delete ID Error");
            }
            String tmpidString=null;
            if(tmpUser.getTc_type().trim().equals("t_id")){
                Statement stm=UserDAO.getStm();
            	ResultSet rs=stm.executeQuery("select * from tid");
                rs.beforeFirst();
                while(rs.next()){
                	if(rs.getString("state").equals("0")) {
                		tmpidString=rs.getString("id");
                		String sqll="update tid set state='1' where id='"+rs.getString("id")+"'";
                        int k=stm.executeUpdate(sqll);
                        if(k>0)
                        	System.out.println("choose id successful");
                        else
                        	System.out.println("choose id error");
                        break;
                	}
                }
                output.writeObject(tmpidString);
                output.flush();
                output.close();
            }
            if(tmpUser.getTc_type().trim().equals("t_idreturn")){
            	Statement stm=UserDAO.getStm();
            	ResultSet rs=stm.executeQuery("select * from tid");
            	String sqll="update tid set state='0' where id='"+tmpUser.getTc_id()+"'";
                int k=stm.executeUpdate(sqll);
                if(k>0)
                	System.out.println("return id successful");
                else
                	System.out.println("return id error");
            }
        } catch (IOException ex) {Logger.getLogger(LoginServer.class.getName()).log(Level.SEVERE, null, ex);} 
        catch (ClassNotFoundException ex) {Logger.getLogger(LoginServer.class.getName()).log(Level.SEVERE, null, ex);} 
        catch (SQLException ex) {Logger.getLogger(LoginServer.class.getName()).log(Level.SEVERE, null, ex);}
    }

}
