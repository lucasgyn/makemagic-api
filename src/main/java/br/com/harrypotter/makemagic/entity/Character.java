package br.com.harrypotter.makemagic.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Entity representing the character of Harry Potter
 * 
 * @author Lucas de Oliveira
 * @since 14/09/2020
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Character implements Serializable {

	private static final long serialVersionUID = 4231040409939206266L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long id;

	@Column(unique = true, nullable = false)
	@NotBlank(message = "Enter the name of the character")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String name;

	@Column(nullable = false)
	@NotBlank(message = "Enter the role of the character")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String role;

	@Column(nullable = false)
	@NotBlank(message = "Enter the school of the character")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String school;

	@Column(nullable = false)
	@NotBlank(message = "Enter the house of the character")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String house;

	@Column(nullable = false)
	@NotBlank(message = "Enter the patronus of the character")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String patronus;
}