package br.com.queue.service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
	
	public Ficha findByEmpresaIs(Long id) {
		return this.repository.findByEmpresaIs(id);
	}
	
	public void deleteByEmpresa(Long id) {
		this.repository.deleteByEmpresa(id);
	}
	
	public List<Ficha> findAllByOrderByIdDesc() {
		return this.repository.findAllByOrderByIdDesc();
	}
	
	public void delete(Long id) {
		this.repository.delete(id);
	}
}
