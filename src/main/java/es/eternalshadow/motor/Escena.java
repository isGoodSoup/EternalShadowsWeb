package es.eternalshadow.motor;
import java.util.List;
public class Escena {
    private String descripcion;
    private List<Opcion> opciones;

    public Escena(String descripcion, List<Opcion> opciones) {
        this.descripcion = descripcion;
        this.opciones = opciones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public List<Opcion> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<Opcion> opciones) {
        this.opciones = opciones;
    }
}