
public class szkeleton
{
    szkeleton()
    {

    }

    void tesztIndit()
    {
        System.out.println("A menupont elotti szím beirasaval valaszthatod ki a dolgokat");
        System.out.println("Mit szeretnel tesztelni?: ");
    }

    void jatekInditasTeszt()
    {
        Jatek tesztJatek = new Jatek();
    }

    void fonalLerakSimaTeszt()
    {
        Gombatest test = new Gombatest();
        Tekton hely = new Tekton();
        Tekton t2 = new Tekton();
        Gombasz karakter = new Gombasz();
    }
}