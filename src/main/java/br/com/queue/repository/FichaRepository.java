package br.com.queue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.queue.model.Ficha;

@Repository
public interface FichaRepository extends JpaRepository<Ficha, Long> {

}
