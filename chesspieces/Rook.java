package chesspieces;

import chess.Chess;

/**
 * Rook Object Class
 * @author Rishik Sarkar
 */
public class Rook extends Piece
{
	/**
	 * Constructor method for the Rook Object.
	 * @param value
	 */
	public Rook(String value) 
	{
		super(value);
	}
	
	/**
	 * Checks whether or not an intended move for a rook is valid or not.
	 * @param oldPosition inputs the old position of the rook on the chess board
	 * @param newPosition inputs the new intended position of the rook on the chess board
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
		
		//If new position is in the same row or column as old position
		if ((oldPosition.charAt(0) == newPosition.charAt(0) || oldPosition.charAt(1) == newPosition.charAt(1)) && !(oldPosition.equals(newPosition)))
		{
			//If new position is empty or not
			if (Chess.chessboard.get(newPosition).getValue().equals("##") || Chess.chessboard.get(newPosition).getValue().equals("  "))
			{
				if (isPathClear(oldPosition, newPosition))
				{
					return true;
				}
				else
				{
					return false;
				}
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
					if (isPathClear(oldPosition, newPosition))
					{
						return true;
					}
					else
					{
						return false;
					}
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
	 * Checks whether or not the rook has a clear path from its old position to its intended new position.
	 * @param oldPosition
	 * @param newPosition
	 * @return true if the path is clear, false if there is an obstruction.
	 */
	public boolean isPathClear(String oldPosition, String newPosition)
	{
		//Same column
		if (oldPosition.charAt(0) == newPosition.charAt(0))
		{
			int oldNumber = oldPosition.charAt(1) - '0';
			int newNumber = newPosition.charAt(1) - '0';
			
			//Moving up
			if (oldNumber < newNumber)
			{
				for (int i = oldNumber + 1; i < newNumber; i++)
				{
					if (!(isEmpty(oldPosition.charAt(0), i)))
					{
						return false;
					}
				}
			}
			//Moving down
			else
			{
				for (int i = newNumber + 1; i < oldNumber; i++)
				{
					if (!(isEmpty(oldPosition.charAt(0), i)))
					{
						return false;
					}
				}
			}
		}
		//Same row
		else if (oldPosition.charAt(1) == newPosition.charAt(1))
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
	 * Moves the rook to the new position from its old position.
	 * @param oldPosition inputs the old position of the rook on the chess board
	 * @param newPosition inputs the new intended position of the rook on the chess board
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
	 * Is exactly the same as the move method for the Rook object, since promotion does not occur.
	 * @param oldPosition inputs the old position of the rook on the chess board
	 * @param newPosition inputs the new intended position of the rook on the chess board
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
