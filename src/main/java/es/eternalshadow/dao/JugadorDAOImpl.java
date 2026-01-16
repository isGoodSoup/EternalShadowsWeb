package es.eternalshadow.dao;

import java.util.List;

import es.eternalshadow.dao.interfaces.JugadorDAO;
import es.eternalshadow.entities.Jugador;
import es.eternalshadow.util.HibernateUtil;

public class JugadorDAOImpl implements JugadorDAO {
    
    @Override
    public void guardar(Jugador jugador) {
        HibernateUtil.save(jugador);
    }
    
    @Override
    public void actualizar(Jugador jugador) {
        HibernateUtil.update(jugador);
    }
    
    @Override
    public void eliminar(Long id) {
        try (var session = HibernateUtil.getSession()) {
            var tx = session.beginTransaction();
            Jugador jugador = session.get(Jugador.class, id);
            if (jugador != null) {
                session.remove(jugador);
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Jugador obtenerPorId(Long id) {
        return HibernateUtil.getById(Jugador.class, id);
    }
    
    @Override
    public List<Jugador> obtenerTodosLosJugadores() {
        try (var session = HibernateUtil.getSession()) {
            return session.createQuery("FROM Jugador", Jugador.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}