/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaredes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author root
 */
public class BorrarIP extends javax.swing.JFrame {

    /**
     * Creates new form BorrarIP
     */
    public BorrarIP() {
        initComponents();
        cargar();
    }

    public void cargar() {
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

            List<String> lista = new ArrayList();
            //Tipo de caracter
            Charset charset = Charset.forName("US-ASCII");
            try {
                //crea un lector del archivo
                BufferedReader reader = Files.newBufferedReader(IpListas, charset);
                String line;

                while ((line = reader.readLine()) != null) {
                    lista.add("192.168.1." + line);
                }
                reader.close();
                DefaultListModel dlm = new DefaultListModel();
                for (String linea : lista) {
                    dlm.addElement(linea);
                }
                jList1.setModel(dlm);
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }

        } catch (Exception ex) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jButton1.setText("Borrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (!jList1.isSelectionEmpty()) {
            String cual = jList1.getSelectedValue().toString();
            Integer cualPox=jList1.getSelectedIndex();

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

                    Object obj = lista.remove(cualPox.intValue());

                    BufferedWriter writer = Files.newBufferedWriter(IpListas, charset);

                    for (String linea : lista) {
                        writer.write(linea);
                        writer.newLine();
                    }
                    writer.close();
                    cargar();

                } catch (Exception x) {
                    System.err.format("Exception: %s%n", x);
                }

            } catch (Exception ex) {
            }





        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BorrarIP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BorrarIP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BorrarIP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BorrarIP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new BorrarIP().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
