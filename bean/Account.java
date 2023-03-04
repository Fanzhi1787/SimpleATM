package bean;

import bean.Account;

public class Account {
  int id ;
  String name;
  String name2;
  String password ;
  int Money ;

	public Account() {
		super();
	}
  
public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}


public String getName2() {
	return name2;
}

public void setName2(String name2) {
	this.name2 = name2;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public int getMoney() {
	return Money;
}

public void setMoney(int money) {
	Money = money;
}




public Account getByName(String string) {
	// TODO Auto-generated method stub
	return null;
}
}

