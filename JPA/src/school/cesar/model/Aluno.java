package school.cesar.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "next_database", name = "next_aluno")
public class Aluno extends AbstractNextEntity {

    private String cpf;

    @ManyToMany(mappedBy = "alunos", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Turma> turmas;

    public Aluno() {
        super();
        this.turmas = new ArrayList<>();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + super.getId() +
                ", nome='" + super.getNome() + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}
