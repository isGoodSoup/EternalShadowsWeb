package es.eternalshadow.dao.interfaces;

import java.util.List;

import es.eternalshadow.entities.Criatura;

public interface CriaturaDAO extends DAO {
	List<Criatura> obtenerTodasLasCriaturas();
}
