package es.eternalshadow.service.interfaces;

import java.util.List;

import es.eternalshadow.entities.Criatura;
import es.eternalshadow.entities.Enemigo;
import es.eternalshadow.entities.Jugador;
import es.eternalshadow.exception.LimiteCombateException;

/**
 * Servicio para gestionar combates.
 */
public interface CombateService {
    
    /**
     * Inicia un combate entre jugadores y enemigos.
     * @param jugadores Lista de jugadores participantes.
     * @param enemigos Lista de enemigos participantes.
     * @param esObligatorio true si no se puede huir del combate.
     */
    void iniciarCombate(List<Criatura> jugadores, List<Criatura> enemigos, boolean esObligatorio) 
            throws LimiteCombateException;
    
    /**
     * Realiza un ataque de un jugador a un enemigo.
     */
    void atacar(Jugador atacante, Enemigo objetivo);
    
    /**
     * Intenta huir del combate.
     * @return true si tiene éxito, false si falla.
     */
    boolean intentarHuir(Jugador jugador);
    
    /**
     * Usa una habilidad especial en combate.
     */
    void usarHabilidad(Jugador jugador, String habilidad, Enemigo objetivo);
    
    /**
     * Usa un item en combate (poción, etc.).
     */
    void usarItem(Jugador jugador, String item);
    
    /**
     * Calcula daño crítico.
     * @param dañoBase Daño base del ataque.
     * @return Daño con crítico aplicado.
     */
    int calcularDañoCritico(int dañoBase);
    
    /**
     * Verifica si el combate ha terminado.
     */
    boolean isCombateTerminado();
    
    /**
     * Obtiene el ganador del combate.
     * @return "JUGADORES", "ENEMIGOS" o null si sigue en curso.
     */
    String getGanador();
    
    /**
     * Obtiene experiencia otorgada por derrotar enemigos.
     */
    int getExperienciaGanada();
    
    /**
     * Obtiene botín obtenido en el combate.
     */
    List<String> getBotinObtenido();
}