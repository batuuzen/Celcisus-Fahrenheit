package deneme1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingConstants;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


 
// MenuBar - Pencerenin yukarısındaki menü barı için class
 
public class MenuBar extends JMenuBar
{
	
     
    public MenuBar()
    {
    	
        JMenu dosya;
        JMenu bilgi;
 
        // Bunlar menü barında yer alan ana butonlar
        dosya = new JMenu( "Dosya" );
        bilgi = new JMenu( "Bilgi" );
 
        // Bunlar ise ana butonlara tıklayınca çıkacak olan, aksi halde gözükmeyen butonlar
        // "ara buton" diyelim bunlara
        JMenuItem cikis = new JMenuItem( new AbstractAction( "Çıkış" ) {
            public void actionPerformed(ActionEvent e) {
                System.exit( 0 );
            } } );
 
        JMenuItem hakkinda = new JMenuItem( new AbstractAction( "Hakkında" ) {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog( null, "Termometre" + (char)(169) + "Nazlı Özdemir", "Hakkında", JOptionPane.INFORMATION_MESSAGE );
            } } );
 
        // "ara buton"ları ilgili ana butonlara ekliyorum
        dosya.add( cikis );
        bilgi.add( hakkinda );
        
        // ana butonları menü barın kendisine ekliyorum
        // NOT: Dilersem menü bara direkt ara buton da ekleyebilirim ve çalışır da. 
        add( dosya );
        add( bilgi );
    }
}
