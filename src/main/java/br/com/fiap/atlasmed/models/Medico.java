package br.com.fiap.atlasmed.models;

import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import br.com.fiap.atlasmed.controllers.MedicoController;
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
    name = "tb_medico",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_cpf_medico",
            columnNames = "cpf_medico"
        ),
        @UniqueConstraint(
            name = "uk_email_medico",
            columnNames = "email_medico"
        ),
        @UniqueConstraint(
            name = "uk_crm_medico",
            columnNames = "crm_medico"
        )
    }
)
public class Medico {

    @Id
    @GeneratedValue(
        generator = "seq_medico",
        strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
        allocationSize = 1,
        name = "seq_medico",
        sequenceName = "seq_medico"
    )
    @Column(name = "id_medico")
    private Long id;

    @Pattern(regexp = "^[A-Z][a-z].* [A-Z][a-z]+$")
    @Column(name = "nome_medico")
    private String nome;

    @Email
    @Column(name = "email_medico")
    private String email;

    @Pattern(regexp = "^\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}$")
    @Column(name = "cpf_medico")
    private String cpf;

    @Column(name = "crm_medico")
    private String crm;

    public Medico() {}

    public Medico(Long id, @Pattern(regexp = "^[A-Z][a-z].* [A-Z][a-z]+$") String nome, @Email String email, @Pattern(regexp = "^\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}$") String cpf, String crm) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.crm = crm;
    }

    public Medico(@Pattern(regexp = "^[A-Z][a-z].* [A-Z][a-z]+$") String nome, @Email String email, @Pattern(regexp = "^\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}$") String cpf, String crm) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.crm = crm;
    }

    public EntityModel<Medico> toModel() {
        return EntityModel.of(
            this,
            linkTo(methodOn(MedicoController.class).show(id)).withSelfRel(),
            linkTo(methodOn(MedicoController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(MedicoController.class).index()).withRel("listAll")
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    @Override
    public String toString() {
        return "Medico [id=" + id + ", nome=" + nome + ", email=" + email + ", cpf=" + cpf + ", crm=" + crm + "]";
    }
}
