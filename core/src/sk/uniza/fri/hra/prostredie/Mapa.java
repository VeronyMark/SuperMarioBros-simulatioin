package sk.uniza.fri.hra.prostredie;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * TRIEDA MAPA OBSAHUJE METODY PREBRANE Z INTERNETU
 *
 * @author     Veronika Markova
 * @version 1.1
 */

public abstract class Mapa {
    private TiledMap mapa;
    private OrthogonalTiledMapRenderer tiledMapRenderer;

    /**
     * PREBRANE Z INTERNETU
     * nacitanie mapy podla nazvuLevelu
     * @param nazovLevelu typu String
     */

    public Mapa(String nazovLevelu) {
        this.mapa = new TmxMapLoader().load(nazovLevelu);
        this.tiledMapRenderer = new OrthogonalTiledMapRenderer(this.mapa);
    }

    /**
     *  METODA PREBRATA Z INTERNETU
     *  metoda vyvolava render mapy
     * @param camera typu OrthograpicCamera
     */
    public void render(OrthographicCamera camera) {
        this.tiledMapRenderer.setView(camera);
        this.tiledMapRenderer.render();
    }


    /**
     *  METODA PREBRATA Z INTERNETU
     * @param vrstva typu int znazornuje vrstvu v mape
     * @param stlpec typu int znazornuje stlpec stvorca
     * @param riadok typu int znazornuje riadok stvorca
     * @return vracia typStvorca najdeny na danej pozicii
     */
    public abstract TypStvorca getStvorec(int vrstva, int stlpec, int riadok);

    /**
     *  METODA PREBRATA Z INTERNETU
     *  ak vieme pixelovu lokalitu dokazeme najst stvorec, ktory koresponduje urcenej lokalite
     *  (velkost stvorca je 16x16)
     * @param vrstva typu int znazornuje vrstvu v mape
     * @param x typu float vyjadruje suradnicu x
     * @param y  typu float vyjadruje suradnicu x
     * @return  typ stvorca, ktori dokazeme najst podla lokality typu TypStvorca
     */
    public TypStvorca getStvorecPodlaSuradnice(int vrstva, float x, float y) {
        return this.getStvorec(vrstva, (int)(x / 16), (int)(y / 16));
    }

    /**
     *metoda vracia mapu
     * @return mapa typu TiledMap
     */

    public TiledMap getMapa() {
        return this.mapa;
    }

    /**
     * metoda nahrad typu void dokaze nahradit stvorec na zaklade pozicie
     * @param pozX typu int vyjadruje poziciu X
     * @param pozY typu int vyjadruje poziciu Y
     */
    public void nahrad(int pozX, int pozY) {
        int vrstva = 1;
        TiledMapTileLayer.Cell stvorec = ((TiledMapTileLayer)this.mapa.getLayers().get(vrstva)).getCell(pozX / 16 , pozY / 16);
        stvorec.setTile(null);
    }

    /**
     * abstraktna metoda getPocetBodov vracia pocet bodov, ktr hrac musi ziskat na
     * uspesne prejdenie levelu
     * @return pocet bodov typu int
     */
    public abstract int getPocetBodov();

    /**
     * abstraktna metoda typu boolean
     * kontroluje pocet bodov, ktore ziskal hrac s poctom bodov, ktore su potrebne na prejdenie levela
     * vracia hodnotu true, alebo false
     * @param pocetBodovHraca typu int znazornuje pocet bodov, ktore ziskal hrac v leveli
     * @return hodnota typu boolean
     */
    public abstract boolean kontrolaPrejdeniaLevela(int pocetBodovHraca);

    /**
     * abstraktna metoda getCisloLevelu vracia cislo konkretneho levelu
     * @return cisloLevelu typu int
     */
    public abstract int getCisloLevelu();

    /**
     *
     * @param batch typu SpriteBatch sluzi na vykreslovanie pohybu zvierat
     * @param pozX typu int vyjadruje poziciu X v mape
     */
    public abstract void pohybZvierat( SpriteBatch batch, float pozX);

    /**
     *abstraktna metoda typu void vyjadruje pohyb zvierat v mapou
     * @param batch typu SpriteBatch sluzi na vykreslovanie pohybu zvierat s mapou
     * @param smer vyjadruje smer pohybu zvierat
     * @param pozX typu int vyjadruje poziciu X v mape
     */
    public abstract void pohybZvieratSMapou( SpriteBatch batch, Smer smer, float pozX);

    /**
     *abstraktna metoda typu boolean, vracia hodnotu vyjadrujucu uspesnost kolizie
     * @param pozX typu float
     * @param pozY typu float
     * @return true alebo false vyjadrujuce uspesnost koliizie
     */
    public abstract boolean kontrolaKolizie(float pozX, float pozY);

}