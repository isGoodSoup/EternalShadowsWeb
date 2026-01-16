package es.eternalshadow.dto;

/**
 * DTO para transferencia de datos de items.
 */
public class ItemDTO {
    private String nombre;
    private int cantidad;
    private String tipo;
    private String descripcion;
    private int valor;
    
    public ItemDTO() {
    }
    
    public ItemDTO(String nombre, int cantidad, String tipo) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipo = tipo;
    }
    
    public ItemDTO(String nombre, int cantidad, String tipo, String descripcion, int valor) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    @Override
    public String toString() {
        return cantidad + "x " + nombre + " (" + tipo + ")";
    }
}