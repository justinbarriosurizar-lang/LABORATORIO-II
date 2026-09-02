public class Visitante {
    private String codigoEntrada;
    private String nombre;
    private int edad;
    private int atraccionesVisitadas;
    private int puntosAcumulados;

    public Visitante(String codigoEntrada, String nombre, int edad, int atraccionesVisitadas, int puntosAcumulados) {
        setEdad(edad);
        setAtraccionesVisitadas(atraccionesVisitadas);
        setPuntosAcumulados(puntosAcumulados);
        this.codigoEntrada = codigoEntrada;
        this.nombre = nombre;
    }

    public String getCodigoEntrada() {
        return codigoEntrada;
    }

    public void setCodigoEntrada(String codigoEntrada) {
        this.codigoEntrada = codigoEntrada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad <= 0) {
            throw new IllegalArgumentException("La edad debe ser mayor que 0.");
        }
        this.edad = edad;
    }

    public int getAtraccionesVisitadas() {
        return atraccionesVisitadas;
    }

    public void setAtraccionesVisitadas(int atraccionesVisitadas) {
        if (atraccionesVisitadas < 0) {
            throw new IllegalArgumentException("La cantidad de atracciones visitadas no puede ser negativa.");
        }
        this.atraccionesVisitadas = atraccionesVisitadas;
    }

    public int getPuntosAcumulados() {
        return puntosAcumulados;
    }

    public void setPuntosAcumulados(int puntosAcumulados) {
        if (puntosAcumulados < 0) {
            throw new IllegalArgumentException("Los puntos acumulados no pueden ser negativos.");
        }
        this.puntosAcumulados = puntosAcumulados;
    }

    @Override
    public String toString() {
        return "Visitante [Codigo Entrada=" + codigoEntrada + ", Nombre=" + nombre + ", Edad=" + edad
                + ", Atracciones=" + atraccionesVisitadas + ", Puntos=" + puntosAcumulados + "]";
    }
}
