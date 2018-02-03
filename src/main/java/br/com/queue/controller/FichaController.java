package br.com.queue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.queue.model.Ficha;
import br.com.queue.service.EmpresaService;
import br.com.queue.service.FichaService;

@Controller
public class FichaController {
	@Autowired
	private FichaService fichaService;
	
	@Autowired
	private EmpresaService empresaService;
	
	@RequestMapping("/entrarFila")
	public ModelAndView entrarFila(@RequestParam("empresa") Long empresa, @RequestParam("usuario") String usuario) {
		ModelAndView mv = new ModelAndView("redirect:/mostrarEmpresa/" + empresa);
		
		Ficha ficha = new Ficha();
		ficha.setEmpresa(empresa);
		ficha.setUsuario(usuario);
		
		fichaService.save(ficha);
		
		mv.addObject("new", "1");
		
		return mv;
	}
	
	@GetMapping("/zerarFila/{id}")
	public ModelAndView zerarFila(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("redirect:/mostrarEmpresa/" + id);
		
		fichaService.deleteByEmpresa(id);
		
		mv.addObject("zerado", "1");
		mv.addObject("empresa", empresaService.findOne(id));
		
		return mv;
	}
	
	@PostMapping("/sairFila")
	public ModelAndView sairFila(String username, Long idEmpresa) {
		ModelAndView mv = new ModelAndView("redirect:/mostrarEmpresa/" + idEmpresa);
		
		List<Ficha> fichas = fichaService.findAllByOrderByIdDesc();
		
		Ficha ficha = fichas.get(0);
		
		try {
			fichaService.delete(ficha.getId());
		} catch(Exception ex) {
			//quando não retornou nada lá na lista
		}
		
		mv.addObject("saiuFila", "1");
		mv.addObject("empresa", empresaService.findOne(idEmpresa));
		
		return mv;
	}
}
