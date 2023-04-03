package GridComponents;

import javax.swing.*;
import java.awt.*;

public class GridCreator {

    private GridBlocks[][] gridBlocks;

    public GridCreator(JFrame frame, int x, int y) {

        int gap = 1;

        gridBlocks = new GridBlocks[y][x];

        for (int i = 0, yPos = 0; i < gridBlocks.length; i++, yPos += (20 + gap)) {
            for (int j = 0, xPos = 0; j < gridBlocks[i].length; j++, xPos += (20 + gap)) {
                gridBlocks[i][j] = new GridBlocks(xPos, yPos);
            }
        }

        frame.setVisible(true);

        for (int i = 0; i < gridBlocks.length; i++) {
            for (int j = 0; j < gridBlocks[i].length; j++) {
                frame.add(gridBlocks[i][j]);
            }
        }

    }

    public GridBlocks[][] getGridBlocks() {
        return gridBlocks;
    }

}
