import GridComponents.GridBlocks;
import GridComponents.GridCreator;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {

    static char direction = 'R';
    static char snakeSpeed = 100;
    static boolean hasMovedUp = false;
    static boolean hasMoveDown = false;
    static boolean hasMovedLeft = false;
    static boolean hasMovedRight = false;

    public static void main(String[] args) throws InterruptedException {

        int width = 30;
        int height = 30;
        int snakeSize = 5;

        JFrame frame = new JFrame();
        ImageIcon icon = new ImageIcon("Icon.png");
        GridCreator grid = new GridCreator(frame, width, height);
        GridBlocks[][] gridBlocks = grid.getGridBlocks();
        GridBlocks[] tails = new GridBlocks[50000];

        frame.setTitle("Bajed's Big Snake");
        frame.setIconImage(icon.getImage());
        frame.setSize(645, 668);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
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
                        if(!(hasMovedLeft || hasMovedRight)) break;
                        direction = 'D';
                        hasMovedLeft = false;
                        hasMovedRight = false;
                        break;
                    case KeyEvent.VK_UP:
                        if (direction == 'D') break;
                        if(!(hasMovedLeft || hasMovedRight)) break;
                        direction = 'U';
                        hasMovedLeft = false;
                        hasMovedRight = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        if (direction == 'R') break;
                        if (!(hasMovedUp || hasMoveDown)) break;
                        direction = 'L';
                        hasMovedUp = false;
                        hasMoveDown = false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (direction == 'L') break;
                        if (!(hasMovedUp || hasMoveDown)) break;
                        direction = 'R';
                        hasMovedUp = false;
                        hasMoveDown = false;
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

        grid.enableBarriers();
        generateFood(gridBlocks);

        while (true) {

            if (direction == 'U') {
                y += height;

                if (gridBlocks[y % height][x % width].isOccupied()) break;

                if (counter == snakeSize) {
                    tails[tailFetcherIndex++].free();
                } else
                    counter++;

                if (gridBlocks[(y - height) % height][x % width].isOccupiedByFood()) {
                    snakeSize++;
                    snakeSpeed--;
                    generateFood(gridBlocks);
                }

                gridBlocks[(y - height) % height][x % width].occupy();
                tails[tailIndex++] = gridBlocks[(y - height) % height][x % width];

                y--;
                hasMovedUp = true;
            }

            if (direction == 'D') {

                if (gridBlocks[y % height][x % width].isOccupied()) break;

                if (counter == snakeSize) {
                    tails[tailFetcherIndex++].free();
                } else
                    counter++;

                if (gridBlocks[y % height][x % width].isOccupiedByFood()) {
                    snakeSize++;
                    snakeSpeed--;
                    generateFood(gridBlocks);
                }

                gridBlocks[y % height][x % width].occupy();
                tails[tailIndex++] = gridBlocks[y % height][x % width];

                y++;
                hasMoveDown = true;
            }

            if (direction == 'L') {
                x += width;

                if (gridBlocks[y % height][x % width].isOccupied()) break;

                if (counter == snakeSize) {
                    tails[tailFetcherIndex++].free();
                } else
                    counter++;

                if (gridBlocks[y % height][(x - width) % width].isOccupiedByFood()) {
                    snakeSize++;
                    snakeSpeed--;
                    generateFood(gridBlocks);
                }

                gridBlocks[y % height][(x - width) % width].occupy();
                tails[tailIndex++] = gridBlocks[y % height][(x - width) % width];

                x--;
                hasMovedLeft = true;
            }

            if (direction == 'R') {

                if (gridBlocks[y % height][x % width].isOccupied()) break;

                if (counter == snakeSize) {
                    tails[tailFetcherIndex++].free();
                } else
                    counter++;

                if (gridBlocks[y % height][x % width].isOccupiedByFood()) {
                    snakeSize++;
                    snakeSpeed--;
                    generateFood(gridBlocks);
                }
                gridBlocks[y % height][x % width].occupy();
                tails[tailIndex++] = gridBlocks[y % height][x % width];

                x++;
                hasMovedRight = true;
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
        while (true) {
            int x = (int) (Math.random() * space.length);
            int y = (int) (Math.random() * space[0].length);

            if (!space[y][x].isOccupied() || !space[y][x].isOccupiedByFood()) {
                space[y][x].occupyWithFood();
                break;
            }
        }
    }

}
