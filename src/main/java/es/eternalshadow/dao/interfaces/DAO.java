package es.eternalshadow.dao.interfaces;

public interface DAO {
	void guardar(Object obj);
	void eliminar(Long id);
	void actualizar(Object obj);
	Object obtenerPorId(Long id);
}
