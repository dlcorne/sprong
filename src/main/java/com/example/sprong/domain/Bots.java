package com.example.sprong.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bots {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String make;
	private Integer age;
	private int cost;
	
	public Bots(Integer id, String name, String make, Integer age, int cost) {
		super();
		this.id = id; 
		this.name = name;
		this.make = make;
		this.age = age;
		this.cost = cost;
	}
	
	public Bots(String name, String make, Integer age, int cost) {
		super();
		this.name = name;
		this.make = make;
		this.age = age;
		this.cost = cost;
	}
	
	public Bots() {
		super();
	}
	
	public int getId() {
		return this.id;
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

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
	@Override
	public String toString() {
		return "Bot [name=" + this.name + ", make= " + this.make + ", age= " + this.age + ", cost= " + this.cost + "]";
	}
	
}
