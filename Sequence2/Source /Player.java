import java.util.*;

/**
 *creates a player object which controls the
 *players hand, a game board that only controls their moves,
 *and their score
 *
 *  @author reinapradhan
 *  @version May 21, 2021
 */
public class Player
{

    private Card[][] playerBoard;
    private ArrayList<Card> hand;

    /**
     * Create a new Player object.
     * @param hand of 5 cards
     * @param gameBoard
     */
    public Player(ArrayList<Card> hand, Card[][] gameBoard)
    {
        this.hand = hand;
        playerBoard = gameBoard;

    }

    /**
     * returns player's hand
     * @return hand
     */
    public ArrayList<Card> getHand()
    {
        return hand;
    }

    /**
     * Returns players board
     * @return playerBoard
     */
    public Card[][] getBoard()
    {
        return playerBoard;
    }


    /**
     * fills up the corresponding spot in the player's game board
     * and removes a card from queue that the player chooses to play
     * @param card
     * @param row
     * @param col
     */
    public void playCard(Card card, int row, int col)
    {

            playerBoard[row][col].fill();
            hand.remove(card);

    }

   /* public void playCard(Card jack, int r, int c )
    {
        if (jack.isJack())
        {
            playerBoard[r][c].fill();
        }

    }*/

    /**
     *adds a card to hand
     * @param card to be added
     */
    public void  replaceCard(Card card)
    {

        hand.add(card);
    }

    /**
     * returns the score of the player
     * @return score
     */
    public int getScore()
    {
        int score = 0;

        //rows
        for (int r = 0; r < playerBoard.length; r++)
        {
            int countR = 0;

            for (int c = 0; c < playerBoard[r].length; c++)
            {
                Card cardR = playerBoard[r][c];

                if (!cardR.isEmpty())
                {
                    countR ++;
                }

                else
                {
                    countR = 0;
                }

                if (countR == 5)
                {
                    score++;
                    countR = 0;
                }
            }
        }

        //columns
        for (int c = 0; c < playerBoard[0].length; c++)
        {
            int countC = 0;

            for(int r = 0; r < playerBoard.length; r++)
            {
                Card cardC = playerBoard[r][c];

                if (!cardC.isEmpty())
                {
                    countC ++;
                }

                else
                {
                    countC = 0;
                }

                if (countC == 5)
                {
                    score++;
                    countC = 0;
                }
            }
        }

        //diagonals
       /* for (int r = 0; r < playerBoard.length; r++)
        {
            int countD = 0;

            Card cardD = playerBoard[r][r];


            if (!cardD.isEmpty())
            {
                countD ++;
            }

            else
            {
                countD = 0;
            }

            if (countD== 5)
            {
                score++;
                countD = 0;
            }
        }*/

        //diagonals V2
        for (int r = 0; r < playerBoard.length; r++)
        {
            int countDR = 0;

            int i = r;
            int j = 0;

            while (i >= 0)
            {
                if (!playerBoard[i][j].isEmpty())
                {
                    countDR++;
                }

                else
                {
                    countDR = 0;
                }

                if (countDR == 5)
                {
                    score++;
                }

                i--;
                j++;
            }
        }

      /* for (int d = 1; d < playerBoard[0].length - 1; d++)
        {
            int countDC = 0;

            int i = playerBoard[0].length - 1;
            int j = d;

            while (j <  playerBoard[0].length && i >= 0)
            {

                    if (!playerBoard[i][j].isEmpty())
                    {
                        countDC++;
                    }

                    else
                    {
                        countDC = 0;
                    }

                    if (countDC == 5)
                    {
                        score++;
                    }

                    i--;
                    j++;
                }
            }

       //diagonals V2
       for (int r = 0; r < playerBoard.length; r++)
       {
           int countDR = 0;

           int i = r;
           int j = playerBoard.length - 1;

           while (i <=  playerBoard.length - 1 && j >= 0)
           {
               if (!playerBoard[i][j].isEmpty())
               {
                   countDR++;
               }

               else
               {
                   countDR = 0;
               }

               if (countDR == 5)
               {
                   score++;
               }

               i++;
               j--;
           }
       }

       //diagonals V2
       for (int c = 0; c < playerBoard.length - 1; c++)
       {
           int countDR = 0;

           int i =  playerBoard.length - 1;
           int j = c;

           while (i >= 0 && j >= 0)
           {
               if (!playerBoard[i][j].isEmpty())
               {
                   countDR++;
               }

               else
               {
                   countDR = 0;
               }

               if (countDR == 5)
               {
                   score++;
               }

               i--;
               j--;
           }
       }*/







        return score;

    }



}
