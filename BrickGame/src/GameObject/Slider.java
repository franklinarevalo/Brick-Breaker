package GameObject;

import java.awt.*;


public class Slider extends GameObject {

    private int x;
    private int y;
    private int width;
    private int height;
    private Rectangle hitBox;

    public Slider(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hitBox = new Rectangle(x, y, width, height);
    }

    public Rectangle getHitBox() {
        return hitBox.getBounds();
    }

    public int getX() { return x; }
    public void setX(int x) {
        this.x = x;
        this.hitBox.setLocation(x,y);
    }

    public void draw(Graphics2D g){
        g.setColor(Color.GREEN);
        g.fillRect(this.x, this.y, this.width, this.height);
    }
}
