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
        Fonal fon10 = new Fonal(t2,jatekos);
        Fonal fon11 = new Fonal(t1,jatekos);
        t2.addFonal(fon11);
        t1.addFonal(fon10);
        Fonal fon20 = new Fonal(t2,jatekos);
        Fonal fon21 = new Fonal(t1,jatekos);
        t2.addFonal(fon21);
        t1.addFonal(fon20);
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
        return tesztvilag;
    }
    
    public static Jatek egyFonalasHonnan() {
        return tesztvilag;
    }
    
    public static Jatek egyFonalasHova() {
        return tesztvilag;
    }
    
    public static Jatek sporaSzorSima() {
        return tesztvilag;
    }
    
    public static Jatek sporaSzorFejlett() {
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
    
