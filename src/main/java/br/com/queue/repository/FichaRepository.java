package br.com.queue.repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.queue.model.Ficha;

@Repository
public interface FichaRepository extends JpaRepository<Ficha, Long> {
	Ficha findByEmpresaIs(Long id);
	
	@Modifying
	@Transactional
	@Query(value="delete from Ficha f where f.empresa= ?1")
	void deleteByEmpresa(Long empresa);
	
	List<Ficha> findAllByOrderByIdDesc();
}
