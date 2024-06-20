package projeto.formatandoExtensao.model;

public class SegmentoVante {

	private String codigo;
	private String azimute;
	private String distancia;
	private String confrontacoes;
	
	public SegmentoVante() {}
	
	public SegmentoVante(String codigo, String azimute, String distancia, String confrontacoes) {
		this.codigo = codigo;
		this.azimute = azimute;
		this.distancia = distancia;
		this.confrontacoes = confrontacoes;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getAzimute() {
		return azimute;
	}
	public void setAzimute(String azimute) {
		this.azimute = azimute;
	}
	public String getDistancia() {
		return distancia;
	}
	public void setDistancia(String distancia) {
		this.distancia = distancia;
	}
	public String getConfrontacoes() {
		return confrontacoes;
	}
	public void setConfrontacoes(String confrontacoes) {
		this.confrontacoes = confrontacoes;
	}

	@Override
	public String toString() {
		return "SegmentoVante [codigo=" + codigo + ", azimute=" + azimute + ", distancia=" + distancia
				+ ", confrontacoes=" + confrontacoes + "]";
	}
		
}
