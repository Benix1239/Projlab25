
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Stack;


public class szkeleton
{
    private static ArrayList<String> gyujtemeny = new ArrayList<>();
    private static Map<Object, String> objectToStringMap = new IdentityHashMap<>();
    private static int objectCounter = 1;

    private static final ThreadLocal<Object> jelenlegiHivo = new ThreadLocal<>();

    private static Stack<String> objektumNevek;

    public static void addToMap(Object o, String name){
        if(o == null)
            return;
        objectToStringMap.putIfAbsent(o, name);
    }

    private static String indentalasSzamitas() {
        int melyseg = Thread.currentThread().getStackTrace().length;
        return "    ".repeat(Math.max(0, melyseg - 4));
    }

    public static void logMethodEntry(Object hivott, String methodName) {
        Object hivo = jelenlegiHivo.get();
        objektumNevek.push(objectToStringMap.get(hivo));
        jelenlegiHivo.set(hivott);
        //System.out.println(indentalasSzamitas() + objectToStringMap.get(hivo) + " -> " + objectToStringMap.get(hivott) + ": " + methodName + "()");
        String uzenet = indentalasSzamitas() + objectToStringMap.get(hivo) + " -> " + objectToStringMap.get(hivott) + ": " + methodName + "()";
        gyujtemeny.add(uzenet);
    }

    public static void logMethodExit(Object hivott, Object returnValue) {
        String hivo = objektumNevek.getLast();
        //System.out.println(indentalasSzamitas() + hivo + " <- " + objectToStringMap.get(hivott) + " : " + returnValue);
        String uzenet = indentalasSzamitas() + hivo + " <- " + objectToStringMap.get(hivott) + " : " + returnValue;
        gyujtemeny.add(uzenet);
        objektumNevek.pop();
    }

    private void gyujtemenyKiiratas(){
        for(String s : gyujtemeny){
            System.out.println(s);
        }
        gyujtemeny = new ArrayList<>();
    }

    szkeleton()
    {
        objektumNevek = new Stack<>();
    }

    void tesztIndit()
    {
        System.out.println("A menupont elotti szím beirasaval valaszthatod ki a dolgokat");
        System.out.println("Mit szeretnel tesztelni?:");
        System.out.println("1:Palya logika\n2:Gombasz logika\n3:Bogarasz logika"); 
        //beolvasas amit lusta vok megcsinalni hajnali 3-kor
        int valaszt = 0;

        switch (valaszt) {
            case 1:
                    PalyaLogikaTeszteles();
                break;
            case 2:
                    GombaszLogikaTeszteles();
                break;
            case 3:
                BogarLogikaTeszteles();
                break;
            default:
                sporaSzorSimaTeszt();
        }
      
    }

    void PalyaLogikaTeszteles()
    {
        System.out.println("\nTesztelheto Palya logikak:\n1:JatekInditasa\n2:FonalElhalSzetesesMiatt\n3:TektonSzetesesFonalNelkul\n4:FonalFelsziv");
    }

    void GombaszLogikaTeszteles()
    {
        System.out.println("\nTesztelheto Gombasz logikak:\n1:FonalLerakSima\n2:SporaSzorSima\n3:SporaSzorFejlett\n4:GombatestEpitFonallal\n5:GombatestEpitSporaval\n6:GombatestMeghal\n7:fonalElhalGombatestMiatt\n8:EgyFonalasHonnan\n9:EgyFonalasHova");
    }

    void BogarLogikaTeszteles()
    {
        System.out.println("\nTesztelheto Bogarasz logikak:\n1:FonalElhalBogarMiatt\n2:BogarLep\n3:BogarRag\n4:BogarEszik\n5:BogarEmeszt");
    }

    void BogarEmesztTeszteles()
    {
        System.out.println("\nTesztelheto Spora fajta emesztesek:\n1:Gyorsito\n2:Benito\n3:Keseru\n4:Lassito");
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
        Tekton t1 = new Tekton();
        addToMap(t1, "t1");
        Tekton t2 = new Tekton();
        addToMap(t1, "t2");
        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        ArrayList<Tekton> palya = new ArrayList<Tekton>();
        palya.add(t2);
        palya.add(t1);
        Gombasz jatekos = new Gombasz(palya);
        Gombatest test = new Gombatest(t1,jatekos);
        addToMap(jatekos, "jatekos");
        addToMap(test, "test");
        jatekos.gombatestHozzaad(test);
        t1.setGombatest(test);

        Lassito spora = new Lassito();
        addToMap(spora, "spora");
        spora.setTartozik(jatekos);
        test.setSpora(spora);
        
        jatekos.sporaSzor();
        gyujtemenyKiiratas();
    }

    void sporaSzorFejlettTeszt()
    {
        Tekton t1 = new Tekton(null);
        Tekton t2 = new Tekton(null);
        Tekton t3 = new Tekton(null);
        addToMap(t1, "t1");
        addToMap(t2, "t2");
        addToMap(t3, "t3");
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
        addToMap(jatekos, "jatekos");
        addToMap(test, "test");
        jatekos.gombatestHozzaad(test);
        t1.setGombatest(test);

        Lassito spora = new Lassito();
        addToMap(spora, "spora");
        spora.setTartozik(jatekos);
        test.setSpora(spora);
        test.setMaradt(2);
        jatekos.sporaSzor();
    }


    void GombatestEpitFonallalTeszt()
    {
        Tekton t1 = new Tekton(null);
        Tekton t2 = new Tekton(null);
        addToMap(t1, "t1");
        addToMap(t2, "t2");
        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        ArrayList<Tekton> palya = new ArrayList<Tekton>();
        palya.add(t2);
        palya.add(t1);
        Gombasz jatekos = new Gombasz(palya);
        Gombatest test = new Gombatest(t1,jatekos);
        addToMap(jatekos, "jatekos");
        addToMap(test, "test");
        jatekos.gombatestHozzaad(test);
        t1.setGombatest(test);

        Lassito s1 = new Lassito();
        addToMap(s1, "s1");
        s1.setTartozik(jatekos);
        t2.addSpora(s1);

        Lassito s2 = new Lassito();
        addToMap(s2, "s2");
        s2.setTartozik(jatekos);
        t2.addSpora(s2);

        Lassito s3 = new Lassito();
        addToMap(s3, "s3");
        s3.setTartozik(jatekos);
        t2.addSpora(s3);

        Lassito s4 = new Lassito();
        addToMap(s4, "s4");
        s4.setTartozik(jatekos);
        t2.addSpora(s4);

        Lassito s5 = new Lassito();
        addToMap(s5, "s5");
        s5.setTartozik(jatekos);
        test.setSpora(s5);
        
        jatekos.fonalLerak();
    }


    void GombatestEpitSporavalTeszt()
    {
        Tekton t1 = new Tekton(null);
        Tekton t2 = new Tekton(null);
        addToMap(t1, "t1");
        addToMap(t2, "t2");
        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        ArrayList<Tekton> palya = new ArrayList<Tekton>();
        palya.add(t2);
        palya.add(t1);
        Gombasz jatekos = new Gombasz(palya);
        Gombatest test = new Gombatest(t1,jatekos);
        addToMap(jatekos, "jatekos");
        addToMap(test, "test");
        jatekos.gombatestHozzaad(test);
        t1.setGombatest(test);

        Lassito s1 = new Lassito();
        addToMap(s1, "s1");
        s1.setTartozik(jatekos);
        t2.addSpora(s1);

        Lassito s2 = new Lassito();
        addToMap(s2, "s2");
        s2.setTartozik(jatekos);
        t2.addSpora(s2);

        Lassito s3 = new Lassito();
        addToMap(s3, "s3");
        s3.setTartozik(jatekos);
        t2.addSpora(s3);

        Lassito s4 = new Lassito();
        addToMap(s4, "s4");
        s4.setTartozik(jatekos);
        t2.addSpora(s4);


        Lassito s5 = new Lassito();
        addToMap(s5, "s5");
        s5.setTartozik(jatekos);
        test.setSpora(s5);
        
        Fonal fon1 = new Fonal(t2,jatekos);
        addToMap(fon1, "fon1");
        t1.addFonal(fon1);
        Fonal fon2 = new Fonal(t1,jatekos);
        addToMap(fon2, "fon2");
        t2.addFonal(fon2);

        jatekos.sporaSzor();
    }


    void gombaTestMeghalTeszt()
    {
        Tekton t1 = new Tekton(null);
        Tekton t2 = new Tekton(null);
        addToMap(t1, "t1");
        addToMap(t2, "t2");
        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        ArrayList<Tekton> palya = new ArrayList<Tekton>();
        palya.add(t2);
        palya.add(t1);
        Gombasz jatekos = new Gombasz(palya);
        Gombatest test = new Gombatest(t1,jatekos);
        addToMap(jatekos, "jatekos");
        addToMap(test, "test");
        jatekos.gombatestHozzaad(test);
        t1.setGombatest(test);

        Lassito spora = new Lassito();
        addToMap(spora, "spora");
        spora.setTartozik(jatekos);
        test.setSpora(spora);
        test.setMaradt(1);

        Fonal fon1 = new Fonal(t2,jatekos);
        addToMap(fon1, "fon1");
        t1.addFonal(fon1);
        Fonal fon2 = new Fonal(t1,jatekos);
        t2.addFonal(fon2);
        addToMap(fon2, "fon2");

        jatekos.sporaSzor();
    }


    void fonalElhalBogarMiattTeszt()
    {
        Tekton t1 = new Tekton(null);
        Tekton t2 = new Tekton(null);
        Tekton t3 = new Tekton(null);
        Tekton t4 = new Tekton(null);
        addToMap(t1, "t1");
        addToMap(t2, "t2");
        addToMap(t3, "t3");
        addToMap(t4, "t4");
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
        addToMap(jatekos, "jatekos");
        addToMap(test, "test");

        jatekos.gombatestHozzaad(test);
        t1.setGombatest(test);

        Fonal fon10 = new Fonal(t2,jatekos);
        Fonal fon11 = new Fonal(t1,jatekos);
        addToMap(fon10, "fon10");
        addToMap(fon11, "fon11");
        t2.addFonal(fon11);
        t1.addFonal(fon10);

        Fonal fon20 = new Fonal(t2,jatekos);
        Fonal fon21 = new Fonal(t3,jatekos);
        addToMap(fon21, "fon21");
        addToMap(fon20, "fon20");
        t2.addFonal(fon21);
        t3.addFonal(fon20);

        Fonal fon30 = new Fonal(t4,jatekos);
        Fonal fon31 = new Fonal(t3,jatekos);
        addToMap(fon31, "fon31");
        addToMap(fon30, "fon30");
        t4.addFonal(fon31);
        t3.addFonal(fon30);

        Bogarasz jatekos1 = new Bogarasz();
        Bogar bogar = new Bogar();
        addToMap(jatekos1, "jatekos1");
        addToMap(bogar, "bogar");
        jatekos1.bogarHozzaad(bogar, t1);

        jatekos1.ragas();
    }


    void fonalElhalGombatestMiattTeszt()
    {
        Tekton t1 = new Tekton(null);
        Tekton t2 = new Tekton(null);
        Tekton t3 = new Tekton(null);
        Tekton t4 = new Tekton(null);
        addToMap(t1, "t1");
        addToMap(t2, "t2");
        addToMap(t3, "t3");
        addToMap(t3, "t3");
        addToMap(t4, "t4");
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
        addToMap(jatekos, "jatekos");
        addToMap(test, "test"); 
        jatekos.gombatestHozzaad(test);
        t1.setGombatest(test);

        Fonal fon10 = new Fonal(t2,jatekos);
        Fonal fon11 = new Fonal(t1,jatekos);
        addToMap(fon10, "fon10");
        addToMap(fon11, "fon11");
        t2.addFonal(fon11);
        t1.addFonal(fon10);

        Fonal fon20 = new Fonal(t2,jatekos);
        Fonal fon21 = new Fonal(t3,jatekos);
        addToMap(fon21, "fon21");
        addToMap(fon20, "fon20");
        t2.addFonal(fon21);
        t3.addFonal(fon20);

        Fonal fon30 = new Fonal(t4,jatekos);
        Fonal fon31 = new Fonal(t3,jatekos);
        addToMap(fon31, "fon31");
        addToMap(fon30, "fon30");
        t4.addFonal(fon31);
        t3.addFonal(fon30);

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
        addToMap(t1, "t1");
        addToMap(t2, "t2");
        addToMap(t3, "t3");
        addToMap(t3, "t3");
        addToMap(t4, "t4");
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
        addToMap(jatekos, "jatekos");
        addToMap(test, "test");

        jatekos.gombatestHozzaad(test);
        t1.setGombatest(test);

        Fonal fon10 = new Fonal(t2,jatekos);
        Fonal fon11 = new Fonal(t1,jatekos);
        addToMap(fon10, "fon10");
        addToMap(fon11, "fon11");
        t2.addFonal(fon11);
        t1.addFonal(fon10);

        Fonal fon20 = new Fonal(t2,jatekos);
        Fonal fon21 = new Fonal(t3,jatekos);
        addToMap(fon21, "fon21");
        addToMap(fon20, "fon20");
        t2.addFonal(fon21);
        t3.addFonal(fon20);

        Fonal fon30 = new Fonal(t4,jatekos);
        Fonal fon31 = new Fonal(t3,jatekos);
        addToMap(fon31, "fon31");
        addToMap(fon30, "fon30");
        t4.addFonal(fon31);
        t3.addFonal(fon30);

       t2.tores();

    }

    void BogarLepTeszt()
    {
        Tekton t1 = new Tekton(null);
        Tekton t2 = new Tekton(null);
        addToMap(t1, "t1");
        addToMap(t2, "t2");

        ArrayList<Tekton> palya = new ArrayList<Tekton>();
        palya.add(t2);
        palya.add(t1);
        Gombasz jatekos = new Gombasz(palya);
        addToMap(jatekos, "jatekos");

        Fonal fon10 = new Fonal(t2,jatekos);
        Fonal fon11 = new Fonal(t1,jatekos);
        addToMap(fon10, "fon10");
        addToMap(fon11, "fon11");
        t2.addFonal(fon11);
        t1.addFonal(fon10);

        Bogarasz jatekos1 = new Bogarasz();
        Bogar bogar = new Bogar();
        addToMap(jatekos1, "jatekos1");
        addToMap(bogar, "bogar");

        jatekos1.bogarHozzaad(bogar, t1);

        jatekos1.lep();
    }

    void BogarRagTeszt()
    {
        Tekton t1 = new Tekton(null);
        Tekton t2 = new Tekton(null);
        addToMap(t1, "t1");
        addToMap(t2, "t2");

        ArrayList<Tekton> palya = new ArrayList<Tekton>();
        palya.add(t2);
        palya.add(t1);
        Gombasz jatekos = new Gombasz(palya);
        addToMap(jatekos, "jatekos");

        Fonal fon10 = new Fonal(t2,jatekos);
        Fonal fon11 = new Fonal(t1,jatekos);
        addToMap(fon10, "fon10");
        addToMap(fon11, "fon11");
        t2.addFonal(fon11);
        t1.addFonal(fon10);

        Bogarasz jatekos1 = new Bogarasz();
        Bogar bogar = new Bogar();
        addToMap(jatekos1, "jatekos1");
        addToMap(bogar, "bogar");

        jatekos1.bogarHozzaad(bogar, t1);

        jatekos1.ragas();
    }

    void BogarEszikTeszt()
    {
        Tekton t1 = new Tekton(null);

        Sima s = new Sima();
        addToMap(s, "s");
        Bogarasz jatekos1 = new Bogarasz();
        Bogar bogar = new Bogar();
        addToMap(jatekos1, "jatekos1");
        addToMap(bogar, "bogar");
        jatekos1.bogarHozzaad(bogar, t1);

        jatekos1.eves();
    }

    void BogarEmesztGyorsitoTeszt()
    {
        Tekton t1 = new Tekton(null);

        Spora s = new Gyorsito();
        addToMap(s, "s");
        Bogarasz jatekos1 = new Bogarasz();
        Bogar bogar = new Bogar();
        addToMap(jatekos1, "jatekos1");
        addToMap(bogar, "bogar");
        jatekos1.bogarHozzaad(bogar, t1);
        bogar.setElozo(s);

        bogar.sporaMegemesztes();
    }

    void BogarEmesztBenitoTeszt()
    {
        Tekton t1 = new Tekton(null);

        Spora s = new Benito();
        addToMap(s, "s");
        Bogarasz jatekos1 = new Bogarasz();
        Bogar bogar = new Bogar();
        addToMap(jatekos1, "jatekos1");
        addToMap(bogar, "bogar");
        jatekos1.bogarHozzaad(bogar, t1);
        bogar.setElozo(s);

        bogar.sporaMegemesztes();
    }

    void BogarEmesztkeseruTeszt()
    {
        Tekton t1 = new Tekton(null);

        Spora s = new Keseru();
        addToMap(s, "s");
        Bogarasz jatekos1 = new Bogarasz();
        Bogar bogar = new Bogar();
        addToMap(jatekos1, "jatekos1");
        addToMap(bogar, "bogar");
        jatekos1.bogarHozzaad(bogar, t1);
        bogar.setElozo(s);

        bogar.sporaMegemesztes();
    }

    void BogarEmesztlassitoTeszt()
    {
        Tekton t1 = new Tekton(null);

        Spora s = new Lassito();
        addToMap(s, "s");
        Bogarasz jatekos1 = new Bogarasz();
        Bogar bogar = new Bogar();
        addToMap(jatekos1, "jatekos1");
        addToMap(bogar, "bogar");
        jatekos1.bogarHozzaad(bogar, t1);
        bogar.setElozo(s);

        bogar.sporaMegemesztes();
    }

    void tektonSzetesesFonalNelkulTeszt()
    {
        Palya jatekter = new Palya();
        Tekton palya = new Tekton(null);
        Tekton szomszed1 = new Tekton(null);
        Tekton szomszed2 = new Tekton(null);
        addToMap(szomszed1, "szomszed1");
        addToMap(szomszed2, "szomszed22");
        palya.addSzomszed(szomszed1);
        palya.addSzomszed(szomszed2);

        jatekter.TektonHozzaad(palya);
        jatekter.TektonHozzaad(szomszed1);
        jatekter.TektonHozzaad(szomszed2);

        jatekter.TektonHozzaad(palya.tores());//itt bekene adni egy 0 erteket, hogy biztosan szettorjon, mert tamas megoldasaval nem megoldhato az, ami miatt direkt ugy csinatuk ahogy akartuk
    }


    void fonalFelszivTeszt()
    {
        Tekton t1 = new Tekton(null);
        Tekton t2 = new Felszivo(null);
        addToMap(t1, "t1");
        addToMap(t2, "t2");
        Palya jatekter = new Palya();
        jatekter.TektonHozzaad(t1);
        jatekter.TektonHozzaad(t2);
        Gombasz jatekos = new Gombasz(jatekter.palya);
        addToMap(jatekos, "jatekos");

        Fonal fon10 = new Fonal(t2,jatekos);
        Fonal fon11 = new Fonal(t1,jatekos);
        addToMap(fon10, "fon10");
        addToMap(fon11, "fon11");
        t2.addFonal(fon11);
        t1.addFonal(fon10);

        jatekter.felszivodo();
     
    }

    void egyFonalasHonnanTeszt()
    {
        Tekton t1 = new Tekton(null);
        Tekton t2 = new Egyfonalas(null);
        Tekton t3 = new Tekton(null);
        addToMap(t1, "t1");
        addToMap(t2, "t2");
        addToMap(t3, "t3");

        Palya jatekter = new Palya();
        jatekter.TektonHozzaad(t1);
        jatekter.TektonHozzaad(t2);
        jatekter.TektonHozzaad(t3);
        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        t3.addSzomszed(t2);
        t2.addSzomszed(t3);
        Gombasz jatekos = new Gombasz(jatekter.palya);
        addToMap(jatekos, "jatekos");

        Fonal fon10 = new Fonal(t2,jatekos);
        Fonal fon11 = new Fonal(t1,jatekos);
        addToMap(fon10, "fon10");
        addToMap(fon11, "fon11");
        t2.addFonal(fon11);
        t1.addFonal(fon10);

        Gombatest test = new Gombatest(t1,jatekos);
        addToMap(test, "test");
        jatekos.gombatestHozzaad(test);
        t1.setGombatest(test);

        Lassito s1 = new Lassito();
        addToMap(s1, "s1");
        s1.setTartozik(jatekos);
        test.setSpora(s1);

        jatekos.fonalLerak();
     
    }

    void egyFonalasHovaTeszt()
    {
        Tekton t1 = new Tekton(null);
        Tekton t2 = new Tekton(null);
        Tekton t3 = new Egyfonalas(null);
        addToMap(t1, "t1");
        addToMap(t2, "t2");
        addToMap(t3, "t3");
        Palya jatekter = new Palya();
        jatekter.TektonHozzaad(t1);
        jatekter.TektonHozzaad(t2);
        jatekter.TektonHozzaad(t3);
        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        t3.addSzomszed(t2);
        t2.addSzomszed(t3);
        Gombasz jatekos = new Gombasz(jatekter.palya);
        addToMap(jatekos, "jatekos");

        Fonal fon10 = new Fonal(t2,jatekos);
        Fonal fon11 = new Fonal(t1,jatekos);
        addToMap(fon10, "fon10");
        addToMap(fon11, "fon11");
        t2.addFonal(fon11);
        t1.addFonal(fon10);

        Gombatest test = new Gombatest(t1,jatekos);
        jatekos.gombatestHozzaad(test);
        addToMap(test, "test");
        t1.setGombatest(test);

        Lassito s1 = new Lassito();
        addToMap(s1, "s1");
        s1.setTartozik(jatekos);
        test.setSpora(s1);

        jatekos.fonalLerak();
     
    }
}