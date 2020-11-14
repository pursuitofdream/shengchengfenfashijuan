package test_server;

import java.io.Serializable;

public class question_Obj implements Serializable{
	
	public question_Obj(String queType, String dxid, String dxquestion, int answer_num,String tkorder ,String tka1, String tka2, String tka3, String tka4, String tka5 ,int points, String teacher
			) {
		super();
		this.queType = queType;
		this.dxid = dxid;
		this.dxquestion = dxquestion;
		this.answer_num = answer_num;
		this.tkorder = tkorder;
		this.tka1 = tka1;
		this.tka2 = tka2;
		this.tka3 = tka3;
		this.tka4 = tka4;
		this.tka5 = tka5;
		this.points = points;
		this.teacher = teacher;
	}
	String queType;
	String dxid;
	String dxquestion;
	int answer_num;
	String right_answer;
	int points;
	String teacher;
	///////////////////////
	String tkorder;
	String tka1;
	String tka2;
	String tka3;
	String tka4;
	String tka5;
	public question_Obj(String queType, String dxid, String dxquestion, String right_answer, int points,
			String teacher) {//判断题构造函数
		super();
		this.queType = queType;
		this.dxid = dxid;
		this.dxquestion = dxquestion;
		this.right_answer = right_answer;
		this.points = points;
		this.teacher = teacher;
	}
	public question_Obj(String queType, String dxid) {
		super();
		this.queType = queType;
		this.dxid = dxid;
	}
	public question_Obj(String queType) {
		super();
		this.queType = queType;
	}
	public question_Obj() {
		super();
	}
	public question_Obj(String queType, String dxid, String dxquestion, int answer_num, String right_answer, int points ,String teacher) {
		super();
		this.queType = queType;
		this.dxid = dxid;
		this.dxquestion = dxquestion;
		this.answer_num = answer_num;
		this.right_answer = right_answer;
		this.points = points;
		this.teacher=teacher;
	}
	public question_Obj(String queType, String dxid, String dxquestion, int answer_num, String right_answer, int points) {
		super();
		this.queType = queType;
		this.dxid = dxid;
		this.dxquestion = dxquestion;
		this.answer_num = answer_num;
		this.right_answer = right_answer;
		this.points = points;
	}
	
	public String getQueType() {
		return queType;
	}
	public void setQueType(String queType) {
		this.queType = queType;
	}
	public String getDxid() {
		return dxid;
	}
	public void setDxid(String dxid) {
		this.dxid = dxid;
	}
	public String getDxquestion() {
		return dxquestion;
	}
	public void setDxquestion(String dxquestion) {
		this.dxquestion = dxquestion;
	}
	public int getAnswer_num() {
		return answer_num;
	}
	public void setAnswer_num(int answer_num) {
		this.answer_num = answer_num;
	}
	public String getRight_answer() {
		return right_answer;
	}
	public void setRight_answer(String right_answer) {
		this.right_answer = right_answer;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getTkorder() {
		return tkorder;
	}
	public void setTkorder(String tkorder) {
		this.tkorder = tkorder;
	}
	public String getTka1() {
		return tka1;
	}
	public void setTka1(String tka1) {
		this.tka1 = tka1;
	}
	public String getTka2() {
		return tka2;
	}
	public void setTka2(String tka2) {
		this.tka2 = tka2;
	}
	public String getTka3() {
		return tka3;
	}
	public void setTka3(String tka3) {
		this.tka3 = tka3;
	}
	public String getTka4() {
		return tka4;
	}
	public void setTka4(String tka4) {
		this.tka4 = tka4;
	}
	public String getTka5() {
		return tka5;
	}
	public void setTka5(String tka5) {
		this.tka5 = tka5;
	}
}
