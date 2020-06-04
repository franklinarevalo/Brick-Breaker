import java.awt.*;


public class ScoreBoard {

    private int score;
    private int level;

    public ScoreBoard(int score, int level) {
        this.score = score;
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void draw(Graphics2D g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Courier New", Font.BOLD, 25));
        g.drawString(""+score, 590, 30);
        g.drawString("LEVEL "+this.level, 77, 30);
    }
}
