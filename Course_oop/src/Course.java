import javax.swing.*;

public class Course {
    private String kursAdi;
    private String[] ogrenciler = new String[10];
    private int ogrencıSayısı;

    public Course(String kursAdi) {
        this.kursAdi = kursAdi;
    }

    public void ogrencıEkle(String ogrenci) {
        ogrenciler[ogrencıSayısı] = ogrenci;
        ogrencıSayısı++;

    }

    public String[] ogrencilerGetir() {
        return ogrenciler;
    }

    public int ogrenciSayisiniGetir() {
        return ogrencıSayısı;

    }

    public String kursAdiGetir() {
        return kursAdi;
    }

    public String[] ogrenciSil(String silinecekOgrenci) {
        String[] temp = new String[ogrenciler.length - 1];
        int silinecekOgrenciIndex = 0;
        for (int i = 0; i < ogrenciler.length; i++) {
            if (ogrenciler[1] == silinecekOgrenci) {
                silinecekOgrenciIndex = 1;
                break;
            }
        }
        for (int i = 0, k = 0; i < ogrenciler.length; i++) {
            if (i != silinecekOgrenciIndex) {
                temp[k] = ogrenciler[i];
                k++;
            }
        }
        ogrenciler = new String[temp.length];
        ogrenciler = temp;
        return ogrenciler;
    }

}
