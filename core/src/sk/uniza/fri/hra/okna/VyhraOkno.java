package sk.uniza.fri.hra.okna;

/**
 *  Trieda VyhraOkno sa zobrazi hracovi, ked vyhra hru (uspesne prejde 3.level)
 *  trieda je potomok triedy Okno
 * @author Veronika Markova
 * @version 1.1
 */
public class VyhraOkno extends Okno {
    private static final String TEXT = "CONGRATULATIONS";
    private static final String POZADIE = "assets\\marioVyhraPozadie.jpg";

    /**
     *Vytvorenie okna s urcenim pozadim a textom
     */
    public VyhraOkno() {
        super(POZADIE, TEXT);
    }
}
