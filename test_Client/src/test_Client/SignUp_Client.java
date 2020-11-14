package test_Client;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import test_server.question_Obj;


public class SignUp_Client extends JFrame{
	private int cj=0;
	private String fin_sex="";
	private ObjectOutputStream output;
    private ObjectInputStream input;
    private Socket client;
    private String ttid="";
	public SignUp_Client(){
		super("注册信息");
		Container container=getContentPane();
		container.setLayout(null);
		Font font1=new Font("微软雅黑",0,20);
		JLabel sid=new JLabel("账号：");
		JLabel stname=new JLabel("真实姓名：");
		JLabel sfname=new JLabel("昵称：");
		JLabel spassword=new JLabel("密码：");
		JLabel srpassword=new JLabel("重复密码：");
		JLabel sbirthday=new JLabel("出生日期：");
		JLabel ssex=new JLabel("性别：");
		JLabel sphone=new JLabel("电话：");
		JLabel smemo=new JLabel("备注：");
		JButton bup=new JButton("提交");
		JButton breset=new JButton("重置");
		JButton bexit=new JButton("关闭");
		JButton bcheck=new JButton("检测");
		JTextField tid=new JTextField();
		JTextField ttname=new JTextField();
		JTextField tfname=new JTextField();
		JTextField tpassword=new JTextField();
		JTextField trpassword=new JTextField();
		JComboBox year=new JComboBox();
		JComboBox mouth=new JComboBox();
		JComboBox day=new JComboBox();
		JRadioButton man=new JRadioButton("男");
		JRadioButton woman=new JRadioButton("女");
		JTextField tphone=new JTextField();
		JTextArea tmemo=new JTextArea();
		//////////////////////////////////
		sid.setFont(font1);
		stname.setFont(font1);
		sfname.setFont(font1);
		spassword.setFont(font1);
		srpassword.setFont(font1);
		sbirthday.setFont(font1);
		ssex.setFont(font1);
		sphone.setFont(font1);
		smemo.setFont(font1);
		bup.setFont(font1);
		breset.setFont(font1);
		bexit.setFont(font1);
		bcheck.setFont(font1);
		tid.setFont(font1);
		ttname.setFont(font1);
		tfname.setFont(font1);
		tpassword.setFont(font1);
		trpassword.setFont(font1);
		year.setFont(font1);
		mouth.setFont(font1);
		day.setFont(font1);
		man.setFont(font1);
		woman.setFont(font1);
		tphone.setFont(font1);
		tmemo.setFont(font1);
		//////////////////////////////
		sid.setSize(100,40);
		stname.setSize(100,40);
		sfname.setSize(100,40);
		spassword.setSize(100,40);
		srpassword.setSize(100,40);
		sbirthday.setSize(100,40);
		ssex.setSize(100,40);
		sphone.setSize(100,40);
		smemo.setSize(100,40);
		bup.setSize(100,40);
		breset.setSize(100,40);
		bexit.setSize(100,40);
		bcheck.setSize(100,40);
		tid.setSize(300,40);
		ttname.setSize(300,40);
		tfname.setSize(300,40);
		tpassword.setSize(300,40);
		trpassword.setSize(300,40);
		year.setSize(100,40);
		mouth.setSize(100,40);
		day.setSize(100,40);
		man.setSize(100,40);
		woman.setSize(100,40);
		tphone.setSize(300,40);
		tmemo.setSize(300,40);
		////////////////////////////////
		sid.setLocation(20,20);
		stname.setLocation(20,70);
		sfname.setLocation(20,120);
		spassword.setLocation(20,170);
		srpassword.setLocation(20,220);
		sbirthday.setLocation(20,270);
		ssex.setLocation(20,320);
		sphone.setLocation(20,370);
		smemo.setLocation(20,420);
		bup.setLocation(100,500);
		breset.setLocation(250,500);
		bexit.setLocation(400,500);
		bcheck.setLocation(450,220);
		tid.setLocation(150,20);
		ttname.setLocation(150,70);
		tfname.setLocation(150,120);
		tpassword.setLocation(150,170);
		trpassword.setLocation(150,220);
		year.setLocation(150,270);
		mouth.setLocation(250,270);
		day.setLocation(350,270);
		man.setLocation(150,320);
		woman.setLocation(250,320);
		tphone.setLocation(150,370);
		tmemo.setLocation(150,420);
		container.add(sid);
		container.add(stname);
		//container.add(sfname);
		container.add(spassword);
		//container.add(srpassword);
		container.add(sbirthday);
		container.add(ssex);
		container.add(sphone);
		container.add(smemo);
		container.add(bup);
		//container.add(breset);
		container.add(bexit);
		//container.add(bcheck);
		container.add(tid);
		container.add(ttname);
		//container.add(tfname);
		container.add(tpassword);
		//container.add(trpassword);
		container.add(year);
		container.add(mouth);
		container.add(day);
		container.add(man);
		container.add(woman);
		container.add(tphone);
		container.add(tmemo);
		/////////////////////////////////
		ButtonGroup g=new ButtonGroup();
		//tid.setEditable(false);
		g.add(man);
		g.add(woman);
		tid.setEditable(false);
		/////////////////////////////////
		this.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent arg0) {
				String tmpS=null;
				try {
                    connectionServer();
                    output = new ObjectOutputStream(client.getOutputStream());
                    output.flush();
                    input = new ObjectInputStream(client.getInputStream());
                    output.writeObject(new teacher_Obj("t_id"));
                    output.flush();
                    tmpS=(String)input.readObject();
                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
				catch (ClassNotFoundException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);}
				tid.setText(tmpS);
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				connectionServer();
                try {
					output = new ObjectOutputStream(client.getOutputStream());
					output.flush();
                    input = new ObjectInputStream(client.getInputStream());
                    teacher_Obj tidreturn=new teacher_Obj("t_idreturn",tid.getText().toString(),"");
                    output.writeObject(tidreturn);
                    output.flush();
                    JOptionPane.showMessageDialog(null, "closing and return id successful");
				} catch (IOException e) {e.printStackTrace();}  
				
			}
		});
		bexit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connectionServer();
                try {
					output = new ObjectOutputStream(client.getOutputStream());
					output.flush();
                    input = new ObjectInputStream(client.getInputStream());
                    teacher_Obj tidreturn=new teacher_Obj("t_idreturn",tid.getText().toString(),"");
                    output.writeObject(tidreturn);
                    output.flush();
                    JOptionPane.showMessageDialog(null, "closing and return id successful");
				} catch (IOException e1) {e1.printStackTrace();}
				SignUp_Client.this.dispose();
			}
		});
		for(int i=1990;i<2020;i++){
	        year.addItem(i);
	    }
	    for(int i=1;i<=12;i++){
	        mouth.addItem(i);
	    }
	    for(int i=1;i<=31;i++){
	        day.addItem(i);
	    }
	    year.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int Year=Integer.parseInt(year.getSelectedItem().toString());
				int Mouth=Integer.parseInt(mouth.getSelectedItem().toString());
				int Day=Integer.parseInt(day.getSelectedItem().toString());
				if(Mouth==2){
					if(Year%4==0){
						day.removeAllItems();
						for(int i=1;i<=29;i++){
							day.addItem(i);
						}
						day.setSelectedIndex(cj);
					}
					else{
						day.removeAllItems();
						for(int i=1;i<=28;i++){day.addItem(i);}
						day.setSelectedIndex(cj);
					}
				}
				if(Mouth==1||Mouth==3||Mouth==5||Mouth==7||Mouth==8||Mouth==10||Mouth==12){
					day.removeAllItems();
					for(int i=1;i<=31;i++){day.addItem(i);}
					day.setSelectedIndex(cj);
				}
				if(Mouth==4||Mouth==6||Mouth==9||Mouth==11){
					day.removeAllItems();
					for(int i=1;i<=30;i++){day.addItem(i);}
					day.setSelectedIndex(cj);
				}
				if(Year%4!=0&&cj==28) {
					cj=0;
					day.setSelectedItem(1);
				}	
			}
		});
		mouth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cj=day.getSelectedIndex();
				System.out.println("mouth_cj:"+cj);
				int Year=Integer.parseInt(year.getSelectedItem().toString());
				int Mouth=Integer.parseInt(mouth.getSelectedItem().toString());
				int Day=Integer.parseInt(day.getSelectedItem().toString());
				if(Mouth==2){
					if(Year%4==0){
						day.removeAllItems();
						for(int i=1;i<=29;i++){day.addItem(i);}
						
					}
					else{
						day.removeAllItems();
						for(int i=1;i<=28;i++){day.addItem(i);}
					}
				}
				if(Mouth==1||Mouth==3||Mouth==5||Mouth==7||Mouth==8||Mouth==10||Mouth==12){
					day.removeAllItems();
					for(int i=1;i<=31;i++){day.addItem(i);}
					day.setSelectedIndex(cj);
				}
				if(Mouth==4||Mouth==6||Mouth==9||Mouth==11){
					day.removeAllItems();
					for(int i=1;i<=30;i++){day.addItem(i);}
					day.setSelectedIndex(cj);
				}
				if(Mouth==2||Mouth==4||Mouth==6||Mouth==9||Mouth==11&&cj==30) {
					cj=0;
					day.setSelectedItem(1);
				}	   
				if(Mouth==2&&cj==29) {
					cj=0;
					day.setSelectedItem(1);
				}
			}
		});
		day.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				cj=day.getSelectedIndex();	
			}
		});

		man.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(man.isSelected()) {
					fin_sex="man";
				}
			}
		});
		woman.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(woman.isSelected()) {
					fin_sex="woman";
				}
			}
		});
		
		setSize(600,600);
		setVisible(true);
		bup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				connectionServer();
				String tmpsex;
				if(man.isSelected()){
					tmpsex="man";
				}
				else{
					tmpsex="woman";
				}
				String tmpid=tid.getText();
				String tmpname=ttname.getText();
				String tmppassword=tpassword.getText();
				String tmpyear=year.getSelectedItem().toString();
				String tmpmouth=mouth.getSelectedItem().toString();
				String tmpday=day.getSelectedItem().toString();
				String tmpphone=tphone.getText();
				String tmpmemo=tmemo.getText();
				teacher_Obj nt=new teacher_Obj("zhuce",tmpid,tmpname,tmppassword,tmpyear,tmpmouth,tmpday,tmpsex,tmpphone,tmpmemo);
				try {
                    output = new ObjectOutputStream(client.getOutputStream());
                    output.flush();
                    input = new ObjectInputStream(client.getInputStream());
                    output.writeObject(nt);
                    output.flush();
                    String tmpS=(String)input.readObject();
                    if(tmpS.equals("Error"))
                        JOptionPane.showMessageDialog(null,"账号或密码错误，请确认后重新登录");
                    else{
                    	JOptionPane.showMessageDialog(null,"注册成功");
                    	ttname.setText("");
                    	tpassword.setText("");
                    	year.setSelectedItem(0);
                    	mouth.setSelectedItem(0);
                    	day.setSelectedItem(0);
                    	man.setSelected(false);
                    	woman.setSelected(false);
                    	tphone.setText("");
                    	tmemo.setText("");
                    	String tmpS1=null;
        				try {
                            connectionServer();
                            output = new ObjectOutputStream(client.getOutputStream());
                            output.flush();
                            input = new ObjectInputStream(client.getInputStream());
                            output.writeObject(new teacher_Obj("t_id"));
                            output.flush();
                            tmpS1=(String)input.readObject();
                        } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
        				catch (ClassNotFoundException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);}
        				tid.setText(tmpS1);
                    }
                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
                catch (ClassNotFoundException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);}
				
			}
		});
	}
	public static void main(String[] args) {
		SignUp_Client w=new SignUp_Client();
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.setLocation(200,10);
	}
    public void connectionServer(){
        try {
            client=new Socket(InetAddress.getByName("127.0.0.1"),12345);    
        } catch (IOException ex) { Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
    }
	public String getTid() {
		return ttid;
	}
	public void setTid(String ttid) {
		this.ttid = ttid;
	}
}
