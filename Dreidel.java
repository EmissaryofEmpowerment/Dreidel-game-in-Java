import java.util.*;
import java.awt.*;
import java.io.*;

class Dreidel
{
   private int potAmount;
   private String [] hebrewLetters;
   private int dieRoll;
   private Random r;
  // private BufferedImage img = null;

   Dreidel ()
   {
      potAmount = 0;
      dieRoll = 0;
      r = new Random();
      hebrewLetters = new String [4];
      hebrewLetters[0] = "Hey";
      hebrewLetters[1] =  "Nun"; 
      hebrewLetters[2] = "Shin";
      hebrewLetters[3] = "Gimel";
  /*    try
      {
         img = ImageIO.read("shin.png");
      }
      catch (IOException e)
      {
         System.out.println("Input/Output Error: " + e.getmessage);
      }*/
   }
   
   public String spinDreidel()
   {
      dieRoll = r.nextInt(4);
      return hebrewLetters[dieRoll];
   }
   
   public int getPotAmount()
   {
      return potAmount;
   }
   
   public void receiveToken()
   {
      potAmount++; 
   }
   
   public void giveWinnings(int mathVariable)
   {
      if (potAmount >= mathVariable)
      {
      potAmount -= mathVariable;
      }
   }
}