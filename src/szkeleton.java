
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Stack;


public class szkeleton
{
    private static ArrayList<String> gyujtemeny = new ArrayList<>();
    private static Map<Object, String> objectToStringMap = new IdentityHashMap<>();
    private static Map<String, Object> stringToObjectMap = new IdentityHashMap<>();
    private static int objectCounter = 1;

    private static Object jelenlegiHivo = new Object();

    private static Stack<String> objektumNevek;

    public static void addToMap(Object o, String name){
        if(o == null || name == null || objectToStringMap.containsKey(o) || stringToObjectMap.containsKey(name))
            return;
        objectToStringMap.putIfAbsent(o, name);
        stringToObjectMap.putIfAbsent(name, o);
    }

    private static String indentalasSzamitas() {
        int melyseg = Thread.currentThread().getStackTrace().length;
        return "    ".repeat(Math.max(0, melyseg - 4));
    }

    public static void logMethodEntry(Object hivott, String methodName) {
        Object hivo = jelenlegiHivo;
        objektumNevek.push(objectToStringMap.get(hivo));
        jelenlegiHivo = hivott;
        //System.out.println(indentalasSzamitas() + objectToStringMap.get(hivo) + " -> " + objectToStringMap.get(hivott) + ": " + methodName + "()");
        String uzenet = indentalasSzamitas() + objectToStringMap.get(hivo) + " -> " + objectToStringMap.get(hivott) + ": " + methodName + "()";
        gyujtemeny.add(uzenet);
    }

    public static void logMethodExit(Object hivott, Object returnValue) {
        String hivo = objektumNevek.getLast();
        //System.out.println(indentalasSzamitas() + hivo + " <- " + objectToStringMap.get(hivott) + " : " + returnValue);
        String uzenet = indentalasSzamitas() + hivo + " <- " + objectToStringMap.get(hivott) + " : " + returnValue;
        gyujtemeny.add(uzenet);

        String jelenlegiHivoNev;
        jelenlegiHivoNev = objektumNevek.pop();
        jelenlegiHivo = stringToObjectMap.get(jelenlegiHivoNev);
    }

    public static void logConstructorEntry(Object hivott, String objectName){
        addToMap(hivott, objectName);
        Object hivo = jelenlegiHivo;
        objektumNevek.push(objectToStringMap.get(hivo));
        jelenlegiHivo = hivott;
        String uzenet = indentalasSzamitas() + objectToStringMap.get(hivo) + " -> " + objectToStringMap.get(hivott) + ": " + "<<Constructor>>";
        gyujtemeny.add(uzenet);
    }

    public static void logConstructorExit(Object hivott){
        //System.out.println(indentalasSzamitas() + hivo + " <- " + objectToStringMap.get(hivott) + " : " + returnValue);

        String jelenlegiHivoNev;
        jelenlegiHivoNev = objektumNevek.pop();
        jelenlegiHivo = stringToObjectMap.get(jelenlegiHivoNev);
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
        addToMap(jatekos, "jatekos");
        Gombatest test = new Gombatest(t1,jatekos);
        addToMap(test, "test");
        jatekos.gombatestHozzaad(test);
        t1.setGombatest(test);

        Lassito spora = new Lassito();
        addToMap(spora, "spora");
        spora.setTartozik(jatekos);
        test.setSpora(spora);
        
        gyujtemeny = new ArrayList<>();
        jatekos.sporaSzor();
        gyujtemenyKiiratas();
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

    void tektonSzetesesFonalNelkulTeszt()
    {
        Palya jatekter = new Palya();
        Tekton palya = new Tekton(null);
        Tekton szomszed1 = new Tekton(null);
        Tekton szomszed2 = new Tekton(null);

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
        Palya jatekter = new Palya();
        jatekter.TektonHozzaad(t1);
        jatekter.TektonHozzaad(t2);
        Gombasz jatekos = new Gombasz(jatekter.palya);

        Fonal fon10 = new Fonal(t2,jatekos);
        t1.addFonal(fon10);
        Fonal fon11 = new Fonal(t1,jatekos);
        t2.addFonal(fon11);

        jatekter.felszivodo();
     
    }

    void egyFonalasHonnanTeszt()
    {
        Tekton t1 = new Tekton(null);
        Tekton t2 = new Egyfonalas(null);
        Tekton t3 = new Tekton(null);
        Palya jatekter = new Palya();
        jatekter.TektonHozzaad(t1);
        jatekter.TektonHozzaad(t2);
        jatekter.TektonHozzaad(t3);
        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        t3.addSzomszed(t2);
        t2.addSzomszed(t3);
        Gombasz jatekos = new Gombasz(jatekter.palya);

        Fonal fon10 = new Fonal(t2,jatekos);
        t1.addFonal(fon10);
        Fonal fon11 = new Fonal(t1,jatekos);
        t2.addFonal(fon11);

        Gombatest test = new Gombatest(t1,jatekos);
        jatekos.gombatestHozzaad(test);
        t1.setGombatest(test);

        Lassito s1 = new Lassito();
        s1.setTartozik(jatekos);
        test.setSpora(s1);

        jatekos.fonalLerak();
     
    }

    void egyFonalasHovaTeszt()
    {
        Tekton t1 = new Tekton(null);
        Tekton t2 = new Tekton(null);
        Tekton t3 = new Egyfonalas(null);
        Palya jatekter = new Palya();
        jatekter.TektonHozzaad(t1);
        jatekter.TektonHozzaad(t2);
        jatekter.TektonHozzaad(t3);
        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        t3.addSzomszed(t2);
        t2.addSzomszed(t3);
        Gombasz jatekos = new Gombasz(jatekter.palya);

        Fonal fon10 = new Fonal(t2,jatekos);
        t1.addFonal(fon10);
        Fonal fon11 = new Fonal(t1,jatekos);
        t2.addFonal(fon11);

        Gombatest test = new Gombatest(t1,jatekos);
        jatekos.gombatestHozzaad(test);
        t1.setGombatest(test);

        Lassito s1 = new Lassito();
        s1.setTartozik(jatekos);
        test.setSpora(s1);

        jatekos.fonalLerak();
     
    }
}