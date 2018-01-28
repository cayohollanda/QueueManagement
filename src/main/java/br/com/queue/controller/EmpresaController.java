package br.com.queue.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.queue.model.Empresa;
import br.com.queue.model.Ficha;
import br.com.queue.service.EmpresaService;
import br.com.queue.service.FichaService;

@Controller
public class EmpresaController {
	private String CADASTRO_EMPRESA = "empresas/cadastroEmpresas";
	private String LISTA_EMPRESAS = "empresas/listaEmpresas";
	private String MOSTRA_EMPRESA = "empresas/mostraEmpresa";
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private FichaService fichaService;
	
	@RequestMapping("/cadastroEmpresa")
	public ModelAndView cadastro(Empresa empresa) {
		ModelAndView mv = new ModelAndView(CADASTRO_EMPRESA);
		
		mv.addObject("empresa", empresa);
		
		return mv;
	}
	
	@PostMapping("/salvarEmpresa")
	public ModelAndView salva(@Valid Empresa empresa, BindingResult result) {
		ModelAndView mv = new ModelAndView(CADASTRO_EMPRESA);
		
		if(result.hasErrors()) {
			return mv;
		}
		
		empresaService.save(empresa);
		
		mv.setViewName(LISTA_EMPRESAS);
		
		mv.addObject("empresas", empresaService.list());
		
		return mv;
	}
	
	@RequestMapping("/listaEmpresas")
	public ModelAndView lista() {
		ModelAndView mv = new ModelAndView(LISTA_EMPRESAS);
		
		mv.addObject("empresas", empresaService.list());
		
		return mv;
	}
	
	@PostMapping("/pesquisarEmpresa")
	public ModelAndView list(String nome) {
		ModelAndView mv = new ModelAndView(LISTA_EMPRESAS);
		
		List<Empresa> empresas = empresaService.findByNomeStartingWith(nome);
		
		mv.addObject("empresas", empresas);
		
		return mv;
	}
	
	@GetMapping("/excluirEmpresa/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView(LISTA_EMPRESAS);
		
		Empresa empresa = empresaService.findOne(id);
		
		if(empresa == null) {
			return new ModelAndView("/dashboard");
		}
		
		empresaService.delete(id);
		
		List<Empresa> empresas = empresaService.list();
		
		mv.addObject("empresas", empresas);
		
		return mv;
	}
	
	@GetMapping("/editarEmpresa/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView(CADASTRO_EMPRESA);
		
		Empresa empresa = empresaService.findOne(id);
		
		mv.addObject("empresa", empresa);
		
		return mv;
	}
	
	@GetMapping("/mostrarEmpresa/{id}")
	public ModelAndView mostrarEmpresa(@PathVariable("id") Long id, Ficha ficha) {
		ModelAndView mv = new ModelAndView(MOSTRA_EMPRESA);
		
		Empresa empresa = empresaService.findOne(id);
		
		String auth = SecurityContextHolder.getContext().getAuthentication().getName();
		
		List<Ficha> fichas = fichaService.list(); 
		
		mv.addObject("fichas", fichas);
		mv.addObject("ficha", ficha);
		mv.addObject("usuario", auth);
		mv.addObject("empresa", empresa);
		
		return mv;
	}
}
