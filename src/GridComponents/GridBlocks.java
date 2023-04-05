package GridComponents;

import javax.swing.*;
import java.awt.*;

public class GridBlocks extends JLabel {

    boolean occupied = false;
    boolean occupiedByFood = false;
    Color backGroundColor = Color.LIGHT_GRAY;
    Color snakeColor = Color.GRAY;
    Color barrierColor = Color.DARK_GRAY;
    ImageIcon foodImage = new ImageIcon("Food.png");

    public GridBlocks(int XLocation, int YLocation) {

        this.setBounds(XLocation, YLocation, 20, 20);
        this.setOpaque(true);
        this.setBackground(Color.LIGHT_GRAY);
        this.setVisible(true);

    }

    public void occupy() {
        this.setBackground(snakeColor);
        this.setIcon(null);
        occupiedByFood = false;
        occupied = true;
    }

    public void makeBarrier() {
        this.setBackground(barrierColor);
        occupied = true;
    }

    public void occupyWithFood() {
        occupiedByFood = true;
        this.setIcon(foodImage);
    }

    public void free() {
        this.setBackground(backGroundColor);
        occupied = false;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public boolean isOccupiedByFood() {
        return occupiedByFood;
    }

}
