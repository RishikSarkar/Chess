package chesspieces;

import chess.Chess;

/**
 * Pawn Object Class
 * @author Rishik Sarkar
 */
public class Pawn extends Piece
{
	public boolean doubleMove;
	public int doubleCounter;
	
	/**
	 * Constructor method for the Pawn Object.
	 * @param value
	 */
	public Pawn(String value)
	{
		super(value);
		doubleMove = false;
		doubleCounter = 0;
	}
	
	/**
	 * Checks whether or not an intended move for a pawn is valid or not.
	 * @param oldPosition inputs the old position of the pawn on the chess board
	 * @param newPosition inputs the new intended position of the pawn on the chess board
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
		
		char oldPosLetter = oldPosition.charAt(0);
		int oldPosNumber = oldPosition.charAt(1) - '0';
		char newPosLetter = newPosition.charAt(0);
		int newPosNumber = newPosition.charAt(1) - '0';
		
		//White Pawn
		if (oldLocation.charAt(0) == 'w')
		{
			if (newPosNumber <= oldPosNumber)
			{
				return false;
			}
			
			if (newPosLetter != oldPosLetter)
			{
				if ((newPosNumber - oldPosNumber) != 1 || Math.abs(newPosLetter - oldPosLetter) != 1 || isEmpty(newPosLetter, newPosNumber) == true)
				{
					return false;
				}
				
				if (oldLocation.charAt(0) == newLocation.charAt(0))
				{
					return false;
				}
				
				return true;
			}
			
			if (Math.abs(newPosNumber - oldPosNumber) == 2)
			{
				if (oldPosNumber != 2)
				{
					return false;
				}
				if (!(isPathClear(oldPosition, newPosition)))
				{
					return false;
				}
				
				doubleMove = true;
				doubleCounter = Chess.gamecounter;
				
				return true;
			}
			else if (Math.abs(newPosNumber - oldPosNumber) == 1)
			{
				if (!isPathClear(oldPosition, newPosition))
				{
					return false;
				}
				
				return true;
			}
			else
			{
				return false;
			}
		}
		//Black Pawn
		else
		{
			if (newPosNumber >= oldPosNumber)
			{
				return false;
			}
			
			if (newPosLetter != oldPosLetter)
			{
				if ((oldPosNumber - newPosNumber) != 1 || Math.abs(newPosLetter - oldPosLetter) != 1 || isEmpty(newPosLetter, newPosNumber) == true)
				{
					return false;
				}
				
				if (oldLocation.charAt(0) == newLocation.charAt(0))
				{
					return false;
				}
				
				return true;
			}
			
			if (Math.abs(newPosNumber - oldPosNumber) == 2)
			{
				if (oldPosNumber != 7)
				{
					return false;
				}
				if (!(isPathClear(oldPosition, newPosition)))
				{
					return false;
				}
				
				doubleMove = true;
				doubleCounter = Chess.gamecounter;
				
				return true;
			}
			else if (Math.abs(newPosNumber - oldPosNumber) == 1)
			{
				if (!isPathClear(oldPosition, newPosition))
				{
					return false;
				}
				
				return true;
			}
			else
			{
				return false;
			}
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
	 * Checks whether or not the pawn has a clear path from its old position to its intended new position.
	 * @param oldPosition
	 * @param newPosition
	 * @return true if the path is clear, false if there is an obstruction.
	 */
	public boolean isPathClear(String oldPosition, String newPosition)
	{
		int oldNumber = oldPosition.charAt(1) - '0';
		int newNumber = newPosition.charAt(1) - '0';
		
		if (oldNumber < newNumber)
		{
			for (int i = oldNumber + 1; i <= newNumber; i++)
			{
				if (!(isEmpty(oldPosition.charAt(0), i)))
				{
					return false;
				}
			}
		}
		else
		{
			for (int i = newNumber; i < oldNumber; i++)
			{
				if (!(isEmpty(oldPosition.charAt(0), i)))
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Moves the pawn to the new position from its old position.
	 * @param oldPosition inputs the old position of the pawn on the chess board
	 * @param newPosition inputs the new intended position of the pawn on the chess board
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
	 * @param oldPosition inputs the old position of the pawn on the chess board
	 * @param newPosition inputs the new intended position of the pawn on the chess board
	 */
	public void move (String oldPosition, String newPosition, char promotion)
	{
		Piece oldPiece = Chess.chessboard.get(oldPosition);
		
		int newNumber = newPosition.charAt(1) - '0';
		
		Chess.chessboard.put(newPosition, oldPiece);
		
		if (Chess.isBlack(oldPosition.charAt(0), oldPosition.charAt(1) - '0'))
		{
			Chess.chessboard.put(oldPosition, new EmptySpace("##"));
		}
		else
		{
			Chess.chessboard.put(oldPosition, new EmptySpace("  "));
		}
		
		if (newNumber == 1 || newNumber == 8)
		{
			promote(newPosition, promotion);
		}
	}
	
	/**
	 * Promotes pawn.
	 * @param newPosition inputs the new position of the pawn on the chess board
	 * @param promotion inputs the intended piece that the pawn will be promoted to
	 */
	private void promote(String newPosition, char promotion)
	{
		int newNumber = newPosition.charAt(1) - '0';
		
		Piece promotionType;
		
		if (promotion == 'R')
		{
			if (newNumber == 8)
			{
				promotionType = new Rook("wR");
			}
			else
			{
				promotionType = new Rook("bR");
			}
			Chess.chessboard.put(newPosition, promotionType);
		}
		else if (promotion == 'B')
		{
			if (newNumber == 8)
			{
				promotionType = new Rook("wB");
			}
			else
			{
				promotionType = new Rook("bB");
			}
			Chess.chessboard.put(newPosition, promotionType);
		}
		else if (promotion == 'N')
		{
			if (newNumber == 8)
			{
				promotionType = new Rook("wN");
			}
			else
			{
				promotionType = new Rook("bN");
			}
			Chess.chessboard.put(newPosition, promotionType);
		}
		else if (promotion == 'Q')
		{
			if (newNumber == 8)
			{
				promotionType = new Rook("wQ");
			}
			else
			{
				promotionType = new Rook("bQ");
			}
			Chess.chessboard.put(newPosition, promotionType);
		}
	}
	
	/**
	 * Returns whether or not the pawn has moved two rows in its last move.
	 * @return doubleMove
	 */
	public boolean getDoubleMove()
	{
		return doubleMove;
	}
	
	/**
	 * Returns the round number when the pawn made a double move.
	 * @return doubleCounter
	 */
	public int getDoubleCounter()
	{
		return doubleCounter;
	}
}
