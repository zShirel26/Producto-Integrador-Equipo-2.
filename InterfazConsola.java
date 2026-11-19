import java.time.LocalDate;
import java.util.Scanner;

public class InterfazConsola {

    public static final int OPCION_SALIR = 0;
    public static final int OPCION_GESTIONAR_PROVEEDOR = 1;
    public static final int OPCION_GESTIONAR_UBICACION = 2;
    public static final int OPCION_GESTIONAR_PRODUCTO = 3;
    public static final int OPCION_REGISTRAR_ENTRADA = 4;
    public static final int OPCION_REGISTRAR_SALIDA = 5;
    public static final int OPCION_CONSULTAR_EXISTENCIA = 6;
    public static final int OPCION_CONSULTAR_HISTORIAL = 7;
    public static final int OPCION_LISTAR_BAJO_MINIMO = 8;
    public static final int OPCION_CANCELAR_MOVIMIENTO = 9;
    public static final int OPCION_INVALIDA = -1;

    private static final int NIVEL_INICIAL_A = 3;
    private static final int NIVEL_INICIAL_B = 2;
    private static final int NIVEL_INICIAL_C = 1;
    private static final double PRECIO_BALATA = 1450.0;
    private static final double PRECIO_FILTRO = 280.0;
    private static final double PRECIO_RODAMIENTO = 920.0;
    private static final double PRECIO_AMORTIGUADOR = 3200.0;
    private static final int MINIMO_BALATA = 8;
    private static final int MINIMO_FILTRO = 15;
    private static final int MINIMO_RODAMIENTO = 6;
    private static final int MINIMO_AMORTIGUADOR = 4;
    private static final int ENTRADA_INICIAL_BALATA = 12;
    private static final int ENTRADA_INICIAL_FILTRO = 20;
    private static final int ENTRADA_INICIAL_RODAMIENTO = 10;
    private static final int ENTRADA_INICIAL_AMORTIGUADOR = 5;
    private static final int SALIDA_VALOR_BALATA = 5;
    private static final int SALIDA_EXCESIVA_PRUEBA = 99;

    private Scanner lector;
    private AdministradoraProveedores admProveedores;
    private AdministradoraUbicaciones admUbicaciones;
    private AdministradoraProductos admProductos;
    private AdministradoraMovimientos admMovimientos;

    public InterfazConsola(AdministradoraProveedores admProveedores, AdministradoraUbicaciones admUbicaciones, AdministradoraProductos admProductos, AdministradoraMovimientos admMovimientos) {
        this.lector = new Scanner(System.in);
        this.admProveedores = admProveedores;
        this.admUbicaciones = admUbicaciones;
        this.admProductos = admProductos;
        this.admMovimientos = admMovimientos;
    }

    public void cargarDatosIniciales() {
        try {
            admProveedores.agregarProveedor("PRV-01", "Frenos y Balatas del Bajio", "3312345678");
            admProveedores.agregarProveedor("PRV-02", "Filtros Diesel de Occidente", "3398765432");
            admProveedores.agregarProveedor("PRV-03", "Suspensiones y Rodamientos MX", "3355511122");

            admUbicaciones.agregarUbicacion("UBI-01", "A", "E1", NIVEL_INICIAL_A);
            admUbicaciones.agregarUbicacion("UBI-02", "A", "E2", NIVEL_INICIAL_B);
            admUbicaciones.agregarUbicacion("UBI-03", "B", "E1", NIVEL_INICIAL_C);
            admUbicaciones.agregarUbicacion("UBI-04", "B", "E2", NIVEL_INICIAL_B);

            Proveedor prv1 = admProveedores.buscarProveedor("PRV-01");
            Proveedor prv2 = admProveedores.buscarProveedor("PRV-02");
            Proveedor prv3 = admProveedores.buscarProveedor("PRV-03");
            Ubicacion ubi1 = admUbicaciones.buscarUbicacion("UBI-01");
            Ubicacion ubi2 = admUbicaciones.buscarUbicacion("UBI-02");
            Ubicacion ubi3 = admUbicaciones.buscarUbicacion("UBI-03");
            Ubicacion ubi4 = admUbicaciones.buscarUbicacion("UBI-04");

            admProductos.agregarProducto("BAL-001", "Juego de balatas para freno neumatico", PRECIO_BALATA, MINIMO_BALATA, prv1, ubi1);
            admProductos.agregarProducto("FIL-001", "Filtro separador de diesel", PRECIO_FILTRO, MINIMO_FILTRO, prv2, ubi2);
            admProductos.agregarProducto("ROD-001", "Rodamiento conico de maza", PRECIO_RODAMIENTO, MINIMO_RODAMIENTO, prv3, ubi3);
            admProductos.agregarProducto("AMO-001", "Amortiguador de cabina", PRECIO_AMORTIGUADOR, MINIMO_AMORTIGUADOR, prv3, ubi4);

            LocalDate hoy = LocalDate.now();
            admMovimientos.registrarEntrada("E-001", hoy, admProductos.buscarProducto("BAL-001"), ENTRADA_INICIAL_BALATA, "FAC-1001");
            admMovimientos.registrarEntrada("E-002", hoy, admProductos.buscarProducto("FIL-001"), ENTRADA_INICIAL_FILTRO, "FAC-1002");
            admMovimientos.registrarEntrada("E-003", hoy, admProductos.buscarProducto("ROD-001"), ENTRADA_INICIAL_RODAMIENTO, "FAC-1003");
            admMovimientos.registrarEntrada("E-004", hoy, admProductos.buscarProducto("AMO-001"), ENTRADA_INICIAL_AMORTIGUADOR, "FAC-1004");
            admMovimientos.registrarSalida("S-001", hoy, admProductos.buscarProducto("BAL-001"), SALIDA_VALOR_BALATA, "TRAILER-12");
        } catch (Exception e) {
            mostrarResultadoOperacion("Error al cargar datos iniciales: " + e.getMessage());
        }
    }

    public void mostrarResumenDemostracion() {
        System.out.println("==============================================");
        System.out.println(" CONTROL DE INVENTARIO DE REFACCIONES");
        System.out.println("==============================================");
        System.out.println("Datos de demostracion cargados:");
        System.out.println(" - Proveedores: " + admProveedores.getContadorProveedores());
        System.out.println(" - Ubicaciones: " + admUbicaciones.getContadorUbicaciones());
        System.out.println(" - Productos:   " + admProductos.getContadorProductos());
        System.out.println(" - Movimientos: " + admMovimientos.getContadorMovimientos());

        Producto balata = admProductos.buscarProducto("BAL-001");
        if (balata != null) {
            System.out.println("\nPrueba de validacion: salida de " + SALIDA_EXCESIVA_PRUEBA + " piezas de " + balata.getDescripcion());
            try {
                Salida s = admMovimientos.registrarSalida("S-PRUEBA", LocalDate.now(), balata, SALIDA_EXCESIVA_PRUEBA, "PRUEBA");
                if (s == null) {
                    System.out.println(" -> Rechazada: no hay existencias suficientes (correcto).");
                } else {
                    System.out.println(" -> ATENCION: se acepto una salida mayor a la existencia.");
                }
            } catch (Exception e) {
                System.out.println(" -> Rechazada: " + e.getMessage());
            }
        }
        System.out.println();
    }

    public void ejecutarMenuPrincipal() {
        int opcion;
        do {
            imprimirOpcionesMenu();
            opcion = leerOpcion("Seleccione una opcion: ");
            procesarOpcionSeleccionada(opcion);
        } while (opcion != OPCION_SALIR);
    }

    public void imprimirOpcionesMenu() {
        System.out.println("\n------------- MENU PRINCIPAL -------------");
        System.out.println(OPCION_GESTIONAR_PROVEEDOR + ". Gestionar proveedores");
        System.out.println(OPCION_GESTIONAR_UBICACION + ". Gestionar ubicaciones");
        System.out.println(OPCION_GESTIONAR_PRODUCTO + ". Gestionar productos");
        System.out.println(OPCION_REGISTRAR_ENTRADA + ". Registrar entrada de mercancia");
        System.out.println(OPCION_REGISTRAR_SALIDA + ". Registrar salida a taller");
        System.out.println(OPCION_CONSULTAR_EXISTENCIA + ". Consultar existencia de un producto");
        System.out.println(OPCION_CONSULTAR_HISTORIAL + ". Consultar historial de movimientos");
        System.out.println(OPCION_LISTAR_BAJO_MINIMO + ". Listar productos bajo el minimo");
        System.out.println(OPCION_CANCELAR_MOVIMIENTO + ". Cancelar movimiento");
        System.out.println(OPCION_SALIR + ". Salir");
        System.out.println("------------------------------------------");
    }

    public void procesarOpcionSeleccionada(int opcion) {
        switch (opcion) {
            case OPCION_GESTIONAR_PROVEEDOR:
                capturarProveedor();
                break;
            case OPCION_GESTIONAR_UBICACION:
                capturarUbicacion();
                break;
            case OPCION_GESTIONAR_PRODUCTO:
                capturarProducto();
                break;
            case OPCION_REGISTRAR_ENTRADA:
                capturarEntrada();
                break;
            case OPCION_REGISTRAR_SALIDA:
                capturarSalida();
                break;
            case OPCION_CONSULTAR_EXISTENCIA:
                consultarExistenciaProducto();
                break;
            case OPCION_CONSULTAR_HISTORIAL:
                mostrarHistorial();
                break;
            case OPCION_LISTAR_BAJO_MINIMO:
                mostrarProductosBajoMinimo();
                break;
            case OPCION_CANCELAR_MOVIMIENTO:
                capturarCancelacionMovimiento();
                break;
            case OPCION_SALIR:
                System.out.println("Hasta luego.");
                break;
            default:
                mostrarResultadoOperacion("Opcion no valida.");
        }
    }

    public void capturarProveedor() {
        System.out.println("\n--- PROVEEDORES ---");
        System.out.println("1. Registrar  2. Actualizar  3. Dar de baja  4. Listar  0. Volver");
        int op = leerOpcion("Opcion: ");
        switch (op) {
            case 1: {
                String codigo = leerTextoNoVacio("Codigo: ");
                String nombre = leerTextoNoVacio("Nombre de la empresa: ");
                String tel = leerTextoNoVacio("Telefono: ");
                mostrarResultadoOperacion(ejecutarBooleano(
                        () -> admProveedores.agregarProveedor(codigo, nombre, tel),
                        "Proveedor registrado.", "No se pudo registrar (codigo repetido o datos invalidos)."));
                break;
            }
            case 2: {
                String codigo = leerTextoNoVacio("Codigo del proveedor a actualizar: ");
                if (admProveedores.buscarProveedor(codigo) == null) {
                    mostrarResultadoOperacion("Proveedor no encontrado.");
                    break;
                }
                String nombre = leerTextoNoVacio("Nuevo nombre: ");
                String tel = leerTextoNoVacio("Nuevo telefono: ");
                mostrarResultadoOperacion(ejecutarBooleano(
                        () -> admProveedores.actualizarProveedor(codigo, nombre, tel),
                        "Proveedor actualizado.", "No se pudo actualizar."));
                break;
            }
            case 3: {
                String codigo = leerTextoNoVacio("Codigo del proveedor a dar de baja: ");
                mostrarResultadoOperacion(ejecutarBooleano(
                        () -> admProveedores.darBajaProveedor(codigo),
                        "Proveedor dado de baja.", "No se pudo dar de baja."));
                break;
            }
            case 4:
                listarProveedores();
                break;
            case 0:
                break;
            default:
                mostrarResultadoOperacion("Opcion no valida.");
        }
    }

    private void listarProveedores() {
        System.out.println("\n--- Listado de proveedores ---");
        Proveedor[] lista = admProveedores.getProveedores();
        int total = admProveedores.getContadorProveedores();
        if (total == 0) {
            System.out.println("(sin registros)");
        }
        for (int i = 0; i < total; i++) {
            System.out.println((lista[i].isActivo() ? "[ACTIVO]   " : "[INACTIVO] ") + lista[i].obtenerFicha());
        }
    }

    public void capturarUbicacion() {
        System.out.println("\n--- UBICACIONES ---");
        System.out.println("1. Registrar  2. Actualizar  3. Dar de baja  4. Listar  0. Volver");
        int op = leerOpcion("Opcion: ");
        switch (op) {
            case 1: {
                String codigo = leerTextoNoVacio("Codigo: ");
                String pasillo = leerTextoNoVacio("Pasillo: ");
                String estante = leerTextoNoVacio("Estante: ");
                int nivel = leerEnteroPositivo("Nivel (" + Ubicacion.NIVEL_MINIMO + " a " + Ubicacion.NIVEL_MAXIMO + "): ");
                mostrarResultadoOperacion(ejecutarBooleano(
                        () -> admUbicaciones.agregarUbicacion(codigo, pasillo, estante, nivel),
                        "Ubicacion registrada.", "No se pudo registrar (codigo repetido o nivel fuera de rango)."));
                break;
            }
            case 2: {
                String codigo = leerTextoNoVacio("Codigo de la ubicacion a actualizar: ");
                if (admUbicaciones.buscarUbicacion(codigo) == null) {
                    mostrarResultadoOperacion("Ubicacion no encontrada.");
                    break;
                }
                String pasillo = leerTextoNoVacio("Nuevo pasillo: ");
                String estante = leerTextoNoVacio("Nuevo estante: ");
                int nivel = leerEnteroPositivo("Nuevo nivel (" + Ubicacion.NIVEL_MINIMO + " a " + Ubicacion.NIVEL_MAXIMO + "): ");
                mostrarResultadoOperacion(ejecutarBooleano(
                        () -> admUbicaciones.actualizarUbicacion(codigo, pasillo, estante, nivel),
                        "Ubicacion actualizada.", "No se pudo actualizar (nivel fuera de rango?)."));
                break;
            }
            case 3: {
                String codigo = leerTextoNoVacio("Codigo de la ubicacion a dar de baja: ");
                mostrarResultadoOperacion(ejecutarBooleano(
                        () -> admUbicaciones.darBajaUbicacion(codigo),
                        "Ubicacion dada de baja.", "No se pudo dar de baja."));
                break;
            }
            case 4:
                listarUbicaciones();
                break;
            case 0:
                break;
            default:
                mostrarResultadoOperacion("Opcion no valida.");
        }
    }

    private void listarUbicaciones() {
        System.out.println("\n--- Listado de ubicaciones ---");
        Ubicacion[] lista = admUbicaciones.getUbicaciones();
        int total = admUbicaciones.getContadorUbicaciones();
        if (total == 0) {
            System.out.println("(sin registros)");
        }
        for (int i = 0; i < total; i++) {
            System.out.println((lista[i].isActivo() ? "[ACTIVO]   " : "[INACTIVO] ") + lista[i].obtenerFicha());
        }
    }

    public void capturarProducto() {
        System.out.println("\n--- PRODUCTOS ---");
        System.out.println("1. Registrar  2. Actualizar  3. Dar de baja  4. Listar  0. Volver");
        int op = leerOpcion("Opcion: ");
        switch (op) {
            case 1:
                registrarNuevoProducto();
                break;
            case 2:
                actualizarProductoExistente();
                break;
            case 3:
                darBajaProducto();
                break;
            case 4:
                listarProductos();
                break;
            case 0:
                break;
            default:
                mostrarResultadoOperacion("Opcion no valida.");
        }
    }

    private void registrarNuevoProducto() {
        String codigo = leerTextoNoVacio("Codigo: ");
        String descripcion = leerTextoNoVacio("Descripcion: ");
        double precio = leerDecimalPositivo("Precio unitario: ");
        int minimo = leerEnteroPositivo("Inventario minimo: ");
        Proveedor prv = pedirProveedor();
        Ubicacion ubi = pedirUbicacion();
        if (prv == null || ubi == null) {
            mostrarResultadoOperacion("Proveedor o ubicacion no encontrados.");
            return;
        }
        mostrarResultadoOperacion(ejecutarBooleano(
                () -> admProductos.agregarProducto(codigo, descripcion, precio, minimo, prv, ubi),
                "Producto registrado.", "No se pudo registrar (codigo repetido o proveedor/ubicacion inactivos)."));
    }

    private void actualizarProductoExistente() {
        String codigo = leerTextoNoVacio("Codigo del producto a actualizar: ");
        if (admProductos.buscarProducto(codigo) == null) {
            mostrarResultadoOperacion("Producto no encontrado.");
            return;
        }
        String descripcion = leerTextoNoVacio("Nueva descripcion: ");
        double precio = leerDecimalPositivo("Nuevo precio unitario: ");
        int minimo = leerEnteroPositivo("Nuevo inventario minimo: ");
        Proveedor prv = pedirProveedor();
        Ubicacion ubi = pedirUbicacion();
        if (prv == null || ubi == null) {
            mostrarResultadoOperacion("Proveedor o ubicacion no encontrados.");
            return;
        }
        mostrarResultadoOperacion(ejecutarBooleano(
                () -> admProductos.actualizarProducto(codigo, descripcion, precio, minimo, prv, ubi),
                "Producto actualizado.", "No se pudo actualizar."));
    }

    private void darBajaProducto() {
        String codigo = leerTextoNoVacio("Codigo del producto a dar de baja: ");
        mostrarResultadoOperacion(ejecutarBooleano(
                () -> admProductos.darBajaProducto(codigo),
                "Producto dado de baja.", "No se pudo dar de baja."));
    }

    private Proveedor pedirProveedor() {
        return admProveedores.buscarProveedor(leerTextoNoVacio("Codigo del proveedor: "));
    }

    private Ubicacion pedirUbicacion() {
        return admUbicaciones.buscarUbicacion(leerTextoNoVacio("Codigo de la ubicacion: "));
    }

    private void listarProductos() {
        System.out.println("\n--- Listado de productos ---");
        Producto[] lista = admProductos.getProductos();
        int total = admProductos.getContadorProductos();
        if (total == 0) {
            System.out.println("(sin registros)");
        }
        for (int i = 0; i < total; i++) {
            Producto p = lista[i];
            int existencia = admMovimientos.calcularExistencia(p.getCodigo());
            System.out.println((p.isActivo() ? "[ACTIVO]   " : "[INACTIVO] ") + p.getCodigo() + " | " + p.getDescripcion() + " | $" + String.format("%.2f", p.getPrecio()) + " | Existencia: " + existencia + " | Minimo: " + p.getInventarioMinimo());
        }
    }

    public void capturarEntrada() {
        System.out.println("\n--- REGISTRAR ENTRADA ---");
        String folio = leerTextoNoVacio("Folio: ");
        Producto producto = admProductos.buscarProducto(leerTextoNoVacio("Codigo del producto: "));
        if (producto == null) {
            mostrarResultadoOperacion("Producto no encontrado.");
            return;
        }
        int cantidad = leerEnteroPositivo("Cantidad de piezas recibidas: ");
        String factura = leerTextoNoVacio("Numero de factura del proveedor: ");
        try {
            Entrada e = admMovimientos.registrarEntrada(folio, LocalDate.now(), producto, cantidad, factura);
            if (e == null) {
                mostrarResultadoOperacion("No se pudo registrar la entrada (folio repetido o producto inactivo).");
            } else {
                mostrarResultadoOperacion("Entrada registrada. Existencia actual: " + admMovimientos.calcularExistencia(producto.getCodigo()));
            }
        } catch (Exception ex) {
            mostrarResultadoOperacion("Error: " + ex.getMessage());
        }
    }

    public void capturarSalida() {
        System.out.println("\n--- REGISTRAR SALIDA A TALLER ---");
        String folio = leerTextoNoVacio("Folio: ");
        Producto producto = admProductos.buscarProducto(leerTextoNoVacio("Codigo del producto: "));
        if (producto == null) {
            mostrarResultadoOperacion("Producto no encontrado.");
            return;
        }
        int cantidad = leerEnteroPositivo("Cantidad de piezas a surtir: ");
        String unidad = leerTextoNoVacio("Unidad de transporte destino: ");
        try {
            Salida s = admMovimientos.registrarSalida(folio, LocalDate.now(), producto, cantidad, unidad);
            if (s == null) {
                mostrarResultadoOperacion("No se pudo registrar la salida (existencias insuficientes, folio repetido o producto inactivo).");
                return;
            }
            int restante = admMovimientos.calcularExistencia(producto.getCodigo());
            mostrarResultadoOperacion("Salida registrada. Existencia restante: " + restante);
            if (producto.esBajoMinimo(restante)) {
                mostrarResultadoOperacion("AVISO: el producto quedo por debajo del inventario minimo (" + producto.getInventarioMinimo() + "). Conviene pedir al proveedor.");
            }
        } catch (Exception ex) {
            mostrarResultadoOperacion("Error: " + ex.getMessage());
        }
    }

    public void capturarCancelacionMovimiento() {
        System.out.println("\n--- CANCELAR MOVIMIENTO ---");
        String folio = leerTextoNoVacio("Folio del movimiento a cancelar: ");
        try {
            if (admMovimientos.buscarMovimiento(folio) == null) {
                mostrarResultadoOperacion("Movimiento no encontrado.");
                return;
            }
            if (admMovimientos.cancelarMovimiento(folio)) {
                mostrarResultadoOperacion("Movimiento cancelado.");
            } else {
                mostrarResultadoOperacion("No se puede cancelar: el saldo quedaria negativo.");
            }
        } catch (Exception ex) {
            mostrarResultadoOperacion("Error: " + ex.getMessage());
        }
    }

    public void consultarExistenciaProducto() {
        System.out.println("\n--- CONSULTAR EXISTENCIA ---");
        Producto p = admProductos.buscarProducto(leerTextoNoVacio("Codigo del producto: "));
        if (p == null) {
            mostrarResultadoOperacion("Producto no encontrado.");
            return;
        }
        int existencia = admMovimientos.calcularExistencia(p.getCodigo());
        System.out.println(p.obtenerFicha());
        System.out.println("Existencia disponible: " + existencia + " piezas");
        System.out.println(p.esBajoMinimo(existencia) ? "Estado: BAJO EL MINIMO (" + p.getInventarioMinimo() + ")" : "Estado: existencia suficiente");
    }

    public void mostrarHistorial() {
        System.out.println("\n--- HISTORIAL DE MOVIMIENTOS ---");
        Producto p = admProductos.buscarProducto(leerTextoNoVacio("Codigo del producto: "));
        if (p == null) {
            mostrarResultadoOperacion("Producto no encontrado.");
            return;
        }
        System.out.println("Producto: " + p.getCodigo() + " - " + p.getDescripcion());
        Movimiento[] lista = admMovimientos.getMovimientos();
        int total = admMovimientos.getContadorMovimientos();
        int encontrados = 0;
        for (int i = 0; i < total; i++) {
            Movimiento m = lista[i];
            if (m != null && m.getProducto().getCodigo().equals(p.getCodigo())) {
                String signo = m.calcularVariacion() >= 0 ? "+" : "";
                System.out.println(m.getFolio() + " | " + m.getFecha() + " | " + m.obtenerTipoMovimiento() + " | " + signo + m.calcularVariacion() + " pzas | P.U. $" + String.format("%.2f", m.getPrecioUnitario()) + " | Importe $" + String.format("%.2f", m.calcularImporte()));
                encontrados++;
            }
        }
        if (encontrados == 0) {
            System.out.println("(sin movimientos)");
        }
        System.out.println("Existencia actual: " + admMovimientos.calcularExistencia(p.getCodigo()));
    }

    public void mostrarProductosBajoMinimo() {
        System.out.println("\n--- PRODUCTOS BAJO EL MINIMO ---");
        Producto[] lista = admProductos.getProductos();
        int total = admProductos.getContadorProductos();
        int encontrados = 0;
        for (int i = 0; i < total; i++) {
            Producto p = lista[i];
            if (!p.isActivo()) {
                continue;
            }
            int existencia = admMovimientos.calcularExistencia(p.getCodigo());
            if (p.esBajoMinimo(existencia)) {
                System.out.println(p.getCodigo() + " | " + p.getDescripcion() + " | Existencia: " + existencia + " | Minimo: " + p.getInventarioMinimo() + " | Proveedor: " + p.getProveedor().getNombre());
                encontrados++;
            }
        }
        if (encontrados == 0) {
            System.out.println("Todos los productos estan por encima del minimo.");
        }
    }

    public void mostrarResultadoOperacion(String mensaje) {
        System.out.println(">> " + mensaje);
    }

    public String leerTextoNoVacio(String mensaje) {
        String texto;
        do {
            System.out.print(mensaje);
            texto = lector.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("El dato no puede estar vacio.");
            }
        } while (texto.isEmpty());
        return texto;
    }

    public int leerEnteroPositivo(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = Integer.parseInt(lector.nextLine().trim());
                if (valor > 0) {
                    return valor;
                }
            } catch (NumberFormatException e) {
            }
            System.out.println("Ingrese un numero entero mayor a cero.");
        }
    }

    public double leerDecimalPositivo(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double valor = Double.parseDouble(lector.nextLine().trim());
                if (valor > 0) {
                    return valor;
                }
            } catch (NumberFormatException e) {
            }
            System.out.println("Ingrese un numero mayor a cero.");
        }
    }

    private int leerOpcion(String mensaje) {
        System.out.print(mensaje);
        try {
            return Integer.parseInt(lector.nextLine().trim());
        } catch (NumberFormatException e) {
            return OPCION_INVALIDA;
        }
    }

    private interface OperacionBooleana {
        boolean ejecutar();
    }

    private String ejecutarBooleano(OperacionBooleana op, String exito, String fallo) {
        try {
            return op.ejecutar() ? exito : fallo;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public void cerrarRecursos() {
        if (lector != null) {
            lector.close();
        }
    }

    public AdministradoraProveedores getAdmProveedores() { return admProveedores; }
    public void setAdmProveedores(AdministradoraProveedores admProveedores) { this.admProveedores = admProveedores; }
    
    public AdministradoraUbicaciones getAdmUbicaciones() { return admUbicaciones; }
    public void setAdmUbicaciones(AdministradoraUbicaciones admUbicaciones) { this.admUbicaciones = admUbicaciones; }
    
    public AdministradoraProductos getAdmProductos() { return admProductos; }
    public void setAdmProductos(AdministradoraProductos admProductos) { this.admProductos = admProductos; }
    
    public AdministradoraMovimientos getAdmMovimientos() { return admMovimientos; }
    public void setAdmMovimientos(AdministradoraMovimientos admMovimientos) { this.admMovimientos = admMovimientos; }
}