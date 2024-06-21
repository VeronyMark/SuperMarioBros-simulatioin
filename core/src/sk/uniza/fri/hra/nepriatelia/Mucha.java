package sk.uniza.fri.hra.nepriatelia;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sk.uniza.fri.hra.prostredie.Smer;


/**
 * Trieda Mucha je potomok triedy Nepriatel
 * Vyjadruje ako reaguje hrac na koliziu
 * Vyjadruje sposob pohybu
 * @author Veronika Markova
 * @version 1.1
 */
public class Mucha extends Nepriatel {
    private static final int RYCHLOST = 12;
    private static final int MAX = 600;
    private static final int MIN = 50;
    private static final int NEPRIATELVMAPE = 700;
    private static final int STRED = 600;

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
    public Mucha( int pozX, int pozY, String obrazok1, String obrazok2) {
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
    @Override
    public void pohyb ( SpriteBatch batch, float poziciaXVMape) {

        if (poziciaXVMape > NEPRIATELVMAPE) {
            if (this.poziciaY > MAX) {
                this.smer = -1;
            } else if (this.poziciaY < MIN) {
                this.smer = 1;
            }

            this.poziciaY += this.smer * RYCHLOST;
            batch.draw(super.getObrazok(), this.poziciaX, this.poziciaY);
            this.zmenaObrazku();
        }
    }

    /**
     *metoda meni obrazok objektu
     */
    public void zmenaObrazku() {
        super.zmenaObrazku();
    }


    /**
     *metoda vyjadruje ako nepriatel  reaguje na pohyb mapy
     * @param batch typu SpriteBatch vykreslovac
     * @param smerStrany typu Smer znazornuje smer ktorym sa predmet pohybuje
     * @param poziciaXVMape vyjadruje aktualnu poziciu hraca v mape
     */
    @Override
    public void pohybSMapou ( SpriteBatch batch, Smer smerStrany, float poziciaXVMape) {

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
    @Override
    public boolean kontrolaKolizie(float pozY) {

        float vzdialenost1 = Math.abs(this.poziciaX - STRED);
        float vzdialenost2 = Math.abs(this.poziciaY - pozY);
        if (vzdialenost2 <= 20 && vzdialenost1 <= 20) {
            return true;
        }
        return false ;
    }
}