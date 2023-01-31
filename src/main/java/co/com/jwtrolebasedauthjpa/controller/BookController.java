package co.com.jwtrolebasedauthjpa.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.jwtrolebasedauthjpa.model.Book;
import co.com.jwtrolebasedauthjpa.service.IBookService;

@RestController
@RequestMapping
public class BookController {

	@Autowired
	private IBookService bookService;
	
	@GetMapping
	public String home() {
				
		return "welcome, " + formatString();
	}
	
	@GetMapping("/books")
	public List<Book> findAll(){		
		return bookService.findAll();	
	}
	
	@GetMapping("/books/{id}")
	public Book findById(@PathVariable Integer id){
				
		return bookService.findById(id);
	}
	
	@PostMapping("/books/save")
	public ResponseEntity<String> save(@RequestBody Book book) {
		
		bookService.save(book);
		
		return new ResponseEntity<String>("the book titled: '" + book.getTitle() + 
				"', written by " + book.getAuthor() 
				+ ", have been saved successfully.", HttpStatus.OK);
	}
	
	@PutMapping("/books/edit/{id}")
	public ResponseEntity<String> edit(@PathVariable Integer id, @RequestBody Book book){
		
		Book bookById = bookService.findById(id);
		
		bookById.setTitle(book.getTitle());
		bookById.setAuthor(book.getAuthor());
		bookById.setPublishedOn(book.getPublishedOn());
		
		bookService.save(bookById);
		
		return new ResponseEntity<String>("the changes made in the book: '" 
		+ bookById.getTitle() + "', have been saved successfully.", HttpStatus.OK);
	}
	
	@DeleteMapping("/books/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id){
		
		Book bookById = bookService.findById(id);
		
		bookService.delete(id);
		
		return new ResponseEntity<String>("the book title: '" + bookById.getTitle() 
		+ "', was successfully deleted from the database.", HttpStatus.OK);
	}
	
	public String formatString() {
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatString = DateTimeFormatter.ofPattern("dd LLLL yyyy", Locale.ENGLISH);
		return today.format(formatString);
	}
	
}
