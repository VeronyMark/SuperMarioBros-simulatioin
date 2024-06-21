package sk.uniza.fri.hra.nepriatelia;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sk.uniza.fri.hra.prostredie.Smer;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *trieda KlietkaNepriatelov potomo Nepriatela
 * implementujeme iterator Iterable aby bolo mozne prechadzat KlietkuNepriatelov
 * @param <T> typovy parameter T vseobecny typ
 *
 *  @author Veronika Markova
 *  @version 1.1
 */
public class KlietkaNepriatelov<T extends Nepriatel> implements Iterable<T> {
    private ArrayList<T> klietkaNepriatelov;

    /**
     *inicializacia A
     */
    public KlietkaNepriatelov() {
        this.klietkaNepriatelov = new ArrayList<>();
    }

    /**
     * verejna metoda typu void pridava do arraylistu nepriatela
     *
     * @param zviera typu T
     */
    public void pridaj(T zviera) {
        this.klietkaNepriatelov.add(zviera);
    }

    /**
     *vraciame to, co sa ma spravat ako iterator
     * @return iterator
     */
    public Iterator<T> iterator() {
        return this.klietkaNepriatelov.iterator();
    }

    /**
     *
     * @param batch typu SpriteBatch vykreslovac
     * @param pozX typu float vyjadruje aktualnu poziciu hraca v mape
     */
    public void pohyb ( SpriteBatch batch, float pozX) {
        for (T zviera : this.klietkaNepriatelov) {
            (zviera).pohyb( batch, pozX);
        }
    }

    /**
     * metoda prechadza Arraylistom
     *
     * @param batch typu SpriteBatch vykreslovac
     * @param smer typu Smer znazornuje smer ktorym sa zviera pohybuje
     * @param pozXMapa typu float vyjadruje aktualnu poziciu hraca v mape
     */
    public void pohybSMapou ( SpriteBatch batch, Smer smer, float pozXMapa) {
        for (T zviera : this.klietkaNepriatelov) {
            (zviera).pohybSMapou( batch, smer, pozXMapa);
        }
    }

}
