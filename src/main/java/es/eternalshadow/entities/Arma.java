package es.eternalshadow.entities;

import es.eternalshadow.enums.Armamento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_ARMA")
public class Arma extends Item {
    
    @Enumerated(EnumType.STRING)
    @Column(name = "ARMAMENTO", nullable = false, length = 50)
    private Armamento tipoArma;

    public Arma(String nombre, int cantidad, Armamento tipoArma) {
        super(nombre, cantidad);
        this.tipoArma = tipoArma;
    }
    
    public Arma() {
        super();
    }

    public Armamento getTipoArma() {
        return tipoArma;
    }

    public void setTipoArma(Armamento tipoArma) {
        this.tipoArma = tipoArma;
    }
    
    public Armamento getArma() {
        return tipoArma;
    }

    public void setArma(Armamento tipoArma) {
        this.tipoArma = tipoArma;
    }
}