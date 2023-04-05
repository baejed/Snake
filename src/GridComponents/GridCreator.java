package GridComponents;

import javax.swing.*;
import java.awt.*;

public class GridCreator {

    private GridBlocks[][] gridBlocks;

    public GridCreator(JFrame frame, int x, int y) {

        int gap = 1;

        gridBlocks = new GridBlocks[y][x];

        frame.setVisible(true);

        for (int i = 0, yPos = 0; i < gridBlocks.length; i++, yPos += (20 + gap)) {
            for (int j = 0, xPos = 0; j < gridBlocks[i].length; j++, xPos += (20 + gap)) {
                gridBlocks[i][j] = new GridBlocks(xPos, yPos);
            }
        }

        for (int i = 0; i < gridBlocks.length; i++) {
            for (int j = 0; j < gridBlocks[i].length; j++) {
                frame.add(gridBlocks[i][j]);
            }
        }

    }

    public void enableBarriers() {
        for (int i = 0; i < gridBlocks.length; i++) {
            //vertical barriers
            gridBlocks[i][0].makeBarrier();
            gridBlocks[i][gridBlocks.length - 1].makeBarrier();

            //horizontal barriers
            gridBlocks[0][i].makeBarrier();
            gridBlocks[gridBlocks[0].length - 1][i].makeBarrier();
        }
    }

    public void disableBarriers() {
        for (int i = 0; i < gridBlocks.length; i++) {
            //vertical barriers
            gridBlocks[i][0].free();
            gridBlocks[i][gridBlocks.length - 1].free();

            //horizontal barriers
            gridBlocks[0][i].free();
            gridBlocks[gridBlocks[0].length - 1][i].free();
        }
    }

    public GridBlocks[][] getGridBlocks() {
        return gridBlocks;
    }

}
