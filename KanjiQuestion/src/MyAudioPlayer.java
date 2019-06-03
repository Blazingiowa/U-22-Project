import java.io.InputStream;
import java.net.URL;

import javazoom.jl.player.Player;

public class MyAudioPlayer extends Thread {

    private URL filepass;
    private boolean loop;
    private Player prehravac;

    public MyAudioPlayer(String getpass, boolean loop) {
    	filepass= this.getClass().getClassLoader().getResource(getpass);
        this.loop = loop;
        start();
    }

    public void run() {

        try {
            do {
                InputStream buff = filepass.openStream();
                prehravac = new Player(buff);
                prehravac.play();
            } while (loop);
        } catch (Exception ioe) {
            // TODO error handling
        }
    }

    public void close(){
        loop = false;
        prehravac.close();
        this.interrupt();
    }
}