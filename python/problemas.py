
from functools import reduce
productos = [
    dict(nombre="Laptop", categoria="Tecnologia", precio=900, stock=8),
    dict(nombre="Mouse", categoria="Tecnologia", precio=20, stock=60),
    dict(nombre="Teclado", categoria="Tecnologia", precio=45, stock=35),
    dict(nombre="Monitor", categoria="Tecnologia", precio=220, stock=12),
    dict(nombre="Cuaderno", categoria="Papeleria", precio=4, stock=100),
    dict(nombre="Lapiz", categoria="Papeleria", precio=1, stock=200),
    dict(nombre="Mochila", categoria="Accesorios", precio=35, stock=25),
    dict(nombre="Audifonos", categoria="Tecnologia", precio=80, stock=18),
    dict(nombre="Agenda", categoria="Papeleria", precio=12, stock=40),
    dict(nombre="USB", categoria="Tecnologia", precio=15, stock=75)
]
print("Seleccion: " + ", ".join(map(lambda p: p["nombre"], filter(lambda p: p["precio"] > 100, productos))))
print("Transformacion: " + ", ".join(map(lambda p: f'{p["nombre"]}:{p["precio"] * 2}', productos)))
print("Validacion: " + str(any(map(lambda p: p["stock"] < 10, productos))).lower())
print("Conteo: " + str(len(list(filter(lambda p: p["categoria"] == "Tecnologia", productos)))))
print("Extremos: " + max(productos, key=lambda p: p["precio"])["nombre"])
print("Aplanamiento: " + ", ".join(sum(map(lambda p: [p["nombre"], p["categoria"]], productos), [])[:6]))
print("Acumulacion: " + str(reduce(lambda total, p: total + p["precio"] * p["stock"], productos, 0)))
