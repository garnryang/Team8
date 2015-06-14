package edu.psu.sweng500.team8.puzzleGenerator;


public class Node {
	private Node left;
	private Node right;
	private Node up;
	private Node down;
	
	public Header head;
	
	private int row;
	
	public void setRow(int row){
		this.row = row;
	}
	
	public int getRow(){
		return this.row;
	}
	
	public void setHeader(Header header){
		this.head = header;
	}
	
	public Header getHeader(){
		return this.head;
	}
	
	public void setDown(Node down){
		this.down = down;
	}
	
	public Node getDown(){
		return this.down;
	}
	
	public void setUp(Node up){
		this.up = up;
	}
	
	public Node getUp(){
		return this.up;
	}
	
	public void setRight(Node right){
		this.right = right;
	}
	
	public Node getRight(){
		return this.right;
	}
		
	public void setLeft(Node left){
		this.left = left;
	}
	
	public Node getLeft(){
		return this.left;
	}
	
	public Node(int row, Header header){
		this.left = this;
		this.right = this;
		this.up = this;
		this.down = this;
		this.row = row;
		this.head = header;
	}
	
	public Node(int row) {
		this(row, null);
	}
	
	public Node(){
		this(-1, null);
	}
}
