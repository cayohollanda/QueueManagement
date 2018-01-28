package br.com.queue.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.queue.model.Grupo;
import br.com.queue.model.Usuario;
import br.com.queue.service.GrupoService;
import br.com.queue.service.UsuarioService;

@Controller
public class UsuarioController {
	private String CADASTRO_USUARIOS = "usuarios/cadastroUsuarios";
	private String LISTA_USUARIOS = "usuarios/listaUsuarios";
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private GrupoService grupoService;
	
	@RequestMapping("/cadastroUsuarios")
	public ModelAndView cadastroUsuarios(Usuario usuario) {
		ModelAndView mv = new ModelAndView(CADASTRO_USUARIOS);
		
		List<Grupo> grupos = grupoService.list();
		
		mv.addObject("grupos", grupos);
		mv.addObject("usuario", usuario);
		
		
		return mv;
	}
	
	@PostMapping("/salvaUsuario")
	public ModelAndView salvaUsuario(@Valid Usuario usuario, BindingResult result) {
		ModelAndView mvError = new ModelAndView(CADASTRO_USUARIOS);
		
		if(result.hasErrors()) {
			mvError.addObject("grupos", grupoService.list());
			mvError.addObject("usuario", usuario);
			
			return mvError;
		}
		
		usuarioService.save(usuario);
		
		ModelAndView mv = new ModelAndView(LISTA_USUARIOS);
		
		mv.addObject("usuarios", usuarioService.list());
		
		return mv;
	}
	
	@RequestMapping("/listaUsuarios")
	public ModelAndView listaUsuarios() {
		ModelAndView mv = new ModelAndView(LISTA_USUARIOS);
		
		List<Usuario> usuarios = usuarioService.list();
		
		mv.addObject("usuarios", usuarios);
		
		return mv;
	}
	
	@GetMapping("/deletaUsuario/{id}")
	public ModelAndView deletaUsuario(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView(LISTA_USUARIOS);
		
		usuarioService.delete(id);
		
		mv.addObject("usuarios", usuarioService.list());
		
		return mv;
	}
	
	@GetMapping("/editaUsuario/{id}")
	public ModelAndView editaUsuario(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView(CADASTRO_USUARIOS);
		
		Usuario usuario = usuarioService.search(id);
		
		mv.addObject("usuario", usuario);
		mv.addObject("grupos", grupoService.list());
		
		return mv;
	}
}
