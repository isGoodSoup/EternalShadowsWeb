package es.eternalshadow.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import es.eternalshadow.dao.interfaces.JugadorDAO;
import es.eternalshadow.entities.Jugador;
import es.eternalshadow.util.HibernateUtil;

public class JugadorDAOImpl implements JugadorDAO {

	@Override
	public void guardar(Object jugador) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory()) {
			tx = session.beginTransaction();
			session.persist(jugador);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}
	}

	@Override
	public void eliminar(Long id) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory()) {
			tx = session.beginTransaction();
			Jugador jugador = session.get(Jugador.class, id);
			if (jugador != null) {
				session.remove(jugador);
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}
	}

	@Override
	public void actualizar(Object jugador) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory()) {
			tx = session.beginTransaction();
			session.merge(jugador);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}
	}

	@Override
	public Jugador obtenerPorId(Long id) {
		try (Session session = HibernateUtil.getSessionFactory()) {
			return session.get(Jugador.class, id);
		}
	}

	@Override
	public List<Jugador> obtenerTodosLosJugadores() {
		try (Session session = HibernateUtil.getSessionFactory()) {
			return session.createQuery("from Jugador", Jugador.class).list();
		}
	}
}
