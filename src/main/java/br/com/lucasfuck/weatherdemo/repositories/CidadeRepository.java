package br.com.lucasfuck.weatherdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lucasfuck.weatherdemo.entities.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
