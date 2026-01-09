package es.eternalshadow.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.eternalshadow.pojos.Item;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "TB_JUGADOR")
public class Jugador extends Criatura {
	@Column(name = "MORAL")
	private int moral;
	@Column(name = "ATAQUE")
	private int ataque;
	@Column(name = "DEFENSA")
	private int defensa;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "JUGADOR_ID")
	private List<Arma> armas = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "JUGADOR_ID")
	private List<Escudo> escudos = new ArrayList<>();
	@Transient
	private Map<String, Item> inventario = new HashMap<>();

	public Jugador() {}

	public Jugador(String tipo, String nombre, int fuerza, int resistencia, int velocidad, int magia, int puntosVida,
			int moral, int ataque, int defensa, Map<String, Item> inventario) {
		super(tipo, nombre, fuerza, resistencia, velocidad, magia, puntosVida);
		this.moral = moral;
		this.ataque = ataque;
		this.defensa = defensa;
		this.inventario = inventario;
	}

	public Jugador(String nombre, String tipo, int nivel, int puntosVida, int moral, List<Arma> armas,
			List<Escudo> escudos, Map<String, Item> inventario) {
		this.moral = moral;
		this.armas = armas;
		this.escudos = escudos;
		this.inventario = inventario;
	}

	public int getMoral() {
		return moral;
	}

	public void setMoral(int moral) {
		this.moral = moral;
	}
	
	public void modMoral(int moral) {
		this.moral += moral;
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

	public void setInventario(Map<String, Item> inventario) {
		this.inventario = inventario != null ? inventario : new HashMap<>();
	}

	@Override
	public String toString() {
		return "Jugador [nombre=" + getNombre() + ", nivel=" + getNivel() + ", puntosVida=" + getPuntosVida()
				+ ", moral=" + moral + ", armas=" + armas + ", escudos=" + escudos + "]";
	}
}
