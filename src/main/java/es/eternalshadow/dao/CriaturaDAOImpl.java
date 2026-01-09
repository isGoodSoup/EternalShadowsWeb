package es.eternalshadow.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import es.eternalshadow.dao.interfaces.CriaturaDAO;
import es.eternalshadow.entities.Criatura;
import es.eternalshadow.util.HibernateUtil;

public class CriaturaDAOImpl implements CriaturaDAO {

	@Override
	public void guardar(Object criatura) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory()) {
			tx = session.beginTransaction();
			session.persist(criatura);
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
			Criatura criatura = session.get(Criatura.class, id);
			if (criatura != null) {
				session.remove(criatura);
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
	public void actualizar(Object criatura) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory()) {
			tx = session.beginTransaction();
			session.merge(criatura);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}
	}

	@Override
	public Criatura obtenerPorId(Long id) {
		try (Session session = HibernateUtil.getSessionFactory()) {
			return session.get(Criatura.class, id);
		}
	}

	@Override
	public List<Criatura> obtenerTodasLasCriaturas() {
		try (Session session = HibernateUtil.getSessionFactory()) {
			return session.createQuery("from Criatura", Criatura.class).list();
		}
	}
}
