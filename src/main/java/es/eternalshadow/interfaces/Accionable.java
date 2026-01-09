package es.eternalshadow.interfaces;

import es.eternalshadow.entities.Criatura;

public interface Accionable {
    int atacar(Criatura criatura);
    int defender();
    boolean isVivo(Criatura criatura);
    int recibirDanio(int danio);
}