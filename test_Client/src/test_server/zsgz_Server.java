package test_server;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import test_Client.userClass;

public class zsgz_Server extends JFrame {

    private JTextArea edit1;
    private Container container;
    private JScrollPane scroll1;
    private Font font1;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerSocket server;
    private Socket connection;
    private StringBuffer showMessage;
    List<String> idlist=new ArrayList<String>();
    public zsgz_Server() {
        super("保存题型服务器");
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
        zsgz_Server w = new zsgz_Server();
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.runServer();
    }
    public void runServer() {
        try {
            server = new ServerSocket(12341, 10);
            while (true) {
                waitForConnection();
                getStreams();
                processConnection();
            }
        } catch (IOException | SQLException ex) {Logger.getLogger(LoginServer.class.getName()).log(Level.SEVERE, null, ex);}
    }
    private void getStreams() {
        try {
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());
            showMessage.append("已得到输入的数据\n");
            edit1.setText(showMessage.toString());

        } catch (IOException ex) {Logger.getLogger(LoginServer.class.getName()).log(Level.SEVERE, null, ex);}
    }
    private void waitForConnection() {
        showMessage.append("等待保存....\n");
        edit1.setText(showMessage.toString());
        try {
            connection=server.accept();
            System.out.println(connection);
        } catch (IOException ex) {Logger.getLogger(LoginServer.class.getName()).log(Level.SEVERE, null, ex);} 
        showMessage.append("连接完成，获取相应的输入输出流.....\n");
        edit1.setText(showMessage.toString());

    }
    String tmpidString=null;
    private void processConnection() throws SQLException {
        try {
            question_Obj que = (question_Obj) input.readObject();
            if(que.getQueType().trim().toString().equals("dx_z")){
            	showMessage.append(que.getQueType()+"\n");
                edit1.setText(showMessage.toString());
                Statement stm=UserDAO.getStm();
                String sqll="insert into dx values('"+que.getDxid()+"','"+que.getDxquestion()+"',"+que.getAnswer_num()+",'"+que.getRight_answer()+"',"+que.getPoints()+",'"+que.getTeacher()+"')";
                int k=stm.executeUpdate(sqll);//产生的值是影响的行数
                if(k>0) 
                	output.writeObject("successful");
                else 
                	output.writeObject("error");
                String delid="delete from dxid where id='"+que.getDxid()+"'";
                int delk=stm.executeUpdate(delid);
                if(delk>0)
                	System.out.println("Delete ID Successful");
                else
                	System.out.println("Delete ID Error");
                
            }
            if(que.getQueType().trim().toString().equals("dx_idreturn")){
            	Statement stm=UserDAO.getStm();
            	ResultSet rs=stm.executeQuery("select * from dxid");
            	String sqll="update dxid set state='0' where id='"+que.getDxid()+"'";
                int k=stm.executeUpdate(sqll);
                if(k>0)
                	System.out.println("return id successful");
                else
                	System.out.println("return id error");
                
            }
            if(que.getQueType().trim().toString().equals("dx_s")){
            	showMessage.append(que.getQueType()+"\n");
                edit1.setText(showMessage.toString());
                Statement stm=UserDAO.getStm();
                ResultSet rs=stm.executeQuery("select * from dx where dx_id='"+que.getDxid()+"'");
                String sqll="delete from dx where dx_id='"+que.getDxid()+"'";
                int k=stm.executeUpdate(sqll);
                if(k>0)
                	output.writeObject("successful");
                else
                	output.writeObject("error");
            }
            if(que.getQueType().trim().toString().equals("dx_g")){
            	showMessage.append(que.getQueType()+"\n");
                edit1.setText(showMessage.toString());
                Statement stm=UserDAO.getStm();
                ResultSet rs=stm.executeQuery("select * from dx where dx_id='"+que.getDxid()+"'");
                rs.beforeFirst();
                question_Obj tmpquestion = null;
                while(rs.next()){
                	System.out.println(rs.getString("dx_id")+","+rs.getString("dx_question"));
                	tmpquestion=new question_Obj("dx_g",rs.getString("dx_id"),rs.getString("dx_question"),Integer.parseInt(rs.getString("dx_num")),rs.getString("dx_answer"),Integer.parseInt(rs.getString("dx_point")));

                }
                output.writeObject(tmpquestion);
                output.flush();
                output.close();
            }
            if(que.getQueType().trim().toString().equals("dx_gsave")){
            	showMessage.append(que.getQueType()+"\n");
                edit1.setText(showMessage.toString());
                Statement stm=UserDAO.getStm();
                ResultSet rs=stm.executeQuery("select * from dx where dx_id='"+que.getDxid()+"'");
                //update 表名 set 更改内容 where 条件;
                String sqll="update dx set dx_teacher='"+que.getTeacher()+"' ,dx_question='"+que.getDxquestion()+"',dx_num="+que.getAnswer_num()+",dx_answer='"+que.getRight_answer()+"',dx_point="+que.getPoints()+" where dx_id='"+que.getDxid()+"'";
                int k=stm.executeUpdate(sqll);
                if(k>0)
                	output.writeObject("change successful");
                else
                	output.writeObject("error");
            }
            if(que.getQueType().trim().toString().equals("dx_c")){
            	showMessage.append(que.getQueType()+"\n");
                edit1.setText(showMessage.toString());
                Statement stm=UserDAO.getStm();
                ResultSet rs=stm.executeQuery("select * from dx where dx_id='"+que.getDxid()+"'");
                rs.beforeFirst();
                question_Obj tmpquestion = null;
                while(rs.next()){
                	System.out.println(rs.getString("dx_id")+","+rs.getString("dx_question"));
                	tmpquestion=new question_Obj("dx_c",rs.getString("dx_id"),rs.getString("dx_question"),Integer.parseInt(rs.getString("dx_num")),rs.getString("dx_answer"),Integer.parseInt(rs.getString("dx_point")));
                }
                output.writeObject(tmpquestion);
                output.flush();
                output.close();
            }
            if(que.getQueType().trim().toString().equals("dx_id")){
                Statement stm=UserDAO.getStm();
            	ResultSet rs=stm.executeQuery("select * from dxid");
                rs.beforeFirst();
                while(rs.next()){
                	if(rs.getString("state").equals("0")) {
                		tmpidString=rs.getString("id");
                		String sqll="update dxid set state='1' where id='"+rs.getString("id")+"'";
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
            /////////////////////////////////////////////////////////////////////////////////////////
            if(que.getQueType().trim().toString().equals("mx_id")){
                Statement stm=UserDAO.getStm();
            	ResultSet rs=stm.executeQuery("select * from mxid");
                rs.beforeFirst();
                while(rs.next()){
                	if(rs.getString("state").equals("0")) {
                		tmpidString=rs.getString("id");
                		String sqll="update mxid set state='1' where id='"+rs.getString("id")+"'";
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
            if(que.getQueType().trim().toString().equals("mx_idreturn")){
            	Statement stm=UserDAO.getStm();
            	ResultSet rs=stm.executeQuery("select * from mxid");
            	String sqll="update mxid set state='0' where id='"+que.getDxid()+"'";
                int k=stm.executeUpdate(sqll);
                if(k>0)
                	System.out.println("return id successful");
                else
                	System.out.println("return id error");
            }
            if(que.getQueType().trim().toString().equals("mx_z")){
            	showMessage.append(que.getQueType()+"\n");
                edit1.setText(showMessage.toString());
                Statement stm=UserDAO.getStm();
                String sqll="insert into mx values('"+que.getDxid()+"','"+que.getDxquestion()+"',"+que.getAnswer_num()+",'"+que.getRight_answer()+"',"+que.getPoints()+",'"+que.getTeacher()+"')";
                int k=stm.executeUpdate(sqll);//产生的值是影响的行数
                if(k>0) 
                	output.writeObject("successful");
                else 
                	output.writeObject("error");
                String delid="delete from mxid where id='"+que.getDxid()+"'";
                int delk=stm.executeUpdate(delid);
                if(delk>0)
                	System.out.println("Delete ID Successful");
                else
                	System.out.println("Delete ID Error");
            }
            if(que.getQueType().trim().toString().equals("mx_c")){
            	showMessage.append(que.getQueType()+"\n");
                edit1.setText(showMessage.toString());
                Statement stm=UserDAO.getStm();
                ResultSet rs=stm.executeQuery("select * from mx where mx_id='"+que.getDxid()+"'");
                rs.beforeFirst();
                question_Obj tmpquestion = null;
                while(rs.next()){
                	tmpquestion=new question_Obj("mx_c",rs.getString("mx_id"),rs.getString("mx_question"),Integer.parseInt(rs.getString("mx_num")),rs.getString("mx_answer"),Integer.parseInt(rs.getString("mx_point")),rs.getString("mx_teacher"));
                }
                output.writeObject(tmpquestion);
                output.flush();
                output.close();
            }
            if(que.getQueType().trim().toString().equals("mx_s")){
            	showMessage.append(que.getQueType()+"\n");
                edit1.setText(showMessage.toString());
                Statement stm=UserDAO.getStm();
                ResultSet rs=stm.executeQuery("select * from mx where mx_id='"+que.getDxid()+"'");
                String sqll="delete from mx where mx_id='"+que.getDxid()+"'";
                int k=stm.executeUpdate(sqll);
                if(k>0)
                	output.writeObject("successful");
                else
                	output.writeObject("error");
            }
            if(que.getQueType().trim().toString().equals("mx_gsave")){
            	showMessage.append(que.getQueType()+"\n");
                edit1.setText(showMessage.toString());
                Statement stm=UserDAO.getStm();
                ResultSet rs=stm.executeQuery("select * from mx where mx_id='"+que.getDxid()+"'");
                String sqll="update mx set mx_teacher='"+que.getTeacher()+"' ,mx_question='"+que.getDxquestion()+"',mx_num="+que.getAnswer_num()+",mx_answer='"+que.getRight_answer()+"',mx_point="+que.getPoints()+" where mx_id='"+que.getDxid()+"'";
                int k=stm.executeUpdate(sqll);
                if(k>0)
                	output.writeObject("change successful");
                else
                	output.writeObject("error");
            }
            ///////////////////////////////////////////////////////////////////////////////////////
            if(que.getQueType().trim().toString().equals("pd_id")){
                Statement stm=UserDAO.getStm();
            	ResultSet rs=stm.executeQuery("select * from pdid");
                rs.beforeFirst();
                while(rs.next()){
                	if(rs.getString("state").equals("0")) {
                		tmpidString=rs.getString("id");
                		String sqll="update pdid set state='1' where id='"+rs.getString("id")+"'";
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
            if(que.getQueType().trim().toString().equals("pd_idreturn")){
            	Statement stm=UserDAO.getStm();
            	ResultSet rs=stm.executeQuery("select * from pdid");
            	String sqll="update pdid set state='0' where id='"+que.getDxid()+"'";
                int k=stm.executeUpdate(sqll);
                if(k>0)
                	System.out.println("return id successful");
                else
                	System.out.println("return id error");
            }
            if(que.getQueType().trim().toString().equals("pd_z")){
            	showMessage.append(que.getQueType()+"\n");
                edit1.setText(showMessage.toString());
                Statement stm=UserDAO.getStm();
                String sqll="insert into pd values('"+que.getDxid()+"','"+que.getDxquestion()+"','"+que.getRight_answer()+"',"+que.getPoints()+",'"+que.getTeacher()+"')";
                int k=stm.executeUpdate(sqll);//产生的值是影响的行数
                if(k>0) 
                	output.writeObject("successful");
                else 
                	output.writeObject("error");
                String delid="delete from pdid where id='"+que.getDxid()+"'";
                int delk=stm.executeUpdate(delid);
                if(delk>0)
                	System.out.println("Delete ID Successful");
                else
                	System.out.println("Delete ID Error");
            }
            if(que.getQueType().trim().toString().equals("pd_c")){
            	showMessage.append(que.getQueType()+"\n");
                edit1.setText(showMessage.toString());
                Statement stm=UserDAO.getStm();
                ResultSet rs=stm.executeQuery("select * from pd where pd_id='"+que.getDxid()+"'");
                rs.beforeFirst();
                question_Obj tmpquestion = null;
                while(rs.next()){
                	tmpquestion=new question_Obj("pd_c",rs.getString("pd_id"),rs.getString("pd_question"),rs.getString("pd_answer"),Integer.parseInt(rs.getString("pd_point")),rs.getString("pd_teacher"));
                }
                output.writeObject(tmpquestion);
                output.flush();
                output.close();
            }
            if(que.getQueType().trim().toString().equals("pd_s")){
            	showMessage.append(que.getQueType()+"\n");
                edit1.setText(showMessage.toString());
                Statement stm=UserDAO.getStm();
                ResultSet rs=stm.executeQuery("select * from pd where pd_id='"+que.getDxid()+"'");
                String sqll="delete from pd where pd_id='"+que.getDxid()+"'";
                int k=stm.executeUpdate(sqll);
                if(k>0)
                	output.writeObject("successful");
                else
                	output.writeObject("error");
            }
            if(que.getQueType().trim().toString().equals("pd_gsave")){
            	showMessage.append(que.getQueType()+"\n");
                edit1.setText(showMessage.toString());
                Statement stm=UserDAO.getStm();
                ResultSet rs=stm.executeQuery("select * from pd where pd_id='"+que.getDxid()+"'");
                String sqll="update pd set pd_question='"+que.getDxquestion()+"',pd_answer='"+que.getRight_answer()+"',pd_point="+que.getPoints()+",pd_teacher='"+que.getTeacher()+"' where pd_id='"+que.getDxid()+"'";
                int k=stm.executeUpdate(sqll);
                if(k>0)
                	output.writeObject("change successful");
                else
                	output.writeObject("error");
            }
            ///////////////////////////////////////////////////////////////////////////////////////////
            if(que.getQueType().trim().toString().equals("tk_id")){
                Statement stm=UserDAO.getStm();
            	ResultSet rs=stm.executeQuery("select * from tkid");
                rs.beforeFirst();
                while(rs.next()){
                	if(rs.getString("state").equals("0")) {
                		tmpidString=rs.getString("id");
                		String sqll="update tkid set state='1' where id='"+rs.getString("id")+"'";
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
            if(que.getQueType().trim().toString().equals("tk_idreturn")){
            	Statement stm=UserDAO.getStm();
            	ResultSet rs=stm.executeQuery("select * from tkid");
            	String sqll="update tkid set state='0' where id='"+que.getDxid()+"'";
                int k=stm.executeUpdate(sqll);
                if(k>0)
                	System.out.println("return id successful");
                else
                	System.out.println("return id error");
            }
            if(que.getQueType().trim().toString().equals("tk_z")){
            	showMessage.append(que.getQueType()+"\n");
                edit1.setText(showMessage.toString());
                Statement stm=UserDAO.getStm();
                String sqll="insert into tk values('"+que.getDxid()+"','"+que.getDxquestion()+"',"+que.getAnswer_num()+",'"+que.getTkorder()+"','"+que.getTka1()+"','"+que.getTka2()+"','"+que.getTka3()+"','"+que.getTka4()+"','"+que.getTka5()+"',"+que.getPoints()+",'"+que.getTeacher()+"')";
                int k=stm.executeUpdate(sqll);
                if(k>0) 
                	output.writeObject("successful");
                else 
                	output.writeObject("error");
                String delid="delete from tkid where id='"+que.getDxid()+"'";
                int delk=stm.executeUpdate(delid);
                if(delk>0)
                	System.out.println("Delete ID Successful");
                else
                	System.out.println("Delete ID Error");
            }
            if(que.getQueType().trim().toString().equals("tk_c")){
            	showMessage.append(que.getQueType()+"\n");
                edit1.setText(showMessage.toString());
                Statement stm=UserDAO.getStm();
                ResultSet rs=stm.executeQuery("select * from tk where tk_id='"+que.getDxid()+"'");
                rs.beforeFirst();
                question_Obj tmpquestion = null;
                while(rs.next()){
                	tmpquestion=new question_Obj("tk_c",rs.getString("tk_id"),rs.getString("tk_question"),Integer.parseInt(rs.getString("tk_num").toString()),rs.getString("tk_order"),rs.getString("tk_a1"),rs.getString("tk_a2"),rs.getString("tk_a3"),rs.getString("tk_a4"),rs.getString("tk_a5"),Integer.parseInt(rs.getString("tk_point").toString()),rs.getString("tk_teacher"));
                }
                output.writeObject(tmpquestion);
                output.flush();
                output.close();
            }
            if(que.getQueType().trim().toString().equals("tk_gsave")){
            	showMessage.append(que.getQueType()+"\n");
                edit1.setText(showMessage.toString());
                Statement stm=UserDAO.getStm();
                ResultSet rs=stm.executeQuery("select * from tk where tk_id='"+que.getDxid()+"'");
                String sqll="update tk set tk_question='"+que.getDxquestion()+"',tk_num="+que.getAnswer_num()+",tk_order='"+que.getTkorder()+"',tk_a1='"+que.getTka1()+"',tk_a2='"+que.getTka2()+"',tk_a3='"+que.getTka3()+"',tk_a4='"+que.getTka4()+"',tk_a5='"+que.getTka5()+"',tk_point="+que.getPoints()+",tk_teacher='"+que.getTeacher()+"' where tk_id='"+que.getDxid()+"'";
                int k=stm.executeUpdate(sqll);
                if(k>0)
                	output.writeObject("change successful");
                else
                	output.writeObject("error");
            }
            if(que.getQueType().trim().toString().equals("tk_s")){
            	showMessage.append(que.getQueType()+"\n");
                edit1.setText(showMessage.toString());
                Statement stm=UserDAO.getStm();
                ResultSet rs=stm.executeQuery("select * from tk where tk_id='"+que.getDxid()+"'");
                String sqll="delete from tk where tk_id='"+que.getDxid()+"'";
                int k=stm.executeUpdate(sqll);
                if(k>0)
                	output.writeObject("successful");
                else
                	output.writeObject("error");
            }
        } catch (IOException ex) {Logger.getLogger(LoginServer.class.getName()).log(Level.SEVERE, null, ex);} 
        catch (ClassNotFoundException ex) {Logger.getLogger(LoginServer.class.getName()).log(Level.SEVERE, null, ex);}
    }

}
