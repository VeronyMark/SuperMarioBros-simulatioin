package sk.uniza.fri.hra.hra;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import sk.uniza.fri.hra.okna.VyhraOkno;
import sk.uniza.fri.hra.pouzivatelia.Hrac;
import sk.uniza.fri.hra.predmety.Bomba;
import sk.uniza.fri.hra.predmety.Hviezda;
import sk.uniza.fri.hra.predmety.IPredmet;
import sk.uniza.fri.hra.predmety.Srdce;
import sk.uniza.fri.hra.prostredie.Mapa;
import sk.uniza.fri.hra.prostredie.MapaLevel1;
import sk.uniza.fri.hra.prostredie.MapaLevel2;
import sk.uniza.fri.hra.prostredie.MapaLevel3;
import sk.uniza.fri.hra.prostredie.Smer;
import sk.uniza.fri.hra.prostredie.TypStvorca;
import javax.swing.JOptionPane;
import java.io.FileNotFoundException;
import java.util.Random;

/**
 * trieda hra je potomok triedy ApplicationAdapter
 * trieda zabezpecuje plynutie pozadia hry
 * zabezpecuje korektne zobrazenie jednotlivych objetkov
 * @author Veronika Markova
 * @version 1.1
 */

public class Hra extends ApplicationAdapter {
    private int stred;
    private int mierkaX;
    private int mierkaY;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Mapa mapaHry;
    private Hrac hrac;
    private boolean padPredmetu;
    private float pozXmapa;
    private float pozYmapa;
    private boolean jump;
    private boolean fall;
    private int pocetBodov;
    private String menoHraca;
    private BitmapFont menoHracaText;

    private String textScoreHraca;
    private BitmapFont scoreHracaText;

    private Hudba hudba;
    private Random generator;
    private IPredmet predmet;
    private Subor subor;


    /**
     * metoda create prepisuje metodu z predka
     * inicializuje potrebne atributy
     */

    @Override
    public void create() {
        try {
            this.subor = new Subor();
        } catch (FileNotFoundException e) {
            System.out.println("SUBOR SA NENASIEL");
        }

        this.camera = new OrthographicCamera();
        this.stred = Gdx.graphics.getWidth() / 2;
        this.mierkaX = Gdx.graphics.getWidth() / 4;
        this.mierkaY = Gdx.graphics.getHeight() / 4;

        this.camera.setToOrtho(false,  this.mierkaX,  this.mierkaY);
        this.camera.update();

        this.batch = new SpriteBatch();
        this.hrac = new Hrac(this.stred , 64);

        this.mapaHry = new MapaLevel1();

        this.padPredmetu = false;
        this.pozXmapa = 150;
        this.pozYmapa = this.hrac.getPoziciaY();
        this.predmet = new Hviezda(10, 10) ;

        this.jump = false;
        this.fall = false;
        this.pocetBodov = 0;
        this.scoreHracaText = new BitmapFont();
        this.menoHracaText = new BitmapFont();
        this.generator = new Random();
        this.hudba = new Hudba();
    }

    /**
     *vykonava render hry, pozadia, reakciu na stlacenie klaves
     *kontroluje poziciu hraca
     * kontroluje vyhru v hre
     */
    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        this.mapaHry.render(this.camera);
        this.batch.begin();

        this.textScoreHraca = "SCORE: " + this.hrac.getCelkoveSkore() + " \n      " + this.pocetBodov + " / " + this.mapaHry.getPocetBodov();
        this.menoHracaText.draw(this.batch, "NAME: " + this.menoHraca, 10, 750);
        this.menoHracaText.getData().setScale(2);
        this.hrac.pridaj(this.batch);
        this.hudba.pridaj(this.batch);

        this.scoreHracaText.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        this.scoreHracaText.draw(this.batch, this.textScoreHraca, 450, 750);
        this.scoreHracaText.getData().setScale(3);

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.vykonajPohybVpravo();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.vykonajPohybVlavo();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            this.hrac.setVyskaSkoku();
            this.jump = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            this.jump = false;
        }
        if (Gdx.input.justTouched()) {
            int pozX = Gdx.input.getX();
            int pozY = Gdx.input.getY();
            if (pozX >= this.hudba.getPozX() && pozX <= (this.hudba.getPozX() + this.hudba.getSirka())) {
                if (pozY <= (800 - this.hudba.getPozY() ) && pozY >= (800 - this.hudba.getPozY() - this.hudba.getVyska())) {
                    this.hudba.zmenaStavu();
                }
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.X)) {
            JOptionPane.showMessageDialog(null, "you canceled the game");
            this.ulozenieHraca();
            Gdx.app.exit();
        }

        if (this.padPredmetu) {
            this.predmet.pohyb(Gdx.graphics.getDeltaTime(), this.batch);
            if (this.predmet instanceof Bomba) {

                if (this.mapaHry.getStvorecPodlaSuradnice(1, this.predmet.getPoziciaX() , this.predmet.getPoziciaY() - 64) == TypStvorca.ZEM) {
                    this.predmet.pouzi(this.hrac, this.batch);
                    this.padPredmetu = false;
                }

            } else {
                float vzdialenost1 = Math.abs(this.hrac.getPoziciaX() - this.predmet.getPoziciaX());
                float vzdialenost2 = Math.abs(this.hrac.getPoziciaY() - this.predmet.getPoziciaY());
                if (vzdialenost2 <= 50 && vzdialenost1 <= 50) {
                    this.predmet.pouzi(this.hrac, this.batch);
                    this.padPredmetu = false;
                }
            }
        }
        if (this.jump) {
            this.skokHore();
        }
        if (this.fall) {
            this.padDole();
        }

        if (this.kontrolaVyhry()) {
            this.zmenLevel();
        }

        if (this.mapaHry instanceof MapaLevel3) {
            this.mapaHry.pohybZvierat(this.batch, this.pozXmapa);
            if (this.mapaHry.kontrolaKolizie(this.pozXmapa, this.pozYmapa)) {
                this.hrac.znizZivot();
                this.hrac.zobrazZivot(this.batch);
            }
        }
        this.kontrolaPozicie();
        this.camera.update();
        this.batch.end();
    }


    /**
     * privatna metoda skokHore
     * vykonava skok a kontroluje poziciu hraca
     */
    private void skokHore() {
        this.hrac.skok(Gdx.graphics.getDeltaTime(), this.batch, this.jump);
        TypStvorca typStvorca = this.mapaHry.getStvorecPodlaSuradnice(1, this.pozXmapa + (this.hrac.getSirkaObrazka()) / 4 , (this.hrac.getPoziciaY() + this.hrac.getVyskaObrazka()) / 4);

        if (this.hrac.dosiahnutieVrcholu() || typStvorca == TypStvorca.ZEM ) {
            this.fall = true;
            this.jump = false;
        } else if (typStvorca == TypStvorca.OTAZNIKY) {
            this.pridajPredmet();
            this.jump = false;
            this.fall = true;
        }
        this.pozYmapa += 4 ;
    }

    /**
     * privatna metoda vykonajPohybVpravo
     * vyjadruje reakciu na stlacenie klavesy vpravo
     *
     */
    private void vykonajPohybVpravo() {
        TypStvorca typStvorca = this.mapaHry.getStvorecPodlaSuradnice(1, this.pozXmapa + this.hrac.getSirkaObrazka() / 4, (this.hrac.getPoziciaY() + 64) / 4);
        if (typStvorca != TypStvorca.ZEM) {
            this.pozXmapa += 1;
            this.camera.translate(+1, 0);
            if (this.padPredmetu) {
                this.predmet.pohybSMapou( this.batch, Smer.VPRAVO);
            }
            if (this.mapaHry instanceof MapaLevel3) {
                this.mapaHry.pohybZvieratSMapou( this.batch, Smer.VPRAVO, this.pozXmapa);
            }
        }
        this.hrac.zmenaObrazkuHraca(this.batch, Smer.VPRAVO);
    }

    /**
     * privatna metoda vykonajPohybVpravo
     * vyjadruje reakciu na stlacenie klavesy vpravo
     *
     */
    private void vykonajPohybVlavo() {
        TypStvorca typStvorca = this.mapaHry.getStvorecPodlaSuradnice(1, this.pozXmapa, (this.hrac.getPoziciaY() + 64) / 4);
        if (typStvorca != TypStvorca.ZEM && this.pozXmapa > 150) {
            this.camera.translate(-1, 0);
            this.pozXmapa -= 1;
            if (this.padPredmetu) {
                this.predmet.pohybSMapou( this.batch, Smer.VLAVO);
            }
            if (this.mapaHry instanceof MapaLevel3) {
                this.mapaHry.pohybZvieratSMapou( this.batch, Smer.VLAVO, this.pozXmapa);
            }
        }
        this.hrac.zmenaObrazkuHraca(this.batch, Smer.VLAVO);
    }


    /**
     * privatna metoda pridava predmet
     * predmet sa nahodne vygeneruje
     */
    private void pridajPredmet() {
        int nahodneCislo = this.generator.nextInt(100);
        this.padPredmetu = true;

        float pozY =  (this.hrac.getPoziciaY() + this.hrac.getVyskaObrazka() + 70);

        if ( nahodneCislo <= 25) {
            this.predmet = new Hviezda(this.stred, pozY);
        } else if (nahodneCislo > 50) {
            this.predmet = new Bomba(this.stred, pozY);
        } else if (nahodneCislo > 25 && nahodneCislo <= 50) {
            this.predmet = new Srdce(this.stred, pozY);
        }
    }

    /**
     * privatna metoda pad dole
     * kontroluje poziciu hraca
     */
    private void padDole() {
        this.hrac.zmenVrchol();
        this.hrac.pad(Gdx.graphics.getDeltaTime(), this.batch, this.fall);
        TypStvorca typStvorca = this.mapaHry.getStvorecPodlaSuradnice(1, this.pozXmapa, this.hrac.getPoziciaY() / 4);

        if (typStvorca == TypStvorca.ZEM || typStvorca == TypStvorca.OTAZNIKY) {
            this.fall = false;
        }
        if (this.pozYmapa == 0) {
            this.fall = false;
            this.hrac.zomri();

        }
        this.pozYmapa -= 4;
    }

    /**
     * metoda typu void kontroluje poziciu kde sa hrac nachadza
     *
     */
    private void kontrolaPozicie() {
        TypStvorca typStvorca = this.mapaHry.getStvorecPodlaSuradnice(1, this.pozXmapa, (this.hrac.getPoziciaY() + 4) / 4);

        if (typStvorca != TypStvorca.ZEM && typStvorca != TypStvorca.OTAZNIKY && !this.jump && !this.fall) {
            this.fall = true;
        }
        TypStvorca typStvorca1 = this.mapaHry.getStvorecPodlaSuradnice(1, this.pozXmapa, (this.hrac.getPoziciaY() + this.hrac.getVyskaObrazka() / 2) / 4);
        if (typStvorca1 == TypStvorca.BODY) {
            this.mapaHry.nahrad((int)this.pozXmapa, ( int)((this.hrac.getPoziciaY() + this.hrac.getVyskaObrazka() / 2) / 4));
            this.pocetBodov++ ;
            this.hrac.pridajSkore(1);
        }
    }

    /**
     * metoda kontroluje vyhru hraca v aktualnom leveli
     * @return typu boolean vracia stav hry
     */
    private boolean kontrolaVyhry() {
        TypStvorca typStvorca = this.mapaHry.getStvorecPodlaSuradnice(1, this.pozXmapa, (this.hrac.getPoziciaY() + 4) / 4);
        if (typStvorca == TypStvorca.CIEL ) {
            if (this.mapaHry.kontrolaPrejdeniaLevela(this.pocetBodov))  {
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * metoda zmenLevel meni level hraca vzhladom na aktualny level
     * da na vyber ci chce hrac pokracovat alebo ukoncit hru
     */
    private void zmenLevel() {
        int aktualnyLevel = this.mapaHry.getCisloLevelu();
        if (aktualnyLevel == 3 ) {
            this.ulozenieHraca();
            Gdx.app.exit();
            VyhraOkno ukoncovac = new VyhraOkno();
        } else {
            int zmenaLevela = JOptionPane.showConfirmDialog(null, "GRATULUJEM! \n Chceš pokračovať v hre ?", "zmena levelu", JOptionPane.YES_NO_OPTION);
            if (zmenaLevela == 0) {
                switch (aktualnyLevel) {
                    case 1:
                        this.mapaHry = new MapaLevel2();
                        break;
                    case 2:
                        this.mapaHry = new MapaLevel3();
                        break;
                }
                this.pozXmapa = 150;
                this.pocetBodov = 0;
                this.camera = new OrthographicCamera();
                this.camera.setToOrtho(false, this.mierkaX, this.mierkaY);
                this.camera.update();
            } else {
                this.ulozenieHraca();
                Gdx.app.exit();
            }
        }
    }

    /**
     *nastavi meno hraca
     * @param meno typu String
     */
    public void setMenoHraca(String meno) {
        this.menoHraca = meno;
    }

    /**
     * vracia meno Hraca
     * @return menoHraca typu String
     */
    public String getMenoHraca () {
        return this.menoHraca;
    }

    /**
     *privatna metoda ulozenieHraca uklada hraca a hru do suboru
     */
    private void ulozenieHraca() {
        try {
            this.subor.ulozenieHry(this.hrac, this);
        } catch (FileNotFoundException e) {
            System.out.println("SUBOR SA NENASIEL");
        }
    }
}