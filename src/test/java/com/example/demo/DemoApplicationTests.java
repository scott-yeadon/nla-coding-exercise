package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

	@Autowired
    BookRepository bookRepository;
	
	@Autowired
    BorrowerRepository borrowerRepository;
	
	@Autowired
	private DemoApplication restController;

	@Test
	void contextLoads() {
		Assert.assertNotNull(restController);
	}

	@Test
	public void areBooksFound() {

		Iterable<Book> books = bookRepository.findAll();
		
		int counter = 0;
		for (Book book : books) {
			counter++;
		}
 
		Assert.assertTrue("No books were found", counter > 1);
	}
	
	@Test
	public void areBorrowersFound() {

		Iterable<Borrower> borrowers = borrowerRepository.findAll();
		
		int counter = 0;
		for (Borrower borrower : borrowers) {
			counter++;
		}
 
		Assert.assertTrue("No borrowers were found", counter > 1);
	}

	@Test
	public void areBooksForBorrowerFound() {

		List<Book> books = bookRepository.findByBorrowerBorrowerId(new Long(4));
 
		Assert.assertTrue("Three records should have been found for borrower 4", books.size() == 3);
	}	
}