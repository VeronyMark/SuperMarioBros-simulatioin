package sk.uniza.fri.hra;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import sk.uniza.fri.hra.hra.Hra;
import sk.uniza.fri.hra.hra.Subor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;


public class Spustac extends JDialog {

    private JFrame okno;
    private JButton zapniHruButton;
    private JButton ukonciHruButton;
    private JButton najlepsiHracButton;
    private JButton odosliButton;
    private JLabel infoOMene;
    private JLabel infoNajlepsiHrac;
    private JLabel myLabel;
    private JTextArea menoTextField;

    public Spustac() {
        this.setUp();
    }

    private void setUp() {
        this.okno = new JFrame("HRA");
        this.okno.setLayout(new BorderLayout());

        ImageIcon pozadie = new ImageIcon(("assets\\marioPozadie.jpg"));
        ImageIcon ikona = new ImageIcon(("assets\\srdce.png"));

        Image ikonaObrazok = ikona.getImage();

        //ikonka
        this.okno.setIconImage(ikonaObrazok);

        //obrazok na okne
        this.myLabel = new JLabel(pozadie);

        this.zapniHruButton = new JButton();
        this.ukonciHruButton = new JButton();
        this.najlepsiHracButton = new JButton();
        this.najlepsiHracButton.setText("najvyššie skore");
        this.najlepsiHracButton.setFont(new Font("Helvetica", Font.ITALIC, 16));
        this.najlepsiHracButton.setSize(200, 50);
        this.najlepsiHracButton.setLocation(400, 500);
        this.najlepsiHracButton.setBackground(Color.lightGray);


        this.zapniHruButton.setText("HRAJ");
        this.zapniHruButton.setFont(new Font("Helvetica", Font.ITALIC, 16));
        this.zapniHruButton.setBackground(Color.lightGray);

        this.ukonciHruButton.setText("UKONCI");
        this.ukonciHruButton.setFont(new Font("Helvetica", Font.ITALIC, 16));
        this.ukonciHruButton.setBackground(Color.lightGray);

        this.zapniHruButton.setBounds(300, 400, 150, 50);
        this.ukonciHruButton.setBounds(550, 400, 150, 50);

        this.menoTextField = new JTextArea();
        this.menoTextField.setSize(100, 25);
        this.menoTextField.setLocation(200, 300);
        this.menoTextField.setVisible(false);

        this.odosliButton = new JButton();
        this.odosliButton.setText("pošli");
        this.odosliButton.setFont(new Font("Helvetica", Font.ITALIC, 16));
        this.odosliButton.setSize(150, 50);
        this.odosliButton.setLocation(300, 400);
        this.odosliButton.setBackground(Color.lightGray);
        this.odosliButton.setVisible(false);

        this.infoOMene = new JLabel("MENO");
        this.infoOMene.setFont(new Font("Helvetica", Font.ITALIC, 30));
        this.infoOMene.setSize(500, 500);
        this.infoOMene.setLocation(100, 0);
        this.infoOMene.setVisible(false);

        this.infoNajlepsiHrac = new JLabel("");
        this.infoNajlepsiHrac.setFont(new Font("Helvetica", Font.CENTER_BASELINE, 30));
        this.infoNajlepsiHrac.setSize(500, 500);
        this.infoNajlepsiHrac.setLocation(450, 330);
        this.infoNajlepsiHrac.setVisible(false);

        this.myLabel.add(this.infoOMene);
        this.myLabel.add(this.infoNajlepsiHrac);
        this.myLabel.add(this.zapniHruButton);
        this.myLabel.add(this.ukonciHruButton);
        this.myLabel.add(this.menoTextField);
        this.myLabel.add(this.odosliButton);
        this.myLabel.add(this.najlepsiHracButton);

        this.okno.add(this.myLabel);

        this.okno.setSize(1000, 700);
        this.okno.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.okno.setVisible(true);
    }

    public void spustiOkno() {
        this.zapniHruButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Spustac.this.zapniHruButton.setVisible(false);
                Spustac.this.ukonciHruButton.setVisible(false);
                Spustac.this.najlepsiHracButton.setVisible(false);
                Spustac.this.infoNajlepsiHrac.setVisible(false);

                Spustac.this.infoOMene.setVisible(true);
                Spustac.this.menoTextField.setVisible(true);
                Spustac.this.odosliButton.setVisible(true);

                Spustac.this.odosliButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {


                        String meno = Spustac.this.menoTextField.getText();

                        Spustac.this.okno.setVisible(false);
                        Spustac.this.okno.dispose();

                        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
                        config.setForegroundFPS(60);
                        config.setWindowIcon("srdce.png");
                        config.setTitle("HRA");
                        config.setResizable(false);
                        config.setWindowedMode(1200, 800);
                        Hra hra = new Hra();
                        hra.setMenoHraca(meno);
                        new Lwjgl3Application(hra, config);
                    }
                });
            }
        });


        this.ukonciHruButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Spustac.this.okno.dispose();
                System.exit(0);
            }
        });


        this.najlepsiHracButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Subor subor = null;
                try {
                    subor = new Subor();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                subor.dajHodnotenie();
                Spustac.this.infoNajlepsiHrac.setText(subor.dajHodnotenie());
                Spustac.this.infoNajlepsiHrac.setVisible(true);

            }

        });

    }
}

