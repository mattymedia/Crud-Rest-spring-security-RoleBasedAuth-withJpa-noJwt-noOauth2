package co.com.jwtrolebasedauthjpa.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin("*")
@RequestMapping("/books")
public class BookController {

	@Autowired
	private IBookService bookService;
	
	@GetMapping("/home")
	public String home() {
		return "welcome to the api!!";
	}
	
	@GetMapping("/show")
	public List<Book> findAll(){		
		return bookService.findAll();	
	}

	@GetMapping("/show/{id}")
	public Book findById(@PathVariable Integer id){
				
		return bookService.findById(id);
	}
	
	@PostMapping("/save")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<String> save(@RequestBody Book book) {
		
		bookService.save(book);
		
		return new ResponseEntity<String>("the book titled: '" + book.getTitle() + 
				"', written by " + book.getAuthor() 
				+ ", have been saved successfully.", HttpStatus.OK);
	}
	
	@PutMapping("/edit/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<String> edit(@PathVariable Integer id, @RequestBody Book book){
		
		Book bookById = bookService.findById(id);
		
		bookById.setTitle(book.getTitle());
		bookById.setAuthor(book.getAuthor());
		bookById.setPublishedOn(book.getPublishedOn());
		
		bookService.save(bookById);
		
		return new ResponseEntity<String>("the changes made in the book: '" 
		+ bookById.getTitle() + "', have been saved successfully.", HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('USER')")
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
