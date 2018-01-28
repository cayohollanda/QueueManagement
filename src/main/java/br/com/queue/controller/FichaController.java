package br.com.queue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.queue.model.Ficha;
import br.com.queue.service.FichaService;

@Controller
public class FichaController {
	@Autowired
	private FichaService fichaService;
	
	@RequestMapping("/entrarFila")
	public ModelAndView entrarFila(@RequestParam("empresa") Long empresa, @RequestParam("usuario") String usuario) {
		ModelAndView mv = new ModelAndView("/dashboard");
		
		Ficha ficha = new Ficha();
		ficha.setEmpresa(empresa);
		ficha.setUsuario(usuario);
		
		fichaService.save(ficha);
		
		return mv;
	}
}
