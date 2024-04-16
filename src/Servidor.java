import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {
    public static void main(String[] args) {
        try {
            // Establece el puerto del servidor
            int puerto = 8080;
            // Establece la dirección del servidor
            String servidor = "192.168.56.110";

            // Escribe en pantalla que el servidor está ejecutándose
            System.out.println("Iniciando servidor en:");
            System.out.println("hostname: " + servidor);
            System.out.println("puerto: " + puerto);

            // Crea el registro de este objeto remoto
            Registry registro = LocateRegistry.createRegistry(puerto);
            // Establece la IP del servidor
            System.setProperty("java.rmi.server.hostname", servidor);
            // Le colocamos un nombre al objeto remoto y establece el stub
            registro.rebind("Calculadora", new CalcRMI());

            // Escribe en pantalla que el servidor está ejecutándose
            System.out.println("Servidor en ejecución en espera de clientes...");
        } catch (RemoteException ex) {
            System.err.println(ex);
        }
    }
}
