
package hw4;

import api.Cell;
import api.Icon;
import api.Piece;
import api.Position;

/**
 * Abstract superclass for implementations of the Piece interface.
 */
public abstract class AbstractPiece implements Piece {
	/**
	 * Position of this Piece
	 */
	private Position position;

	/**
	 * Cells of this Piece
	 */
	private Cell[] cells;

	/**
	 * Constructs a piece with the given position. Subclasses extending this class
	 * MUST call setCells to initialize initial cell positions and icons.
	 * 
	 * @param position initial position for upper-left corner of bounding box
	 */
	protected AbstractPiece(Position position) {
		this.position = position;
	}

	// YOUR CODE HERE TO IMPLEMENT THE Piece INTERFACE

	@Override
	public Piece clone() {
		try {
			AbstractPiece s = (AbstractPiece) super.clone();

			s.cells = new Cell[cells.length];
			for (int i = 0; i < cells.length; ++i) {
				s.cells[i] = new Cell(cells[i]);
			}
			return s;
		} catch (CloneNotSupportedException e) {
			
			return null;
		}
	}

	@Override
	public Position getPosition() {
		return position;
	}

	@Override
	public Cell[] getCells() {
		
		Cell[] cellsCopy = new Cell[cells.length];
		for (int i = 0; i < cellsCopy.length; ++i) {
			cellsCopy[i] = new Cell(cells[i]);
		}
		return cellsCopy;
	}

	@Override
	public Cell[] getCellsAbsolute() {
		Cell[] relativePos = new Cell[cells.length];
		int row = 0;
		;
		int col = 0;
		;
		Icon icon;

		for (int i = 0; i < cells.length; ++i) {
			row = cells[i].getRow() + position.row();
			col = cells[i].getCol() + position.col();
			icon = cells[i].getIcon();
			relativePos[i] = new Cell(icon, new Position(row, col));
		}
		return relativePos;
	}

	@Override
	public void setCells(Cell[] givenCells) {
		cells = givenCells;
	}

	@Override
	public void shiftDown() {
		position = new Position(position.row() + 1, position.col());
	}

	@Override
	public void shiftLeft() {
		position = new Position(position.row(), position.col() - 1);

	}

	@Override
	public void shiftRight() {
		position = new Position(position.row(), position.col() + 1);

	}

	@Override
	public void transform() {
		Cell[] cells = getCells();

		for (Cell cell : cells) {
			int newCol = 2 - cell.getCol();
			cell.setRowCol(cell.getRow(), newCol);
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
}
