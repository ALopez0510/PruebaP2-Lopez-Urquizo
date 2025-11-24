import java.util.ArrayList;

public class InventarioProductos {
    private ArrayList<Producto> lista;

    public InventarioProductos() {
        lista = new ArrayList<>();
        lista.add(new Producto(1, "Laptop", 850.00));
        lista.add(new Producto(5, "Mouse", 25.50));
        lista.add(new Producto(2, "Teclado", 45.00));
        lista.add(new Producto(4, "Monitor", 150.00));
        lista.add(new Producto(3, "Silla Gamer", 200.00));
    }

    public ArrayList<Producto> getLista() {
        return lista;
    }

    public boolean agregar(int id, String nombre, double precio) {
        for (Producto p : lista) {
            if (p.getId() == id) return false;
        }
        lista.add(new Producto(id, nombre, precio));
        return true;
    }

    public void ordenarPrecioDesc() {
        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (lista.get(j).getPrecio() < lista.get(j + 1).getPrecio()) {
                    Producto temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }
    }

    public void ordenarPorId() {
        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (lista.get(j).getId() > lista.get(j + 1).getId()) {
                    Producto temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }
    }

    public Producto buscarBinaria(int id) {
        ordenarPorId();
        int inicio = 0, fin = lista.size() - 1;
        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;
            Producto p = lista.get(medio);
            if (p.getId() == id) return p;
            if (p.getId() < id) inicio = medio + 1;
            else fin = medio - 1;
        }
        return null;
    }

    public Producto buscarNombre(String nombre) {
        for (Producto p : lista) {
            if (p.getNombre().equalsIgnoreCase(nombre)) return p;
        }
        return null;
    }
}