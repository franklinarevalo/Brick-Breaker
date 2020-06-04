import javax.swing.*;
import java.awt.*;

/**
 * Title: Brick Breaker Game
 * Author: Franklin Arevalo
 *
 * Summary: This is a brick breaker game, a simple game that has a ball, a slider (aka the player)
 *          and bricks! The point of the game is to break the bricks by catching the ball with the
 *          slider and not letting it fall.
 *
 * Controls:
 *          Left Key - Move Left
 *          Right Key - Move Right
 *          Enter - Allows to go restart level after ball has fallen
 *          Spacebar - Allows to go to next level after level is complete
 * */


public class Main {

    public static void main(String[] args){

        Game gamePlay = new Game();

        JFrame frame = new JFrame("Brick Breaker");
        gamePlay.setPreferredSize(new Dimension(692, 592));
        frame.getContentPane().add(gamePlay);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

    }

}
