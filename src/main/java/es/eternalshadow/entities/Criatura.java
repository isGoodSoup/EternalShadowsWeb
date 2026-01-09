package es.eternalshadow.entities;

import java.util.ArrayList;
import java.util.List;

import es.eternalshadow.util.Dados;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "TB_CRIATURA")
public class Criatura extends Raza {
	@Column(name = "NOMBRE", nullable = false, length = 100)
	private String nombre;
	@Column(name = "NIVEL", nullable = false)
	private int nivel = 1;
	@Column(name = "ATAQUE")
	private int ataque;
	@Column(name = "DEFENSA")
	private int defensa;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CRIATURA_ID")
    private List<Arma> armas = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CRIATURA_ID")
    private List<Escudo> escudos = new ArrayList<>();
	@Column(name = "POCION")
	private String pocion;
	@Column(name = "PUNTOS_VIDA", nullable = false)
	private int puntosVida;

	public Criatura() {}

	public Criatura(String nombre, int nivel, ArrayList<Escudo> escudos,
			ArrayList<Arma> armas, String pocion, int puntosVida) {
		super();
		this.nombre = nombre;
		this.nivel = nivel;
		this.escudos = escudos;
		this.armas = armas;
		this.pocion = pocion;
		this.puntosVida = puntosVida;
	}

	public Criatura(String tipo, String nombre, int fuerza, int resistencia,
			int velocidad, int magia, int puntosVida) {
		super(tipo, fuerza, resistencia, velocidad, magia);
		this.nombre = nombre;
		this.puntosVida = puntosVida;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	public int getAtaque() {
		return ataque;
	}

	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}

	public int getDefensa() {
		return defensa;
	}

	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}

	public List<Escudo> getEscudos() {
		return escudos;
	}

	public void setEscudos(List<Escudo> escudos) {
		this.escudos = escudos;
	}

	public List<Arma> getArmas() {
		return armas;
	}

	public void setArmas(List<Arma> armas) {
		this.armas = armas;
	}

	public String getPocion() {
		return pocion;
	}

	public void setPocion(String pocion) {
		this.pocion = pocion;
	}

	public int getPuntosVida() {
		return puntosVida;
	}

	public void setPuntosVida(int puntosVida) {
		this.puntosVida = puntosVida;
	}

	@Override

	public String toString() {
		return "Criatura [nombre=" + nombre + ", nivel=" + nivel + ", escudos="
				+ escudos + ", armas=" + armas + ", pocion=" + pocion
				+ ", puntosVida=" + puntosVida + "]";
	}

	@Override
	public int atacar(Criatura criatura) {
		return Dados.tirarDados() * nivel + this.ataque;
	}

	@Override
	public int defender() {
		return Dados.tirarDados() * nivel + this.defensa;
	}
	
	@Override
	public boolean isVivo(Criatura criatura) {
		return this.puntosVida > 0;
	}

	@Override
	public int recibirDanio(int danio) {
		this.puntosVida -= danio;
		if (this.puntosVida < 0) {
			this.puntosVida = 0;
		}
		System.out.println(this.nombre + " recibe " + danio
				+ " de daño");
		return this.puntosVida;
	}
}
