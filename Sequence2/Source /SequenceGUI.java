import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import java.util.Queue;
import javax.imageio.ImageIO;
import javax.swing.*;


/**
 *  Creates the Sequence Board, GUI,
 *  and starts the game
 *
 *  Resources: Downloaded images from
 * https://github.com/zjk97/sequence-game/tree/master/res/normalCards
 *
 *
 *  @author reinapradhan
 *  @version May 26, 2021
 */
public class SequenceGUI implements ActionListener
{

    public JFrame frame = new JFrame("Sequence");
    private GridBagConstraints gbcFrame = new GridBagConstraints();

    private JPanel title_panel = new JPanel();
    private JLabel textField = new JLabel();

    private JPanel board_panel = new JPanel();
    private JButton[][] buttons = new JButton[10][10];

    private JPanel deck_panel = new JPanel();
    private JLabel deck_img = new JLabel();

    private JButton[] hand = new JButton[5];

    private Player player;
    private AI AI;
    private GameSetup setUp;
    private Card[][] gameBoard;

    private Boolean playerTurn = true;
    private Queue<Card> deck;




    /**
     * Create a new SequenceGUI object. Creates a title panel a board array make
     * up of buttons with pictures of cards and a panel of "hand" hand
     * represents the 5 cards the player has at all times
     */
    public SequenceGUI()
    {
        // set up frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(230, 230, 230));
        frame.setSize(3000, 3000);
        frame.setResizable(true);
        // frame layout
        frame.setLayout(new GridBagLayout());

        // title text field
        textField.setText("SEQUENCE");

        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setBackground(new Color(230, 230, 230));
        textField.setForeground(new Color(0, 0, 128));
        textField.setFont(new Font("Cooperplate Gothic Bold", Font.BOLD, 70));

        title_panel.setLayout(new BorderLayout());

        title_panel.add(textField);
        // title_panel.setBounds(0,0,1400, 100);

        // add title panel to frame
        gbcFrame.gridx = 0;
        gbcFrame.gridy = 0;
        gbcFrame.fill = GridBagConstraints.HORIZONTAL;
        gbcFrame.gridwidth = 4;
        frame.add(title_panel, gbcFrame);

        // board panel set up
        board_panel.setLayout(new GridLayout(10, 10));
        board_panel.setBackground(new Color(99, 102, 106));
        // board_panel.setBounds(0, 100, 850, 2750);
        GameSetup setup = new GameSetup();
        Card[][] reference = setup.buildBoard();

        for (int r = 0; r < 10; r++)
        {
            for (int c = 0; c < 10; c++)
            {
                buttons[r][c] = new JButton();
                buttons[r][c].setBackground(new Color(99, 102, 106));
                buttons[r][c].addActionListener(this);
                buttons[r][c].setFont(new Font("MV Boli", Font.BOLD, 120));
                buttons[r][c].setFocusable(true);
                buttons[r][c].setVisible(true);
                buttons[r][c].setLayout(new BorderLayout());

                String suit = reference[r][c].getSuit().substring(0, 1).toUpperCase();

                String img;
                if (reference[r][c].getValue() <= 10 && reference[r][c].getValue() != 0)
                {
                    img = reference[r][c].getValue() + suit;
                }

                else if (reference[r][c].getValue() == 0)
                {
                    img = "joker";
                }

                else if (reference[r][c].getValue() == 12)
                {
                    img = "Q" + suit;
                }

                else if (reference[r][c].getValue() == 13)
                    img = "K" + suit;

                else
                {
                    img = "A" + suit;
                }

                ImageIcon icon =
                    new ImageIcon(getClass().getResource("/normalCards/" + img + ".png"));
                Image image = icon.getImage();
                // int width = buttons[r][c].getWidth();
                // int height = buttons[r][c].getHeight();
                Image resized = image.getScaledInstance(85, 55, java.awt.Image.SCALE_SMOOTH);
                buttons[r][c].setIcon(new ImageIcon(resized));
                buttons[r][c].setVisible(true);

                board_panel.add(buttons[r][c]);

            }

        }

        // adding the board to frame
        gbcFrame.gridx = 0;
        gbcFrame.gridy = 1;
        frame.add(board_panel, gbcFrame);

        // deck Label
        deck_img.setText("Hand:");
        deck_img.setHorizontalTextPosition(JLabel.CENTER);
        deck_img.setBackground(new Color(0, 0, 128));
        deck_img.setForeground(new Color(211, 211, 211));
        deck_img.setFont(new Font("Cooperplate Gothic Bold", Font.BOLD, 55));

        deck_panel.setLayout(new GridLayout());
        deck_panel.add(deck_img);

        // creating Hand Buttons
        for (int a = 0; a < 5; a++)
        {
            hand[a] = new JButton();
            hand[a].setBackground(new Color(11, 211, 211));
            hand[a].addActionListener(this);
            hand[a].setFont(new Font("MV Boli", Font.BOLD, 40));
            hand[a].setFocusable(true);
            hand[a].setVisible(true);
            deck_panel.add(hand[a]);

        }

        // adding deck label
        gbcFrame.gridx = 1;
        gbcFrame.gridy = 2;
        // gbcFrame.ipady = 40;
        frame.add(deck_panel, gbcFrame);
        deck_panel.setBounds(0, 0, 1400, 100);

        frame.setVisible(true);

        playGame();
    }


    /**
     * Starts a game
     * Initializes: the card array board, t
     * he Player, and the AI, and randomly shuffled cards
     * gives each player/AI 5 cards to begin with
     *
     * controls the AI's game play
     *
     *
     */
    public void playGame()
    {
        setUp = new GameSetup();
        gameBoard = setUp.buildBoard();
        deck = setUp.shuffleCards();
        playerTurn = true;
        GameSetup playerSetUp = new GameSetup();
        GameSetup AISetUp = new GameSetup();

        // System.out.println(deck.size());

        /*
         * while(!deck.isEmpty()) { System.out.println(deck.remove().getValue()
         * + " ||| " + deck.remove().getSuit()); }
         */

        ArrayList<Card> tempPlayer = new ArrayList<Card>();

        tempPlayer.add(deck.remove());
        tempPlayer.add(deck.remove());
        tempPlayer.add(deck.remove());
        tempPlayer.add(deck.remove());
        tempPlayer.add(deck.remove());

        ArrayList<Card> tempAI = new ArrayList<Card>();
        tempAI.add(deck.remove());
        tempAI.add(deck.remove());
        tempAI.add(deck.remove());
        tempAI.add(deck.remove());
        tempAI.add(deck.remove());

        player = new Player(tempPlayer, playerSetUp.buildBoard());
        AI = new AI(tempAI, AISetUp.buildBoard());

        setHandGUI(this.player.getHand());

        // System.out.println(player.getHand().get(0).getSuit());
        // System.out.println(gameBoard[0][0].getSuit() +
        // gameBoard[0][0].getValue());

        while (!deck.isEmpty())

        {
            System.out.println(playerTurn);

            //boolean test = playerTurn;

            if (!playerTurn)
            {
                System.out.println("AI TURN");
                Card AIcard = AI.chooseMove();
                if (gameBoard[AIcard.getRow()][AIcard.getCol()].isEmpty())
                {
                    AI.playCard();
                }

                String img;
                String suit = AIcard.getSuit().substring(0, 1).toUpperCase();

                if (AIcard.getValue() <= 10 && AIcard.getValue() != 0)
                {
                    img = AIcard.getValue() + suit;
                }

                else if (AIcard.getValue() == 0)
                {
                    img = "joker";
                }

                else if (AIcard.getValue() == 12)
                {
                    img = "Q" + suit;
                }

                else if (AIcard.getValue() == 13)
                {
                    img = "K" + suit;
                }

                else
                {
                    img = "A" + suit;
                }

                ImageIcon icon =
                    new ImageIcon(getClass().getResource("/GreenFilledCards/" + img + ".png"));
                Image image = icon.getImage();
                Image resized = image.getScaledInstance(85, 55, java.awt.Image.SCALE_SMOOTH);

                if (!AIcard.isJack())
                {
                    buttons[AIcard.getRow()][AIcard.getCol()].setIcon(new ImageIcon(resized));
                    gameBoard[AIcard.getRow()][AIcard.getCol()].fill();
                    AI.getBoard()[AIcard.getRow()][AIcard.getCol()].fill();
                }

                if (AIcard.isTwoEyed())
                {
                    boolean isFull = true;
                    int r = 0;
                    int c = 0;

                    while (isFull)
                    {
                        int row = (int)(Math.random() * 10);
                        int col = (int)(Math.random() * 10);
                        if (gameBoard[row][col].isEmpty())
                        {
                            r = row;
                            c = col;
                            isFull = false;
                        }
                    }

                    buttons[r][c].setIcon(new ImageIcon(resized));
                    gameBoard[r][c].fill();
                    AI.getBoard()[r][c].fill();

                }

                if (AIcard.isOneEyed())
                {
                    boolean isEmpty = true;
                    int r = 0;
                    int c = 0;

                    while (isEmpty)
                    {
                        int row = (int)(Math.random() * 10);
                        int col = (int)(Math.random() * 10);
                        if (player.getBoard()[row][col].isEmpty())
                        {
                            r = row;
                            c = col;
                            isEmpty = false;
                        }
                    }

                    ImageIcon blank_icon =
                        new ImageIcon(getClass().getResource("/normalCards/" + img + ".png"));
                    Image blank_image = icon.getImage();
                    Image resized_blank =
                        image.getScaledInstance(85, 55, java.awt.Image.SCALE_SMOOTH);
                    buttons[r][c].setIcon(new ImageIcon(resized_blank));

                    player.getBoard()[r][c].empty();
                    gameBoard[r][c].empty();

                }

                frame.repaint();
                AI.replaceCard(deck.remove());
                System.out.println("AI Score: " + AI.getScore());

                playerTurn = true;

            }

            if (player.getScore() == 2)
            {
                textField.setText("You WON!");

                for (int r = 0; r < 10; r ++)
                {
                    for (int c = 0; c < 10; c++)
                    {
                        buttons[r][c].setEnabled(false);
                    }
                }

                for (int h = 0; h < hand.length; h++)
                {
                    hand[h].setEnabled(false);
                }
            }

            if (AI.getScore() == 2)
            {
                textField.setText("You LOST");

                for (int r = 0; r < 10; r ++)
                {
                    for (int c = 0; c < 10; c++)
                    {
                        buttons[r][c].setEnabled(false);
                    }
                }

                for (int h = 0; h < hand.length; h++)
                {
                    hand[h].setEnabled(false);
                }
            }

        }

    }


    /**
     * Updates the hand panel of player with a new card at the leftmost button
     * @param hand of cards
     */
    public void setHandGUI(ArrayList<Card> hand)
    {
        for (int i = 0; i < 5; i++)
        {
            Card card = hand.get(i);
            String suit = card.getSuit().substring(0, 1).toUpperCase();

            String img;
            if (card.getValue() <= 10 && card.getValue() != 0)
            {
                img = card.getValue() + suit;
            }

            else if (card.getValue() == 0)
            {
                img = "joker";
            }

            else if (card.getValue() == 12)
            {
                img = "Q" + suit;
            }

            else if (card.getValue() == 13)
            {
                img = "K" + suit;
            }

            else if (card.isJack())
            {
                img = "J" + suit;
            }

            else
            {
                img = "A" + suit;
            }

            ImageIcon icon = new ImageIcon(getClass().getResource("/normalCards/" + img + ".png"));
            Image image = icon.getImage();
            Image resized = image.getScaledInstance(85, 55, java.awt.Image.SCALE_SMOOTH);
            this.hand[i].setIcon(new ImageIcon(resized));
            this.hand[i].setVisible(true);

        }
    }


    /**
     * initializes a SequenceGUI object.
     * @param args
     */
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        SequenceGUI gui = new SequenceGUI();

    }


    /**
     * {@inheritDoc} controls the players moves allows player to select a card
     * from their hand and highlights corresponding spaces on the board
     *  if player selects a normal card, 2 spaces are highlighted and the player can
     * choose to place a token on either IF the space is empty
     *
     * if player selects a two eye jack, the entire board is highlighted and player can choose any
     * empty space for their card
     *
     *  if player selects a one eyed jack, all spots occupied by the AI are highlighted,
     *  and player can remove one of the AIs tokens
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {

        // Replaces button the user has clicked on with an image of the button +
        // token
        Color colorHighlight = new Color(250, 237, 39);
        for (int row1 = 0; row1 < 10; row1++)
        {
            for (int col1 = 0; col1 < 10; col1++)
            {
                if (e.getSource() == buttons[row1][col1])
                {
                    if (buttons[row1][col1].getBackground().equals(colorHighlight)
                        && gameBoard[row1][col1].isEmpty())
                    {

                        String suit = gameBoard[row1][col1].getSuit().substring(0, 1).toUpperCase();

                        String img;
                        if (gameBoard[row1][col1].getValue() <= 10
                            && gameBoard[row1][col1].getValue() != 0)
                        {
                            img = gameBoard[row1][col1].getValue() + suit;
                        }

                        else if (gameBoard[row1][col1].getValue() == 0)
                        {
                            img = "joker";
                        }

                        else if (gameBoard[row1][col1].getValue() == 12)
                        {
                            img = "Q" + suit;
                        }

                        else if (gameBoard[row1][col1].getValue() == 13)
                        {
                            img = "K" + suit;
                        }

                        else
                        {
                            img = "A" + suit;
                        }

                        ImageIcon icon = new ImageIcon(
                            getClass().getResource("/BlueFilledCards/" + img + ".png"));
                        Image image = icon.getImage();

                        Image resized =
                            image.getScaledInstance(85, 55, java.awt.Image.SCALE_SMOOTH);
                        buttons[row1][col1].setIcon(new ImageIcon(resized));

                        gameBoard[row1][col1].fill();
                        // player.playCard(gameBoard[row1][col1], row1, col1);
                        player.getBoard()[row1][col1].fill();

                        for (Card playedCard : player.getHand())
                        {
                            if (playedCard.getValue() == gameBoard[row1][col1].getValue()
                                && playedCard.getSuit().equals(gameBoard[row1][col1].getSuit()))
                            {
                                player.getHand().remove(playedCard);
                                break;

                            }
                        }

                        // player.getHand().remove(gameBoard[row1][col1]);
                        // setHandGUI(player.getHand());

                        player.getHand().add(deck.remove());

                        setHandGUI(player.getHand());

                        System.out.println("Player Score: " + player.getScore());
                        System.out.println(playerTurn);

                        String title =
                            "AI: " + AI.getScore() + "   SEQUENCE   " + "You: " + player.getScore();
                        textField.setText(title);

                        playerTurn = false;
                    }
                }
            }
            // frame.repaint();
        }

        Color twoEyedJack = new Color(50, 205, 50);
        for (int row1 = 0; row1 < 10; row1++)
        {
            for (int col1 = 0; col1 < 10; col1++)
            {
                if (e.getSource() == buttons[row1][col1])
                {
                    if (buttons[row1][col1].getBackground().equals(twoEyedJack)
                        && gameBoard[row1][col1].isEmpty())
                    {

                        String suit = gameBoard[row1][col1].getSuit().substring(0, 1).toUpperCase();

                        String img;
                        if (gameBoard[row1][col1].getValue() <= 10
                            && gameBoard[row1][col1].getValue() != 0)
                        {
                            img = gameBoard[row1][col1].getValue() + suit;
                        }

                        else if (gameBoard[row1][col1].getValue() == 0)
                        {
                            img = "joker";
                        }

                        else if (gameBoard[row1][col1].getValue() == 12)
                        {
                            img = "Q" + suit;
                        }

                        else if (gameBoard[row1][col1].getValue() == 13)
                        {
                            img = "K" + suit;
                        }

                        else
                        {
                            img = "A" + suit;
                        }

                        ImageIcon icon = new ImageIcon(
                            getClass().getResource("/BlueFilledCards/" + img + ".png"));
                        Image image = icon.getImage();

                        Image resized =
                            image.getScaledInstance(85, 55, java.awt.Image.SCALE_SMOOTH);
                        buttons[row1][col1].setIcon(new ImageIcon(resized));

                        gameBoard[row1][col1].fill();
                        // player.playCard(gameBoard[row1][col1], row1, col1);
                        player.getBoard()[row1][col1].fill();

                        for (Card playedCard : player.getHand())
                        {
                            if (playedCard.isJack() && playedCard.isTwoEyed())
                            {
                                player.getHand().remove(playedCard);
                                break;
                            }
                        }

                        player.getHand().add(deck.remove());

                        setHandGUI(player.getHand());

                        System.out.println("Player Score: " + player.getScore());
                        System.out.println(playerTurn);

                        String title =
                            "AI: " + AI.getScore() + "   SEQUENCE   " + "You: " + player.getScore();
                        textField.setText(title);

                        playerTurn = false;

                    }
                }
            }
        }

        Color OneEyedJack = new Color(128, 0, 0);
        {
            for (int row1 = 0; row1 < 10; row1++)
            {
                for (int col1 = 0; col1 < 10; col1++)
                {
                    if (e.getSource() == buttons[row1][col1])
                    {
                        if (buttons[row1][col1].getBackground().equals(OneEyedJack))
                        {

                            String suit =
                                gameBoard[row1][col1].getSuit().substring(0, 1).toUpperCase();

                            String img;
                            if (gameBoard[row1][col1].getValue() <= 10
                                && gameBoard[row1][col1].getValue() != 0)
                            {
                                img = gameBoard[row1][col1].getValue() + suit;
                            }

                            else if (gameBoard[row1][col1].getValue() == 0)
                            {
                                img = "joker";
                            }

                            else if (gameBoard[row1][col1].getValue() == 12)
                            {
                                img = "Q" + suit;
                            }

                            else if (gameBoard[row1][col1].getValue() == 13)
                            {
                                img = "K" + suit;
                            }

                            else
                            {
                                img = "A" + suit;
                            }

                            ImageIcon icon = new ImageIcon(
                                getClass().getResource("/NormalCards/" + img + ".png"));
                            Image image = icon.getImage();

                            Image resized =
                                image.getScaledInstance(85, 55, java.awt.Image.SCALE_SMOOTH);
                            buttons[row1][col1].setIcon(new ImageIcon(resized));

                            gameBoard[row1][col1].empty();
                            // player.playCard(gameBoard[row1][col1], row1,
                            // col1);
                            player.getBoard()[row1][col1].empty();

                            for (Card playedCard : player.getHand())
                            {
                                if (playedCard.isJack() && playedCard.isOneEyed())
                                {
                                    player.getHand().remove(playedCard);
                                    break;
                                }
                            }

                            if (!deck.isEmpty())
                            {
                                player.getHand().add(deck.remove());
                            }
                            setHandGUI(player.getHand());

                            System.out.println("Player Score: " + player.getScore());
                            System.out.println(playerTurn);

                            String title = "AI: " + AI.getScore() + "   SEQUENCE   " + "You: "
                                + player.getScore();
                            textField.setText(title);

                            playerTurn = false;

                        }
                    }
                }
            }
        }

        frame.repaint();

        // repaint all buttons after another button is clicked
        for (int row = 0; row < 10; row++)
        {
            for (int col = 0; col < 10; col++)
            {
                buttons[row][col].setBackground(new Color(99, 102, 106));
            }
        }

        // cards on board that correspond to cards chosen by user are
        // highlighted
        if (playerTurn)
        {
            for (int a = 0; a < 5; a++)
            {
                if (e.getSource() == hand[a])
                {

                    Card card = player.getHand().get(a);

                    if (card.isJack())
                    {
                        if (card.isTwoEyed())
                        {
                            for (int r = 0; r < 10; r++)
                            {
                                for (int c = 0; c < 10; c++)
                                {
                                    buttons[r][c].setForeground(new Color(50, 205, 50));
                                    buttons[r][c].setBackground(new Color(50, 205, 50));
                                    buttons[r][c].setOpaque(true);

                                }
                            }
                        }

                        else if (card.isOneEyed())
                        {
                            for (int r = 0; r < 10; r++)
                            {
                                for (int c = 0; c < 10; c++)
                                {
                                    if (!AI.getBoard()[r][c].isEmpty())
                                    {
                                        buttons[r][c].setForeground(new Color(128, 0, 0));
                                        buttons[r][c].setBackground(new Color(128, 0, 0));
                                        buttons[r][c].setOpaque(true);

                                    }
                                }
                            }
                        }
                    }

                    else
                    {
                        for (int r = 0; r < 10; r++)
                        {
                            for (int c = 0; c < 10; c++)
                            {

                                if (gameBoard[r][c].getSuit().equals(card.getSuit())
                                    && gameBoard[r][c].getValue() == card.getValue())
                                {

                                    System.out.println(r + " , " + c);
                                    buttons[r][c].setForeground(new Color(250, 237, 39));
                                    buttons[r][c].setBackground(new Color(250, 237, 39));
                                    buttons[r][c].setOpaque(true);

                                }
                            }
                        }
                    }

                }
            }

        }

    }

}
