package es.eternalshadow.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "JUGADOR_ID")
    private List<Arma> armasJugador = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "JUGADOR_ID")
    private List<Escudo> escudosJugador = new ArrayList<>();
    
    @Transient
    private Map<String, ItemEntity> inventario = new HashMap<>();

    public Jugador() {
        super();
    }

    public Jugador(String tipo, String nombre, int fuerza, int resistencia, int velocidad, int magia, int puntosVida, int moral) {
        super(tipo, nombre, fuerza, resistencia, velocidad, magia, puntosVida);
        this.moral = moral;
    }

    public Jugador(String nombre, String tipo, int nivel, int puntosVida, int moral, List<Arma> armas, List<Escudo> escudos, Map<String, ItemEntity> inventario) {
        super();
        setNombre(nombre);
        setNivel(nivel);
        setPuntosVida(puntosVida);
        this.moral = moral;
        this.armasJugador = armas != null ? armas : new ArrayList<>();
        this.escudosJugador = escudos != null ? escudos : new ArrayList<>();
        this.inventario = inventario != null ? inventario : new HashMap<>();
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

    public List<Arma> getArmasJugador() {
        return armasJugador;
    }

    public void setArmasJugador(List<Arma> armasJugador) {
        this.armasJugador = armasJugador != null ? armasJugador : new ArrayList<>();
    }

    public List<Escudo> getEscudosJugador() {
        return escudosJugador;
    }

    public void setEscudosJugador(List<Escudo> escudosJugador) {
        this.escudosJugador = escudosJugador != null ? escudosJugador : new ArrayList<>();
    }

    public Map<String, ItemEntity> getInventario() {
        return inventario;
    }

    public void setInventario(Map<String, ItemEntity> inventario) {
        this.inventario = inventario != null ? inventario : new HashMap<>();
    }

    @Override
    public String toString() {
        return "Jugador [nombre=" + getNombre() + ", nivel=" + getNivel() + ", puntosVida=" + getPuntosVida() + ", moral=" + moral + ", ataque=" + getAtaque() + ", defensa=" + getDefensa() + ", armasJugador=" + armasJugador + ", escudosJugador=" + escudosJugador + "]";
    }
}