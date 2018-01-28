package br.com.queue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.queue.model.Ficha;
import br.com.queue.service.FichaService;

@Controller
public class FichaController {
	@Autowired
	private FichaService fichaService;
	
	@PostMapping("/entrarFila")
	public ModelAndView entrarFila(Ficha ficha) {
		ModelAndView mv = new ModelAndView("/dashboard");
		
		fichaService.save(ficha);
		
		return mv;
	}
}
