/*
 * RutinasIP.java
 *
 * Created on May 6, 2012, 3:22:14 PM
 */
package pruebaredes;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author root
 */
public class RutinasIP extends javax.swing.JFrame {

    /**
     * Creates new form RutinasIP
     */
    public RutinasIP() {
        initComponents();
        cargarIPs();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        jList2.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new RutinasIP().setVisible(true);
            }
        });
    }

    public void cargarIPs() {
        try {
            //Crea la posible ruta del archivo, en el directorio actual en la carpeta 'Redes'
            Path IpListas = Paths.get("/var/www", "listaIP.txt");

            List<String> lista = new ArrayList<String>();
            List<Integer> count = new ArrayList<Integer>();

            //Tipo de caracter
            Charset charset = Charset.forName("US-ASCII");
            try {
                //crea un lector del archivo
                BufferedReader reader = Files.newBufferedReader(IpListas, charset);
                String line = null;
                while ((line = reader.readLine()) != null) {
                    if (lista.contains(line)) {
                        count.set(lista.indexOf(line), count.get(lista.indexOf(line)) + 1);
                    } else {
                        lista.add(line);
                        count.add(1);
                    }
                }
                reader.close();
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }

            DefaultListModel listaModel = new DefaultListModel();
            DefaultListModel listaModel2 = new DefaultListModel();
            for (String ip : lista) {
                listaModel.addElement(ip);
            }
            jList1.setModel(listaModel);


            for (Integer c : count) {
                listaModel2.addElement(c);;
            }
            jList2.setModel(listaModel2);

        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
