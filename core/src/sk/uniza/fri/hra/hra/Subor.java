package sk.uniza.fri.hra.hra;

import sk.uniza.fri.hra.pouzivatelia.Hrac;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;


/**
 *Trieda subor obsahuje subor kde su ulozeni vsetci hraci aj so skore
 * Trieda dokaze do suboru zapisat dalsieho hraca a vratit hraca s najlepsim skore
 * @author Veronika Markova
 * @version 1.1
 */
public class Subor {
    private File suborUlozenie;
    private PrintWriter zapisovac;
    private HashMap<String, Integer> hraci;

    /**
     * konstruktor cita subor a hracov uklada do HashMapy, kde klucom je meno hraca a hodnotou je celkoveSkore hraca
     *
     * @throws FileNotFoundException vynimka v pripade, ze subor nenajdeme
     */

    public Subor() throws FileNotFoundException {

        this.suborUlozenie = new File("HRACIskore.hrn");
        this.hraci = new HashMap<>();
        Scanner citac = new Scanner(this.suborUlozenie);

        while (citac.hasNextLine()) {
            String[] riadok = citac.nextLine().split(" ");
            String key = riadok[0];
            Integer value = Integer.parseInt(riadok[1]);
            this.hraci.put(key, value);
        }
        citac.close();
    }

    /**
     * metoda uklada do suboru vsetkych hracov z hashmapu a noveho hraca, ktory prave ukoncil hru
     *
     * @param hrac typu Hrac
     * @param hra typu Hra
     * @throws FileNotFoundException vynimka ked nenajdeme subor
     */
    public void ulozenieHry(Hrac hrac, Hra hra) throws FileNotFoundException {

        this.zapisovac = new PrintWriter(this.suborUlozenie);
        for (String h : this.hraci.keySet()) {
            this.zapisovac.println(h + " " + this.hraci.get(h));
        }

        this.zapisovac.print(hra.getMenoHraca() + " ");
        this.zapisovac.println(hrac.getCelkoveSkore());
        this.zapisovac.close();

    }

    /**
     * metoda dajHodnotenie vracia Stringovu reprezentaciu najlepsieho hraca aj s jeho skore
     * v pripade, ze hru este nikto nehral vracia text NULL
     * @return  reprezentacia najlepsieho hraca typu String
     */
    public String dajHodnotenie() {
        if (this.hraci.isEmpty()) {
            return ("NULL");
        } else {
            int maxHodnota = -1;
            String meno = null;
            for (String hrac : this.hraci.keySet()) {
                if (this.hraci.get(hrac) > maxHodnota) {
                    maxHodnota = this.hraci.get(hrac);
                    meno = hrac;
                }
            }
            return (meno + " " + maxHodnota);
        }
    }
}

