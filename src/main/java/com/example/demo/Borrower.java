package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "borrower")
public class Borrower {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "borrowerId")
    private Long borrowerId;

    @Column(name = "surname")
    private String surname;
	
    @Column(name = "givenNames")
	private String givenNames;
	
	@Column (name = "email")
	private String email;
		
	@OneToMany(
        cascade = CascadeType.ALL,
		fetch = FetchType.LAZY,
		mappedBy = "borrower"
    )
    private List<Book> borrowedBooks = new ArrayList<>();
 	
	public Borrower() {
		super();
	}
    
	public Borrower(String surname, String givenNames, String email) {
		this.surname = surname;
		this.givenNames = givenNames;
		this.email = email;
	}
	
	public Long getBorrowerId() {
		return this.borrowerId;
	}
	
    public String getSurname() {
		return this.surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
    public String getGivenNames() {
		return this.givenNames;
	}
	
	public void setGivenNames(String givenNames) {
		this.givenNames = givenNames;
	}

    public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void addBook(Book book) {
		this.borrowedBooks.add(book);
	}

	
	public String toString() {
		
        return String.format("(%d, %s, %s, %s)", this.borrowerId, this.surname, this.givenNames, this.email);
    }
}