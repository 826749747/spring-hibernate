package com.harry.spring_hibernate.test;

import java.sql.SQLException;
import java.util.Arrays;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.harry.spring_hibernate.DAO.BookShopDao;
import com.harry.spring_hibernate.service.BookShopService;
import com.harry.spring_hibernate.service.Cashier;

public class SpringHibernateTest {
	
	private ApplicationContext ctx = null;
	BookShopDao bookShopDao = null;
	BookShopService bookShopService = null;
	Cashier cashier = null;
	
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		bookShopDao = ctx.getBean(BookShopDao.class);
		bookShopService =ctx.getBean(BookShopService.class);
		cashier = ctx.getBean(Cashier.class);
	}
	
	@Test
	public void testDataSource() throws SQLException {
		DataSource dataSource = ctx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}
	
	@Test
	public void testFindBookPriceByIsbn() {
		System.out.println(bookShopDao.findBookPriceByIsbn("1001"));
	}
	
	@Test
	public void testUpdateBookStock() {
		bookShopDao.updateBookStock("1001");
	}
	
	@Test
	public void testUpdateUserAccount() {
		bookShopDao.updateUserAccount("AA", 10);
	}
	
	@Test
	public void testPurchar() {
		bookShopService.purchar("AA", "1001");
	}
	
	@Test
	public void testCheckOut() {
		cashier.checkout("AA", Arrays.asList("1001","1002"));
	}
}
