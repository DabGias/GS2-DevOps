package br.com.fiap.atlasmed.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.atlasmed.models.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {}
