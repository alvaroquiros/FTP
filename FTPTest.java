package FTP;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.net.*;


public class FTPTest {

    public static void main(String[] args) throws SocketException, IOException{
        FTPClient cliente = new FTPClient();
        String servFTP = "www.cc.uah.es";
        System.out.println("Nos conectamos a: " + servFTP);
        cliente.connect(servFTP);

        // respuesta de servidor

        System.out.println(cliente.getReplyString());

        // Codigo de respuesta

        int respuesta = cliente.getReplyCode();

        System.out.println("Respuesta: " + respuesta);

        // Comprobacion del codigo de respuesta

        if (!FTPReply.isPositiveCompletion(respuesta)) {
            cliente.disconnect();
            System.out.println("Conexion rechazada: " + respuesta);
            System.exit(0);
        }

        // Desconexion del servidor FTP

        cliente.disconnect();
        System.out.println("Conexion finalizada.");
    }

}
