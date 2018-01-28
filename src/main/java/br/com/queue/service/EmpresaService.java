package br.com.queue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.queue.model.Empresa;
import br.com.queue.repository.EmpresaRepository;

@Service
public class EmpresaService {
	@Autowired
	private EmpresaRepository repository;
	
	public void save(Empresa empresa) {
		this.repository.save(empresa);
	}
	
	public List<Empresa> list() {
		return this.repository.findAll();
	}
	
	public List<Empresa> findByNomeStartingWith(String nome) {
		return this.repository.findByNomeStartingWith(nome);
	}
	
	public Empresa findOne(Long id) {
		return this.repository.findOne(id);
	}
	
	public void delete(Long id) {
		this.repository.delete(id);
	}
}