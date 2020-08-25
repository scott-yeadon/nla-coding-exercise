package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;
import org.json.JSONArray;

@SpringBootApplication
@RestController
public class DemoApplication {

	private static Log log = LogFactory.getLog(DemoApplication.class);
	
	@Autowired
    private final BookRepository bookRepository;
	
	@Autowired
    private final BorrowerRepository borrowerRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	public DemoApplication(BookRepository bookRepository, BorrowerRepository borrowerRepository) {
        this.bookRepository = bookRepository;
		this.borrowerRepository = borrowerRepository;
		loadData();		
    }

	private void loadData() {
		log.info("initialising database");

		Borrower borrower = new Borrower("Newman", "Alfred E", "aenewman@coldmail.com");
		borrowerRepository.save(borrower);
        bookRepository.save(new Book("The Drover's Wife", "Henry Lawson", "978-1502334060", borrower));
        bookRepository.save(new Book("In For The Long Haul", "Annegret Hall", "978-0987629203", borrower));
		
		borrower = new Borrower("Angelo", "Michael", "michaela@yeehaw.com");
		borrowerRepository.save(borrower);
	    bookRepository.save(new Book("The Riders", "Tim Winton", "978-0684802961", borrower));
        bookRepository.save(new Book("The Fatal Shore", "Robert Hughes", "978-0307291615", borrower));
        bookRepository.save(new Book("Convict Girl", "Chrissie Michaels", "978-1743620151", borrower));

		borrower = new Borrower("Normal", "A B", "abnormal@geemail.com");
		borrowerRepository.save(borrower);
        bookRepository.save(new Book("Picnic at Hanging Rock", "Joan Lindsay", "978-0855583644", borrower));
		
		borrower = new Borrower("Smith", "John", "jsmith@yeehaw.com");
		borrowerRepository.save(borrower);
		bookRepository.save(new Book("The Book Thief", "Markus Zusak", "978-0375842209", borrower));
		
		borrower = new Borrower("Nerks", "Fred", "fred.nerks@geemail.com");
		borrowerRepository.save(borrower);
		bookRepository.save(new Book("The Chant of Jimmie Blacksmith", "Thomas Keneally", "978-1525257757", borrower));
		
		bookRepository.save(new Book("The Broken Shore", "Peter Temple", "978-0374116934"));
		bookRepository.save(new Book("The Thorn Birds", " Colleen McCullough", "978-0230196803"));

		log.info("books found with findAll():");
        bookRepository.findAll().forEach(c -> log.info(c.toString()));

		log.info("borrowers found with findAll():");
        borrowerRepository.findAll().forEach(c -> log.info(c.toString()));
	}

	@GetMapping("/books")
	public String getBooks(@RequestParam(required = false) Long borrowerId) throws Exception{
		log.info("getBooks called with borrowerID = " + borrowerId);
		if (borrowerId == null)
		{
			JSONObject bookList = new JSONObject();
			JSONArray books = new JSONArray();
			bookRepository.findAll().forEach(item -> {
				try {
					JSONObject book = new JSONObject();
					book.put("bookID", item.getBookId());
					book.put("title", item.getTitle());
					book.put("author", item.getAuthor());
					book.put("isbn", item.getISBN());
					if (item.getBorrower() != null) {
						book.put("borrowerId", item.getBorrower().getBorrowerId());
					}
					books.put(book);
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}
			});
			return books.toString();
		} else {
			JSONObject bookList = new JSONObject();
			JSONArray books = new JSONArray();
			bookRepository.findByBorrowerBorrowerId(borrowerId).forEach(item -> {
				try {
					JSONObject book = new JSONObject();
					book.put("bookID", item.getBookId());
					book.put("title", item.getTitle());
					book.put("author", item.getAuthor());
					book.put("isbn", item.getISBN());
					book.put("borrowerId", item.getBorrower().getBorrowerId());
					books.put(book);
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}
			});			
			return books.toString();
		}	
	}
	
	@GetMapping("/borrowers")
	public String getBorrowers() throws Exception {
		log.info("getBorrowers called");
		JSONObject borrowerList = new JSONObject();
		JSONArray borrowers = new JSONArray();
		borrowerRepository.findAll().forEach(item -> {
			try {
			JSONObject borrower = new JSONObject();
			borrower.put("borrowerId", item.getBorrowerId());
			borrower.put("surname", item.getSurname());
			borrower.put("givenNames", item.getGivenNames());
			borrower.put("email", item.getEmail());
			borrowers.put(borrower);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		});
		return borrowers.toString();
	}
}
