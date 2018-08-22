package com.harry.spring_hibernate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.harry.spring_hibernate.service.BookShopService;
import com.harry.spring_hibernate.service.Cashier;

@Repository
public class CashierImpl implements Cashier {
	
	@Autowired
	private BookShopService bookShopService;
	
	@Transactional
	public void checkout(String userName , List<String> isbns) {
		for (String Isbn:isbns) {
			bookShopService.purchar(userName, Isbn);
		}
	}
}
