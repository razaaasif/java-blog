package com.raza.blog.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column
	private String title;

	@Lob
	@Column
	@NotEmpty
	private String content;
	@Column
	@CreationTimestamp
	private Date createdOn;
	@Column
	@UpdateTimestamp
	private Date updatedOn;
	
	@Column
	@NotBlank
	private String username;

}
