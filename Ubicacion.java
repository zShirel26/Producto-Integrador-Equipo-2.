public class Ubicacion extends ElementoCatalogo {
    public static final int NIVEL_MINIMO = 1;
    public static final int NIVEL_MAXIMO = 10;

    private String pasillo;
    private String estante;
    private int nivel;

    public Ubicacion(String codigo, String pasillo, String estante, int nivel) {
        super(codigo);
        setPasillo(pasillo);
        setEstante(estante);
        setNivel(nivel);
    }

    @Override
    public String obtenerFicha() {
        String estado;
        estado = getActivo() ? "ACTIVA" : "INACTIVA";
        return "[" + getCodigo() + "] Pasillo: " + pasillo
                + " | Estante: " + estante + " | Nivel: " + nivel
                + " | Estado: " + estado;
    }

    public String getPasillo() {
        return pasillo;
    }

    public void setPasillo(String pasillo) {
        if (pasillo != null && !pasillo.trim().isEmpty()) {
            this.pasillo = pasillo.trim().toUpperCase();
        } else if (this.pasillo == null) {
            this.pasillo = "A";
        }
    }

    public String getEstante() {
        return estante;
    }

    public void setEstante(String estante) {
        if (estante != null && !estante.trim().isEmpty()) {
            this.estante = estante.trim().toUpperCase();
        } else if (this.estante == null) {
            this.estante = "R1";
        }
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        if (nivel >= NIVEL_MINIMO && nivel <= NIVEL_MAXIMO) {
            this.nivel = nivel;
        } else if (this.nivel < NIVEL_MINIMO) {
            this.nivel = NIVEL_MINIMO;
        }
    }
}
