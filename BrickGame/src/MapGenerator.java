import java.awt.*;


public class MapGenerator {

    private int map[][];
    private int brickWidth;
    private int brickHeight;

//    private int totalBricks;


    public MapGenerator(int row, int col){
        map = new int[row][col];
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                map[i][j] = 3;
            }
        }

        brickWidth = 540/col;
        brickHeight = 150/row;
    }

    public MapGenerator(int row, int col, int level){

        if (level == 2){
            map = new int[][]{
                    {3,3,3,3,2,3,3},
                    {4,3,2,3,3,2,4},
                    {4,2,2,3,3,3,4}
            };
        } else if (level == 3){
            map = new int[][]{
                    {4,3,3,3,2,3,3},
                    {4,3,2,4,4,2,3},
                    {4,1,2,3,3,3,3}
            };
        }

        // COUNTING TOTAL BRICKS
//        int whiteBricks = 0;
//        int pinkBricks = 0;
//        int redBricks = 0;
//
//        for (int i = 0; i < map.length; i++){
//            for (int j = 0; j < map[0].length; j++){
//                if (map[i][j] == 3){
//                    whiteBricks++;
//                } else if (map[i][j] == 2){
//                    pinkBricks++;
//                } else if (map[i][j] == 1){
//                    redBricks++;
//                }
//            }
//        }
//
//        totalBricks = (21-pinkBricks) + (21-redBricks) + (21-redBricks);
//
//        System.out.println(totalBricks);

        brickWidth = 540/col;
        brickHeight = 150/row;
    }

    public int[][] getMap() {
        return map;
    }

    public int getBrickWidth() {
        return brickWidth;
    }

    public int getBrickHeight() {
        return brickHeight;
    }

    public void draw(Graphics2D g){
        for(int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){

                if (map[i][j] == 4){
                    g.setColor(Color.GRAY);
                    g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                } else if (map[i][j] == 3){
                    g.setColor(Color.WHITE);
                    g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                } else if (map[i][j] == 2){
                    g.setColor(Color.PINK);
                    g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                } else if (map[i][j] == 1){
                    g.setColor(Color.RED);
                    g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                }

                g.setStroke(new BasicStroke(3));
                g.setColor(Color.BLACK);
                g.drawRect(j * brickWidth + 80, i * brickHeight + 50,brickWidth,brickHeight);
            }
        }
    }

    public void setBrickValue(int value, int row, int col){
        map[row][col] = value;
    }

}
