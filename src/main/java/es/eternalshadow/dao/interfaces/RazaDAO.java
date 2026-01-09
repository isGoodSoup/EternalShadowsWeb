package es.eternalshadow.dao.interfaces;

import java.util.List;

import es.eternalshadow.entities.Raza;

public interface RazaDAO {
	void guardarRaza(Raza raza);
	void eliminarRaza(int id);
	void actualizarRaza(Raza raza);
	Raza obtenerRazaPorId(int id);
	List<Raza> obtenerTodasLasRazas();
}
