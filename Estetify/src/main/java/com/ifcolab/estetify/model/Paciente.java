package com.ifcolab.estetify.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.ifcolab.estetify.model.enums.TipoUsuario;
import com.ifcolab.estetify.model.enums.TipoSexo;

@Getter
@Setter
@Entity
public class Paciente extends Pessoa {
    @Column(length = 1000)
    private String historicoMedico;
    
    @OneToMany(mappedBy = "paciente")
    private List<Consulta> consultas;
    
    public Paciente() {
        super();
    }
    
    public Paciente(String nome, String email, String senha, String cpf, TipoSexo sexo, String dataNascimento, String telefone, String endereco, String historicoMedico, int avatar) {
        super(nome, 
              email, 
              senha, 
              cpf, 
              sexo, 
              LocalDate.parse(dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy")), 
              telefone, 
              endereco, 
              TipoUsuario.PACIENTE,
              avatar);
        this.historicoMedico = historicoMedico;
    }
    
    @Override
    public String toString() {
        return this.getNome();
    }
}
