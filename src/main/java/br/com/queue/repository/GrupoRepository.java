package br.com.queue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.queue.model.Grupo;
import br.com.queue.model.Usuario;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {

	List<Grupo> findByUsuariosIn(Usuario usuario);

}
