package hw4;

import api.Cell;
import api.Icon;
import api.Position;

public class SnakingPiece extends AbstractPiece {
    private static final Position[] INITIAL_POSITIONS = {
        new Position(0, 0),
        new Position(1, 0),
        new Position(1, 1),
        new Position(1, 2)
    };

    // Correctly verified twelve states
    private static final Position[][] TRANSFORM_STATES = {
        { new Position(0, 0), new Position(1, 0), new Position(1, 1), new Position(1, 2) },
        { new Position(0, 1), new Position(0, 0), new Position(1, 0), new Position(1, 1) },
        { new Position(0, 2), new Position(0, 1), new Position(0, 0), new Position(1, 0) },
        { new Position(1, 2), new Position(0, 2), new Position(0, 1), new Position(0, 0) },
        { new Position(2, 2), new Position(1, 2), new Position(0, 2), new Position(0, 1) },
        { new Position(2, 1), new Position(2, 2), new Position(1, 2), new Position(0, 2) },
        { new Position(2, 0), new Position(2, 1), new Position(2, 2), new Position(1, 2) },
        { new Position(1, 0), new Position(2, 0), new Position(2, 1), new Position(2, 2) },
        { new Position(0, 0), new Position(1, 0), new Position(2, 0), new Position(2, 1) },
        { new Position(0, 1), new Position(0, 0), new Position(1, 0), new Position(2, 0) },
        { new Position(0, 2), new Position(0, 1), new Position(0, 0), new Position(1, 0) },
        { new Position(1, 2), new Position(0, 2), new Position(0, 1), new Position(0, 0) }
    };

    private int currentState = 0;

    /**
     * Constructs a piece with the given position. Subclasses extending this class
     * MUST call setCells to initialize initial cell positions and icons.
     * 
     * @param position initial position for upper-left corner of bounding box
     */
    protected SnakingPiece(Position position, Icon[] icons) {
        super(position);

        if (icons.length != 4) {
            throw new IllegalArgumentException("SnakingPiece requires exactly 4 icons.");
        }

        Cell[] cells = new Cell[4];
        for (int i = 0; i < cells.length; i++) {
            cells[i] = new Cell(icons[i], INITIAL_POSITIONS[i]);
        }
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
        currentState = (currentState + 1) % 12;
        Cell[] cells = getCells();
        for (int i = 0; i < cells.length; i++) {
            cells[i].setPosition(TRANSFORM_STATES[currentState][i]);
        }
        setCells(cells);
    }
}

