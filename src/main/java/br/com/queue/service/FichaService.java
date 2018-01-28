package br.com.queue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.queue.model.Ficha;
import br.com.queue.repository.FichaRepository;

@Service
public class FichaService {
	@Autowired
	private FichaRepository repository;
	
	public List<Ficha> list() {
		return this.repository.findAll();
	}
	
	public void save(Ficha ficha) {
		this.repository.save(ficha);
	}
}
