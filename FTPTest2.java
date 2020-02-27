package FTP;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;

public class FTPTest2 {
    public static void main(String[] args) throws IOException {
        FTPClient cliente = new FTPClient();
        String servFTP = "www.cc.uah.es";
        System.out.println("Nos conectamos a: " + servFTP);
        String usuario = "anonymous";
        String clave = "anonymous";

        cliente.connect(servFTP);
        cliente.enterLocalPassiveMode();

        boolean login = cliente.login(usuario, clave);

        if (login) {
            System.out.println("Login correcto");
        } else {
            System.out.println("Login incorrecto");
            cliente.disconnect();
            System.exit(0);
        }

        System.out.println("Directorio actual: " + cliente.printWorkingDirectory());
        // Entramos en pub y listamos
        if(cliente.changeWorkingDirectory("pub")){
            System.out.println("Directorio cambiado!");
        }
        System.out.println("Directorio actual: " + cliente.printWorkingDirectory());

        FTPFile[] files = cliente.listFiles();
        System.out.println("Ficheros en el directorio actual: " + files.length);

        String tipos[] = {"Fichero", "Directorio", "Enlace simb"};

        for (int i = 0; i < files.length; i++) {
            System.out.println("\t" + files[i].getName() + " => " + tipos[files[i].getType()]);
        }

        boolean logout = cliente.logout();

        if (logout) {
            System.out.println("Logout del servidor.");
        } else {
            System.out.println("Error al hacer logout");
        }

        cliente.disconnect();
        System.out.println("Desconectado");
    }
}
