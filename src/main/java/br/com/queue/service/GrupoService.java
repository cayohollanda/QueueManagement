package br.com.queue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.queue.model.Grupo;
import br.com.queue.model.Usuario;
import br.com.queue.repository.GrupoRepository;

@Service
public class GrupoService {

	@Autowired
	private GrupoRepository repository;
	
	public List<Grupo> findByUsuariosIn(Usuario usuario) {
		return this.repository.findByUsuariosIn(usuario);
	}
	
	public List<Grupo> list() {
		return this.repository.findAll();
	}
}
