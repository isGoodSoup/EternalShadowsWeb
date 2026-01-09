package es.eternalshadow.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.eternalshadow.entities.Arma;
import es.eternalshadow.entities.Escudo;
import es.eternalshadow.pojos.Item;

public class JugadorDTO {
	private Long id;
	private String nombre;
	private String tipo;
	private int nivel;
	private int puntosVida;
	private int moral;
	private List<Arma> armas;
	private List<Escudo> escudos;
	private Map<String, Item> inventario;

	public JugadorDTO() {}

	public JugadorDTO(String nombre, String tipo, int nivel, int puntosVida, int moral, 
			List<Arma> armas, List<Escudo> escudos) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.nivel = nivel;
		this.puntosVida = puntosVida;
		this.moral = moral;
		this.armas = armas;
		this.escudos = escudos;
		
		setInventario();
	}

	public JugadorDTO(String nombre, String tipo, int nivel, int puntosVida, int moral) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.nivel = nivel;
		this.puntosVida = puntosVida;
		this.moral = moral;
		
		setInventario();
	}
	
	public JugadorDTO(String nombre, String tipo, int nivel, int puntosVida, int moral, 
            List<Arma> armas, List<Escudo> escudos, Map<String, Item> inventario) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.nivel = nivel;
		this.puntosVida = puntosVida;
		this.moral = moral;
		this.armas = armas;
		this.escudos = escudos;
		this.inventario = inventario != null ? inventario : new HashMap<>();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getPuntosVida() {
		return puntosVida;
	}

	public void setPuntosVida(int puntosVida) {
		this.puntosVida = puntosVida;
	}

	public int getMoral() {
		return moral;
	}

	public void setMoral(int moral) {
		this.moral = moral;
	}

	public List<Arma> getArmas() {
		return armas;
	}

	public void setArmas(List<Arma> armas) {
		this.armas = armas;
	}

	public List<Escudo> getEscudos() {
		return escudos;
	}

	public void setEscudos(List<Escudo> escudos) {
		this.escudos = escudos;
	}

	public Map<String, Item> getInventario() {
		return inventario;
	}

	public void setInventario() {
		this.inventario = new HashMap<>();
	}
}
