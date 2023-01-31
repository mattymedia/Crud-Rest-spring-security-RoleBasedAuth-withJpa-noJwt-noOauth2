package co.com.jwtrolebasedauthjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.jwtrolebasedauthjpa.model.Book;

public interface IBookRepository extends JpaRepository<Book, Integer> {

}
