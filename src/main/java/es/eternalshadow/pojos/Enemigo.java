package es.eternalshadow.pojos;

import es.eternalshadow.entities.Criatura;

public class Enemigo extends Criatura {
	private String nombre;
	private int vida;
	private int ataque;
	private int defensa;
	private int experienciaOtorgada;

	public Enemigo(String nombre, int vida, int ataque, int defensa, int experienciaOtorgada) {
		this.nombre = nombre;
		this.vida = vida;
		this.ataque = ataque;
		this.defensa = defensa;
		this.experienciaOtorgada = experienciaOtorgada;
	}

	public String getNombre() {
		return nombre;
	}

	public int getVida() {
		return vida;
	}

	public int getAtaque() {
		return ataque;
	}

	public int getDefensa() {
		return defensa;
	}

	public int getExperienciaOtorgada() {
		return experienciaOtorgada;
	}

	public boolean isVivo() {
		return vida > 0;
	}
	
	@Override
	public int recibirDanio(int i) {
		return super.recibirDanio(i);
	}
}
