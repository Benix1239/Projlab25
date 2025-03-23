
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Scanner;
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
        int melyseg = objektumNevek.size();
        return "\t".repeat(melyseg);
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
        boolean tart = true;
        do
        {
            
            System.out.println("A menupont elotti szím beirasaval valaszthatod ki a dolgokat");
            System.out.println("Mit szeretnel tesztelni?:");
            System.out.println("1:Palya logika\n2:Gombasz logika\n3:Bogarasz logika\n4:kilepes"); 
            
            Scanner bemenet = new Scanner(System.in);
            int valaszt = bemenet.nextInt();
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
                case 4:
                    tart = false;
                    break;
                default:
                    
            }
            bemenet.close();
            
        }while(tart);
        
    }

    void PalyaLogikaTeszteles()
    {
        System.out.println("\nTesztelheto Palya logikak:\n1:JatekInditasa\n2:FonalElhalSzetesesMiatt\n3:TektonSzetesesFonalNelkul\n4:FonalFelsziv");
        Scanner bemenet = new Scanner(System.in);
        int valaszt = bemenet.nextInt();
        switch (valaszt) {
            case 1:
                    jatekInditasTeszt();
                break;
            case 2:
                    fonalElhalSzetesesMiattTeszt();
                break;
            case 3:
                    tektonSzetesesFonalNelkulTeszt();
                break;
            case 4:
                    fonalFelszivTeszt();
                break;
            default:
                
        }
        bemenet.close();
    }

    void GombaszLogikaTeszteles()
    {
        System.out.println("\nTesztelheto Gombasz logikak:\n1:FonalLerakSima\n2:SporaSzorSima\n3:SporaSzorFejlett\n4:GombatestEpitFonallal\n5:GombatestEpitSporaval\n6:GombatestMeghal\n7:fonalElhalGombatestMiatt\n8:EgyFonalasHonnan\n9:EgyFonalasHova");
        Scanner bemenet = new Scanner(System.in);
        int valaszt = bemenet.nextInt();
        switch (valaszt) {
            case 1:
                this.fonalLerakSimaTeszt();
                break;
            case 2:
                this.sporaSzorSimaTeszt();
                break;
            case 3:
                this.sporaSzorFejlettTeszt();
                break;
            case 4:
                this.GombatestEpitFonallalTeszt();
                break;
            case 5:
                this.GombatestEpitSporavalTeszt();
                break;
            case 6:
                this.gombaTestMeghalTeszt();
                break;
            case 7:
                this.fonalElhalGombatestMiattTeszt();
                break;
            case 8:
                this.egyFonalasHonnanTeszt();
                break;
            case 9:
                this.egyFonalasHovaTeszt();
                break;
            default:
                
        }
        bemenet.close();
    }

    void BogarLogikaTeszteles()
    {
        System.out.println("\nTesztelheto Bogarasz logikak:\n1:FonalElhalBogarMiatt\n2:BogarLep\n3:BogarRag\n4:BogarEszik\n5:BogarEmeszt");
        Scanner bemenet = new Scanner(System.in);
        int valaszt = bemenet.nextInt();
        switch (valaszt) {
            case 1:
                    fonalElhalBogarMiattTeszt();
                break;
            case 2:
                    BogarLepTeszt();
                break;
            case 3:
                    BogarRagTeszt();
                break;
            case 4:
                    BogarEszikTeszt();
                break;
            case 5:
                    BogarEmesztTeszteles();
                break;
            default:
                
        }
        bemenet.close();
    }

    void BogarEmesztTeszteles()
    {
        System.out.println("\nTesztelheto Spora fajta emesztesek:\n1:Gyorsito\n2:Benito\n3:Keseru\n4:Lassito");
        Scanner bemenet = new Scanner(System.in);
        int valaszt = bemenet.nextInt();
        switch (valaszt) {
            case 1:
                    BogarEmesztGyorsitoTeszt();
                break;
            case 2:
                    BogarEmesztBenitoTeszt();
                break;
            case 3:
                    BogarEmesztkeseruTeszt();
                break;
            case 4:
                    BogarEmesztlassitoTeszt();
                break;
            default:
                
        }
        bemenet.close();
    }

//---------------------------------------------------------------------------------------------------------------------------------
    void jatekInditasTeszt()
    {
        Jatek tesztJatek = new Jatek(10);
    }

    void fonalLerakSimaTeszt()
    {
        
        Tekton hely = new Tekton();
        Tekton t2 = new Tekton();
        addToMap(hely, "hely");
        addToMap(t2, "t2");
        hely.addSzomszed(t2);
        t2.addSzomszed(hely);
        Palya jatekter = new Palya();
        addToMap(jatekter, "jatekter");
        jatekter.tektonHozzaad(t2);
        jatekter.tektonHozzaad(hely);
        Gombasz karakter = new Gombasz(jatekter.getPalya());
        Gombatest test = new Gombatest(hely,karakter);
        addToMap(karakter, "karakter");
        addToMap(test, "test");
        karakter.gombatestHozzaad(test);
        hely.setGombatest(test);

        karakter.fonalLerak();
    }

    //naon fasza, diagramm szerint fut
    void sporaSzorSimaTeszt()
    {
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        addToMap(t1, "t1");
        addToMap(t2, "t2");
        t1.addSzomszed(t2);
        t2.addSzomszed(t1);

        Palya jatekter = new Palya();
        addToMap(jatekter, "jatekter");
        jatekter.tektonHozzaad(t2);
        jatekter.tektonHozzaad(t1);

        Gombasz jatekos = new Gombasz(jatekter.getPalya());
        Gombatest test = new Gombatest(t1,jatekos);
        addToMap(jatekos, "jatekos");
        addToMap(test, "test");
        jatekos.gombatestHozzaad(test);
        t1.setGombatest(test);

        Lassito spora = new Lassito(jatekos);
        addToMap(spora, "spora");
        spora.setTartozik(jatekos);
        test.setSpora(spora);
        
        gyujtemeny = new ArrayList<>();
        jatekos.sporaSzor();
        gyujtemenyKiiratas();
    }

    void sporaSzorFejlettTeszt()
    {
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        Tekton t3 = new Tekton();
        addToMap(t1, "t1");
        addToMap(t2, "t2");
        addToMap(t3, "t3");
        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        t3.addSzomszed(t2);
        t2.addSzomszed(t3);

        Palya jatekter = new Palya();
        addToMap(jatekter, "jatekter");
        jatekter.tektonHozzaad(t1);
        jatekter.tektonHozzaad(t2);
        jatekter.tektonHozzaad(t3);

        Gombasz jatekos = new Gombasz(jatekter.getPalya());
        Gombatest test = new Gombatest(t1,jatekos);
        addToMap(jatekos, "jatekos");
        addToMap(test, "test");
        jatekos.gombatestHozzaad(test);
        t1.setGombatest(test);

        Lassito spora = new Lassito(jatekos);
        addToMap(spora, "spora");
        spora.setTartozik(jatekos);
        test.setSpora(spora);
        test.setMaradt(2);
        jatekos.sporaSzor();
    }


    void GombatestEpitFonallalTeszt()
    {
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        addToMap(t1, "t1");
        addToMap(t2, "t2");
        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        Palya jatekter = new Palya();
        addToMap(jatekter, "jatekter");
        jatekter.tektonHozzaad(t1);
        jatekter.tektonHozzaad(t2);
        Gombasz jatekos = new Gombasz(jatekter.getPalya());
        Gombatest test = new Gombatest(t1,jatekos);
        addToMap(jatekos, "jatekos");
        addToMap(test, "test");
        jatekos.gombatestHozzaad(test);
        t1.setGombatest(test);

        Lassito s1 = new Lassito(jatekos);
        addToMap(s1, "s1");
        s1.setTartozik(jatekos);
        t2.addSpora(s1);

        Lassito s2 = new Lassito(jatekos);
        addToMap(s2, "s2");
        s2.setTartozik(jatekos);
        t2.addSpora(s2);

        Lassito s3 = new Lassito(jatekos);
        addToMap(s3, "s3");
        s3.setTartozik(jatekos);
        t2.addSpora(s3);

        Lassito s4 = new Lassito(jatekos);
        addToMap(s4, "s4");
        s4.setTartozik(jatekos);
        t2.addSpora(s4);

        Lassito s5 = new Lassito(jatekos);
        addToMap(s5, "s5");
        s5.setTartozik(jatekos);
        test.setSpora(s5);
        
        jatekos.fonalLerak();
    }


    void GombatestEpitSporavalTeszt()
    {
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        addToMap(t1, "t1");
        addToMap(t2, "t2");
        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        Palya jatekter = new Palya();
        jatekter.tektonHozzaad(t1);
        jatekter.tektonHozzaad(t2);
        Gombasz jatekos = new Gombasz(jatekter.getPalya());
        Gombatest test = new Gombatest(t1,jatekos);
        addToMap(jatekos, "jatekos");
        addToMap(test, "test");
        jatekos.gombatestHozzaad(test);
        t1.setGombatest(test);

        Lassito s1 = new Lassito(jatekos);
        addToMap(s1, "s1");
        s1.setTartozik(jatekos);
        t2.addSpora(s1);

        Lassito s2 = new Lassito(jatekos);
        addToMap(s2, "s2");
        s2.setTartozik(jatekos);
        t2.addSpora(s2);

        Lassito s3 = new Lassito(jatekos);
        addToMap(s3, "s3");
        t2.addSpora(s3);

        Lassito s4 = new Lassito(jatekos);
        addToMap(s4, "s4");
        t2.addSpora(s4);


        Lassito s5 = new Lassito(jatekos);
        addToMap(s5, "s5");
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
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        addToMap(t1, "t1");
        addToMap(t2, "t2");
        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        Palya jatekter = new Palya();
        addToMap(jatekter, "jatekter");
        jatekter.tektonHozzaad(t1);
        jatekter.tektonHozzaad(t2);
        Gombasz jatekos = new Gombasz(jatekter.getPalya());
        Gombatest test = new Gombatest(t1,jatekos);
        addToMap(jatekos, "jatekos");
        addToMap(test, "test");
        jatekos.gombatestHozzaad(test);
        t1.setGombatest(test);

        Lassito spora = new Lassito(jatekos);
        addToMap(spora, "spora");
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
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        Tekton t3 = new Tekton();
        Tekton t4 = new Tekton();
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

        Palya jatekter = new Palya();
        addToMap(jatekter, "jatekter");
        jatekter.tektonHozzaad(t1);
        jatekter.tektonHozzaad(t2);
        jatekter.tektonHozzaad(t3);
        jatekter.tektonHozzaad(t4);
        Gombasz jatekos = new Gombasz(jatekter.getPalya());
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
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        Tekton t3 = new Tekton();
        Tekton t4 = new Tekton();
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
        Palya jatekter = new Palya();
        addToMap(jatekter, "jatekter");
        jatekter.tektonHozzaad(t1);
        jatekter.tektonHozzaad(t2);
        jatekter.tektonHozzaad(t3);
        jatekter.tektonHozzaad(t4);
        Gombasz jatekos = new Gombasz(jatekter.getPalya());
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

        Lassito spora = new Lassito(jatekos);
        spora.setTartozik(jatekos);
        test.setSpora(spora);
        test.setMaradt(1);

        jatekos.sporaSzor();

    }

    void fonalElhalSzetesesMiattTeszt()
    {
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        Tekton t3 = new Tekton();
        Tekton t4 = new Tekton();
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

        Palya jatekter = new Palya();
        addToMap(jatekter, "jatekter");
        jatekter.tektonHozzaad(t1);
        jatekter.tektonHozzaad(t2);
        jatekter.tektonHozzaad(t3);
        jatekter.tektonHozzaad(t4);

        Gombasz jatekos = new Gombasz(jatekter.getPalya());
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
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        addToMap(t1, "t1");
        addToMap(t2, "t2");

        Palya jatekter = new Palya();
        addToMap(jatekter, "jatekter");
        jatekter.tektonHozzaad(t1);
        jatekter.tektonHozzaad(t2);
        Gombasz jatekos = new Gombasz(jatekter.getPalya());
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
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        addToMap(t1, "t1");
        addToMap(t2, "t2");

        Palya jatekter = new Palya();
        addToMap(jatekter, "jatekter");
        jatekter.tektonHozzaad(t1);
        jatekter.tektonHozzaad(t2);
        Gombasz jatekos = new Gombasz(jatekter.getPalya());
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
        Tekton t1 = new Tekton();
        addToMap(t1, "t1");
        Palya jatekter = new Palya();
        addToMap(jatekter, "jatekter");
        jatekter.tektonHozzaad(t1);
        Gombasz jatekos = new Gombasz(jatekter.getPalya());
        addToMap(jatekos, "jatekos");
        Sima s = new Sima(jatekos);
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
        Tekton t1 = new Tekton();
        addToMap(t1, "t1");
        Palya jatekter = new Palya();
        addToMap(jatekter, "jatekter");
        jatekter.tektonHozzaad(t1);
        Gombasz jatekos = new Gombasz(jatekter.getPalya());
        addToMap(jatekos, "jatekos");
        Spora s = new Gyorsito(jatekos);
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
        Tekton t1 = new Tekton();
        addToMap(t1, "t1");
        Palya jatekter = new Palya();
        addToMap(jatekter, "jatekter");
        jatekter.tektonHozzaad(t1);
        Gombasz jatekos = new Gombasz(jatekter.getPalya());
        addToMap(jatekos, "jatekos");
        Spora s = new Benito(jatekos);
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
        Tekton t1 = new Tekton();
        addToMap(t1, "t1");
        Palya jatekter = new Palya();
        addToMap(jatekter, "jatekter");
        jatekter.tektonHozzaad(t1);
        Gombasz jatekos = new Gombasz(jatekter.getPalya());
        addToMap(jatekos, "jatekos");
        Spora s = new Keseru(jatekos);
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
        Tekton t1 = new Tekton();
        addToMap(t1, "t1");
        Palya jatekter = new Palya();
        addToMap(jatekter, "jatekter");
        jatekter.tektonHozzaad(t1);
        Gombasz jatekos = new Gombasz(jatekter.getPalya());
        addToMap(jatekos, "jatekos");
        Spora s = new Lassito(jatekos);
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
        Palya jatekter = new Palya(10);
        addToMap(jatekter, "jatekter");
        Tekton palya = new Tekton();
        addToMap(palya, "palya");
        Tekton szomszed1 = new Tekton();
        Tekton szomszed2 = new Tekton();
        addToMap(szomszed1, "szomszed1");
        addToMap(szomszed2, "szomszed2");
        palya.addSzomszed(szomszed1);
        palya.addSzomszed(szomszed2);

        jatekter.tektonHozzaad(palya);
        jatekter.tektonHozzaad(szomszed1);
        jatekter.tektonHozzaad(szomszed2);

        jatekter.tektonHozzaad(palya.tores());//itt bekene adni egy 0 erteket, hogy biztosan szettorjon, mert tamas megoldasaval nem megoldhato az, ami miatt direkt ugy csinatuk ahogy akartuk
    }


    void fonalFelszivTeszt()
    {
        Tekton t1 = new Tekton();
        Tekton t2 = new Felszivo();
        addToMap(t1, "t1");
        addToMap(t2, "t2");
        Palya jatekter = new Palya();
        addToMap(jatekter, "jatekter");
        jatekter.tektonHozzaad(t1);
        jatekter.tektonHozzaad(t2);
        Gombasz jatekos = new Gombasz(jatekter.getPalya());
        addToMap(jatekos, "jatekos");

        Fonal fon10 = new Fonal(t2,jatekos);
        Fonal fon11 = new Fonal(t1,jatekos);
        addToMap(fon10, "fon10");
        addToMap(fon11, "fon11");
        t2.addFonal(fon11);
        t1.addFonal(fon10);

        jatekter.felszivodik();
     
    }

    void egyFonalasHonnanTeszt()
    {
        Tekton t1 = new Tekton();
        Tekton t2 = new Egyfonalas();
        Tekton t3 = new Tekton();
        addToMap(t1, "t1");
        addToMap(t2, "t2");
        addToMap(t3, "t3");

        Palya jatekter = new Palya();
        addToMap(jatekter, "jatekter");
        jatekter.tektonHozzaad(t1);
        jatekter.tektonHozzaad(t2);
        jatekter.tektonHozzaad(t3);
        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        t3.addSzomszed(t2);
        t2.addSzomszed(t3);
        Gombasz jatekos = new Gombasz(jatekter.getPalya());
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

        Lassito s1 = new Lassito(jatekos);
        addToMap(s1, "s1");
        s1.setTartozik(jatekos);
        test.setSpora(s1);

        jatekos.fonalLerak();
     
    }

    void egyFonalasHovaTeszt()
    {
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        Tekton t3 = new Egyfonalas();
        addToMap(t1, "t1");
        addToMap(t2, "t2");
        addToMap(t3, "t3");
        Palya jatekter = new Palya();
        addToMap(jatekter, "jatekter");
        jatekter.tektonHozzaad(t1);
        jatekter.tektonHozzaad(t2);
        jatekter.tektonHozzaad(t3);
        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        t3.addSzomszed(t2);
        t2.addSzomszed(t3);
        Gombasz jatekos = new Gombasz(jatekter.getPalya());
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

        Lassito s1 = new Lassito(jatekos);
        addToMap(s1, "s1");
        s1.setTartozik(jatekos);
        test.setSpora(s1);

        jatekos.fonalLerak();
     
    }
}