package br.com.fiap.atlasmed.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.atlasmed.models.Paciente;
import br.com.fiap.atlasmed.repositories.PacienteRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    PacienteRepository repo;

    @GetMapping
    public List<Paciente> index() {
        log.info("Buscando todos os pacientes...");

        return repo.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Paciente> show(@PathVariable Long id) {
        log.info("Buscando paciente de ID " + id + "...");

        Optional<Paciente> p = repo.findById(id);

        if (p.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(p.get());
    }

    @PostMapping
    public ResponseEntity<EntityModel<Paciente>> create(@RequestBody @Valid Paciente paciente) {
        log.info("Criando o paciente " + paciente + "...");

        repo.save(paciente);

        return ResponseEntity.created(paciente.toModel().getRequiredLink("self").toUri()).body(paciente.toModel());
    }

    @PutMapping("{id}")
    public ResponseEntity<Paciente> update(@PathVariable Long id, @RequestBody @Valid Paciente paciente) {
        log.info("Editando o paciente de ID " + id + "...");

        Optional<Paciente> p = repo.findById(id);

        if (p.isEmpty()) return ResponseEntity.notFound().build();

        BeanUtils.copyProperties(paciente, p.get(), "id");

        repo.save(p.get());

        return ResponseEntity.ok(p.get());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Paciente> destroy(@PathVariable Long id) {
        log.info("Deletando o paciente de ID " + id + "...");

        Paciente paciente = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado!"));

        repo.delete(paciente);

        return ResponseEntity.noContent().build();
    }
}
