package sk.uniza.fri.hra.prostredie;

/**
 *enum typu stvorca, kazdy stvorec obsahuje jedinecne identifikacne cislo
 *enum reprezentuje 4 typy stvorcov
 * @author Veronika Markova
 * @version 1.1
 */

public enum TypStvorca {
    ZEM(133 ),
    OTAZNIKY (25),
    CIEL(67),
    BODY( 58);

    private int identifikacneCislo;

    /**
     * konstruktor typu stvorca priradi instancii identifikacne cislo
     * @param cislo typu int, kazdy stvorec obsahuje specificke cislo
     */
    TypStvorca(int cislo) {
        this.identifikacneCislo = cislo;
    }

    /**
     *Vrati cislo stvorca typu int
     * @return identifikacneCislo typu int
     */
    public int getCislo() {
        return this.identifikacneCislo;
    }

}
