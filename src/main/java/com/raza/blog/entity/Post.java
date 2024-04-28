package com.raza.blog.entity;

import lombok.*;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
	private Instant createdOn;
	@Column
	private Instant updatedOn;
	
	@Column
	@NotBlank
	private String username;

}
