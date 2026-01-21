package es.eternalshadow.story;

import java.util.List;

import org.jline.reader.LineReader;

import es.eternalshadow.entities.Criatura;
import es.eternalshadow.main.GameContext;
import es.eternalshadow.main.Panel;
import es.eternalshadow.util.UtilHub;

public class HistoriaPrincipal extends Historia {
	private final GameContext context;
	
	public HistoriaPrincipal(String titulo, Panel panel, GameContext context) {
		super(titulo, panel);
		this.context = context;
	}

	@Override
	public List<Criatura> iniciar(List<Criatura> criaturas, LineReader reader, UtilHub util) {
		for (int i = 0; i < getCapitulos().size(); i++) {
		    Capitulo capitulo = context.getServices().getCapitulosLoaderService().cargarCapitulos(
			    "docs/mq/capitulo" + getCapitulos().get(i).getNumero() + ".txt",
			    criaturas,
			    context.getJugador()
			);
			getCapitulos().set(i, capitulo);
		}
		return criaturas;
	}
}
