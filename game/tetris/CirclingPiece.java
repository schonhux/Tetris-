package hw4;

import api.Cell;
import api.Icon;
import api.Position;

public class CirclingPiece extends AbstractPiece {

    /**
     * Constructs a piece with the given position. Subclasses extending this class
     * MUST call setCells to initialize initial cell positions and icons.
     * 
     * @param position initial position for upper-left corner of bounding box
     */
    protected CirclingPiece(Position position, Icon[] icons) {
        super(position);

        if (icons.length != 4) {
            throw new IllegalArgumentException("CirclingPiece requires exactly 4 icons.");
        }

        Cell[] cells = new Cell[4];
        cells[0] = new Cell(icons[0], new Position(0, 0));
        cells[1] = new Cell(icons[1], new Position(1, 0));
        cells[2] = new Cell(icons[2], new Position(2, 0));
        cells[3] = new Cell(icons[3], new Position(2, 1));
        setCells(cells);
    }

    @Override
    public void cycle() {
        Cell[] cells = getCells();
     
        if (cells.length > 1) {
            Icon lastIcon = cells[cells.length - 1].getIcon();

            for (int i = cells.length - 1; i > 0; --i) {
                cells[i].setIcon(cells[i - 1].getIcon());
            }
            
            cells[0].setIcon(lastIcon);
        }
        setCells(cells);
    }

    @Override
    public void transform() {
        Cell[] cells = getCells();
        for (Cell cell : cells) {
            int row = cell.getRow();
            int col = cell.getCol();
            if (row == 0 && col < 2) {
                cell.setRowCol(row, col + 1);
            } else if (col == 2 && row < 2) {
                cell.setRowCol(row + 1, col);
            } else if (row == 2 && col > 0) {
                cell.setRowCol(row, col - 1);
            } else if (col == 0 && row > 0) {
                cell.setRowCol(row - 1, col);
            }
        }
        setCells(cells);
    }
}



