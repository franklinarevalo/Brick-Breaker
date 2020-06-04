import GameObject.Ball;
import GameObject.Slider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Game extends JPanel implements KeyListener, ActionListener {

    private Timer timer;
    private int delay;
    private int SCREEN_WIDTH;
    private int SCREEN_HEIGHT;

    private boolean play;
    private boolean gameStart;
    private ScoreBoard scoreBoard;

    private Slider slider;
    private Ball ball;
    private boolean win;

    private int brickRows;
    private int brickCols;
    private int totalBricks;
    private MapGenerator map;
    private MapGenerator map2;

    public Game(){
        init();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer.start();
    }

    public void init(){
        delay = 8;
        timer = new Timer(delay, this);
        SCREEN_WIDTH = 692;
        SCREEN_HEIGHT = 592;

        // Game
        play = false;
        gameStart = false;
        scoreBoard = new ScoreBoard(0,1);

        // Game Objects
        slider = new Slider(310,550,100,8);
        ball = new Ball(400, 350, -1, -2, 20, 20);

        // Bricks
        brickRows = 3;
        brickCols = 7;
        totalBricks = (brickRows * brickCols) * 3;
        map = new MapGenerator(brickRows,brickCols);
    }

    public void paint(Graphics g){
        super.paint(g);

        // Background
        g.setColor(Color.BLACK);
        g.fillRect(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);

        // drawing map
        map.draw((Graphics2D)g);

        // score
        scoreBoard.draw((Graphics2D)g);

        // the slider
        slider.draw((Graphics2D)g);

        // the ball
        ball.draw((Graphics2D)g);

        if (totalBricks <= 0){
            reset();
            win = true;
            g.setColor(Color.GREEN);
            g.setFont(new Font("Courier New", Font.BOLD, 25));
            g.drawString("YOU WIN ", 190, 300);

            if (scoreBoard.getLevel() == 1){
                g.setFont(new Font("Courier New", Font.BOLD, 20));
                g.drawString("Press Spacebar for Level 2", 195, 350);
            } else if (scoreBoard.getLevel() == 2){
                g.setFont(new Font("Courier New", Font.BOLD, 20));
                g.drawString("Press Spacebar for Level 3", 195, 350);
            } else if (scoreBoard.getLevel() == 3){
                g.setFont(new Font("Courier New", Font.BOLD, 20));
                g.drawString("Congratulations no more levels!", 195, 350);
            } else {
                g.setFont(new Font("Courier New", Font.BOLD, 20));
                g.drawString("End of Level 2...", 195, 350);
            }

            g.setFont(new Font("Courier New", Font.BOLD, 20));
            g.drawString("Press Enter to Restart", 195, 375);
        }

        if (ball.getBallY() > 590){
            reset();
            g.setColor(Color.RED);
            g.setFont(new Font("Courier New", Font.BOLD, 25));
            g.drawString("GAME OVER, Score: "+scoreBoard.getScore(), SCREEN_WIDTH/3, 300);

            g.setFont(new Font("Courier New", Font.BOLD, 20));
            g.drawString("Press Enter to Restart", SCREEN_WIDTH/3, 350);
        }

        if (gameStart == false){
            g.setColor(Color.WHITE);
            g.setFont(new Font("Courier New", Font.BOLD, 25));
            g.drawString("START GAME ", SCREEN_WIDTH/4, 300);

            g.setFont(new Font("Courier New", Font.BOLD, 20));
            g.drawString("Move Left & Right Arrow Keys to Play",SCREEN_WIDTH/3-50, 325);
        }

        g.dispose();
    }

    public void reset (){
        play = false;
        ball.setBallVx(0);
        ball.setBallVy(0);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        timer.start();

        if (play){
            if (ball.getHitBox().intersects(slider.getHitBox())){
                ball.setBallVy(-ball.getBallVy());
            }

         A: for (int i = 0; i < map.getMap().length; i++){
                for (int j = 0; j < map.getMap()[0].length; j++){
                    if (map.getMap()[i][j] > 0){
                        int brickX = j * map.getBrickWidth() + 80;
                        int brickY = i * map.getBrickHeight() + 50;
                        int brickWidth = map.getBrickWidth();
                        int brickHeight = map.getBrickHeight();

                        Rectangle brickRect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ball.getBallX(), ball.getBallY(), 20,20);

                        if (ballRect.intersects(brickRect)){
                            int value = map.getMap()[i][j];

                            if (value < 4){
                                value -= 1;
                                map.setBrickValue(value, i, j);
                                totalBricks--;
                                scoreBoard.setScore(scoreBoard.getScore()+5);
                            }

                            if (ball.getBallX() + 19 <= brickRect.x || ball.getBallX() + 1 >= brickRect.x + brickRect.width){
                                ball.setBallVx(-ball.getBallVx());
                            } else {
                                ball.setBallVy(-ball.getBallVy());
                            }

                            break A;
                        }
                    }
                }
            }

            ball.setBallX(ball.getBallX() + ball.getBallVx());
            ball.setBallY(ball.getBallY() + ball.getBallVy());

            ball.checkBorder(ball.getBallX(), ball.getBallY(), ball.getBallVx(), ball.getBallVy());
        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) { }

    @Override
    public void keyReleased(KeyEvent keyEvent) { }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            if (slider.getX() >= 600){
                slider.setX(600);
            } else {
                moveRight();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            if (slider.getX() < 10){
                slider.setX(10);
            } else {
                moveLeft();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            if (!play){
                scoreBoard.setLevel(1);
                loadGame(1);
                repaint();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            if (!play){
                if(win == true && scoreBoard.getLevel() < 3) {
                    scoreBoard.setLevel(scoreBoard.getLevel()+1);
                }

                if (win == true){
                    loadGame(2);
                    repaint();
                } else if (win == true && scoreBoard.getLevel() == 2){
                    loadGame(3);
                    repaint();
                }
            }
        }
    }

    public void loadGame(int level){
        if (level == 1){
            play = true;
            ball.resetBallValues(400, 350, -1, -2);
            slider.setX(310);
            scoreBoard.setScore(0);
            totalBricks = (3*7)*3;
            map = new MapGenerator(3,7);
        } else if (level == 2){
            play = true;
            ball.resetBallValues(400, 350, -1, -2);
            slider.setX(310);
            totalBricks = 46;
            map = new MapGenerator(3,7, 2);
        } else if (level == 3){
            play = true;
            ball.resetBallValues(400, 350, -1, -2);
            slider.setX(310);
            totalBricks = 43;
            map = new MapGenerator(3,7, 3);
        }
    }

    public void moveRight(){
        play = true;
        gameStart = true;
        slider.setX(slider.getX() + 20);
    }

    public void moveLeft(){
        play = true;
        gameStart = true;
        slider.setX(slider.getX() - 20);
    }
}