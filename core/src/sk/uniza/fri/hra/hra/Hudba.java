package sk.uniza.fri.hra.hra;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Trieda Hudba znazornuje hudbu v hre a grafické znazornenie hudby na hracej ploche
 *
 * @author Veronika Markova
 * @version 1.1
 */
public class Hudba {
    private static final int POZICIAX = 975;
    private static final int POZICIAY = 630;
    private int vyskaObrazka;
    private int sirkaObrazka;

    private Texture obrazok;
    private Texture[] obrazkyHudba;
    private Music hudbaHry;

    /**
     * Vytvara graficke znazornenie hudby na hracej ploche
     * inicializuje atributy reprezentujuce sirku a vysku obrazku
     * Inicializuje hudbu hry, spusta hudbu
     * nastavenie hudby hry na prehravanie znova a znova
     */
    public Hudba () {
        this.obrazkyHudba = new Texture[] {new Texture("hudba_vypnuta.png"), new Texture("hudba_zapnuta.png")};
        this.obrazok = this.obrazkyHudba[1];
        this.sirkaObrazka = this.obrazok.getWidth();
        this.vyskaObrazka = this.obrazok.getHeight();
        this.hudbaHry = Gdx.audio.newMusic(Gdx.files.internal("hudba/Hudba_pozadie.mp3"));
        this.hudbaHry.setLooping(true);
        this.hudbaHry.play();
    }


    /**
     *Nakreslenie grafickeho znazornenia hudny na obrazovku
     * @param batch typu SpriteBatch
     */
    public void pridaj(SpriteBatch batch) {
        batch.draw(this.obrazok, POZICIAX, POZICIAY);
    }

    /**
     *Verejna metoda zmena stavu zmení stav hudby a graficke znazornenie hudby
     * Ak hudba hra, tak hudba sa stopne a zmení sa graficke znazornenie
     * Ak hudba nehra, tak sa hudba spusti a zmení sa graficke znazornenie
     */
    public void zmenaStavu() {
        if (this.obrazok == this.obrazkyHudba[0]) {
            this.obrazok = this.obrazkyHudba[1];
            this.hudbaHry.play();
        } else {
            this.obrazok = this.obrazkyHudba[0];
            this.hudbaHry.pause();
        }
    }

    /**
     *Vrati poziciu X obrazku, ktr znazornuje hudbu na hracej ploche
     * @return POZICIAX typu int
     */
    public int getPozX() {
        return POZICIAX;
    }

    /**
     *Vrati poziciu Y obrazku, ktr znazornuje hudbu na hracej ploche
     * @return POZICIAY typu int
     */
    public int getPozY() {
        return POZICIAY;
    }

    /**
     *Vrati vysku obrazku, ktr znazornuje hudbu na hracej ploche
     * @return vyskaObrazka typu int
     */
    public int getVyska() {
        return this.vyskaObrazka;
    }

    /**
     * Vrati sirku obrazku, ktr znazornuje hudbu na hracej ploche
     * @return sirkaObrazka typu int
     */
    public int getSirka() {
        return this.sirkaObrazka;
    }
}
