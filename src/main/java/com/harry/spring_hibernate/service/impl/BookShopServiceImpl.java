package com.harry.spring_hibernate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.harry.spring_hibernate.DAO.BookShopDao;
import com.harry.spring_hibernate.service.BookShopService;

@Service
public class BookShopServiceImpl implements BookShopService {
	
	@Autowired
	private BookShopDao bookShopDao;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void purchar(String userName, String Isbn) {
		
		int price = bookShopDao.findBookPriceByIsbn(Isbn);
		bookShopDao.updateUserAccount(userName, price);
		bookShopDao.updateBookStock(Isbn);
		
	}

}
