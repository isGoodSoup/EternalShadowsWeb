package es.eternalshadow.dto;

import java.util.ArrayList;

import es.eternalshadow.entities.Arma;
import es.eternalshadow.entities.Escudo;

public class CriaturaDTO {
	private String nombre;
	private int nivel;
	private ArrayList<Escudo> escudos;
	private ArrayList<Arma> armas;
	private String pocion;
	private int puntosVida;

	public CriaturaDTO() {}

	public CriaturaDTO(String nombre, int nivel, ArrayList<Escudo> escudos, ArrayList<Arma> armas, String pocion,
			int puntosVida) {
		this.nombre = nombre;
		this.nivel = nivel;
		this.escudos = escudos;
		this.armas = armas;
		this.pocion = pocion;
		this.puntosVida = puntosVida;
	}

	public CriaturaDTO(String nombre, int nivel, String pocion, int puntosVida) {
		this.nombre = nombre;
		this.nivel = nivel;
		this.pocion = pocion;
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

	public ArrayList<Escudo> getEscudos() {
		return escudos;
	}

	public void setEscudos(ArrayList<Escudo> escudos) {
		this.escudos = escudos;
	}

	public ArrayList<Arma> getArmas() {
		return armas;
	}

	public void setArmas(ArrayList<Arma> armas) {
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
		return "CriaturaDTO [nombre=" + nombre + ", nivel=" + nivel + ", escudos=" + escudos + ", armas=" + armas
				+ ", pocion=" + pocion + ", puntosVida=" + puntosVida + "]";
	}
}
