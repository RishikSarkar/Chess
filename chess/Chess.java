package chess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import chesspieces.*;

/**
 * @author Rishik Sarkar
 */

public class Chess 
{
	public static HashMap<String, Piece> chessboard = new HashMap<String, Piece>(64);
	
	public static int gamecounter = 0;
	
	/**
	 * Creates and sets up a chess board using a hashmap, 
	 * and adds white and black spaces (in the form of "  " and "##"), 
	 * and adds white and black pieces.
	 */
	public static void createBoard()
	{
		for (char letter = 'a'; letter <= 'h'; letter++)
		{
			for (int number = 1; number <= 8; number++)
			{
				String location = Character.toString(letter) + Integer.toString(number);
				
				if (isBlack(letter, number))
				{
					chessboard.put(location, new EmptySpace("##"));
				}
				else
				{
					chessboard.put(location, new EmptySpace("  "));
				}
				
				// White Pieces
				if (location.equals("a1") || location.equals("h1"))
				{
					chessboard.put(location, new Rook("wR"));
				}
				else if (location.equals("b1") || location.equals("g1"))
				{
					chessboard.put(location, new Knight("wN"));
				}
				else if (location.equals("c1") || location.equals("f1"))
				{
					chessboard.put(location, new Bishop("wB"));
				}
				else if (location.equals("d1"))
				{
					chessboard.put(location, new Queen("wQ"));
				}
				else if (location.equals("e1"))
				{
					chessboard.put(location, new King("wK"));
				}
				else if (number == 2)
				{
					chessboard.put(location, new Pawn("wp"));
				}
				
				//Black Pieces
				if (location.equals("a8") || location.equals("h8"))
				{
					chessboard.put(location, new Rook("bR"));
				}
				else if (location.equals("b8") || location.equals("g8"))
				{
					chessboard.put(location, new Knight("bN"));
				}
				else if (location.equals("c8") || location.equals("f8"))
				{
					chessboard.put(location, new Bishop("bB"));
				}
				else if (location.equals("d8"))
				{
					chessboard.put(location, new Queen("bQ"));
				}
				else if (location.equals("e8"))
				{
					chessboard.put(location, new King("bK"));
				}
				else if (number == 7)
				{
					chessboard.put(location, new Pawn("bp"));
				}
			}
		}
	}
	
	/**
	 * Checks if a space on the board is black or white.
	 * @param letter inputs the column letter of the space
	 * @param number inputs the row number of the space
	 * @return true if space is black, false if it is white
	 */
	public static boolean isBlack(char letter, int number)
	{
		if ((number % 2 == 1) && (letter == 'a' || letter == 'c' || letter == 'e' || letter == 'g'))
		{
			return true;
		}
		else if ((number % 2 == 0) && (letter == 'b' || letter == 'd' || letter == 'f' || letter == 'h'))
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * Prints out the chess board.
	 */
	public static void displayBoard()
	{
		for (int number = 8; number >= 1; number--)
		{
			for (char letter = 'a'; letter <= 'h'; letter++)
			{
				String location = Character.toString(letter) + Integer.toString(number);
				String piece = chessboard.get(location).getValue();
				System.out.print(piece + " ");
			}
			System.out.println(number);
		}
		System.out.println(" a" + "  b" + "  c" + "  d" + "  e" + "  f" + "  g" + "  h");
		System.out.println();
	}
	
	/*public static boolean inputIsValid(String inputString)
	{
		if ((inputString.length() >= 7 && (inputString.charAt(2) != ' ' || inputString.charAt(5) != ' ')) || inputString.length() <= 0)
		{
			return false;
		}
		
		return true;
	}*/
	
	/*public static boolean round(int whiteblack, boolean draw)
	{
		Scanner s = new Scanner(System.in);
		
		if (whiteblack == 0)
		{
			System.out.print("White's move: ");
		}
		else
		{
			System.out.print("Black's move: ");
		}
		
		String inputString = s.nextLine();
		System.out.println();
		
		if (inputString.equalsIgnoreCase("resign"))
		{
			if (whiteblack == 0)
			{
				System.out.println("Black wins");
			}
			else if (whiteblack == 1)
			{
				System.out.println("White wins");
			}
			
			return false;
		}
		
		if (draw == true)
		{
			if (inputString.equalsIgnoreCase("draw"))
			{
				System.out.println("Draw");
				return false;
			}
			else
			{
				draw = false;
			}
		}
		
		while (!inputIsValid(inputString))
		{
			System.out.println("Illegal move, try again");
			System.out.println();
			
			if (whiteblack == 0)
			{
				System.out.print("White's move: ");
			}
			else if (whiteblack == 1)
			{
				System.out.print("Black's move: ");
			}
			
			inputString = s.next();
			System.out.println();
		}
		
		String[] input = new String[3];
		input = inputString.split(" ");
		
		displayBoard();
		
		return true;
	}*/
	
	/**
	 * Castles the King and a Rook, if applicable.
	 * @param oldPosition Inputs the previous position of the King
	 * @param newPosition Inputs the new intended position of the King
	 * @return true if the King and Rook have been castled successfully, false if not
	 */
	public static boolean castle(String oldPosition, String newPosition)
	{
		Piece king = chessboard.get(oldPosition);
		
		if (!(king instanceof King))
		{
			return false;
		}
		
		String wb = king.getValue().charAt(0) + "";
		
		Piece rook;
		String oldRookPosition = "";
		String newRookPosition = "";
		
		if ((oldPosition.equals("e1") && (newPosition.equals("g1") || newPosition.equals("c1"))) || (oldPosition.equals("e8") && (newPosition.equals("g8") || newPosition.equals("c8"))))
		{
			if (newPosition.equals("g1"))
			{
				oldRookPosition = "h1";
				newRookPosition = "f1";
				
				if (check(wb, "e1") || check(wb, "f1") || check(wb, "g1"))
				{
					return false;
				}
			}
			else if (newPosition.equals("c1"))
			{
				oldRookPosition = "a1";
				newRookPosition = "d1";
				
				if (check(wb, "e1") || check(wb, "d1") || check(wb, "c1"))
				{
					return false;
				}
			}
			else if (newPosition.equals("g8"))
			{
				oldRookPosition = "h8";
				newRookPosition = "f8";
				
				if (check(wb, "e8") || check(wb, "f8") || check(wb, "g8"))
				{
					return false;
				}
			}
			else if (newPosition.equals("c8"))
			{
				oldRookPosition = "a8";
				newRookPosition = "d8";
				
				if (check(wb, "e8") || check(wb, "d8") || check(wb, "c8"))
				{
					return false;
				}
			}
			
			rook = chessboard.get(oldRookPosition);
		}
		else
		{
			return false;
		}
		
		if (!(rook instanceof Rook))
		{
			return false;
		}
		else
		{
			if (rook.isPathClear(oldRookPosition, newRookPosition) && king.isPathClear(oldPosition, newPosition))
			{
				if (!king.getMoved() && !rook.getMoved())
				{
					king.move(oldPosition, newPosition);
					king.setMoved(true);
					
					rook.move(oldRookPosition, newRookPosition);
					rook.setMoved(true);
					
					displayBoard();
					
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Allows for an En Passant Pawn move, if applicable.
	 * Only applies if an opponent Pawn has moved two steps in its last move, and the intended 
	 * capturer Pawn is to either the immediate left or right of the opponent Pawn.
	 * @param oldPosition Inputs the previous position of the capturer Pawn
	 * @param newPosition Inputs the intended new position of the capturer Pawn
	 * @return true if the capturer Pawn is successfully able to perform an En Passant, false if not
	 */
	public static boolean enPassant(String oldPosition, String newPosition)
	{
		Piece pawn1 = chessboard.get(oldPosition);
		
		//System.out.println("1");
		
		if (!(pawn1 instanceof Pawn))
		{
			return false;
		}
		
		//System.out.println("2");
		
		String pawnPosition = null;
		
		Piece temp = null;
		Piece pawn2 = null;
		
		if (pawn1.getValue().charAt(0) == 'w')
		{
			//System.out.println("3");
			
			if ((oldPosition.charAt(1) != '5') || (newPosition.charAt(1) != '6'))
			{
				return false;
			}
			
			//System.out.println("4");
			
			if ((Math.abs(newPosition.charAt(0) - oldPosition.charAt(0)) == 1) && (newPosition.charAt(1) - oldPosition.charAt(1) == 1))
			{
				//System.out.println("5");
				
				pawnPosition = Character.toString(newPosition.charAt(0)) + oldPosition.charAt(1);
				
				//System.out.println(pawnPosition);
				
				if (!chessboard.containsKey(pawnPosition))
				{
					return false;
				}
				
				//System.out.println("6");
				
				temp = chessboard.get(pawnPosition);
				
				//System.out.println("enPassant counter: " + temp.getDoubleCounter());
				
				if (!(temp instanceof Pawn) && (temp.getValue().charAt(0) != 'b'))
				{
					return false;
				}
				
				pawn2 = (Pawn) temp;
				
				//System.out.println("7");
				
				if (!pawn2.getDoubleMove())
				{
					return false;
				}
				if (pawn2.getDoubleCounter() != gamecounter - 1)
				{
					return false;
				}
				
				chessboard.put(newPosition, pawn1);
				
				if (isBlack(oldPosition.charAt(0), oldPosition.charAt(1) - '0'))
				{
					chessboard.put(oldPosition, new EmptySpace("##"));
				}
				else
				{
					chessboard.put(oldPosition, new EmptySpace("  "));
				}
				
				if (isBlack(pawnPosition.charAt(0), pawnPosition.charAt(1) - '0'))
				{
					chessboard.put(pawnPosition, new EmptySpace("##"));
				}
				else
				{
					chessboard.put(pawnPosition, new EmptySpace("  "));
				}
				
				displayBoard();
				
				return true;
			}
			else
			{
				return false;
			}
		}
		else if (pawn1.getValue().charAt(0) == 'b')
		{
			//System.out.println("3");
			
			if ((oldPosition.charAt(1) != '4') || (newPosition.charAt(1) != '3'))
			{
				return false;
			}
			
			//System.out.println("4");
			
			if ((Math.abs(newPosition.charAt(0) - oldPosition.charAt(0)) == 1) && (oldPosition.charAt(1) - newPosition.charAt(1) == 1))
			{
				//System.out.println("5");
				
				pawnPosition = Character.toString(newPosition.charAt(0)) + oldPosition.charAt(1);
				
				//System.out.println(pawnPosition);
				
				if (!chessboard.containsKey(pawnPosition))
				{
					return false;
				}
				
				//System.out.println("6");
				
				temp = chessboard.get(pawnPosition);
				
				//System.out.println("enPassant counter: " + temp.getDoubleCounter());
				
				if (!(temp instanceof Pawn) && (temp.getValue().charAt(0) != 'w'))
				{
					return false;
				}
				
				pawn2 = (Pawn) temp;
				
				//System.out.println("7");
				
				if (!pawn2.getDoubleMove())
				{
					return false;
				}
				if (pawn2.getDoubleCounter() != gamecounter - 1)
				{
					return false;
				}
				
				//System.out.println("EnP Counter: " + pawn2.getDoubleCounter());
				
				chessboard.put(newPosition, pawn1);
				
				if (isBlack(oldPosition.charAt(0), oldPosition.charAt(1) - '0'))
				{
					chessboard.put(oldPosition, new EmptySpace("##"));
				}
				else
				{
					chessboard.put(oldPosition, new EmptySpace("  "));
				}
				
				if (isBlack(pawnPosition.charAt(0), pawnPosition.charAt(1) - '0'))
				{
					chessboard.put(pawnPosition, new EmptySpace("##"));
				}
				else
				{
					chessboard.put(pawnPosition, new EmptySpace("  "));
				}
				
				displayBoard();
				
				return true;
			}
			else
			{
				return false;
			}
		}
		
		return false;
	}
	
	/**
	 * Checks whether the King is in check or not.
	 * @param wb Inputs whether the King is white or black
	 * @return true if the King is in check, false if it is not
	 */
	public static boolean check(String wb)
	{
		String kingPosition = "";
		
		Piece king = null;
			
		for (String k : chessboard.keySet())
		{
			king = chessboard.get(k);
			
			if (king.getValue().equals(wb + "K"))
			{
				kingPosition = k;
				break;
			}
		}
		
		//System.out.println(kingPosition);
		
		for (String p : chessboard.keySet())
		{
			Piece piece = chessboard.get(p);
			
			if ((wb.equals("w") && piece.getValue().charAt(0) == 'b') || (wb.equals("b") && piece.getValue().charAt(0) == 'w'))
			{
				//System.out.println("Position: " + p + ", value: " + piece.getValue());
				
				if (piece.valid(p, kingPosition))
				{
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Overriden check method including a position on the board as a parameter.
	 * @param wb Inputs whether the King is white or black
	 * @param kingPosition Inputs a potential/current position of the King
	 * @return true if the King is in check, false if it is not
	 */
	public static boolean check(String wb, String kingPosition)
	{
		for (String p : chessboard.keySet())
		{
			Piece piece = chessboard.get(p);
			
			if ((wb.equals("w") && piece.getValue().charAt(0) == 'b') || (wb.equals("b") && piece.getValue().charAt(0) == 'w'))
			{
				if (piece.valid(p, kingPosition))
				{
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Checks whether the King is in checkmate or not.
	 * @param wb Inputs whether the King is white or black
	 * @return true if the King is in checkmate, false if it is not
	 */
	public static boolean checkMate(String wb)
	{
		if (!check(wb))
		{
			return false;
		}
		
		String kingPosition = "";
		
		Piece king = null;
			
		for (String k : chessboard.keySet())
		{
			king = chessboard.get(k);
			
			if (king.getValue().equals(wb + "K"))
			{
				kingPosition = k;
				break;
			}
		}
		
		//System.out.println(king.getValue());
		
		ArrayList<String> possibleMoves = new ArrayList<String>();
		
		char whiteorblack = 'b';
		
		if (wb.equals("w"))
		{
			whiteorblack = 'w';
		}
		else if (wb.equals("b"))
		{
			whiteorblack = 'b';
		}
		
		char opponentcolor = 'w';
		
		if (wb.equals("w"))
		{
			opponentcolor = 'b';
		}
		else if (wb.equals("b"))
		{
			opponentcolor = 'w';
		}
		
		for (int i = 0; i < 2; i++)
		{
			for (int j = 0; j < 2; j++)
			{
				String add = Character.toString((char)(kingPosition.charAt(0) + i)) + (char)(((kingPosition.charAt(1) - '0') + j) + '0');
				String sub = Character.toString((char)(kingPosition.charAt(0) - i)) + (char)(((kingPosition.charAt(1) - '0') - j) + '0');
				
				//System.out.println(add + " " + sub);
				
				if (Chess.chessboard.containsKey(add) && Chess.chessboard.get(add).getValue().charAt(0) != whiteorblack && !possibleMoves.contains(add))
				{
					possibleMoves.add(add);
				}
				
				if (Chess.chessboard.containsKey(sub) && Chess.chessboard.get(sub).getValue().charAt(0) != whiteorblack && !possibleMoves.contains(sub))
				{
					possibleMoves.add(sub);
				}
			}
		}
		
		//System.out.println(possibleMoves.toString());
		
		chessboard.put(kingPosition, new EmptySpace(wb + "K"));
		
		for (int i = 0; i < possibleMoves.size(); i++)
		{
			if (chessboard.get(possibleMoves.get(i)).getValue().charAt(0) == opponentcolor)
			{
				Piece block = chessboard.get(possibleMoves.get(i));
				chessboard.put(possibleMoves.get(i), new EmptySpace("  "));
				
				if (!check(wb, possibleMoves.get(i)))
				{
					chessboard.put(kingPosition, king);
					chessboard.put(possibleMoves.get(i), block);
					return false;
				}
				
				chessboard.put(possibleMoves.get(i), block);
			}
			
			if (!check(wb, possibleMoves.get(i)))
			{
				chessboard.put(kingPosition, king);
				return false;
			}
			
			else
			{
				continue;
			}
		}
		
		return true;
	}
	
	/**
	 * Main method of the Chess class.
	 * @param args
	 */
	@SuppressWarnings({"resource"})
	public static void main(String[] args)
	{
		createBoard();
		displayBoard();
		
		Scanner s = new Scanner(System.in);
		
		String oldPosition;
		String newPosition;
		
		String promote;
		char promotionPiece;
		
		int whiteOrBlack = 0;
		boolean draw = false;
		boolean isValid = false;
		//int game = 1;
		gamecounter = 0;
		
		while (true)
		{	
			//System.out.println("Game: " + gamecounter);
			
			if (whiteOrBlack == 0)
			{
				System.out.print("White's move: ");
			}
			else if (whiteOrBlack == 1)
			{
				System.out.print("Black's move: ");
			}
			
			String[] input = new String[3];
			
			String inputString = s.nextLine();
			
			while ((inputString.length() >= 7 && (inputString.charAt(2) != ' ' || inputString.charAt(5) != ' ')) || inputString.length() <= 0)
			{
				System.out.println("Illegal move, try again");
				System.out.println();
				
				if (whiteOrBlack == 0)
				{
					System.out.print("White's move: ");
				}
				else if (whiteOrBlack == 1)
				{
					System.out.print("Black's move: ");
				}
				
				inputString = s.nextLine();
			}
			
			System.out.println();
			
			if (inputString.equalsIgnoreCase("resign"))
			{
				if (whiteOrBlack == 0)
				{
					System.out.println("Black wins");
				}
				else if (whiteOrBlack == 1)
				{
					System.out.println("White wins");
				}
				
				//game = 0;
				return;
			}
			
			if (draw == true)
			{
				if (inputString.equals("draw"))
				{
					System.out.println("draw");
					
					//game = 0;
					return;
				}
				else
				{
					draw = false;
				}
			}
			
			input = inputString.split(" ");
			
			promote = null;
			promotionPiece = '0';
			
			if (input.length > 2)
			{
				promote = input[2];
				promotionPiece = promote.charAt(0);
				
				if (input[2].equalsIgnoreCase("draw?"))
				{
					draw = true;
				}
			}
			
			if (input.length < 2)
			{
				System.out.println("Illegal move, try again");
				System.out.println();
				continue;
			}
			
			oldPosition = input[0];
			newPosition = input[1];
			
			if (!chessboard.containsKey(oldPosition) || !chessboard.containsKey(newPosition))
			{
				System.out.println("Illegal move, try again");
				System.out.println();
				continue;
			}
			
			Piece oldLocation = chessboard.get(oldPosition);
			Piece newLocation = chessboard.get(newPosition);
			
			//System.out.println(oldLocation.getValue());
			
			if ((whiteOrBlack == 0 && oldLocation.getValue().charAt(0) == 'w') || (whiteOrBlack == 1 && oldLocation.getValue().charAt(0) == 'b'))
			{
				isValid = oldLocation.valid(oldPosition, newPosition);
				
				if (isValid)
				{
					String wb;
					
					if (whiteOrBlack == 0)
					{
						wb = "w";
					}
					else
					{
						wb = "b";
					}
					
						
					oldLocation.move(oldPosition, newPosition, promotionPiece);
						
					//System.out.println(check(wb));
					
					//System.out.println(kingPosition);
					
					if (check(wb))
					{
						isValid = false;
					}
					else
					{
						isValid = true;
					}
				
					chessboard.put(oldPosition, oldLocation);
					chessboard.put(newPosition, newLocation);
				}
				
				if(castle(oldPosition, newPosition))
				{
					if (whiteOrBlack == 0)
					{
						whiteOrBlack = 1;
					}
					else
					{
						whiteOrBlack = 0;
					}
					
					continue;
				}
				
				if(enPassant(oldPosition, newPosition))
				{
					if (whiteOrBlack == 0)
					{
						whiteOrBlack = 1;
					}
					else
					{
						whiteOrBlack = 0;
					}
					
					continue;
				}
			}
			else
			{
				System.out.println("Illegal move, try again");
				System.out.println();
				continue;
			}
			
			if (isValid)
			{
				oldLocation.move(oldPosition, newPosition, promotionPiece);
				oldLocation.setMoved(true);
				
				String wb;
				
				if (whiteOrBlack == 0)
				{
					wb = "b";
				}
				else
				{
					wb = "w";
				}
				
				if (check(wb))
				{
					if (checkMate(wb))
					{
						displayBoard();
						
						System.out.println("Checkmate");
						
						if (wb.equals("w"))
						{
							System.out.println("Black wins");
						}
						else
						{
							System.out.println("White wins");
						}
						
						return;
					}
					
					System.out.println("Check");
					System.out.println();
				}
				
				gamecounter++;
				
				displayBoard();
			}
			else
			{
				System.out.println("Illegal move, try again");
				System.out.println();
				continue;
			}
			
			if (whiteOrBlack == 0)
			{
				whiteOrBlack = 1;
			}
			else
			{
				whiteOrBlack = 0;
			}
		}
		
		/*Piece p1 = chessboard.get("a2");
		p1.move("a2", "h6");
		
		Piece p2 = chessboard.get("b2");
		p2.move("b2", "c6");
		
		Piece p3 = chessboard.get("c2");
		p2.move("c2", "e6");
		
		Piece n = chessboard.get("b1");
		if (n.valid("b1", "a3"))
			n.move("b1", "a3");
		
		if (n.valid("a3", "c4"))
			n.move("a3", "c4");
		
		if (n.valid("c4", "c2"))
			n.move("c4", "c2");
		
		if (n.valid("c4", "b2"))
			n.move("c4", "b2");
		
		if (n.valid("b2", "x3"))
			n.move("b2", "x3");
		
		displayBoard();*/
	}
}
