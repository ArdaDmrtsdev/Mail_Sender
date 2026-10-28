
import java.util.ArrayList;

//ortak davranışlar burada tanımlandı
interface StokIslemleri
{
    void stokEkle(String urunAdi,int adet);

    boolean stoktanDus(String urunAdi,int adet);

    int stokSorgula(String urunAdi);
}

class UrunKaydi
{
    String urunAdi;
    int miktar;

    UrunKaydi(String urunAdi,int miktar)
    {
        this.urunAdi=urunAdi;
        this.miktar=miktar;
    }
}

class DepoStokYonetimi implements StokIslemleri
{
    private String depoAdi;
    private ArrayList<UrunKaydi> urunListesi=new ArrayList<>();

    public DepoStokYonetimi (String depoAdi)
    {
        this.depoAdi=depoAdi;
    }

    private UrunKaydi urunBul(String urunAdi)
    {
        for(UrunKaydi kayit: urunListesi)
        {
            if (kayit.urunAdi.equalsIgnoreCase(urunAdi)) {
                return kayit;
            }
        }
        return null;
    }

    @Override
    public void stokEkle(String urunAdi,int adet)
    {
        if (adet<=0)
            return;

        UrunKaydi kayit=urunBul(urunAdi);

        //bu ürün ilk kez eklenecekse
        if (kayit==null) {

            urunListesi.add(new UrunKaydi(urunAdi,adet));
        }
        //bu ürün daha önceden var ise
        else
            kayit.miktar+=adet;
    }

    @Override
    public boolean stoktanDus(String urunAdi,int adet)
    {
        if(adet<=0) return false;

        UrunKaydi kayit=urunBul(urunAdi);

        if (kayit==null || kayit.miktar<adet) {
            return false;//yetrli stok yok demek
        }
        kayit.miktar-=adet;
        return true;
    }

    @Override
    public int stokSorgula(String urunAdi)
    {
        UrunKaydi kayit=urunBul(urunAdi);
        if(kayit==null)
            return 0;

        return kayit.miktar;
    }

    public void stokDurumuYazdir()
    {
        if (urunListesi.isEmpty()) {
            System.out.println("Stokta hiç bir yok");
        }
        else
        {
            for(UrunKaydi kayit:urunListesi)
            {
                System.out.println(kayit.urunAdi+ ": "+kayit.miktar+" adet vardır");
            }
        }
    }

}

class MagazaStokYonetimi implements StokIslemleri{

    private String magazaAdi;
    private int kritikSeviye;
    private ArrayList<UrunKaydi>urunListesi=new ArrayList<>();

    public MagazaStokYonetimi(String magazaAdi,int kiritikSeviye)
    {
        this.magazaAdi=magazaAdi;
        this.kritikSeviye=kritikSeviye;
    }

    private UrunKaydi urunBul(String urunAdi)
    {
        for(UrunKaydi kayit: urunListesi)
        {
            if (kayit.urunAdi.equalsIgnoreCase(urunAdi)) {
                return kayit;
            }
        }
        return null;
    }

    @Override
    public void stokEkle(String urunAdi,int adet)
    {
        if (adet<=0) return;

        UrunKaydi kayit=urunBul(urunAdi);

        //bu ürün ilk kez eklenecekse
        if (kayit==null) {

            urunListesi.add(new UrunKaydi(urunAdi,adet));
        }
        //bu ürün daha önceden var ise
        else
            kayit.miktar+=adet;
    }

    @Override
    public boolean stoktanDus(String urunAdi,int adet)
    {
        if(adet<=0) return false;

        UrunKaydi kayit=urunBul(urunAdi);

        if (kayit==null || kayit.miktar<adet) {
            return false;//yetrli stok yok demek
        }
        kayit.miktar-=adet;

        if (kayit.miktar<=kritikSeviye) {

            System.out.println("UYARI!!!! "+magazaAdi
                    +" mağazasında "+urunAdi+ " stok kritik seviyeye düştü mevcut miktar: "
                    +kayit.miktar);
        }
        return true;
    }

    @Override
    public int stokSorgula(String urunAdi)
    {
        UrunKaydi kayit=urunBul(urunAdi);
        if(kayit==null)
            return 0;

        return kayit.miktar;
    }

    public void stokDurumuYazdir()
    {
        if (urunListesi.isEmpty()) {
            System.out.println("Mağazada hiç bir ürün yok");
        }
        else
        {
            for(UrunKaydi kayit:urunListesi)
            {
                System.out.println(kayit.urunAdi+ ": "+kayit.miktar+" adet vardır");
            }
        }
    }

}
public class Main {

    public static void satisYap(StokIslemleri stok, String urunAdi, int adet)
    {
        boolean basarilimi=stok.stoktanDus(urunAdi,adet);

        if (basarilimi) {

            int kalan=stok.stokSorgula(urunAdi);
            System.out.println(urunAdi+" ürününden "+adet
                    +" kadar satıldı. Kalan stok: "+kalan);
        }
        else
            System.out.println("Satış başarısız "+urunAdi+
                    " için yeterli stok bulunmamaktadır!");
    }

    public static void main(String[] args) {
        DepoStokYonetimi depo =new DepoStokYonetimi("Ana Depo");
        MagazaStokYonetimi magaza=new MagazaStokYonetimi("Merkez Mağası",5);

        depo.stokEkle("Laptop", 50);
        depo.stokEkle("Oyuncu Koltuğu",20);

        magaza.stokEkle("Laptop", 20);
        magaza.stokEkle("Oyuncu Koltuğu",5);

        depo.stokDurumuYazdir();
        magaza.stokDurumuYazdir();

        System.out.println("Mağaza Satışları");
        satisYap(magaza,"Laptop",2);
        satisYap(magaza,"Laptop",5);
        satisYap(magaza,"Oyuncu Koltuğu",3);

        System.out.println("Depoda  HAreketler");
        satisYap(depo,"Laptop",20);
        satisYap(depo,"Oyuncu Koltuğu",12);

        depo.stokDurumuYazdir();
        magaza.stokDurumuYazdir();

        StokIslemleri [] stokIslemleri={depo,magaza};
        System.out.println("Tüm sistemlerdeki Laptop  miktarının sorgulanması");
        for(StokIslemleri sistem: stokIslemleri)
        {
            int miktar=sistem.stokSorgula("Laptop");
            System.out.println("Laptop stok miktarı: "+miktar);
        }

    }
}