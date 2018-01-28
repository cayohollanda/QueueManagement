package br.com.queue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.queue.model.Grupo;
import br.com.queue.model.Permissao;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

	List<Permissao> findByGruposIn(Grupo grupo);

}
