public class Producto extends ElementoCatalogo {

    public static final double PRECIO_MINIMO = 1.0;

    private String descripcion;
    private double precio;
    private int inventarioMinimo;
    private Proveedor proveedor;
    private Ubicacion ubicacion;

    public Producto(String codigo, String descripcion, double precio, int inventarioMinimo, Proveedor proveedor, Ubicacion ubicacion) {
        super(codigo);
        setDescripcion(descripcion);
        setPrecio(precio);
        setInventarioMinimo(inventarioMinimo);
        setProveedor(proveedor);
        setUbicacion(ubicacion);
    }

    @Override
    public String obtenerFicha() {
        return getCodigo() + " | " + descripcion + " | Precio: $" + String.format("%.2f", precio) + " | Minimo: " + inventarioMinimo + " | Proveedor: " + proveedor.getNombre() + " | Ubicacion: " + ubicacion.getPasillo() + "-" + ubicacion.getEstante() + "-N" + ubicacion.getNivel();
    }

    public boolean esBajoMinimo(int existenciaActual) {
        return existenciaActual < inventarioMinimo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripcion no puede estar vacia.");
        }
        this.descripcion = descripcion.trim();
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio < PRECIO_MINIMO) {
            throw new IllegalArgumentException("El precio minimo es " + PRECIO_MINIMO + ".");
        }
        this.precio = precio;
    }

    public int getInventarioMinimo() {
        return inventarioMinimo;
    }

    public void setInventarioMinimo(int inventarioMinimo) {
        if (inventarioMinimo < 0) {
            throw new IllegalArgumentException("El inventario minimo no puede ser negativo.");
        }
        this.inventarioMinimo = inventarioMinimo;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        if (proveedor == null) {
            throw new IllegalArgumentException("El proveedor es obligatorio.");
        }
        this.proveedor = proveedor;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        if (ubicacion == null) {
            throw new IllegalArgumentException("La ubicacion es obligatoria.");
        }
        this.ubicacion = ubicacion;
    }
}