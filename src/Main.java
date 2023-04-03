import GridComponents.GridBlocks;
import GridComponents.GridCreator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {

    static char direction = 'R';
    static char snakeSpeed = 500;

    public static void main(String[] args) throws InterruptedException {

        int width = 30;
        int height = 30;
        int snakeSize = 5;
        int[] tailLocation = new int[2];

        Color snakeColor = Color.DARK_GRAY;
        Color backGroundColor = Color.LIGHT_GRAY;

        JFrame frame = new JFrame();
        GridCreator grid = new GridCreator(frame, width, height);
        GridBlocks[][] gridBlocks = grid.getGridBlocks();

        frame.setSize(645, 668);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        frame.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_DOWN:
                        direction = 'D';
                        break;
                    case KeyEvent.VK_UP:
                        direction = 'U';
                        break;
                    case KeyEvent.VK_LEFT:
                        direction = 'L';
                        break;
                    case KeyEvent.VK_RIGHT:
                        direction = 'R';
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        int x = width;
        int y = height;
        int lastYPosition = 0;
        int lastXPosition = 0;
        int counter;
        int leftoverCount = snakeSize;

        while (true) {

            if (direction == 'U') {

                counter = 0;

                while (true) {

                    y += height;

                    if (counter == snakeSize)
                        try {
                            gridBlocks[Math.abs((y + height + snakeSize) % height)][x % width].setBackground(backGroundColor);
                            gridBlocks[Math.abs((y + height + snakeSize) % height)][x % width].free();
                        } catch (ArrayIndexOutOfBoundsException e) {

                        }
                    else {
                        //was going right
                        if (!gridBlocks[lastYPosition][(x - snakeSize + counter) % width].isOccupiedByFood()) {
                            gridBlocks[lastYPosition][(x - snakeSize + counter) % width].setBackground(backGroundColor);
                            gridBlocks[lastYPosition][(x - snakeSize + counter) % width].free();
                        }

                        //was going left
                        if (!gridBlocks[lastYPosition][(x + snakeSize - counter) % width].isOccupiedByFood()) {
                            gridBlocks[lastYPosition][(x + snakeSize - counter) % width].setBackground(backGroundColor);
                            gridBlocks[lastYPosition][(x + snakeSize - counter) % width].free();
                        }

                        counter++;
                    }

                    gridBlocks[(y - height) % height][x % width].setBackground(snakeColor);

                    if (direction == 'L' || direction == 'R') {
                        lastXPosition = x % width;
                        break;
                    }

                    Thread.sleep(snakeSpeed);

                    y--;
                }

            }

            if (direction == 'D') {

                counter = 0;

                while (true) {

                    x += width;

                    if (counter == snakeSize)
                        try {
                            gridBlocks[(y - snakeSize) % height][x % width].setBackground(backGroundColor);
                            gridBlocks[(y - snakeSize) % height][x % width].free();
                        } catch (ArrayIndexOutOfBoundsException e) {

                        }
                    else {
                        //was going right
                        if (!gridBlocks[lastYPosition][(x - snakeSize + counter) % width].isOccupiedByFood()) {
                            gridBlocks[lastYPosition][(x - snakeSize + counter) % width].setBackground(backGroundColor);
                            gridBlocks[lastYPosition][(x - snakeSize + counter) % width].free();
                        }

                        //was going left
                        if (!gridBlocks[lastYPosition][(x + snakeSize - counter) % width].isOccupiedByFood()) {
                            gridBlocks[lastYPosition][(x + snakeSize - counter) % width].setBackground(backGroundColor);
                            gridBlocks[lastYPosition][(x + snakeSize - counter) % width].free();
                        }

                        counter++;
                    }

                    gridBlocks[y % height][x % width].setBackground(snakeColor);

                    //leftover count is not yet used
                    if (direction == 'L' || direction == 'R') {
                        if (counter != snakeSize) leftoverCount = counter;
                        lastXPosition = x % width;
                        lastYPosition = x % height;
                        break;
                    }

                    Thread.sleep(snakeSpeed);

                    y++;
                }

            }

            if (direction == 'L') {

                counter = 0;

                while (true) {

                    if (counter == snakeSize)
                        try {
                            gridBlocks[y % height][Math.abs((x + width + snakeSize) % width)].setBackground(backGroundColor);
                            gridBlocks[y % height][Math.abs((x + width + snakeSize) % width)].free();
                        } catch (ArrayIndexOutOfBoundsException e) {

                        }
                    else {
                        //was going down
                        if (!gridBlocks[(y - snakeSize + counter) % height][lastXPosition].isOccupiedByFood()) {
                            gridBlocks[(y - snakeSize + counter) % height][lastXPosition].setBackground(backGroundColor);
                            gridBlocks[(y - snakeSize + counter) % height][lastXPosition].free();
                        }

                        //was going up
                        if (!gridBlocks[Math.abs((y + height + snakeSize - counter) % height)][lastXPosition].isOccupiedByFood()) {
                            gridBlocks[Math.abs((y + height + snakeSize - counter) % height)][lastXPosition].setBackground(backGroundColor);
                            gridBlocks[Math.abs((y + height + snakeSize - counter) % height)][lastXPosition].free();
                        }

                        counter++;
                    }

                    gridBlocks[y % height][(x - width) % width].setBackground(snakeColor);

                    if (direction == 'U' || direction == 'D') {
                        lastYPosition = y % height;
                        break;
                    }

                    Thread.sleep(snakeSpeed);

                    x--;
                }
            }

            if (direction == 'R') {

                counter = 0;

                while (true) {

                    if (counter == snakeSize)
                        try {
                            gridBlocks[y % height][(x - snakeSize) % width].setBackground(backGroundColor);
                            gridBlocks[y % height][(x - snakeSize) % width].free();
                        } catch (ArrayIndexOutOfBoundsException e) {

                        }
                    else {
                        //was going down
                        if (!gridBlocks[(y - snakeSize + counter) % height][lastXPosition].isOccupiedByFood()) {
                            gridBlocks[(y - snakeSize + counter) % height][lastXPosition].setBackground(backGroundColor);
                            gridBlocks[(y - snakeSize + counter) % height][lastXPosition].free();
                        }

                        //was going up
                        if (!gridBlocks[Math.abs((y + height + snakeSize - counter) % height)][lastXPosition].isOccupiedByFood()) {
                            gridBlocks[Math.abs((y + height + snakeSize - counter) % height)][lastXPosition].setBackground(backGroundColor);
                            gridBlocks[Math.abs((y + height + snakeSize - counter) % height)][lastXPosition].free();
                        }

                        counter++;
                    }

                    gridBlocks[y % height][x % width].setBackground(snakeColor);

                    if (direction == 'U' || direction == 'D') {
                        lastYPosition = y % height;
                        break;
                    }

                    Thread.sleep(snakeSpeed);

                    x++;
                }
            }

        }

    }

}
