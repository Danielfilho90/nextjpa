package teste.muitospramuitos;

import infra.DAO;
import modelo.consulta.NotaFilme;

public class ObterMediadosFilmes {
	
	public static void main(String[] args) {
		
		DAO<NotaFilme> dao = new DAO<>(NotaFilme.class);
		NotaFilme nota = dao.consultarUm("obterMediaGeralDosFilmes");
		
		System.out.println(nota.getMedia());
		
		dao.fechar();
	}

}
