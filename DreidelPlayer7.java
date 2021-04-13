import java.util.*;

class DreidelPlayer
{
   private Scanner in;
   private String name;
   private int numTokens;
   private boolean playerOutOfGame; 
   
   DreidelPlayer()
   {
      in = new Scanner(System.in);
      name = " ";
      numTokens = 0;
      playerOutOfGame = false;
      numTokens = 10;
      setName();   

   }
   
   public void setName()
   {
      this.name = in.nextLine();
   }
   
   public String getName()
   {
      return this.name;
   }
   
   public void receiveTokens(int numTokensWon)
   {
      this.numTokens += numTokensWon;
   }
   
   public void loseOrAnteToken()
   {  //instead of creating a new DreidelPlayer[] to cut out players who lost all tokens, we will ignore that this player exists anymore
      if (this.playerOutOfGame == false) 
      {  
         if (this.numTokens <= 0) //Also, we can't have a negative amount of tokens
         {
            this.playerOutOfGame = true;
         }
         else if (this.numTokens > 0)
         { 
            this.numTokens--;
         }
      }
   }
   
   public int getNumTokens()
   {
      return this.numTokens;
   }
    
   public boolean IsPlayerOut()
   {
      return this.playerOutOfGame;
   } 
   
   public void YouLose()
   {
      this.playerOutOfGame = true;
      System.out.println(this.name + " is out of the game.");
   }  
}