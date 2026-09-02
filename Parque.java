import java.util.ArrayList;

public class Parque {
    private String nombre;
    private String codigo;
    private String encargado;
    private PuntoAcceso[] puntosAcceso;
    private ArrayList<Visitante> visitantes;

    public Parque(String nombre, String codigo, String encargado) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.encargado = encargado;
        this.puntosAcceso = new PuntoAcceso[5];
        this.visitantes = new ArrayList<Visitante>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getEncargado() {
        return encargado;
    }

    public PuntoAcceso[] getPuntosAcceso() {
        return puntosAcceso;
    }

    public ArrayList<Visitante> getVisitantes() {
        return visitantes;
    }

    // Operaciones con puntos de accesos 
    public boolean habilitarPuntoAcceso(int posicion, PuntoAcceso punto) {
        if (posicion < 0 || posicion >= puntosAcceso.length) {
            throw new IndexOutOfBoundsException("La posicion " + posicion + " esta fuera de los limites del arreglo (0-4).");
        }
        if (puntosAcceso[posicion] != null) {
            return false; // ya se ocupó la posición
        }
        puntosAcceso[posicion] = punto;
        return true;
    }

    public PuntoAcceso obtenerPuntoAcceso(int posicion) {
        if (posicion < 0 || posicion >= puntosAcceso.length) {
            throw new IndexOutOfBoundsException("La posicion " + posicion + " esta fuera de los limites del arreglo (0-4).");
        }
        return puntosAcceso[posicion];
    }

    public boolean cerrarPuntoAcceso(int posicion) {
        if (posicion < 0 || posicion >= puntosAcceso.length) {
            throw new IndexOutOfBoundsException("La posicion " + posicion + " esta fuera de los limites del arreglo (0-4).");
        }
        if (puntosAcceso[posicion] == null) {
            return false; // Ya estaba vacío
        }
        puntosAcceso[posicion] = null;
        return true;
    }

    // Operaciones con visitante (ArrayList)
    public boolean registrarVisitante(Visitante visitante) {
        if (buscarVisitante(visitante.getCodigoEntrada()) != null) {
            return false; // el visitante ya está registrado
        }
        visitantes.add(visitante);
        return true;
    }

    public Visitante buscarVisitante(String codigoEntrada) {
        for (Visitante v : visitantes) {
            if (v.getCodigoEntrada().equalsIgnoreCase(codigoEntrada)) {
                return v;
            }
        }
        return null;
    }

    public boolean eliminarVisitante(String codigoEntrada) {
        Visitante v = buscarVisitante(codigoEntrada);
        if (v != null) {
            return visitantes.remove(v);
        }
        return false;
    }

    // Métodos de reportes y cálculos
    public int contarPuntosAccesoHabilitados() {
        int contador = 0;
        for (PuntoAcceso p : puntosAcceso) {
            if (p != null) {
                contador++;
            }
        }
        return contador;
    }

    public int contarEspaciosDisponiblesPuntosAcceso() {
        return puntosAcceso.length - contarPuntosAccesoHabilitados();
    }

    public PuntoAcceso calcularPuntoAccesoMayorCapacidad() {
        PuntoAcceso mayor = null;
        for (PuntoAcceso p : puntosAcceso) {
            if (p != null) {
                if (mayor == null || p.getCapacidadMaximaPorHora() > mayor.getCapacidadMaximaPorHora()) {
                    mayor = p;
                }
            }
        }
        return mayor;
    }

    public int contarVisitantesRegistrados() {
        return visitantes.size();
    }

    public Visitante calcularVisitanteMayorPuntos() {
        if (visitantes.isEmpty()) return null;
        Visitante mayor = visitantes.get(0);
        for (Visitante v : visitantes) {
            if (v.getPuntosAcumulados() > mayor.getPuntosAcumulados()) {
                mayor = v;
            }
        }
        return mayor;
    }

    public Visitante calcularVisitanteMayorAtracciones() {
        if (visitantes.isEmpty()) return null;
        Visitante mayor = visitantes.get(0);
        for (Visitante v : visitantes) {
            if (v.getAtraccionesVisitadas() > mayor.getAtraccionesVisitadas()) {
                mayor = v;
            }
        }
        return mayor;
    }

    public double calcularPromedioEdad() {
        if (visitantes.isEmpty()) return 0.0;
        double suma = 0;
        for (Visitante v : visitantes) {
            suma += v.getEdad();
        }
        return suma / visitantes.size();
    }
}