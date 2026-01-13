package es.eternalshadow.dto;

public class ItemDTO {
    private String nombre;
    private int cantidad;

    public ItemDTO(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }
    
    public ItemDTO() {}

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
}