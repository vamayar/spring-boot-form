package com.bolsasideas.springboot.form.app.models.domain;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.bolsasideas.springboot.form.app.validation.IdentificadorRegex;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
	
	//@Pattern(regexp = "[0-9]{2}[.][\\d]{3}[.][\\d]{3}[.][-][A-Z]{1}")
	@IdentificadorRegex
	private String identificador;
	
	@NotBlank
	@Size(min=3 , max=8)
	private String username;
	
	@NotEmpty
	private String password;
	
	@NotEmpty
	@Email(message = "debe ser un correo valido")
	private String email;
	
	//@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String apellido;
	
	@NotNull //es para objetos, notempty es para strings
	@Min(5)
	@Max(5000)
	private Integer cuenta;
	
	@NotNull
	//@Past // valida que sea una fecha en el pasado
	@Future // valida que sea una fecha en el futuro
	//@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fechaNacimiento;
	
	
	@Valid  //indica que se validen los campos relacionados
	private Pais pais;
}
