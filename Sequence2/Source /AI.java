import java.util.ArrayList;

/**
 *  Creates an AI object, which extends the player class
 *
 *  @author reinapradhan
 *  @version May 25, 2021
 */
public class AI extends Player
{

    private Card move;


    /**
     * Create a new AI object.
     * @param hand of 5 cards
     * @param gameBoard
     */
    public AI(ArrayList<Card> hand, Card[][] gameBoard)
    {
        super(hand, gameBoard);
    }


    /**
     * {@inheritDoc}
     * returns board
     */
    public Card[][] getBoard()
    {
        return super.getBoard();
    }


    /**
     * AI randomly chooses a move from within its potential moves
     * (cards in hand)
     * @return
     */
    public Card chooseMove()
    {
        ArrayList<Card> hand = getHand();
        int choice = (int)(Math.random() * 5);
        move = hand.get(choice);

        int num5 = getRows(getBoard(), 5);
        int num4 = getRows(getBoard(), 4);
        int num3 = getRows(getBoard(), 3);
        int num2 = getRows(getBoard(), 2);


        Card[][] temp = getBoard();


        for(int i = 0; i < 5; i++)
        {
            temp[hand.get(i).getRow()][hand.get(i).getCol()].fill();

            if(getRows(temp, 5) >= num5 )
            {
                num5 = getRows(temp, 5);
                move = hand.get(i);
            }

            else if (getRows(temp, 4) >= num4 )
            {
                num4 = getRows(temp, 5);
                move = hand.get(i);
            }

            else if(getRows(temp, 3) >= num3)
            {
                num3 = getRows(temp, 3);
                move = hand.get(i);
            }

            else if (getRows(temp, 2) >= num2)
            {
                num2 = getRows(temp, 2);
                move = hand.get(i);
            }

        }





        return move;
    }

    /**
     * places card on the AI's game board
     * removes card from hand
     */
    public void playCard()
    {
       /*if (!move.isJack())
        {
            super.playCard(move, move.getRow(), move.getCol());
        }

        else
        {
            chooseMove();
        }*/

        for(int i = 0; i <5; i++)
        {
            System.out.println(getHand().get(i));
        }

       super.playCard(move, move.getRow(), move.getCol());

    }

    /**
     * {@inheritDoc}
     * adds a card to the hand
     */
    public void  replaceCard(Card card)
    {
        super.replaceCard(card);
    }

    public int getRows(Card[][] arr, int num)
    {
        int score = 0;

        //rows
        for (int r = 0; r < arr.length; r++)
        {
            int countR = 0;

            for (int c = 0; c < arr[r].length; c++)
            {
                Card cardR = arr[r][c];

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
        for (int c = 0; c < arr[0].length; c++)
        {
            int countC = 0;

            for(int r = 0; r < arr.length; r++)
            {
                Card cardC = arr[r][c];

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
        for (int r = 0; r < arr.length; r++)
        {
            int countDR = 0;

            int i = r;
            int j = 0;

            while (i >= 0)
            {
                if (!arr[i][j].isEmpty())
                {
                    countDR++;
                }

                else
                {
                    countDR = 0;
                }

                if (countDR == num)
                {
                    score++;
                }

                i--;
                j++;
            }
        }

       for (int d = 1; d < arr[0].length - 1; d++)
        {
            int countDC = 0;

            int i = arr[0].length - 1;
            int j = d;

            while (j < arr[0].length && i >= 0)
            {

                    if (!arr[i][j].isEmpty())
                    {
                        countDC++;
                    }

                    else
                    {
                        countDC = 0;
                    }

                    if (countDC == num)
                    {
                        score++;
                    }

                    i--;
                    j++;
                }
            }

       //diagonals V2
       for (int r = 0; r < arr.length; r++)
       {
           int countDR = 0;

           int i = r;
           int j = arr.length - 1;

           while (i <=  arr.length - 1 && j >= 0)
           {
               if (!arr[i][j].isEmpty())
               {
                   countDR++;
               }

               else
               {
                   countDR = 0;
               }

               if (countDR == num)
               {
                   score++;
               }

               i++;
               j--;
           }
       }

       //diagonals V2
       for (int c = 0; c < arr.length - 1; c++)
       {
           int countDR = 0;

           int i =  arr.length - 1;
           int j = c;

           while (i >= 0 && j >= 0)
           {
               if (!arr[i][j].isEmpty())
               {
                   countDR++;
               }

               else
               {
                   countDR = 0;
               }

               if (countDR == num)
               {
                   score++;
               }

               i--;
               j--;
           }
       }







        return score;

    }


}
