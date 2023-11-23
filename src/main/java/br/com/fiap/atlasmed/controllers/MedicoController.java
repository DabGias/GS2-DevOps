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

import br.com.fiap.atlasmed.models.Medico;
import br.com.fiap.atlasmed.repositories.MedicoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    MedicoRepository repo;

    @GetMapping
    public List<Medico> index() {
        log.info("Buscando todos os médicos...");

        return repo.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Medico> show(@PathVariable Long id) {
        log.info("Buscando o médico de ID " + id + "...");

        Optional<Medico> m = repo.findById(id);

        if (m.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(m.get());
    }

    @PostMapping
    public ResponseEntity<EntityModel<Medico>> create(@RequestBody @Valid Medico medico) {
        log.info("Criando o médico " + medico + "...");

        repo.save(medico);

        return ResponseEntity.created(medico.toModel().getRequiredLink("self").toUri()).body(medico.toModel());
    }

    @PutMapping("{id}")
    public ResponseEntity<Medico> update(@PathVariable Long id, @RequestBody @Valid Medico medico) {
        log.info("Editando o médico de ID " + id + "...");

        Optional<Medico> m = repo.findById(id);

        if (m.isEmpty()) return ResponseEntity.notFound().build();

        BeanUtils.copyProperties(medico, m.get(), "id");

        repo.save(m.get());

        return ResponseEntity.ok(m.get());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Medico> destroy(@PathVariable Long id) {
        log.info("Deletando o médico de ID " + id + "...");

        Medico medico = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico não encontrado!"));

        repo.delete(medico);

        return ResponseEntity.noContent().build();
    }
}
