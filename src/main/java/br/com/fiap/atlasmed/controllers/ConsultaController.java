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

import br.com.fiap.atlasmed.models.Consulta;
import br.com.fiap.atlasmed.repositories.ConsultaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ConsultaRepository repo;

    @GetMapping
    public List<Consulta> index() {
        log.info("Buscando todas as consultas...");

        return repo.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Consulta> show(@PathVariable Long id) {
        log.info("Buscando categoria com o ID igual a " + id + "...");

        Optional<Consulta> c = repo.findById(id);

        if (c.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(c.get());
    }

    @PostMapping
    public ResponseEntity<EntityModel<Consulta>> create(@RequestBody @Valid Consulta consulta) {
        log.info("Criando a consulta " + consulta + "...");

        repo.save(consulta);

        return ResponseEntity.created(consulta.toModel().getRequiredLink("self").toUri()).body(consulta.toModel());
    }

    @PutMapping("{id}")
    public ResponseEntity<Consulta> update(@PathVariable Long id, @RequestBody @Valid Consulta consulta) {
        log.info("Editando a consulta de ID " + id + "...");

        Optional<Consulta> c = repo.findById(id);

        if (c.isEmpty()) return ResponseEntity.notFound().build();

        BeanUtils.copyProperties(consulta, c.get(), "id");

        repo.save(c.get());

        return ResponseEntity.ok(c.get());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Consulta> destroy(@PathVariable Long id) {
        log.info("Deletando a consulta de ID " + id + "...");

        Consulta consulta = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Consulta não encontrada!"));

        repo.delete(consulta);

        return ResponseEntity.noContent().build();
    }
}
