package deneme1;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
 
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.Color;
import java.awt.Dimension;
 
// Termometre - Bir termometre'nin bilgisayara uyarlanmış hali
 
public class Termometre extends JPanel
{
    // DEĞİŞKENLER
    public Fahren fahren;
 
    public static int ustSinir = 200; // Termometrenin göstereceği üst sıcaklık limiti
    public static int altSinir = -200; // Termometrenin göstereceği alt sıcaklık limiti
 
    private int erimeNoktasi, kaynamaNoktasi;
    private int derece;
 
    private String isim; // Termometrenin adı ("Celcius", "Kelvin" vb.)
 
    private JLabel ustYazi, altYazi; // GUI ile alakalı, termometre ile direkt alakalı olmayan yazılar
    private DereceBari termometre; // Termometrenin görsel gösterimini sağlayacak olan panel
 
    private Termometre t; // Termometrenin kendisini depolayan bir değişken (inner-class kullanıyor)
                          // çok verimli bir çözüm değil ama malesef böyle gerekti...
 
    public Termometre( int erimeNoktasi, int kaynamaNoktasi, String isim )
    {
        t = this;
 
        this.erimeNoktasi = erimeNoktasi;
        this.kaynamaNoktasi = kaynamaNoktasi;
        this.isim = isim;
 
        setLayout( new BorderLayout() );
 
        ustYazi = new JLabel( isim );
        altYazi = new JLabel();
        termometre = new DereceBari();
 
        // Yazıları ortala
        ustYazi.setHorizontalAlignment( SwingConstants.CENTER );
        altYazi.setHorizontalAlignment( SwingConstants.CENTER );
 
        // Yazıların arkaplanını turuncu yap
        ustYazi.setBackground( Color.ORANGE );
        altYazi.setBackground( Color.ORANGE );
 
        // Görsel termometrenin olduğu panelin arkasını sarı yap
        termometre.setBackground( Color.YELLOW );
 
        // Yazıların turuncu arkaplanlarının gözükmesi için onları opak yap
        ustYazi.setOpaque( true );
        altYazi.setOpaque( true );
 
        // JLabel component'lerinin ebatlarını ayarla
        ustYazi.setPreferredSize( new Dimension( getWidth(), 25 ) );
        altYazi.setPreferredSize( new Dimension( getWidth(), 40 ) );
 
        // Termometrenin mouse ile tıklanarak değerinin değişmesi için termometreye MouseListener ekle
        termometre.addMouseListener( termometre );
        termometre.addMouseMotionListener( termometre );
 
        // Yazıları ve görsel termometreyi ana panele ekle
        add( ustYazi, BorderLayout.NORTH );
        add( termometre, BorderLayout.CENTER );
        add( altYazi, BorderLayout.SOUTH );
    }
 
    // erimeNoktasi'nın değerini döndürür
    public int eN()
    {
        return erimeNoktasi;
    }
 
    // kaynamaNoktasi'nın değerini döndürür
    public int kN()
    {
        return kaynamaNoktasi;
    }
 
    // derece'nin değerini döndürür
    public int derece()
    {
        return derece;
    }
 
    // erimeNoktasi'nın değerini değiştirir
    public void eNBelirle( int derece )
    {
        erimeNoktasi = derece;
    }
 
    // kaynamaNoktasi'nın değerini değiştirir
    public void kNBelirle( int derece )
    {
        kaynamaNoktasi = derece;
    }
 
    // derece'nin değerini değiştirir
    public void dereceBelirle( int derece )
    {
        this.derece = derece;
    }
 
    // Termometrenin formülünün ve mevcut sıcaklığının olduğu JLabel'ı güncelle
    // ( boolean ) ? ( true statement ) : ( false statement ) kullanımına dikkat!
    // Bu ifade alttaki comment içindeki ifadenin kısaltılmış pratik halidir:
 
    /*
     if( boolean )
        true statement;
     else
        false statement;
    */
 
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        // JLabel'da \n komutu çalışmadığı için bu JLabel'ı 2 satıra ayırabilmek JLabel'ın
        // içinde biraz HTML kodu kullandım. (En pratik yolu buydu)
        altYazi.setText( "<html><body><center>" + derece + " " + (char)( 176 ) + "" + isim.charAt( 0 ) + " derece" + "<br>" + " ( " + isim.charAt( 0 ) + " " + ( ( eN() != 0 ) ? ( ( eN() > 0 ) ? ( "- " + eN() + " " ) : ( "+ " + ( -eN() ) + " " ) ) : "" ) + ") / " + ( kN() - eN() ) + "</center></body></html>" );
    }
 
    // Girilen erimeNoktasi ve kaynamaNoktasi'na sahip olan "derece" sıcaklığındaki bir
    // termometrenin sıcaklığını baz alarak bu termometrenin sıcaklığını ona uygun şekilde değiştirir
    public void sicaklikAyarla( int derece, int eN, int kN )
    {
        this.derece = (int)( ( derece - eN ) * 1.0 / ( kN - eN ) * ( this.kN() - this.eN() ) + this.eN() );
    }
 
    // Termometrenin görselleştirilmiş halini çizmeye yarayan bir inner-class
    class DereceBari extends JPanel implements MouseListener, MouseMotionListener
    {
        // Ekrana termometreyi, onun solunda da referans sıcaklık çizgilerini çizdirir
        public void paintComponent( Graphics g )
        {
            super.paintComponent( g ); // Bu ifade paintComponent() metodunda olmak zorundadır
 
            Graphics2D g2D = (Graphics2D) g; // referans çizgileri daha düzgün çizmek için double
                                             // değerlerle çalışmak adına Graphics2D'ye geçiş yap
 
            // Önce sol kısımda referans çizgileri göster
            // REFERANS ÇİZGİLERİ ÇİZİM BAŞLANGIÇ
 
            g.drawLine( 0, 0, 0, getHeight() );
 
            int sayac = 0;
 
            for( float i = 0; i <= getHeight(); i += getHeight() * 1.0 / 100 )
            {
                if( sayac % 5 != 0 )
                {
                    // Kısa çizgileri çizmeye yarar
                    g2D.draw( new Line2D.Double( 0, getHeight() - i, getWidth() * 0.07, getHeight() - i ) );
                }
                else
                {
                    // Uzun çizgileri çizmeye ve yanlarına değerlerini yazmaya yarar
                    // Birkaç parçaya ayırdım çünkü referans çizgilerin düzgün gözükmesi için
                    // öyle gerekti...
                    if( sayac == 0 )
                    {
                        // En alttaki uzun çizgideysek
                        g2D.draw( new Line2D.Double( 0, getHeight() - 1, getWidth() * 0.15, getHeight() - 1 ) );
                        g2D.drawString( "" + Termometre.altSinir, ( int )( getWidth() * 0.18 ), getHeight() - 2 );
                    }
                    else if( sayac == 100 )
                    {
                        // En üstteki uzun çizgideysek
                        g2D.draw( new Line2D.Double( 0, 0, getWidth() * 0.15, 0 ) );
                        g2D.drawString( "" + Termometre.ustSinir, ( int )( getWidth() * 0.18 ), 10 );
                    }
                    else
                    {
                        // Aralardaki bir uzun çizgideysek
                        g2D.draw( new Line2D.Double( 0, getHeight() - i, getWidth() * 0.15, getHeight() - i ) );
                        g2D.drawString( "" + ( Termometre.altSinir + (int)( ( Termometre.ustSinir - Termometre.altSinir ) * sayac * 1.0 / 100 ) ), ( int )( getWidth() * 0.18 ), getHeight() - i + 5 );
                    }
                }
 
                sayac++;
            }
 
            // Aşağıdaki if ifadesi ender rastladığım bir bug'u çözmek için orada.
            // double değerlerle uğraşırken bazen >= ifadesi çok ufak küsüratlarla yanlış sonuç
            // döndürebiliyor ve bunun neticesinde üstteki for ifadesi i = getHeight()'ta değil de ondan
            // hafif önce bitiyor ve en üstteki referans çizgisi gözükmüyordu. Bu if() ifadesi ile onu
            // düzelttim. İsterseniz commentleyip sorunu görebilirsiniz...
            if( sayac == 100 )
            {
                g2D.draw( new Line2D.Double( 0, 0, getWidth() * 0.15, 0 ) );
                g2D.drawString( "" + Termometre.ustSinir, ( int )( getWidth() * 0.18 ), 10 );
            }
 
            // REFERANS ÇİZGİLERİ ÇİZİM BİTİŞ
 
            // Referans çizgilerin sağına derece barını çiz (termometrenin grafiksel halini çiz)
            Color c = g.getColor();
 
            // Derecenin üst veya alt sıcaklık sınırını aşması durumunda sorun yaşanmaması için tedbir
            int dereceTemp = derece();
 
            if( dereceTemp < Termometre.altSinir )
                dereceTemp = Termometre.altSinir;
            if( dereceTemp > Termometre.ustSinir )
                dereceTemp = Termometre.ustSinir;
 
            // Termometrenin arkaplanını kırmızı renkte boya
            g.setColor( Color.RED );
            g.fillRect( (int)( getWidth() * 0.55 ), 0, (int)( getWidth() * 0.25 ), getHeight() );
 
            // Termometrenin değerini gösteren sıvıyı yeşil renkte boya
            g.setColor( Color.GREEN );
            g.fillRect( (int)( getWidth() * 0.55 ), getHeight() - (int)( ( dereceTemp - Termometre.altSinir ) * 1.0 / ( Termometre.ustSinir - Termometre.altSinir ) * getHeight() ), (int)( getWidth() * 0.25 ), getHeight() );
 
            // Termometrenin dış hatlarını siyah renkle vurgula
            g.setColor( Color.BLACK );
            g.drawRect( (int)( getWidth() * 0.55 ), 0, (int)( getWidth() * 0.25 ), getHeight() - 1 );
 
            g.setColor( c );
        }
 
        public void mousePressed( MouseEvent e )
        {
            // Termometreye mouse ile tıklandığında tüm termometrelerin sıcaklık değerlerini güncelle
            double oran = 1 - e.getY() * 1.0 / getHeight();
            dereceBelirle( (int)( Math.round( oran * ( ustSinir - altSinir ) + altSinir ) ) );
            fahren.guncelle( t );
        }
 
        public void mouseDragged( MouseEvent e )
        {
            // Termometrede mouse tıklı halde sürüklendiğinde de tüm termometrelin
            // sıcaklık değerlerini güncelle
            // NOT: mouseDragged() metodunda nispeten yoğun bir işlem yapıldığından güçsüz
            // bilgisayarlarda bu metod kasabilir, içeriği commentlendiğinde sorun düzelir...
            double oran = 1 - e.getY() * 1.0 / getHeight();
            dereceBelirle( (int)( Math.round( oran * ( ustSinir - altSinir ) + altSinir ) ) );
            fahren.guncelle( t );
        }
 
        public void mouseClicked( MouseEvent e ) {}
        public void mouseEntered( MouseEvent e ) {}
        public void mouseExited( MouseEvent e ) {}
        public void mouseReleased( MouseEvent e ) {}
        public void mouseMoved( MouseEvent e ) {}
    }
}