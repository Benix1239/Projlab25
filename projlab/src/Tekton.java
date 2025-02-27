import java.util.Random;

public class Tekton 
{

    Boolean tipus; //true = szilard, False = viz
    int kor;
    String attributum; //PH tipus, speckó effektje a tektonnak

    Tekton()
    {
        tipus = false;
        kor = 0;
    }

    void SetTipus(Boolean NTipus)
    {
        this.tipus = NTipus;
    }

    Boolean GetTipus()
    {
        return this.tipus;
    }

    void SetKor(int NKor)
    {
        this.kor = NKor;
    }

    int GetKor()
    {
        return this.kor;
    }

    void NovelKor(int N)
    {
        this.kor += N;
    }

    Boolean Szeteses()
    {
        Random rnd = new Random();
        if(rnd.nextInt(100) < this.GetKor())
        {
            return true;
        }
        return false;
    }

    
}
