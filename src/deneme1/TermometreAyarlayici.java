package deneme1;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
 
// TermometreAyarlayici - Kişiselleştirilebilir termometrenin değerlerini değiştirmeye ve
// tüm termometrelerin gösterebileceği üst ve alt sıcaklık limitlerini belirlemeye yarayan class
 
public class TermometreAyarlayici extends JPanel implements ActionListener
{
    // DEĞİŞKENLER
    Termometre termometre;
    Fahren u;
 
    
 
    // CONSTRUCTOR
    
 
    // METODLAR
    // TextField'a veri girildiğinde ve Enter'a basıldığında çağırılır
    public void actionPerformed( ActionEvent e )
    {
       
        u.guncelle();
    }
}