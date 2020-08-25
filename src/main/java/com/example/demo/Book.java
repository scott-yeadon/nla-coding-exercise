package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;

import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "books")
public class Book {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bookId")
    private Long bookId;

    @Column(name = "title")
    private String title;
	
    @Column(name = "author")
	private String author;
	
	@Column (name = "isbn")
	private String isbn;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "borrowerId", nullable = true)
    private Borrower borrower;

	
	public Book() {
		super();
	}

	public Book(String title, String author, String isbn, Borrower borrower) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.borrower = borrower;
	}

    
	public Book(String title, String author, String isbn) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
	}
	
	public Long getBookId() {
		return this.bookId;
	}
	
    public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
    public String getAuthor() {
		return this.author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}

    public String getISBN() {
		return this.isbn;
	}
	
	public void setISBN(String isbn) {
		this.isbn = isbn;
	}

	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}
	
	public Borrower getBorrower() {
		return this.borrower;
	}
	
	public Long getBorrowerId() {
		return this.borrower.getBorrowerId();
	}

	public String toString() {
		if (borrower == null) {
			return String.format("(%d, %d, %s, %s, %s)", this.bookId, null, this.title, this.author, this.isbn);
		}
		
        return String.format("(%d, %d, %s, %s, %s)", this.bookId, this.borrower.getBorrowerId(), this.title, this.author, this.isbn);
    }
}