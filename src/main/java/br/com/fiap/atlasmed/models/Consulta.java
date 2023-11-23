package br.com.fiap.atlasmed.models;

import java.time.LocalDate;

import org.springframework.hateoas.EntityModel;

import br.com.fiap.atlasmed.controllers.ConsultaController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_consulta")
public class Consulta {
    
    @Id
    @GeneratedValue(
        generator = "seq_consulta",
        strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
        allocationSize = 1,
        name = "seq_consulta",
        sequenceName = "seq_consulta"
    )
    @Column(name = "id_consulta")
    private Long id;

    @NotBlank
    @Column(name = "motivo_consulta")
    private String motivo;

    @NotBlank
    @Column(name = "diagnostico_consulta")
    private String diagnostico;

    @NotNull
    @Column(name = "data_consulta")
    private LocalDate data;

    @ManyToOne(
        fetch = FetchType.EAGER,
        cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        }
    )
    @JoinColumn(
        name = "id_paciente",
        referencedColumnName = "id_paciente",
        foreignKey = @ForeignKey(name = "fk_tb_paciente")
    )
    private Paciente paciente;

    @ManyToOne(
        fetch = FetchType.EAGER,
        cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        }
    )
    @JoinColumn(
        name = "id_medico",
        referencedColumnName = "id_medico",
        foreignKey = @ForeignKey(name = "fk_tb_medico")
    )
    private Medico medico;

    public Consulta() {}

    public Consulta(Long id, @NotBlank String motivo, @NotBlank String diagnostico, @NotNull LocalDate data, Paciente paciente, Medico medico) {
        this.id = id;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.data = data;
        this.paciente = paciente;
        this.medico = medico;
    }

    public Consulta(@NotBlank String motivo, @NotBlank String diagnostico, @NotNull LocalDate data, Paciente paciente, Medico medico) {
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.data = data;
        this.paciente = paciente;
        this.medico = medico;
    }

    public EntityModel<Consulta> toModel() {
        return EntityModel.of(
            this,
            linkTo(methodOn(ConsultaController.class).show(id)).withSelfRel(),
            linkTo(methodOn(ConsultaController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(ConsultaController.class).index()).withRel("listAll")
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    @Override
    public String toString() {
        return "Consulta [id=" + id + ", motivo=" + motivo + ", diagnostico=" + diagnostico + ", data=" + data + ", paciente=" + paciente + ", medico=" + medico + "]";
    }
}
