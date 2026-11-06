# Trabajo de consulta: Funciones anónimas

**Materia:** Programación Funcional  
**Entrega:** Semana 5  
**Lenguajes:** Scala, Java y Python


## Parte 1. Explorar: programación funcional en el mundo real

### Caso 1: Apache Spark

**Problema:** procesar y analizar grandes conjuntos de datos distribuidos entre varias máquinas.  
**Herramienta:** Apache Spark, con API para Scala.  
**Ventaja:** operaciones como `filter` y `map` permiten describir transformaciones de datos de manera concisa; Spark organiza su ejecución distribuida.  
**Ejemplo propio basado en la API:**

```scala
val numeros = spark.sparkContext.parallelize(Seq(1, 2, 3, 4, 5))
val paresDobles = numeros.filter(n => n % 2 == 0).map(n => n * 2)
println(paresDobles.collect().mkString(", ")) // 4, 8
```

La lambda de `filter` conserva los pares y la de `map` duplica cada valor. Requiere un entorno con Apache Spark; es un ejemplo ilustrativo y no forma parte de los archivos ejecutables del proyecto.

**Fuente:** [Documentación oficial de Apache Spark: RDD Programming Guide](https://spark.apache.org/docs/latest/rdd-programming-guide.html).

### Caso 2: Java Streams

**Problema:** seleccionar, transformar y resumir colecciones de registros en aplicaciones Java sin escribir bucles repetitivos.  
**Herramienta:** Java Stream API.  
**Ventaja:** las operaciones `filter`, `map` y `reduce` permiten expresar una secuencia de procesamiento declarativa y combinable.

```java
List<Integer> numeros = List.of(3, 7, 10, 12);
List<Integer> dobles = numeros.stream()
    .filter(n -> n >= 10)
    .map(n -> n * 2)
    .toList(); // [20, 24]
```

Aquí `n -> n >= 10` decide qué valores conservar y `n -> n * 2` los transforma. Es un ejemplo propio basado en la API, no código copiado de un repositorio empresarial.

**Fuente:** [Documentación oficial de Java Stream](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html).

## Parte 2. Entender: funciones anónimas

### ¿Qué es una función anónima y de dónde viene el nombre lambda?

Es una función que se escribe como una expresión sin asignarle necesariamente un nombre. Es útil cuando se necesita una operación corta que se entrega como argumento a otra función. El término *lambda* procede del cálculo lambda, un sistema matemático para representar funciones y su aplicación.

```scala
val numeros = List(1, 2, 3)
println(numeros.map(n => n * 2)) // List(2, 4, 6)
```

### Diferencia entre función nombrada y anónima

Una función nombrada tiene un identificador propio y puede reutilizarse con ese nombre; una anónima puede escribirse directamente donde se utiliza.

```scala
def duplicar(n: Int): Int = n * 2
val conNombre = List(1, 2, 3).map(duplicar)
val anonima = List(1, 2, 3).map(n => n * 2)
// Ambas producen List(2, 4, 6)
```

### Funciones como ciudadanos de primera clase

Significa que una función se puede guardar en una variable, pasar como argumento y devolver como resultado de otra función. Por eso `map` recibe una función que transforma elementos y `filter` recibe una función que decide si cada elemento se conserva.

```scala
val esPar: Int => Boolean = n => n % 2 == 0
println(List(1, 2, 3, 4).filter(esPar)) // List(2, 4)
```

### ¿Qué es un closure?

Es una función que utiliza variables del entorno donde fue creada. La función conserva acceso a ese entorno incluso cuando se utiliza después.

```scala
val descuento = 10
val aplicarDescuento = (precio: Int) => precio - descuento
println(aplicarDescuento(50)) // 40
```

## Parte 3. Aplicar: catálogo de problemas


| Tipo | Situación real | Operación Scala | Resultado |
|---|---|---|---|
| Selección | Mostrar productos que cuestan más de $100 | `filter` | Laptop, Monitor |
| Transformación | Calcular el precio duplicado de cada producto | `map` | Laptop:1800, Mouse:40, Teclado:90, Monitor:440, Cuaderno:8, Lapiz:2, Mochila:70, Audifonos:160, Agenda:24, USB:30 |
| Validación | Comprobar si hay algún producto con menos de 10 unidades | `exists` | true |
| Conteo | Contar los productos de Tecnología | `count` | 6 |
| Extremos | Encontrar el producto más caro | `maxBy` | Laptop |
| Aplanamiento | Unir nombres y categorías en una sola secuencia y mostrar los primeros seis datos | `flatMap` | Laptop, Tecnologia, Mouse, Tecnologia, Teclado, Tecnologia |
| Acumulación | Calcular el valor monetario total del inventario (precio × stock) | `foldLeft` | 17135 |

**Código en los tres lenguajes:** [Scala](scala/Problemas.scala) · [Java](java/Problemas.java) · [Python](python/problemas.py).


## Parte 4. Ampliar: cuatro operaciones nuevas

Archivo: [OperacionesNuevas.scala](operaciones/OperacionesNuevas.scala).

### 1. `sortBy`

**Qué hace:** ordena elementos a partir de una clave calculada para cada uno.  
**Lambda:** `p => p.precio`, de tipo `Producto => Int`.  
**Situación:** mostrar primero los productos más económicos. Se relaciona con *extremos* y agrega el tipo *ordenamiento*.

```scala
productos.sortBy(p => p.precio).take(3).map(p => p.nombre)
// List(Lapiz, Cuaderno, Agenda)
```

### 2. `groupBy`

**Qué hace:** agrupa los productos por una característica compartida.  
**Lambda:** `p => p.categoria`, de tipo `Producto => String`.  
**Situación:** organizar un inventario por categoría. Se relaciona con *conteo* y añade *agrupación*.

```scala
productos.groupBy(p => p.categoria).view.mapValues(_.size).toMap
// Map(Tecnologia -> 6, Papeleria -> 3, Accesorios -> 1)
```

### 3. `partition`

**Qué hace:** divide la lista en dos grupos según una condición.  
**Lambda:** `p => p.stock >= 30`, de tipo `Producto => Boolean`.  
**Situación:** separar productos con suficiente inventario de los que requieren atención. Se relaciona con *selección*.

```scala
val (suficientes, pocos) = productos.partition(p => p.stock >= 30)
// suficientes: 5, pocos: 5
```

### 4. `find`

**Qué hace:** devuelve el primer elemento que cumple una condición, si existe.  
**Lambda:** `p => p.precio < 5`, de tipo `Producto => Boolean`.  
**Situación:** localizar rápidamente un artículo económico. Se relaciona con *validación* y *selección*.

```scala
productos.find(p => p.precio < 5).map(p => p.nombre)
// Some(Cuaderno)
```

### 4.3. Combinar operaciones

**Pregunta:** ¿Qué categorías tienen más de 100 unidades de stock total, ordenadas de mayor a menor cantidad?

```scala
val resultado = productos.groupBy(p => p.categoria)
  .toList.filter(par => par._2.map(p => p.stock).sum > 100)
  .sortBy(par => -par._2.map(p => p.stock).sum)
  .map(par => s"${par._1}:${par._2.map(p => p.stock).sum}")
// Papeleria:340, Tecnologia:208
```

1. `groupBy` (nueva): reúne los productos por categoría.
2. `filter` (vista en clase): conserva solo las categorías cuyo stock total supera 100.
3. `sortBy` (nueva): ordena los grupos por stock total descendente usando el valor negativo como clave.
4. `map` (vista en clase): convierte los resultados en textos legibles.

## Parte 5. Comparar y concluir

### 5.1 Scala, Java y Python frente a frente

| Criterio | Scala | Java | Python |
|---|---|---|---|
| Sintaxis de lambda | `p => p.precio` | `p -> p.precio()` | `lambda p: p["precio"]` |
| Cómo se declara el tipo | Inferencia o `Producto => Int` | Inferencia contextual o interfaz funcional (`Function<Producto,Integer>`) | Tipado dinámico; anotaciones opcionales |
| Operaciones con listas | `map`, `filter`, `count`, `maxBy`, `foldLeft`, `flatMap` | `stream().map()`, `filter()`, `count()`, `max()`, `flatMap()`, `reduce()` | `map`, `filter`, `any`, `max(key=...)`, `reduce` |
| Restricción del lenguaje | Tipos estáticos; requiere compatibilidad de tipos | Las lambdas necesitan un tipo objetivo de interfaz funcional | `lambda` admite una sola expresión; no incluye sentencias múltiples |
| Legibilidad (opinión) | Muy clara para encadenar colecciones | Más extensa, pero explícita | Breve en ejemplos simples; las cadenas complejas pueden ser menos claras |

**¿En qué lenguaje fue más natural?** Para este ejercicio, Scala resultó más natural porque las operaciones se aplican directamente sobre la lista y el código conserva una estructura compacta. Python también fue sencillo para transformaciones básicas, aunque algunas operaciones requieren funciones auxiliares o conversiones a listas. Java necesitó más sintaxis, pero los Streams permiten expresar claramente el procesamiento.

**¿En qué tipo hubo más diferencias?** En *acumulación* y *aplanamiento*: Scala ofrece `foldLeft` y `flatMap` directamente; Java usa `reduce` y `flatMap` sobre Streams; Python utiliza `functools.reduce` y puede construir la lista aplanada con otras herramientas. Los tres permiten resolver el problema, pero con distinta sintaxis.

### 5.2 Conclusión

Las funciones anónimas son importantes porque permiten definir condiciones y transformaciones justo donde se necesitan, sin crear una función nombrada para cada operación pequeña. En listas de productos facilitan seleccionar artículos, calcular valores, comprobar condiciones y resumir inventarios. Además, el mismo razonamiento puede expresarse en Scala, Java y Python, aunque cambie la sintaxis.

El caso de Apache Spark muestra que estas ideas no se limitan a ejercicios pequeños: `filter` y `map` también se usan para expresar transformaciones sobre datos distribuidos. Aprender lambdas ayuda a escribir programas más claros y a separar la lógica de cada transformación del mecanismo que recorre los datos.

## Fuentes y referencias

- Apache Spark. [RDD Programming Guide](https://spark.apache.org/docs/latest/rdd-programming-guide.html).
- Scala. [Collections Methods](https://docs.scala-lang.org/overviews/collections-2.13/trait-iterable.html).
- Oracle. [Java Stream API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html).
- Oracle. [Lambda Expressions](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html).
- Python Software Foundation. [Functional Programming HOWTO](https://docs.python.org/3/howto/functional.html).
- Python Software Foundation. [functools](https://docs.python.org/3/library/functools.html).
- OpenAI, ChatGPT. Asistencia para estructurar la documentación, generar ejemplos y revisar las soluciones (noviembre de 2026). Los ejemplos son elaboraciones originales.
