package sk.uniza.fri.hra.nepriatelia;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sk.uniza.fri.hra.prostredie.Smer;


/**
 * Abstraktna trieda Nepriatel
 * Vyjadruje ako reaguje hrac na koliziu
 * Vyjadruje sposob pohybu
 * @author  Veronika Markova
 * @version 1.1
 */

public abstract class Nepriatel {
    private int pocitadlo;
    private Texture obrazok;
    private Texture[] nepriatel;

    /**
     * inicializacia potrebnych atributov
     * @param obrazok1 typu String znazornuje nazov prveho obrazku
     * @param obrazok2 typu String znazornuje nazov druheho obrazku
     */
    public Nepriatel (String obrazok1, String obrazok2) {
        this.pocitadlo = 0;
        this.nepriatel = new Texture[] {new Texture(obrazok1), new Texture(obrazok2)};
        this.obrazok = this.nepriatel[0];
    }
    /**
     *metoda meni obrazok objektu
     */
    public void zmenaObrazku() {
        this.pocitadlo =  (this.pocitadlo + 1) % 15;
        switch (this.pocitadlo) {
            case 1:
                this.obrazok = this.nepriatel[1];
                break;
            case 10:
                this.obrazok = this.nepriatel[0];
                break;
        }
    }

    /**
     * vracia znazornenie objektu
     * @return obrazok typu Texture
     */
    public Texture getObrazok() {
        return this.obrazok;
    }

    /**
     *  Vykreslenie pohybu objektu
     * @param batch  typu SpriteBatch vykreslovac obrazka
     * @param pozX typu float znazornuje poziciu X hraca v mape
     */
    public abstract void pohyb( SpriteBatch batch, float pozX);

    /**
     * metoda vyjadruje ako predmet reaguje na pohyb mapy
     * @param batch typu SpriteBatch vykreslovac
     * @param smerStrany typu Smer znazornuje smer ktorym sa predmet pohybuje
     */
    public abstract void pohybSMapou ( SpriteBatch batch, Smer smerStrany, float poziciaXVMape);

    /**
     * metoda kontroluje koliziu hraca s nepriatlom
     * @param pozY typu float reprezentuje poziciu Y hraca
     * @return vracia pravdivostnu hodnotu
     */
    public abstract boolean kontrolaKolizie(float pozY);
}
