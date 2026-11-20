public class AdministradoraProveedores {
    public static final int MAXIMO_PROVEEDOR = 100;

    private Proveedor[] proveedores;
    private int contadorProveedor;

    public AdministradoraProveedores() {
        proveedores = new Proveedor[MAXIMO_PROVEEDOR];
        contadorProveedor = 0;
    }

    public boolean agregarProveedor(String codigo, String nombre, String telefono) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return false;
        }
        if (contadorProveedor >= MAXIMO_PROVEEDOR || buscarProveedor(codigo) != null) {
            return false;
        }
        proveedores[contadorProveedor] = new Proveedor(codigo, nombre, telefono);
        contadorProveedor = contadorProveedor + 1;
        return true;
    }

    public Proveedor buscarProveedor(String codigo) {
        String clave;
        int i;
        if (codigo == null) {
            return null;
        }
        clave = codigo.trim().toUpperCase();
        for (i = 0; i < contadorProveedor; i = i + 1) {
            if (proveedores[i].obtenerCodigo().equals(clave)) {
                return proveedores[i];
            }
        }
        return null;
    }

    public boolean actualizarProveedor(String codigo, String nombre, String telefono) {
        Proveedor encontrado;
        encontrado = buscarProveedor(codigo);
        if (encontrado == null || !encontrado.obtenerActivo()) {
            return false;
        }
        encontrado.asignarNombre(nombre);
        encontrado.asignarTelefono(telefono);
        return true;
    }

    public boolean darBajaProveedor(String codigo) {
        Proveedor encontrado;
        encontrado = buscarProveedor(codigo);
        if (encontrado == null || !encontrado.obtenerActivo()) {
            return false;
        }
        encontrado.asignarActivo(false);
        return true;
    }

    public String consultarListadoProveedores() {
        StringBuilder salida;
        int i;
        if (contadorProveedor == 0) {
            return "No existen proveedores registrados.";
        }
        salida = new StringBuilder();
        for (i = 0; i < contadorProveedor; i = i + 1) {
            salida.append(proveedores[i].obtenerFicha()).append("\n");
        }
        return salida.toString().trim();
    }

    public Proveedor[] obtenerProveedores() {
        return proveedores;
    }

    public int obtenerContadorProveedores() {
        return contadorProveedor;
    }
}
