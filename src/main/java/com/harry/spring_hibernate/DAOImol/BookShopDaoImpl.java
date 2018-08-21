package com.harry.spring_hibernate.DAOImol;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.harry.spring_hibernate.DAO.BookShopDao;

@Repository
public class BookShopDaoImpl implements BookShopDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * 不推荐使用HibernateTemplate 和 HibernateDaoSupport
	 * 因为这样会导致Dao和Spring的API融合
	 * 可移植性变差
	 * */
	
	//获取和当前线程绑定的Session。
	private Session getSession() {
		
		return sessionFactory.getCurrentSession();
	}
	
	//根据书号获取书的单价
	public int findBookPriceByIsbn(String Isbn) {
		return 0;
	}
	
	//更新书的库存，使书号对应的库存-1
	public void updateBookStock(String Isbn) {
	}
	
	//更新用户的账户余额：是用户的blance-price
	public void updateUserAccount(String userName , int price) {
	} 
}
