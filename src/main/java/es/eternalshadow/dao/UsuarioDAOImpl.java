package es.eternalshadow.dao;

import java.util.List;

import es.eternalshadow.dao.interfaces.UsuarioDAO;
import es.eternalshadow.entities.Usuario;
import es.eternalshadow.util.HibernateUtil;

public class UsuarioDAOImpl implements UsuarioDAO {
    
    @Override
    public void guardar(Usuario usuario) {
        HibernateUtil.save(usuario);
    }
    
    @Override
    public void actualizar(Usuario usuario) {
        HibernateUtil.update(usuario);
    }
    
    @Override
    public Usuario obtenerPorId(int id) {
        return HibernateUtil.getById(Usuario.class, id);
    }
    
    @Override
    public Usuario obtenerPorString(String usernameOrEmail) {
        try (var session = HibernateUtil.getSession()) {
            return session.createQuery(
                "FROM Usuario u WHERE u.username = :valor OR u.email = :valor", 
                Usuario.class)
                .setParameter("valor", usernameOrEmail)
                .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public boolean isExisteEmail(String email) {
        try (var session = HibernateUtil.getSession()) {
            Long count = session.createQuery(
                "SELECT COUNT(u) FROM Usuario u WHERE u.email = :email", 
                Long.class)
                .setParameter("email", email)
                .uniqueResult();
            return count != null && count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        try (var session = HibernateUtil.getSession()) {
            return session.createQuery("FROM Usuario", Usuario.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
    
   public boolean esAdmin(String username, String password) {
		try (var session = HibernateUtil.getSession()) {
			Long count = session.createQuery(
				"SELECT COUNT(u) FROM Usuario u WHERE u.username = :username AND u.password = :password AND u.rol = 'ADMIN'", 
				Long.class)
				.setParameter("username", username)
				.setParameter("password", password)
				.uniqueResult();
			return count != null && count > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}


}