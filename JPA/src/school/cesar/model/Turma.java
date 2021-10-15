package school.cesar.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "next_database", name = "next_turma")
public class Turma extends AbstractNextEntity {

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_escola", referencedColumnName = "id", nullable = false)
    private Escola escola;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_professor", referencedColumnName = "id", nullable = false)
    private Professor professor;

    @ManyToMany(targetEntity = Aluno.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(schema = "next_database", name = "next_aluno_turma",
            joinColumns = @JoinColumn(name = "id_turma", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_aluno", referencedColumnName = "id", nullable = false)
    )
    private List<Aluno> alunos;

    public Turma() {
        super();
        this.alunos = new ArrayList<>();
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }
}
