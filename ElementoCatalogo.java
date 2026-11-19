public abstract class ElementoCatalogo {

    private String codigo;
    private boolean activo;

    public ElementoCatalogo(String codigo) {
        setCodigo(codigo);
        setActivo(true);
    }

    public abstract String obtenerFicha();

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El codigo no puede estar vacio.");
        }
        this.codigo = codigo.trim();
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}