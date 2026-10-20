abstract class Robot {
    int enerji = 100;
    static void tip() {
        System.out.println("Robot ");
    }
    void selam() { System.out.println("Sinyal ");
    }
    abstract void gorev();
}

class TemizlikRobotu extends Robot {

    int enerji = 50;

    static void tip() {
        System.out.println("Süpürge ");
    }
    void selam() {
        super.selam();
        System.out.print("Bip ");
    }
    void gorev() { System.out.println("Temizlik "); }
}

class vars{

    int X =100;
}
class sub extends vars{

    int X =120;
}
public class Main {
    public static void main(String[] args) {

        // Main:
        Robot r = new TemizlikRobotu();
        System.out.println(r.enerji + " ");
        r.tip();
        r.selam();
        r.gorev();


        vars ar = new sub();

    System.out.println(ar.X);
    }
}