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
        return tesztvilag;
    }
    
    public static Jatek egyFonalasHonnan() {
        tesztvilag = new Jatek();
        return tesztvilag;
    }
    
    public static Jatek egyFonalasHova() {
        tesztvilag = new Jatek();
        return tesztvilag;
    }
    
    public static Jatek sporaSzorSima() {
        tesztvilag = new Jatek();
        return tesztvilag;
    }
    
    public static Jatek sporaSzorFejlett() {
        tesztvilag = new Jatek();
        return tesztvilag;
    }
    
    public static Jatek gombatestEpitFonallal() {
        tesztvilag = new Jatek();
        return tesztvilag;
    }
    
    public static Jatek gombatestEpitSporaval() {
        tesztvilag = new Jatek();
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
    
