import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import java.util.Queue;
import javax.imageio.ImageIO;
import javax.swing.*;
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author reinapradhan
 *  @version May 29, 2021
 */
public class StartGame implements ActionListener
{

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JButton button = new JButton();


    public StartGame()

    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(230, 230, 230));
        frame.setSize(3000, 3000);
        frame.setResizable(true);


        ImageIcon icon = new ImageIcon(getClass().getResource("cover.png"));
        Image image = icon.getImage();


        ImagePanel panel = new ImagePanel(new ImageIcon(getClass()
            .getResource("cover.png"))
            .getImage());

        frame.add(panel);

        button.setBackground(new Color(0, 0, 80));
        button.setForeground(new Color(255, 255, 255));
        button.addActionListener(this);
        button.setFont(new Font("MV Boli", Font.BOLD, 60));
        button.setText("Start Game");
        button.setBounds(480, 325, 450, 150);
        button.setFocusable(false);
        button.setVisible(true);
        button.setLayout(new BorderLayout());
        button.setOpaque(true);

        panel.add(button);
        frame.add(panel);

        frame.setVisible(true);

    }

    public static void main(String[] args)
    {
        StartGame game = new StartGame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // TODO Auto-generated method stub
        System.out.println("Sequence started");
        SequenceGUI gui = new SequenceGUI();

    }

}
