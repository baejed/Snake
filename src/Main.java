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
                        if (direction == 'U') break;
                        direction = 'D';
                        break;
                    case KeyEvent.VK_UP:
                        if (direction == 'D') break;
                        direction = 'U';
                        break;
                    case KeyEvent.VK_LEFT:
                        if (direction == 'R') break;
                        direction = 'L';
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (direction == 'L') break;
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

        generateFood(gridBlocks);

        while (true) {

            if (direction == 'U') {
                y += height;

                if(gridBlocks[y % height][x % width].isOccupied()) break;

                if (counter == snakeSize) {
                    tails[tailFetcherIndex++].free();
                } else
                    counter++;

                if(gridBlocks[(y - height) % height][x % width].isOccupiedByFood()){
                    snakeSize++;
                    generateFood(gridBlocks);
                }

                gridBlocks[(y - height) % height][x % width].occupy();
                tails[tailIndex++] = gridBlocks[(y - height) % height][x % width];

                y--;
            }

            if (direction == 'D') {

                if(gridBlocks[y % height][x % width].isOccupied()) break;

                if (counter == snakeSize) {
                    tails[tailFetcherIndex++].free();
                } else
                    counter++;

                if(gridBlocks[y % height][x % width].isOccupiedByFood()){
                    snakeSize++;
                    generateFood(gridBlocks);
                }

                gridBlocks[y % height][x % width].occupy();
                tails[tailIndex++] = gridBlocks[y % height][x % width];

                y++;
            }

            if (direction == 'L') {
                x += width;

                if(gridBlocks[y % height][x % width].isOccupied()) break;

                if (counter == snakeSize) {
                    tails[tailFetcherIndex++].free();
                } else
                    counter++;

                if(gridBlocks[y % height][(x - width) % width].isOccupiedByFood()){
                    snakeSize++;
                    generateFood(gridBlocks);
                }
                gridBlocks[y % height][(x - width) % width].occupy();
                tails[tailIndex++] = gridBlocks[y % height][(x - width) % width];

                x--;
            }

            if (direction == 'R') {

                if(gridBlocks[y % height][x % width].isOccupied()) break;

                if (counter == snakeSize) {
                    tails[tailFetcherIndex++].free();
                } else
                    counter++;

                if(gridBlocks[y % height][x % width].isOccupiedByFood()){
                    snakeSize++;
                    generateFood(gridBlocks);
                }
                gridBlocks[y % height][x % width].occupy();
                tails[tailIndex++] = gridBlocks[y % height][x % width];

                x++;
            }

            Thread.sleep(snakeSpeed);

        }

        gameOverScreen();

    }

    public static void gameOverScreen() {

        JOptionPane optionPane = new JOptionPane(new JLabel("Game Over", JLabel.CENTER), JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_OPTION, null, new String[]{"OK"});
        JDialog dialog = optionPane.createDialog("Snake");
        dialog.setModal(true);
        dialog.setVisible(true);
        System.exit(0);

    }

    public static void generateFood(GridBlocks[][] space) {
        while(true){
            int x = (int)(Math.random() * space.length);
            int y = (int)(Math.random() * space[0].length);

            if(!space[y][x].isOccupied()){
                space[y][x].occupyWithFood();
                break;
            }
        }
    }

    public void collisionChecker() {

    }

}
