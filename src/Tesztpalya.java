public class Tesztpalya 
{
   public  static Jatek tesztvilag = new Jatek();

    public static Jatek bogarLep()
    {
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
        tesztvilag.getGombaszok().add(jatekos);

        return tesztvilag;
    }

    public static Jatek bogarEmeszt(String fajta)
    {
        Tekton t1;
        Bogarasz karakter;
        Bogar bogar;
        Gombasz jatekos;
        Spora spora;
        switch (fajta) {
            case "gyorsito":
                t1 = new Tekton();
                tesztvilag.getJatekter().tektonHozzaad(t1);
        
                karakter =new Bogarasz();
                bogar = new Bogar();
                karakter.bogarHozzaad(bogar, t1);
                tesztvilag.getBogaraszok().add(karakter);
        
                jatekos = new Gombasz(tesztvilag.getJatekter().getPalya());
                spora= new Gyorsito(jatekos);
                t1.addSpora(spora);
                tesztvilag.getGombaszok().add(jatekos);
                break;
            case "benito":
                t1 = new Tekton();
                tesztvilag.getJatekter().tektonHozzaad(t1);
        
                karakter =new Bogarasz();
                bogar = new Bogar();
                karakter.bogarHozzaad(bogar, t1);
                tesztvilag.getBogaraszok().add(karakter);
        
                jatekos = new Gombasz(tesztvilag.getJatekter().getPalya());
                spora= new Benito(jatekos);
                t1.addSpora(spora);
                tesztvilag.getGombaszok().add(jatekos);
                break;
            case "keseru":
                t1 = new Tekton();
                tesztvilag.getJatekter().tektonHozzaad(t1);
        
                karakter =new Bogarasz();
                bogar = new Bogar();
                karakter.bogarHozzaad(bogar, t1);
                tesztvilag.getBogaraszok().add(karakter);
        
                jatekos = new Gombasz(tesztvilag.getJatekter().getPalya());
                spora= new Keseru(jatekos);
                t1.addSpora(spora);
                tesztvilag.getGombaszok().add(jatekos);
                break;
            case "lassito":
                t1 = new Tekton();
                tesztvilag.getJatekter().tektonHozzaad(t1);
        
                karakter =new Bogarasz();
                bogar = new Bogar();
                karakter.bogarHozzaad(bogar, t1);
                tesztvilag.getBogaraszok().add(karakter);
        
                jatekos = new Gombasz(tesztvilag.getJatekter().getPalya());
                spora= new Lassito(jatekos);
                t1.addSpora(spora);
                tesztvilag.getGombaszok().add(jatekos);
                break;
            case "szaporodo":
                t1 = new Tekton();
                tesztvilag.getJatekter().tektonHozzaad(t1);
        
                karakter =new Bogarasz();
                bogar = new Bogar();
                karakter.bogarHozzaad(bogar, t1);
                tesztvilag.getBogaraszok().add(karakter);
        
                jatekos = new Gombasz(tesztvilag.getJatekter().getPalya());
                spora= new Szaporodo(jatekos);
                t1.addSpora(spora);
                tesztvilag.getGombaszok().add(jatekos);
                break;
            default:
            
        }
        return tesztvilag;
    }
    
    public static Jatek fonalLerakSima() {
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

        return tesztvilag;
    }
    
    public static Jatek egyFonalasHonnan() {
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
        return tesztvilag;
    }
    
    public static Jatek gombatestEpitSporaval() {
        return tesztvilag;
    }
    
    public static Jatek gombatestMeghal() {
        return tesztvilag;
    }
    
    public static Jatek fonalElhalGombatestMiatt() {
        return tesztvilag;
    }
    
    public static Jatek fonalEvesBogar() {
        return tesztvilag;
    }
    
    public static Jatek fonalElhalSzetesesMiatt() {
        return tesztvilag;
    }
    
    public static Jatek gombatestEpitTestetlenre() {
        return tesztvilag;
    }
    
    public static Jatek fonalElhalBogarMiatt() {
        return tesztvilag;
    }
    
    public static Jatek eletbenTart() {
        return tesztvilag;
    }
    
    public static Jatek tektonSzetesesFonalNelkul() {
        return tesztvilag;
    }
    
    public static Jatek fonalFelsziv() {
        return tesztvilag;
    }
}
    
