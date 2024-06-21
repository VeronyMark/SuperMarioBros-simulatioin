package sk.uniza.fri.hra.okna;

/**
 *  Trieda PrehraOkno sa zobrazi hracovi, ked hrac prehrava
 *  Trieda je potomok triedy Okno
 * @author Veronika Markova
 * @version 1.1
 */
public class PrehraOkno extends Okno {
    private static final String POZADIE = "assets\\gameOverPozadie.jpg";

    /**
     * Vytvorenie okna s urcenim pozadim
     */
    public PrehraOkno() {
        super(POZADIE , null);
    }

}