package com.bolsasideas.springboot.form.app.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bolsasideas.springboot.form.app.models.domain.Usuario;

@Component
public class UsuarioValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		//Usuario usuario = (Usuario)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty.usuario.nombre");//se pone el nombre de la propiedad a validar y NotEmpty.usuario.nombre viene del properties
		
		/*if(!usuario.getIdentificador().matches("[0-9]{2}[.][\\d]{3}[.][\\d]{3}[.][-][A-Z]{1}")) {
			errors.rejectValue("identificador", "pattern.usuario.identificador");
		}*/

	}

}
