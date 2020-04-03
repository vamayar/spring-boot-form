package com.bolsasideas.springboot.form.app.controllers;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsasideas.springboot.form.app.editors.NombreMayusculaEditors;
import com.bolsasideas.springboot.form.app.models.domain.Pais;
import com.bolsasideas.springboot.form.app.models.domain.Usuario;
import com.bolsasideas.springboot.form.app.validation.UsuarioValidator;

@Controller
@SessionAttributes("usuario")
public class FormController {
	
	@Autowired
	private UsuarioValidator validador;
	
	@InitBinder// este metodo y anotacion se hace en caso de que no se quiere inicializar el validador de forma manual
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validador);
		
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "fechaNacimiento", new CustomDateEditor(dateFormat, false));  //para formatear informacion que viene de la vista
		//binder.registerCustomEditor(String.class, new NombreMayusculaEditors()); //cambia todos los campos
		binder.registerCustomEditor(String.class, "nombre",new NombreMayusculaEditors());  //cambia solo el campo indicado
	}

	@ModelAttribute("paises")
	public List<String> paises(){
		return Arrays.asList("Mexico", "Chile", "Noruega", "Argentina", "Perú");
	}
	
	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises(){
		return Arrays.asList(
				new Pais(1,"MX","Mexico"), 
				new Pais(2,"CH","Chile"), 
				new Pais(3,"NR","Noruega"), 
				new Pais(4,"AR","Argentina"), 
				new Pais(5,"PR","Perú"));
	}
	
	@ModelAttribute("paisesMap")
	public Map<String, String> paisesMap(){
		Map<String,String > paises = new HashMap<>();
		paises.put("MX", "Mexico");
		paises.put("CH", "Chile");
		paises.put("NR", "Noruega");
		paises.put("AR", "Argentina");
		paises.put("PR", "Perú");
		return paises;
	}
	
	@GetMapping("/form")
	public String form(Model model) {
		Usuario usuario = Usuario.builder().nombre("Jhon").apellido("Doe").identificador("125.231.421-K").build();
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "Formulario usuarios");
		return "form";
	}

	@PostMapping("/form")
	public String procesaFormulario(
			@Valid Usuario usuario, 
			BindingResult result,
			Model model, 
			//@RequestParam(name = "username") String username, //se puede especificar el nombre del campo en caso de que no se llame igual
			//@RequestParam String password,
			//@RequestParam String email
			SessionStatus status
			) {
		
		//Usuario usuario = Usuario.builder().username(username).email(email).password(password).build();
		
		
		//validador.validate(usuario, result);
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
		status.setComplete();
		return "resultado";
	}
}

