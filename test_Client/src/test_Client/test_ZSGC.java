package test_Client;


import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import test_server.question_Obj;


public class test_ZSGC extends JFrame{
	private String t_name;
	private JDesktopPane desk1;
	private ObjectOutputStream output;
    private ObjectInputStream input;
    private Socket client;
    private String tmpdx_id;
    teacher_Obj t1=new teacher_Obj();
	public test_ZSGC() {
		super("试题维护系统");
		Container container=getContentPane();
//		container.setLayout(null);
		Font font1=new Font("微软雅黑",0,25);
		Font font2=new Font("微软雅黑",0,20);
		//////////////////////////////////////////////////
		desk1=new JDesktopPane();
		JMenuBar bar1=new JMenuBar();
		JMenu main_Menu=new JMenu("维护");
		JMenu main_File=new JMenu("文件");
		JMenu wh_dx=new JMenu("单选");
		JMenu wh_mx=new JMenu("多选");
		JMenu wh_tk=new JMenu("填空");
		JMenu wh_pd=new JMenu("判断");
		JMenuItem dx_z=new JMenuItem("增加");
		JMenuItem dx_s=new JMenuItem("删除");
		JMenuItem dx_g=new JMenuItem("修改");
		JMenuItem dx_c=new JMenuItem("查找");
		JMenuItem mx_z=new JMenuItem("增加");
		JMenuItem mx_s=new JMenuItem("删除");
		JMenuItem mx_g=new JMenuItem("修改");
		JMenuItem mx_c=new JMenuItem("查找");
		JMenuItem tk_z=new JMenuItem("增加");
		JMenuItem tk_s=new JMenuItem("删除");
		JMenuItem tk_g=new JMenuItem("修改");
		JMenuItem tk_c=new JMenuItem("查找");
		JMenuItem pd_z=new JMenuItem("增加");
		JMenuItem pd_s=new JMenuItem("删除");
		JMenuItem pd_g=new JMenuItem("修改");
		JMenuItem pd_c=new JMenuItem("查找");
		//////////////////////////////////////////////////
		main_Menu.setFont(font1);
		main_File.setFont(font1);
		wh_dx.setFont(font1);
		wh_mx.setFont(font1);
		wh_tk.setFont(font1);
		wh_pd.setFont(font1);
		dx_z.setFont(font1);
		dx_s.setFont(font1);
		dx_g.setFont(font1);
		dx_c.setFont(font1);
		mx_z.setFont(font1);
		mx_s.setFont(font1);
		mx_g.setFont(font1);
		mx_c.setFont(font1);
		tk_z.setFont(font1);
		tk_s.setFont(font1);
		tk_g.setFont(font1);
		tk_c.setFont(font1);
		pd_z.setFont(font1);
		pd_s.setFont(font1);
		pd_g.setFont(font1);
		pd_c.setFont(font1);
		//////////////////////////////////////////////////
		setJMenuBar(bar1);
		bar1.add(main_File);
		bar1.add(main_Menu);
		main_Menu.add(wh_dx);
		main_Menu.add(wh_mx);
		main_Menu.add(wh_tk);
		main_Menu.add(wh_pd);
		wh_dx.add(dx_z);
		wh_dx.add(dx_s);
		wh_dx.add(dx_g);
		wh_dx.add(dx_c);
		wh_mx.add(mx_z);
		wh_mx.add(mx_s);
		wh_mx.add(mx_g);
		wh_mx.add(mx_c);
		wh_tk.add(tk_z);
		wh_tk.add(tk_s);
		wh_tk.add(tk_g);
		wh_tk.add(tk_c);
		wh_pd.add(pd_z);
		wh_pd.add(pd_s);
		wh_pd.add(pd_g);
		wh_pd.add(pd_c);
		//////////////////////////////////////////////////
	    dx_z.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
                    typeconnectionServer();
                    output = new ObjectOutputStream(client.getOutputStream());
                    output.flush();
                    input = new ObjectInputStream(client.getInputStream());
                    output.writeObject(new question_Obj("dx_id"));
                    output.flush();
                    String tmpS=(String)input.readObject();
                    tmpdx_id=tmpS;
                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
				catch (ClassNotFoundException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
				JInternalFrame subFrame=new JInternalFrame("增加选择题，出题人："+t1.getTc_name(),true,true,true,true);
				Container subcontainer=subFrame.getContentPane();
				subcontainer.setLayout(null);
				//------------------------------------------
				JLabel ds_id=new JLabel("题号：");
				JLabel ds_question=new JLabel("题干：");
				JLabel ds_ansnum=new JLabel("选项数量：");
				JLabel ds_bingo=new JLabel("正确答案：");
				JLabel ds_point=new JLabel("得分：");
				JTextField text_dsid=new JTextField(30);
				JTextArea question_area=new JTextArea(50,50);
				JScrollPane sp1=new JScrollPane(question_area);
				JComboBox answerBox=new JComboBox();
				JComboBox questionBox=new JComboBox();
				JTextField text_point=new JTextField(30);
				JButton bsave=new JButton("保存");
				JButton bexit=new JButton("关闭");
				for(int i=2;i<7;i++) {answerBox.addItem(i);}
				answerBox.setSelectedIndex(2);
				text_dsid.setText(tmpdx_id);
				questionBox.addItem("A");
				questionBox.addItem("B");
				questionBox.addItem("C");
				questionBox.addItem("D");
				questionBox.addItem("E");
				questionBox.addItem("F");
				questionBox.addItem("G");
				questionBox.setSelectedIndex(0);
				sp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				text_dsid.setEditable(false);
				//////////////////////////////////////////
				ds_id.setFont(font2);
				ds_question.setFont(font2);
				ds_ansnum.setFont(font2);
				ds_bingo.setFont(font2);
				ds_point.setFont(font2);
				text_dsid.setFont(font2);
				question_area.setFont(font2);
				answerBox.setFont(font2);
				questionBox.setFont(font2);
				bsave.setFont(font2);
				bexit.setFont(font2);
				text_point.setFont(font2);
				//////////////////////////////////////////
				ds_id.setSize(100,30);
				ds_question.setSize(100,30);
				ds_bingo.setSize(100,30);
				ds_ansnum.setSize(100,30);
				ds_point.setSize(100,30);
				text_dsid.setSize(300,30);
				question_area.setSize(800,230);
				sp1.setSize(800,230);
				answerBox.setSize(100,30);
				questionBox.setSize(100,30);
				text_point.setSize(150,30);
				bsave.setSize(100,30);
				bexit.setSize(100,30);
				//////////////////////////////////////////
				ds_id.setLocation(20,20);
				ds_question.setLocation(20,70);
				ds_ansnum.setLocation(20,320);
				ds_bingo.setLocation(20,370);
				ds_point.setLocation(20,420);
				text_dsid.setLocation(130,20);
				question_area.setLocation(130,70);
				sp1.setLocation(130,70);
				answerBox.setLocation(130,320);
				questionBox.setLocation(130,370);
				text_point.setLocation(130,420);
				bsave.setLocation(100,500);
				bexit.setLocation(300,500);
				//////////////////////////////////////////
				subcontainer.add(ds_id);
				subcontainer.add(ds_question);
				subcontainer.add(ds_ansnum);
				subcontainer.add(ds_bingo);
				subcontainer.add(ds_point);
				subcontainer.add(text_dsid);
				subcontainer.add(answerBox);
				subcontainer.add(questionBox);
				subcontainer.add(sp1);
				subcontainer.add(text_point);
				subcontainer.add(bsave);
				subcontainer.add(bexit);
				//////////////////////////////////////////
				//------------------------------------------
				subFrame.setPreferredSize(new Dimension(1000,600));
				subFrame.pack();
				desk1.add(subFrame);
				subFrame.setVisible(true);
				subFrame.addInternalFrameListener(new InternalFrameAdapter() {
					public void internalFrameClosing(InternalFrameEvent arg0) {
	                    typeconnectionServer();
	                    try {
							output = new ObjectOutputStream(client.getOutputStream());
							output.flush();
		                    input = new ObjectInputStream(client.getInputStream());
		                    question_Obj dxidreturn=new question_Obj("dx_idreturn",text_dsid.getText());
		                    output.writeObject(dxidreturn);
		                    output.flush();
		                    JOptionPane.showMessageDialog(null, "closing and return id successful");
						} catch (IOException e) {e.printStackTrace();}    
					}
				});
				bsave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String tmpquestion=question_area.getText().trim();
						int tmpnum=(int) answerBox.getSelectedItem();
						String tmpright=(String) questionBox.getSelectedItem();
						int tmppoint=Integer.parseInt(text_point.getText().trim());
						try {
							typeconnectionServer();
		                    output = new ObjectOutputStream(client.getOutputStream());
		                    output.flush();
		                    input = new ObjectInputStream(client.getInputStream());
		                    question_Obj dx1Dx_Obj=new question_Obj("dx_z",tmpdx_id,tmpquestion,tmpnum,tmpright,tmppoint,t1.getTc_name());
		                    output.writeObject(dx1Dx_Obj);
		                    output.flush();
		                    try {
								String tmpwar = (String) input.readObject();
								if(tmpwar.equals("successful")) {
									JOptionPane.showMessageDialog(null, "save successful");
									try {
					                    typeconnectionServer();
					                    output = new ObjectOutputStream(client.getOutputStream());
					                    output.flush();
					                    input = new ObjectInputStream(client.getInputStream());
					                    output.writeObject(new question_Obj("dx_id"));
					                    output.flush();
					                    String tmpS=(String)input.readObject();
					                    tmpdx_id=tmpS;
					                    text_dsid.setText(tmpdx_id);
					                    question_area.setText("");
					                    answerBox.setSelectedIndex(0);
					                    questionBox.setSelectedIndex(0);
					                    text_point.setText("");
					                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
								}
								else {
									JOptionPane.showMessageDialog(null, "save error");
								}
							} catch (ClassNotFoundException e) {e.printStackTrace();}
		                    
		                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
						
					}
				});
			}
		});
	    dx_s.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JInternalFrame subFrame=new JInternalFrame("删除选择题，出题人："+t1.getTc_name(),true,true,true,true);
				Container subcontainer=subFrame.getContentPane();
				subcontainer.setLayout(null);
				//----------------------------------------------------
				JLabel ds_delid=new JLabel("题号：");
				JLabel ds_delquestion=new JLabel("题干：");
				JTextField text_delid=new JTextField(30);
				JTextField text_delquestion=new JTextField(30);
				JTextArea text_del=new JTextArea(30,30);
				JScrollPane sp2=new JScrollPane(text_del);
				JButton bfind=new JButton("查找");
				JButton bdel=new JButton("删除");
				//////////////////////////////////////////////
				ds_delid.setFont(font2);
				ds_delquestion.setFont(font2);
				text_del.setFont(font2);
				text_delid.setFont(font2);
				text_delquestion.setFont(font2);
				bfind.setFont(font2);
				bdel.setFont(font2);
				//////////////////////////////////////////////
				ds_delid.setSize(100,30);
				ds_delquestion.setSize(100,30);
				text_del.setSize(450,300);
				text_delid.setSize(300,30);
				text_delquestion.setSize(300,30);
				sp2.setSize(800,300);
				bfind.setSize(100,30);
				bdel.setSize(100,30);
				//////////////////////////////////////////////
				ds_delid.setLocation(20,20);
				ds_delquestion.setLocation(20,70);
				text_del.setLocation(20,120);
				text_delid.setLocation(130,20);
				text_delquestion.setLocation(130,70);
				sp2.setLocation(20,120);
				bfind.setLocation(100,500);
				bdel.setLocation(300,500);
				//----------------------------------------------------
				subcontainer.add(ds_delid);
				subcontainer.add(ds_delquestion);
				subcontainer.add(sp2);
				subcontainer.add(text_delid);
				subcontainer.add(text_delquestion);
				subcontainer.add(bfind);
				subcontainer.add(bdel);
				subFrame.setPreferredSize(new Dimension(1000,600));
				subFrame.pack();
				desk1.add(subFrame);
				subFrame.setVisible(true);
				bfind.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String tmpid=text_delid.getText();
						try {
							typeconnectionServer();
		                    output = new ObjectOutputStream(client.getOutputStream());
		                    output.flush();
		                    input = new ObjectInputStream(client.getInputStream());
		                    question_Obj dx1Dx_Obj=new question_Obj("dx_c",tmpid,"",0,"",0);
		                    output.writeObject(dx1Dx_Obj);
		                    output.flush();
		                    try {
								question_Obj dx_g1 = (question_Obj) input.readObject();
								text_del.setText(dx_g1.getDxid()+"\t"+dx_g1.getDxquestion()+"\t"+dx_g1.getRight_answer()+"\t"+dx_g1.getPoints());
								
							} catch (ClassNotFoundException e) {e.printStackTrace();}
		                    
		                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);}
					}
				});
				bdel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String tmpid=text_delid.getText();
						if(text_del.getText().equals("")){
							JOptionPane.showMessageDialog(null, "没有读取到记录");
						}
						else{
							try {
								typeconnectionServer();
			                    output = new ObjectOutputStream(client.getOutputStream());
			                    output.flush();
			                    input = new ObjectInputStream(client.getInputStream());
			                    question_Obj dx1Dx_Obj=new question_Obj("dx_s",tmpid,"",0,"",0);
			                    output.writeObject(dx1Dx_Obj);
			                    output.flush();
			                    try {
									String tmpdel=(String)input.readObject();
									JOptionPane.showMessageDialog(null, "Delete "+tmpdel);
									text_del.setText("");
									text_delid.setText("");
									text_delquestion.setText("");
								} catch (ClassNotFoundException e) {e.printStackTrace();}
			                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);}
						}
					}
				});
			}
		});
	    dx_g.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JInternalFrame subFrame=new JInternalFrame("修改选择题，出题人："+t1.getTc_name(),true,true,true,true);
				Container subcontainer=subFrame.getContentPane();
				subcontainer.setLayout(null);
				//------------------------------------------
				JLabel ds_changeid=new JLabel("题号：");
				JLabel ds_changequestion=new JLabel("题干：");
				JLabel ds_changeansnum=new JLabel("选项数量：");
				JLabel ds_changebingo=new JLabel("正确答案：");
				JLabel ds_changepoint=new JLabel("得分：");
				JTextField text_changedsid=new JTextField(30);
				JTextArea question_changearea=new JTextArea(30,30);
				JScrollPane sp3=new JScrollPane(question_changearea);
				JComboBox changeanswerBox=new JComboBox();
				JComboBox changequestionBox=new JComboBox();
				JTextField text_changepoint=new JTextField(30);
				JButton bchangefind=new JButton("查找");
				JButton bchangesave=new JButton("保存");
				for(int i=2;i<7;i++) {changeanswerBox.addItem(i);}
				changeanswerBox.setSelectedIndex(2);
				changequestionBox.addItem("A");
				changequestionBox.addItem("B");
				changequestionBox.addItem("C");
				changequestionBox.addItem("D");
				changequestionBox.addItem("E");
				changequestionBox.addItem("F");
				changequestionBox.addItem("G");
				changequestionBox.setSelectedIndex(0);
				sp3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				//////////////////////////////////////////
				ds_changeid.setFont(font2);
				ds_changequestion.setFont(font2);
				ds_changeansnum.setFont(font2);
				ds_changebingo.setFont(font2);
				ds_changepoint.setFont(font2);
				text_changedsid.setFont(font2);
				question_changearea.setFont(font2);
				changeanswerBox.setFont(font2);
				changequestionBox.setFont(font2);
				bchangefind.setFont(font2);
				bchangesave.setFont(font2);
				text_changepoint.setFont(font2);
				//////////////////////////////////////////
				ds_changeid.setSize(100,30);
				ds_changequestion.setSize(100,30);
				ds_changebingo.setSize(100,30);
				ds_changeansnum.setSize(100,30);
				ds_changepoint.setSize(100,30);
				text_changedsid.setSize(300,30);
				question_changearea.setSize(300,230);
				sp3.setSize(800,230);
				changeanswerBox.setSize(100,30);
				changequestionBox.setSize(100,30);
				text_changepoint.setSize(150,30);
				bchangefind.setSize(100,30);
				bchangesave.setSize(100,30);
				//////////////////////////////////////////
				ds_changeid.setLocation(20,20);
				ds_changequestion.setLocation(20,70);
				ds_changeansnum.setLocation(20,320);
				ds_changebingo.setLocation(20,370);
				ds_changepoint.setLocation(20,420);
				text_changedsid.setLocation(130,20);
				question_changearea.setLocation(130,70);
				sp3.setLocation(130,70);
				changeanswerBox.setLocation(130,320);
				changequestionBox.setLocation(130,370);
				text_changepoint.setLocation(130,420);
				bchangefind.setLocation(100,500);
				bchangesave.setLocation(300,500);
				//////////////////////////////////////////
				subcontainer.add(ds_changeid);
				subcontainer.add(ds_changequestion);
				subcontainer.add(ds_changeansnum);
				subcontainer.add(ds_changebingo);
				subcontainer.add(ds_changepoint);
				subcontainer.add(text_changedsid);
				subcontainer.add(changeanswerBox);
				subcontainer.add(changequestionBox);
				subcontainer.add(sp3);
				subcontainer.add(text_changepoint);
				subcontainer.add(bchangefind);
				subcontainer.add(bchangesave);
				//////////////////////////////////////////
				//------------------------------------------
				subFrame.setPreferredSize(new Dimension(1000,600));
				subFrame.pack();
				desk1.add(subFrame);
				subFrame.setVisible(true);
				bchangefind.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String tmpid=text_changedsid.getText();
						try {
							typeconnectionServer();
		                    output = new ObjectOutputStream(client.getOutputStream());
		                    output.flush();
		                    input = new ObjectInputStream(client.getInputStream());
		                    question_Obj dx1Dx_Obj=new question_Obj("dx_g",tmpid,"",0,"",0);
		                    output.writeObject(dx1Dx_Obj);
		                    output.flush();
		                    try {
								question_Obj dx_g1 = (question_Obj) input.readObject();
								ds_changeid.setEnabled(false);
								question_changearea.setText(dx_g1.getDxquestion());
								changeanswerBox.setSelectedItem(dx_g1.getAnswer_num());
								changequestionBox.setSelectedItem(dx_g1.getRight_answer());
								text_changepoint.setText(String.valueOf(dx_g1.getPoints()));
							} catch (ClassNotFoundException e) {e.printStackTrace();}
		                    
		                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
					}
				});
				bchangesave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String tmpid=text_changedsid.getText();
						question_Obj xinti=new question_Obj("dx_gsave",tmpid,question_changearea.getText(),Integer.parseInt(changeanswerBox.getSelectedItem().toString()),String.valueOf(changequestionBox.getSelectedItem()),Integer.parseInt(text_changepoint.getText()),t1.getTc_name());
						try {
							typeconnectionServer();
		                    output = new ObjectOutputStream(client.getOutputStream());
		                    output.flush();
		                    input = new ObjectInputStream(client.getInputStream());
		                    output.writeObject(xinti);
		                    output.flush();
		                    try {
								String tmpwarning=(String)input.readObject();
								if(tmpwarning.equals("error")) {
									JOptionPane.showMessageDialog(null, "change Error");
								}
								else {
									JOptionPane.showMessageDialog(null, "change Successful");
								}
							} catch (ClassNotFoundException e) {e.printStackTrace();}
		                    
		                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
					}
				});
			}
		});
	    dx_c.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JInternalFrame subFrame=new JInternalFrame("查找选择题，出题人："+t1.getTc_name(),true,true,true,true);
				Container subcontainer=subFrame.getContentPane();
				subcontainer.setLayout(null);
				//----------------------------------------------------
				JLabel ds_findid=new JLabel("题号：");
				JLabel ds_findquestion=new JLabel("题干：");
				JTextField text_findid=new JTextField(30);
				JTextField text_findquestion=new JTextField(30);
				JTextArea text_find=new JTextArea(30,30);
				JScrollPane sp4=new JScrollPane(text_find);
				JButton bfind=new JButton("查找");
				JButton bfindexit=new JButton("关闭");
				//////////////////////////////////////////////
				ds_findid.setFont(font2);
				ds_findquestion.setFont(font2);
				text_find.setFont(font2);
				text_findid.setFont(font2);
				text_findquestion.setFont(font2);
				bfind.setFont(font2);
				bfindexit.setFont(font2);
				//////////////////////////////////////////////
				ds_findid.setSize(100,30);
				ds_findquestion.setSize(100,30);
				text_find.setSize(450,300);
				text_findid.setSize(300,30);
				text_findquestion.setSize(300,30);
				sp4.setSize(800,300);
				bfind.setSize(100,30);
				bfindexit.setSize(100,30);
				//////////////////////////////////////////////
				ds_findid.setLocation(20,20);
				ds_findquestion.setLocation(20,70);
				text_find.setLocation(20,120);
				text_findid.setLocation(130,20);
				text_findquestion.setLocation(130,70);
				sp4.setLocation(20,120);
				bfind.setLocation(100,500);
				bfindexit.setLocation(300,500);
				//----------------------------------------------------
				subcontainer.add(ds_findid);
				subcontainer.add(ds_findquestion);
				subcontainer.add(sp4);
				subcontainer.add(text_findid);
				subcontainer.add(text_findquestion);
				subcontainer.add(bfind);
				subcontainer.add(bfindexit);
				subFrame.setPreferredSize(new Dimension(1000,600));
				subFrame.pack();
				desk1.add(subFrame);
				subFrame.setVisible(true);
				bfind.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String tmpid=text_findid.getText();
						try {
							typeconnectionServer();
		                    output = new ObjectOutputStream(client.getOutputStream());
		                    output.flush();
		                    input = new ObjectInputStream(client.getInputStream());
		                    question_Obj dx1Dx_Obj=new question_Obj("dx_c",tmpid,"",0,"",0);
		                    output.writeObject(dx1Dx_Obj);
		                    output.flush();
		                    try {
								question_Obj dx_g1 = (question_Obj) input.readObject();
								text_find.setText(dx_g1.getDxid()+"\t"+dx_g1.getDxquestion()+"\t"+dx_g1.getRight_answer()+"\t"+dx_g1.getPoints());
							} catch (ClassNotFoundException e) {e.printStackTrace();}
		                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
						System.out.println("successful");
					}
				});
			}
		});

	    mx_z.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
                    typeconnectionServer();
                    output = new ObjectOutputStream(client.getOutputStream());
                    output.flush();
                    input = new ObjectInputStream(client.getInputStream());
                    output.writeObject(new question_Obj("mx_id"));
                    output.flush();
                    String tmpS=(String)input.readObject();
                    tmpdx_id=tmpS;
                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
				catch (ClassNotFoundException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);}
				JInternalFrame subFrame=new JInternalFrame("增加多选题，出题人："+t1.getTc_name(),true,true,true,true);
				Container subcontainer=subFrame.getContentPane();
				subcontainer.setLayout(null);
				//------------------------------------------
				JLabel mx_id=new JLabel("题号：");
				JLabel mx_question=new JLabel("题干：");
				JLabel mx_ansnum=new JLabel("选项数量：");
				JLabel mx_bingo=new JLabel("正确答案：");
				JLabel mx_point=new JLabel("得分：");
				JTextField text_mxid=new JTextField(30);
				JTextArea question_area=new JTextArea(30,30);
				JScrollPane sp1=new JScrollPane(question_area);
				JComboBox answerBox=new JComboBox();
				JTextField questionBox=new JTextField();
				JTextField text_point=new JTextField(30);
				JButton bsave=new JButton("保存");
				JButton bexit=new JButton("关闭");
				for(int i=2;i<7;i++) {answerBox.addItem(i);}
				answerBox.setSelectedIndex(2);
				sp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				text_mxid.setEditable(false);
				text_mxid.setText(tmpdx_id);
				//////////////////////////////////////////
				mx_id.setFont(font2);
				mx_question.setFont(font2);
				mx_ansnum.setFont(font2);
				mx_bingo.setFont(font2);
				mx_point.setFont(font2);
				text_mxid.setFont(font2);
				question_area.setFont(font2);
				answerBox.setFont(font2);
				questionBox.setFont(font2);
				bsave.setFont(font2);
				bexit.setFont(font2);
				text_point.setFont(font2);
				//////////////////////////////////////////
				mx_id.setSize(100,30);
				mx_question.setSize(100,30);
				mx_bingo.setSize(100,30);
				mx_ansnum.setSize(100,30);
				mx_point.setSize(100,30);
				text_mxid.setSize(300,30);
				question_area.setSize(300,230);
				sp1.setSize(800,230);
				answerBox.setSize(100,30);
				questionBox.setSize(100,30);
				text_point.setSize(150,30);
				bsave.setSize(100,30);
				bexit.setSize(100,30);
				//////////////////////////////////////////
				mx_id.setLocation(20,20);
				mx_question.setLocation(20,70);
				mx_ansnum.setLocation(20,320);
				mx_bingo.setLocation(20,370);
				mx_point.setLocation(20,420);
				text_mxid.setLocation(130,20);
				question_area.setLocation(130,70);
				sp1.setLocation(130,70);
				answerBox.setLocation(130,320);
				questionBox.setLocation(130,370);
				text_point.setLocation(130,420);
				bsave.setLocation(100,500);
				bexit.setLocation(300,500);
				//////////////////////////////////////////
				subcontainer.add(mx_id);
				subcontainer.add(mx_question);
				subcontainer.add(mx_ansnum);
				subcontainer.add(mx_bingo);
				subcontainer.add(mx_point);
				subcontainer.add(text_mxid);
				subcontainer.add(answerBox);
				subcontainer.add(questionBox);
				subcontainer.add(sp1);
				subcontainer.add(text_point);
				subcontainer.add(bsave);
				subcontainer.add(bexit);
				//////////////////////////////////////////
				//------------------------------------------
				subFrame.setPreferredSize(new Dimension(1000,600));
				subFrame.pack();
				desk1.add(subFrame);
				subFrame.setVisible(true);
				subFrame.addInternalFrameListener(new InternalFrameAdapter() {
					public void internalFrameClosing(InternalFrameEvent arg0) {
	                    typeconnectionServer();
	                    try {
							output = new ObjectOutputStream(client.getOutputStream());
							output.flush();
		                    input = new ObjectInputStream(client.getInputStream());
		                    question_Obj dxidreturn=new question_Obj("mx_idreturn",text_mxid.getText());
		                    output.writeObject(dxidreturn);
		                    output.flush();
		                    JOptionPane.showMessageDialog(null, "closing and return id successful");
						} catch (IOException e) {e.printStackTrace();}    
					}
				});
				bsave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String tmpquestion=question_area.getText().trim();
						int tmpnum=(int) answerBox.getSelectedItem();
						String tmpright=(String) questionBox.getText();
						int tmppoint=Integer.parseInt(text_point.getText().trim());
						try {
							typeconnectionServer();
		                    output = new ObjectOutputStream(client.getOutputStream());
		                    output.flush();
		                    input = new ObjectInputStream(client.getInputStream());
		                    question_Obj dx1Dx_Obj=new question_Obj("mx_z",tmpdx_id,tmpquestion,tmpnum,tmpright,tmppoint,t1.getTc_name());
		                    output.writeObject(dx1Dx_Obj);
		                    output.flush();
		                    try {
								String tmpwar = (String) input.readObject();
								if(tmpwar.equals("successful")) {
									JOptionPane.showMessageDialog(null, "save successful");
									try {
					                    typeconnectionServer();
					                    output = new ObjectOutputStream(client.getOutputStream());
					                    output.flush();
					                    input = new ObjectInputStream(client.getInputStream());
					                    output.writeObject(new question_Obj("mx_id"));
					                    output.flush();
					                    String tmpS=(String)input.readObject();
					                    tmpdx_id=tmpS;
					                    text_mxid.setText(tmpdx_id);
					                    question_area.setText("");
					                    answerBox.setSelectedIndex(0);
					                    questionBox.setText("");
					                    text_point.setText("");
					                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
								}
								else {
									JOptionPane.showMessageDialog(null, "save error");
								}
							} catch (ClassNotFoundException e) {e.printStackTrace();}
		                    
		                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
						
					}
				});
			}
		});
	    mx_s.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JInternalFrame subFrame=new JInternalFrame("删除多选题，出题人："+t1.getTc_name(),true,true,true,true);
				Container subcontainer=subFrame.getContentPane();
				subcontainer.setLayout(null);
				//----------------------------------------------------
				JLabel mx_delid=new JLabel("题号：");
				JLabel mx_delquestion=new JLabel("题干：");
				JTextField text_delid=new JTextField(30);
				JTextField text_delquestion=new JTextField(30);
				JTextArea text_del=new JTextArea(30,30);
				JScrollPane sp2=new JScrollPane(text_del);
				JButton bdelfind=new JButton("查找");
				JButton bdeldel=new JButton("删除");
				//////////////////////////////////////////////
				mx_delid.setFont(font2);
				mx_delquestion.setFont(font2);
				text_del.setFont(font2);
				text_delid.setFont(font2);
				text_delquestion.setFont(font2);
				bdelfind.setFont(font2);
				bdeldel.setFont(font2);
				//////////////////////////////////////////////
				mx_delid.setSize(100,30);
				mx_delquestion.setSize(100,30);
				text_del.setSize(450,300);
				text_delid.setSize(300,30);
				text_delquestion.setSize(300,30);
				sp2.setSize(800,300);
				bdelfind.setSize(100,30);
				bdeldel.setSize(100,30);
				//////////////////////////////////////////////
				mx_delid.setLocation(20,20);
				mx_delquestion.setLocation(20,70);
				text_del.setLocation(20,120);
				text_delid.setLocation(130,20);
				text_delquestion.setLocation(130,70);
				sp2.setLocation(20,120);
				bdelfind.setLocation(100,500);
				bdeldel.setLocation(300,500);
				//----------------------------------------------------
				subcontainer.add(mx_delid);
				subcontainer.add(mx_delquestion);
				subcontainer.add(sp2);
				subcontainer.add(text_delid);
				subcontainer.add(text_delquestion);
				subcontainer.add(bdelfind);
				subcontainer.add(bdeldel);
				subFrame.setPreferredSize(new Dimension(1000,600));
				subFrame.pack();
				desk1.add(subFrame);
				subFrame.setVisible(true);
				bdelfind.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String tmpid=text_delid.getText();
						try {
							typeconnectionServer();
		                    output = new ObjectOutputStream(client.getOutputStream());
		                    output.flush();
		                    input = new ObjectInputStream(client.getInputStream());
		                    question_Obj dx1Dx_Obj=new question_Obj("mx_c",tmpid,"",0,"",0);
		                    output.writeObject(dx1Dx_Obj);
		                    output.flush();
		                    try {
								question_Obj dx_g1 = (question_Obj) input.readObject();
								text_del.setText(dx_g1.getDxid()+"\t"+dx_g1.getDxquestion()+"\t"+dx_g1.getRight_answer()+"\t"+dx_g1.getPoints()+"\t"+dx_g1.getTeacher());
							} catch (ClassNotFoundException e) {e.printStackTrace();}
		                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
						System.out.println("successful");
					}
				});
				bdeldel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String tmpid=text_delid.getText();
						if(text_del.getText().equals("")){
							JOptionPane.showMessageDialog(null, "没有读取到记录");
						}
						else{
							try {
								typeconnectionServer();
			                    output = new ObjectOutputStream(client.getOutputStream());
			                    output.flush();
			                    input = new ObjectInputStream(client.getInputStream());
			                    question_Obj dx1Dx_Obj=new question_Obj("mx_s",tmpid,"",0,"",0);
			                    output.writeObject(dx1Dx_Obj);
			                    output.flush();
			                    try {
									String tmpdel=(String)input.readObject();
									JOptionPane.showMessageDialog(null, "Delete "+tmpdel);
									text_del.setText("");
									text_delid.setText("");
									text_delquestion.setText("");
								} catch (ClassNotFoundException e) {e.printStackTrace();}
			                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);}
						}
					}
				});
			}
		});
	    mx_g.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JInternalFrame subFrame=new JInternalFrame("修改多选题，出题人："+t1.getTc_name(),true,true,true,true);
				Container subcontainer=subFrame.getContentPane();
				subcontainer.setLayout(null);
				//------------------------------------------
				JLabel mx_changeid=new JLabel("题号：");
				JLabel mx_changequestion=new JLabel("题干：");
				JLabel mx_changeansnum=new JLabel("选项数量：");
				JLabel mx_changebingo=new JLabel("正确答案：");
				JLabel mx_changepoint=new JLabel("得分：");
				JTextField text_changemxid=new JTextField(30);
				JTextArea question_changearea=new JTextArea(30,30);
				JScrollPane sp3=new JScrollPane(question_changearea);
				JComboBox changeanswerBox=new JComboBox();
				JTextField changequestionBox=new JTextField();
				JTextField text_changepoint=new JTextField(30);
				JButton bchangefind=new JButton("查找");
				JButton bchangesave=new JButton("保存");
				for(int i=2;i<7;i++) {changeanswerBox.addItem(i);}
				changeanswerBox.setSelectedIndex(2);
				sp3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				//////////////////////////////////////////
				mx_changeid.setFont(font2);
				mx_changequestion.setFont(font2);
				mx_changeansnum.setFont(font2);
				mx_changebingo.setFont(font2);
				mx_changepoint.setFont(font2);
				text_changemxid.setFont(font2);
				question_changearea.setFont(font2);
				changeanswerBox.setFont(font2);
				changequestionBox.setFont(font2);
				bchangefind.setFont(font2);
				bchangesave.setFont(font2);
				text_changepoint.setFont(font2);
				//////////////////////////////////////////
				mx_changeid.setSize(100,30);
				mx_changequestion.setSize(100,30);
				mx_changebingo.setSize(100,30);
				mx_changeansnum.setSize(100,30);
				mx_changepoint.setSize(100,30);
				text_changemxid.setSize(300,30);
				question_changearea.setSize(300,230);
				sp3.setSize(800,230);
				changeanswerBox.setSize(100,30);
				changequestionBox.setSize(100,30);
				text_changepoint.setSize(150,30);
				bchangefind.setSize(100,30);
				bchangesave.setSize(100,30);
				//////////////////////////////////////////
				mx_changeid.setLocation(20,20);
				mx_changequestion.setLocation(20,70);
				mx_changeansnum.setLocation(20,320);
				mx_changebingo.setLocation(20,370);
				mx_changepoint.setLocation(20,420);
				text_changemxid.setLocation(130,20);
				question_changearea.setLocation(130,70);
				sp3.setLocation(130,70);
				changeanswerBox.setLocation(130,320);
				changequestionBox.setLocation(130,370);
				text_changepoint.setLocation(130,420);
				bchangefind.setLocation(100,500);
				bchangesave.setLocation(300,500);
				//////////////////////////////////////////
				subcontainer.add(mx_changeid);
				subcontainer.add(mx_changequestion);
				subcontainer.add(mx_changeansnum);
				subcontainer.add(mx_changebingo);
				subcontainer.add(mx_changepoint);
				subcontainer.add(text_changemxid);
				subcontainer.add(changeanswerBox);
				subcontainer.add(changequestionBox);
				subcontainer.add(sp3);
				subcontainer.add(text_changepoint);
				subcontainer.add(bchangefind);
				subcontainer.add(bchangesave);
				//////////////////////////////////////////
				//------------------------------------------
				subFrame.setPreferredSize(new Dimension(1000,600));
				subFrame.pack();
				desk1.add(subFrame);
				subFrame.setVisible(true);
				bchangefind.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String tmpid=text_changemxid.getText();
						try {
							typeconnectionServer();
		                    output = new ObjectOutputStream(client.getOutputStream());
		                    output.flush();
		                    input = new ObjectInputStream(client.getInputStream());
		                    question_Obj dx1Dx_Obj=new question_Obj("mx_c",tmpid,"",0,"",0);
		                    output.writeObject(dx1Dx_Obj);
		                    output.flush();
		                    try {
								question_Obj dx_g1 = (question_Obj) input.readObject();
								text_changemxid.setText(tmpid);
								question_changearea.setText(dx_g1.getDxquestion());
								changeanswerBox.setSelectedItem(dx_g1.getAnswer_num());
								changequestionBox.setText(dx_g1.getRight_answer());
								text_changepoint.setText(String.valueOf(dx_g1.getPoints()));
							} catch (ClassNotFoundException e) {e.printStackTrace();}
		                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
						System.out.println("successful");
					}
				});
				bchangesave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String tmpid=text_changemxid.getText();
						question_Obj xinti=new question_Obj("mx_gsave",tmpid,question_changearea.getText(),Integer.parseInt(changeanswerBox.getSelectedItem().toString()),changequestionBox.getText(),Integer.parseInt(text_changepoint.getText()),t1.getTc_name());
						try {
							typeconnectionServer();
		                    output = new ObjectOutputStream(client.getOutputStream());
		                    output.flush();
		                    input = new ObjectInputStream(client.getInputStream());
		                    output.writeObject(xinti);
		                    output.flush();
		                    try {
								String tmpwarning=(String)input.readObject();
								if(tmpwarning.equals("error")) {
									JOptionPane.showMessageDialog(null, "change Error");
								}
								else {
									JOptionPane.showMessageDialog(null, "change Successful");
								}
							} catch (ClassNotFoundException e) {e.printStackTrace();}
		                    
		                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
					}
				});
			}
		});
	    mx_c.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JInternalFrame subFrame=new JInternalFrame("查找多选题，出题人："+t1.getTc_name(),true,true,true,true);
				Container subcontainer=subFrame.getContentPane();
				subcontainer.setLayout(null);
				//----------------------------------------------------
				JLabel mx_findid=new JLabel("题号：");
				JLabel mx_findquestion=new JLabel("题干：");
				JTextField text_findid=new JTextField(30);
				JTextField text_findquestion=new JTextField(30);
				JTextArea text_find=new JTextArea(30,30);
				JScrollPane sp4=new JScrollPane(text_find);
				JButton bfind=new JButton("查找");
				JButton bfindexit=new JButton("关闭");
				//////////////////////////////////////////////
				mx_findid.setFont(font2);
				mx_findquestion.setFont(font2);
				text_find.setFont(font2);
				text_findid.setFont(font2);
				text_findquestion.setFont(font2);
				bfind.setFont(font2);
				bfindexit.setFont(font2);
				//////////////////////////////////////////////
				mx_findid.setSize(100,30);
				mx_findquestion.setSize(100,30);
				text_find.setSize(450,300);
				text_findid.setSize(300,30);
				text_findquestion.setSize(300,30);
				sp4.setSize(800,300);
				bfind.setSize(100,30);
				bfindexit.setSize(100,30);
				//////////////////////////////////////////////
				mx_findid.setLocation(20,20);
				mx_findquestion.setLocation(20,70);
				text_find.setLocation(20,120);
				text_findid.setLocation(130,20);
				text_findquestion.setLocation(130,70);
				sp4.setLocation(20,120);
				bfind.setLocation(100,500);
				bfindexit.setLocation(300,500);
				//----------------------------------------------------
				subcontainer.add(mx_findid);
				subcontainer.add(mx_findquestion);
				subcontainer.add(sp4);
				subcontainer.add(text_findid);
				subcontainer.add(text_findquestion);
				subcontainer.add(bfind);
				subcontainer.add(bfindexit);
				subFrame.setPreferredSize(new Dimension(1000,600));
				subFrame.pack();
				desk1.add(subFrame);
				subFrame.setVisible(true);
				bfind.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String tmpid=text_findid.getText();
						try {
							typeconnectionServer();
		                    output = new ObjectOutputStream(client.getOutputStream());
		                    output.flush();
		                    input = new ObjectInputStream(client.getInputStream());
		                    question_Obj dx1Dx_Obj=new question_Obj("mx_c",tmpid,"",0,"",0);
		                    output.writeObject(dx1Dx_Obj);
		                    output.flush();
		                    try {
								question_Obj dx_g1 = (question_Obj) input.readObject();
								text_find.setText(dx_g1.getDxid()+"\t"+dx_g1.getDxquestion()+"\t"+dx_g1.getRight_answer()+"\t"+dx_g1.getPoints()+"\t"+dx_g1.getTeacher());
							} catch (ClassNotFoundException e) {e.printStackTrace();}
		                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
						System.out.println("successful");
					}
				});
			}
		});
	    
		tk_z.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
                    typeconnectionServer();
                    output = new ObjectOutputStream(client.getOutputStream());
                    output.flush();
                    input = new ObjectInputStream(client.getInputStream());
                    output.writeObject(new question_Obj("tk_id"));
                    output.flush();
                    String tmpS=(String)input.readObject();
                    tmpdx_id=tmpS;
                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
				catch (ClassNotFoundException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
				JInternalFrame subFrame=new JInternalFrame("增加填空题，出题人："+t1.getTc_name(),true,true,true,true);
				Container subcontainer=subFrame.getContentPane();
				subcontainer.setLayout(null);
				subFrame.setLocation(100,20);
				//------------------------------------------
				JLabel tk_id=new JLabel("题号：");
				JLabel tk_question=new JLabel("题干：");
				JLabel tk_ansnum=new JLabel("选项数量：");
				JLabel tk_bingo=new JLabel("正确答案：");
				JLabel tk_point=new JLabel("得分：");
				JLabel tk_a1=new JLabel("答案1:");
				JLabel tk_a2=new JLabel("答案2:");
				JLabel tk_a3=new JLabel("答案3:");
				JLabel tk_a4=new JLabel("答案4:");
				JLabel tk_a5=new JLabel("答案5:");
				JCheckBox tk_order=new JCheckBox("是否有序");
				JTextField text_tkid=new JTextField(30);
				JTextArea question_area=new JTextArea(30,30);
				JScrollPane sp1=new JScrollPane(question_area);
				JComboBox answerBox=new JComboBox();
				JTextField a1=new JTextField();
				JTextField a2=new JTextField();
				JTextField a3=new JTextField();
				JTextField a4=new JTextField();
				JTextField a5=new JTextField();
				JTextField text_point=new JTextField(30);
				JButton bsave=new JButton("保存");
				JButton bexit=new JButton("关闭");
				for(int i=1;i<6;i++) {answerBox.addItem(i);}
				answerBox.setSelectedIndex(0);
				sp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				text_tkid.setEditable(false);
				text_tkid.setText(tmpdx_id);
				a2.setEditable(false);
				a3.setEditable(false);
				a4.setEditable(false);
				a5.setEditable(false);
				answerBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(Integer.parseInt(answerBox.getSelectedItem().toString())==1) {
							a1.setEditable(true);
							a2.setEditable(false);
							a3.setEditable(false);
							a4.setEditable(false);
							a5.setEditable(false);
							a2.setText("");
							a3.setText("");
							a4.setText("");
							a5.setText("");
						}
						if(Integer.parseInt(answerBox.getSelectedItem().toString())==2) {
							a1.setEditable(true);
							a2.setEditable(true);
							a3.setEditable(false);
							a4.setEditable(false);
							a5.setEditable(false);
							a3.setText("");
							a4.setText("");
							a5.setText("");
						}
						if(Integer.parseInt(answerBox.getSelectedItem().toString())==3) {
							a1.setEditable(true);
							a2.setEditable(true);
							a3.setEditable(true);
							a4.setEditable(false);
							a5.setEditable(false);
							a4.setText("");
							a5.setText("");
						}
						if(Integer.parseInt(answerBox.getSelectedItem().toString())==4) {
							a1.setEditable(true);
							a2.setEditable(true);
							a3.setEditable(true);
							a4.setEditable(true);
							a5.setEditable(false);
							a5.setText("");
						}
						if(Integer.parseInt(answerBox.getSelectedItem().toString())==5) {
							a1.setEditable(true);
							a2.setEditable(true);
							a3.setEditable(true);
							a4.setEditable(true);
							a5.setEditable(true);
						}
					}
				});
				//////////////////////////////////////////
				tk_id.setFont(font2);
				tk_question.setFont(font2);
				tk_ansnum.setFont(font2);
				tk_bingo.setFont(font2);
				tk_point.setFont(font2);
				text_tkid.setFont(font2);
				question_area.setFont(font2);
				answerBox.setFont(font2);
				a1.setFont(font2);
				a2.setFont(font2);
				a3.setFont(font2);
				a4.setFont(font2);
				a5.setFont(font2);
				bsave.setFont(font2);
				bexit.setFont(font2);
				tk_order.setFont(font2);
				tk_a1.setFont(font2);
				tk_a2.setFont(font2);
				tk_a3.setFont(font2);
				tk_a4.setFont(font2);
				tk_a5.setFont(font2);
				text_point.setFont(font2);
				//////////////////////////////////////////
				tk_id.setSize(100,30);
				tk_question.setSize(100,30);
				tk_bingo.setSize(100,30);
				tk_ansnum.setSize(100,30);
				tk_point.setSize(100,30);
				text_tkid.setSize(300,30);
				question_area.setSize(700,230);
				sp1.setSize(700,230);
				answerBox.setSize(100,30);
				a1.setSize(300,30);
				a2.setSize(300,30);
				a3.setSize(300,30);
				a4.setSize(300,30);
				a5.setSize(300,30);
				text_point.setSize(150,30);
				bsave.setSize(100,30);
				bexit.setSize(100,30);
				tk_order.setSize(150,30);
				tk_a1.setSize(100,30);
				tk_a2.setSize(100,30);
				tk_a3.setSize(100,30);
				tk_a4.setSize(100,30);
				tk_a5.setSize(100,30);
				//////////////////////////////////////////
				tk_id.setLocation(20,20);
				tk_question.setLocation(20,70);
				tk_ansnum.setLocation(20,320);
				tk_bingo.setLocation(900,20);
				tk_point.setLocation(20,420);
				text_tkid.setLocation(130,20);
				question_area.setLocation(130,70);
				sp1.setLocation(130,70);
				answerBox.setLocation(130,320);
				a1.setLocation(970,70);
				a2.setLocation(970,120);
				a3.setLocation(970,170);
				a4.setLocation(970,220);
				a5.setLocation(970,270);
				text_point.setLocation(130,420);
				bsave.setLocation(100,500);
				bexit.setLocation(300,500);
				tk_order.setLocation(20,370);
				tk_a1.setLocation(900,70);
				tk_a2.setLocation(900,120);
				tk_a3.setLocation(900,170);
				tk_a4.setLocation(900,220);
				tk_a5.setLocation(900,270);
				//////////////////////////////////////////
				subcontainer.add(tk_id);
				subcontainer.add(tk_question);
				subcontainer.add(tk_ansnum);
				subcontainer.add(tk_bingo);
				subcontainer.add(tk_point);
				subcontainer.add(text_tkid);
				subcontainer.add(answerBox);
				subcontainer.add(a1);
				subcontainer.add(a2);
				subcontainer.add(a3);
				subcontainer.add(a4);
				subcontainer.add(a5);
				subcontainer.add(sp1);
				subcontainer.add(text_point);
				subcontainer.add(bsave);
				subcontainer.add(bexit);
				subcontainer.add(tk_order);
				subcontainer.add(tk_a1);
				subcontainer.add(tk_a2);
				subcontainer.add(tk_a3);
				subcontainer.add(tk_a4);
				subcontainer.add(tk_a5);
				//////////////////////////////////////////
				//------------------------------------------
				subFrame.setPreferredSize(new Dimension(1300,600));
				subFrame.pack();
				desk1.add(subFrame);
				subFrame.setVisible(true);
				subFrame.addInternalFrameListener(new InternalFrameAdapter() {
					public void internalFrameClosing(InternalFrameEvent arg0) {
	                    typeconnectionServer();
	                    try {
							output = new ObjectOutputStream(client.getOutputStream());
							output.flush();
		                    input = new ObjectInputStream(client.getInputStream());
		                    question_Obj dxidreturn=new question_Obj("tk_idreturn",text_tkid.getText());
		                    output.writeObject(dxidreturn);
		                    output.flush();
		                    JOptionPane.showMessageDialog(null, "closing and return id successful");
						} catch (IOException e) {e.printStackTrace();}    
					}
				});
				bsave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String tmpquestion=question_area.getText().trim();
						int tmpnum=(int) answerBox.getSelectedItem();
						int tmppoint=Integer.parseInt(text_point.getText().trim());
						try {
							typeconnectionServer();
		                    output = new ObjectOutputStream(client.getOutputStream());
		                    output.flush();
		                    input = new ObjectInputStream(client.getInputStream());
		                    question_Obj dx1Dx_Obj=new question_Obj("tk_z",text_tkid.getText(),tmpquestion,Integer.parseInt(answerBox.getSelectedItem().toString()),String.valueOf(tk_order.isSelected()),a1.getText(),a2.getText(),a3.getText(),a4.getText(),a5.getText(),tmppoint,t1.getTc_name());
		                    output.writeObject(dx1Dx_Obj);
		                    output.flush();
		                    try {
								String tmpwar = (String) input.readObject();
								if(tmpwar.equals("successful")) {
									JOptionPane.showMessageDialog(null, "save successful");
									try {
					                    typeconnectionServer();
					                    output = new ObjectOutputStream(client.getOutputStream());
					                    output.flush();
					                    input = new ObjectInputStream(client.getInputStream());
					                    output.writeObject(new question_Obj("tk_id"));
					                    output.flush();
					                    String tmpS=(String)input.readObject();
					                    tmpdx_id=tmpS;
					                    text_tkid.setText(tmpdx_id);
					                    question_area.setText("");
					                    answerBox.setSelectedIndex(0);
					                    a1.setText("");
					                    a2.setText("");
					                    a3.setText("");
					                    a4.setText("");
					                    a5.setText("");
					                    tk_order.setSelected(false);
					                    text_point.setText("");
					                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
								}
								else {
									JOptionPane.showMessageDialog(null, "save error");
								}
							} catch (ClassNotFoundException e) {e.printStackTrace();}
		                    
		                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
						
					}
				});
			}
		});
		tk_s.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JInternalFrame subFrame=new JInternalFrame("删除填空题，出题人："+t1.getTc_name(),true,true,true,true);
				Container subcontainer=subFrame.getContentPane();
				subcontainer.setLayout(null);
				//----------------------------------------------------
				JLabel tk_delid=new JLabel("题号：");
				JLabel tk_delquestion=new JLabel("题干：");
				JTextField text_delid=new JTextField(30);
				JTextField text_delquestion=new JTextField(30);
				JTextArea text_del=new JTextArea(30,30);
				JScrollPane sp2=new JScrollPane(text_del);
				JButton bdelfind=new JButton("查找");
				JButton bdelsave=new JButton("删除");
				//////////////////////////////////////////////
				tk_delid.setFont(font2);
				tk_delquestion.setFont(font2);
				text_del.setFont(font2);
				text_delid.setFont(font2);
				text_delquestion.setFont(font2);
				bdelfind.setFont(font2);
				bdelsave.setFont(font2);
				//////////////////////////////////////////////
				tk_delid.setSize(100,30);
				tk_delquestion.setSize(100,30);
				text_del.setSize(450,300);
				text_delid.setSize(300,30);
				text_delquestion.setSize(300,30);
				sp2.setSize(800,300);
				bdelfind.setSize(100,30);
				bdelsave.setSize(100,30);
				//////////////////////////////////////////////
				tk_delid.setLocation(20,20);
				tk_delquestion.setLocation(20,70);
				text_del.setLocation(20,120);
				text_delid.setLocation(130,20);
				text_delquestion.setLocation(130,70);
				sp2.setLocation(20,120);
				bdelfind.setLocation(100,500);
				bdelsave.setLocation(300,500);
				//----------------------------------------------------
				subcontainer.add(tk_delid);
				subcontainer.add(tk_delquestion);
				subcontainer.add(sp2);
				subcontainer.add(text_delid);
				subcontainer.add(text_delquestion);
				subcontainer.add(bdelfind);
				subcontainer.add(bdelsave);
				subFrame.setPreferredSize(new Dimension(1000,600));
				subFrame.pack();
				desk1.add(subFrame);
				subFrame.setVisible(true);
				bdelfind.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String tmpid=text_delid.getText();
						try {
							typeconnectionServer();
		                    output = new ObjectOutputStream(client.getOutputStream());
		                    output.flush();
		                    input = new ObjectInputStream(client.getInputStream());
		                    question_Obj dx1Dx_Obj=new question_Obj("tk_c",tmpid,"",0,"",0);
		                    output.writeObject(dx1Dx_Obj);
		                    output.flush();
		                    try {
								question_Obj dx_g1 = (question_Obj) input.readObject();
								text_del.setText(dx_g1.getDxid()+"\t"+dx_g1.getDxquestion()+"\t"+dx_g1.getAnswer_num()+"\t"+dx_g1.getTkorder()+"\t"+dx_g1.getTka1()+"\t"+dx_g1.getTka2()+"\t"+dx_g1.getTka3()+"\t"+dx_g1.getTka4()+"\t"+dx_g1.getTka5()+"\t"+dx_g1.getPoints()+"\t"+dx_g1.getTeacher());
							} catch (ClassNotFoundException e) {e.printStackTrace();}
		                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
						System.out.println("successful");
					}
				});
				bdelsave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String tmpid=text_delid.getText();
						if(text_del.getText().equals("")){
							JOptionPane.showMessageDialog(null, "没有读取到记录");
						}
						else{
							try {
								typeconnectionServer();
			                    output = new ObjectOutputStream(client.getOutputStream());
			                    output.flush();
			                    input = new ObjectInputStream(client.getInputStream());
			                    question_Obj dx1Dx_Obj=new question_Obj("tk_s",tmpid,"",0,"",0);
			                    output.writeObject(dx1Dx_Obj);
			                    output.flush();
			                    try {
									String tmpdel=(String)input.readObject();
									JOptionPane.showMessageDialog(null, "Delete "+tmpdel);
									text_del.setText("");
									text_delid.setText("");
									text_delquestion.setText("");
								} catch (ClassNotFoundException e) {e.printStackTrace();}
			                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);}
						}
					}
				});
			}
		});
		tk_g.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JInternalFrame subFrame=new JInternalFrame("增加填空题，出题人："+t1.getTc_name(),true,true,true,true);
				Container subcontainer=subFrame.getContentPane();
				subcontainer.setLayout(null);
				//------------------------------------------
				JLabel tk_id=new JLabel("题号：");
				JLabel tk_question=new JLabel("题干：");
				JLabel tk_ansnum=new JLabel("选项数量：");
				JLabel tk_bingo=new JLabel("正确答案：");
				JLabel tk_point=new JLabel("得分：");
				JLabel tk_a1=new JLabel("答案1:");
				JLabel tk_a2=new JLabel("答案2:");
				JLabel tk_a3=new JLabel("答案3:");
				JLabel tk_a4=new JLabel("答案4:");
				JLabel tk_a5=new JLabel("答案5:");
				JCheckBox tk_order=new JCheckBox("是否有序");
				JTextField text_tkid=new JTextField(30);
				JTextArea question_area=new JTextArea(30,30);
				JScrollPane sp1=new JScrollPane(question_area);
				JComboBox answerBox=new JComboBox();
				JTextField a1=new JTextField();
				JTextField a2=new JTextField();
				JTextField a3=new JTextField();
				JTextField a4=new JTextField();
				JTextField a5=new JTextField();
				JTextField text_point=new JTextField(30);
				JButton bfind=new JButton("查找");
				JButton bchange=new JButton("修改");
				for(int i=1;i<6;i++) {answerBox.addItem(i);}
				answerBox.setSelectedIndex(0);
				sp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				a2.setEditable(false);
				a3.setEditable(false);
				a4.setEditable(false);
				a5.setEditable(false);
				answerBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(Integer.parseInt(answerBox.getSelectedItem().toString())==1) {
							a1.setEditable(true);
							a2.setEditable(false);
							a3.setEditable(false);
							a4.setEditable(false);
							a5.setEditable(false);
							a2.setText("");
							a3.setText("");
							a4.setText("");
							a5.setText("");
						}
						if(Integer.parseInt(answerBox.getSelectedItem().toString())==2) {
							a1.setEditable(true);
							a2.setEditable(true);
							a3.setEditable(false);
							a4.setEditable(false);
							a5.setEditable(false);
							a3.setText("");
							a4.setText("");
							a5.setText("");
						}
						if(Integer.parseInt(answerBox.getSelectedItem().toString())==3) {
							a1.setEditable(true);
							a2.setEditable(true);
							a3.setEditable(true);
							a4.setEditable(false);
							a5.setEditable(false);
							a4.setText("");
							a5.setText("");
						}
						if(Integer.parseInt(answerBox.getSelectedItem().toString())==4) {
							a1.setEditable(true);
							a2.setEditable(true);
							a3.setEditable(true);
							a4.setEditable(true);
							a5.setEditable(false);
							a5.setText("");
						}
						if(Integer.parseInt(answerBox.getSelectedItem().toString())==5) {
							a1.setEditable(true);
							a2.setEditable(true);
							a3.setEditable(true);
							a4.setEditable(true);
							a5.setEditable(true);
						}
					}
				});
				//////////////////////////////////////////
				tk_id.setFont(font2);
				tk_question.setFont(font2);
				tk_ansnum.setFont(font2);
				tk_bingo.setFont(font2);
				tk_point.setFont(font2);
				text_tkid.setFont(font2);
				question_area.setFont(font2);
				answerBox.setFont(font2);
				a1.setFont(font2);
				a2.setFont(font2);
				a3.setFont(font2);
				a4.setFont(font2);
				a5.setFont(font2);
				bfind.setFont(font2);
				bchange.setFont(font2);
				tk_order.setFont(font2);
				tk_a1.setFont(font2);
				tk_a2.setFont(font2);
				tk_a3.setFont(font2);
				tk_a4.setFont(font2);
				tk_a5.setFont(font2);
				text_point.setFont(font2);
				//////////////////////////////////////////
				tk_id.setSize(100,30);
				tk_question.setSize(100,30);
				tk_bingo.setSize(100,30);
				tk_ansnum.setSize(100,30);
				tk_point.setSize(100,30);
				text_tkid.setSize(300,30);
				question_area.setSize(700,230);
				sp1.setSize(700,230);
				answerBox.setSize(100,30);
				a1.setSize(300,30);
				a2.setSize(300,30);
				a3.setSize(300,30);
				a4.setSize(300,30);
				a5.setSize(300,30);
				text_point.setSize(150,30);
				bchange.setSize(100,30);
				bfind.setSize(100,30);
				tk_order.setSize(150,30);
				tk_a1.setSize(100,30);
				tk_a2.setSize(100,30);
				tk_a3.setSize(100,30);
				tk_a4.setSize(100,30);
				tk_a5.setSize(100,30);
				//////////////////////////////////////////
				tk_id.setLocation(20,20);
				tk_question.setLocation(20,70);
				tk_ansnum.setLocation(20,320);
				tk_bingo.setLocation(900,20);
				tk_point.setLocation(20,420);
				text_tkid.setLocation(130,20);
				question_area.setLocation(130,70);
				sp1.setLocation(130,70);
				answerBox.setLocation(130,320);
				a1.setLocation(970,70);
				a2.setLocation(970,120);
				a3.setLocation(970,170);
				a4.setLocation(970,220);
				a5.setLocation(970,270);
				text_point.setLocation(130,420);
				bfind.setLocation(100,500);
				bchange.setLocation(300,500);
				tk_order.setLocation(20,370);
				tk_a1.setLocation(900,70);
				tk_a2.setLocation(900,120);
				tk_a3.setLocation(900,170);
				tk_a4.setLocation(900,220);
				tk_a5.setLocation(900,270);
				//////////////////////////////////////////
				subcontainer.add(tk_id);
				subcontainer.add(tk_question);
				subcontainer.add(tk_ansnum);
				subcontainer.add(tk_bingo);
				subcontainer.add(tk_point);
				subcontainer.add(text_tkid);
				subcontainer.add(answerBox);
				subcontainer.add(a1);
				subcontainer.add(a2);
				subcontainer.add(a3);
				subcontainer.add(a4);
				subcontainer.add(a5);
				subcontainer.add(sp1);
				subcontainer.add(text_point);
				subcontainer.add(bfind);
				subcontainer.add(bchange);
				subcontainer.add(tk_order);
				subcontainer.add(tk_a1);
				subcontainer.add(tk_a2);
				subcontainer.add(tk_a3);
				subcontainer.add(tk_a4);
				subcontainer.add(tk_a5);
				//////////////////////////////////////////
				//------------------------------------------
				subFrame.setPreferredSize(new Dimension(1300,600));
				subFrame.pack();
				desk1.add(subFrame);
				subFrame.setVisible(true);
				bfind.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String tmpid=text_tkid.getText();
						try {
							typeconnectionServer();
		                    output = new ObjectOutputStream(client.getOutputStream());
		                    output.flush();
		                    input = new ObjectInputStream(client.getInputStream());
		                    question_Obj dx1Dx_Obj=new question_Obj("tk_c",tmpid,"",0,"",0);
		                    output.writeObject(dx1Dx_Obj);
		                    output.flush();
		                    try {
								question_Obj dx_g1 = (question_Obj) input.readObject();
								//text_find.setText(+"\t"++"\t"++"\t"+dx_g1.getTka2()+"\t"+dx_g1.getTka3()+"\t"+dx_g1.getTka4()+"\t"+dx_g1.getTka5()+"\t"++"\t"+dx_g1.getTeacher());
								question_area.setText(dx_g1.getDxquestion());
								answerBox.setSelectedItem(dx_g1.getAnswer_num());
								//if(dx_g1.getAnswer_num()==1)
								if(dx_g1.getAnswer_num()==1) {
									a1.setEditable(true);
									a2.setEditable(false);
									a3.setEditable(false);
									a4.setEditable(false);
									a5.setEditable(false);
									a2.setText("");
									a3.setText("");
									a4.setText("");
									a5.setText("");
								}
								if(dx_g1.getAnswer_num()==2) {
									a1.setEditable(true);
									a2.setEditable(true);
									a3.setEditable(false);
									a4.setEditable(false);
									a5.setEditable(false);
									a3.setText("");
									a4.setText("");
									a5.setText("");
								}
								if(dx_g1.getAnswer_num()==3) {
									a1.setEditable(true);
									a2.setEditable(true);
									a3.setEditable(true);
									a4.setEditable(false);
									a5.setEditable(false);
									a4.setText("");
									a5.setText("");
								}
								if(dx_g1.getAnswer_num()==4) {
									a1.setEditable(true);
									a2.setEditable(true);
									a3.setEditable(true);
									a4.setEditable(true);
									a5.setEditable(false);
									a5.setText("");
								}
								if(dx_g1.getAnswer_num()==5) {
									a1.setEditable(true);
									a2.setEditable(true);
									a3.setEditable(true);
									a4.setEditable(true);
									a5.setEditable(true);
								}
								if(dx_g1.getTkorder().equals("true"))
									tk_order.setSelected(true);
								else 
									tk_order.setSelected(false);
								text_point.setText(String.valueOf(dx_g1.getPoints()));
								a1.setText(dx_g1.getTka1());
								a2.setText(dx_g1.getTka2());
								a3.setText(dx_g1.getTka3());
								a4.setText(dx_g1.getTka4());
								a5.setText(dx_g1.getTka5());
		                    } catch (ClassNotFoundException e) {e.printStackTrace();}
		                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
						System.out.println("successful");
					}
				});
				bchange.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String tmpid=text_tkid.getText();
	                    question_Obj xinti=new question_Obj("tk_gsave",text_tkid.getText(),question_area.getText(),Integer.parseInt(answerBox.getSelectedItem().toString()),String.valueOf(tk_order.isSelected()),a1.getText(),a2.getText(),a3.getText(),a4.getText(),a5.getText(),Integer.parseInt(text_point.getText().toString()),t1.getTc_name());
						try {
							typeconnectionServer();
		                    output = new ObjectOutputStream(client.getOutputStream());
		                    output.flush();
		                    input = new ObjectInputStream(client.getInputStream());
		                    output.writeObject(xinti);
		                    output.flush();
		                    try {
								String tmpwarning=(String)input.readObject();
								if(tmpwarning.equals("error")) {
									JOptionPane.showMessageDialog(null, "change Error");
								}
								else {
									JOptionPane.showMessageDialog(null, "change Successful");
								}
							} catch (ClassNotFoundException e) {e.printStackTrace();}
		                    
		                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
					}
				});
			}
		});
		tk_c.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JInternalFrame subFrame=new JInternalFrame("查找填空题，出题人："+t1.getTc_name(),true,true,true,true);
				Container subcontainer=subFrame.getContentPane();
				subcontainer.setLayout(null);
				//----------------------------------------------------
				JLabel tk_findid=new JLabel("题号：");
				JLabel tk_findquestion=new JLabel("题干：");
				JTextField text_findid=new JTextField(30);
				JTextField text_findquestion=new JTextField(30);
				JTextArea text_find=new JTextArea(30,30);
				JScrollPane sp4=new JScrollPane(text_find);
				JButton bfind=new JButton("查找");
				JButton bfindexit=new JButton("关闭");
				//////////////////////////////////////////////
				tk_findid.setFont(font2);
				tk_findquestion.setFont(font2);
				text_find.setFont(font2);
				text_findid.setFont(font2);
				text_findquestion.setFont(font2);
				bfind.setFont(font2);
				bfindexit.setFont(font2);
				//////////////////////////////////////////////
				tk_findid.setSize(100,30);
				tk_findquestion.setSize(100,30);
				text_find.setSize(450,300);
				text_findid.setSize(300,30);
				text_findquestion.setSize(300,30);
				sp4.setSize(800,300);
				bfind.setSize(100,30);
				bfindexit.setSize(100,30);
				//////////////////////////////////////////////
				tk_findid.setLocation(20,20);
				tk_findquestion.setLocation(20,70);
				text_find.setLocation(20,120);
				text_findid.setLocation(130,20);
				text_findquestion.setLocation(130,70);
				sp4.setLocation(20,120);
				bfind.setLocation(100,500);
				bfindexit.setLocation(300,500);
				//----------------------------------------------------
				subcontainer.add(tk_findid);
				subcontainer.add(tk_findquestion);
				subcontainer.add(sp4);
				subcontainer.add(text_findid);
				subcontainer.add(text_findquestion);
				subcontainer.add(bfind);
				subcontainer.add(bfindexit);
				subFrame.setPreferredSize(new Dimension(1000,600));
				subFrame.pack();
				desk1.add(subFrame);
				subFrame.setVisible(true);
				bfind.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String tmpid=text_findid.getText();
						try {
							typeconnectionServer();
		                    output = new ObjectOutputStream(client.getOutputStream());
		                    output.flush();
		                    input = new ObjectInputStream(client.getInputStream());
		                    question_Obj dx1Dx_Obj=new question_Obj("tk_c",tmpid,"",0,"",0);
		                    output.writeObject(dx1Dx_Obj);
		                    output.flush();
		                    try {
								question_Obj dx_g1 = (question_Obj) input.readObject();
								text_find.setText(dx_g1.getDxid()+"\t"+dx_g1.getDxquestion()+"\t"+dx_g1.getAnswer_num()+"\t"+dx_g1.getTkorder()+"\t"+dx_g1.getTka1()+"\t"+dx_g1.getTka2()+"\t"+dx_g1.getTka3()+"\t"+dx_g1.getTka4()+"\t"+dx_g1.getTka5()+"\t"+dx_g1.getPoints()+"\t"+dx_g1.getTeacher());
							} catch (ClassNotFoundException e) {e.printStackTrace();}
		                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
						System.out.println("successful");
					}
				});
				bfindexit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
					}
				});
			}
		});
		
		pd_z.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
                    typeconnectionServer();
                    output = new ObjectOutputStream(client.getOutputStream());
                    output.flush();
                    input = new ObjectInputStream(client.getInputStream());
                    output.writeObject(new question_Obj("pd_id"));
                    output.flush();
                    String tmpS=(String)input.readObject();
                    tmpdx_id=tmpS;
                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
				catch (ClassNotFoundException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);}
				JInternalFrame subFrame=new JInternalFrame("增加判断题，出题人："+t1.getTc_name(),true,true,true,true);
				Container subcontainer=subFrame.getContentPane();
				subcontainer.setLayout(null);
				//------------------------------------------
				JLabel pd_id=new JLabel("题号：");
				JLabel pd_question=new JLabel("题干：");
				JLabel pd_bingo=new JLabel("正确答案：");
				JLabel pd_point=new JLabel("得分：");
				JTextField text_pdid=new JTextField(30);
				JTextArea question_area=new JTextArea(30,30);
				JScrollPane sp1=new JScrollPane(question_area);
				JComboBox answerBox=new JComboBox();
				JTextField text_point=new JTextField(30);
				JButton bsave=new JButton("保存");
				JButton bexit=new JButton("关闭");
				answerBox.addItem("true");
				answerBox.addItem("false");
				sp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				text_pdid.setEditable(false);
				text_pdid.setText(tmpdx_id);
				//////////////////////////////////////////
				pd_id.setFont(font2);
				pd_question.setFont(font2);
				pd_bingo.setFont(font2);
				pd_point.setFont(font2);
				text_pdid.setFont(font2);
				question_area.setFont(font2);
				answerBox.setFont(font2);
				bsave.setFont(font2);
				bexit.setFont(font2);
				text_point.setFont(font2);
				//////////////////////////////////////////
				pd_id.setSize(100,30);
				pd_question.setSize(100,30);
				pd_bingo.setSize(100,30);
				pd_point.setSize(100,30);
				text_pdid.setSize(300,30);
				question_area.setSize(300,230);
				sp1.setSize(800,230);
				answerBox.setSize(100,30);
				text_point.setSize(150,30);
				bsave.setSize(100,30);
				bexit.setSize(100,30);
				//////////////////////////////////////////
				pd_id.setLocation(20,20);
				pd_question.setLocation(20,70);
				pd_bingo.setLocation(20,320);
				pd_point.setLocation(20,370);
				text_pdid.setLocation(130,20);
				question_area.setLocation(130,70);
				sp1.setLocation(130,70);
				answerBox.setLocation(130,320);
				text_point.setLocation(130,370);
				bsave.setLocation(100,500);
				bexit.setLocation(300,500);
				//////////////////////////////////////////
				subcontainer.add(pd_id);
				subcontainer.add(pd_question);
				subcontainer.add(pd_bingo);
				subcontainer.add(pd_point);
				subcontainer.add(text_pdid);
				subcontainer.add(answerBox);
				subcontainer.add(sp1);
				subcontainer.add(text_point);
				subcontainer.add(bsave);
				subcontainer.add(bexit);
				//////////////////////////////////////////
				//------------------------------------------
				subFrame.setPreferredSize(new Dimension(1000,600));
				subFrame.pack();
				desk1.add(subFrame);
				subFrame.setVisible(true);
				subFrame.addInternalFrameListener(new InternalFrameAdapter() {
					public void internalFrameClosing(InternalFrameEvent arg0) {
	                    typeconnectionServer();
	                    try {
							output = new ObjectOutputStream(client.getOutputStream());
							output.flush();
		                    input = new ObjectInputStream(client.getInputStream());
		                    question_Obj dxidreturn=new question_Obj("pd_idreturn",text_pdid.getText());
		                    output.writeObject(dxidreturn);
		                    output.flush();
		                    JOptionPane.showMessageDialog(null, "closing and return id successful");
						} catch (IOException e) {e.printStackTrace();}    
					}
				});
				bsave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String tmpquestion=question_area.getText().trim();
						String rightanswer=answerBox.getSelectedItem().toString();
						int tmppoint=Integer.parseInt(text_point.getText().trim());
						try {
							typeconnectionServer();
		                    output = new ObjectOutputStream(client.getOutputStream());
		                    output.flush();
		                    input = new ObjectInputStream(client.getInputStream());
		                    question_Obj dx1Dx_Obj=new question_Obj("pd_z",tmpdx_id,tmpquestion,rightanswer,tmppoint,t1.getTc_name());
		                    output.writeObject(dx1Dx_Obj);
		                    output.flush();
		                    try {
								String tmpwar = (String) input.readObject();
								if(tmpwar.equals("successful")) {
									JOptionPane.showMessageDialog(null, "save successful");
									try {
					                    typeconnectionServer();
					                    output = new ObjectOutputStream(client.getOutputStream());
					                    output.flush();
					                    input = new ObjectInputStream(client.getInputStream());
					                    output.writeObject(new question_Obj("pd_id"));
					                    output.flush();
					                    String tmpS=(String)input.readObject();
					                    tmpdx_id=tmpS;
					                    text_pdid.setText(tmpdx_id);
					                    question_area.setText("");
					                    answerBox.setSelectedIndex(0);
					                    text_point.setText("");
					                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
								}
								else {
									JOptionPane.showMessageDialog(null, "save error");
								}
							} catch (ClassNotFoundException e) {e.printStackTrace();}
		                    
		                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
						
					}
				});
			}
		});
		pd_s.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JInternalFrame subFrame=new JInternalFrame("删除判断题，出题人："+t1.getTc_name(),true,true,true,true);
				Container subcontainer=subFrame.getContentPane();
				subcontainer.setLayout(null);
				//----------------------------------------------------
				JLabel pd_delid=new JLabel("题号：");
				JLabel pd_delquestion=new JLabel("题干：");
				JTextField text_delid=new JTextField(30);
				JTextField text_delquestion=new JTextField(30);
				JTextArea text_del=new JTextArea(30,30);
				JScrollPane sp2=new JScrollPane(text_del);
				JButton bdelfind=new JButton("查找");
				JButton bdeldel=new JButton("删除");
				//////////////////////////////////////////////
				pd_delid.setFont(font2);
				pd_delquestion.setFont(font2);
				text_del.setFont(font2);
				text_delid.setFont(font2);
				text_delquestion.setFont(font2);
				bdelfind.setFont(font2);
				bdeldel.setFont(font2);
				//////////////////////////////////////////////
				pd_delid.setSize(100,30);
				pd_delquestion.setSize(100,30);
				text_del.setSize(450,300);
				text_delid.setSize(300,30);
				text_delquestion.setSize(300,30);
				sp2.setSize(800,300);
				bdelfind.setSize(100,30);
				bdeldel.setSize(100,30);
				//////////////////////////////////////////////
				pd_delid.setLocation(20,20);
				pd_delquestion.setLocation(20,70);
				text_del.setLocation(20,120);
				text_delid.setLocation(130,20);
				text_delquestion.setLocation(130,70);
				sp2.setLocation(20,120);
				bdelfind.setLocation(100,500);
				bdeldel.setLocation(300,500);
				//----------------------------------------------------
				subcontainer.add(pd_delid);
				subcontainer.add(pd_delquestion);
				subcontainer.add(sp2);
				subcontainer.add(text_delid);
				subcontainer.add(text_delquestion);
				subcontainer.add(bdelfind);
				subcontainer.add(bdeldel);
				subFrame.setPreferredSize(new Dimension(1000,600));
				subFrame.pack();
				desk1.add(subFrame);
				subFrame.setVisible(true);
				bdelfind.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String tmpid=text_delid.getText();
						try {
							typeconnectionServer();
		                    output = new ObjectOutputStream(client.getOutputStream());
		                    output.flush();
		                    input = new ObjectInputStream(client.getInputStream());
		                    question_Obj dx1Dx_Obj=new question_Obj("pd_c",tmpid,"",0,"",0);
		                    output.writeObject(dx1Dx_Obj);
		                    output.flush();
		                    try {
								question_Obj dx_g1 = (question_Obj) input.readObject();
								text_del.setText(dx_g1.getDxid()+"\t"+dx_g1.getDxquestion()+"\t"+dx_g1.getRight_answer()+"\t"+dx_g1.getPoints()+"\t"+dx_g1.getTeacher());
							} catch (ClassNotFoundException e) {e.printStackTrace();}
		                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
						System.out.println("successful");
					}
				});
				bdeldel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String tmpid=text_delid.getText();
						if(text_del.getText().equals("")){
							JOptionPane.showMessageDialog(null, "没有读取到记录");
						}
						else{
							try {
								typeconnectionServer();
			                    output = new ObjectOutputStream(client.getOutputStream());
			                    output.flush();
			                    input = new ObjectInputStream(client.getInputStream());
			                    question_Obj dx1Dx_Obj=new question_Obj("pd_s",tmpid,"",0,"",0);
			                    output.writeObject(dx1Dx_Obj);
			                    output.flush();
			                    try {
									String tmpdel=(String)input.readObject();
									JOptionPane.showMessageDialog(null, "Delete "+tmpdel);
									text_del.setText("");
									text_delid.setText("");
									text_delquestion.setText("");
								} catch (ClassNotFoundException e) {e.printStackTrace();}
			                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);}
						}
					}
				});
			}
		});
		pd_g.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JInternalFrame subFrame=new JInternalFrame("修改判断题，出题人："+t1.getTc_name(),true,true,true,true);
				Container subcontainer=subFrame.getContentPane();
				subcontainer.setLayout(null);
				//------------------------------------------
				JLabel pd_id=new JLabel("题号：");
				JLabel pd_question=new JLabel("题干：");
				JLabel pd_bingo=new JLabel("正确答案：");
				JLabel pd_point=new JLabel("得分：");
				JTextField text_pdid=new JTextField(30);
				JTextArea question_area=new JTextArea(30,30);
				JScrollPane sp1=new JScrollPane(question_area);
				JComboBox answerBox=new JComboBox();
				JTextField text_point=new JTextField(30);
				JButton bfind=new JButton("查找");
				JButton bchange=new JButton("修改");
				answerBox.addItem("true");
				answerBox.addItem("false");
				sp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				//////////////////////////////////////////
				pd_id.setFont(font2);
				pd_question.setFont(font2);
				pd_bingo.setFont(font2);
				pd_point.setFont(font2);
				text_pdid.setFont(font2);
				question_area.setFont(font2);
				answerBox.setFont(font2);
				bfind.setFont(font2);
				bchange.setFont(font2);
				text_point.setFont(font2);
				//////////////////////////////////////////
				pd_id.setSize(100,30);
				pd_question.setSize(100,30);
				pd_bingo.setSize(100,30);
				pd_point.setSize(100,30);
				text_pdid.setSize(300,30);
				question_area.setSize(300,230);
				sp1.setSize(800,230);
				answerBox.setSize(100,30);
				text_point.setSize(150,30);
				bfind.setSize(100,30);
				bchange.setSize(100,30);
				//////////////////////////////////////////
				pd_id.setLocation(20,20);
				pd_question.setLocation(20,70);
				pd_bingo.setLocation(20,320);
				pd_point.setLocation(20,370);
				text_pdid.setLocation(130,20);
				question_area.setLocation(130,70);
				sp1.setLocation(130,70);
				answerBox.setLocation(130,320);
				text_point.setLocation(130,370);
				bfind.setLocation(100,500);
				bchange.setLocation(300,500);
				//////////////////////////////////////////
				subcontainer.add(pd_id);
				subcontainer.add(pd_question);
				subcontainer.add(pd_bingo);
				subcontainer.add(pd_point);
				subcontainer.add(text_pdid);
				subcontainer.add(answerBox);
				subcontainer.add(sp1);
				subcontainer.add(text_point);
				subcontainer.add(bfind);
				subcontainer.add(bchange);
				//////////////////////////////////////////
				//------------------------------------------
				subFrame.setPreferredSize(new Dimension(1000,600));
				subFrame.pack();
				desk1.add(subFrame);
				subFrame.setVisible(true);
				bfind.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String tmpid=text_pdid.getText();
						try {
							typeconnectionServer();
		                    output = new ObjectOutputStream(client.getOutputStream());
		                    output.flush();
		                    input = new ObjectInputStream(client.getInputStream());
		                    question_Obj dx1Dx_Obj=new question_Obj("pd_c",tmpid,"",0,"",0);
		                    output.writeObject(dx1Dx_Obj);
		                    output.flush();
		                    try {
								question_Obj dx_g1 = (question_Obj) input.readObject();
								question_area.setText(dx_g1.getDxquestion());
								answerBox.setSelectedItem(dx_g1.getRight_answer());
								text_point.setText(String.valueOf(dx_g1.getPoints()));
							} catch (ClassNotFoundException e) {e.printStackTrace();}
		                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
						System.out.println("successful");
					}
				});
				bchange.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String tmpid=text_pdid.getText();
	                    question_Obj xinti=new question_Obj("pd_gsave",text_pdid.getText(),question_area.getText(),answerBox.getSelectedItem().toString(),Integer.parseInt(text_point.getText()),t1.getTc_name());
						try {
							typeconnectionServer();
		                    output = new ObjectOutputStream(client.getOutputStream());
		                    output.flush();
		                    input = new ObjectInputStream(client.getInputStream());
		                    output.writeObject(xinti);
		                    output.flush();
		                    try {
								String tmpwarning=(String)input.readObject();
								if(tmpwarning.equals("error")) {
									JOptionPane.showMessageDialog(null, "change Error");
								}
								else {
									JOptionPane.showMessageDialog(null, "change Successful");
								}
							} catch (ClassNotFoundException e) {e.printStackTrace();}
		                    
		                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
					}
				});
			}
		});
		pd_c.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JInternalFrame subFrame=new JInternalFrame("查找判断题，出题人："+t1.getTc_name(),true,true,true,true);
				Container subcontainer=subFrame.getContentPane();
				subcontainer.setLayout(null);
				//----------------------------------------------------
				JLabel pd_findid=new JLabel("题号：");
				JLabel pd_findquestion=new JLabel("题干：");
				JTextField text_findid=new JTextField(30);
				JTextField text_findquestion=new JTextField(30);
				JTextArea text_find=new JTextArea(30,30);
				JScrollPane sp4=new JScrollPane(text_find);
				JButton bfind=new JButton("查找");
				JButton bfindexit=new JButton("关闭");
				//////////////////////////////////////////////
				pd_findid.setFont(font2);
				pd_findquestion.setFont(font2);
				text_find.setFont(font2);
				text_findid.setFont(font2);
				text_findquestion.setFont(font2);
				bfind.setFont(font2);
				bfindexit.setFont(font2);
				//////////////////////////////////////////////
				pd_findid.setSize(100,30);
				pd_findquestion.setSize(100,30);
				text_find.setSize(450,300);
				text_findid.setSize(300,30);
				text_findquestion.setSize(300,30);
				sp4.setSize(800,300);
				bfind.setSize(100,30);
				bfindexit.setSize(100,30);
				//////////////////////////////////////////////
				pd_findid.setLocation(20,20);
				pd_findquestion.setLocation(20,70);
				text_find.setLocation(20,120);
				text_findid.setLocation(130,20);
				text_findquestion.setLocation(130,70);
				sp4.setLocation(20,120);
				bfind.setLocation(100,500);
				bfindexit.setLocation(300,500);
				//----------------------------------------------------
				subcontainer.add(pd_findid);
				subcontainer.add(pd_findquestion);
				subcontainer.add(sp4);
				subcontainer.add(text_findid);
				subcontainer.add(text_findquestion);
				subcontainer.add(bfind);
				subcontainer.add(bfindexit);
				subFrame.setPreferredSize(new Dimension(1000,600));
				subFrame.pack();
				desk1.add(subFrame);
				subFrame.setVisible(true);
				bfind.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String tmpid=text_findid.getText();
						try {
							typeconnectionServer();
		                    output = new ObjectOutputStream(client.getOutputStream());
		                    output.flush();
		                    input = new ObjectInputStream(client.getInputStream());
		                    question_Obj dx1Dx_Obj=new question_Obj("pd_c",tmpid,"",0,"",0);
		                    output.writeObject(dx1Dx_Obj);
		                    output.flush();
		                    try {
								question_Obj dx_g1 = (question_Obj) input.readObject();
								text_find.setText(dx_g1.getDxid()+"\t"+dx_g1.getDxquestion()+"\t"+dx_g1.getRight_answer()+"\t"+dx_g1.getPoints()+"\t"+dx_g1.getTeacher());
							} catch (ClassNotFoundException e) {e.printStackTrace();}
		                } catch (IOException ex) {Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
						System.out.println("successful");
					}
				});
			}
		});
		/////////////////////////////////////////////////////////////////////
		
	    container.add(desk1);
	    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    setVisible(true);
	}
	public static void main(String[] args) {
		test_ZSGC a=new test_ZSGC();
		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
//    public void connectionServer(){
//        try {
//            client=new Socket(InetAddress.getByName("127.0.0.1"),12340);    
//        } catch (IOException ex) { Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
//    }
    public void typeconnectionServer(){
        try {
            client=new Socket(InetAddress.getByName("127.0.0.1"),12341);    
        } catch (IOException ex) { Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);} 
    }
	public String getT_name() {return t_name;}
	public void setT_name(String t_name) {this.t_name = t_name;}
	public teacher_Obj getT1() {return t1;}
	public void setT1(teacher_Obj t1) {this.t1 = t1;}

}
