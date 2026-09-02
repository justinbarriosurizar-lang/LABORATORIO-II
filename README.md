# LABORATORIO-II

## 1 y 2. Clases, propiedades, métodos y clases.

### Modelo

#### Clase: `Parque`
* **Propiedades:**
  * `private String nombre`: Nombre del parque.
  * `private String codigo`: Código de identificación.
  * `private String encargado`: Nombre del encargado.
  * `private PuntoAcceso[] puntosAcceso`: Arreglo básico de tamaño fijo (5 posiciones).
  * `private ArrayList<Visitante> visitantes`: Colección dinámica de visitantes.
* **Métodos:**
  * `public boolean habilitarPuntoAcceso(int posicion, PuntoAcceso punto)`
  * `public PuntoAcceso obtenerPuntoAcceso(int posicion)`
  * `public boolean cerrarPuntoAcceso(int posicion)`
  * `public boolean registrarVisitante(Visitante visitante)`
  * `public Visitante buscarVisitante(String codigoEntrada)`
  * `public boolean eliminarVisitante(String codigoEntrada)`
  * `public PuntoAcceso calcularPuntoAccesoMayorCapacidad()`
  * `public Visitante calcularVisitanteMayorPuntos()`
  * `public Visitante calcularVisitanteMayorAtracciones()`
  * `public double calcularPromedioEdad()`
  * `public int contarPuntosAccesoHabilitados()`
  * `public int contarEspaciosDisponiblesPuntosAcceso()`

---

#### Clase: `PuntoAcceso`
* **Propiedades:**
  * `private String codigo`: Código del punto de acceso.
  * `private String nombre`: Nombre descriptivo.
  * `private String ubicacion`: Ubicación física.
  * `private int capacidadMaximaPorHora`: Capacidad máxima por hora (debe ser > 0).
  * `private String estado`: Estado actual (ej. "Habilitado", "Cerrado").
* **Métodos:**
  * Constructores, *Getters* y *Setters* con validaciones de negocio.
  * `public String toString()`: Representación en texto del punto de acceso.

---

#### Clase: `Visitante`
* **Propiedades:**
  * `private String codigoEntrada`: Identificador único de entrada.
  * `private String nombre`: Nombre del visitante.
  * `private int edad`: Edad del visitante (debe ser > 0).
  * `private int atraccionesVisitadas`: Cantidad de atracciones (debe ser >= 0).
  * `private int puntosAcumulados`: Puntos acumulados (debe ser >= 0).
* **Métodos:**
  * Constructores, *Getters* y *Setters* con validaciones de negocio.
  * `public String toString()`: Representación en texto del visitante.

---

### Vista

#### Clase: `Vista`
* **Propiedades:**
  * `private Scanner scanner`: Instancia para la captura de entradas por consola.
* **Métodos:**
  * `public int mostrarMenu()`: Despliega las 13 opciones y solicita la elección del usuario.
  * `public String pedirTexto(String mensaje)`: Muestra un mensaje y captura una cadena.
  * `public int pedirEntero(String mensaje)`: Captura y valida un entero (maneja `InputMismatchException` limpiando el búfer con `scanner.nextLine()`).
  * `public void mostrarMensaje(String mensaje)`: Muestra mensajes informativos o de éxito.
  * `public void mostrarError(String mensaje)`: Muestra mensajes de error o excepciones capturadas.
  * `public void mostrarReporte(String reporte)`: Imprime el reporte general consolidado del parque.

---

### Controlador

#### Clase: `ControladorParque` (Driver Program)
* **Propiedades:**
  * `private Parque parque`: Instancia activa del modelo.
  * `private Vista vista`: Instancia de la vista para interacción con el usuario.
* **Métodos:**
  * `public static void main(String[] args)`: Punto de entrada principal que inicia la ejecución.
  * `public void ejecutar()`: Bucle principal del sistema que procesa las opciones del menú.
  * `private void inicializarParque()`: Solicita los datos iniciales e instancia un nuevo `Parque`.
  * `private void habilitarPuntoAcceso()`: Flujo interactivo para ingresar y validar un nuevo `PuntoAcceso`.
  * `private void consultarPuntosAcceso()`: Solicita a la vista listar los puntos habilitados.
  * `private void consultarPuntoAccesoEspecifico()`: Lee la posición deseada y muestra la información.
  * `private void modificarPuntoAcceso()`: Solicita posición y nuevos datos para actualizar la capacidad/estado.
  * `private void cerrarPuntoAcceso()`: Solicita posición y remueve el punto del arreglo.
  * `private void registrarVisitante()`: Solicita datos y agrega un nuevo `Visitante` al `ArrayList`.
  * `private void consultarVisitantes()`: Despliega el listado completo de visitantes.
  * `private void buscarVisitante()`: Solicita el código y muestra las coincidencias.
  * `private void modificarVisitante()`: Actualiza la información de un visitante registrado.
  * `private void eliminarVisitante()`: Solicita el código de entrada y lo elimina de la colección.
  * `private void mostrarReporteParque()`: Obtiene los cálculos del modelo y solicita a la vista mostrarlos.

---

## 3. Implementación con arreglos
* **Propiedad:** `puntosAcceso` en la clase `Parque`.
* **Tipo de Objetos:** `PuntoAcceso`.
* **Tamaño:** `5` posiciones fijas.

---

## 4. Implementación con ArrayList
* **Propiedad:** `visitantes` en la clase `Parque`.
* **Tipo de Objetos:** `Visitante`.

---

## 5. Modificadores de Visibilidad
* **Atributos:** Privados (`private`) para garantizar el principio de encapsulamiento.
* **Métodos:** Públicos (`public`) para proveer la interfaz de interacción entre los componentes del sistema.

---

## 6. Parámetros Requeridos por los métodos 
* `habilitarPuntoAcceso(int posicion, PuntoAcceso punto)`: Recibe la casilla del arreglo y el objeto a insertar.
* `obtenerPuntoAcceso(int posicion)` / `cerrarPuntoAcceso(int posicion)`: Reciben el índice entero de la posición a operar.
* `registrarVisitante(Visitante visitante)`: Recibe la instancia del visitante.
* `buscarVisitante(String codigoEntrada)` / `eliminarVisitante(String codigoEntrada)`: Reciben la cadena con el código único del visitante.

---

## 7. Valores iniciales y validación de los valores 
* **Inicialización:** Los objetos reciben sus valores iniciales a través de sus constructores parametrizados al instanciarse.
* **Validaciones antes de modificar:**
  * **`PuntoAcceso`:** La `capacidadMaximaPorHora` debe ser estricta y obligatoriamente mayor a 0 (`> 0`).
  * **`Visitante`:** La `edad` debe ser mayor a 0 (`> 0`). La `cantidad de atracciones visitadas` y los `puntos acumulados` deben ser mayores o iguales a 0 (`>= 0`).
  * Si alguna regla es violada, se debe lanzar una excepción de tipo `IllegalArgumentException`.

---

## 8. Control de posiciones y referencias en los arreglos ¿
* **Verificación:** Antes de realizar cualquier lectura o invocar métodos de un `PuntoAcceso`, se evalúa si la casilla contiene una referencia nula con `if (puntosAcceso[posicion] != null)`.
* **Liberación de espacios:** Para cerrar o deshabilitar un punto de acceso en el arreglo, se asigna `null` a la posición correspondiente: `puntosAcceso[posicion] = null`.

---

## 9. Operaciones dentro del ArrayList
* **Búsqueda:** Se realiza recorriendo la lista mediante un ciclo `for` o `for-each` y comparando el `codigoEntrada` de cada objeto `Visitante`.
* **Modificación:** Se localiza el visitante deseado en la lista, se validan los nuevos datos recibidos y se actualiza su estado mediante sus métodos *setter*.
* **Eliminación:** Se busca la posición o coincidencia del objeto por su `codigoEntrada` y se remueve de la lista utilizando el método `.remove()`.

---

## 10. Control de Excepciones
* **`InputMismatchException`:**
  * **Causa:** Ocurre cuando el usuario ingresa caracteres o un tipo de dato no esperado mediante `Scanner` (ej. texto en lugar de un entero).
  * **Manejo:** Se captura con un bloque `try-catch` en la lectura de entradas dentro del *driver program*, limpiando el búfer mediante `scanner.nextLine()`.
* **`IllegalArgumentException`:**
  * **Causa:** Se lanza explícitamente desde los constructores y métodos *setter* de las clases `PuntoAcceso` y `Visitante` al recibir datos numéricos que violen las reglas de negocio (ej. edades o capacidades <= 0).
  * **Manejo:** Se captura en la interfaz de usuario para mostrar un mensaje claro con el error y solicitar los datos nuevamente.
* **Uso de `finally`:**
  * Se implementa en las rutinas de captura o procesamiento de datos para asegurar la ejecución de acciones finales (como confirmaciones de finalización de módulo o limpieza de recursos) sin importar si la operación concluyó exitosamente o lanzó una excepción
