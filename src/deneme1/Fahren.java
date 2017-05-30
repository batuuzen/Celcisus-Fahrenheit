package deneme1;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
 
// Fahren - main() metodunu kapsar ve ayrıca termometreleri depolar
 
public class Fahren
{
    // DEĞİŞKENLER
    Termometre celcius, fahrenheit;
 
    // CONSTRUCTOR
    public Fahren()
    {
        // 2 adet termometre oluştur
        celcius = new Termometre( 0, 100, "Celcius" );
        fahrenheit = new Termometre( 32, 212, "Fahrenheit" );
       
        celcius.fahren = this;
        fahrenheit.fahren = this;
        
 
        // En başta termometrelerin sıcaklığını EN'si 0, KN'si 100 olan bir termometrede
        // 25 derece gösterecek şekilde ayarla ( bir başka deyişle 25 santigrat derece yap )
        celcius.sicaklikAyarla( 25, 0, 100 );
        fahrenheit.sicaklikAyarla( 25, 0, 100 );
       
    }
 
    // METODLAR
    // Tüm termometrelerin değerlerini, referans olarak verilen "t" termometresine bakarak güncelle
    public void guncelle( Termometre t )
    {
        celcius.sicaklikAyarla( t.derece(), t.eN(), t.kN() );
        fahrenheit.sicaklikAyarla( t.derece(), t.eN(), t.kN() );
        
 
        // Termometreleri yeni değerlerini gösterecek şekilde güncelle
        celcius.repaint();
        fahrenheit.repaint();
       
    }
 
    // Tüm termometreleri yeniden çizdir, yani ekranı güncelle
    public void guncelle()
    {
        celcius.sicaklikAyarla( celcius.derece(), 0, 100 );
        fahrenheit.sicaklikAyarla( celcius.derece(), 0, 100 );
       
 
        // Termometreleri güncelle
        celcius.repaint();
        fahrenheit.repaint();
        
    }
 
    public static void main( String[] args )
    {
        // DEĞİŞKENLER
        JFrame pencere;
 
        Fahren u;
 
        // PROGRAM KODU
        u = new Fahren();
 
        // Ekranda 100,100 koordinatlarında bir JFrame oluştur
        pencere = new JFrame( "Termometre" );
        pencere.setBounds( 100, 100, 650, 500 );
 
        // "X" işaretine tıklayınca JFrame'in kapanmasını sağla
        pencere.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
 
        // Pencereyi yatay düzlemde 5 parçaya ayır ( 1 satır, 5 sütuna ayır )
        pencere.setLayout( new GridLayout( 1, 5 ) );
 
        // JFrame'in içine 2 Termometre objesi ve bir de bazı değerleri değiştirmeye yarayan bir
        // Termometre Ayarlayıcısı ekle
        pencere.add( u.celcius );
        pencere.add( u.fahrenheit );
      
        // JFrame'in tepesine kişiselleştirilmiş bir menü bar ekle
        pencere.setJMenuBar( new MenuBar() );
 
        // JFrame'i görünür yap (aksi halde program çalıştırılınca hiçbir şey gerçekleşmez)
        pencere.setVisible( true );
    }
}