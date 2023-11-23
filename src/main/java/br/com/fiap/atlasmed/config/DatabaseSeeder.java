package br.com.fiap.atlasmed.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.atlasmed.models.Consulta;
import br.com.fiap.atlasmed.models.Medico;
import br.com.fiap.atlasmed.models.Paciente;
import br.com.fiap.atlasmed.repositories.ConsultaRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    ConsultaRepository repo;

    @Override
    public void run(String... args) throws Exception {
        Medico m1 = new Medico("Dr. Lucas Aquino", "lucas@fiap.com.br", "111.111.111-11", "CRM111");
        Medico m2 = new Medico("Dr. Gabriel Dias", "gabriel@fiap.com.br", "222.222.222-22", "CRM222");
        Medico m3 = new Medico("Dr. Samuel Baggio", "samuel@fiap.com.br", "333.333.333-33", "CRM333");
        Medico m4 = new Medico("Dr. Renan Calejon", "renan@fiap.com.br", "444.444.444-44", "CRM444");
        Medico m5 = new Medico("Dr. Henrique Incerpi", "henrique@fiap.com.br", "555.555.555-55", "CRM555");

        Paciente p1 = new Paciente("Julia da Silva", "721.434.650-84", "julia@fiap.com.br");
        Paciente p2 = new Paciente("Gustavo da Silva", "744.973.530-01", "gustavo@fiap.com.br");
        Paciente p3 = new Paciente("Maria da Silva", "495.498.630-34", "maria@fiap.com.br");
        Paciente p4 = new Paciente("Anna da Silva", "927.063.510-47", "anna@fiap.com.br");
        Paciente p5 = new Paciente("Pedro da Silva", "609.047.380-43", "pedro@fiap.com.br");

        Consulta c1 = new Consulta("Tosse persistente", "Bronquite", LocalDate.now(), p1, m1);
        Consulta c2 = new Consulta("Dor de cabeça", "Sinusite", LocalDate.now(), p2, m2);
        Consulta c3 = new Consulta("Coceira na parte do antebraço", "Alergia", LocalDate.now(), p3, m3);
        Consulta c4 = new Consulta("Checkup", "COVID-19", LocalDate.now(), p4, m4);
        Consulta c5 = new Consulta("Dor no braço", "Braço quebrado", LocalDate.now(), p5, m5);

        repo.saveAll(List.of(c1, c2, c3, c4, c5));
    }
}
