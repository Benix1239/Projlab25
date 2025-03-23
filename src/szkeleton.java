
import java.util.ArrayList;


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
        
        Tekton hely = new Tekton(null);
        Tekton t2 = new Tekton(null);
        hely.addSzomszed(t2);
        t2.addSzomszed(hely);
        ArrayList<Tekton> palya = new ArrayList<Tekton>();
        palya.add(t2);
        palya.add(hely);
        Gombasz karakter = new Gombasz(palya);
        Gombatest test = new Gombatest(hely,karakter);
        karakter.gombatestHozzaad(test);
        hely.setGombatest(test);

        //hianyzik a fv
    }

    //naon fasza, diagramm szerint fut
    void sporaSzorSimaTeszt()
    {
        Tekton t1 = new Tekton(null);
        Tekton t2 = new Tekton(null);
        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        ArrayList<Tekton> palya = new ArrayList<Tekton>();
        palya.add(t2);
        palya.add(t1);
        Gombasz jatekos = new Gombasz(palya);
        Gombatest test = new Gombatest(t1,jatekos);
        jatekos.gombatestHozzaad(test);
        t1.setGombatest(test);

        Lassito spora = new Lassito();
        spora.setTartozik(jatekos);
        test.setSpora(spora);
        
        jatekos.sporaSzor();
    }

    void sporaSzorFejlettTeszt()
    {
        Tekton t1 = new Tekton(null);
        Tekton t2 = new Tekton(null);
        Tekton t3 = new Tekton(null);
        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        t3.addSzomszed(t2);
        t2.addSzomszed(t3);
        ArrayList<Tekton> palya = new ArrayList<Tekton>();
        palya.add(t2);
        palya.add(t1);
        palya.add(t3);
        Gombasz jatekos = new Gombasz(palya);
        Gombatest test = new Gombatest(t1,jatekos);
        jatekos.gombatestHozzaad(test);
        t1.setGombatest(test);

        Lassito spora = new Lassito();
        spora.setTartozik(jatekos);
        test.setSpora(spora);
        test.setMaradt(2);
        jatekos.sporaSzor();
    }


    void GombatestEpitFonallalTeszt()
    {
        Tekton t1 = new Tekton(null);
        Tekton t2 = new Tekton(null);
        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        ArrayList<Tekton> palya = new ArrayList<Tekton>();
        palya.add(t2);
        palya.add(t1);
        Gombasz jatekos = new Gombasz(palya);
        Gombatest test = new Gombatest(t1,jatekos);
        jatekos.gombatestHozzaad(test);
        t1.setGombatest(test);

        Lassito s1 = new Lassito();
        s1.setTartozik(jatekos);
        t2.addSpora(s1);

        Lassito s2 = new Lassito();
        s2.setTartozik(jatekos);
        t2.addSpora(s2);

        Lassito s3 = new Lassito();
        s3.setTartozik(jatekos);
        t2.addSpora(s3);

        Lassito s4 = new Lassito();
        s4.setTartozik(jatekos);
        t2.addSpora(s4);

        Lassito s5 = new Lassito();
        s5.setTartozik(jatekos);
        test.setSpora(s5);
        
        jatekos.fonalLerak();
    }


    void GombatestEpitSporavalTeszt()
    {
        Tekton t1 = new Tekton(null);
        Tekton t2 = new Tekton(null);
        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        ArrayList<Tekton> palya = new ArrayList<Tekton>();
        palya.add(t2);
        palya.add(t1);
        Gombasz jatekos = new Gombasz(palya);
        Gombatest test = new Gombatest(t1,jatekos);
        jatekos.gombatestHozzaad(test);
        t1.setGombatest(test);

        Lassito s1 = new Lassito();
        s1.setTartozik(jatekos);
        t2.addSpora(s1);

        Lassito s2 = new Lassito();
        s2.setTartozik(jatekos);
        t2.addSpora(s2);

        Lassito s3 = new Lassito();
        s3.setTartozik(jatekos);
        t2.addSpora(s3);

        Lassito s4 = new Lassito();
        s4.setTartozik(jatekos);
        t2.addSpora(s4);

        Lassito s5 = new Lassito();
        s5.setTartozik(jatekos);
        test.setSpora(s5);
        
        Fonal fon1 = new Fonal(t2,jatekos);
        t1.addFonal(fon1);
        Fonal fon2 = new Fonal(t1,jatekos);
        t2.addFonal(fon2);

        jatekos.sporaSzor();
    }


    void gombaTestMeghalTeszt()
    {
        Tekton t1 = new Tekton(null);
        Tekton t2 = new Tekton(null);
        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        ArrayList<Tekton> palya = new ArrayList<Tekton>();
        palya.add(t2);
        palya.add(t1);
        Gombasz jatekos = new Gombasz(palya);
        Gombatest test = new Gombatest(t1,jatekos);
        jatekos.gombatestHozzaad(test);
        t1.setGombatest(test);

        Lassito spora = new Lassito();
        spora.setTartozik(jatekos);
        test.setSpora(spora);
        test.setMaradt(1);

        Fonal fon1 = new Fonal(t2,jatekos);
        t1.addFonal(fon1);
        Fonal fon2 = new Fonal(t1,jatekos);
        t2.addFonal(fon2);

        jatekos.sporaSzor();
    }


    void fonalElhalBogarMiattTeszt()
    {
        Tekton t1 = new Tekton(null);
        Tekton t2 = new Tekton(null);
        Tekton t3 = new Tekton(null);
        Tekton t4 = new Tekton(null);
        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        t3.addSzomszed(t2);
        t2.addSzomszed(t3);
        t3.addSzomszed(t4);
        t4.addSzomszed(t3);
        ArrayList<Tekton> palya = new ArrayList<Tekton>();
        palya.add(t2);
        palya.add(t1);
        palya.add(t3);
        palya.add(t4);
        Gombasz jatekos = new Gombasz(palya);
        Gombatest test = new Gombatest(t1,jatekos);
        jatekos.gombatestHozzaad(test);
        t1.setGombatest(test);

        Fonal fon10 = new Fonal(t2,jatekos);
        t1.addFonal(fon10);
        Fonal fon11 = new Fonal(t1,jatekos);
        t2.addFonal(fon11);

        Fonal fon20 = new Fonal(t2,jatekos);
        t3.addFonal(fon20);
        Fonal fon21 = new Fonal(t3,jatekos);
        t2.addFonal(fon21);

        Fonal fon30 = new Fonal(t4,jatekos);
        t3.addFonal(fon30);
        Fonal fon31 = new Fonal(t3,jatekos);
        t4.addFonal(fon31);

        Bogarasz jatekos1 = new Bogarasz();
        Bogar bogar = new Bogar();
        jatekos1.bogarHozzaad(bogar, t1);

        jatekos1.ragas();
    }


    void fonalElhalGombatestMiattTeszt()
    {
        Tekton t1 = new Tekton(null);
        Tekton t2 = new Tekton(null);
        Tekton t3 = new Tekton(null);
        Tekton t4 = new Tekton(null);
        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        t3.addSzomszed(t2);
        t2.addSzomszed(t3);
        t3.addSzomszed(t4);
        t4.addSzomszed(t3);
        ArrayList<Tekton> palya = new ArrayList<Tekton>();
        palya.add(t2);
        palya.add(t1);
        palya.add(t3);
        palya.add(t4);
        Gombasz jatekos = new Gombasz(palya);
        Gombatest test = new Gombatest(t1,jatekos);
        jatekos.gombatestHozzaad(test);
        t1.setGombatest(test);

        Fonal fon10 = new Fonal(t2,jatekos);
        t1.addFonal(fon10);
        Fonal fon11 = new Fonal(t1,jatekos);
        t2.addFonal(fon11);

        Fonal fon20 = new Fonal(t2,jatekos);
        t3.addFonal(fon20);
        Fonal fon21 = new Fonal(t3,jatekos);
        t2.addFonal(fon21);

        Fonal fon30 = new Fonal(t4,jatekos);
        t3.addFonal(fon30);
        Fonal fon31 = new Fonal(t3,jatekos);
        t4.addFonal(fon31);

        Lassito spora = new Lassito();
        spora.setTartozik(jatekos);
        test.setSpora(spora);
        test.setMaradt(1);

        jatekos.sporaSzor();

    }

    void fonalElhalSzetesesMiattTeszt()
    {
        Tekton t1 = new Tekton(null);
        Tekton t2 = new Tekton(null);
        Tekton t3 = new Tekton(null);
        Tekton t4 = new Tekton(null);
        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        t3.addSzomszed(t2);
        t2.addSzomszed(t3);
        t3.addSzomszed(t4);
        t4.addSzomszed(t3);

        ArrayList<Tekton> palya = new ArrayList<Tekton>();
        palya.add(t2);
        palya.add(t1);
        palya.add(t3);
        palya.add(t4);

        Gombasz jatekos = new Gombasz(palya);
        Gombatest test = new Gombatest(t1,jatekos);
        jatekos.gombatestHozzaad(test);
        t1.setGombatest(test);

        Fonal fon10 = new Fonal(t2,jatekos);
        t1.addFonal(fon10);
        Fonal fon11 = new Fonal(t1,jatekos);
        t2.addFonal(fon11);

        Fonal fon20 = new Fonal(t2,jatekos);
        t3.addFonal(fon20);
        Fonal fon21 = new Fonal(t3,jatekos);
        t2.addFonal(fon21);

        Fonal fon30 = new Fonal(t4,jatekos);
        t3.addFonal(fon30);
        Fonal fon31 = new Fonal(t3,jatekos);
        t4.addFonal(fon31);

       t2.tores();

    }

    void BogarLepTeszt()
    {
        Tekton t1 = new Tekton(null);
        Tekton t2 = new Tekton(null);
        ArrayList<Tekton> palya = new ArrayList<Tekton>();
        palya.add(t2);
        palya.add(t1);
        Gombasz jatekos = new Gombasz(palya);

        Fonal fon10 = new Fonal(t2,jatekos);
        t1.addFonal(fon10);
        Fonal fon11 = new Fonal(t1,jatekos);
        t2.addFonal(fon11);

        Bogarasz jatekos1 = new Bogarasz();
        Bogar bogar = new Bogar();
        jatekos1.bogarHozzaad(bogar, t1);

        jatekos1.lep();
    }

    void BogarRagTeszt()
    {
        Tekton t1 = new Tekton(null);
        Tekton t2 = new Tekton(null);
        ArrayList<Tekton> palya = new ArrayList<Tekton>();
        palya.add(t2);
        palya.add(t1);
        Gombasz jatekos = new Gombasz(palya);

        Fonal fon10 = new Fonal(t2,jatekos);
        t1.addFonal(fon10);
        Fonal fon11 = new Fonal(t1,jatekos);
        t2.addFonal(fon11);

        Bogarasz jatekos1 = new Bogarasz();
        Bogar bogar = new Bogar();
        jatekos1.bogarHozzaad(bogar, t1);

        jatekos1.ragas();
    }

    void BogarEszikTeszt()
    {
        Tekton t1 = new Tekton(null);

        Sima s = new Sima();

        Bogarasz jatekos1 = new Bogarasz();
        Bogar bogar = new Bogar();
        jatekos1.bogarHozzaad(bogar, t1);

        jatekos1.eves();
    }

    void BogarEmesztGyorsitoTeszt()
    {
        Tekton t1 = new Tekton(null);

        Spora s = new Gyorsito();

        Bogarasz jatekos1 = new Bogarasz();
        Bogar bogar = new Bogar();
        jatekos1.bogarHozzaad(bogar, t1);
        bogar.setElozo(s);

        bogar.sporaMegemesztes();
    }

    void BogarEmesztBenitoTeszt()
    {
        Tekton t1 = new Tekton(null);

        Spora s = new Benito();

        Bogarasz jatekos1 = new Bogarasz();
        Bogar bogar = new Bogar();
        jatekos1.bogarHozzaad(bogar, t1);
        bogar.setElozo(s);

        bogar.sporaMegemesztes();
    }

    void BogarEmesztkeseruTeszt()
    {
        Tekton t1 = new Tekton(null);

        Spora s = new Keseru();

        Bogarasz jatekos1 = new Bogarasz();
        Bogar bogar = new Bogar();
        jatekos1.bogarHozzaad(bogar, t1);
        bogar.setElozo(s);

        bogar.sporaMegemesztes();
    }

    void BogarEmesztlassitoTeszt()
    {
        Tekton t1 = new Tekton(null);

        Spora s = new Lassito();

        Bogarasz jatekos1 = new Bogarasz();
        Bogar bogar = new Bogar();
        jatekos1.bogarHozzaad(bogar, t1);
        bogar.setElozo(s);

        bogar.sporaMegemesztes();
    }
}