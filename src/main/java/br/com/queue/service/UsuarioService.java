package br.com.queue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.queue.model.Usuario;
import br.com.queue.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;
	
	public List<Usuario> list() {
		return this.repository.findAll();
	}
	
	public Usuario search(Long id) {
		return this.repository.findOne(id);
	}
	
	public Usuario findByLogin(String login) {
		return this.repository.findByLogin(login);
	}
	
	public void save(Usuario usuario) {
		String senha = new BCryptPasswordEncoder().encode(usuario.getSenha());
		usuario.setSenha(senha);
		this.repository.save(usuario);
	}
	
	public void delete(Long id) {
		this.repository.delete(id);
	}
}
