package school.cesar;

import school.cesar.dao.AlunoDAO;
import school.cesar.dao.TurmaDAO;
import school.cesar.model.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JPATest {
    public static void main(String... args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("school.cesar.next.PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            Turma turma = new Turma();
            turma.setNome("Turma de Java Avançada");

            Escola escola = new Escola();
            escola.setNome("CESAR Schooooooooool Public");

            TipoEscola tipoEscola = entityManager.find(TipoEscola.class, 1);
            escola.setTipo(tipoEscola);

            turma.setEscola(escola);

            Professor professor = entityManager.find(Professor.class, 1);
            turma.setProfessor(professor);

            for(int i=10; i<40; i++) {
                Aluno aluno = new Aluno();
                aluno.setNome("Aluno Muito Calminho" + i);
                aluno.setCpf("222333444" + i);

                turma.getAlunos().add(aluno);
            }

            entityManager.persist(turma);
            entityManager.getTransaction().commit();

            TypedQuery<Aluno> query = entityManager.createQuery("select a from Aluno a where a.nome like :nome", Aluno.class);
            query.setParameter("nome", "Aluno Muito Calminho%");

            List<Aluno> alunosConsultados = query.getResultList();
            for (Aluno aluno : alunosConsultados) {
                System.out.println(aluno);
            }
            System.out.println("===================================================================");


            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Aluno> criteriaQuery = criteriaBuilder.createQuery(Aluno.class);
            Root<Aluno> root = criteriaQuery.from(Aluno.class);

            CriteriaBuilder.In<Integer> in = criteriaBuilder.in(root.get("id"));
            for(int i=1; i<=10; i++) {
                in.value(i);
            }

            criteriaQuery.where(
                    criteriaBuilder.and(
                        criteriaBuilder.like(root.get("nome"), "Aluno Muito Calminho%"),
                        criteriaBuilder.not(in)
                    )
            );
            TypedQuery<Aluno> typedQuery = entityManager.createQuery(criteriaQuery);

            List<Aluno> maisAlunosConsultados = typedQuery.getResultList();
            for (Aluno aluno : maisAlunosConsultados) {
                System.out.println(aluno);
            }

            System.out.println("===================================================================");

            AlunoDAO alunoDAO = AlunoDAO.getInstance();
            for (Aluno aluno : alunoDAO.findAll()) {
                System.out.println(aluno);
            }

            TurmaDAO turmaDAO = TurmaDAO.getInstance();
            for (Turma turmax : turmaDAO.findAll()) {
                System.out.println(turmax);
            }

        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
            exception.printStackTrace();
            throw exception;
        } finally {
            entityManager.close();
        }
    }
}
