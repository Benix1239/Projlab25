public class Tesztpalya 
{
    static Jatek tesztvilag;

    void bogarLep()
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
    }

    void bogarEszik()
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
    }

    void bogarNemEszik()
    {
        Tekton t1 = new Tekton();
        tesztvilag.getJatekter().tektonHozzaad(t1);

        Bogarasz karakter =new Bogarasz();
        Bogar bogar = new Bogar();
        karakter.bogarHozzaad(bogar, t1);
        tesztvilag.getBogaraszok().add(karakter);

        Gombasz jatekos = new Gombasz(tesztvilag.getJatekter().getPalya());
        tesztvilag.getGombaszok().add(jatekos);
    }

    void bogarRagas()
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
    }

    void bogarNemRagas()
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
    }

    void bogarEmeszt(String fajta)
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
    
