/*import java.util.ArrayList;
import java.util.Queue;*/
import java.util.*;

/**
 *Has two helper methods,
 *one which creates a shuffled and randomized deck of cards
 *another which creates an array of cards in the order of the
 *sequence board
 *
 *  @author reinapradhan
 *  @version May 8, 2021
 */
public class GameSetup
{

    //Notes: A value = 14
    // suits = spade, heart, club, diamond

    private ArrayList<Card> cards = new ArrayList<Card>();
    private Queue<Card> deck = new LinkedList<Card>();
    private Card[][] board = new Card[10][10];


    /**
     * populates the card array with card objects
     * each of which have locations corresponding to the sequence board
     */
    public GameSetup()
    {
        /****** spades ********/

        // top row
        for (int i = 1; i <= 8; i++)
        {
            cards.add(new Card("spade", i + 1, 0, i)); // -1));
        }

        // column 9
        for (int i = 1; i <= 4; i++)
        {
            int val = i + 9;
            if (val >= 11)
            {
                val++;
            }

            cards.add(new Card("spade", val, i, 9));
        }

        // column 1 *< 8
        for (int i = 2; i <= 8; i++)
        {
            int val = 16 - i;
            if (val <= 11)
            {
                val--;
            }
            cards.add(new Card("spade", val, i, 1));
        }

        // row 8
        for (int i = 2; i <= 6; i++)
        {
            int val = 8 - i;
            cards.add(new Card("spade", val, 8, i));
        }

        /****** hearts ********/

        // row 1
        for (int i = 5; i <= 8; i++)
        {
            int val = 19 - i;
            if (val <= 11)
            {
                val--;
            }

            cards.add(new Card("heart", val, 1, i));
        }

        // column 8
        for (int i = 2; i <= 8; i++)
        {
            int val = 11 - i;
            cards.add(new Card("heart", val, i, 8));
        }

        cards.add(new Card("heart", 2, 8, 7));

        // row 4
        for (int i = 3; i <= 5; i++)
        {
            int val = 9 - i;
            cards.add(new Card("heart", val, 4, i));
        }

        // row 5
        cards.add(new Card("heart", 7, 5, 3));
        cards.add(new Card("heart", 2, 5, 4));
        cards.add(new Card("heart", 3, 5, 5));

        // row 6
        for (int i = 3; i <= 5; i++)
        {
            int val = 5 + i;
            cards.add(new Card("heart", val, 6, i));
        }

        // column 6
        for (int i = 4; i <= 6; i++)
        {
            int val = 18 - i;
            cards.add(new Card("heart", val, i, 6));
        }

        /****** clubs ********/

        // row 1
        for (int i = 0; i <= 4; i++)
        {
            int val = 6 - i;
            cards.add(new Card("club", val, 1, i));
        }

        // column 0
        for (int i = 2; i <= 8; i++)
        {
            int val = i + 5;
            if (val >= 11)
            {
                val++;
            }

            cards.add(new Card("club", val, i, 0));
        }

        // row 3
        for (int i = 2; i <= 6; i++)
        {
            int val = 8 - i;
            cards.add(new Card("club", val, 3, i));
        }

        // column 2
        for (int i = 4; i <= 7; i++)
        {
            int val = i + 3;
            cards.add(new Card("club", val, i, 2));
        }

        // row 7
        for (int i = 3; i <= 5; i++)
        {
            int val = i + 9;
            cards.add(new Card("club", val, 7, i));
        }

        /****** diamonds ********/

        // row 2
        for (int i = 2; i <= 7; i++)
        {
            int val = i;
            cards.add(new Card("diamond", val, 2, i));
        }

        // column 7
        for (int i = 3; i <= 7; i++)
        {
            int val = i + 5;
            if (val >= 11)
            {
                val++;
            }

            cards.add(new Card("diamond", val, i, 7));

        }

        // column 6
        cards.add(new Card("diamond", 14, 7, 6));

        // column 9
        for (int i = 5; i <= 8; i++)
        {
            int val = i - 3;
            cards.add(new Card("diamond", val, i, 9));
        }

        // row 9
        for (int i = 1; i <= 8; i++)
        {
            int val = 15 - i;
            if (val <= 11)
            {
                val--;
            }

            cards.add(new Card("diamond", val, 9, i));

        }

        cards.add(new Card("free", 0, 0, 0));
        cards.add(new Card("free", 0, 0, 9));
        cards.add(new Card("free", 0, 9, 0));
        cards.add(new Card("free", 0, 9, 9));

        /*
         * for(int i = 0; i < cards.size(); i++) {
         * System.out.println(cards.get(i).getValue() + " , " +
         * cards.get(i).getSuit()); }
         */

    }


    /**
     * Adds Jacks to the cards ArrayList
     * Removes Card objects from the cards ArrayList in a
     * random order to create a shuffled deck of cards
     * @return
     */
    public Queue<Card> shuffleCards()
    {
        cards.add(new Jack("heart", 11, true, false));
        cards.add(new Jack("heart", 11, true, false));
        cards.add(new Jack("club", 11, false, true));
        cards.add(new Jack("club", 11, false, true));
        cards.add(new Jack("diamond", 11, false, true));
        cards.add(new Jack("diamond", 11, false, true));
        cards.add(new Jack("spade", 11, true, false));
        cards.add(new Jack("spade", 11, true, false));

        ArrayList<Card> unshuffled = cards;
        int numCards = unshuffled.size();
        System.out.println(numCards);

        for (int i = 0; i < numCards; i++)
        {
            int index = (int)(Math.random() * unshuffled.size());
            Card card = unshuffled.remove(index);
            deck.add(card);
            /*
             * System.out.println(card.getValue() + ", " + card.getSuit()); if
             * (card.isJack()) { System.out.println("******JACK*****"); }
             */

        }

        System.out.println(deck.size());
        return deck;
    }


    /**
     * iterates through the cards ArrayList and places each card
     * object in its corresponding location on the board
     * @return board
     */
    public Card[][] buildBoard()
    {

        int numCards = cards.size();

        for (int i = 0; i < numCards; i++)
        {
            Card card = cards.get(i);
            if (!card.isJack())
            {
                board[card.getRow()][card.getCol()] = card;
            }
        }

        return board;
    }


    /**
     * main method
     * creates a game setup object for testing purposes
     * @param args
     */
    public static void main(String args[])
    {
        GameSetup gs = new GameSetup();
        gs.shuffleCards();
        /*
         * Card[][] cards = gs.buildBoard(); for (int r = 0; r < 10; r ++) { for
         * (int c = 0; c < 10; c++) System.out.println(cards[r][c].getSuit()); }
         */

    }
}
