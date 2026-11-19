public class Proveedor extends ElementoCatalogo {
    private String nombre;
    private String telefono;

    public Proveedor(String codigo, String nombre, String telefono) {
        super(codigo);
        setNombre(nombre);
        setTelefono(telefono);
    }

    @Override
    public String obtenerFicha() {
        String estado;
        estado = getActivo() ? "ACTIVO" : "INACTIVO";
        return "[" + getCodigo() + "] Proveedor: " + nombre
                + " | Tel: " + telefono + " | Estado: " + estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre.trim();
        } else if (this.nombre == null) {
            this.nombre = "Sin nombre";
        }
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono != null && !telefono.trim().isEmpty()) {
            this.telefono = telefono.trim();
        } else if (this.telefono == null) {
            this.telefono = "Sin telefono";
        }
    }
}
