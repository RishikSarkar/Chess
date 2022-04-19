package chesspieces;

import chess.Chess;

/**
 * King Object Class
 * @author Rishik Sarkar
 */
public class King extends Piece
{
	/**
	 * Constructor method for the King Object.
	 * @param value
	 */
	public King(String value) 
	{
		super(value);
	}
	
	/**
	 * Checks whether or not an intended move for the king is valid or not.
	 * @param oldPosition inputs the old position of the king on the chess board
	 * @param newPosition inputs the new intended position of the king on the chess board
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
		
		int horizontal = Math.abs((oldPosition.charAt(0)) - (newPosition.charAt(0)));
		int vertical = Math.abs((oldPosition.charAt(1)) - (newPosition.charAt(1)));
		
		//If new position is exactly 1 space away from the old position in any direction
		if (!(horizontal == 0 && vertical == 0) && (horizontal == 0 || horizontal == 1) && (vertical == 0 || vertical == 1))
		{
			//If new position is empty or not
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
	 * Checks whether or not the inputted location on the chess board is empty or not.
	 * @param letter
	 * @param number
	 * @return true if the space is empty, false if there is a piece there.
	 */
	private boolean isEmpty(char letter, int number)
	{
		String location = Character.toString(letter) + Integer.toString(number);
		
		if (Chess.chessboard.get(location).getValue().equals("##") || Chess.chessboard.get(location).getValue().equals("  "))
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * Checks whether or not the king has a clear path from its old position to its intended new position for castling.
	 * @param oldPosition
	 * @param newPosition
	 * @return true if the path is clear, false if there is an obstruction.
	 */
	public boolean isPathClear(String oldPosition, String newPosition)
	{
		//Same row
		if (oldPosition.charAt(1) == newPosition.charAt(1))
		{
			char oldLetter = oldPosition.charAt(0);
			char newLetter = newPosition.charAt(0);
			
			//Moving right
			if (oldLetter < newLetter)
			{
				for (char i = (char) (oldLetter + 1); i < newLetter; i++)
				{
					if (!(isEmpty(i, oldPosition.charAt(1) - '0')))
					{
						return false;
					}
				}
			}
			//Moving left
			else
			{
				for (char i = (char) (newLetter + 1); i < oldLetter; i++)
				{
					if (!(isEmpty(i, oldPosition.charAt(1) - '0')))
					{
						return false;
					}
				}
			}
			
			return true;
		}
		
		return true;
	}
	
	/**
	 * Moves the king to the new position from its old position.
	 * @param oldPosition inputs the old position of the king on the chess board
	 * @param newPosition inputs the new intended position of the king on the chess board
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
	 * Is exactly the same as the move method for the King object, since promotion does not occur.
	 * @param oldPosition inputs the old position of the king on the chess board
	 * @param newPosition inputs the new intended position of the king on the chess board
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
