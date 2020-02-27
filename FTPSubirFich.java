package FTP;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FTPSubirFich {

    public static void main(String[] args) {
        FTPClient cliente = new FTPClient();
        String servFTP = "127.0.0.1";
        System.out.println("Nos conectamos a: " + servFTP);

        String usuario = "user1";
        String clave = "user1";

        try {
            cliente.connect(servFTP);
            // modo de conexion del cliente: pasivo-comandos de tipo PASV

            String direc = "/NUEVODIREC";

            cliente.enterLocalPassiveMode();

            boolean login = cliente.login(usuario, clave);
            if (login)
                System.out.println("Login correcto...");


            else {
                System.out.println("Login Incorrecto...");
                cliente.disconnect();
                System.exit(1);
            }

            System.out.println("CD a DIRECTORIO1");
            if (cliente.changeWorkingDirectory("DIRECTORIO1")) {
                System.out.println("Dir Actual: "+ cliente.printWorkingDirectory());

            }

            cliente.setFileType(FTP.BINARY_FILE_TYPE);
            String archivo = "C:\\Users\\Álvaro\\Desktop\\Practica-2_AlvaroQuiros.pdf";

            BufferedInputStream in = new BufferedInputStream(new FileInputStream(archivo));

            System.out.println("Intentando subir archivo al DIRECTORIO1");
            if (cliente.storeFile("pdff.pdf", in)) {
                System.out.println("Subido correctamente");
            } else {
                System.out.println("ERROR al subir archivo");
            }

            in.close();

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
