/**
 *  Creates a card that contains a suit, value, and position on board
 *
 *  @author reinapradhan
 *  @version May 8, 2021
 */
public class Card
{

    private int value;
    private String suit;
    private int row;
    private int col;
    private boolean empty;


    /**
     * Creates a new Card object.
     * @param suit h, d, s, c
     * @param val 2 - 14 (not including 11)
     * @param r (row) on board
     * @param c (col) on board
     */
    public Card(String suit, int val, int r, int c)
    {
        value = val;
        this.suit = suit;
        row = r;
        col = c;
        empty = true;
    }

    /**
     * Create a new Card object.
     * @param suit of card
     */
    public Card(String suit, int val)
    {
        this.suit = suit;
       value = val;
    }

    /**
     * Returns suit
     * @return suit of card
     */
    public String getSuit()
    {
        return suit;
    }

    /**
     * returns value
     * @return value of card
     */
    public int getValue()
    {
        return value;
    }

    /**
     * returns card's row on board
     * @return row of card
     */
    public int getRow()
    {
        return row;
    }

    /**
     * returns card's column on the board
     * @return col of card
     */
    public int getCol()
    {
        return col;
    }

    /**
     * tells us if there is a token on the cards
     *  place on the board or not
     * @return empty
     */
    public boolean isEmpty()
    {
        return empty;
    }

    public void empty()
    {
        empty = true;
    }

    /**
     * places a token on the space by
     * setting empty to false
     */
    public void fill()
    {
        empty = false;
    }

    /**
     * returns true if jack, or else false
     * @return
     */
    public boolean isJack()
    {
        return false;
    }

    /**
     * returns true if jack and one eyed
     * @return
     */
    public boolean isOneEyed()
    {
        return false;
    }

    /**
     * returns true if jack and two eyed
     * @return
     */
    public boolean isTwoEyed()
    {
        return false;
    }

}
