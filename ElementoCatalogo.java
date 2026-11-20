public abstract class ElementoCatalogo {

    private String codigo;
    private boolean activo;

    public ElementoCatalogo(String codigo) {
        asignarCodigo(codigo);
        asignarActivo(true);
    }

    public abstract String obtenerFicha();

    public String obtenerCodigo() {
        return codigo;
    }

    public void asignarCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El codigo no puede estar vacio.");
        }
        this.codigo = codigo.trim();
    }

    public boolean obtenerActivo() {
        return activo;
    }

    public void asignarActivo(boolean activo) {
        this.activo = activo;
    }
}
