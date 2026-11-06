// Scala 3 - ejecutar: scala run operaciones/OperacionesNuevas.scala
object OperacionesNuevas {
  case class Producto(nombre: String, categoria: String, precio: Int, stock: Int)
  val productos = List(
    Producto("Laptop", "Tecnologia", 900, 8),
    Producto("Mouse", "Tecnologia", 20, 60),
    Producto("Teclado", "Tecnologia", 45, 35),
    Producto("Monitor", "Tecnologia", 220, 12),
    Producto("Cuaderno", "Papeleria", 4, 100),
    Producto("Lapiz", "Papeleria", 1, 200),
    Producto("Mochila", "Accesorios", 35, 25),
    Producto("Audifonos", "Tecnologia", 80, 18),
    Producto("Agenda", "Papeleria", 12, 40),
    Producto("USB", "Tecnologia", 15, 75)
  )
  def main(args: Array[String]): Unit = {
    // sortBy: recibe Producto => Int, devuelve la clave de ordenamiento
    println("sortBy: " + productos.sortBy(p => p.precio).take(3).map(p => p.nombre).mkString(", "))
    // groupBy: recibe Producto => String, devuelve la clave del grupo
    println("groupBy: " + productos.groupBy(p => p.categoria).toList.sortBy(x => x._1).map(x => s"${x._1}:${x._2.size}").mkString(", "))
    // partition: recibe Producto => Boolean, divide según el predicado
    val (disponibles, agotados) = productos.partition(p => p.stock >= 30)
    println(s"partition: suficientes=${disponibles.size}, pocos=${agotados.size}")
    // find: recibe Producto => Boolean, devuelve el primer elemento coincidente
    println("find: " + productos.find(p => p.precio < 5).map(p => p.nombre).getOrElse("Ninguno"))
    // Combinacion: groupBy + filter + sortBy; 2 nuevas y 1 vista en clase
    val resultado = productos.groupBy(p => p.categoria)
      .toList.filter(par => par._2.map(p => p.stock).sum > 100)
      .sortBy(par => -par._2.map(p => p.stock).sum)
      .map(par => s"${par._1}:${par._2.map(p => p.stock).sum}")
    println("Combinacion: " + resultado.mkString(", "))
  }
}
