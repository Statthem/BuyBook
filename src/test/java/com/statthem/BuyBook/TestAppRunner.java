package com.statthem.BuyBook;

import java.io.File;

import org.junit.runner.RunWith;

import com.statthem.BuyBook.DAO.BookDAOTest;


@RunWith(BookDAOTest.class)
public class TestAppRunner {
	public static void main(String args[]) {
		
		File file = new File("src/main/webapp/resources/images/witcher.jpg");
		if(file.isFile()) System.out.println("is file");
		
	}

}
