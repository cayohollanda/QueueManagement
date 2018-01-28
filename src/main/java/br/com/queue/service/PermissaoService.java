package br.com.queue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.queue.model.Grupo;
import br.com.queue.model.Permissao;
import br.com.queue.repository.PermissaoRepository;

@Service
public class PermissaoService {
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	public List<Permissao> findByGruposIn(Grupo grupo) {
		return this.permissaoRepository.findByGruposIn(grupo);
	}

}
