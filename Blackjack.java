import java.util.InputMismatchException;
import java.util.Scanner;

public class Blackjack
{
    public static void main(String args[]) {
        //Defining all your variables before you start using them is 9
        // always a good idea. Some require values to be defined immediately, others don't require values immediately.
        P1Random rand = new P1Random();
        Scanner scnr = new Scanner(System.in);
        int gameNumber = 1;
        int card;
        int userScore = 0;
        int aiScore;
        int input = 0;
        int userWins = 0;
        int aiWins = 0;
        int ties = 0;
        int winner = 0;
        double playerPercentage;
        double totalGames;

        while (input != 4) {
            //This if statement runs every time a new game is started, and only runs the first time the program is started and after a winner is decided.
            if (input == 0) {
                System.out.println("START GAME #" + gameNumber);
                userScore = 0; //Resets the users score every time a new game is started.
                winner = 0; //Resets who the winner is every time a new game is started, with 1 being the player, 2 being the dealer, and 3 being a tie.
                input = 1; //Directs the program to deal the player their first card at the start of the game.
            }

            //This if statement will run if the player chooses to take another card, and will automatically end the game if the player either scores 21 or goes over 21.
            else if (input == 1) {
                card = rand.nextInt(13) + 1;
                if (card == 1) System.out.println("\nYour card is a ACE!");
                else if (card > 1 && card < 11) System.out.println("\nYour card is a " + card + "!");
                else if (card == 11) {
                    System.out.println("\nYour card is a JACK!");
                    card = 10; //Must have because otherwise a jack would count as 11 towards a player's score, same with queens or kings.
                } else if (card == 12) {
                    System.out.println("\nYour card is a QUEEN!");
                    card = 10;
                } else if (card == 13) {
                    System.out.println("\nYour card is a KING!");
                    card = 10;
                }

                userScore += card;
                System.out.println("Your hand is: " + userScore);
                input = 5;

                if (userScore == 21) {
                    System.out.println("\nBLACKJACK! You win!\n");
                    userWins++;
                    gameNumber++;
                    input = 0; //This skips the rest of the if statements, and diverts to the first loop, restarting the game.
                } else if (userScore > 21) {
                    System.out.println("\nYou exceeded 21! You lose.\n");
                    aiWins++;
                    gameNumber++;
                    input = 0; //^^^^
                }
            }

            //If the player chooses to hold their hand, this statement runs, and it determines what the dealers score is, and then determines a winner before restarting the game.
            else if (input == 2) {
                aiScore = rand.nextInt(11) + 16;
                System.out.println("\nDealer's hand: " + aiScore);
                System.out.println("Your hand is: " + userScore);
                if (aiScore > 21) winner = 1;
                else if (aiScore <= 21) {
                    if (aiScore > userScore) winner = 2;
                    else if (aiScore < userScore) winner = 1;
                    else if (aiScore == userScore) winner = 3;
                }
                if (winner == 1) {
                    System.out.println("\nYou win!\n");
                    userWins++;
                } else if (winner == 2) {
                    System.out.println("\nDealer wins!\n");
                    aiWins++;
                } else if (winner == 3) {
                    System.out.println("\nIt's a tie! No one wins!\n");
                    ties++;
                }
                gameNumber++;
                input = 0; //Redirects to the game start statement.
            }

            //This if statement prints the game statistics
            else if (input == 3) {
                System.out.println("\nNumber of Player wins: " + userWins);
                System.out.println("Number of Dealer wins: " + aiWins);
                System.out.println("Number of tie games: " + ties);
                totalGames = userWins + aiWins + ties;
                int games = (int) totalGames;
                System.out.println("Total # of games played is: " + games);
                playerPercentage = (userWins / totalGames) * 100;
                System.out.print("Percentage of Player wins: ");
                System.out.format("%.1f", playerPercentage); //This rounds the double value playerPercentage to one decimal point.
                System.out.println("%");
                input = 5;
            }

            //If the user chooses to quit, this statement simply exits out of the original while loop, killing the program. It is not entirely necessary, as the while loop would terminate anyways since input = 4, but makes the code more readable.
            else if (input == 4) break;

                //The user does not have the option to input 5, so the user can never force this if statement to run. Other parts of the program set the value of input to 5 in order to bring up the menu when appropriate.
            else if (input == 5) {
                System.out.print("\n1. Get another card\n2. Hold hand\n3. Print statistics\n4. Exit\n\nChoose an option: ");
                try
                {
                    input = scnr.nextInt();
                }
                catch(InputMismatchException e)
                {
                    System.out.println("System error, you did not enter an integer.");
                    System.exit(1);
                }
                if (input > 4 || input < 1) //This prevents the user from entering an invalid input, and simply diverts them right back to the start of this statement if they do.
                {
                    System.out.println("\nInvalid input!\nPlease enter an integer value between 1 and 4.");
                    input = 5;
                }
            }
        }
    }
}
