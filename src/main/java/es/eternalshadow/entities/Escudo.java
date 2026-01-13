package es.eternalshadow.entities;

import es.eternalshadow.enums.Escuderia;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_ESCUDO")
public class Escudo extends ItemEntity {
    
    @Enumerated(EnumType.STRING)
    @Column(name = "ESCUDERIA", nullable = false, length = 50)
    private Escuderia tipoEscudo;

    public Escudo(String nombre, int cantidad, Escuderia tipoEscudo) {
        super(nombre, cantidad);
        this.tipoEscudo = tipoEscudo;
    }
    
    public Escudo() {
        super();
    }

    public Escuderia getTipoEscudo() {
        return tipoEscudo;
    }

    public void setTipoEscudo(Escuderia tipoEscudo) {
        this.tipoEscudo = tipoEscudo;
    }
    
    public Escuderia getEscudo() {
        return tipoEscudo;
    }

    public void setEscudo(Escuderia tipoEscudo) {
        this.tipoEscudo = tipoEscudo;
    }
}