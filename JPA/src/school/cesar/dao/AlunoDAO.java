package school.cesar.dao;

import school.cesar.model.Aluno;
import java.util.concurrent.atomic.AtomicReference;

public class AlunoDAO extends AbstractDAO<Aluno> {

    private static final AtomicReference<AlunoDAO> INSTANCE = new AtomicReference<>();

    private AlunoDAO() {
        super(Aluno.class);
    }

    public static AlunoDAO getInstance() {
        INSTANCE.compareAndSet(null, new AlunoDAO());
        return INSTANCE.get();
    }
}
