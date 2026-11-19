public class AdministradoraProducto {
    public static final int MAXIMO_PRODUCTOS = 100;
    private Producto[] productos;
    private int contadorProductos;

    public AdministradoraProducto() {
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
            if (productos[i].getCodigo().equalsIgnoreCase(codigo.trim())) {
                return productos[i];
            }
        }
        return null;
    }

    public boolean actualizarProducto(String codigo, String descripcion, double precio, int inventarioMinimo,
                                      Proveedor proveedor, Ubicacion ubicacion) {
        Producto encontrado = buscarProducto(codigo);
        if (encontrado == null || !encontrado.isActivo() || !referenciasValidas(proveedor, ubicacion)) {
            return false;
        }
        encontrado.setDescripcion(descripcion);
        encontrado.setPrecio(precio);
        encontrado.setInventarioMinimo(inventarioMinimo);
        encontrado.setProveedor(proveedor);
        encontrado.setUbicacion(ubicacion);
        return true;
    }

    public boolean darBajaProducto(String codigo) {
        Producto encontrado = buscarProducto(codigo);
        if (encontrado == null || !encontrado.isActivo()) {
            return false;
        }
        encontrado.setActivo(false);
        return true;
    }

    private boolean referenciasValidas(Proveedor proveedor, Ubicacion ubicacion) {
        return proveedor != null && proveedor.isActivo() && ubicacion != null && ubicacion.isActivo();
    }

    public Producto[] getProductos() { return productos; }
    public int getContadorProductos() { return contadorProductos; }
}
