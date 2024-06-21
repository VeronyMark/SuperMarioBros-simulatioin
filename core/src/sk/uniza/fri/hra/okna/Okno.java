package sk.uniza.fri.hra.okna;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *Trieda Okno
 * Obsahuje tlacidlo na ukoncenie hry a informacny text
 * @author  Veronika Markova
 * @version 1.1
 */

public class Okno {

    private JFrame okno;
    private JLabel myLabel;
    private JLabel text;
    private JButton ukonciHruButton;

    /**
     * spusta privatnu metodu setUP
     * spusta privatnu metodu spustiOkno
     * @param pozadieObrazok typu String nazov obrazku, ktory sa zobrazi na okne
     * @param text typu String, text, ktory sa zobrazi
     */
    public Okno(String pozadieObrazok, String text) {
        this.setUp(pozadieObrazok, text);
        this.spustiOkno();
    }

    /**
     * privatna metoda setUp() typu void
     * Vytvorenie a nastavenie  okna
     * Vytvorenie a nastavenie JButton sluziaceho na ukoncenie hry
     * Vytvorenie a nastavenie textu, ktr je znazorneni na okne
     *
     */
    private void setUp(String pozadieObrazok, String text) {

        ImageIcon pozadie = new ImageIcon(pozadieObrazok);
        ImageIcon ikona = new ImageIcon(("assets\\srdce.png"));
        Image ikonaObrazok = ikona.getImage();

        this.okno = new JFrame("HRA");
        this.okno.setLayout(new BorderLayout());
        this.okno.setIconImage(ikonaObrazok);

        this.myLabel = new JLabel(pozadie);

        this.ukonciHruButton = new JButton();
        this.ukonciHruButton.setText("EXIT");
        this.ukonciHruButton.setFont(new Font("Helvetica", Font.ITALIC, 16));
        this.ukonciHruButton.setBackground(Color.lightGray);
        this.ukonciHruButton.setBounds(400, 400, 150, 50);


        if (text != null) {
            this.text = new JLabel(text);
            this.text.setFont(new Font("Helvetica", Font.ITALIC, 60));
            this.text.setSize(600, 600);
            this.text.setLocation(200, 50);
            this.text.setForeground(Color.WHITE);
            this.text.setVisible(true);
            this.myLabel.add(this.text);
        }

        this.myLabel.add(this.ukonciHruButton);
        this.okno.add(this.myLabel);

        this.okno.setSize(1000, 700);
        this.okno.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.okno.setVisible(true);
    }

    /**
     * privatna metoda typu void
     * obsahuje listener, a tak sa vykona pri kliknuti potrebny ukon a to je ukoncenie
     */
    private void spustiOkno() {
        this.ukonciHruButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Okno.this.okno.dispose();
                System.exit(0);
            }
        });
    }

}
