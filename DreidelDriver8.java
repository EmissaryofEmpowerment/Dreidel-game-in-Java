import java.util.*;
class DreidelDriver
{
   public static void main (String []args)
   {
      Scanner in = new Scanner(System.in);
      String userInput = new String();
      int numPlayers = 0;
      int roundNumber = 0;
      System.out.println("Welcome to the Dreidel game! " +
      " In this famous game played during Chanukah, all players start each round by adding one token to the \n middle (aka pot) " +
      " and then spin the Dreidel and may add tokens to the pot or take tokens from the pot depending on the Dreidel roll. \n" +
      "Are you ready to play Dreidel?\n" +
       "Press 'Y' then Press 'Enter' to continue or Press 'N' or 'Q', then 'Enter' to quit.");
      userInput = in.next();
      
      //Take it away subMain(), it's your show from here on out :)
      //recursion is my friend
      subMain(in, userInput, numPlayers, roundNumber);
      
   }
   
   public static void subMain(Scanner in, String userInput, int numPlayers, int roundNumber)
   {
      //all the player specific variables and methods will be contained in the DreidelPlayerClass
      
      try
      {  
         if (userInput.equalsIgnoreCase("Q") || userInput.equalsIgnoreCase("N"))
         {
            try
            {
               System.exit(1);
            }
            catch (Exception e)
            {
               System.exit(0);
            }
         }
         else if (userInput.equalsIgnoreCase("Y"))
         { // begin else if (userInput.equalsIgnoreCase("Y"
 //this if statement is to account for non-integers being entered for number of players (Line 58)
 //If numPlayers == 6755 that means that the user failed to enter a number and got recursioned to here
 
            if (numPlayers == 6755)
            {
               numPlayers = in.nextInt();   // this if statement corresponds to the catch block on Line 120
            }
            //rather than write the rest of the DreidelDriver program in this else if (userInput.equalsIgnoreCase("Y")
            // I elected to let the loop terminate, then call the GeneratePlayers() method on Line 50
            else if (numPlayers!= 6755)
            {
            in.nextLine();  //Flush the buffer
            System.out.println("Please enter the number of players, followed by the 'Enter' key");
            numPlayers = in.nextInt();
            }
            //recursion is my friend; keep giving the user a chance to enter one of the correct characters
         }  // end else if (userInput.equalsIgnoreCase("Y")
         else
         {
            in.nextLine(); //Flush the buffer
            System.out.println("That is not a valid entry, Please press Y, Q, or N followed by the 'Enter' key");
            userInput = in.next();
            //recursion is my friend
            subMain(in, userInput, numPlayers, roundNumber);
         }

            
      
// HERE IS THE BULK OF THE SUBMAIN METHOD; THIS CODE IS GENERIC TO EACH ITERATION OF THE DREIDEL GAME
// I DECLARED variables that are relevant to the whole DreidelDriver class in the main method, then passed them as arguments to subMain()   
      DreidelPlayer[] players = new DreidelPlayer[numPlayers];
      players = GeneratePlayers(players);
   
      //start the game using Dreidel class
      Dreidel dreidel = new Dreidel();
      
      // playRound will contain the logic directly based on the rules of Dreidel
      //I decided to use iteration instead of recursion because iteration is also my friend
      do
      {
         roundNumber++;
         playRound(players, dreidel, roundNumber, userInput, in);
         in.nextLine(); //Flush Buffer
         System.out.println("Would you like to continue: Press 'Y' for yes 'N' for no or 'G' for game to start a new game");
         userInput = in.next();   
         while (!userInput.equalsIgnoreCase("Y") && !userInput.equalsIgnoreCase("N") && !userInput.equalsIgnoreCase("G"))
         {
            in.nextLine(); //Flush buffer2
            System.out.println("Sorry, " + userInput + " is not a valid entry. Please press Y, N, or G then press Enter");
            userInput = in.next();   
         }
      }
      while (userInput.equalsIgnoreCase("Y"));
      if (userInput.equalsIgnoreCase("N"))
      {
         try
         {
            System.exit(1);
         }
         catch (Exception e)
         {
            System.exit(0);
         } 
      }
      else if (userInput.equalsIgnoreCase("G"))
      {
         //recursion is my friend
         roundNumber = 0;  //to restart the game
         userInput = "Y"; //Counterintuitive to assign userInput the value 'Y', but it does effectively allow a user to start a new game
         System.out.println("\nWelcome to the Dreidel game!\n");
         subMain(in, userInput, numPlayers, roundNumber);
      }
     } // end of main try block in subMain
     
      catch (InputMismatchException e)
      {
         in.nextLine();  //Flush the buffer
         System.out.println("That entry is " + e.getMessage() + " and void. Please Enter a number followed by the 'Enter' key.\n");
         userInput = "Y";
         numPlayers = 6755;
         subMain(in, userInput, numPlayers, roundNumber);   //This will lead to and if statement on Line 40
         //It seems like this catch is only triggered from the user entering something besides a number
      }
      catch (Exception e)
      {
         System.out.println("NonSpecific Error subMain: " + e.getMessage());
         userInput = "Y";
         numPlayers = 6755;
         Dreidel dreidel = new Dreidel(); 
         DreidelPlayer [] players = new DreidelPlayer [numPlayers];  //The program can't find these, even though they are declared in this method on Line 76
         players = GeneratePlayers(players); //So, I'll make some new ones
         playRound(players, dreidel, roundNumber, userInput, in);
         //It seems like this catch block gets triggered when the user enters something besides Y, N, or G (Line 88)
      }
   }
   
   public static DreidelPlayer[] GeneratePlayers(DreidelPlayer[] players)
   {   // public static DreidelPlayer[] GeneratePlayers
      try
      {         
         
         for (int i = 0; i < players.length; i++)
         {
            System.out.println("Please Enter a name for Player " + (i + 1) + ":");         
            players[i] = new DreidelPlayer();
         }
         
         return players;
      }
      catch (InputMismatchException e)
      {
         System.out.println("InputMisMatch Exception: " + e.getMessage());
         return players;
      }
      catch (Exception e)
      {
         System.out.println("NonSpecific Error from GeneratePlayers(): " + e.getMessage());
         return players;
      }
   }  // end method generateplayers
   
   public static void playRound(DreidelPlayer[] players, Dreidel dreidel, int roundNumber, String userInput, Scanner in)
   {
      int mathVariable = 0;
      try
      {  // begin try block
         //Dreidel, dreidel, dreidel; I made you out of java...
         
         System.out.println("\nRound number " + roundNumber + ": ");
         boolean nextRound = true;
         AnteUp(players, dreidel, nextRound);//Ante up 
         System.out.println("Pot: " + dreidel.getPotAmount() + "\n");
         
         for (int i = 0; i < players.length; i++)
         {  // begin player loop
            //rather than deleting the player from the DreidelPlayer[], the program will just pretend she/he doesn't exist
            if (players[i].IsPlayerOut() == false)
            {   //if player is still in the game
               System.out.println(players[i].getName() + "'s turn: Enter a character (number, letter, or symbol) and hit 'Enter' to Spin the Dreidel");
               //The game is a lot funner if each person gets to spin the dreidel on her/his turn
               in.next(); //The whole point of this line of code is to pause the program until the user spins the dreidel
               while(userInput== " ")
               {
                  in.nextLine();
                  userInput = in.nextLine();
               }
               userInput = dreidel.spinDreidel();
               mathVariable = dreidel.getPotAmount();
               System.out.println("You rolled: " + userInput + "!");
                              
               switch (userInput)
               { //start switch (userInput)
                   case "Shin":
                   System.out.println("You lose one token!");
                   if (players[i].getNumTokens() <= 0)
                   {
                        players[i].YouLose();                  
                   }
                   else if (players[i].getNumTokens() > 0)
                   {
                     players[i].loseOrAnteToken();
                     dreidel.receiveToken();

                   }
                   break;
                   
                   case "Hey":
                   System.out.println("You win half the pot!");
                   if (mathVariable % 2 == 0)
                   {
                     players[i].receiveTokens(mathVariable /2);
                     dreidel.giveWinnings(mathVariable /2);
                   }
                   else if (mathVariable % 2 == 1)
                   {
                     mathVariable ++;
                     players[i].receiveTokens(mathVariable /2);
                     dreidel.giveWinnings(mathVariable /2);
                   }
                   break;
                   
                   case "Gimel":
                   System.out.println("You win the whole pot!");
                   players[i].receiveTokens(mathVariable);
                   dreidel.giveWinnings(mathVariable);
                   mathVariable = dreidel.getPotAmount();
                   System.out.println(players[i].getName() + " has " + players[i].getNumTokens());
                   System.out.println("Pot: " + mathVariable);  //should be 0
                   if ((i + 1) != (players.length)) // We only need to Ante Up if this is not the last player
                   {    // if it is the last player, we can wait for the Ante at the start of the next round
                        nextRound = false;
                       AnteUp(players, dreidel, nextRound);
                   }  // end if (It's not the last player in the round)
                   break;
                   
                   case "Nun":
                   System.out.println("Nothing happens.");
                   break;
                   
                   default:
                   System.out.println("Error: If you're reading this, the input from the Dreidel was neither \"Hey\", \"Shin\", \"Nun\", nor \"Gimel\". \n " +
                   "It should be one of those 4.");
                   break;
                   
               } //end switch (userInput)
               
                //rather than deleting a player who ran out of tokens from the DreidelPlayer[], the program will just pretend she/he doesn't exist
                if (players[i].getNumTokens() <= 0)
                {
                  players[i].YouLose();
                }
                else if (players[i].getNumTokens() > 0)
                {
                  System.out.println(players[i].getName() + " has " + players[i].getNumTokens() + " tokens.");
                }
               System.out.println("Pot: " + dreidel.getPotAmount() + "\n");
             } //end if (player's actually there)
         }// end players[] loop 

      } //end try block
      catch (Exception e)
      {
         System.out.println("NonSpecific Error from the playRound method: " + e.getMessage());
      }
    }  //end playRound() Method
    
    public static void AnteUp(DreidelPlayer[] players, Dreidel dreidel, boolean nextRound)
    {
      System.out.println("Time to Ante Up! \n " +
         "Every player will add 1 token to the pot.");
         int endGame = 0;
         for (int i = 0; i < players.length; i++)
         {  // for players loop
            if (players[i].IsPlayerOut() == false)
            {
               players[i].loseOrAnteToken();
               dreidel.receiveToken();
               if (nextRound == true)
               {
                  System.out.println(players[i].getName() + " starts the round with " + players[i].getNumTokens() + " tokens.");
               }
            }
            else if (players[i].IsPlayerOut() == true)
            {    // begin if (playerIsOut == true)
               endGame ++;
               
               if (endGame >= (players.length-1))  //because endGame starts at 1 (in the playRound method) and because
               {                                     // players.length starts at 0, when they match, there is only one player left
                  System.out.println("We have a winner! Game Over!");
                  try
                  {
                     System.exit(1);
                  }
                  catch (Exception e)
                  {
                     System.exit(0);
                  }
               }
            }  // end if (IsPlayerOut() == true)
         }  //end for (players) loop

    }
  } // end class DreidelDriver