package com.statthem.BuyBook.DAO;

import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/servlet-context.xml"})
@WebAppConfiguration
public class UserDAOImplTest {

	@Autowired
	@Qualifier(value = "hibernate4AnnotatedSessionFactory")
	SessionFactory sessionFactory;
	
	@Transactional
	@Test
	public void givenSessionFactory_whenGetSession_thenNotNull() {
		Assert.assertNotNull("problem with db connection",sessionFactory.getCurrentSession());
	}
}
