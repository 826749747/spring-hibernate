package com.harry.spring_hibernate.DAOImpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.harry.spring_hibernate.DAO.BookShopDao;
import com.harry.spring_hibernate.entities.BookStockExcpetion;
import com.harry.spring_hibernate.entities.UserAccountExcpetion;


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
		String hql = "SELECT b.price from Book b  where b.isbn= ?0";
		Query query = getSession().createQuery(hql).setString(0, Isbn);
		return (Integer)query.uniqueResult();
	}
	
	//更新书的库存，使书号对应的库存-1
	public void updateBookStock(String Isbn) {
		
		//验证书的库存是否足够
		String hql2 = "Select stock from Book  where isbn = ?0";
		int stock = (Integer) getSession().createQuery(hql2).setString(0, Isbn).uniqueResult();
		if (stock == 0) {
			throw new BookStockExcpetion("库存不足！！");
		}
		
		String hql = "UPDATE Book b set b.stock = b.stock - 1 where b.isbn = ?0";
		getSession().createQuery(hql).setString(0,Isbn).executeUpdate();
	}
	
	//更新用户的账户余额：是用户的blance-price
	public void updateUserAccount(String userName , int price) {
		//验证余额是否足够
		String hql2 = "select balance from Account where userName = ?0";
		int balance = (Integer) getSession().createQuery(hql2).setString(0, userName).uniqueResult();
		if (balance < price) {
			throw new UserAccountExcpetion("余额不足");
		}
		String hql = "UPDATE Account set balance = balance - ?0 where userName = ?1";
		getSession().createQuery(hql).setInteger(0, price ).setString(1, userName).executeUpdate();
	} 
}
