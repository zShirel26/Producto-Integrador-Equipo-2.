import java.time.LocalDate;

public class Entrada extends Movimiento {
    private String facturaProveedor;

    public Entrada(String folio, LocalDate fecha, Producto producto, int cantidad, String facturaProveedor) {
        super(folio, fecha, producto, cantidad);
        asignarFacturaProveedor(facturaProveedor);
    }

    @Override
    public int calcularVariacion() { return obtenerCantidad(); }

    @Override
    public String obtenerTipoMovimiento() { return "Entrada"; }

    public String obtenerFacturaProveedor() { return facturaProveedor; }
    public void asignarFacturaProveedor(String facturaProveedor) {
        if (facturaProveedor == null || facturaProveedor.trim().isEmpty()) {
            throw new IllegalArgumentException("La factura del proveedor es obligatoria.");
        }
        this.facturaProveedor = facturaProveedor.trim();
    }
}
