package sk.uniza.fri.hra.predmety;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sk.uniza.fri.hra.pouzivatelia.Hrac;
import sk.uniza.fri.hra.prostredie.Smer;

/**
 * Trieda Hviezda implementuje interface IPredmet
 * Vykreslenie, reakcie hviezdy na pohyb mapy, pouzitie
 * @author  Veronika Markova
 * @version 1.1
 */
public class Hviezda implements IPredmet {
    private static final String OBRAZOK = "hviezda.png";
    private final Texture obrazok;
    private float poziciaX;
    private float poziciaY;

    /**
     * inicializacia potrebnych atributov
     * @param pozX typu float znazornuje poziciuX predmetu
     * @param pozY typu float znazornuje poziciuX predmetu
     */
    public Hviezda(float pozX, float pozY) {
        this.poziciaX = pozX;
        this.poziciaY = pozY;
        this.obrazok = new Texture(OBRAZOK);
    }


    /**
     * Vykreslenie hviezdy
     * @param deltaTime typu float
     * @param batch typu SpriteBatch vykreslovac obrazka
     */
    @Override
    public void pohyb(float deltaTime, SpriteBatch batch) {
        batch.draw(this.obrazok,  this.getPoziciaX(),  this.getPoziciaY() );
    }


    /**
     * vyjadruje reakciu na pouzitie predmetu
     *
     * @param hrac typu Hrac
     * @param batch typu SpriteBatch
     */
    @Override
    public void pouzi(Hrac hrac, SpriteBatch batch) {
        hrac.pridajSkore(25);
    }

    /**
     * vracia poziciu X
     * @return poziciaY typu float reprezentuje poziciu Y
     */
    @Override
    public float getPoziciaX() {
        return this.poziciaX;
    }

    /**
     * vracia poziciu Y
     * @return poziciaY typu float reprezentuje poziciu Y
     */
    @Override
    public float getPoziciaY() {
        return this.poziciaY;
    }

    /**
     * metoda vyjadruje ako predmet reaguje na pohyb mapy
     * @param batch typu SpriteBatch vykreslovac
     * @param smerStrany typu Smer znazornuje smer ktorym sa predmet pohybuje
     */
    @Override
    public void pohybSMapou( SpriteBatch batch, Smer smerStrany) {
        switch (smerStrany) {
            case VPRAVO:
                this.poziciaX -= 4 ;
                batch.draw(this.obrazok, this.poziciaX,  this.poziciaY );
                break;
            case VLAVO:
                this.poziciaX += 4 ;
                batch.draw(this.obrazok, this.poziciaX,  this.poziciaY );
                break;
        }
    }

}

