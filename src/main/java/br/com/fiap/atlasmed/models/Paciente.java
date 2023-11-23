package br.com.fiap.atlasmed.models;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;

import br.com.fiap.atlasmed.controllers.PacienteController;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(
    name = "tb_paciente",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_cpf_paciente",
            columnNames = "cpf_paciente"
        ),
        @UniqueConstraint(
            name = "uk_email_paciente",
            columnNames = "email_paciente"
        )
    }
)
public class Paciente {

    @Id
    @GeneratedValue(
        generator = "seq_paciente",
        strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
        allocationSize = 1,
        name = "seq_paciente",
        sequenceName = "seq_paciente"
    )
    @Column(name = "id_paciente")
    private Long id;

    @Pattern(regexp = "^[A-Z][a-z].* [A-Z][a-z]+$")
    @Column(name = "nome_paciente")
    private String nome;

    @Pattern(regexp = "^\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}$")
    @Column(name = "cpf_paciente")
    private String cpf;

    @Email
    @Column(name = "email_paciente")
    private String email;

    public Paciente() {}

    public Paciente(Long id, @Pattern(regexp = "^[A-Z][a-z].* [A-Z][a-z]+$") String nome, @Pattern(regexp = "^\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}$") String cpf, @Email String email) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public Paciente(@Pattern(regexp = "^[A-Z][a-z].* [A-Z][a-z]+$") String nome, @Pattern(regexp = "^\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}$") String cpf, @Email String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public EntityModel<Paciente> toModel() {
        return EntityModel.of(
            this,
            linkTo(methodOn(PacienteController.class).show(id)).withSelfRel(),
            linkTo(methodOn(PacienteController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(PacienteController.class).index()).withRel("listAll")
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Paciente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", email=" + email + "]";
    }
}
