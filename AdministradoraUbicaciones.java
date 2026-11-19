public class AdministradoraUbicaciones {
    public static final int MAXIMO_UBICACION = 100;

    private Ubicacion[] ubicaciones;
    private int contadorUbicacion;

    public AdministradoraUbicaciones() {
        ubicaciones = new Ubicacion[MAXIMO_UBICACION];
        contadorUbicacion = 0;
    }

    public boolean agregarUbicacion(String codigo, String pasillo, String estante, int nivel) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return false;
        }
        if (nivel < Ubicacion.NIVEL_MINIMO || nivel > Ubicacion.NIVEL_MAXIMO) {
            return false;
        }
        if (contadorUbicacion >= MAXIMO_UBICACION || buscarUbicacion(codigo) != null) {
            return false;
        }
        ubicaciones[contadorUbicacion] = new Ubicacion(codigo, pasillo, estante, nivel);
        contadorUbicacion = contadorUbicacion + 1;
        return true;
    }

    public Ubicacion buscarUbicacion(String codigo) {
        String clave;
        int i;
        if (codigo == null) {
            return null;
        }
        clave = codigo.trim().toUpperCase();
        for (i = 0; i < contadorUbicacion; i = i + 1) {
            if (ubicaciones[i].getCodigo().equals(clave)) {
                return ubicaciones[i];
            }
        }
        return null;
    }

    public boolean actualizarUbicacion(String codigo, String pasillo, String estante, int nivel) {
        Ubicacion encontrada;
        encontrada = buscarUbicacion(codigo);
        if (encontrada == null || !encontrada.getActivo()) {
            return false;
        }
        if (nivel < Ubicacion.NIVEL_MINIMO || nivel > Ubicacion.NIVEL_MAXIMO) {
            return false;
        }
        encontrada.setPasillo(pasillo);
        encontrada.setEstante(estante);
        encontrada.setNivel(nivel);
        return true;
    }

    public boolean darBajaUbicacion(String codigo) {
        Ubicacion encontrada;
        encontrada = buscarUbicacion(codigo);
        if (encontrada == null || !encontrada.getActivo()) {
            return false;
        }
        encontrada.setActivo(false);
        return true;
    }

    public String consultarListadoUbicaciones() {
        StringBuilder salida;
        int i;
        if (contadorUbicacion == 0) {
            return "No existen ubicaciones registradas.";
        }
        salida = new StringBuilder();
        for (i = 0; i < contadorUbicacion; i = i + 1) {
            salida.append(ubicaciones[i].obtenerFicha()).append("\n");
        }
        return salida.toString().trim();
    }

    public Ubicacion[] getUbicaciones() {
        return ubicaciones;
    }

    public int getContadorUbicacion() {
        return contadorUbicacion;
    }
}
