package chesspieces;

import chess.Chess;

/**
 * Knight Object Class
 * @author Rishik Sarkar
 */
public class Knight extends Piece
{
	/**
	 * Constructor method for the Knight Object.
	 * @param value
	 */
	public Knight(String value) 
	{
		super(value);
	}
	
	/**
	 * Checks whether or not an intended move for a knight is valid or not.
	 * @param oldPosition inputs the old position of the knight on the chess board
	 * @param newPosition inputs the new intended position of the knight on the chess board
	 * @return true if the move is valid, false if not
	 */
	public boolean valid(String oldPosition, String newPosition)
	{
		if (Chess.chessboard.containsKey(newPosition) == false)
		{
			return false;
		}
		
		String oldLocation = Chess.chessboard.get(oldPosition).getValue();
		String newLocation = Chess.chessboard.get(newPosition).getValue();
		
		int startX = (int)(oldPosition.charAt(0));
		int startY = oldPosition.charAt(1) - '0';
		
		int endX = (int)(newPosition.charAt(0));
		int endY = newPosition.charAt(1) - '0';
		
		int diffX = Math.abs(endX - startX);
		int diffY = Math.abs(endY - startY);
		
		//If new position is a L-shape (1,2) away from old position
		if (Chess.chessboard.containsKey(newPosition) && (diffX == 1 && diffY == 2) || (diffX == 2 && diffY == 1))
		{
			if (Chess.chessboard.get(newPosition).getValue().equals("##") || Chess.chessboard.get(newPosition).getValue().equals("  "))
			{
				return true;
			}
			else
			{
				//Both pieces are the same color
				if (oldLocation.charAt(0) == newLocation.charAt(0))
				{
					return false;
				}
				else
				{
					return true;
				}
			}
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Moves the knight to the new position from its old position.
	 * @param oldPosition inputs the old position of the knight on the chess board
	 * @param newPosition inputs the new intended position of the knight on the chess board
	 */
	public void move (String oldPosition, String newPosition)
	{
		Piece oldPiece = Chess.chessboard.get(oldPosition);
		
		Chess.chessboard.put(newPosition, oldPiece);
		
		if (Chess.isBlack(oldPosition.charAt(0), oldPosition.charAt(1) - '0'))
		{
			Chess.chessboard.put(oldPosition, new EmptySpace("##"));
		}
		else
		{
			Chess.chessboard.put(oldPosition, new EmptySpace("  "));
		}
	}
	
	/**
	 * Overloaded move method for pawn promotion.
	 * Is exactly the same as the move method for the Knight object, since promotion does not occur.
	 * @param oldPosition inputs the old position of the knight on the chess board
	 * @param newPosition inputs the new intended position of the knight on the chess board
	 */
	public void move (String oldPosition, String newPosition, char promotion)
	{
		Piece oldPiece = Chess.chessboard.get(oldPosition);
		
		Chess.chessboard.put(newPosition, oldPiece);
		
		if (Chess.isBlack(oldPosition.charAt(0), oldPosition.charAt(1) - '0'))
		{
			Chess.chessboard.put(oldPosition, new EmptySpace("##"));
		}
		else
		{
			Chess.chessboard.put(oldPosition, new EmptySpace("  "));
		}
	}
}
