package br.com.fiap.atlasmed.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.atlasmed.models.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {}
