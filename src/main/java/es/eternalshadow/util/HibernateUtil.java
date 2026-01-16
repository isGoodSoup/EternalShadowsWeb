package es.eternalshadow.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.eternalshadow.entities.Usuario;
import es.eternalshadow.enums.RolUsuario;
import es.eternalshadow.main.GameContext;

public class HibernateUtil {
    private static GameContext context;
    private static SessionFactory sessionFactory;
    private static final Logger log = LoggerFactory.getLogger(HibernateUtil.class);

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            
            // Añadir entidades
            configuration.addAnnotatedClass(es.eternalshadow.entities.Usuario.class);
            configuration.addAnnotatedClass(es.eternalshadow.entities.Raza.class);
            configuration.addAnnotatedClass(es.eternalshadow.entities.Criatura.class);
            configuration.addAnnotatedClass(es.eternalshadow.entities.Jugador.class);
            configuration.addAnnotatedClass(es.eternalshadow.entities.Enemigo.class);
            configuration.addAnnotatedClass(es.eternalshadow.entities.Item.class);
            configuration.addAnnotatedClass(es.eternalshadow.entities.Arma.class);
            configuration.addAnnotatedClass(es.eternalshadow.entities.Escudo.class);
            
            sessionFactory = configuration.buildSessionFactory();
            log.info("SessionFactory creada exitosamente");
        } catch (Throwable ex) {
            log.error("Error al crear SessionFactory", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
            log.info("SessionFactory cerrada");
        }
    }
    
    public static void setContext(GameContext gameContext) {
        context = gameContext;
    }
    
    public static void comprobarRolAdmin() {
        if (context == null) {
            log.warn("Contexto no inicializado, saltando creación de admin");
            return;
        }
        
        try (Session session = getSession()) {
            Transaction tx = session.beginTransaction();

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
                nuevoAdmin.setEmail("admin@eternalshadow.com");
                nuevoAdmin.setPassword(context.getUtil().getPasswordUtil().hashPassword("admin123"));
                nuevoAdmin.setRol(RolUsuario.ADMIN);
                nuevoAdmin.setActivo(true);
                nuevoAdmin.setFechaAlta(new java.sql.Date(System.currentTimeMillis()));

                session.persist(nuevoAdmin);
                log.info("Usuario ADMIN creado con éxito.");
            } else {
                log.info("Usuario ADMIN ya existe.");
            }

            tx.commit();
        } catch (Exception e) {
            log.error("Error al verificar/crear admin", e);
        }
    }
    
    public static void save(Object entity) {
        try (Session session = getSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(entity);
            tx.commit();
        } catch (Exception e) {
            log.error("Error al guardar entidad", e);
        }
    }
    
    public static void update(Object entity) {
        try (Session session = getSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(entity);
            tx.commit();
        } catch (Exception e) {
            log.error("Error al actualizar entidad", e);
        }
    }
    
    public static <T> T getById(Class<T> clazz, Object id) {
        try (Session session = getSession()) {
            return session.get(clazz, id);
        } catch (Exception e) {
            log.error("Error al obtener entidad por ID", e);
            return null;
        }
    }
}