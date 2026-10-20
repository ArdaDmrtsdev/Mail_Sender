
public class Main {
    public static void main(String[] args) {
Course c1 = new Course("Nesneye dayalı programlama");
Course c2=new Course("İleri Phyton Programlama");
c1.ogrencıEkle("Ali al");
c1.ogrencıEkle("Veli gel");
c1.ogrencıEkle("Ayşe Solmaz");

c2.ogrencıEkle("Aylın getır");
c2.ogrencıEkle("Pınar Taşır");
c2.ogrencıEkle("Gökhan okur");

System.out.println("c1 nesnesi ogrencı sayısı : "+c1.ogrenciSayisiniGetir());
String[]tümOgrenciler=c1.ogrencilerGetir();
for(int i=0; i<tümOgrenciler.length; i++)
{
    System.out.println(tümOgrenciler[i]+",");
}

System.out.println();
System.out.println("c2 nesnesi ögrenci sayısı : "+c2.ogrenciSayisiniGetir());
String[]yeniOgrenciler=c2.ogrenciSil("Gökhan okur");
for(int i = 0; i< yeniOgrenciler.length;i++)
{
    System.out.println("c2 nesnesi "+yeniOgrenciler[i]);

}

    }
}