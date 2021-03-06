/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Logica.*;
import Clases.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import kj.dsp.KJDigitalSignalProcessingAudioDataConsumer;
import org.blinkenlights.jid3.ID3Exception;
import org.tritonus.share.sampled.file.TAudioFileFormat;

/**
 *
 * @author Gaby Verdugo
 */
    
public class IdentificacionUsuario extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    public IdentificacionUsuario() {
        initComponents();
        // Linea de Codigo para que la ventana aparezca en el medio
        this.setLocationRelativeTo(null);
        // Linea de COdigo para ponerle nombre a la Venatana
        setTitle("Pantalla Inicio de Sesion");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabelUsuario = new javax.swing.JLabel();
        jLabelContrasena = new javax.swing.JLabel();
        NombreUsuario = new javax.swing.JTextField();
        Password = new javax.swing.JPasswordField();
        jButtonLogin = new javax.swing.JButton();
        jButtonRegistro = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabelFondo = new javax.swing.JLabel();

        jLabel3.setText("jLabel3");

        jLabel2.setText("jLabel2");

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 204, 204));
        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, -1, -1));

        jLabelUsuario.setBackground(new java.awt.Color(255, 51, 0));
        jLabelUsuario.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelUsuario.setForeground(new java.awt.Color(0, 255, 255));
        jLabelUsuario.setText("Usuario :");
        getContentPane().add(jLabelUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        jLabelContrasena.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelContrasena.setForeground(new java.awt.Color(0, 255, 255));
        jLabelContrasena.setText("Password :");
        getContentPane().add(jLabelContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

        NombreUsuario.setBackground(new java.awt.Color(204, 255, 255));
        NombreUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        NombreUsuario.setForeground(new java.awt.Color(51, 51, 51));
        NombreUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreUsuarioActionPerformed(evt);
            }
        });
        getContentPane().add(NombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 170, 30));

        Password.setBackground(new java.awt.Color(204, 255, 255));
        Password.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Password.setForeground(new java.awt.Color(51, 51, 51));
        Password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordActionPerformed(evt);
            }
        });
        getContentPane().add(Password, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, 170, 30));

        jButtonLogin.setBackground(new java.awt.Color(204, 204, 204));
        jButtonLogin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonLogin.setForeground(new java.awt.Color(0, 204, 204));
        jButtonLogin.setText("Iniciar Sesion");
        jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 360, -1, -1));

        jButtonRegistro.setBackground(new java.awt.Color(255, 255, 255));
        jButtonRegistro.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonRegistro.setForeground(new java.awt.Color(0, 204, 204));
        jButtonRegistro.setText("Registrarse");
        jButtonRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistroActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/usuario_at.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 150, 180));

        jLabelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo_azul.png"))); // NOI18N
        getContentPane().add(jLabelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 362, 410));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Salir de una ventana
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoginActionPerformed
        // TODO add your handling code here:
        IniciarSesion();
    }//GEN-LAST:event_jButtonLoginActionPerformed

    private void IniciarSesion()
    {
       if(!NombreUsuario.toString().isEmpty() && !Password.toString().isEmpty())
        {
            l = new ManejoUsuarios();        
            UsoUsuario=l.getUsuario(NombreUsuario.getText(), Password.getText());
            if (!UsoUsuario.getCodigo().isEmpty() && !UsoUsuario.getNombre().isEmpty()){
//                } catch (ID3Exception ex) {
//                    Logger.getLogger(IdentificacionUsuario.class.getName()).log(Level.SEVERE, null, ex);
                this.setVisible(false);
                VentanaReproductor obj = new VentanaReproductor();
                obj.getUsuarioFavoritos(NombreUsuario.getText(),UsoUsuario.getNombre());
//                obj.getNombreUsuarioFavoritos(UsoUsuario.getNombre());
                obj.setVisible(true);
                dispose();
            }
            else{
                JOptionPane.showMessageDialog(null,"El Usuario o la Contraseña son incorrectos, verifiquelos");     
            }
        }
        else
        {
           JOptionPane.showMessageDialog(null,"Ingrese su nombre de usuario y contraseña.");     
        } 
    }
    
    private void jButtonRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistroActionPerformed
        RegistrarUsuario obj = new RegistrarUsuario();
        // Linea de codigo que permite que se haga visible la pantalla
        obj.setVisible(true);
        // Linea de Codigo para que la ventana desaparezca mientras se abre la otra
        dispose();
    }//GEN-LAST:event_jButtonRegistroActionPerformed

    private void NombreUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreUsuarioActionPerformed
        // TODO add your handling code here:
        IniciarSesion();
    }//GEN-LAST:event_NombreUsuarioActionPerformed

    private void PasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordActionPerformed
        // TODO add your handling code here:
        IniciarSesion();
    }//GEN-LAST:event_PasswordActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IdentificacionUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IdentificacionUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IdentificacionUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IdentificacionUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new IdentificacionUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField NombreUsuario;
    private javax.swing.JPasswordField Password;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonLogin;
    private javax.swing.JButton jButtonRegistro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelContrasena;
    private javax.swing.JLabel jLabelFondo;
    private javax.swing.JLabel jLabelUsuario;
    // End of variables declaration//GEN-END:variables
    public ManejoUsuarios l;
    public Usuario UsoUsuario;


}
