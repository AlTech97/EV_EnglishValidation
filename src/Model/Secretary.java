package Model;

import Interface.UserInterface;

public class Secretary implements UserInterface{
	private String e;
	private String n;
	private String su;
	private char s;
	private String p;
	private int u;
	
	public Secretary() {
	}
	public Secretary(String email, String name, String surname, char sex, String password, int userType) {
		this.e = email;
		this.n = name;
		this.su = surname;
		this.s = sex;
		this.p = password;
		this.u = userType;
	}
	@Override
	public String getEmail() {
		return e;
	}
	@Override
	public String getName() {
		return n;
	}
	@Override
	public String getSurname() {
		return su;
	}
	@Override
	public char getSex() {
		return s;
	}
	@Override
	public String getPassword() {
		return p;
	}
	@Override
	public int getUserType() {
		return u;
	}
	@Override
	public void setEmail(String email) {
		this.e = email;
	}
	@Override
	public void setName(String name) {
		this.n = name;
	}
	@Override
	public void setSurname(String surname) {
		this.su = surname;
	}
	@Override
	public void setSex(char sex) {
		this.s = sex;
	}
	@Override
	public void setPassword(String password) {
		this.p = password;
	}
	@Override
	public void setUserType(int userType) {
		this.u = userType;
	}
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
