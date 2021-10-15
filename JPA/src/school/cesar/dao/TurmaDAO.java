package school.cesar.dao;

import school.cesar.model.Turma;
import java.util.concurrent.atomic.AtomicReference;

public class TurmaDAO extends AbstractDAO<Turma> {

    private static final AtomicReference<TurmaDAO> INSTANCE = new AtomicReference<>();

    private TurmaDAO() {
        super(Turma.class);
    }

    public static TurmaDAO getInstance() {
        INSTANCE.compareAndSet(null, new TurmaDAO());
        return INSTANCE.get();
    }

}
