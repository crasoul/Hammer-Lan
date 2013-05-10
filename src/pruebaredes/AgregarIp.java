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

/**
 *
 * @author Memo
 */
public class AgregarIp extends javax.swing.JFrame {

    public AgregarIp() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Agrega IP");

        jLabel1.setText("Agregar IP:");

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 0, 10)); // NOI18N
        jLabel3.setText("Solo la terminacion de la IP (ej. 192.168.1.xxx)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(55, 55, 55)))
                        .addGap(43, 43, 43)
                        .addComponent(jButton1))
                    .addComponent(jLabel3))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(8, 8, 8)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            String ip = jTextField1.getText();
            if (Integer.parseInt(ip) <= 255 && Integer.parseInt(ip) > 1) {
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

                if (!lista.contains(jTextField1.getText())) {
                    for (int i = 0; i < lista.size(); i++) {
                        ipCad += lista.get(i) + ",";
                    }

                    try {
                        ipCad += ip;
                        BufferedWriter writer = Files.newBufferedWriter(IpListas, charset);
                        String[] ipLista = ipCad.split(",");
                        for (int i = 0; i < ipLista.length; i++) {
                            writer.write(ipLista[i]);
                            writer.newLine();
                        }
                        writer.close();
                    } catch (IOException x) {
                        System.err.format("IOException: %s%n", x);
                    }

                    this.dispose();
                } else {
                    jLabel2.setText("IP ya existe");
                }
            } else {
                jLabel2.setText("Introduce un valor menor a 255");
            }
        } catch (NumberFormatException ex) {
            jLabel2.setText("Introduce un valor numerico menor a 255");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(AgregarIp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgregarIp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgregarIp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgregarIp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new AgregarIp().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
