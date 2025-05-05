# Arquitectura y Organización

El sistema se desarrolló en **Java** siguiendo los principios de **Programación Orientada a Objetos**. Se organizó en dos paquetes principales:

## Paquetes del Sistema

### `models`
Contiene las clases de dominio:

- **Cultivo**:  
  Atributos: `nombre`, `variedad`, `superficie`, `fechaSiembra`, `estado`, y una lista de `actividad`. Cada cultivo está asociado a una parcela.

- **Parcela**:  
  Representa una porción de terreno. Atributos: `codigo`, `ubicación`, `tamaño`, y una lista de cultivos.

- **Actividad**:  
  Representa acciones realizadas sobre un cultivo (como riego o fertilización). Atributos: `tipo`, `fecha`, `completada`.

- **ElementoAgricola**:  
  Clase base común para objetos con `nombre`, `fechaSiembra` y `estado`, utilizada para abstraer `Cultivo`.

### `services`
Orquesta las operaciones que manipulan los objetos del dominio:

- **CultivoService**:  
  Administra la creación, edición, eliminación, búsqueda y asignación de actividades a cultivos.

- **ParcelaService**:  
  Administra la creación, edición, eliminación y asignación de cultivos a parcelas.

- **CSVService**:  
  Se encarga de leer el archivo CSV al iniciar la aplicación y guardar los cambios al finalizar.

---

# Colecciones y Persistencia

- Al iniciar el programa se lee el archivo **`cultivos.csv`** para poblar los objetos.
- Se usan estructuras como `ArrayList` para almacenar objetos en memoria.
- Al finalizar, se escriben los cambios en el CSV para mantener la persistencia.

---

# Modificadores de Acceso

Se utilizó:

- `private` para los atributos internos, garantizando la **encapsulación**.
- `public` para constructores, getters y setters, permitiendo un acceso controlado y seguro desde el exterior.

---

# Desafíos y Decisiones

## Diseño de jerarquía de clases
Fue un reto inicial establecer relaciones claras entre `Cultivo`, `Parcela` y `Actividad`. La solución fue crear una clase abstracta `ElementoAgricola` para factorizar atributos comunes.

## Relaciones entre objetos
Asociar correctamente los cultivos con parcelas y vincular múltiples actividades fue complejo. Se decidió manejar estas relaciones desde los servicios para centralizar la lógica del negocio.

## Control del CSV
La clase `CSVService` se encargó de:
- La **lectura inicial**: línea por línea desde el archivo.
- **Procesamiento**: creación de objetos `Cultivo`, `Parcela`, `Actividad`.
- **Escritura final**: guardar los cambios realizados.
- Validaciones básicas: detectar campos vacíos, mal formateados o duplicados.

---

# Reflexiones Finales / Autoevaluación

### ¿Qué fue lo más desafiante de implementar en POO?
### ¿Cómo controlaron la lectura y escritura de CSV?
### ¿Qué aprendizajes surgieron del proyecto?

---

## Santiago

> Se me hizo difícil manejar archivos CSV en Java; es más complejo de lo que parece, especialmente con campos entre comillas o fechas con formatos distintos. Al principio, los errores de parseo me frustraron, pero luego descubrí que imprimir mensajes detallados y hacer pruebas con datos de ejemplo era clave. También aprendí la importancia de pensar bien la estructura desde el inicio. Usar enums para estados fue un gran descubrimiento, y los tests automatizados habrían ahorrado tiempo. Lo mejor fue ver todo funcionando: lectura del CSV, servicios comunicándose y datos persistentes. Si empezara de nuevo, dedicaría más tiempo al diseño inicial y documentaría mejor. Fue desafiante, pero fortaleció mis habilidades.

---

## Martín

> Lo más desafiante fue la estructura de clases. Al principio no tenía idea de cómo organizarlas y me confundía con nombres de funciones o variables. Aprendí que tener una buena estructura de datos es clave. En proyectos grandes, escribir código limpio y comentarlo correctamente es fundamental para el trabajo en equipo. Mejora la eficiencia y permite entender el trabajo de los demás.

---

## Javi

> Aprendí que guardar datos en CSV es más complejo de lo que parece. Al separar en `parcelas.csv` y `actividades.csv`, surgieron problemas como formatos inconsistentes y relaciones rotas. Usar IDs únicos y validar cada campo fue crucial. Los momentos más frustrantes fueron cuando se corrompían archivos o se perdían relaciones, pero ver todo funcionar fue muy gratificante. Aprendí que la clave es estandarizar, documentar y hacer backups. Los errores me enseñaron más que los aciertos.

---

# Uso de Inteligencia Artificial

Se utilizó IA para resolver dudas puntuales y facilitar el desarrollo:

### ChatGPT como asistente creativo:
- Generación de estructuras de clases y métodos.
- Soluciones rápidas para manejo de fechas y expresiones regulares.
- Explicación de conceptos complejos en lenguaje simple.

### DeepSeek como analista técnico:
- Análisis del código para sugerir optimizaciones.
- Identificación de fallos en manejo de CSV.
- Propuestas de mejoras arquitectónicas para escalabilidad.
