package modelo.umpramuitos;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Date data;
	
	@OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY)// valor default
	private List<itemPedido> itens;
	
	public Pedido() {
		this(new Date());
	}
	
	

	public Pedido(Date data) {
		super();
		this.data = data;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}



	public List<itemPedido> getItens() {
		return itens;
	}



	public void setItens(List<itemPedido> itens) {
		this.itens = itens;
	}
	
	
	

}
