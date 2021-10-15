package school.cesar.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "next_database", name = "next_tipo_escola")
public class TipoEscola extends AbstractNextEntity {

    @OneToMany(targetEntity = Escola.class, mappedBy = "tipo", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Escola> escolas;

    public TipoEscola() {
        super();
        this.escolas = new ArrayList<>();
    }

    public List<Escola> getEscolas() {
        return escolas;
    }

    public void setEscolas(List<Escola> escolas) {
        this.escolas = escolas;
    }
}
