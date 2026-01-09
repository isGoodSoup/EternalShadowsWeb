package es.eternalshadow.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jline.reader.LineReader;

import es.eternalshadow.entities.Criatura;
import es.eternalshadow.entities.Jugador;
import es.eternalshadow.exception.LimiteCombateException;
import es.eternalshadow.main.GameContext;

public class CharacterFactory {
	private GameContext context;
	private static Random random = new Random();
	
	/**
	 * Permite al usuario crear un personaje personalizado eligiendo clase y
	 * atributos.
	 * @param reader Lector de líneas.
	 * @return Objeto {@link Criatura} creado.
	 * @throws LimiteCombateException 
	 */
	public Jugador crearPersonaje(LineReader reader) throws LimiteCombateException {
		int puntos = 0;
		int ataque = 0, defensa = 0;
		int moral = 50;
		int f, r, v, m;
		do {
			f = InputHandler.toScanInteger(reader, q("la fuerza"));
			puntos += f;
			r = InputHandler.toScanInteger(reader, q("la resistencia"));
			puntos += r;
			v = InputHandler.toScanInteger(reader, q("la velocidad"));
			puntos += v;
			m = InputHandler.toScanInteger(reader, q("la magia"));
			puntos += m;
		} while (puntos != 80);
		
		boolean isAtLimite = false;
		do {
			ataque = InputHandler.toScanInteger(reader, q("el ataque"));
			defensa = InputHandler.toScanInteger(reader, q("la defensa"));
			
			isAtLimite = ataque + defensa != 10;
					
			if (isAtLimite) { 
				ExceptionsHandler.printException(new LimiteCombateException("El ataque y la defensa deben sumar 10"));
			}
		} while (isAtLimite);
		
		String tipo = InputHandler.toScan(reader, "Elige tu raza");
		String nombre = InputHandler.toScan(reader, "Introduce tu nombre");
		Jugador jugador = new Jugador(tipo, nombre, f, r, v, m, 100, moral, ataque, defensa, null);
		return jugador;
	}

	/**
	 * Crea una criatura default con valores default con el objetivo de testeo
	 * más eficiente.
	 * 
	 * @return Objeto {@link Criatura} creado.
	 */
	public List<Criatura> crearPersonaje() {
		List<Criatura> criaturas = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			int f = Dados.tirarDados(), 
					r = Dados.tirarDados(), 
					v = Dados.tirarDados(), 
					m = Dados.tirarDados();
			String tipo = "CriaturaGenérica";
			String nombre = "Jugador" + i;
			Jugador jugador = new Jugador(tipo, nombre, f, r, v, m, 100, 50,
					Dados.tirarDados(), Dados.tirarDados(), null);
			criaturas.add(jugador);
		}
		return criaturas;
	}

	/**
	 * Crea una criatura aleatoria con atributos que suman 100 y clase
	 * aleatoria.
	 * 
	 * @return Criatura aleatoria creada.
	 */
	public Criatura crearCriaturaAleatoria() {
		int[] atributos = new int[4];
		int puntosTotal = 100;
		for (int i = 0; i < 3; i++) {
			atributos[i] = random.nextInt(puntosTotal + 1);
			puntosTotal -= atributos[i];
		}
		atributos[3] = puntosTotal;

		int fuerza = atributos[0];
		int resistencia = atributos[1];
		int velocidad = atributos[2];
		int magia = atributos[3];

		String[] razas = { "Mago", "Guerrero", "Demonio", "Elfo Oscuro",
				"Enano", "Elfo" };
		int numale = random.nextInt(razas.length);
		String tipo = razas[numale];

		// TODO Nombre
		Criatura c = new Criatura(tipo, null, fuerza, resistencia, velocidad,
				magia, context.getJugador().getPuntosVida());
		if (c != null) {
			System.out.println("Criatura enemiga creada: " + c.getNombre()
					+ " con atributos: " + "Fuerza: " + fuerza
					+ ", Resistencia: " + resistencia + ", Velocidad: "
					+ velocidad + ", Magia: " + magia);
		}
		return c;
	}

	/**
	 * Formatea un mensaje para solicitar un atributo.
	 * 
	 * @param s Nombre del atributo.
	 * @return Mensaje formateado.
	 */
	private String q(String s) {
		return "Introduce el valor de " + s;
	}
}
