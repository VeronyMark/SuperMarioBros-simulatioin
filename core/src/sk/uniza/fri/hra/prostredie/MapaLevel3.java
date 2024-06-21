package sk.uniza.fri.hra.prostredie;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import sk.uniza.fri.hra.nepriatelia.KlietkaNepriatelov;
import sk.uniza.fri.hra.nepriatelia.Mucha;
import sk.uniza.fri.hra.nepriatelia.Nepriatel;
import sk.uniza.fri.hra.nepriatelia.Netopier;
import sk.uniza.fri.hra.nepriatelia.ZelenyVtak;
import java.util.HashMap;

/**
 * MapaLevel3 je potom triedy Mapa
 *
 * Obsahuje HashMap, informacie o pocteBodov, ktore hrac musi ziskat na prejdenie levela
 * informacie o cisle levela, obsahuje nepriatelov, ktore sa vyskytuju v hre
 *
 * @author Veronika Markova
 * @version 1.1
 */
public class MapaLevel3 extends Mapa {
    private HashMap<Integer, TypStvorca> tileMap;
    private static final int POCETBODOV = 10;
    private static final int CISLOLEVELU = 3;

    private Mucha mucha;
    private ZelenyVtak zelenyVtak;
    private Netopier netopier;
    private KlietkaNepriatelov<Nepriatel> klietkaNepriatelia;


    /**
     * konstruktor nacita prislusni level
     * vola privatnu metodu setUp
     */
    public MapaLevel3() {
        super("level3.tmx");
        this.setUp();
    }

    /**
     * privatna metoda typu void
     * pripraví level do zakladneho stavu, pripravi HashMap, vytvara nepriatelov a prida nepriatelov do klietkyNepriatelov
     */
    private void setUp() {
        this.tileMap = new HashMap<>();
        this.tileMap.put(TypStvorca.ZEM.getCislo(), TypStvorca.ZEM);
        this.tileMap.put(TypStvorca.OTAZNIKY.getCislo(), TypStvorca.OTAZNIKY);
        this.tileMap.put(TypStvorca.CIEL.getCislo(), TypStvorca.CIEL);
        this.tileMap.put(TypStvorca.BODY.getCislo(), TypStvorca.BODY);

        this.netopier = new Netopier(1400, 350, "netopier_1.png", "netopier_2.png");
        this.mucha = new Mucha(1400, 150, "vtakRuzovy_1.png", "vtakRuzovy_2.png");
        this.zelenyVtak = new ZelenyVtak(1400, 150, "vtakZeleny_1.png", "vtakZeleny_2.png" );

        this.klietkaNepriatelia = new KlietkaNepriatelov<>();

        this.klietkaNepriatelia.pridaj(this.zelenyVtak);
        this.klietkaNepriatelia.pridaj(this.netopier);
        this.klietkaNepriatelia.pridaj(this.mucha);
    }

    /**
     *  METODA PREBRATA Z INTERNETU
     *  metoda vyvolava render mapy
     * @param camera typu OrthograpicCamera
     */
    @Override
    public void render(OrthographicCamera camera) {
        super.render(camera);
    }

    /**
     *  METODA PREBRATA Z INTERNETU
     * @param vrstva typu int znazornuje vrstvu v mape
     * @param stlpec typu int znazornuje stlpec stvorca
     * @param riadok typu int znazornuje riadok stvorca
     * @return vracia typStvorca najdeny na danej pozicii
     */
    @Override
    public TypStvorca getStvorec(int vrstva, int stlpec, int riadok) {
        TiledMapTileLayer.Cell stvorec = ((TiledMapTileLayer)super.getMapa().getLayers().get(vrstva)).getCell(stlpec, riadok);
        if (stvorec != null) {
            TiledMapTile tile = stvorec.getTile();
            if (tile != null) {
                int id = tile.getId();
                return this.getStvorec(id);
            }
        }
        return null;
    }

    /**
     * METODA PREBRANA Z INTERNETU
     * vracia typ stvorca na zaklade identifikacneho cisla
     * @param id typu int reprezentuje cislo id
     * @return vracia stvorec podla identifikacneho cisla
     */
    private TypStvorca getStvorec(int id) {
        return this.tileMap.get(id);
    }

    /**
     * metoda nahrad typu void dokaze nahradit stvorec na zaklade pozicie
     * @param pozX typu int vyjadruje poziciu X
     * @param pozY typu int vyjadruje poziciu Y
     */
    public void nahrad( int pozX, int pozY) {
        super.nahrad(pozX, pozY );
    }


    /**
     * metoda typu boolean vyjadruje uspesnost prejdenia levela
     * @param pocetBodovHraca typu int znazornuje pocet bodov, ktore ziskal hrac v leveli
     * @return hodnotu true alebo false, vyjadruje uspesnost prejdenia levela
     */
    public boolean kontrolaPrejdeniaLevela(int pocetBodovHraca) {
        if (pocetBodovHraca >= POCETBODOV) {
            return true;
        }
        return false;
    }


    /**
     * metoda vracia ciselnu hodnotu levelu, teda poradie levelu v hre
     * @return CISLOLEVELU typu int vracia poradie levelu
     */
    @Override
    public int getCisloLevelu() {
        return CISLOLEVELU;
    }


    /**
     *metoda vracia pocet bodov, ktore potrebuje hrac ziskat aby uspesne absolvoval level
     * @return POCETBODOV typu int vracia pocet bodov ktore moze hrac ziskat v hre
     */
    @Override
    public int getPocetBodov() {
        return POCETBODOV;
    }

    /**
     *
     * @param batch typu SpriteBatch sluzi na vykreslovanie pohybu zvierat
     * @param pozX typu int vyjadruje poziciu X v mape
     */
    public void pohybZvierat( SpriteBatch batch, float pozX) {
        this.klietkaNepriatelia.pohyb( batch, pozX);
    }


    /**
     * metoda typu void vyjadruje pohyb zvierat v mape
     * @param batch typu SpriteBatch sluzi na vykreslovanie pohybu zvierat s mapou
     * @param smer vyjadruje smer pohybu zvierat
     * @param pozX typu int vyjadruje poziciu X v mape
     */
    @Override
    public void pohybZvieratSMapou( SpriteBatch batch, Smer smer, float pozX) {
        this.klietkaNepriatelia.pohybSMapou( batch, smer, pozX);
    }

    /**
     * metoda  vracia pravdivostnu hodnotu vyjadrujucu koliziu hraca s nepriatelom
     * @param pozX typu float vyjadruje poziciu X hraca v mape
     * @param pozY typu float vyjadruje poziciu Y hraca v mape
     * @return vracia pravdivostnu vyjadruje ci nastala kolizia
     */
    public boolean kontrolaKolizie(float pozX, float pozY) {
        for (Nepriatel nepriatel : this.klietkaNepriatelia) {
            if (nepriatel.kontrolaKolizie(pozY)) {
                return true;
            }
        }
        return false;
    }
}
