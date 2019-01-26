/**************************************************************************
*                                                                         *
*     Program Filename:  Khan13C.java                                     *
*     Author          :  Sarim Khan    	                                  *
*     Date Written    :  October 31st, 2017    	                          *
*     Purpose         :  Find palindromes and provide minimal cuts        *
*     Input from      :  Keyboard                                         *
*     Output to       :  Screen                                           *
*                                                                         *
**************************************************************************/
import java.util.Scanner;
import java.io.*; 
import java.util.ArrayList;
import java.util.List;
public class Khan13C
{            
    public static void main(String args[])
    {
       String str;
       Scanner Scan = new Scanner(System.in); 
       int i, j, k, l; 
       
       System.out.println("Please enter the character string you would like to evaluate: "); 
       str = Scan.nextLine(); 
       str = str.toUpperCase(); 
       
       MinimalCuts(str);                
    }//end main 
    
    static int MinimalCuts(String str)
    {
        boolean[][] PalindromeMatrix = FindPalindromes(str);
        int N = str.length();
       
        ArrayList<ArrayList<Integer>> PalindromeList = new ArrayList<ArrayList<Integer>>(N);
        
        for (int i=0; i<N; i++)
            PalindromeList.add(new ArrayList<Integer>());
 
        PalindromeList.get(N-1).add(N-1);
 
        for(int i = N-2; i >= 0; i--)
        {
            if(PalindromeMatrix[i][N-1])
            {
                PalindromeList.get(i).clear();
                PalindromeList.get(i).add(i);
            }//end if 
            else
            {
                for(int k = N-2; k >= i; k--)
                {
                    if(PalindromeMatrix[i][k])
                    {
                        int PalindromeCount = 1 + PalindromeList.get(k+1).size(); 
 
                        if (PalindromeList.get(i).isEmpty() ||
                            PalindromeCount < PalindromeList.get(i).size())
                        {
                            ArrayList<Integer> PA2 = PalindromeList.get(i);
                            PA2.clear();
                            PA2.add(0, i);
                            PA2.addAll(PalindromeList.get(k+1));
                        } //end if 
                    } //end if 
                } //end for 
            } //end else if 
        }//end for 
 
        PrintPalindromes(str, PalindromeList);
        return (PalindromeList.get(0).size() - 1);
    }
    //end MinimalCuts
    //calculate minimal number of cuts and print the cuts 

    private static boolean[][] FindPalindromes(String str)
    {
        int N = str.length();
        boolean IsPalindrome[][] = new boolean[N][N];
 
        for (int i = N-1; i >= 0; i--)
        {
            for (int j = i; j < N; j++)
            {
                if (i== j)
                {
                    IsPalindrome[i][j] = true;
                }//end if 
                else if(str.charAt(i) != str.charAt(j))
                {
                    IsPalindrome[i][j] = false;
                }//end else if 
                else
                {
                    IsPalindrome[i][j] = ((j-i == 1)?true:IsPalindrome[i+1][j-1]);
                } //end else 
            }//end for 
        }//end for 
        return IsPalindrome;
    }//end FindPalindromes

    static void PrintPalindromes(String str, ArrayList<ArrayList<Integer>> Palindromes)
    {
        int minimalCuts = 0; 
        List<Integer> indices = Palindromes.get(0);
        int i;
        for (i=0; i<indices.size()-1; i++)
        {
            minimalCuts++; 
            int begin = indices.get(i);
            int end = indices.get(i+1);
            System.out.print(str.substring(begin, end) + "|");
        }//end for 
        int begin = indices.get(i);
        System.out.println(str.substring(begin));
        
        System.out.println("The minimal number of cuts is " + minimalCuts); 
    }    //end printPalindromes. 
}//end class 
