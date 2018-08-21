package com.harry.spring_hibernate.service;

import java.util.List;

public interface Cashier {
	
	public void checkout(String userName,List<String> isbns);
}
