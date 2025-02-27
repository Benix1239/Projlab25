import java.util.Random;

public class App 
{
    public static void main(String[] args) throws Exception 
    {
    
        Tekton[][] palya = new Tekton[32][32];

        Random rnd = new Random();

        
            for(int i = 0; i < 31; i++)
            {
                for(int j = 0; j < 31; j++)
                {
                    palya[i][j] = new Tekton();
                    if(rnd.nextInt(100) < 20)
                    {
                        palya[i][j].SetTipus(true);
                        
                    }            
                }
            }
        
        /*
        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j < 10; j++)
            {
                
                if(palya[i][j].GetTipus())
                {
                    
                    System.out.print("1 ");
                }
                else
                {
                    System.out.print("0 ");
                }
                
            }
            System.out.println();
        }
        */
        int iter = 0;

        while(true)
        {

            System.out.println("iteracio: " + iter);
            iter++;
            for(int i = 1; i < 30; i++)
            {
                for(int j = 1; j < 30; j++)
                {
                    
                    if(palya[i][j].GetTipus())
                    {
                        
                        System.out.print("1 ");
                    }
                    else
                    {
                        System.out.print("0 ");
                    } 
                }
                System.out.println();
            }

            for(int i = 0; i < 10000000; i++){
                System.out.print("");
            }

            for(int i = 1; i < 30; i++)
            {
                for(int j = 1; j < 30; j++)
                {
                    palya[i][j].NovelKor(1);

                    if(palya[i][j].Szeteses() && palya[i][j].GetTipus())
                    {
                        if(!keresszomszed(i,j,palya))
                        {
                            palya[i][j].SetTipus(false);
                        }
                    }
                    
                }
                
            }
            System.out.println("------------------");

        }

    }

    static boolean keresszomszed(int x, int y, Tekton[][] palya)
    {
        
        for(int i = x-1; i <= x+1; i++)
        {
            for(int j = y+1; j >= y-1; j--)
            {
                if(!palya[i][j].GetTipus())
                {
                    palya[i][j].SetTipus(true);
                    palya[i][j].SetKor(0);
                    palya[x][y].SetKor(0);
                    return true;
                }
            }
        }
        palya[x][y].SetKor(90);
        return false;
    }

}
