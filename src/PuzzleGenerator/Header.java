package CoreDataStructures;


public class Header extends Node{
	private int size;
	private int name;
	
	public void setSize(int size){
		this.size = size;
	}
	
	public int getSize(){
		return this.size;
	}
	
	public void setName(int name){
		this.name = name;
	}
	
	public int getName(){
		return this.name;
	}
	
	public Header(int name){
		this.name = name;
		this.size = 0;
		this.head = this;
	}
	
	public Header(){
		this(0);
	}
}
