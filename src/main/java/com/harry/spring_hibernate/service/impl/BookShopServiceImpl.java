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
	
	/**
	 * Spring hibernate 事务的流程
	 * 1.在方法开始之前
	 * 		1.获取Session
	 * 		2.把Session和当前线程绑定，这样就可以在Dao中使用SessionFactory的getCurrentSession() 方法来获取Session了
	 * 		3.开启事务
	 * 
	 * 2.若正常结束，即没有出现异常，则
	 * 		1.提交事务
	 * 		2.使和当前线程绑定的Session 解除绑定
	 *		3.关闭Session
	 *
	 * 3.若方法出现异常，则
	 * 		1.回滚事务
	 * 		2.使和当前线程绑定的Session 解除绑定
	 *		3.关闭Session
	 * */
	//@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void purchar(String userName, String Isbn) {
		
		int price = bookShopDao.findBookPriceByIsbn(Isbn);
		bookShopDao.updateBookStock(Isbn);
		bookShopDao.updateUserAccount(userName, price);
		
		
	}

}
