import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Ventana {

    private JTabbedPane Tabbedpane;
    private JPanel panel1;
    private JSpinner spId;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JButton btnAgregar;
    private JList lstProductos;
    private JButton btnOrdenarPrecio;
    private JButton btnOrdenId;
    private JButton btnBuscarId;
    private JButton btnBuscarNombre;
    private JTextField txtBuscar;
    private JList list1;

    private InventarioProductos inventario;
    private DefaultListModel<String> modeloLista1;
    private DefaultListModel<String> modeloLista2;

    public Ventana() {
        inventario = new InventarioProductos();

        modeloLista1 = new DefaultListModel<>();
        lstProductos.setModel(modeloLista1);

        modeloLista2 = new DefaultListModel<>();
        list1.setModel(modeloLista2);

        actualizarListas();

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProducto();
            }
        });

        btnOrdenarPrecio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventario.ordenarPrecioDesc();
                actualizarListas();
                JOptionPane.showMessageDialog(panel1, "Ordenado por Precio Descendente");
            }
        });

        btnOrdenId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventario.ordenarPorId();
                actualizarListas();
                JOptionPane.showMessageDialog(panel1, "Ordenado por ID");
            }
        });

        btnBuscarId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPorId();
            }
        });

        btnBuscarNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPorNombre();
            }
        });
    }

    private void agregarProducto() {
        try {
            int id = (Integer) spId.getValue();
            String nombre = txtNombre.getText();

            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(panel1, "El nombre no puede estar vacío.");
                return;
            }

            double precio = Double.parseDouble(txtPrecio.getText());

            boolean exito = inventario.agregar(id, nombre, precio);

            if (exito) {
                JOptionPane.showMessageDialog(panel1, "Producto agregado correctamente.");
                txtNombre.setText("");
                txtPrecio.setText("");
                actualizarListas();
            } else {
                JOptionPane.showMessageDialog(panel1, "Error: El ID ya existe.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(panel1, "El precio debe ser un número válido.");
        }
    }

    private void buscarPorId() {
        try {
            int id = Integer.parseInt(txtBuscar.getText());
            Producto p = inventario.buscarBinaria(id);
            mostrarResultado(p);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(panel1, "Ingrese un número entero para buscar ID.");
        }
    }

    private void buscarPorNombre() {
        String nombre = txtBuscar.getText();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(panel1, "Ingrese un nombre para buscar.");
            return;
        }
        Producto p = inventario.buscarNombre(nombre);
        mostrarResultado(p);
    }

    private void mostrarResultado(Producto p) {
        modeloLista2.clear();
        if (p != null) {
            modeloLista2.addElement("--- RESULTADO ---");
            modeloLista2.addElement(p.toString());
        } else {
            JOptionPane.showMessageDialog(panel1, "Producto no encontrado.");
            actualizarListas();
        }
    }

    private void actualizarListas() {
        modeloLista1.clear();
        modeloLista2.clear();
        ArrayList<Producto> lista = inventario.getLista();

        for (Producto p : lista) {
            modeloLista1.addElement(p.toString());
            modeloLista2.addElement(p.toString());
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sistema Inventario");
        frame.setContentPane(new Ventana().panel1); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

