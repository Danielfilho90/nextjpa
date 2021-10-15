package school.cesar.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "next_database", name = "next_escola")
public class Escola extends AbstractNextEntity {

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_tipo_escola", referencedColumnName = "id", nullable = false)
    private TipoEscola tipo;

    @OneToMany(targetEntity = Turma.class, mappedBy = "escola", orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Turma> turmas;

    public Escola() {
        super();
        this.turmas = new ArrayList<>();
    }

    public TipoEscola getTipo() {
        return tipo;
    }

    public void setTipo(TipoEscola tipo) {
        this.tipo = tipo;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

}
