import java.time.LocalDate;

public abstract class Movimiento {
    public static final int CANTIDAD_MINIMA = 1;
    private String folio;
    private LocalDate fecha;
    private Producto producto;
    private int cantidad;
    private double precioUnitario;

    public Movimiento(String folio, LocalDate fecha, Producto producto, int cantidad) {
        asignarFolio(folio);
        asignarFecha(fecha);
        asignarProducto(producto);
        asignarCantidad(cantidad);
        asignarPrecioUnitario(producto.obtenerPrecio());
    }

    public abstract int calcularVariacion();
    public abstract String obtenerTipoMovimiento();

    public double calcularImporte() {
        return cantidad * precioUnitario;
    }

    public String obtenerFolio() { return folio; }
    public void asignarFolio(String folio) {
        if (folio == null || folio.trim().isEmpty()) {
            throw new IllegalArgumentException("El folio es obligatorio.");
        }
        this.folio = folio.trim().toUpperCase();
    }

    public LocalDate obtenerFecha() { return fecha; }
    public void asignarFecha(LocalDate fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha es obligatoria.");
        }
        this.fecha = fecha;
    }

    public Producto obtenerProducto() { return producto; }
    public void asignarProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto es obligatorio.");
        }
        this.producto = producto;
    }

    public int obtenerCantidad() { return cantidad; }
    public void asignarCantidad(int cantidad) {
        if (cantidad < CANTIDAD_MINIMA) {
            throw new IllegalArgumentException("La cantidad minima es " + CANTIDAD_MINIMA + ".");
        }
        this.cantidad = cantidad;
    }

    public double obtenerPrecioUnitario() { return precioUnitario; }
    public void asignarPrecioUnitario(double precioUnitario) {
        if (!Double.isFinite(precioUnitario) || precioUnitario < Producto.PRECIO_MINIMO) {
            throw new IllegalArgumentException("Precio unitario invalido.");
        }
        this.precioUnitario = precioUnitario;
    }
}
