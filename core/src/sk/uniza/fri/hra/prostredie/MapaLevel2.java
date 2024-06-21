package sk.uniza.fri.hra.prostredie;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import java.util.HashMap;

/**
 * MapaLevel2 je potom triedy Mapa
 *
 * Obsahuje HashMap, informacie o pocteBodov, ktore hrac musi ziskat na prejdenie levela
 * informacie o cisle levela
 * @author Veronika Markova
 *  @version 1.1
 */
public class MapaLevel2 extends Mapa {
    private HashMap<Integer, TypStvorca> tileMap;
    private static final int POCETBODOV = 11;
    private static final int CISLOLEVELU = 2;

    /**
     * konstruktor nacita prislusni level
     * vola privatnu metodu setUp
     */
    public MapaLevel2 () {
        super("level2.tmx");
        this.setUp();
    }

    /**
     * privatna metoda typu void
     * pripraví level do zakladneho stavu, pripravi HashMap, prida nepriatelov
     */
    private void setUp() {
        this.tileMap = new HashMap<>();
        this.tileMap.put(TypStvorca.ZEM.getCislo(), TypStvorca.ZEM);
        this.tileMap.put(TypStvorca.OTAZNIKY.getCislo(), TypStvorca.OTAZNIKY);
        this.tileMap.put(TypStvorca.CIEL.getCislo(), TypStvorca.CIEL);
        this.tileMap.put(TypStvorca.BODY.getCislo(), TypStvorca.BODY);
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
     *metoda vracia pocet bodov, ktore potrebuje hrac ziskat aby uspesne absolvoval level
     * @return POCETBODOV typu int vracia pocet bodov ktore moze hrac ziskat v hre
     */
    @Override
    public int getPocetBodov() {
        return POCETBODOV;
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
     * V tejto triede sa nenachadzaju nepriatelia, pohyb zvierat nevykona ziadnu akciu
     * @param batch typu SpriteBatch sluzi na vykreslovanie pohybu zvierat
     * @param pozX typu int vyjadruje poziciu X v mape
     */
    @Override
    public void pohybZvierat( SpriteBatch batch, float pozX) {
    }

    /**
     * V tejto triede sa nenachadzaju nepriatelia, pohyb zvierat s mapou nevykona ziadnu akciu
     * @param batch typu SpriteBatch sluzi na vykreslovanie pohybu zvierat s mapou
     * @param smer vyjadruje smer pohybu zvierat
     * @param pozX typu int vyjadruje poziciu X v mape
     */
    @Override
    public void pohybZvieratSMapou( SpriteBatch batch, Smer smer, float pozX) {
    }

    /**
     * metoda v tejto trieda vracia pravdivostnu hodnotu false, kolizia s nepriatelom nenastane
     * @param pozX typu float vyjadruje poziciu X hraca v mape
     * @param pozY typu float vyjadruje poziciu Y hraca v mape
     * @return
     */
    @Override
    public boolean kontrolaKolizie(float pozX, float pozY) {
        return false;
    }

}
