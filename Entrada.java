import java.time.LocalDate;

public class Entrada extends Movimiento {
    private String facturaProveedor;

    public Entrada(String folio, LocalDate fecha, Producto producto, int cantidad, String facturaProveedor) {
        super(folio, fecha, producto, cantidad);
        setFacturaProveedor(facturaProveedor);
    }

    @Override
    public int calcularVariacion() { return getCantidad(); }

    @Override
    public String obtenerTipoMovimiento() { return "Entrada"; }

    public String getFacturaProveedor() { return facturaProveedor; }
    public void setFacturaProveedor(String facturaProveedor) {
        if (facturaProveedor == null || facturaProveedor.trim().isEmpty()) {
            throw new IllegalArgumentException("La factura del proveedor es obligatoria.");
        }
        this.facturaProveedor = facturaProveedor.trim();
    }
}
