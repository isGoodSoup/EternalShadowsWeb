package es.eternalshadow.dto;

import java.util.Map;

/**
 * DTO para transferencia de datos de jugadores.
 */
public class JugadorDTO {
    private Long id;
    private String nombre;
    private String tipo;
    private int nivel;
    private int puntosVida;
    private int moral;
    private int ataque;
    private int defensa;
    private int fuerza;
    private int resistencia;
    private int velocidad;
    private int magia;
    private Map<String, ItemDTO> inventario;
    
    public JugadorDTO() {
    }
    
    public JugadorDTO(String nombre, String tipo, int nivel, int puntosVida, int moral) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivel = nivel;
        this.puntosVida = puntosVida;
        this.moral = moral;
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

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getMagia() {
        return magia;
    }

    public void setMagia(int magia) {
        this.magia = magia;
    }

    public Map<String, ItemDTO> getInventario() {
        return inventario;
    }

    public void setInventario(Map<String, ItemDTO> inventario) {
        this.inventario = inventario;
    }
    
    @Override
    public String toString() {
        return nombre + " [" + tipo + "] Nvl " + nivel + " - PV: " + puntosVida + "/100";
    }
}