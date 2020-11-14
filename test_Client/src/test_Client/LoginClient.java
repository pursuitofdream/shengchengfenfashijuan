package test_Client;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
//create table tid(id varchar(20) primary key,state varchar(10));
//create table t_table(t_id varchar(20) primary key,t_name varchar(10),t_password varchar(50),t_date date,t_sex varchar(10),t_phone varchar(20),t_memo varchar(100));
//create table dxid(id varchar(20) primary key,state varchar(10));
//create table dx(dx_id varchar(10) primary key,dx_question varchar(300),dx_num int(5),dx_answer varchar(10),dx_point int(5),dx_teacher varchar(20));
//create table mxid(id varchar(20) primary key,state varchar(10));
//create table mx(mx_id varchar(10) primary key,mx_question varchar(300),mx_num int(5),mx_answer varchar(10),mx_point int(5),mx_teacher varchar(20));
//create table pdid(id varchar(20) primary key,state varchar(10));
//create table pd(pd_id varchar(10) primary key,pd_question varchar(300),pd_answer varchar(10),pd_point int(5),pd_teacher varchar(20));
//create table tkid(id varchar(20) primary key,state varchar(10));
//create table tk(tk_id varchar(10) primary key,tk_question varchar(300),tk_num int(5),tk_order varchar(10),tk_a1 varchar(100),tk_a2 varchar(100),tk_a3 varchar(100),tk_a4 varchar(100),tk_a5 varchar(100),tk_point int(5),tk_teacher varchar(20));

import test_server.question_Obj;

public class LoginClient extends JFrame{
    private JTextField text_id;
    private JPasswordField password;
    private JButton command1;
    private Container container;
    private Font font1;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Socket client;
    public LoginClient(){   
    	super("考试登录界面");
		container=getContentPane();
		container.setLayout(null);
		Font font1=new Font("微软雅黑",1,20);
		JTextField text_id=new JTextField(20);
		JPasswordField text_password=new JPasswordField(20);
		JButton b1=new JButton("登录");
		JButton b2=new JButton("注册");
		JLabel label1=new JLabel("用户：");
		JLabel label2=new JLabel("密码：");
		text_id.setFont(font1);
		text_password.setFont(font1);
		b1.setFont(font1);
		b2.setFont(font1);
		label1.setFont(font1);
		label2.setFont(font1);
		//////////////////////////////////////////
		label1.setSize(70,30);
		label2.setSize(70,30);
		text_id.setSize(200,30);
		text_password.setSize(200,30);
		b1.setSize(90,30);
		b2.setSize(90,30);
		//////////////////////////////////////////
		label1.setLocation(20,30);
		label2.setLocation(20,90);
		text_id.setLocation(100,32);
		text_password.setLocation(100,92);
		b1.setLocation(50,150);
		b2.setLocation(200,150);
		//////////////////////////////////////////
		container.add(b1);
		container.add(b2);
		container.add(text_id);
		container.add(text_password);
		container.add(label1);
		container.add(label2);
		setSize(400,250);
		setVisible(true);
		b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String tmpId = text_id.getText();
                    String tmpPass=text_password.getText();
                    connectionServer();
                    output = new ObjectOutputStream(client.getOutputStream());
                    output.flush();
                    input = new ObjectInputStream(client.getInputStream());
                    output.writeObject(new teacher_Obj("denglu",tmpId,tmpPass));
                    output.flush();
                    String tmpS=(String)input.readObject();
                    if(tmpS.equals("Error"))
                        JOptionPane.showMessageDialog(null,"账号或密码错误，请确认后重新登录");
                    else{
                        LoginClient.this.setVisible(false);
                        test_ZSGC w1=new test_ZSGC();
                        w1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        w1.setTitle("试题维护系统——出题人："+tmpS);
                        w1.setT1(new teacher_Obj(tmpId,tmpS));
                    }
                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
                catch (ClassNotFoundException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
            }
        });
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SignUp_Client x=new SignUp_Client();
				x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				x.setLocation(300,20);
			}
		});
    }
    public static void main(String args[]){
        LoginClient w=new LoginClient();
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void connectionServer(){
        try {
            client=new Socket(InetAddress.getByName("127.0.0.1"),12345);    
        } catch (IOException ex) { Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
    }



}
