package com.bolsasideas.springboot.form.app.models.domain;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pais {
	
	@NotNull
	private Integer id;
	private String codigo, nombre;
	
	
}
