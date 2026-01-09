package es.eternalshadow.motor;

public class Opcion {
	private String texto;
	private String siguienteEscenaId;
	private Escena escenaDestino;
	private Runnable accion;

	public Opcion(String texto, String siguienteEscenaId, Runnable accion) {
		this.texto = texto;
		this.siguienteEscenaId = siguienteEscenaId;
		this.accion = accion;
	}

	public Opcion(String texto) {
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getSiguienteEscenaId() {
		return siguienteEscenaId;
	}

	public void setSiguienteEscenaId(String id) {
	    this.siguienteEscenaId = id;
	}

	public Runnable getAccion() {
		return accion;
	}

	public void setAccion(Runnable accion) {
		this.accion = accion;
	}

	public Escena getEscenaDestino() {
		return escenaDestino;
	}

	public void setEscenaDestino(Escena escenaDestino) {
		this.escenaDestino = escenaDestino;
	}
	
	public void ejecutar() {
	    if (accion != null)
	        accion.run();
	}
}
