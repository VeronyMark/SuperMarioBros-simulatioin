package sk.uniza.fri.hra.nepriatelia;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sk.uniza.fri.hra.prostredie.Smer;


/**
 * Trieda Netopier je potomok triedy Nepriatel
 * Vyjadruje ako reaguje hrac na koliziu
 * Vyjadruje sposob pohybu
 * @author Veronika Markova
 * @version 1.1
 */
public class Netopier extends Nepriatel {
    private static final int RYCHLOST = 3;
    private static final int MAXROZSAH = 500;
    private static final int MINROZSAH = 100;

    private static final int NEPRIATELVMAPE = 1000;

    private int poziciaX;
    private int poziciaY;
    private int smer;

    /**
     * inicializacia potrebnych atributov
     * @param pozX  typu int znazornuje poziciu X
     * @param pozY typu int znazornuje poziciu Y
     * @param obrazok1 typu String  znazornuje nazov prveho obrazku
     * @param obrazok2 typu String znazornuje nazov druheho obrazku
     */
    public Netopier(int pozX, int pozY, String obrazok1, String obrazok2) {
        super(obrazok1, obrazok2);
        this.poziciaX = pozX;
        this.poziciaY = pozY;
        this.smer = 1;
    }

    /**
     *  Vykreslenie pohybu objektu
     * @param batch  typu SpriteBatch vykreslovac obrazka
     * @param poziciaXVMape typu float znazornuje poziciu X hraca v mape
     */
    public void pohyb( SpriteBatch batch, float poziciaXVMape) {

        if (poziciaXVMape > NEPRIATELVMAPE) {
            if (this.poziciaY > MAXROZSAH) {
                this.smer = -1;
            } else if (this.poziciaY < MINROZSAH) {
                this.smer = 1;
            }
            this.poziciaY += this.smer * RYCHLOST;
            batch.draw(super.getObrazok(), this.poziciaX, this.poziciaY);
            this.zmenaObrazku();
        }
    }

    /**
     *metoda vyjadruje ako nepriatel  reaguje na pohyb mapy
     * @param batch typu SpriteBatch vykreslovac
     * @param smerStrany typu Smer znazornuje smer ktorym sa predmet pohybuje
     * @param poziciaXVMape vyjadruje aktualnu poziciu hraca v mape
     */
    @Override
    public void pohybSMapou( SpriteBatch batch, Smer smerStrany, float poziciaXVMape) {
        if (poziciaXVMape > NEPRIATELVMAPE) {
            switch (smerStrany) {
                case VPRAVO:
                    this.poziciaX -= 4;
                    break;
                case VLAVO:
                    this.poziciaX += 4;
                    break;
            }
            batch.draw(super.getObrazok(), this.poziciaX, this.poziciaY);
        }
    }

    /**
     * vracia poziciu X
     * @return poziciaX typu float reprezentuje poziciu X
     */
    public int getPozX() {
        return this.poziciaX;
    }

    /**
     * metoda kontroluje koliziu hraca s nepriatlom
     * @param pozY typu float reprezentuje poziciu Y hraca
     * @return vracia pravdivostnu hodnotu
     */
    public boolean kontrolaKolizie(float pozY) {
        if (this.poziciaX == 600 ) {
            float vzdialenost = this.poziciaY - pozY;
            if (Math.abs(vzdialenost) <= 100) {
                return true;
            }
        }
        return false ;
    }

    /**
     *metoda meni obrazok objektu
     */
    public void zmenaObrazku() {
        super.zmenaObrazku();
    }
}