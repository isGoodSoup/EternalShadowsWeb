package es.eternalshadow.interfaces;

import es.eternalshadow.entities.Criatura;

/**
 * Interfaz que define las acciones básicas que puede realizar una criatura.
 */
public interface Accionable {
    
    /**
     * Realiza un ataque a otra criatura.
     * @param objetivo Criatura a atacar.
     * @return Daño infligido.
     */
    int atacar(Criatura objetivo);
    
    /**
     * Defiende de un ataque.
     * @return Valor de defensa.
     */
    int defender();
    
    /**
     * Verifica si la criatura está viva.
     * @param criatura Criatura a verificar.
     * @return true si está viva, false si está muerta.
     */
    boolean isVivo(Criatura criatura);
    
    /**
     * Recibe daño.
     * @param danio Cantidad de daño a recibir.
     * @return Puntos de vida restantes.
     */
    int recibirDanio(int danio);
    
    /**
     * Realiza una acción especial (habilidad única).
     * @param objetivo Criatura objetivo (puede ser null).
     * @return Resultado de la acción.
     */
    default String accionEspecial(Criatura objetivo) {
        return "Realiza acción especial";
    }
    
    /**
     * Usa un item o habilidad.
     * @param item Nombre del item o habilidad.
     * @return Resultado del uso.
     */
    default String usar(String item) {
        return "Usa " + item;
    }
}