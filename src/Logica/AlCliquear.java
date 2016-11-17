/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Logica;
import Clases.Cancion;
import GUI.Reproductor;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.UnsupportedAudioFileException;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import org.tritonus.share.sampled.TAudioFormat;
import org.tritonus.share.sampled.file.TAudioFileFormat;


/**
 *
 * @author AndresZ
 */
public class AlCliquear {
    private BasicPlayer player;
    private String album;
    private long duracion;
    private int birate;




    public AlCliquear(){
        player = new BasicPlayer();        
    }    

    public String getAlbum(){
        return album;
    }
    public long getDuracion(){
        return duracion;
    }
    public int getBirate(){
        return birate;
    }
    public String Reproducir(int id,int v,int m,ArrayList<Cancion> l) throws BasicPlayerException {
        int i = 0;
        String ruta = "";
        boolean noEncontre = false;
        while(i < l.size() && !noEncontre){
            Cancion c = l.get(i);
            if (c.getId() == id){
                ruta = c.getNombre();
                noEncontre = true;
            }
            i++;
        }        
        ruta = ruta.replace('?', '\'');        
        File aux = new File(ruta);                        
        String s = "";
        String as = "";
        String a = "";
        AudioFileFormat baseFileFormat = null;
        AudioFormat baseFormat = null;
        try {
            baseFileFormat = AudioSystem.getAudioFileFormat(aux);
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
            key = "duration";
            duracion = (Long)properties.get(key);
            key = "album";
            a = (String)properties.get(key);
        }
        if (baseFormat instanceof TAudioFormat)
        {
             Map properties = ((TAudioFormat)baseFormat).properties();
             String key = "bitrate";
             birate = (Integer) properties.get(key);
        }

        if ((as == null || as.equals("")) && (s == null || s.equals(""))) as = aux.getName();
        /*if (s == null || s.equals("")) s = "IntérpreteDesconocido";
        if (a == null || a.equals("")) a = "Album Desconocido";*/
        String res = as + " :: " + s;
        album = a;
        player.open(new File(ruta));
        
        player.play();             
        player.setGain((double) v / (double) m);        
        return res;
    }
    public void adelantar(double b){
        try {
            player.seek((long) b);
        } catch (BasicPlayerException ex) {
            Logger.getLogger(AlCliquear.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setVolumen(float a) throws BasicPlayerException{
        player.setGain(a);
    }
    public void resume() throws BasicPlayerException{
        player.resume();
    }
    public void pausa() throws BasicPlayerException{
        player.pause();
    }
    public void stop() throws BasicPlayerException{
        player.stop();
    }
    public BasicPlayer getPlayer() {
        return player;
    }
    public void setPan(double p){
        try {
            player.setPan(p);
        } catch (BasicPlayerException ex) {
            Logger.getLogger(AlCliquear.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void despDeEditar(int id,ArrayList<Cancion> l){
        int i = 0;
        String ruta = "";
        boolean noEncontre = false;
        while(i < l.size() && !noEncontre){
            Cancion c = l.get(i);
            if (c.getId() == id){
                ruta = c.getNombre();
                noEncontre = true;
            }
            i++;
        }
        try {
            ruta = ruta.replace('?', '\'');
            player.open(new File(ruta));            
            player.play();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(AlCliquear.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
