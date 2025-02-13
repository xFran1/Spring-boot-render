package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AnuncioController {
	
	@Autowired
	private AnuncioServicio servicio;
	
	@GetMapping({ "/anuncios", "/" })
    public String listarTrabajadores(Model modelo) {
        modelo.addAttribute("anuncios", servicio.listarAnuncio());
        return "anuncio"; // nos retorna al archivo trabajadores
    }
	
	@GetMapping("/anuncios/anunciocrear")
    public String formulario(Model modelo) {
        Anuncio anuncio = new Anuncio();
        modelo.addAttribute("anuncio", anuncio);
        return "anunciocrear";
    }
	
	  @PostMapping("/anuncios")
	    public String guardarAnuncio(@ModelAttribute("anuncio") Anuncio anuncio) {
	        servicio.guardarAnuncio(anuncio);
	        return "redirect:/anuncios";
	    }

	  @GetMapping("/anuncios/anuncioeditar/{id}")
	    public String formularioEditar(@PathVariable Integer id, Model modelo) {
	        modelo.addAttribute("anuncio", servicio.obtenerAnuncio(id));
	        return "anuncioeditar";
	    }
	  @PostMapping("/anuncios/{id}")
	    public String actualizar(@PathVariable Integer id, @ModelAttribute("anuncio") Anuncio anuncio,
	            Model modelo) {
	        Anuncio actualizarAnuncio = servicio.obtenerAnuncio(id);
	        actualizarAnuncio.setId(id);
	        actualizarAnuncio.setNombre(anuncio.getNombre());
	        actualizarAnuncio.setAsunto(anuncio.getAsunto());
	        actualizarAnuncio.setDescripcion(anuncio.getDescripcion());
	        servicio.actualizarAnuncio(actualizarAnuncio);
	        return "redirect:/anuncios";
	    }
	  
	  @GetMapping("/anuncios/{id}")
	    public String eliminar(@PathVariable Integer id) {
	        servicio.borrarAnuncio(id);
	        return "redirect:/anuncios";
	    }
	  
	  @GetMapping("/anuncios/anunciodetalles/{id}")
	    public String findById(Model model, @PathVariable Integer id) {
	        // Recuperamos todos los anuncios.
	        model.addAttribute("anuncio", servicio.obtenerAnuncio(id));
	        return "anunciodetalles";
	    }

}
