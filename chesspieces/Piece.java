package chesspieces;

/**
 * Piece Object Class
 * @author Rishik Sarkar
 */
public abstract class Piece 
{
	String value;
	boolean moved = false;

	/**
	 * Constructor method for the Piece Object.
	 * @param value inputs the String value of the piece, eg. bN, wQ, etc.
	 */
	public Piece(String value) 
	{
		this.value = value;
		moved = false;
	}
	
	/**
	 * Returns the value of the piece, eg. bN, wQ, etc.
	 * @return value
	 */
	public String getValue()
	{
		return this.value;
	}
	
	/**
	 * Checks if the piece has been moved or not.
	 * @return moved
	 */
	public boolean getMoved()
	{
		return this.moved;
	}
	
	/**
	 * Sets the moved value for the piece.
	 * @param moved
	 */
	public void setMoved(boolean moved)
	{
		this.moved = moved;
	}
	
	/**
	 * Moves the piece from the old position to the intended new position.
	 * @param oldPosition
	 * @param newPosition
	 */
	public void move(String oldPosition, String newPosition)
	{
		return;
	}
	
	/**
	 * Overloaded move method to account for pawn promotion.
	 * @param oldPosition
	 * @param newPosition
	 */
	public void move(String oldPosition, String newPosition, char promotion)
	{
		return;
	}
	
	/**
	 * Checks whether or not an intended move for a piece is valid or not.
	 * @param oldPosition inputs the old position of the piece on the chess board
	 * @param newPosition inputs the new intended position of the piece on the chess board
	 * @return true if the move is valid, false if not
	 */
	public boolean valid(String oldPosition, String newPosition)
	{
		return true;
	}
	
	/**
	 * Checks whether or not the piece has a clear path from its old position to its intended new position.
	 * @param oldPosition
	 * @param newPosition
	 * @return true if the path is clear, false if there is an obstruction
	 */
	public boolean isPathClear(String oldPosition, String newPosition)
	{
		return true;
	}
	
	/**
	 * Returns whether or not the pawn has moved two rows in its last move.
	 * @return doubleMove
	 */
	public boolean getDoubleMove()
	{
		return false;
	}
	
	/**
	 * Returns the round number when the pawn made a double move.
	 * @return doubleCounter
	 */
	public int getDoubleCounter()
	{
		return 0;
	}
}
