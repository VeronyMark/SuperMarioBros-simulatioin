package sk.uniza.fri.hra.predmety;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sk.uniza.fri.hra.pouzivatelia.Hrac;
import sk.uniza.fri.hra.prostredie.Smer;

/**
 * Interface IPredmet obsahuje metody typu void znazornujuce pohyb, pouzitie a pohyb s mapou
 * metody s navratovou hodnotou typu float vracajucu poziciu X a poziciu Y
 * @author Veronika Markova
 * @version 1.1
 *
 */

public interface IPredmet {
    void pohyb( float deltaTime, SpriteBatch batch);
    void pouzi(Hrac hrac, SpriteBatch batch);
    float getPoziciaX();
    float getPoziciaY();
    void pohybSMapou( SpriteBatch batch, Smer smerStrany);
}
