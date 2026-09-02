public class ControladorParque {
    private Parque parque;
    private Vista vista;

    public ControladorParque() {
        this.vista = new Vista();
    }

    public static void main(String[] args) {
        ControladorParque controlador = new ControladorParque();
        controlador.ejecutar();
    }

    public void ejecutar() {
        vista.mostrarMensaje("Este es el sistema de control del parque de atracciones de UVG");
        inicializarParque();

        boolean salir = false;
        while (!salir) {
            try {
                int opcion = vista.mostrarMenu();
                switch (opcion) {
                    case 1:
                        inicializarParque();
                        break;
                    case 2:
                        habilitarPuntoAcceso();
                        break;
                    case 3:
                        consultarPuntosAcceso();
                        break;
                    case 4:
                        consultarPuntoAccesoEspecifico();
                        break;
                    case 5:
                        modificarPuntoAcceso();
                        break;
                    case 6:
                        cerrarPuntoAcceso();
                        break;
                    case 7:
                        registrarVisitante();
                        break;
                    case 8:
                        consultarVisitantes();
                        break;
                    case 9:
                        buscarVisitante();
                        break;
                    case 10:
                        modificarVisitante();
                        break;
                    case 11:
                        eliminarVisitante();
                        break;
                    case 12:
                        mostrarReporteParque();
                        break;
                    case 13:
                        salir = true;
                        vista.mostrarMensaje("Gracias por utilizar el sistema.");
                        break;
                    default:
                        vista.mostrarError("Opción no valida.");
                }
            } catch (Exception e) {
                vista.mostrarError("Ocurrio un error inesperado: " + e.getMessage());
            } finally {
                // Bloque finally requerido para asegurar confirmación o estado limpio del ciclo
                if (!salir) {
                    System.out.println("----------------------------------------------------");
                }
            }
        }
    }

    private void inicializarParque() {
        vista.mostrarMensaje("Ingrese los datos para registrar un Parque:");
        String nombre = vista.pedirTexto("Nombre del Parque: ");
        String codigo = vista.pedirTexto("Codigo de Identificacion: ");
        String encargado = vista.pedirTexto("Nombre del Encargado: ");

        this.parque = new Parque(nombre, codigo, encargado);
        vista.mostrarMensaje("Parque '" + nombre + "' inicializado correctamente sin puntos de acceso ni visitantes.");
    }

    private void habilitarPuntoAcceso() {
        try {
            int pos = vista.pedirEntero("Ingrese la posicion del arreglo donde desea colocar el punto (0-4): ");
            if (pos < 0 || pos >= 5) {
                vista.mostrarError("Posicion fuera de limites. Debe ser un indice entre 0 y 4.");
                return;
            }
            if (parque.getPuntosAcceso()[pos] != null) {
                vista.mostrarError("La posicion seleccionada ya se encuentra ocupada.");
                return;
            }

            String codigo = vista.pedirTexto("Codigo del punto de acceso: ");
            String nombre = vista.pedirTexto("Nombre del punto de acceso: ");
            String ubicacion = vista.pedirTexto("Ubicacion: ");
            int capacidad = vista.pedirEntero("Capacidad maxima por hora (>0): ");
            String estado = vista.pedirTexto("Estado (ej. Habilitado): ");

            PuntoAcceso nuevoPunto = new PuntoAcceso(codigo, nombre, ubicacion, capacidad, estado);
            parque.habilitarPuntoAcceso(pos, nuevoPunto);
            vista.mostrarMensaje("Punto de acceso habilitado exitosamente en la posicion " + pos + ".");
        } catch (IllegalArgumentException e) {
            vista.mostrarError("Error al crear el Punto de Acceso: " + e.getMessage());
        }
    }

    private void consultarPuntosAcceso() {
        PuntoAcceso[] puntos = parque.getPuntosAcceso();
        boolean hayPuntos = false;
        System.out.println("\nPUNTOS DE ACCESO HABILITADOS");
        for (int i = 0; i < puntos.length; i++) {
            if (puntos[i] != null) {
                System.out.println("Posición " + i + ": " + puntos[i]);
                hayPuntos = true;
            }
        }
        if (!hayPuntos) {
            vista.mostrarMensaje("No hay puntos de acceso habilitados actualmente.");
        }
    }

    private void consultarPuntoAccesoEspecifico() {
        try {
            int pos = vista.pedirEntero("Ingrese la posicion a consultar (0-4): ");
            PuntoAcceso p = parque.obtenerPuntoAcceso(pos);
            if (p != null) {
                System.out.println("Informacion de la posicion " + pos + ": " + p);
            } else {
                vista.mostrarMensaje("La posicion " + pos + " esta vacia (contiene null).");
            }
        } catch (IndexOutOfBoundsException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void modificarPuntoAcceso() {
        try {
            int pos = vista.pedirEntero("Ingrese la posicion del punto de acceso a modificar (0-4): ");
            PuntoAcceso p = parque.obtenerPuntoAcceso(pos);
            if (p == null) {
                vista.mostrarError("No se puede modificar una posicion vacia (null).");
                return;
            }

            int nuevaCapacidad = vista.pedirEntero("Ingrese la nueva capacidad maxima por hora (>0): ");
            String nuevoEstado = vista.pedirTexto("Ingrese el nuevo estado: ");

            p.setCapacidadMaximaPorHora(nuevaCapacidad);
            p.setEstado(nuevoEstado);
            vista.mostrarMensaje("Punto de acceso modificado correctamente.");
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            vista.mostrarError("Error en la modificacion: " + e.getMessage());
        }
    }

    private void cerrarPuntoAcceso() {
        try {
            int pos = vista.pedirEntero("Ingrese la posicion del punto de acceso a cerrar (0-4): ");
            if (parque.cerrarPuntoAcceso(pos)) {
                vista.mostrarMensaje("El punto de acceso en la posicion " + pos + " ha sido cerrado y devuelto a null.");
            } else {
                vista.mostrarMensaje("La posicion " + pos + " ya se encontraba vacia.");
            }
        } catch (IndexOutOfBoundsException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void registrarVisitante() {
        try {
            String codigo = vista.pedirTexto("Codigo de entrada: ");
            if (parque.buscarVisitante(codigo) != null) {
                vista.mostrarError("Ya existe un visitante registrado con el codigo de entrada: " + codigo);
                return;
            }

            String nombre = vista.pedirTexto("Nombre del visitante: ");
            int edad = vista.pedirEntero("Edad (>0): ");
            int atracciones = vista.pedirEntero("Cantidad de atracciones visitadas (>=0): ");
            int puntos = vista.pedirEntero("Puntos acumulados (>=0): ");

            Visitante v = new Visitante(codigo, nombre, edad, atracciones, puntos);
            parque.registrarVisitante(v);
            vista.mostrarMensaje("Visitante registrado exitosamente.");
        } catch (IllegalArgumentException e) {
            vista.mostrarError("Error en el registro de visitante: " + e.getMessage());
        }
    }

    private void consultarVisitantes() {
        if (parque.getVisitantes().isEmpty()) {
            vista.mostrarMensaje("No hay visitantes registrados en la jornada.");
            return;
        }
        System.out.println("\n--- LISTA DE VISITANTES REGISTRADOS ---");
        for (Visitante v : parque.getVisitantes()) {
            System.out.println(v);
        }
    }

    private void buscarVisitante() {
        String codigo = vista.pedirTexto("Ingrese el codigo de entrada a buscar: ");
        Visitante v = parque.buscarVisitante(codigo);
        if (v != null) {
            System.out.println("Visitante encontrado: " + v);
        } else {
            vista.mostrarMensaje("No se encontro ningún visitante registrado con el codigo: " + codigo);
        }
    }

    private void modificarVisitante() {
        try {
            String codigo = vista.pedirTexto("Ingrese el codigo de entrada del visitante a modificar: ");
            Visitante v = parque.buscarVisitante(codigo);
            if (v == null) {
                vista.mostrarError("No se encontro un visitante con ese codigo.");
                return;
            }

            String nuevoNombre = vista.pedirTexto("Nuevo nombre: ");
            int nuevaEdad = vista.pedirEntero("Nueva edad (>0): ");
            int nuevasAtracciones = vista.pedirEntero("Nueva cantidad de atracciones visitadas (>=0): ");
            int nuevosPuntos = vista.pedirEntero("Nuevos puntos acumulados (>=0): ");

            v.setNombre(nuevoNombre);
            v.setEdad(nuevaEdad);
            v.setAtraccionesVisitadas(nuevasAtracciones);
            v.setPuntosAcumulados(nuevosPuntos);
            vista.mostrarMensaje("Informacion del visitante actualizada exitosamente.");
        } catch (IllegalArgumentException e) {
            vista.mostrarError("Error en la actualizacion: " + e.getMessage());
        }
    }

    private void eliminarVisitante() {
        String codigo = vista.pedirTexto("Ingrese el codigo de entrada del visitante a eliminar: ");
        if (parque.eliminarVisitante(codigo)) {
            vista.mostrarMensaje("Visitante eliminado exitosamente del registro.");
        } else {
            vista.mostrarError("No se encontro ningun visitante con ese codigo.");
        }
    }

    private void mostrarReporteParque() {
        System.out.println("\n REPORTE GENERAL DEL PARQUE ");
        System.out.println("Parque: " + parque.getNombre() + " (Codigo: " + parque.getCodigo() + ")");
        System.out.println("Encargado: " + parque.getEncargado());
        System.out.println("----------------------------------------------------");
        System.out.println("Puntos de acceso habilitados: " + parque.contarPuntosAccesoHabilitados());
        System.out.println("Espacios disponibles para puntos de acceso: " + parque.contarEspaciosDisponiblesPuntosAcceso());
        
        PuntoAcceso mayorCapacidad = parque.calcularPuntoAccesoMayorCapacidad();
        if (mayorCapacidad != null) {
            System.out.println("Punto de acceso con mayor capacidad: " + mayorCapacidad.getNombre() + " (" + mayorCapacidad.getCapacidadMaximaPorHora() + " por hora)");
        } else {
            System.out.println("Punto de acceso con mayor capacidad: N/A (No hay puntos habilitados)");
        }

        System.out.println("----------------------------------------------------");
        System.out.println("Cantidad de visitantes registrados: " + parque.contarVisitantesRegistrados());

        if (parque.getVisitantes().isEmpty()) {
            System.out.println("Visitante con mayor cantidad de puntos: N/A (Lista vacia)");
            System.out.println("Visitante con mayor cantidad de atracciones: N/A (Lista vacia)");
            System.out.println("Promedio de edad de visitantes: N/A (Lista vacia)");
        } else {
            Visitante mayorPuntos = parque.calcularVisitanteMayorPuntos();
            Visitante mayorAtracciones = parque.calcularVisitanteMayorAtracciones();
            System.out.println("Visitante con mayor cantidad de puntos: " + mayorPuntos.getNombre() + " (" + mayorPuntos.getPuntosAcumulados() + " pts)");
            System.out.println("Visitante con mayor cantidad de atracciones: " + mayorAtracciones.getNombre() + " (" + mayorAtracciones.getAtraccionesVisitadas() + " atracciones)");
            System.out.printf("Promedio de edad de los visitantes: %.2f años\n", parque.calcularPromedioEdad());
        }
    }
}