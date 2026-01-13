package es.eternalshadow.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_ENEMIGO")
public class Enemigo extends Criatura {
    
    @Column(name = "EXPERIENCIA_OTORGADA")
    private int experienciaOtorgada;

    public Enemigo() {
        super();
    }
    
    public Enemigo(String tipo, String nombre, int fuerza, int resistencia, int velocidad, int magia, int puntosVida, int experienciaOtorgada) {
        super(tipo, nombre, fuerza, resistencia, velocidad, magia, puntosVida);
        this.experienciaOtorgada = experienciaOtorgada;
    }

    public int getExperienciaOtorgada() {
        return experienciaOtorgada;
    }

    public void setExperienciaOtorgada(int experienciaOtorgada) {
        this.experienciaOtorgada = experienciaOtorgada;
    }
    
    @Override
    public String toString() {
        return "Enemigo [nombre=" + getNombre() + ", experienciaOtorgada=" + experienciaOtorgada + ", puntosVida=" + getPuntosVida() + "]";
    }
}