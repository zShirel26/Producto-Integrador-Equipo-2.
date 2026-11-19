import java.time.LocalDate;

public abstract class Movimiento {
    public static final int CANTIDAD_MINIMA = 1;
    private String folio;
    private LocalDate fecha;
    private Producto producto;
    private int cantidad;
    private double precioUnitario;

    public Movimiento(String folio, LocalDate fecha, Producto producto, int cantidad) {
        setFolio(folio);
        setFecha(fecha);
        setProducto(producto);
        setCantidad(cantidad);
        setPrecioUnitario(producto.getPrecio());
    }

    public abstract int calcularVariacion();
    public abstract String obtenerTipoMovimiento();

    public double calcularImporte() {
        return cantidad * precioUnitario;
    }

    public String getFolio() { return folio; }
    public void setFolio(String folio) {
        if (folio == null || folio.trim().isEmpty()) {
            throw new IllegalArgumentException("El folio es obligatorio.");
        }
        this.folio = folio.trim().toUpperCase();
    }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha es obligatoria.");
        }
        this.fecha = fecha;
    }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto es obligatorio.");
        }
        this.producto = producto;
    }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) {
        if (cantidad < CANTIDAD_MINIMA) {
            throw new IllegalArgumentException("La cantidad minima es " + CANTIDAD_MINIMA + ".");
        }
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) {
        if (!Double.isFinite(precioUnitario) || precioUnitario < Producto.PRECIO_MINIMO) {
            throw new IllegalArgumentException("Precio unitario invalido.");
        }
        this.precioUnitario = precioUnitario;
    }
}
