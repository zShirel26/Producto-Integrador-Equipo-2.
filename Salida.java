import java.time.LocalDate;

public class Salida extends Movimiento {
    private String unidadDestino;

    public Salida(String folio, LocalDate fecha, Producto producto, int cantidad, String unidadDestino) {
        super(folio, fecha, producto, cantidad);
        setUnidadDestino(unidadDestino);
    }

    @Override
    public int calcularVariacion() { return -getCantidad(); }

    @Override
    public String obtenerTipoMovimiento() { return "Salida"; }

    public String getUnidadDestino() { return unidadDestino; }
    public void setUnidadDestino(String unidadDestino) {
        if (unidadDestino == null || unidadDestino.trim().isEmpty()) {
            throw new IllegalArgumentException("La unidad de destino es obligatoria.");
        }
        this.unidadDestino = unidadDestino.trim();
    }
}
