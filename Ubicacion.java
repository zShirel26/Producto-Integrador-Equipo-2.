public class Ubicacion extends ElementoCatalogo {
    public static final int NIVEL_MINIMO = 1;
    public static final int NIVEL_MAXIMO = 10;

    private String pasillo;
    private String estante;
    private int nivel;

    public Ubicacion(String codigo, String pasillo, String estante, int nivel) {
        super(codigo);
        asignarPasillo(pasillo);
        asignarEstante(estante);
        asignarNivel(nivel);
    }

    @Override
    public String obtenerFicha() {
        String estado;
        estado = obtenerActivo() ? "ACTIVA" : "INACTIVA";
        return "[" + obtenerCodigo() + "] Pasillo: " + pasillo
                + " | Estante: " + estante + " | Nivel: " + nivel
                + " | Estado: " + estado;
    }

    public String obtenerPasillo() {
        return pasillo;
    }

    public void asignarPasillo(String pasillo) {
        if (pasillo != null && !pasillo.trim().isEmpty()) {
            this.pasillo = pasillo.trim().toUpperCase();
        } else if (this.pasillo == null) {
            this.pasillo = "A";
        }
    }

    public String obtenerEstante() {
        return estante;
    }

    public void asignarEstante(String estante) {
        if (estante != null && !estante.trim().isEmpty()) {
            this.estante = estante.trim().toUpperCase();
        } else if (this.estante == null) {
            this.estante = "R1";
        }
    }

    public int obtenerNivel() {
        return nivel;
    }

    public void asignarNivel(int nivel) {
        if (nivel >= NIVEL_MINIMO && nivel <= NIVEL_MAXIMO) {
            this.nivel = nivel;
        } else if (this.nivel < NIVEL_MINIMO) {
            this.nivel = NIVEL_MINIMO;
        }
    }
}
