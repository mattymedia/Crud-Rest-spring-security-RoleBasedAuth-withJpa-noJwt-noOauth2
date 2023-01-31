package co.com.jwtrolebasedauthjpa.service;

import java.util.List;

import co.com.jwtrolebasedauthjpa.model.Book;

public interface IBookService {

	public List<Book> findAll();
	
	public Book findById(Integer id);
	
	public Book save(Book book);
	
	public void delete(Integer id);
}
