package FTP;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FTPTestFileZ {

    public static void main(String[] args) {
        FTPClient cliente = new FTPClient();
        String servFTP = "127.0.0.1";
        System.out.println("Nos conectamos a: " + servFTP);

        String usuario = "user1";
        String clave = "user1";

        try {
            cliente.connect(servFTP);
            // modo de conexion del cliente: pasivo-comandos de tipo PASV
            cliente.enterLocalPassiveMode();

            boolean login = cliente.login(usuario, clave);
            if (login)
                System.out.println("Login correcto...");
            else {
                System.out.println("Login Incorrecto...");
                cliente.disconnect();
                System.exit(1);
            }

            FTPFile[] files = cliente.listFiles();

            String tipos[] = {"Fichero", "Directorio", "Enlace simb."};

            System.out.println("Visualizamos directorios del directorio actual:");

            for (int i = 0; i < files.length; i++) {
                if (files[i].getType() == 1)
                    System.out.println("\t" + files[i].getName());
            }

            // Nos desconectamos
            boolean logout = cliente.logout();
            if (logout)
                System.out.println("Logout del servidor FTP...");
            else
                System.out.println("Error al hacer Logout...");

            cliente.disconnect();
            System.out.println("Desconectado...");

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}