package br.com.queue.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
		
		mv.addObject("cadastra", 1);
		mv.addObject("voltarDash", 0);
		mv.addObject("action", 1);
		mv.addObject("grupos", grupos);
		mv.addObject("usuario", usuario);
		
		return mv;
	}
	
	@PostMapping("/salvaUsuario")
	public ModelAndView salvaUsuario(Long voltarDash, Long action, @Valid Usuario usuario, BindingResult result) {
		ModelAndView mvError = new ModelAndView(CADASTRO_USUARIOS);
		
		//Verificando se o usuário está colocando um username que já existe (sem ser o dele)
		Usuario verifica = usuarioService.findByLogin(usuario.getLogin());
		
		//Cadastrando
		if(verifica != null && verifica.getLogin().equals(usuario.getLogin())) {
			if(!(verifica.getId() == usuario.getId())) {
				mvError.addObject("existente", 1);
				
				List<Grupo> grupos = grupoService.findAll();
				
				mvError.addObject("grupos", grupos);
				mvError.addObject("action", 0);
				mvError.addObject("voltarDash", 1);
				
				return mvError;
			}
		}
		//Verificando se o usuário está colocando um username que já existe (sem ser o dele)
		
		if(result.hasErrors()) {
			if(voltarDash == 1) {
				return new ModelAndView("/dashboard");	//Se tiver erro quando tiver alterando a propria senha, volta pro dash
			}
			
			mvError.addObject("grupos", grupoService.list());
			mvError.addObject("usuario", usuario);
			
			return mvError;
		}
		
		if(action == 1) {
			String senha = new BCryptPasswordEncoder().encode(usuario.getSenha());
			usuario.setSenha(senha);
		
			usuarioService.save(usuario);
			
			if(voltarDash == 1) {
				return new ModelAndView("/dashboard");	//Se tiver alterando a propria senha, nao faz sentido ir pra lista de usuários
			}
		} else {
			usuarioService.save(usuario);
		}
		
		ModelAndView mv = new ModelAndView(LISTA_USUARIOS);
		
		mv.addObject("usuarios", usuarioService.list());
		
		if(action == 3) {
			return new ModelAndView("/login");
		}
		
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
		
		mv.addObject("action", 0);
		mv.addObject("usuario", usuario);
		mv.addObject("grupos", grupoService.list());
		
		return mv;
	}
	
	@GetMapping("/alterarDados")
	public ModelAndView alterarDados(Usuario usuario) {
		ModelAndView mv = new ModelAndView(CADASTRO_USUARIOS);
		
		String authUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		usuario = usuarioService.findByLogin(authUser);
		List<Grupo> grupos = grupoService.findAll();
		
		mv.addObject("grupos", grupos);
		mv.addObject("usuario", usuario);
		mv.addObject("username", authUser);
		mv.addObject("action", 0);
		mv.addObject("tirarGrupos", 1);
		
		return mv;
	}
	
	@GetMapping("/alterarSenha")
	public ModelAndView alterarSenha(Usuario usuario) {
		ModelAndView mv = new ModelAndView("usuarios/alterarSenha");
		
		String authUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		usuario = usuarioService.findByLogin(authUser);
		
		mv.addObject("usuario", usuario);
		
		return mv;
	}
}
