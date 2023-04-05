import GridComponents.GridBlocks;
import GridComponents.GridCreator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {

    static char direction = 'R';
    static char snakeSpeed = 100;

    public static void main(String[] args) throws InterruptedException {

        int width = 30;
        int height = 30;
        int snakeSize = 5;

        Color snakeColor = Color.DARK_GRAY;

        JFrame frame = new JFrame();
        GridCreator grid = new GridCreator(frame, width, height);
        GridBlocks[][] gridBlocks = grid.getGridBlocks();
        GridBlocks[] tails = new GridBlocks[10000];

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

        int x = width + 1;
        int y = height + 1;
        int counter = 0;
        int tailIndex = 0;
        int tailFetcherIndex = 0;

        while (true) {

            if (direction == 'U') {

                while (true) {

                    y += height;

                    if (counter == snakeSize) {
                        tails[tailFetcherIndex++].free();
                    } else
                        counter++;

                    gridBlocks[(y - height) % height][x % width].setBackground(snakeColor);
                    tails[tailIndex++] = gridBlocks[(y - height) % height][x % width];

                    if ((direction == 'L' || direction == 'R')) {
                        break;
                    }

                    Thread.sleep(snakeSpeed);

                    y--;
                }

            }

            if (direction == 'D') {

                while (true) {

                    if (counter == snakeSize) {
                        tails[tailFetcherIndex++].free();
                    } else
                        counter++;

                    gridBlocks[y % height][x % width].setBackground(snakeColor);
                    tails[tailIndex++] = gridBlocks[y % height][x % width];

                    if (direction == 'L' || direction == 'R') {
                        break;
                    }

                    Thread.sleep(snakeSpeed);

                    y++;
                }

            }

            if (direction == 'L') {

                while (true) {

                    x += width;

                    if (counter == snakeSize) {
                        tails[tailFetcherIndex++].free();
                    } else
                        counter++;

                    gridBlocks[y % height][(x - width) % width].setBackground(snakeColor);
                    tails[tailIndex++] = gridBlocks[y % height][(x - width) % width];

                    if ((direction == 'U' || direction == 'D')) {
                        break;
                    }

                    Thread.sleep(snakeSpeed);

                    x--;
                }
            }

            if (direction == 'R') {

                while (true) {

                    if (counter == snakeSize) {
                        tails[tailFetcherIndex++].free();
                    } else
                        counter++;

                    gridBlocks[y % height][x % width].setBackground(snakeColor);
                    tails[tailIndex++] = gridBlocks[y % height][x % width];

                    if (direction == 'U' || direction == 'D') {
                        break;
                    }

                    Thread.sleep(snakeSpeed);

                    x++;
                }
            }

        }

    }

}
