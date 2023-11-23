package br.com.fiap.atlasmed.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.atlasmed.models.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {}
