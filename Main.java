public class Main {

    public static void main(String[] args) {
        AdministradoraProveedores admProveedores = new AdministradoraProveedores();
        AdministradoraUbicaciones admUbicaciones = new AdministradoraUbicaciones();
        AdministradoraProductos admProductos = new AdministradoraProductos();
        AdministradoraMovimientos admMovimientos = new AdministradoraMovimientos();

        InterfazConsola interfaz = new InterfazConsola(
                admProveedores, admUbicaciones, admProductos, admMovimientos);

        try {
            interfaz.ejecutarMenuPrincipal();
        } finally {
            interfaz.cerrarRecursos();
        }
    }
}
