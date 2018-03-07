package com.statthem.BuyBook.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.swing.text.html.ImageView;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Books")
@Getter @Setter
public class Book implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4425091974069183462L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="book_id", nullable=false, unique=true)
	private long id;
	
	@Column(name="book_name")
	@NotNull
	@NotEmpty
	private String bookName;
	
	@Column(name="book_description")
	@NotNull
	@NotEmpty
	private String bookDescription;
	
	@Column(name="book_author")
	@NotNull
	@NotEmpty
	private String bookAuthor;
	
	@Column(name="book_genre")
	@NotNull
	@NotEmpty
	private String bookGenre;
	
	@Column(name="book_rating")
	private int bookRating;
	
	@Column(name="text")
	private String text;
	
	@Column(name="release_date")
	private Date releaseDate;
	
	@Column(name="image")
	private String imageId;
	
	
	@ManyToMany(mappedBy = "favoriteBooks",fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	private Set<User> usersInFavourite;
	
	@Transient
	private String HtmlDate;
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", book_name=" + bookName + ", book_description=" + bookDescription
				+ ", book_author=" + bookAuthor + ", book_genre=" + bookGenre + ", book_rating=" + bookRating
				+ ", text=" + text + ", imageId= " + imageId + "]";
	}


	//without id calculating
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookAuthor == null) ? 0 : bookAuthor.hashCode());
		result = prime * result + ((bookDescription == null) ? 0 : bookDescription.hashCode());
		result = prime * result + ((bookGenre == null) ? 0 : bookGenre.hashCode());
		result = prime * result + ((bookName == null) ? 0 : bookName.hashCode());
		result = prime * result + bookRating;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	//without id checking
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (bookAuthor == null) {
			if (other.bookAuthor != null)
				return false;
		} else if (!bookAuthor.equals(other.bookAuthor))
			return false;
		if (bookDescription == null) {
			if (other.bookDescription != null)
				return false;
		} else if (!bookDescription.equals(other.bookDescription))
			return false;
		if (bookGenre == null) {
			if (other.bookGenre != null)
				return false;
		} else if (!bookGenre.equals(other.bookGenre))
			return false;
		if (bookName == null) {
			if (other.bookName != null)
				return false;
		} else if (!bookName.equals(other.bookName))
			return false;
		if (bookRating != other.bookRating)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}
	
	//Getters and Setters provided by LomBok

}
