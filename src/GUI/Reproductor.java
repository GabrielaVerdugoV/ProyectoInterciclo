/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

//import Logica.*;

import Logica.MoveMouseListener;
import Clases.*;
import Logica.*;
import java.util.ArrayList;
import java.util.Map;
import javax.sound.sampled.SourceDataLine;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JSlider;
import kj.dsp.KJDigitalSignalProcessingAudioDataConsumer;

//import Clases.*;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.tritonus.share.sampled.file.TAudioFileFormat;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Map;
import java.util.Observable;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javazoom.jlgui.basicplayer.BasicController; 
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;
import java.io.*;
import java.util.ArrayList;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import kj.dsp.KJDigitalSignalProcessingAudioDataConsumer;

import org.blinkenlights.jid3.*;
import org.blinkenlights.jid3.v2.*;

/**
 *
// * @author Gaby Verdugo
 */


public class Reproductor  extends javax.swing.JFrame implements BasicPlayerListener {

    @Override
    public void opened(Object o, Map map) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void progress(int i, long l, byte[] bytes, Map map) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void stateUpdated(BasicPlayerEvent bpe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setController(BasicController bc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public class MiTabla extends DefaultListModel {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
    }

    
    public DefaultListModel modelo;
    public double bytesLength = 0.0;
    float progressUpdate;
    double progressNow;
    private ManejoCancionReproducir clic;
    public boolean shuffle = false;
    public int segundos1 = 0;
    public int minutos1 = 0;
    public int horas1 = 0;
    JLabel imagen;    
    ArrayList<Cancion> listaC;
    public ArrayList<String> cola;
    JSlider[] arregloE = new JSlider[10];
    float[] arreglo = new float[10];
    Map audioInfo;
    public KJDigitalSignalProcessingAudioDataConsumer dsp;
    SourceDataLine linea = null;
    public Reproductor() {
        initComponents();
//        
        cola = new ArrayList<String>();       
        seCerroVentana em = new seCerroVentana(this);
        this.addWindowListener(em);
        horas11.setText("00:00:00");
        this.setIconImage(new ImageIcon(getClass().getResource("/Imagenes/Volume.png")).getImage());
        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        vol = jSlider1.getValue();
        this.clic = new ManejoCancionReproducir();
        this.clic.getPlayer().addBasicPlayerListener(this);
        l = new ManejoCanciones();        
        listaC = new ArrayList<Cancion>();
        dsp = new KJDigitalSignalProcessingAudioDataConsumer(2048, 50);
        for (int i =0;i < l.largo();i++) {
            Cancion c = l.getCancion(i);
            String aux = c.getNombre().replace('?', '\'');
            File movido = new File(aux);
            if (movido.isFile()) {                
                String s = "";
                String as = "";
                AudioFileFormat baseFileFormat = null;               
                try {
                    baseFileFormat = AudioSystem.getAudioFileFormat(movido);
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, ex);
                }                
                if (baseFileFormat instanceof TAudioFileFormat)
                {
                    Map properties = ((TAudioFileFormat)baseFileFormat).properties();
                    String key = "author";
                    s = (String) properties.get(key);
                    key = "title";
                    as = (String) properties.get(key);                    
                }
                if ((as == null || as.equals("")) && (s == null || s.equals(""))) as = movido.getName();
                /*if (as == null || as.equals("")) as = "TituloDesconocido";
                if (s == null || s.equals("")) s = "IntérpreteDesconocido";*/
                
                Object o = new Object();
                String aux1 = String.valueOf(c.getId());
                o =  aux1 +" . "+ as + " - " + s;
                listaSoloT.add(as + " - " + s);
                lista.add((String) o);
                modelo.addElement(o);
                listaC.add(c);
            }else {
                modelo.addElement(c.getId() +" . Elemento_movido_o_dañado");
                lista.add(c.getId() +" . Elemento_movido_o_dañado");
                listaSoloT.add("Elemento_movido_o_dañado");
                listaC.add(c);
            }

        }
        l.eliminarCanciones();
        MoveMouseListener mml = new MoveMouseListener(jPanel4);
        jPanel4.addMouseListener(mml);
        jPanel4.addMouseMotionListener(mml);
        this.getRootPane().setBorder((Border) BorderFactory.createLineBorder( new Color(45,137,205),4));
//        imagen = new JLabel();
//        imagen.setIcon(new ImageIcon(getClass().getResource("/Imagenes/fondo.jpg")));
//        imagen.setBounds(0,0,590,421);        
//        this.add(imagen);
        this.setLocationRelativeTo(null);
    }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jSlider1 = new javax.swing.JSlider();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        labelTiempo = new javax.swing.JLabel();
        labelAlbum = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jSlider13 = new javax.swing.JSlider();
        jLabel8 = new javax.swing.JLabel();
        info = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jSlider3 = new javax.swing.JSlider();
        labelBirate1 = new javax.swing.JLabel();
        horas11 = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        modelo = new MiTabla();
        jList1 = new javax.swing.JList(modelo);
        addFile = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        BotonCerrarSesion = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Usuario = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        play = new javax.swing.JButton();
        stop = new javax.swing.JButton();
        back = new javax.swing.JButton();
        next = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        elimAll = new javax.swing.JButton();
        elimUno = new javax.swing.JButton();
        BuscarTexto = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jSlider2 = new javax.swing.JSlider();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setOpaque(false);

        jSlider1.setBackground(new java.awt.Color(26, 91, 134));
        jSlider1.setOrientation(javax.swing.JSlider.VERTICAL);
        jSlider1.setValue(25);
        jSlider1.setOpaque(false);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/vol3.png"))); // NOI18N
        jLabel4.setText("jLabel3");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 40, 160));

        jPanel2.setOpaque(false);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aleatorio.png"))); // NOI18N
        jLabel2.setToolTipText("Aleatorio On/Off");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/listaA.png"))); // NOI18N
        jLabel5.setToolTipText("Mostrar/Ocultar PlayList");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        labelTiempo.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N
        labelTiempo.setForeground(new java.awt.Color(207, 142, 76));
        labelTiempo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTiempo.setText("   00:00:00");

        labelAlbum.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N
        labelAlbum.setForeground(new java.awt.Color(207, 142, 76));
        labelAlbum.setText("Album : ");

        jPanel9.setOpaque(false);

        jSlider13.setMinimum(-100);
        jSlider13.setValue(0);
        jSlider13.setOpaque(false);
        jSlider13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSlider13MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSlider13, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSlider13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jSlider13.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        jLabel8.setForeground(new java.awt.Color(207, 142, 76));
        jLabel8.setText("Center");

        info.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        info.setForeground(new java.awt.Color(207, 142, 76));
        info.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                infoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                infoMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(info, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAlbum, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(jLabel2)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel5)))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(info, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(labelAlbum, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(labelTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, -1, 130));

        jPanel4.setBackground(new java.awt.Color(255, 255, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/anteirortr.png"))); // NOI18N
        jPanel4.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 50, -1));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/siguientetr.png"))); // NOI18N
        jPanel4.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 50, -1));

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/stoptr.png"))); // NOI18N
        jPanel4.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 60, -1));

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/playtr.png"))); // NOI18N
        jPanel4.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 50, -1));

        jSlider3.setValue(0);
        jSlider3.setOpaque(false);
        jSlider2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });
        jPanel4.add(jSlider3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 310, -1));

        labelBirate1.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N
        labelBirate1.setForeground(new java.awt.Color(207, 142, 76));
        labelBirate1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelBirate1.setText("kbps");
        jPanel4.add(labelBirate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, 50, 20));

        horas11.setEditable(false);
        horas11.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        horas11.setForeground(new java.awt.Color(207, 142, 76));
        horas11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        horas11.setText("00:00:00");
        horas11.setBorder(null);
        horas11.setOpaque(false);
        jPanel4.add(horas11, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 50, 20));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 660, 60));

        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setToolTipText("");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(69, 108, 140), java.awt.Color.white));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jList1.setBackground(new java.awt.Color(0, 0, 0));
        jList1.setForeground(new java.awt.Color(255, 255, 255));
        jList1.setOpaque(false);
        jList1.setSelectionBackground(new java.awt.Color(45, 137, 205));
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jList1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jList1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jList1KeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jTabbedPane1.addTab("Lista de Canciones", jScrollPane1);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 650, 170));

        addFile.setBackground(new java.awt.Color(0, 0, 0));
        addFile.setForeground(new java.awt.Color(255, 255, 255));
        addFile.setText("Agregar Canción Favoritos");
        addFile.setToolTipText("Abrir archivos");
        addFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFileActionPerformed(evt);
            }
        });
        jPanel1.add(addFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 350, 160, -1));

        jComboBox1.setForeground(new java.awt.Color(207, 142, 76));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"No hay canciones"}));
        jComboBox1.setToolTipText("Agregue canciones con la tecla Q");
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 380, -1, -1));

        BotonCerrarSesion.setBackground(new java.awt.Color(255, 255, 204));
        BotonCerrarSesion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        BotonCerrarSesion.setText("Cerrar Sesion");
        BotonCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonCerrarSesionActionPerformed(evt);
            }
        });
        jPanel1.add(BotonCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("REPRODUCTOR MUSICA DE: ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        Usuario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Usuario.setText("Lista de Canciones a Reproducirse");
        jPanel1.add(Usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, 250, 50));

        jTextField1.setBackground(new java.awt.Color(204, 204, 204));
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 110, 20, -1));

        play.setBackground(new java.awt.Color(0, 0, 0));
        play.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        play.setForeground(new java.awt.Color(255, 255, 255));
        play.setText(">");
        jPanel1.add(play, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        stop.setBackground(new java.awt.Color(0, 0, 0));
        stop.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        stop.setForeground(new java.awt.Color(255, 255, 255));
        stop.setText("◘");
        jPanel1.add(stop, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, -1, -1));

        back.setBackground(new java.awt.Color(0, 0, 0));
        back.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        back.setForeground(new java.awt.Color(255, 255, 255));
        back.setText("<<");
        jPanel1.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, -1, -1));

        next.setBackground(new java.awt.Color(0, 0, 0));
        next.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        next.setForeground(new java.awt.Color(255, 255, 255));
        next.setText(">>");
        jPanel1.add(next, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, -1, -1));

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("x");
        jButton3.setToolTipText("Quitar de la Cola");
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, -1, -1));

        edit.setBackground(new java.awt.Color(0, 0, 0));
        edit.setForeground(new java.awt.Color(255, 255, 255));
        edit.setText("Edit");
        edit.setToolTipText("Editar Canción seleccionada");
        jPanel1.add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, -1, -1));

        elimAll.setBackground(new java.awt.Color(0, 0, 0));
        elimAll.setForeground(new java.awt.Color(255, 255, 255));
        elimAll.setText("Ra");
        elimAll.setToolTipText("Eliminar todas las canciones");
        jPanel1.add(elimAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, -1, -1));

        elimUno.setBackground(new java.awt.Color(0, 0, 0));
        elimUno.setForeground(new java.awt.Color(255, 255, 255));
        elimUno.setMnemonic('R');
        elimUno.setText("R");
        elimUno.setToolTipText("Eliminar la canción seleccionada");
        jPanel1.add(elimUno, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 110, -1, -1));

        BuscarTexto.setBackground(new java.awt.Color(255, 255, 204));
        BuscarTexto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BuscarTexto.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                BuscarTextoComponentRemoved(evt);
            }
        });
        BuscarTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarTextoActionPerformed(evt);
            }
        });
        jPanel1.add(BuscarTexto, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 200, 40));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscartr.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 40));

        jSlider2.setMinimum(-100);
        jSlider2.setValue(0);
        jSlider2.setOpaque(false);
        jSlider2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSlider2MouseClicked(evt);
            }
        });
        jPanel1.add(jSlider2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 430, -1, -1));
        jSlider13.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 580));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotonCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonCerrarSesionActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_BotonCerrarSesionActionPerformed

    
    private void BuscarTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarTextoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BuscarTextoActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void BuscarTextoComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_BuscarTextoComponentRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_BuscarTextoComponentRemoved

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void infoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_infoMouseEntered

    private void infoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_infoMouseExited

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        // TODO add your handling code here:
        //if (suena) {
    }//GEN-LAST:event_jSlider1StateChanged

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jSlider13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSlider13MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jSlider13MouseClicked

    private void jSlider2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSlider2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jSlider2MouseClicked

    private void addFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFileActionPerformed
        // TODO add your handling code here:
                fc1 = new JFileChooser();
        fc1.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos *MP3", "mp3");
        fc1.setFileFilter(filter);
        fc1.setMultiSelectionEnabled(true);        
        int returnVal = fc1.showOpenDialog(null);        
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            int largo = fc1.getSelectedFiles().length;
            File[] f = new File[largo];
            f = fc1.getSelectedFiles();            
            cont = listaC.size();
            for (int i = 0;i < largo;i++){
                String s = "";
                String as = "";               
                if (f[i].isFile()){
                    String ruta = f[i].getAbsolutePath().replace('\'', '?');
                    File ff = new File(ruta);
                    AudioFileFormat baseFileFormat = null;
                    AudioFormat baseFormat = null;
                    try {
                        baseFileFormat = AudioSystem.getAudioFileFormat(ff);
                    } catch (UnsupportedAudioFileException ex) {
                        Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    baseFormat = baseFileFormat.getFormat();
                    if (baseFileFormat instanceof TAudioFileFormat)
                    {
                        Map properties = ((TAudioFileFormat)baseFileFormat).properties();
                        String key = "author";
                        s = (String) properties.get(key);
                        key = "title";
                        as = (String) properties.get(key);                                                
                    }
                    if ((as == null || as.equals("")) && (s == null || s.equals(""))) as = ff.getName();
                    /*if (as == null || as.equals("")) as = "TituloDesconocido";
                    if (s == null || s.equals("")) s = "IntérpreteDesconocido";*/
                    Cancion c = new Cancion();
                    c.setNombre(ruta);
                    int id = 0;
                    if (cont == 0) {
                        id = 1;
                    } else {
                        id = cont + 1;
                    }
                    c.setId(id);                    
                    Object o = new Object();
                    String aux = String.valueOf(id);
                    o = aux + " . " + as + " - " + s;
                    listaSoloT.add(as + " - " + s);
                    modelo.addElement(o);
                    lista.add((String) o);
                    listaC.add(c);                    
                    cont++;
                }
                else {
                    listarRec(f[i],cont);
                }                                                                                                                  
            }
            
        }        

    }//GEN-LAST:event_addFileActionPerformed

    public String pasarAMinutos(long d){                
        int mili = (int) (d / 1000);
        int seg = (mili / 1000) % 60;
        int min = (mili / 1000) / 60;
        String se = ""+seg;        
        String minu = ""+min;
        if (seg < 10)  se = "0"+seg;
        if (min < 10)  minu = "0"+min;        

        return "00:"+minu+":"+se;
    }
    
    public void setDatos(String unA,int unBi,String unHo){
        labelTiempo.setText("   "+unHo);
        labelBirate1.setText(String.valueOf(unBi/1000)+"kbps");
        labelAlbum.setText("Album : "+unA);
        labelAlbum.setToolTipText(unA);
    }

      public void rotarTexto(String s){
        char[] cadena = s.toCharArray();        
        char tmp = cadena[0];
        char tmp2;
        cadena[0] = cadena[s.length() -1];
        for (int i = 1; i < s.length();i++){
            tmp2 = cadena[i];
            cadena[i] = tmp;
            tmp = tmp2;
        }
        String aux = "";        
        for (int i = 0; i < s.length();i++){
            aux = aux.concat(String.valueOf(cadena[i]));
            
        }        
        info.setText(aux);
        ponerTitulo(aux);
   }
 
    
   public void ponerTitulo(String s){
       this.setTitle(s);
   }

   public boolean menor(int i){
        return (i < 10);
    }
   
   public void reloj(){
        String h;
        String m;
        String s;
        if (menor(segundos1)) s = "0"+segundos1;
        else s = "" + segundos1;
        if (menor(minutos1)) m = "0"+minutos1;
        else m = "" + minutos1;
        if (menor(horas1)) h = "0"+horas1;
        else h = "" + horas1;
        if (estado.equals("Play")) {
            if (segundos1 < 60) {                
                horas11.setText(h+":"+m+":" +s);
            }else{
                segundos1 = 0;
                s="00";
                horas11.setText(h+":"+m+":" +"00");
                minutos1++;
                if (menor(minutos1)) m = "0"+minutos1;
                else m = "" + minutos1;
                if (minutos1 < 60) {                    
                    horas11.setText(h+":"+ m+":"+s);
                }else{
                    minutos1 = 0;
                    horas11.setText(h+":"+ "00"+":" + s);
                    horas1++;
                    if (menor(horas1)) h = "0"+horas1;
                    else h = "" + horas1;
                    horas11.setText(h+":"+m+":" +s);   
                }
            }
            segundos1++;
        }
   }

     public class automatico extends Observable{
         public automatico(){
             Timer timer = new Timer();
             timer.scheduleAtFixedRate(timerTask, 0,1000);
         }
         TimerTask timerTask = new TimerTask(){
             @SuppressWarnings({ "deprecation", "static-access" })
                    public void run() {
                    if (listaC.size()>0){
                        reloj();                        
                        if (moverInfo){
                            rotarTexto(info.getText());
                        }                        
                        if (jSlider2.getValue() >= bytesLength){
                            if(cola.isEmpty()) {
                                if (shuffle) {
                                    jSlider2.setValue(0);
                                    segundos1 = 0;
                                    minutos1 = 0;
                                    horas1 = 0;

                                    horas11.setText("00:00:00");

                                    Random r = new Random();
                                    int valorDado = r.nextInt(listaC.size())+1;
                                    int gainValue = jSlider1.getValue();
                                    int maxGain = jSlider1.getMaximum();
                                    jList1.setSelectedIndex(valorDado -1);
                                    try {
                                        String movido = lista.get(valorDado -1);
                                        if (!movido.equals(valorDado+" . Elemento_movido_o_dañado")) {                                            
                                            String s = clic.Reproducir(valorDado, gainValue, maxGain,listaC);
                                            String album = clic.getAlbum();
                                            int birate = clic.getBirate();
                                            long duracion = clic.getDuracion();
                                            String duracion1 = pasarAMinutos(duracion);
                                            setDatos(album,birate,duracion1);
                                            estaSonando = valorDado;
                                            estado = "Play";
                                            moverInfo = true;
                                            int largo = s.length();
                                            int resto = 25 - largo;
                                            s = ".:: "+duracion1+" :: "+ s + " :: "+labelBirate1.getText()+" ::.";
                                            if (resto > 0) {
                                                for(int i = 0;i < (resto*2);i++){
                                                    s = s.concat(" ");
                                                }
                                            }else s = s.concat("   ");
                                            info.setText(s);
                                            ponerTitulo(s);
                                        }
                                    } catch (BasicPlayerException ex) {
                                        Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }else{
                                    jSlider2.setValue(0);
                                    segundos1 = 0;
                                    minutos1 = 0;
                                    horas1 = 0;
                                    horas11.setText("00:00:00");
                                    int id = estaSonando;
                                    if (id == l.largo()){
                                        id = 1;
                                        jList1.setSelectedIndex(0);
                                    }
                                    else {
                                        id++;
                                        jList1.setSelectedIndex(id-1);
                                    }
                                    int gainValue = jSlider1.getValue();
                                    int maxGain = jSlider1.getMaximum();
                                    try {
                                        String movido = lista.get(id-1);
                                        if (!movido.equals(id+" . Elemento_movido_o_dañado")) {                                            
                                            String s = clic.Reproducir(id, gainValue, maxGain,listaC);
                                            String album = clic.getAlbum();
                                            int birate = clic.getBirate();
                                            long duracion = clic.getDuracion();
                                            String duracion1 = pasarAMinutos(duracion);
                                            setDatos(album,birate,duracion1);
                                            estaSonando = id;
                                            estado = "Play";
                                            moverInfo = true;
                                            int largo = s.length();
                                            int resto = 25 - largo;
                                            s = ".:: "+duracion1+" :: "+ s + " :: "+labelBirate1.getText()+" ::.";
                                            if (resto > 0) {
                                                for(int i = 0;i < (resto*2);i++){
                                                    s = s.concat(" ");
                                                }
                                            }else s = s.concat("   ");
                                            info.setText(s);
                                            ponerTitulo(s);
                                        }
                                    } catch (BasicPlayerException ex) {
                                        Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }else {
                                String s = cola.get(0);
                                cola.remove(0); 
                                jComboBox1.removeItemAt(0);
                                if (cola.isEmpty()) jComboBox1.addItem("No hay canciones");
                                StringTokenizer st = new StringTokenizer(s);
                                int id = Integer.parseInt(st.nextToken());
                                jSlider2.setValue(0);
                                segundos1 = 0;
                                minutos1 = 0;
                                horas1 = 0;
                                horas11.setText("00:00:00");
                                int gainValue = jSlider1.getValue();
                                int maxGain = jSlider1.getMaximum();
                                try {
                                    s = clic.Reproducir(id, gainValue, maxGain, listaC);
                                } catch (BasicPlayerException ex) {
                                    Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                String album = clic.getAlbum();
                                int birate = clic.getBirate();
                                long duracion = clic.getDuracion();
                                String duracion1 = pasarAMinutos(duracion);
                                setDatos(album,birate,duracion1);
                                estaSonando = id;
                                estado = "Play";
                                moverInfo = true;
                                int largo = s.length();
                                int resto = 25 - largo;
                                s = ".:: "+duracion1+" :: "+ s + " :: "+labelBirate1.getText()+" ::.";
                                if (resto > 0) {
                                    for(int i = 0;i < (resto*2);i++){
                                        s = s.concat(" ");
                                    }
                                }else s = s.concat("   ");
                                info.setText(s);
                                ponerTitulo(s);
                            }
                        }
                     }
             }
         };
    }

    public void reproducir(){
        if (lista.size()>0){
            int fila = jList1.getSelectedIndex();
            if (fila > -1) {
                idSel = fila;
                String movido = (String)modelo.getElementAt(fila);
                StringTokenizer st = new StringTokenizer(movido);
                final int id = Integer.parseInt(st.nextToken());
                String auxi = st.nextToken(); // el .                
                auxi = st.nextToken(); // el resto                
                if (!auxi.equals("Elemento_movido_o_dañado")) {
                    int gainValue = jSlider1.getValue();
                    int maxGain = jSlider1.getMaximum();
                    try {                        
                        String s = clic.Reproducir(id, gainValue, maxGain,listaC);
                        //setupDSP(linea);
/*
                        try {
                           AudioFormat format = new AudioFormat(44100, 16, 2, true, false) ;
                           SourceDataLine line = null ;
                            try {
                                line = AudioSystem.getSourceDataLine(format);
                            } catch (LineUnavailableException ex) {
                                Logger.getLogger(VentanaReproductor.class.getName()).log(Level.SEVERE, null, ex);
                            }
                           line. open() ;
                           line. start() ;
                           // now you may write data to the line
                           int size = line. getBufferSize() ;
                            byte[] data = new byte[ size] ;
                            java.util.Random random = new java.util.Random() ;
                             
                            
                            for (int i=0;i<10; i++) {
                               random.nextBytes(data) ;
                               line.write(data, 0, size) ;
                            }
                            line. drain() ;
                           // insert code here to generate audio data
                           line. drain() ;   // send out any data still in the buffer
                           // when you are done, drain and close the line
                           //line. close() ;   // close the line
                        } catch (LineUnavailableException e) {
                           e. printStackTrace( ) ;
                        }
*/

                        jList1.requestFocus();
                        String album = clic.getAlbum();
                        int birate = clic.getBirate();
                        long duracion = clic.getDuracion();
                        String duracion1 = pasarAMinutos(duracion);
                        setDatos(album,birate,duracion1);
                        estaSonando = id;                        
                        int largo = s.length();
                        int resto = 25 - largo;
                        s = ".:: "+duracion1+" :: "+ s + " :: "+labelBirate1.getText()+" ::.";
                        if (resto > 0) {
                            for(int i = 0;i < (resto*2);i++){
                                s = s.concat(" ");
                            }
                        }else s = s.concat("   ");
                        info.setText(s);
                        ponerTitulo(s);
                        segundos1 = 0;
                        minutos1 = 0;
                        horas1 = 0;
                        horas11.setText("00:00:00");
                        reloj();
                        if (estado.equals("Stop"))
                            a = new automatico();
                        estado = "Play";
                        play.setText("||");
                    } catch (BasicPlayerException ex) {
                        Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else JOptionPane.showMessageDialog(this,"Archivo no disponible para reproduccion.","Error",JOptionPane.ERROR_MESSAGE);
            }else estado = "Stop";
        }
    }

    
    private void jList1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jList1KeyPressed
        // TODO add your handling code here:
            if (evt.getKeyCode() == 10){
            if (estado.equals("Play") || estado.equals("Pause")){
                try {
                    clic.stop();
                } catch (BasicPlayerException ex) {
                    Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            reproducir();
        }

    }//GEN-LAST:event_jList1KeyPressed

    private void jList1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jList1KeyTyped
        // TODO add your handling code here:
        switch (evt.getKeyChar()){
            case 'q': if (jList1.getSelectedIndex() >= 0){
                          String nom = (String)modelo.getElementAt(jList1.getSelectedIndex());
                          StringTokenizer st = new StringTokenizer(nom);
                          st.nextToken();
                          st.nextToken();
                          if (!st.nextToken().equals("Elemento_movido_o_dañado")){
                            if (cola.isEmpty())
                                jComboBox1.removeAllItems();
                            cola.add(nom);
                            jComboBox1.addItem(nom);
                          }
                      }
                      break;
            case 'x': play.doClick();
                      break;
            case 'c': stop.doClick();
                      break;
            case 'v': back.doClick();
                      break;
            case 'b': next.doClick();
                      break;
            case 's': if (!shuffle) {
                            shuffle = true;
                            jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aleatorio1.png")));
                      }else{
                            shuffle = false;
                            jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aleatorio.png")));

                      }break;
            case 'l': if (!playlist) {
                            playlist = true;
                            jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/listaA.png")));
                            jPanel1.setVisible(true);
                            this.setSize(595, 418);
                            imagen.setBounds(0,0,590,418);
                      }else{
                            playlist = false;
                            jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/listaN.png")));
                            jPanel1.setVisible(false);
                            this.setSize(305, 418);
                            imagen.setBounds(-294,0,590,418);
                      }break;
            default:                       
        }

    }//GEN-LAST:event_jList1KeyTyped

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2){            
            reproducir();            
        }else{
            int fila = jList1.getSelectedIndex();
            if (fila > -1) {
                idSel = fila;
                String movido = (String)modelo.getElementAt(fila);
                StringTokenizer st = new StringTokenizer(movido);
                int id = Integer.parseInt(st.nextToken());
                
                    File f = new File(listaC.get(id-1).getNombre());
                    if (!f.isFile()) {
                        modelo.setElementAt(id+" . Elemento_movido_o_dañado", fila);
                    }
                
            }
        }

    }//GEN-LAST:event_jList1MouseClicked

    public class musicFilter implements FileFilter{
          private final String[] okFileExtensions = new String[] {"mp3"};
          public boolean accept(File file)
          {
            for (String extension : okFileExtensions)
            {
              if (file.getName().toLowerCase().endsWith(extension) || file.isDirectory())
              {
                return true;
              }
            }
            return false;
          }
    }

    public void listarRec(File f1,int c){
    musicFilter filter = new musicFilter();
    File[] f = f1.listFiles(filter);
    cont = c;
    String s = "";
    String as = "";
 
    for (int x=0;x<f.length;x++){
                //System.out.println(separador + ficheros[x].getName());
                if (f[x].isDirectory()){                        
                        listarRec(f[x],cont);
                }else if(f[x].isFile()){
                    
                    File ff = new File(f[x].getAbsolutePath());
                    AudioFileFormat baseFileFormat = null;
                    AudioFormat baseFormat = null;
                    try {
                        baseFileFormat = AudioSystem.getAudioFileFormat(ff);
                    } catch (UnsupportedAudioFileException ex) {
                        Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    baseFormat = baseFileFormat.getFormat();
                    if (baseFileFormat instanceof TAudioFileFormat)
                    {
                        Map properties = ((TAudioFileFormat)baseFileFormat).properties();
                        String key = "author";
                        s = (String) properties.get(key);
                        key = "title";
                        as = (String) properties.get(key);                        
                    }
                    if ((as == null || as.equals("")) && (s == null || s.equals(""))) as = ff.getName();
                    /*if (s == null || s.equals("")) s = "TituloDesconocido";
                    if (as == null || as.equals("")) as = "IntérpreteDesconocido";*/
                    Cancion c1 = new Cancion();
                    c1.setNombre(f[x].getAbsolutePath());
                    int id = 0;
                    if (cont == 0) {
                        id = 1;
                    } else {
                        id = cont + 1;
                    }
                    c1.setId(id);
                    //l.guardarCancion(c1);
                    Object o = new Object();
                    String aux = String.valueOf(id);
                    o = aux + " . " + as + " - " + s;
                    listaSoloT.add(as + " - " + s);
                    listaC.add(c1);
                    modelo.addElement(o);
                    lista.add((String) o);
                    cont++;
                }
        }
    }

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
            java.util.logging.Logger.getLogger(Reproductor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reproductor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reproductor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reproductor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //Usuario l = null;
                //new Reproductor(l, new AccionReproductor()).setVisible(true);
            }
        });
    }
    
    private void noVi(){
        this.setVisible(false);
    }

    public class seCerroVentana implements WindowListener {
	    JFrame gen;
	    public seCerroVentana(JFrame origen) {
	        gen = origen;
	    }
	    public void windowClosed(WindowEvent e) {}
	    public void windowActivated(WindowEvent e) {}
            public void windowClosing(WindowEvent e) {
                noVi();
                if (estado.equals("Play") || estado.equals("Pause"))
                    try {
                    clic.stop();
                } catch (BasicPlayerException ex) {
                    Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (int i = 0; i < listaC.size();i++){
                    l.guardarCanciones(listaC.get(i));
                }
            }
            public void windowDeactivated(WindowEvent e) {}
            public void windowDeiconified(WindowEvent e) {}
            public void windowIconified(WindowEvent e) {}
            public void windowOpened(WindowEvent e) {
        }
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonCerrarSesion;
    private javax.swing.JTextField BuscarTexto;
    private javax.swing.JLabel Usuario;
    private javax.swing.JButton addFile;
    private javax.swing.JButton back;
    private javax.swing.JButton edit;
    private javax.swing.JButton elimAll;
    private javax.swing.JButton elimUno;
    private javax.swing.JTextField horas11;
    private javax.swing.JLabel info;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    public javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSlider jSlider13;
    private javax.swing.JSlider jSlider2;
    private javax.swing.JSlider jSlider3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel labelAlbum;
    private javax.swing.JLabel labelBirate1;
    private javax.swing.JLabel labelTiempo;
    private javax.swing.JButton next;
    private javax.swing.JButton play;
    private javax.swing.JButton stop;
    // End of variables declaration//GEN-END:variables
    public MiTabla modelo1;
    public int idSel = -1;
    public ManejoCanciones l;
    JFileChooser fc1;        
    automatico a;
    public int u = 0;
    boolean silencio = false;
    int vol;
    boolean moverInfo = true;
    public String estado = "Stop";
    int estaSonando = -1;
    ArrayList<String> lista = new ArrayList<String>();
    ArrayList<String> listaSoloT = new ArrayList<String>();
    int cont;
    boolean playlist = true;

}
