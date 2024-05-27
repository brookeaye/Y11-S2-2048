import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;

public class Cell extends GraphicsProgram {
    int power;
    int baseNum;
    int[] location;
    GLabel label;
    GRect rect;
    int boardSize;
    public Cell(int baseNum, int power, int[] location, int boardSize){
        this.baseNum = baseNum;
        this.power = power;
        this.location = location;
        this.boardSize = boardSize;

        rect = new GRect(boardSize*10 + 80*location[1], boardSize*10 + 80*location[0], 80, 80);
        label = new GLabel("" + (int) Math.pow(baseNum, power));
        label.setLocation(rect.getX() + ((80-label.getWidth())/2), rect.getY() + 50);
        label.setFont(new Font("Serif", Font.PLAIN, 32));
    }

    public Color pickColor(){
        switch (power){
            case 1:
                return new Color(102, 184, 255);

            case 2:
                return new Color(4, 119, 220);

            case 3:
                return new Color(238, 238, 238);

            case 4:
                return new Color(255, 77, 137);

            case 5:
                return new Color(199, 11, 74);

            case 6:
                return new Color(212, 92, 255);

            case 7:
                return new Color(145, 14, 182);

            case 8:
                return new Color(251, 255, 110);

            case 9:
                return new Color(91, 255, 119);

            case 10:
                return new Color(37, 169, 37);

            case 11:
                return new Color(124, 42, 246);

        }
        return Color.WHITE;
    }

    public void setLocation(int[] newLocation){
        location[0] = newLocation[0];
        location[1] = newLocation[1];
        rect.setLocation(boardSize*10 + 80*newLocation[1], boardSize*10 + 80*newLocation[0]);
        label.setLocation(rect.getX() + ((80-label.getWidth())/2), rect.getY() + 50);
    }
    public void increasePower(){
        power++;
        label.setLabel("" + (int) Math.pow(baseNum, power));
        rect.setFillColor(pickColor());
        rect.setFilled(true);
    }

}
