public class AdministradoraProductos {
    public static final int MAXIMO_PRODUCTOS = 100;
    private Producto[] productos;
    private int contadorProductos;

    public AdministradoraProductos() {
        productos = new Producto[MAXIMO_PRODUCTOS];
        contadorProductos = 0;
    }

    public boolean agregarProducto(String codigo, String descripcion, double precio, int inventarioMinimo,
                                   Proveedor proveedor, Ubicacion ubicacion) {
        if (codigo == null || codigo.trim().isEmpty() || contadorProductos >= MAXIMO_PRODUCTOS
                || buscarProducto(codigo) != null || !referenciasValidas(proveedor, ubicacion)) {
            return false;
        }
        productos[contadorProductos] = new Producto(codigo, descripcion, precio, inventarioMinimo, proveedor, ubicacion);
        contadorProductos++;
        return true;
    }

    public Producto buscarProducto(String codigo) {
        if (codigo == null) {
            return null;
        }
        for (int i = 0; i < contadorProductos; i++) {
            if (productos[i].obtenerCodigo().equalsIgnoreCase(codigo.trim())) {
                return productos[i];
            }
        }
        return null;
    }

    public boolean actualizarProducto(String codigo, String descripcion, double precio, int inventarioMinimo,
                                      Proveedor proveedor, Ubicacion ubicacion) {
        Producto encontrado = buscarProducto(codigo);
        if (encontrado == null || !encontrado.obtenerActivo() || !referenciasValidas(proveedor, ubicacion)) {
            return false;
        }
        encontrado.asignarDescripcion(descripcion);
        encontrado.asignarPrecio(precio);
        encontrado.asignarInventarioMinimo(inventarioMinimo);
        encontrado.asignarProveedor(proveedor);
        encontrado.asignarUbicacion(ubicacion);
        return true;
    }

    public boolean darBajaProducto(String codigo) {
        Producto encontrado = buscarProducto(codigo);
        if (encontrado == null || !encontrado.obtenerActivo()) {
            return false;
        }
        encontrado.asignarActivo(false);
        return true;
    }

    private boolean referenciasValidas(Proveedor proveedor, Ubicacion ubicacion) {
        return proveedor != null && proveedor.obtenerActivo() && ubicacion != null && ubicacion.obtenerActivo();
    }

    public Producto[] obtenerProductos() { return productos; }
    public int obtenerContadorProductos() { return contadorProductos; }
}
