package GameObject;

import java.awt.*;


public class Ball extends GameObject {

    private int ballX;
    private int ballY;
    private int ballVx;
    private int ballVy;
    private int width;
    private int height;
    private Rectangle hitBox;

    public Ball(int ballX, int ballY, int ballVx, int ballVy, int width, int height) {
        this.ballX = ballX;
        this.ballY = ballY;
        this.ballVx = ballVx;
        this.ballVy = ballVy;
        this.width = width;
        this.height = height;
        this.hitBox = new Rectangle(ballX, ballY, width, height);
    }

    public Rectangle getHitBox() {
        return hitBox.getBounds();
    }



    public int getBallX() {
        return ballX;
    }

    public void setBallX(int ballX) {
        this.ballX = ballX;
        this.hitBox.setLocation(ballX,ballY);
    }

    public int getBallY() {
        return ballY;
    }

    public void setBallY(int ballY) {
        this.ballY = ballY;
        this.hitBox.setLocation(ballX,ballY);
    }

    public int getBallVx() {
        return ballVx;
    }

    public void setBallVx(int ballVx) {
        this.ballVx = ballVx;
    }

    public int getBallVy() {
        return ballVy;
    }

    public void setBallVy(int ballVy) {
        this.ballVy = ballVy;
    }



    public void resetBallValues(int ballX, int ballY, int ballVx, int ballVy){
        setBallX(ballX);
        setBallY(ballY);
        setBallVx(ballVx);
        setBallVy(ballVy);
    }

    public void checkBorder(int ballX, int ballY, int ballVx, int ballVy){
        if (ballX < 0){
            this.ballVx = -ballVx;
        }
        if (ballY < 0){
            this.ballVy = -ballVy;
        }
        if (ballX > 670){
            this.ballVx = -ballVx;
        }
    }

    public void draw(Graphics2D g){
        g.setColor(Color.CYAN);
        g.fillOval(this.ballX, this.ballY, this.width,this.height);
    }
}
