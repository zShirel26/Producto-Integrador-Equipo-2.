import java.time.LocalDate;

public class AdministradoraMovimientos {

    public static final int MAXIMO_MOVIMIENTOS = 500;
    public static final int INDICE_NO_ENCONTRADO = -1;

    private Movimiento[] movimientos;
    private int contadorMovimientos;

    public AdministradoraMovimientos() {
        movimientos = new Movimiento[MAXIMO_MOVIMIENTOS];
        contadorMovimientos = 0;
    }

    public Entrada registrarEntrada(String folio, LocalDate fecha, Producto producto, int cantidad, String facturaProveedor) {
        Entrada entrada = null;
        if (puedeRegistrar(folio, producto, cantidad)) {
            entrada = new Entrada(folio, fecha, producto, cantidad, facturaProveedor);
            movimientos[contadorMovimientos] = entrada;
            contadorMovimientos++;
        }
        return entrada;
    }

    public Salida registrarSalida(String folio, LocalDate fecha, Producto producto, int cantidad, String unidadDestino) {
        Salida salida = null;
        if (puedeRegistrar(folio, producto, cantidad)) {
            if (calcularExistencia(producto.getCodigo()) >= cantidad) {
                salida = new Salida(folio, fecha, producto, cantidad, unidadDestino);
                movimientos[contadorMovimientos] = salida;
                contadorMovimientos++;
            }
        }
        return salida;
    }

    public boolean cancelarMovimiento(String folio) {
        boolean cancelado = false;
        int indice = buscarIndiceMovimiento(folio);
        if (indice != INDICE_NO_ENCONTRADO) {
            Movimiento m = movimientos[indice];
            int existenciaResultante = calcularExistencia(m.getProducto().getCodigo()) - m.calcularVariacion();
            if (existenciaResultante >= 0) {
                for (int i = indice; i < contadorMovimientos - 1; i++) {
                    movimientos[i] = movimientos[i + 1];
                }
                movimientos[contadorMovimientos - 1] = null;
                contadorMovimientos--;
                cancelado = true;
            }
        }
        return cancelado;
    }

    public boolean existeMovimiento(String folio) {
        return buscarIndiceMovimiento(folio) != INDICE_NO_ENCONTRADO;
    }

    public int buscarIndiceMovimiento(String folio) {
        int indiceEncontrado = INDICE_NO_ENCONTRADO;
        if (folio != null) {
            for (int i = 0; i < contadorMovimientos; i++) {
                if (movimientos[i].getFolio().equalsIgnoreCase(folio.trim())) {
                    indiceEncontrado = i;
                    break;
                }
            }
        }
        return indiceEncontrado;
    }

    public Movimiento buscarMovimiento(String folio) {
        Movimiento encontrado = null;
        int indice = buscarIndiceMovimiento(folio);
        if (indice != INDICE_NO_ENCONTRADO) {
            encontrado = movimientos[indice];
        }
        return encontrado;
    }

    public int calcularExistencia(String codigoProducto) {
        int existencia = 0;
        if (codigoProducto != null) {
            for (int i = 0; i < contadorMovimientos; i++) {
                if (movimientos[i].getProducto().getCodigo().equalsIgnoreCase(codigoProducto.trim())) {
                    existencia += movimientos[i].calcularVariacion();
                }
            }
        }
        return existencia;
    }

    public Movimiento[] getMovimientos() {
        return movimientos;
    }

    public int getContadorMovimientos() {
        return contadorMovimientos;
    }

    private boolean puedeRegistrar(String folio, Producto producto, int cantidad) {
        return folio != null && !folio.trim().isEmpty() && producto != null && producto.isActivo() && cantidad >= Movimiento.CANTIDAD_MINIMA && contadorMovimientos < MAXIMO_MOVIMIENTOS && !existeMovimiento(folio);
    }
}