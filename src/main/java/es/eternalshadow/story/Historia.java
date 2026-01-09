package es.eternalshadow.story;

import java.util.ArrayList;
import java.util.List;

import org.jline.reader.LineReader;

import es.eternalshadow.entities.Criatura;
import es.eternalshadow.main.Panel;
import es.eternalshadow.motor.Escena;
import es.eternalshadow.motor.Opcion;
import es.eternalshadow.util.UtilHub;

public abstract class Historia {
	private String titulo;
	private Panel panel;
	private UtilHub util;
	private List<Capitulo> capitulos;
	private Escena escena;

	public Historia(String titulo, Panel panel) {
		this.titulo = titulo;
		this.panel = panel;
		this.capitulos = new ArrayList<>();
		this.util = new UtilHub();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Panel getPanel() {
		return panel;
	}

	public void setPanel(Panel panel) {
		this.panel = panel;
	}

	public UtilHub getUtil() {
		return util;
	}

	public void setUtil(UtilHub util) {
		this.util = util;
	}

	public List<Capitulo> getCapitulos() {
		return capitulos;
	}

	public void setCapitulos(List<Capitulo> capitulos) {
		this.capitulos = capitulos;
	}

	public Escena getEscena() {
		return escena;
	}

	public void setEscena(Escena escena) {
		this.escena = escena;
	}

	public void iniciarCapitulo(Capitulo capitulo) {
		this.escena = capitulo.getEscena();
		mostrarEscena();
	}

	public void mostrarEscena() {
		System.out.println("\n" + escena.getDescripcion());
		List<Opcion> opciones = escena.getOpciones();
		for (int i = 0; i < opciones.size(); i++) {
			System.out.println((i + 1) + ". " + opciones.get(i).getTexto());
		}
	}

	public void elegirOpcion(int indice) {
		List<Opcion> opciones = escena.getOpciones();
		if (indice < 1 || indice > opciones.size()) {
			System.out.println("Opción no válida.");
			return;
		}

		escena = opciones.get(indice - 1).getEscenaDestino();
		mostrarEscena();
	}

	public abstract List<Criatura> iniciar(List<Criatura> criaturas,
			LineReader reader, UtilHub util);
}
