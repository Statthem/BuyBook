package com.statthem.BuyBook.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.*;
import javax.validation.constraints.NotNull;


import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.impl.NotNullValidator;

import com.statthem.BuyBook.validation.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="Users")
@PasswordMatches
@Getter @Setter
public class User implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7535410678182778293L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id", nullable=false, unique=true)
	private long id;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="user_email")
	@ValidEmail
	private String userEmail;
	
	@Column(name="user_password")
	private String userPassword;
	
	@Column(name="role_id")
	private int roleId;
	
	@Transient
	private String matchingPassword;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(
	        name = "users_books", 
	        joinColumns = { @JoinColumn(name = "user_id") }, 
	        inverseJoinColumns = { @JoinColumn(name = "book_id") }
	    )
	private Set<Book> favoriteBooks;


	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", userEmail=" + userEmail + ", userPassword="
				+ userPassword + ", roleId=" + roleId + ", matchingPassword=" + matchingPassword + "]";
	}

	

	//Getters and Setters provided by LomBok
	
}
