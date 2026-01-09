package es.eternalshadow.story;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.eternalshadow.main.GameContext;
import es.eternalshadow.motor.Escena;
import es.eternalshadow.motor.Opcion;

public class Capitulo {
	private String nombre;
	private int numero;
	private List<String> lineas = new ArrayList<>();
	private Map<String, Escena> escenas = new HashMap<>();
	private Escena escena;
	
	public Capitulo() {}

	public Capitulo(String nombre, int numero, List<String> lineas,
			Map<String, Escena> escenas, Escena escena) {
		super();
		this.nombre = nombre;
		this.numero = numero;
		this.lineas = lineas;
		this.escenas = escenas;
		this.escena = escena;
	}

	public Capitulo(int numero, String nombre, Escena escena) {
		super();
		this.nombre = nombre;
		this.numero = numero;
		this.escena = escena;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public List<String> getLineas() {
		return lineas;
	}

	public void setLineas(List<String> lineas) {
		this.lineas = lineas;
	}
	
	public Map<String, Escena> getEscenas() {
		return escenas;
	}

	public void setEscenas(Map<String, Escena> escenas) {
		this.escenas = escenas;
	}

	public Escena getEscena() {
		return escena;
	}

	public void setEscena(Escena escena) {
		this.escena = escena;
	}
	
	/**
     * Método que inicia el juego de este capítulo.
     * Muestra las escenas, las opciones, ejecuta acciones y avanza a la siguiente escena.
	 * @throws InterruptedException 
     */
    public void jugar(GameContext context) throws InterruptedException {
        Escena escenaActual = escena;
        while (escenaActual != null) {
            System.out.println(escenaActual.getDescripcion());
            List<Opcion> opciones = escenaActual.getOpciones();
            if (opciones.isEmpty()) {
                System.out.println("ERROR: No hay más opciones. Fin del capítulo.");
                break;
            }
            for (int i = 0; i < opciones.size(); i++) {
                System.out.println((i + 1) + ". " + opciones.get(i).getTexto());
            }
            int seleccion = context.getUtil().getInputHandler().crearMenu(
                    context.getReader(),
                    opciones.stream()
                    .map(Opcion::getTexto)
                    .toArray(String[]::new),
                    "Elige tu acción:"
            );
            Opcion opcionElegida = opciones.get(seleccion - 1);
            opcionElegida.ejecutar();
            escenaActual = opcionElegida.getEscenaDestino();
        }
    }
}
