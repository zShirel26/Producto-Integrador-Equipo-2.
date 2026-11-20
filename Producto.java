public class Producto extends ElementoCatalogo {

    public static final double PRECIO_MINIMO = 1.0;

    private String descripcion;
    private double precio;
    private int inventarioMinimo;
    private Proveedor proveedor;
    private Ubicacion ubicacion;

    public Producto(String codigo, String descripcion, double precio, int inventarioMinimo, Proveedor proveedor, Ubicacion ubicacion) {
        super(codigo);
        asignarDescripcion(descripcion);
        asignarPrecio(precio);
        asignarInventarioMinimo(inventarioMinimo);
        asignarProveedor(proveedor);
        asignarUbicacion(ubicacion);
    }

    @Override
    public String obtenerFicha() {
        return obtenerCodigo() + " | " + descripcion + " | Precio: $" + String.format("%.2f", precio) + " | Minimo: " + inventarioMinimo + " | Proveedor: " + proveedor.obtenerNombre() + " | Ubicacion: " + ubicacion.obtenerPasillo() + "-" + ubicacion.obtenerEstante() + "-N" + ubicacion.obtenerNivel();
    }

    public boolean esBajoMinimo(int existenciaActual) {
        return existenciaActual < inventarioMinimo;
    }

    public String obtenerDescripcion() {
        return descripcion;
    }

    public void asignarDescripcion(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripcion no puede estar vacia.");
        }
        this.descripcion = descripcion.trim();
    }

    public double obtenerPrecio() {
        return precio;
    }

    public void asignarPrecio(double precio) {
        if (precio < PRECIO_MINIMO) {
            throw new IllegalArgumentException("El precio minimo es " + PRECIO_MINIMO + ".");
        }
        this.precio = precio;
    }

    public int obtenerInventarioMinimo() {
        return inventarioMinimo;
    }

    public void asignarInventarioMinimo(int inventarioMinimo) {
        if (inventarioMinimo < 0) {
            throw new IllegalArgumentException("El inventario minimo no puede ser negativo.");
        }
        this.inventarioMinimo = inventarioMinimo;
    }

    public Proveedor obtenerProveedor() {
        return proveedor;
    }

    public void asignarProveedor(Proveedor proveedor) {
        if (proveedor == null) {
            throw new IllegalArgumentException("El proveedor es obligatorio.");
        }
        this.proveedor = proveedor;
    }

    public Ubicacion obtenerUbicacion() {
        return ubicacion;
    }

    public void asignarUbicacion(Ubicacion ubicacion) {
        if (ubicacion == null) {
            throw new IllegalArgumentException("La ubicacion es obligatoria.");
        }
        this.ubicacion = ubicacion;
    }
}
