package com.statthem.BuyBook.controller.bookcontroller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.servlet.ModelAndView;

import com.statthem.BuyBook.model.Book;
import com.statthem.BuyBook.path.FolderPaths;
import com.statthem.BuyBook.service.BookService;

/**
 * Handles requests for the application file upload requests
 */
@Controller
public class BookUploadController {

	private static final Logger logger = LoggerFactory.getLogger(BookUploadController.class);
	
	@Autowired
	BookService bookService;
	

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String getUploadPage(Model model) {

		Book book = new Book();
		book.setImageId("");
		book.setText("");
		// book.setReleaseDate(DEFAULT_DATE);
		model.addAttribute("Book", book);
		model.addAttribute("genreList", BookService.GENRES);
		return "UploadPage";
	}

	/**
	 * Upload multiple file using Spring Controller
	 */
	@RequestMapping(value = "/uploadBook", method = RequestMethod.POST)
	public ModelAndView uploadMultipleFileHandler(@ModelAttribute("Book") @Valid Book book,BindingResult result, Model model,
			@RequestParam("releaseDate") String releaseDate,
			@RequestParam("file") MultipartFile[] files) {

		//Checking book fields and files
		List<String> checkedErrorList = checkBook(book,files,releaseDate);
		
		checkedErrorList.forEach((error) -> logger.debug("error: " + error));
		
		if(!checkedErrorList.isEmpty()){
			return new ModelAndView("UploadPage","errorsList",checkedErrorList);
		}


		// Creating directories to store files
		File backUpImageDir = new File(FolderPaths.BACKUP_IMAGE_FOLDER.getPath());
		File backUpBookDir = new File(FolderPaths.BACKUP_BOOK_FOLDER.getPath());

		File imageDir = new File(FolderPaths.IMAGE_FOLDER.getPath());
		File bookDir = new File(FolderPaths.BOOK_FOLDER.getPath());

		if (!imageDir.exists())
			imageDir.mkdirs();

		if (!bookDir.exists())
			bookDir.mkdirs();


		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];

			String fileName = FilenameUtils.getBaseName(file.getOriginalFilename());
			String extension = FilenameUtils.getExtension(file.getOriginalFilename());

			String name = fileName +"."+ extension;

			try {
				byte[] bytes = file.getBytes();

				// Create 2 files on server
				if (extension.contains("jpg")) {
					//main file
					File serverFile = new File(imageDir.getAbsolutePath() + File.separator + name);
					//backUp file
					File backUpServerFile = new File(backUpImageDir.getAbsolutePath() + File.separator + name);

					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					BufferedOutputStream backUpStream = new BufferedOutputStream(new FileOutputStream(backUpServerFile));

					book.setImageId(name);
					
					stream.write(bytes);
					stream.close();
					backUpStream.write(bytes);
					backUpStream.close();

					logger.info("Server File Location=" + serverFile.getAbsolutePath());

				}

				if (extension.contains("pdf")) {
					File serverFile = new File(bookDir.getAbsolutePath() + File.separator + name);
					File backUpServerFile = new File(backUpBookDir.getAbsolutePath() + File.separator + name);

					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					BufferedOutputStream backUpStream = new BufferedOutputStream(new FileOutputStream(backUpServerFile));

					book.setText(name);
					
					stream.write(bytes);
					stream.close();
					backUpStream.write(bytes);
					backUpStream.close();

					logger.info("Server File Location=" + serverFile.getAbsolutePath());

				}

			} catch (Exception e) {
				logger.debug("You failed to upload " + name + " => " + e.getMessage());
			}


		}

		// adding book to data base
		bookService.addBook(book);
		
		ModelAndView  modelAndView  =  new ModelAndView();
		modelAndView.setViewName("MainPage");
		model.addAttribute("newBook",book.getBookName());
		return modelAndView;
	}
	
	
	private List<String> checkBook(Book book,MultipartFile[] files,String releaseDate){
		List<String> errorList = new ArrayList<>();
		
		//if no pdf file
		if(Arrays.asList(files).stream().noneMatch(file -> FilenameUtils.getName(((MultipartFile) file).getOriginalFilename()).contains("pdf"))) {
			errorList.add("You must provide book PDF file");
		}
		
		if(releaseDate == null || releaseDate.length()==0 ||
		   book.getBookName() == null || book.getBookName().length()==0 ||
		   book.getBookDescription() == null || book.getBookDescription().length()==0 ||
           book.getBookAuthor()== null || book.getBookAuthor().length()==0 ||
           book.getBookGenre()== null || book.getBookGenre().length()==0)
        	{ 
			errorList.add("all fields with *  are required");
		    }
		
		//formating releaseDate
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date parsed;
		try {
			parsed = format.parse(releaseDate);
			java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
			book.setReleaseDate(sqlDate);
		} catch (ParseException exc) {
			errorList.add("wrong release date");
		}
		
		
		return errorList;
		
	}

	
}