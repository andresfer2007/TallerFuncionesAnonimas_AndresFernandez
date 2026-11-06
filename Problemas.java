// Java 17+ - ejecutar: java java/Problemas.java
import java.util.*;
import java.util.stream.*;
public class Problemas {
  record Producto(String nombre, String categoria, int precio, int stock) {}
  public static void main(String[] args) {
    List<Producto> productos = List.of(
      new Producto("Laptop", "Tecnologia", 900, 8),
      new Producto("Mouse", "Tecnologia", 20, 60),
      new Producto("Teclado", "Tecnologia", 45, 35),
      new Producto("Monitor", "Tecnologia", 220, 12),
      new Producto("Cuaderno", "Papeleria", 4, 100),
      new Producto("Lapiz", "Papeleria", 1, 200),
      new Producto("Mochila", "Accesorios", 35, 25),
      new Producto("Audifonos", "Tecnologia", 80, 18),
      new Producto("Agenda", "Papeleria", 12, 40),
      new Producto("USB", "Tecnologia", 15, 75)
    );
    System.out.println("Seleccion: " + productos.stream().filter(p -> p.precio() > 100).map(Producto::nombre).collect(Collectors.joining(", ")));
    System.out.println("Transformacion: " + productos.stream().map(p -> p.nombre() + ":" + p.precio() * 2).collect(Collectors.joining(", ")));
    System.out.println("Validacion: " + productos.stream().anyMatch(p -> p.stock() < 10));
    System.out.println("Conteo: " + productos.stream().filter(p -> p.categoria().equals("Tecnologia")).count());
    System.out.println("Extremos: " + productos.stream().max(Comparator.comparingInt(p -> p.precio())).orElseThrow().nombre());
    System.out.println("Aplanamiento: " + productos.stream().flatMap(p -> Stream.of(p.nombre(), p.categoria())).limit(6).collect(Collectors.joining(", ")));
    System.out.println("Acumulacion: " + productos.stream().reduce(0, (total, p) -> total + p.precio() * p.stock(), Integer::sum));
  }
}
