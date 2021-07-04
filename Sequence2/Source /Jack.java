/**
 *  Creates a Jack Card
 *
 *  @author reinapradhan
 *  @version May 8, 2021
 */
public class Jack extends Card
{
    private boolean oneEyed;
    private boolean twoEyed;


    /**
     * Creates a new Jack object (extended from card)
     * @param suit of jack (string)
     * @param val of jack (int) always going to be 11
     * @param oneEyed t/f
     * @param twoEyed t/f
     */
    public Jack(String suit, int val, boolean oneEyed, boolean twoEyed )
    {
        super(suit, val);
        this.oneEyed = oneEyed;
        this.twoEyed = twoEyed;

    }

    /**
     * {@inheritDoc}
     * returns true if oneEyed
     */
    public boolean isOneEyed()
    {
        return oneEyed;
    }

    /**
     * {@inheritDoc}
     * returns true if twoEyed
     */
    public boolean isTwoEyed()
    {
        return twoEyed;
    }

    /**
     * {@inheritDoc}
     * returns true
     */
    public boolean isJack()
    {
        return true;
    }


}
