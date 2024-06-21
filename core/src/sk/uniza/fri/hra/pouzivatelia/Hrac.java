package sk.uniza.fri.hra.pouzivatelia;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sk.uniza.fri.hra.prostredie.Smer;
import sk.uniza.fri.hra.okna.PrehraOkno;

/**
 *Trieda hrac reprezentuje hraca hry
 *Obsahuje informacie o hracovi, prebiehajucom stave, rychosti, celkove skore, pocet zivotov
 * @author Veronika Markova
 * @version 1.1
 */
public class Hrac {
    private static final int RYCHLOST = 300;
    private static final int ROZSAHSKOKU = 380;
    private static final int POZICIAXZIVOT = 900;
    private static final int POZICIAYZIVOT = 700;

    private final Texture[] obrazkyHracovVpravo;
    private final Texture[] obrazkyHracovVlavo;
    private final Texture[] obrazkyZivotov;

    private Texture obrazokHraca;
    private Texture obrazokZivot;

    private final float sirkaObrazka;
    private final float vyskaObrazka;
    private float poziciaX;
    private float poziciaY;

    private boolean vrchol;
    private float vyskaSkoku;
    private int pocitadlo;
    private int celkoveSkore;
    private int pocetZivotovHraca;

    /**
     * Vytvara hraca, inicializuje graficke znazornenie hraca a jeho aktualneho stavu poctu zivotov
     * inicializuje celkove skore
     *
     * @param poziciaX typu float pozicia X hraca
     * @param poziciaY typu float pozicia Y hraca
     */
    public Hrac( float poziciaX, float poziciaY) {

        this.obrazkyHracovVpravo = new Texture[] {new Texture("marioPohyb1_vpravo.png"), new Texture("marioPohyb2_vpravo.png"), new Texture("marioPohyb3_vpravo.png")};
        this.obrazkyHracovVlavo = new Texture[] {new Texture("marioPohyb1_vlavo.png"), new Texture("marioPohyb2_vlavo.png"), new Texture("marioPohyb3_vlavo.png")};

        this.obrazokHraca = this.obrazkyHracovVpravo[0];
        this.poziciaX = poziciaX;
        this.poziciaY = poziciaY;
        this.sirkaObrazka = this.obrazokHraca.getWidth();
        this.vyskaObrazka = this.obrazokHraca.getHeight();
        this.vrchol = false;
        this.pocitadlo = 1;

        this.pocetZivotovHraca = 3;
        this.obrazkyZivotov = new Texture[] {new Texture("1zivot.png"), new Texture("2zivoty.png"), new Texture("3zivoty.png")};
        this.obrazokZivot = this.obrazkyZivotov[2];
        this.celkoveSkore = 0;
    }

    /**
     * metoda prida obrazok hraca a graficke znazornenie zivotov hraca
     * @param batch typu SpriteBatch
     */
    public void pridaj(SpriteBatch batch) {
        batch.draw(this.obrazokHraca, this.poziciaX, this.poziciaY);
        batch.draw(this.obrazokZivot, POZICIAXZIVOT, POZICIAYZIVOT);
    }

    /**
     * vracia poziciu X hraca
     * @return poziciaX typu float
     */
    public float getPoziciaX() {
        return this.poziciaX;
    }

    /**
     * vracia poziciu Y hraca
     * @return poziciaY typu float
     */
    public float getPoziciaY() {
        return this.poziciaY;
    }

    /**
     * vracia sirku obrazku, ktory znazornuje hraca
     * @return sirkaObrazka typu float
     */
    public float getSirkaObrazka() {
        return this.sirkaObrazka;
    }

    /**
     * vracia vysku obrazku, ktory znazornuje hraca
     * @return vyskaObrazka typu float
     */
    public float getVyskaObrazka() {
        return this.vyskaObrazka;
    }

    /**
     *metoda skok vyjadruje skok hraca, ktr si vykona ak je parameter jump true
     * @param deltaTime typu float
     * @param batch typu SpriteBatch vykreslovac
     * @param jump typu boolean
     */
    public void skok(float deltaTime, SpriteBatch batch, boolean jump) {
        if (jump) {
            this.poziciaY +=  deltaTime * RYCHLOST;
            batch.draw(this.obrazokHraca, this.poziciaX, this.poziciaY);
            if ((this.poziciaY + this.vyskaObrazka + 16) >= (this.vyskaSkoku)) {
                this.vrchol = true;
            }
        }
    }

    /**
     * vracia hodnotu vrcholu,
     * vracia ci hrac dosiahol alebo nedosiahol vrchol vyskoku
     * @return vrchol typu boolean
     */
    public boolean dosiahnutieVrcholu() {
        return this.vrchol;
    }

    /**
     * metoda void meni premennu vrchol
     */
    public void zmenVrchol() {
        this.vrchol = !this.vrchol;
    }

    /**
     * vykresluje pohyb padu hraca
     * @param deltaTime typu float
     * @param batch typu SpriteBatch vykreslovac obrazku hraca
     * @param fall typu boolean
     */
    public void pad(float deltaTime, SpriteBatch batch, boolean fall) {
        if (fall) {
            this.poziciaY -= deltaTime * RYCHLOST;
            batch.draw(this.obrazokHraca, this.poziciaX, this.poziciaY);
        }
    }

    /**
     * metoda meni obrazok hraca, vytvara iluziu, ze sa hrac pohybuje
     * @param batch typu SpriteBatch vykreslovac hraca
     * @param smer typu Smer
     */
    public void zmenaObrazkuHraca(SpriteBatch batch, Smer smer ) {
        this.pocitadlo = (this.pocitadlo + 1) % 15;
        if (smer.equals(Smer.VPRAVO)) {
            switch (this.pocitadlo) {
                case 0:
                    this.obrazokHraca = this.obrazkyHracovVpravo[1];
                    break;
                case 5:
                    this.obrazokHraca = this.obrazkyHracovVpravo[0];
                    break;
                case 10:
                    this.obrazokHraca = this.obrazkyHracovVpravo[2];
                    break;
            }
        } else if (smer.equals(Smer.VLAVO)) {
            switch (this.pocitadlo) {
                case 0:
                    this.obrazokHraca = this.obrazkyHracovVlavo[1];
                    break;
                case 5:
                    this.obrazokHraca = this.obrazkyHracovVlavo[0];
                    break;
                case 10:
                    this.obrazokHraca = this.obrazkyHracovVlavo[2];
                    break;
            }
        }
        batch.draw(this.obrazokHraca, this.poziciaX, this.poziciaY);

    }

    /**
     * metoda nastavi vysku skoku vzhladom na aktualnu vysku hraca
     */
    public void setVyskaSkoku() {
        this.vyskaSkoku = this.poziciaY + ROZSAHSKOKU;
    }

    /**
     * metoda zobrazZivot zobrazuje zivot hraca, graficke znazornenie
     * na zaklade hodnoty poctu zivotov hraca
     * @param batch typu SpriteBatch vykreslovac zivotov hraca
     */
    public void zobrazZivot(SpriteBatch batch) {

        switch (this.pocetZivotovHraca) {
            case 3:
                this.obrazokZivot = this.obrazkyZivotov[2];
                break;
            case 2:
                this.obrazokZivot = this.obrazkyZivotov[1];
                break;
            case 1:
                this.obrazokZivot = this.obrazkyZivotov[0];
                break;
        }
        batch.draw(this.obrazokZivot, POZICIAXZIVOT, POZICIAYZIVOT);
    }


    /**
     * metoda znizZivot typu void
     * znizuje pocet zivotov hraca
     * ak ma hrac 0 zivotov zomiera
     */
    public void znizZivot() {
        this.pocetZivotovHraca--;
        if (this.pocetZivotovHraca == 0) {
            this.zomri();
        }
    }

    /**
     * metoda zomri typu void
     * ukoncuje libGDX application
     * vytvara  informace okno
     */
    public void zomri() {
        Gdx.app.exit();
        PrehraOkno prehra = new PrehraOkno();

    }

    /**
     * metoda pridajZivot typu void
     * ak hrac ma menej ako je max pocet zivotov prida sa mu zivot
     * inak sa prida skore
     */
    public void pridajZivot() {
        if (this.pocetZivotovHraca < 3) {
            this.pocetZivotovHraca++;
        } else {
            this.pridajSkore(50);
        }
    }

    /**
     * metoda typu void zvysuje skore hraca
     * @param oKolko typu int znazornuje o kolko sa ma zvysit hracove skore
     */
    public void pridajSkore(int oKolko) {
        this.celkoveSkore += oKolko;
    }

    /**
     * vracia celkove skore hraca v hre
     * @return celkoveSkore typu int
     */
    public int getCelkoveSkore() {
        return this.celkoveSkore;
    }

}
