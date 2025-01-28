
package hw4;

import java.util.Random;

import api.Generator;
import api.Icon;
import api.Piece;
import api.Position;

import examples.SamplePiece;

/**
 * Generator for Piece objects in BlockAddiction. Icons are always selected
 * uniformly at random, and the Piece types are generated with different
 * probabilities.
 *
 * The initial position of each piece is based on its vertical size as well as
 * the width of the grid (given as an argument to getNext). The initial column
 * is always width/2 - 1. The initial row is usually set to -2 for Pieces with
 * 3x3 bounding boxes.
 */
public class BasicGenerator implements Generator
{
	private Random rand;

	/**
	 * Constructs a BasicGenerator that will use the given Random object as its source of randomness.
	 *
	 * @param givenRandom instance of Random to use
	 */
	public BasicGenerator()
	{
		rand = new Random();
	}

	@Override
	public Piece getNext(int width)
	{

		int col = width / 2 - 1;

		int p = rand.nextInt(100);

		// TODO: change this to return the different concrete Pieces
	        // by commenting it out and using the next if statement (in
	        // the comment), with modifications.  
		return new LShapedPiece(new Position(-1, col), getBlocks(4));

		/*
		 * The following if-else statement should be enabled
		 * one piece as a time while you work.  It should be
		 * done after commenting out the preceding if statement.
		  
		
		if (p < 10)
		{
			return new LShapedPiece(new Position(-1, col),
					  getBlocks(4));
		}
		else if (p < 35)
		{
			return new TeePiece(new Position(-1, col),
						 getBlocks(4));
		}
		else if (p < 50)
		{
			return new CirclingPiece(new Position(-1, col),
					       getBlocks(4));
		}
		else if (p < 60)
		{
			return new SnakingPiece(new Position(-1, col),
					      getBlocks(4));
		}
		else if (p < 80)
		{
			return new QuotesPiece(new Position(-1, col),
					      getBlocks(4));
		}
		else
		{
			return new RotatingSPiece(new Position(-1, col),
					  getBlocks(4));
		}
		*/
		
	}

	@Override
	public Icon randomIcon()
	{

		return new Icon(Icon.COLORS[rand.nextInt(Icon.COLORS.length)]);

	}

	private Icon[] getBlocks(int num)
	{
		Icon[] icons = new Icon[num];
		for (int i = 0; i < icons.length; i++)
		{
			icons[i] = randomIcon();
		}

		return icons;
	}
}
