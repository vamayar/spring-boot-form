package com.bolsasideas.springboot.form.app.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bolsasideas.springboot.form.app.models.domain.Usuario;

@Controller
public class FormController {

	@GetMapping("/form")
	public String form(Model model) {
		Usuario usuario = Usuario.builder().nombre("Jhon").apellido("Doe").identificador("12345.231.21-k").build();
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "Formulario usuarios");
		return "form";
	}

	@PostMapping("/form")
	public String procesaFormulario(
			@Valid Usuario usuario, 
			BindingResult result,
			Model model 
			//@RequestParam(name = "username") String username, //se puede especificar el nombre del campo en caso de que no se llame igual
			//@RequestParam String password,
			//@RequestParam String email
			) {
		
		//Usuario usuario = Usuario.builder().username(username).email(email).password(password).build();
		
		model.addAttribute("titulo", "Resultado form");
		
		if(result.hasErrors()) {
			
			/*Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(),"El campo: ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			});
			
			model.addAttribute("error", errores);*/
			return "form";
		}
		
		model.addAttribute("usuario", usuario);
		return "resultado";
	}
}

