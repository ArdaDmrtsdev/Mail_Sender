import  java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


enum SaglayiciTuru
{
    KONSOL,
    SMTP_SIMULE
}
 enum EpostaOnceligi
 {
     DUSUK,
     NORMAL,
     YUKSEK
 }
 class Kisi
 {
     private String adSoyad;
     private String eposta;

     public Kisi(String new_adSoyad, String new_eposta)
     {
         this.adSoyad=new_adSoyad;
         this.eposta=new_eposta;
     }

     public String getAdSoyad()
     {
         return adSoyad;
     }

     public String getEposta() {
         return eposta;
     }

     @Override
     public String toString() {
         return this.getAdSoyad()+"<"+this.getEposta()+">";
     }
 }

 class EkDosya
 {
     private String dosyaAdi;
     private  String icerik;

    public  EkDosya(String dosyaAdi,String icerik)
    {
        this.dosyaAdi=dosyaAdi;
        this.icerik=icerik;
    }

     public String getDosyaAdi() {
         return dosyaAdi;
     }

     public String getIcerik() {
         return icerik;
     }

     public  String toString()
     {
         return " EkDosyanız : { " + dosyaAdi + " } ";
     }
 }
 class  EpostaMesaji               //4.3 UĞRAŞTIRICI KISIM DİKKAT ET!!
{
    private Kisi gonderen;                                               /*  kişi tıpınde gonderen referansı */
    private ArrayList<Kisi>alicilar=new ArrayList<>();
    private  String konu;
    private  String govde;
    private  EpostaOnceligi oncelik;                                     /* epostaoncelıgı tıpınde oncelık referansı enum*/
    private ArrayList<EkDosya>ekDosyalar=new ArrayList<>();

    public  void  setGonderen(Kisi gonderen)
    {
      this.gonderen=gonderen;
    }
    public void  setKonu(String konu)
    {
        this.konu=konu;
    }
    public void  setGovde(String govde)
    {
        this.govde=govde;
    }
    public  void setOncelik(EpostaOnceligi oncelik)
    {
        this.oncelik=oncelik;                                        /* enumları kullanıyoruz.*/
    }

    // -------- LİSTEYE EKLEME METOTLARI --------

    public void aliciEkle(Kisi alici) {
      alicilar.add(alici);
    }

    public void ekDosyaEkle(EkDosya ek) {
        ekDosyalar.add(ek);
    }
    // -------- GET METOTLARI --------

    public Kisi getGonderen() {
        return gonderen;
    }

    public ArrayList<Kisi> getAlicilar() {
        return alicilar;
    }

    public String getKonu() {
        return konu;
    }

    public String getGovde() {
        return govde;
    }

    public EpostaOnceligi getOncelik() {
        return oncelik;
    }

    public ArrayList<EkDosya> getEkDosyalar() {
        return ekDosyalar;
    }
}

///  5.0

interface IEpostaGonderici
{
    void gonder(EpostaMesaji mesaj);
}

abstract  class SoyutEpostaGonderici implements  IEpostaGonderici
{
    @Override
   public final void gonder(EpostaMesaji mesaj)
    {
        String id = KimlikUretici.yeniId();
        gonderimOncesi(mesaj, id);
        asilGonder(mesaj, id);
        gonderimSonrasi(mesaj, id);

    }

    protected void gonderimOncesi(EpostaMesaji mesaj,String id)
    {
       System.out.println("Gönderim öncesi hazırlık yapılıyor");
    }
    protected abstract void   asilGonder(EpostaMesaji mesaj,String id);

    protected  void  gonderimSonrasi(EpostaMesaji mesaj,String id)
    {
        System.out.println("Tamamlandı !! ");
    }
}
class KonsolEpostaGonderici extends  SoyutEpostaGonderici
{
    @Override
    protected  void  asilGonder(EpostaMesaji mesaj, String id)
    {
        System.out.println("ID: "+ id+ " "+ " Gonderen kısı : "+ mesaj.getGonderen() + " Alıcılar : " + mesaj.getAlicilar() + " Öncelikler : "+ mesaj.getOncelik() + " Konu,govde : " + mesaj.getKonu() +" "+ mesaj.getGovde() );

        if(mesaj.getEkDosyalar().size()!=0)
        {
            System.out.println("EK dosyalar : "+mesaj.getEkDosyalar());
        }

    }
}

class  SmtpSimuleEpostaGonderici extends  SoyutEpostaGonderici
{
    @Override
    protected void asilGonder(EpostaMesaji mesaj, String id)
    {
        System.out.println("MAİL KİMDEN: " + mesaj.getGonderen().getEposta());
        for (Kisi k : mesaj.getAlicilar()) {
            System.out.println("KİME GİDİYOR : " + k.getEposta());
        }
        System.out.println("VERİLERİMİZ :");
        System.out.println("KONU : " + mesaj.getKonu());
        System.out.println("ÖNCELİK : " + mesaj.getOncelik());
        System.out.println();
        System.out.println( "GOVDE :"+mesaj.getGovde());
    }
}

/* =======================
   FABRİKA SINIFI BURADA
   ======================= */
class EpostaGondericiFabrikasi {

    public static IEpostaGonderici olustur(SaglayiciTuru tur) {
        if (tur == SaglayiciTuru.SMTP_SIMULE) {
            return new SmtpSimuleEpostaGonderici();
        }
        return new KonsolEpostaGonderici();
    }
}

/* =======================
   9) KİMLİK ÜRETİCİ   SINIFI BURDA
   ======================= */

class KimlikUretici {

    public static String yeniId() {
        Random rnd = new Random();
        int sayi = rnd.nextInt(1001); // 0-1000
        return "MSG-" + sayi;
    }
}


public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("1 - Konsol Göndericisi");
        System.out.println("2 - SMTP Simülasyonu Göndericisi");
        System.out.print("Gönderim türünü seçiniz:");

        int secim = scanner.nextInt();

        SaglayiciTuru tur;
        if (secim == 2) {
            tur = SaglayiciTuru.SMTP_SIMULE;
        } else {
            tur = SaglayiciTuru.KONSOL;
        }

        IEpostaGonderici gonderici = EpostaGondericiFabrikasi.olustur(tur);

        Kisi hoca = new Kisi("Sena Hoca", "sena.hoca@mail.com");
        Kisi ogr1 = new Kisi("Irmak Öğrenci", "ırmak@mail.com");
        Kisi ogr2 = new Kisi("Arda Öğrenci", "arda@mail.com");


        EpostaMesaji mesaj1 = new EpostaMesaji();
        mesaj1.setGonderen(hoca);
        mesaj1.aliciEkle(ogr1);
        mesaj1.aliciEkle(ogr2);
        mesaj1.setKonu("Hafta 12 Ödevi");
        mesaj1.setGovde("Ödev detayları ektedir.Dikkatle inceleyiniz.");
        mesaj1.setOncelik(EpostaOnceligi.YUKSEK);
        mesaj1.ekDosyaEkle(new EkDosya("ödev.pdf", "PDF içerik"));

        EpostaMesaji mesaj2 = new EpostaMesaji();
        mesaj2.setGonderen(hoca);
        mesaj2.aliciEkle(ogr1);
        mesaj2.setKonu("Hatırlatma");
        mesaj2.setGovde("Son teslim yarın.");
        mesaj2.setOncelik(EpostaOnceligi.NORMAL);

        gonderici.gonder(mesaj1);
        gonderici.gonder(mesaj2);

    }
}