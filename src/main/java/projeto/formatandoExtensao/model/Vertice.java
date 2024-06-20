package projeto.formatandoExtensao.model;

public class Vertice {

	private String codigo;
	private String longitude;
	private String latitude;
	private String altitude;
	
	public Vertice() {}

	public Vertice(String codigo, String longitude, String latitude, String altitude) {
		this.codigo = codigo;
		this.longitude = longitude;
		this.latitude = latitude;
		this.altitude = altitude;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getAltitude() {
		return altitude;
	}
	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	@Override
	public String toString() {
		return "Vertice [codigo=" + codigo + ", longitude=" + longitude + ", latitude=" + latitude + ", altitude="
				+ altitude + "]";
	}
	
}
