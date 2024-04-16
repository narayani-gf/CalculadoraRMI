import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

public class Cliente {
    public static void main(String[] args) {
        // Establece el puerto del servidor
        int puerto = 8080;
        // Establece la dirección del servidor
        String servidor = "192.168.56.110";

        try {
            // Es importante que estos valores sean los mismos del servidor
            Calculadora calc = (Calculadora) Naming.lookup("rmi://" + servidor + ":" + puerto + "/Calculadora");

            // Iniciamos un ciclo para realizar varias operaciones
            while (true) {
                // Se presenta el menú
                String opt = JOptionPane.showInputDialog(
                    "Calcular\n" +
                        "Suma...............(1)\n" +
                        "Resta..............(2)\n" +
                        "Multip.............(3)\n" +
                        "Division...........(4)\n\n" +
                        "Cancelar para salir" 
                );
                // Si no selecciona nada, termina.
                if (opt == null)
                    break;

                // Obtenemos los dos parámetros de la operación
                int a = Integer.parseInt(JOptionPane.showInputDialog("Ingrese a"));
                int b = Integer.parseInt(JOptionPane.showInputDialog("Ingrese b"));

                // Menú de operaciones
                switch (opt) {
                    case "1": {
                        JOptionPane.showMessageDialog(null, a + "+" + b + " = " + calc.sum(a, b));
                        break;
                    }
                    
                    case "2": {
                        JOptionPane.showMessageDialog(null, a + "-" + b + " = " + calc.res(a, b));
                        break;
                    }

                    case "3": {
                        JOptionPane.showMessageDialog(null, a + "*" + b + " = " + calc.mul(a, b));
                        break;
                    }

                    case "4": {
                        JOptionPane.showMessageDialog(null, a + "*" + b + " = " + calc.div(a, b));
                        break;
                    }
                }
            }
        } catch (RemoteException | NotBoundException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar al servidor\n" + ex);
        } catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(null, "La URL esta en formato incorrecto\n" + ex);
        }
    }
}
