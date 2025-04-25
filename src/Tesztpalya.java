import java.util.ArrayList;
import java.util.IdentityHashMap;

public class Tesztpalya 
{
   public  static Jatek tesztvilag = new Jatek();

    public static Jatek bogarLep()
    {
        tesztvilag = new Jatek();
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        
        tesztvilag.getJatekter().tektonHozzaad(t1);
        tesztvilag.getJatekter().tektonHozzaad(t2);

        Bogarasz karakter =new Bogarasz();
        Bogar bogar = new Bogar();
        karakter.bogarHozzaad(bogar, t1);
        tesztvilag.getBogaraszok().add(karakter);

        Gombasz jatekos = new Gombasz(tesztvilag.getJatekter().getPalya());
        Fonal fon10 = new Fonal(t2,jatekos);
        Fonal fon11 = new Fonal(t1,jatekos);
        t2.addFonal(fon11);
        t1.addFonal(fon10);
        tesztvilag.getGombaszok().add(jatekos);

        return tesztvilag;
    }

    public static Jatek bogarEszik()
    {
        tesztvilag = new Jatek();
        Tekton t1 = new Tekton();
        tesztvilag.getJatekter().tektonHozzaad(t1);

        Bogarasz karakter =new Bogarasz();
        Bogar bogar = new Bogar();
        karakter.bogarHozzaad(bogar, t1);
        tesztvilag.getBogaraszok().add(karakter);

        Gombasz jatekos = new Gombasz(tesztvilag.getJatekter().getPalya());
        Spora s= new Sima(jatekos);
        t1.addSpora(s);
        tesztvilag.getGombaszok().add(jatekos);

        return tesztvilag;
    }

    public static Jatek bogarNemEszik()
    {
        tesztvilag = new Jatek();
        Tekton t1 = new Tekton();
        tesztvilag.getJatekter().tektonHozzaad(t1);

        Bogarasz karakter =new Bogarasz();
        Bogar bogar = new Bogar();
        karakter.bogarHozzaad(bogar, t1);
        tesztvilag.getBogaraszok().add(karakter);

        Gombasz jatekos = new Gombasz(tesztvilag.getJatekter().getPalya());
        tesztvilag.getGombaszok().add(jatekos);

        return tesztvilag;
    }

    public static Jatek bogarRagas()
    {
        tesztvilag = new Jatek();
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        
        tesztvilag.getJatekter().tektonHozzaad(t1);
        tesztvilag.getJatekter().tektonHozzaad(t2);

        Bogarasz karakter =new Bogarasz();
        Bogar bogar = new Bogar();
        karakter.bogarHozzaad(bogar, t1);
        tesztvilag.getBogaraszok().add(karakter);

        Gombasz jatekos = new Gombasz(tesztvilag.getJatekter().getPalya());
        Fonal fon10 = new Fonal(t2,jatekos);
        Fonal fon11 = new Fonal(t1,jatekos);
        t2.addFonal(fon11);
        t1.addFonal(fon10);
        tesztvilag.getGombaszok().add(jatekos);

        return tesztvilag;
    }

    public static Jatek bogarNemRagas()
    {
        tesztvilag = new Jatek();
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        Tekton t3 = new Tekton();

        t1.addSzomszed(t2);
        t1.addSzomszed(t3);
        t2.addSzomszed(t1);
        t3.addSzomszed(t1);
        
        tesztvilag.getJatekter().tektonHozzaad(t1);
        tesztvilag.getJatekter().tektonHozzaad(t2);
        tesztvilag.getJatekter().tektonHozzaad(t3);

        Bogarasz karakter =new Bogarasz();
        Bogar bogar = new Bogar();
        karakter.bogarHozzaad(bogar, t1);
        tesztvilag.getBogaraszok().add(karakter);

        Gombasz jatekos = new Gombasz(tesztvilag.getJatekter().getPalya());
        
        Gombatest test = new Gombatest(t2,jatekos);
        jatekos.gombatestHozzaad(test);
        t2.setGombatest(test);

        Fonal fon10 = new Fonal(t2,jatekos);
        Fonal fon11 = new Fonal(t1,jatekos);
        t2.addFonal(fon11);
        t1.addFonal(fon10);
        Fonal fon20 = new Fonal(t3,jatekos);
        Fonal fon21 = new Fonal(t1,jatekos);
        t3.addFonal(fon21);
        t1.addFonal(fon20);
        

        tesztvilag.getGombaszok().add(jatekos);

        return tesztvilag;
    }

    public static Jatek bogarEmeszt(String fajta)
    {
        tesztvilag = new Jatek();
        Tekton t1;
        Bogarasz karakter;
        Bogar bogar;
        Gombasz jatekos;
        Spora spora;
        t1 = new Tekton();
        tesztvilag.getJatekter().tektonHozzaad(t1);

        karakter =new Bogarasz();
        bogar = new Bogar();
        karakter.bogarHozzaad(bogar, t1);
        tesztvilag.getBogaraszok().add(karakter);

        jatekos = new Gombasz(tesztvilag.getJatekter().getPalya());
        spora = new Sima(jatekos);

        switch (fajta) {
            case "gyorsito":
                spora= new Gyorsito(jatekos);
                break;
            case "benito":
                spora= new Benito(jatekos);
                break;
            case "keseru":
                spora= new Keseru(jatekos);
                break;
            case "lassito":
                spora= new Lassito(jatekos);
                break;
            case "szaporodo":
                spora= new Szaporodo(jatekos);
                break;
            default:
            
        }
        t1.addSpora(spora);
        tesztvilag.getGombaszok().add(jatekos);
        return tesztvilag;
    }
    
    public static Jatek fonalLerakSima() {
        tesztvilag = new Jatek();
        Tekton hely = new Tekton();
        Tekton t2 = new Tekton();
        hely.addSzomszed(t2);
        t2.addSzomszed(hely);
        tesztvilag.getJatekter().tektonHozzaad(t2);
        tesztvilag.getJatekter().tektonHozzaad(hely);
        Gombasz karakter = new Gombasz(tesztvilag.getJatekter().getPalya());
        Gombatest test = new Gombatest(hely,karakter);
        karakter.gombatestHozzaad(test);
        hely.setGombatest(test);
        tesztvilag.getGombaszok().add(karakter);
	    tesztvilag.jelenlegiJatekos().korElejeInicializalas();

        return tesztvilag;
    }
    
    public static Jatek egyFonalasHova() {
        Tekton t1 = new Egyfonalas();
        Tekton t2 = new Tekton();
        Tekton t3 = new Tekton();

        tesztvilag.getJatekter().tektonHozzaad(t1);
        tesztvilag.getJatekter().tektonHozzaad(t2);
        tesztvilag.getJatekter().tektonHozzaad(t3);
        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        t1.addSzomszed(t3);
        t3.addSzomszed(t1);
        t2.addSzomszed(t3);
        t3.addSzomszed(t2);
        Gombasz jatekos = new Gombasz(tesztvilag.getJatekter().getPalya());
        Gombasz jatekos2 = new Gombasz(tesztvilag.getJatekter().getPalya());

        Fonal fon10 = new Fonal(t2,jatekos2);
        Fonal fon11 = new Fonal(t1,jatekos2);
        Fonal fon12 = new Fonal(t2, jatekos);
        Fonal fon13 = new Fonal(t3, jatekos);
        t1.addFonal(fon10);
        t2.addFonal(fon11);
        t2.addFonal(fon13);
        t3.addFonal(fon12);

        Gombatest test = new Gombatest(t2,jatekos2);
        jatekos2.gombatestHozzaad(test);
        t2.setGombatest(test);

        Gombatest test2 = new Gombatest(t3, jatekos);
        jatekos.gombatestHozzaad(test2);
        t3.setGombatest(test2);

        tesztvilag.getGombaszok().add(jatekos);
        tesztvilag.getGombaszok().add(jatekos2);

	    tesztvilag.jelenlegiJatekos().korElejeInicializalas();

        tesztvilag = new Jatek();
        return tesztvilag;
    }
    
    public static Jatek egyFonalasHonnan() {
        tesztvilag = new Jatek();
        Tekton t1 = new Egyfonalas();
        Tekton t2 = new Egyfonalas();
        Tekton t3 = new Tekton();

        tesztvilag.getJatekter().tektonHozzaad(t1);
        tesztvilag.getJatekter().tektonHozzaad(t2);
        tesztvilag.getJatekter().tektonHozzaad(t3);
        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        t1.addSzomszed(t3);
        t3.addSzomszed(t1);
        t2.addSzomszed(t3);
        t3.addSzomszed(t2);
        Gombasz jatekos = new Gombasz(tesztvilag.getJatekter().getPalya());

        Fonal fon10 = new Fonal(t2,jatekos);
        Fonal fon11 = new Fonal(t1,jatekos);
        t1.addFonal(fon10);
        t2.addFonal(fon11);

        Gombatest test = new Gombatest(t1,jatekos);
        jatekos.gombatestHozzaad(test);
        t1.setGombatest(test);

        tesztvilag.getGombaszok().add(jatekos);

	    tesztvilag.jelenlegiJatekos().korElejeInicializalas();

        return tesztvilag;
    }
    
    public static Jatek sporaSzorSima() {
        tesztvilag = new Jatek();
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        
        tesztvilag.getJatekter().tektonHozzaad(t2);
        tesztvilag.getJatekter().tektonHozzaad(t1);

        Gombasz jatekos = new Gombasz(tesztvilag.getJatekter().getPalya());
        Gombatest test = new Gombatest(t1,jatekos);
        jatekos.gombatestHozzaad(test);
        t1.setGombatest(test);

        tesztvilag.getGombaszok().add(jatekos);

        tesztvilag.jelenlegiJatekos().korElejeInicializalas();
        return tesztvilag;
    }
    
    public static Jatek sporaSzorFejlett() {
        tesztvilag = new Jatek();
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        Tekton t3 = new Tekton();
        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        t3.addSzomszed(t2);
        t2.addSzomszed(t3);

        tesztvilag.getJatekter().tektonHozzaad(t1);
        tesztvilag.getJatekter().tektonHozzaad(t2);
        tesztvilag.getJatekter().tektonHozzaad(t3);

        Gombasz jatekos = new Gombasz(tesztvilag.getJatekter().getPalya());
        Gombatest test = new Gombatest(t1,jatekos);
	    test.setMaradt(2);
        jatekos.gombatestHozzaad(test);
        t1.setGombatest(test);

	    tesztvilag.getGombaszok().add(jatekos);
	
        tesztvilag.jelenlegiJatekos().korElejeInicializalas();
        return tesztvilag;
    }
    
    public static Jatek gombatestEpitFonallal() {
        tesztvilag = new Jatek();
        return tesztvilag;
    }
    
    public static Jatek gombatestEpitSporaval() {
        tesztvilag = new Jatek();

        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        
        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
       
        tesztvilag.getJatekter().tektonHozzaad(t1);
        tesztvilag.getJatekter().tektonHozzaad(t2);

        Gombasz jatekos = new Gombasz(tesztvilag.getJatekter().getPalya());

        Gombatest test = new Gombatest(t1,jatekos);
        jatekos.gombatestHozzaad(test);
        t1.setGombatest(test);

        Lassito s1 = new Lassito(jatekos);
        s1.setTartozik(jatekos);
        t2.addSpora(s1);

        Lassito s2 = new Lassito(jatekos);
        s2.setTartozik(jatekos);
        t2.addSpora(s2);

        Lassito s3 = new Lassito(jatekos);
        s3.setTartozik(jatekos);
        t2.addSpora(s3);

        Lassito s4 = new Lassito(jatekos);
        s4.setTartozik(jatekos);
        t2.addSpora(s4);

        Lassito s5 = new Lassito(jatekos);
        s5.setTartozik(jatekos);
        t2.addSpora(s5);

        tesztvilag.getGombaszok().add(jatekos);

        tesztvilag.jelenlegiJatekos().korElejeInicializalas();

        return tesztvilag;
    }
    
    public static Jatek gombatestMeghal() {
        tesztvilag = new Jatek();
        return tesztvilag;
    }
    
    public static Jatek fonalElhalGombatestMiatt() {
        tesztvilag = new Jatek();
        return tesztvilag;
    }
    
    public static Jatek fonalEvesBogar() {
        tesztvilag = new Jatek();
        return tesztvilag;
    }
    
    public static Jatek fonalElhalSzetesesMiatt() {
        tesztvilag = new Jatek();
        return tesztvilag;
    }
    
    public static Jatek gombatestEpitTestetlenre() {
        tesztvilag = new Jatek();
        return tesztvilag;
    }
    
    public static Jatek fonalElhalBogarMiatt() {
        tesztvilag = new Jatek();
        return tesztvilag;
    }
    
    public static Jatek eletbenTart() {
        tesztvilag = new Jatek();
        return tesztvilag;
    }
    
    public static Jatek tektonSzetesesFonalNelkul() {
        tesztvilag = new Jatek();
        return tesztvilag;
    }
    
    public static Jatek fonalFelsziv() {
        tesztvilag = new Jatek();
        return tesztvilag;
    }
}
    
