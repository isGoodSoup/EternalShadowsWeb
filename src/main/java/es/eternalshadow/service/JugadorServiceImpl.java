package es.eternalshadow.service;

import java.util.ArrayList;
import java.util.List;

import es.eternalshadow.dao.JugadorDAOImpl;
import es.eternalshadow.entities.Jugador;
import es.eternalshadow.main.GameContext;
import es.eternalshadow.service.interfaces.JugadorService;
import es.eternalshadow.util.RandomUtils;

public class JugadorServiceImpl implements JugadorService {
    private GameContext context;
    private JugadorDAOImpl jugadorDAO;
    private Jugador jugadorPrincipal;
    private List<Jugador> jugadoresActivos;
    
    public JugadorServiceImpl(GameContext context) {
        this.context = context;
        this.jugadorDAO = new JugadorDAOImpl();
        this.jugadoresActivos = new ArrayList<>();
        this.jugadorPrincipal = null;
    }
    
    @Override
    public Jugador crearJugador(String nombre, String tipo) {
        // Valores por defecto para nuevo jugador
        int fuerza = RandomUtils.toGetInteger(5, 15);
        int resistencia = RandomUtils.toGetInteger(5, 15);
        int velocidad = RandomUtils.toGetInteger(5, 15);
        int magia = RandomUtils.toGetInteger(5, 15);
        int ataque = RandomUtils.toGetInteger(3, 8);
        int defensa = 10 - ataque;
        
        Jugador jugador = new Jugador(
            tipo, nombre, fuerza, resistencia, velocidad, magia,
            100, 50, ataque, defensa
        );
        
        jugadoresActivos.add(jugador);
        if (jugadorPrincipal == null) {
            jugadorPrincipal = jugador;
        }
        
        System.out.println("✓ Jugador creado: " + nombre + " [" + tipo + "]");
        return jugador;
    }
    
    @Override
    public Jugador getJugadorPrincipal() {
        return jugadorPrincipal;
    }
    
    @Override
    public void setJugadorPrincipal(Jugador jugador) {
        this.jugadorPrincipal = jugador;
        System.out.println("✓ Jugador principal establecido: " + jugador.getNombre());
    }
    
    @Override
    public List<Jugador> getTodosJugadores() {
        List<Jugador> todos = new ArrayList<>(jugadoresActivos);
        todos.addAll(jugadorDAO.obtenerTodosLosJugadores());
        return todos;
    }
    
    @Override
    public void guardarJugador(Jugador jugador) {
        jugadorDAO.guardar(jugador);
        System.out.println("✓ Jugador guardado: " + jugador.getNombre());
    }
    
    @Override
    public void actualizarJugador(Jugador jugador) {
        jugadorDAO.actualizar(jugador);
        System.out.println("✓ Jugador actualizado: " + jugador.getNombre());
    }
    
    @Override
    public Jugador cargarJugador(int id) {
        Jugador jugador = jugadorDAO.obtenerPorId((long) id);
        if (jugador != null) {
            jugadoresActivos.add(jugador);
            System.out.println("✓ Jugador cargado: " + jugador.getNombre());
        }
        return jugador;
    }
    
    @Override
    public void eliminarJugador(int id) {
        jugadorDAO.eliminar((long) id);
        jugadoresActivos.removeIf(j -> j.getId() == id);
        System.out.println("✓ Jugador eliminado (ID: " + id + ")");
    }
    
    @Override
    public void subirNivel(Jugador jugador) {
        int nuevoNivel = jugador.getNivel() + 1;
        jugador.setNivel(nuevoNivel);
        
        // Mejora atributos al subir nivel
        jugador.setFuerza(jugador.getFuerza() + 2);
        jugador.setResistencia(jugador.getResistencia() + 2);
        jugador.setVelocidad(jugador.getVelocidad() + 1);
        jugador.setMagia(jugador.getMagia() + 1);
        jugador.setPuntosVida(jugador.getPuntosVida() + 20);
        
        System.out.println("✓ " + jugador.getNombre() + " subió al nivel " + nuevoNivel);
    }
    
    @Override
    public void aplicarExperiencia(Jugador jugador, int experiencia) {
        // Sistema simple: 100 exp por nivel
        int expNecesaria = jugador.getNivel() * 100;
        int expAcumulada = experiencia; // En un sistema real habría acumulado
        
        if (expAcumulada >= expNecesaria) {
            subirNivel(jugador);
            System.out.println("✓ " + jugador.getNombre() + " ganó " + experiencia + " exp");
        }
    }
    
    @Override
    public void curarJugador(Jugador jugador, int puntosCuracion) {
        int nuevaVida = jugador.getPuntosVida() + puntosCuracion;
        int maxVida = 100 + (jugador.getNivel() * 10);
        
        jugador.setPuntosVida(Math.min(nuevaVida, maxVida));
        System.out.println("✓ " + jugador.getNombre() + " curado (+" + puntosCuracion + " PV)");
    }
    
    @Override
    public void añadirItem(Jugador jugador, String item, int cantidad) {
        // Implementación básica
        System.out.println("✓ Añadido " + cantidad + "x " + item + " a " + jugador.getNombre());
    }
    
    @Override
    public void quitarItem(Jugador jugador, String item, int cantidad) {
        // Implementación básica
        System.out.println("✓ Quitado " + cantidad + "x " + item + " de " + jugador.getNombre());
    }
}