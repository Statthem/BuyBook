package com.statthem.BuyBook.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.statthem.BuyBook.model.Book;
import com.statthem.BuyBook.service.BookService;

/**
 * Handles requests for the application file upload requests
 */
@Controller
public class FileUploadController {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	private static final Date DEFAULT_DATE = new Date(00000000);
	
	@Autowired
	BookService bookService;
	
	

	@RequestMapping(value = "/UploadPage", method = RequestMethod.GET)
	public String getUploadPage(Model model) {
		
		Book book = new Book();
		book.setImageId("");
		book.setText("");
		//book.setReleaseDate(DEFAULT_DATE);
		model.addAttribute("Book", book);
		model.addAttribute("genreList", new ArrayList<String>() {
			{
				add("Drama");
				add("Comedy");
				add("Adventure");
				add("Horror fiction");
				add("Literary realism");
				add("Romance");
				add("Satire");
				add("Tragedy");
				add("Tragicomedy");
				add("Fantasy");
			}
		});
		return "UploadBook";
	}

	
	
	/**
	 * Upload multiple file using Spring Controller
	 */
	@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
	public @ResponseBody String uploadMultipleFileHandler(@ModelAttribute("Book")Book book,BindingResult result,
			@RequestParam("releaseDate") String releaseDate,
			@RequestParam("file") MultipartFile[] files) {

		
		if(book == null)System.out.println("book == null!!!");
		System.out.println("error count:"+ result.getErrorCount() + "   " + result.toString());
		
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date parsed;
		try {
			parsed = format.parse(releaseDate);
			java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
			book.setReleaseDate(sqlDate);
		} catch (ParseException exc) {
			System.out.println("Parse exception");
		}
		
		System.out.println("book release date:" + book.getReleaseDate());
		
		// Creating the directory to store file
		String rootPath = System.getProperty("catalina.home");

		File imageDir = new File("src/main/webapp/resources/images");
		File bookDir = new File("src/main/webapp/resources/books");
		if (!imageDir.exists())
			imageDir.mkdirs();

		if (!bookDir.exists())
			bookDir.mkdirs();
	
		
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			
			String fileName = FilenameUtils.getBaseName(file.getOriginalFilename());
			String extension = FilenameUtils.getExtension(file.getOriginalFilename());

			System.out.println("file extension: " + extension);
			System.out.println("file name: " + fileName);

			String name = fileName +"."+ extension;
			
			try {
				byte[] bytes = file.getBytes();

				// Create the file on server
				if (extension.contains("jpg")) {
					File serverFile = new File(imageDir.getAbsolutePath() + File.separator + name);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					
					book.setImageId(name);
					stream.write(bytes);
					stream.close();

					logger.info("Server File Location=" + serverFile.getAbsolutePath());

				}

				if (extension.equals("pdf")) {
					File serverFile = new File(bookDir.getAbsolutePath() + File.separator + name);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					
					book.setText(name);
					stream.write(bytes);
					stream.close();

					logger.info("Server File Location=" + serverFile.getAbsolutePath());

				}
				
			} catch (Exception e) {
				System.out.println ("You failed to upload " + name + " => " + e.getMessage());
			}
			
			
		}
		
		bookService.addBook(book);
		
		return "new book uploaded succesfull";
	}
}