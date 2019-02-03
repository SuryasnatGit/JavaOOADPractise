package com.chess;

public class Piece {

	private int x;
	private int y;

	private boolean available; // mark the live or dead
	private int color; // mark the owner

	public Piece(boolean available, int x, int y, int color) {
		super();
		this.available = available;
		this.x = x;
		this.y = y;
		this.color = color;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isValid(Board board, int fromX, int fromY, int toX, int toY) {
		return available;
		// different by character of piece
	}

	public class King extends Piece {
		public King(boolean available, int x, int y, int color) {
			super(available, x, y, color);
			// TODO Auto-generated constructor stub
		}

		@Override
		public boolean isValid(Board board, int fromX, int fromY, int toX, int toY) {
			return available;
		}
	}

	public class Rook extends Piece {
		public Rook(boolean available, int x, int y, int color) {
			super(available, x, y, color);
			// TODO Auto-generated constructor stub
		}

		@Override
		public boolean isValid(Board board, int fromX, int fromY, int toX, int toY) {
			return available;
		}
	}

	public class Queen extends Piece {
		public Queen(boolean available, int x, int y, int color) {
			super(available, x, y, color);
			// TODO Auto-generated constructor stub
		}

		@Override
		public boolean isValid(Board board, int fromX, int fromY, int toX, int toY) {
			return available;
		}
	}

	public class Knight extends Piece {
		public Knight(boolean available, int x, int y, int color) {
			super(available, x, y, color);
			// TODO Auto-generated constructor stub
		}

		@Override
		public boolean isValid(Board board, int fromX, int fromY, int toX, int toY) {
			return available;
		}
	}

	public class Bishop extends Piece {
		public Bishop(boolean available, int x, int y, int color) {
			super(available, x, y, color);
			// TODO Auto-generated constructor stub
		}

		@Override
		public boolean isValid(Board board, int fromX, int fromY, int toX, int toY) {
			return available;
		}
	}

	public class Pawn extends Piece {
		public Pawn(boolean available, int x, int y, int color) {
			super(available, x, y, color);
			// TODO Auto-generated constructor stub
		}

		@Override
		public boolean isValid(Board board, int fromX, int fromY, int toX, int toY) {
			return available;
		}
	}

	// ..... for Queen, Rook, Bishop, Pawn
}
