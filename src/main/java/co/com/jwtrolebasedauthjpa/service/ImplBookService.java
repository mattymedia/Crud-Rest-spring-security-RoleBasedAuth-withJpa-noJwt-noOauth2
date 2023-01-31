package co.com.jwtrolebasedauthjpa.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.jwtrolebasedauthjpa.model.Book;
import co.com.jwtrolebasedauthjpa.repository.IBookRepository;

@Service
public class ImplBookService implements IBookService {

	@Autowired
	private IBookRepository bookRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Book findById(Integer id) {
		return bookRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Book save(Book book) {
		return bookRepository.save(book);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		bookRepository.deleteById(id);
	}

}
