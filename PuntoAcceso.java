public class PuntoAcceso {
    private String codigo;
    private String nombre;
    private String ubicacion;
    private int capacidadMaximaPorHora;
    private String estado;

    public PuntoAcceso(String codigo, String nombre, String ubicacion, int capacidadMaximaPorHora, String estado) {
        setCapacidadMaximaPorHora(capacidadMaximaPorHora);
        this.codigo = codigo;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getCapacidadMaximaPorHora() {
        return capacidadMaximaPorHora;
    }

    public void setCapacidadMaximaPorHora(int capacidadMaximaPorHora) {
        if (capacidadMaximaPorHora <= 0) {
            throw new IllegalArgumentException("La capacidad maxima por hora debe ser mayor que 0.");
        }
        this.capacidadMaximaPorHora = capacidadMaximaPorHora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "PuntoAcceso [Codigo=" + codigo + ", Nombre=" + nombre + ", Ubicacion=" + ubicacion
                + ", Capacidad/Hora=" + capacidadMaximaPorHora + ", Estado=" + estado + "]";
    }
}