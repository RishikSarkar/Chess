package chesspieces;

import java.util.ArrayList;

import chess.Chess;

/**
 * Bishop Object Class
 * @author Rishik Sarkar
 */
public class Bishop extends Piece
{
	/**
	 * Constructor method for the Bishop Object.
	 * @param value
	 */
	public Bishop(String value) 
	{
		super(value);
	}
	
	/**
	 * Checks whether or not an intended move for a bishop is valid or not.
	 * @param oldPosition inputs the old position of the bishop on the chess board
	 * @param newPosition inputs the new intended position of the bishop on the chess board
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
		
		//If new position is in the same diagonal as old position
		if ((Math.abs(oldPosition.charAt(0) - newPosition.charAt(0)) == (Math.abs(oldPosition.charAt(1) - newPosition.charAt(1)))) && !(oldPosition.equals(newPosition)))
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
	 * Checks whether or not the bishop has a clear diagonal path from its old position to its intended new position.
	 * @param oldPosition
	 * @param newPosition
	 * @return true if the path is clear, false if there is an obstruction.
	 */
	public boolean isPathClear(String oldPosition, String newPosition)
	{
		ArrayList<String> spaces = diagonalSpaces(oldPosition, newPosition);
		
		for (String i : spaces)
		{
			if (!(Chess.chessboard.get(i).getValue().equals("##") || Chess.chessboard.get(i).getValue().equals("  ")))
			{
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Returns an ArrayList containing all pieces/spaces between the old and new positions, to be checked in the isPathClear() method
	 * @param oldPosition
	 * @param newPosition
	 * @return an ArrayList<String> containing all the pieces/spaces in the diagonal between the old position and the intended new position
	 */
	public static ArrayList<String> diagonalSpaces(String oldPosition, String newPosition)
	{
		ArrayList<String> spaces = new ArrayList<String>();
		
		int startX = (int)(oldPosition.charAt(0));
		int startY = oldPosition.charAt(1) - '0';
		
		int endX = (int)(newPosition.charAt(0));
		int endY = newPosition.charAt(1) - '0';
		
		//Either 1 or -1
		int slopeX = Math.abs(endX - startX) / (endX - startX);
		int slopeY = Math.abs(endY - startY) / (endY - startY);
		
		for (int i = 1; i < Math.abs(endX - startX); i++)
		{
			char nextX = (char)(startX + (i * slopeX));
			int nextY = startY + (i * slopeY);
			
			spaces.add(Character.toString(nextX) + Integer.toString(nextY));
		}
		
		return spaces;
	}
	
	/**
	 * Moves the bishop to the new position from its old position.
	 * @param oldPosition inputs the old position of the bishop on the chess board
	 * @param newPosition inputs the new intended position of the bishop on the chess board
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
	 * Is exactly the same as the move method for the Bishop object, since promotion does not occur.
	 * @param oldPosition inputs the old position of the bishop on the chess board
	 * @param newPosition inputs the new intended position of the bishop on the chess board
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
