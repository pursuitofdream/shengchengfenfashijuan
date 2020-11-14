package test_Client;

import java.io.Serializable;

public class teacher_Obj implements Serializable{
	public teacher_Obj(String tc_type) {
		super();
		this.tc_type = tc_type;
	}
	String tc_type;
	String tc_id;
	String tc_name;
	String tc_password;
	String tc_year;
	String tc_mouth;
	String tc_day;
	String tc_sex;
	String tc_phone;
	String tc_memo;
	public teacher_Obj(String tc_id, String tc_name) {
		super();
		this.tc_id = tc_id;
		this.tc_name = tc_name;
	}
	public teacher_Obj() {super();}
	public teacher_Obj(String tc_type, String tc_id, String tc_password) {
		super();
		this.tc_type = tc_type;
		this.tc_id = tc_id;
		this.tc_password = tc_password;
	}
	public teacher_Obj(String tc_type, String tc_id, String tc_name, String tc_password) {
		super();
		this.tc_type = tc_type;
		this.tc_id = tc_id;
		this.tc_name = tc_name;
		this.tc_password = tc_password;
	}
	public teacher_Obj(String tc_type, String tc_id, String tc_name, String tc_password, String tc_year,
			String tc_mouth, String tc_day, String tc_sex, String tc_phone, String tc_memo) {
		super();
		this.tc_type = tc_type;
		this.tc_id = tc_id;
		this.tc_name = tc_name;
		this.tc_password = tc_password;
		this.tc_year = tc_year;
		this.tc_mouth = tc_mouth;
		this.tc_day = tc_day;
		this.tc_sex = tc_sex;
		this.tc_phone = tc_phone;
		this.tc_memo = tc_memo;
	}
	
	public String getTc_type() {return tc_type;}
	public void setTc_type(String tc_type) {this.tc_type = tc_type;}
	public String getTc_id() {return tc_id;}
	public void setTc_id(String tc_id) {this.tc_id = tc_id;}
	public String getTc_name() {
		return tc_name;
	}
	public void setTc_name(String tc_name) {
		this.tc_name = tc_name;
	}
	public String getTc_password() {
		return tc_password;
	}
	public void setTc_password(String tc_password) {
		this.tc_password = tc_password;
	}
	public String getTc_year() {
		return tc_year;
	}
	public void setTc_year(String tc_year) {
		this.tc_year = tc_year;
	}
	public String getTc_mouth() {
		return tc_mouth;
	}
	public void setTc_mouth(String tc_mouth) {
		this.tc_mouth = tc_mouth;
	}
	public String getTc_day() {
		return tc_day;
	}
	public void setTc_day(String tc_day) {
		this.tc_day = tc_day;
	}
	public String getTc_sex() {
		return tc_sex;
	}
	public void setTc_sex(String tc_sex) {
		this.tc_sex = tc_sex;
	}
	public String getTc_phone() {
		return tc_phone;
	}
	public void setTc_phone(String tc_phone) {
		this.tc_phone = tc_phone;
	}
	public String getTc_memo() {
		return tc_memo;
	}
	public void setTc_memo(String tc_memo) {
		this.tc_memo = tc_memo;
	}
	
}
