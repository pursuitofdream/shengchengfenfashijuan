package test_server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class connect_MySQL {//默认从默认库里找表格
	
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
    	List<String> idlist=new ArrayList<String>();
        Class.forName("com.mysql.jdbc.Driver");//加载驱动程序
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test1029?zeroDateTimeBehavior=convertToNull", "root", "Gjt147369456");//创建连接字符串
        Statement stm=con.createStatement();//连接状态句柄
        ///////////////查询数据//////////////////////////////////////////////////////////
        ResultSet rs=stm.executeQuery("select * from dx where dx_id='dx1001'");//正常写语句
        rs.beforeFirst();//从第一条前面开始
        while(rs.next()){
//        	idlist.add(rs.getString("dx_id"));
            System.out.println(rs.getString("dx_id")+"\t"+rs.getString("dx_question")+"\t"+rs.getString("dx_num"));
        }
        rs.close();
        stm.close();
//        for(String i:idlist) {
//        	System.out.println(i);
//        }
//        question_Obj dx1=new question_Obj("dx","dx1003","haha",4,"A",3);
//        String sqll="insert into dx values("+dx1.getDxid()+","+dx1.getDxquestion()+","+dx1.getAnswer_num()+","+dx1.getRight_answer()+","+dx1.getPoints()+")";
//        String sqll="insert into dx values('"+dx1.getDxid()+"','"+dx1.getDxquestion()+"',"+dx1.getAnswer_num()+",'"+dx1.getRight_answer()+"',"+dx1.getPoints()+")";
//
//        int k=stm.executeUpdate(sqll);//产生的值是影响的行数
//        if(k>0) {
//        	System.out.println("seccessful");
//        }
       //////////////////读取表的结构数据////////////////////////////////////////////////
//        ResultSet rs=stm.executeQuery("select * from tmpstudent");
//        ResultSetMetaData rsm=rs.getMetaData();
//        System.out.println(rsm.getColumnCount());//得到一共几列
//        for(int i=1;i<=rsm.getColumnCount();i++){
//            System.out.println(rsm.getColumnName(i)+"\t\t"+rsm.getColumnTypeName(i));//得到属性名和属性的类型
//        }
//        rs.close();
        //////////////////插入数据（删改也是update）改变结构也是这个////////////////////////////////////////////
//        String sqll="insert into tmpstudent(name,zy,chinese,english,math,computer) values('haha',3,0,0,0,0)";
//        int k=stm.executeUpdate(sqll);//产生的值是影响的行数
//        if(k>0)
//            System.out.println("seccessful");
//        else
//            System.out.println("error_haha");
        //////////////////调用存储过程///////////////////////////////////////////////////////
//        ResultSet rs=stm.executeQuery("call tout()");
//        rs.beforeFirst();//从第一条前面开始
//        while(rs.next()){
//            System.out.println(rs.getString("cj_id")+"\t");//不知道里面的属性的话还需要自己查询。
//        }
//        rs.close();
//        stm.close();
        /////////////不分增删改查，直接用（不咋用）///////////////////////////////////////////////////////////////////
//        boolean t=stm.execute("delete from tmpstudent where id=201");//查询语句就是真值，非查询语句就是假值。
//        System.out.println(t);
    }
}
