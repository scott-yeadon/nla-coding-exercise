package com.example.demo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowerRepository extends CrudRepository<Borrower, Long> {
    List<Borrower> findBySurname(String surname);
}