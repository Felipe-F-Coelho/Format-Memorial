package projeto.formatandoExtensao.model;

import java.util.ArrayList;
import java.util.List;

public class Memorial {

	private List<Vertice> listVertice;
	private List<SegmentoVante> listSegmentoVante;
	
	public Memorial() {
		this.listVertice = new ArrayList<>();
		this.listSegmentoVante = new ArrayList<>();
	}
	
	public Memorial(List<Vertice> listVertice, List<SegmentoVante> listSegmentoVante) {
		this.listVertice = listVertice;
		this.listSegmentoVante = listSegmentoVante;
	}
	
	public List<Vertice> getListVertice() {
		return listVertice;
	}
	public void setListVertice(List<Vertice> listVertice) {
		this.listVertice = listVertice;
	}
	public List<SegmentoVante> getListSegmentoVante() {
		return listSegmentoVante;
	}
	public void setListSegmentoVante(List<SegmentoVante> listSegmentoVante) {
		this.listSegmentoVante = listSegmentoVante;
	}
	
}
