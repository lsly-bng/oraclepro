package com.javaex.phone;

public class PersonVo {

	// field
	private int personId;
	private String name;
	private String hp;
	private String company;

	// constructor
	public PersonVo() {
	}

	public PersonVo(int personId) {
		this.personId = personId;
	}

	public PersonVo(String name, String hp, String company) {
		this.name = name;
		this.hp = hp;
		this.company = company;
	}

	public PersonVo(String name, String hp, String company, int personId) {
		this.name = name;
		this.hp = hp;
		this.company = company;
		this.personId = personId;
	}
	
	public PersonVo(int personId, String name, String hp, String company) {
		super();
		this.personId = personId;
		this.name = name;
		this.hp = hp;
		this.company = company;
	}

	// method - g/s
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	// method - general
	@Override
	public String toString() {
		return "PersonVo [personId=" + personId + ", name=" + name + ", hp=" + hp + ", company=" + company + "]";
	}
}
