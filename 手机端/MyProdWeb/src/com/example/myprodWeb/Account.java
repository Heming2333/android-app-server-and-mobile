package com.example.myprodWeb;

public class Account {
	private Long id;			
	private String name;
	private Integer balance;
	private Integer stock;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getBalance() {
		return balance;
	}
	public Integer getStock() {
		return stock;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Account(Long id, String name, Integer balance,Integer stock) {
		super();
		this.id = id;
		this.name = name;
		this.balance = balance;
		this.stock = stock;
	}
	public Account(String name, Integer balance,Integer stock) {
		super();
		this.name = name;
		this.balance = balance;
		this.stock = stock;
	}
	public Account() {
		super();
	}
	public String toString() {
		return "[序号: " + id + ", 商品名称姓名: " + name + ", 余额: " + balance + ", 商品数量: "+stock+"]";
	}
}
