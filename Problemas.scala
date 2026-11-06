// Scala 3 - ejecutar: scala run scala/Problemas.scala
object Problemas {
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
    println("Seleccion: " + productos.filter(p => p.precio > 100).map(p => p.nombre).mkString(", "))
    println("Transformacion: " + productos.map(p => s"${p.nombre}:${p.precio * 2}").mkString(", "))
    println("Validacion: " + productos.exists(p => p.stock < 10))
    println("Conteo: " + productos.count(p => p.categoria == "Tecnologia"))
    println("Extremos: " + productos.maxBy(p => p.precio).nombre)
    println("Aplanamiento: " + productos.flatMap(p => List(p.nombre, p.categoria)).take(6).mkString(", "))
    println("Acumulacion: " + productos.foldLeft(0)((total, p) => total + p.precio * p.stock))
  }
}
