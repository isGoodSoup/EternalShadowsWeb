package es.eternalshadow.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.eternalshadow.entities.Usuario;
import es.eternalshadow.enums.RolUsuario;
import es.eternalshadow.main.GameContext;
import jakarta.transaction.Transaction;

public class HibernateUtil {
	private static GameContext context;
	private static final SessionFactory sessionFactory = buildSessionFactory();
	private static final Logger log = LoggerFactory.getLogger(HibernateUtil.class);

	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			log.error("Error al crear SessionFactory");
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session getSessionFactory() {
		return sessionFactory.openSession();
	}

	public static void shutdown() {
		sessionFactory.close();
	}
	
	public static void comprobarRolAdmin() {
	    try (Session session = HibernateUtil.getSessionFactory()) {
	        Transaction tx = (Transaction) session.beginTransaction();

	        Usuario admin = session.createQuery(
	            "FROM Usuario u WHERE u.rol = :rol",
	            Usuario.class
	        )
	        .setParameter("rol", RolUsuario.ADMIN)
	        .setMaxResults(1)
	        .uniqueResult();

	        if (admin == null) {
	            Usuario nuevoAdmin = new Usuario();
	            nuevoAdmin.setUsername("admin");
	            nuevoAdmin.setEmail("admin@admin.com");
	            nuevoAdmin.setPassword(context.getUtil().getPasswordUtil().hashPassword("admin"));
	            nuevoAdmin.setRol(RolUsuario.ADMIN);
	            nuevoAdmin.setActivo(true);

	            session.persist(nuevoAdmin);
	            log.info("Usuario ADMIN creado con éxito.");
	        }

	        tx.commit();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
