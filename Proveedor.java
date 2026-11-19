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
        return getCodigo() + " | " + nombre + " | Tel: " + telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
        this.nombre = nombre.trim();
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El telefono no puede estar vacio.");
        }
        this.telefono = telefono.trim();
    }
}