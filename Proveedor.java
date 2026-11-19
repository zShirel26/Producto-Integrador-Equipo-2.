public class Proveedor extends ElementoCatalogo {
    private String nombre;
    private String telefono;

    public Proveedor(String codigo, String nombre, String telefono) {
        super(codigo);
        asignarNombre(nombre);
        asignarTelefono(telefono);
    }

    @Override
    public String obtenerFicha() {
        String estado;
        estado = obtenerActivo() ? "ACTIVO" : "INACTIVO";
        return "[" + obtenerCodigo() + "] Proveedor: " + nombre
                + " | Tel: " + telefono + " | Estado: " + estado;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public void asignarNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre.trim();
        } else if (this.nombre == null) {
            this.nombre = "Sin nombre";
        }
    }

    public String obtenerTelefono() {
        return telefono;
    }

    public void asignarTelefono(String telefono) {
        if (telefono != null && !telefono.trim().isEmpty()) {
            this.telefono = telefono.trim();
        } else if (this.telefono == null) {
            this.telefono = "Sin telefono";
        }
    }
}
