package com.roachf.ssm.spring.test;

import java.lang.reflect.Field;

import javax.jws.soap.SOAPBinding.Use;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_user")
public class User {

	@Id
	private String id;
	
	@Column(name = "u_name")
	private String name; 
	
	@Column(name = "u_birth")
	private String birth;
	
	
	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		Field[] fields = User.class.getDeclaredFields();
		Table table = User.class.getAnnotation(Table.class);
		String tableName = null;
		if(table != null){
			tableName  = table.name();
		}else{
			tableName = User.class.getSimpleName().toUpperCase();
		}
		System.out.println(tableName);
		
		Field f = User.class.getField("birth");
		System.out.println(f.getName());
		
		for (int i = 0; i < fields.length; i++) {
			System.out.println(fields[i].getName());
			Column column = fields[i].getAnnotation(Column.class);
			if(column != null)
			System.out.println("columnName==" + column.name());
		}
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getBirth() {
		return birth;
	}


	public void setBirth(String birth) {
		this.birth = birth;
	}
	
}




