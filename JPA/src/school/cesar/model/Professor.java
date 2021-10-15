package school.cesar.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "next_database", name = "next_professor")
public class Professor extends AbstractNextEntity {

    @OneToMany(targetEntity = Turma.class, mappedBy = "professor", orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Turma> turmas;

    public Professor() {
        super();
        this.turmas = new ArrayList<>();
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }
}
