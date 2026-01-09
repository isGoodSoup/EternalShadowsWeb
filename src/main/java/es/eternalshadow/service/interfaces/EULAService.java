package es.eternalshadow.service.interfaces;

import java.io.IOException;
import java.util.List;

public interface EULAService {
	boolean isExiste();
	boolean isAceptado() throws IOException;
	List<String> leerTexto() throws IOException;
	void guardar(boolean aceptado) throws IOException;
}
