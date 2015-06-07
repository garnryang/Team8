package PuzzleGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import CoreDataStructures.CellGrid;

public class DLX {
	protected Header[] columns;
	protected NodePointer[] rows;
	protected Stack<Node> sol;
	protected Header h;
	protected int totalSolution;
	public int[][] solution;
	public boolean getSolution;
	protected List<String> problem = new ArrayList<String>(); 
	protected CellGrid problemGrid;

	
	
	public DLX(CellGrid problemGrid){
		this.h = new Header(0);
		this.sol = new Stack<Node>();
		this.totalSolution = 0;
		this.problemGrid = problemGrid;
		
		for(int i = 0; i<9; i++ ){
			String rowTemp ="";
			for(int j = 0; j<9; j++ ){
				rowTemp += Integer.toString(problemGrid.getCell(i, j).getNumber());
			}
			problem.add(rowTemp);
		
		}
		buildSudokuMatrix();
		
	}
	
	public void buildSudokuMatrix(){
		buildSkeleton(729,324);
		
		int b=0;
		for (int r=0; r<9; r++) {
			for (int c=0; c<9; c++) {
				// set the block
				b=(r/81)*81+(c/81);
				for (int d=0; d<9; d++) {
					appendRow(new int[] {r*9+c, 9*(9+r)+d, 9*(c+2*9)+d, 9*(b+3*9)+d }, r*9*9+c*9+d);
				}
			}
		}
		int test = 0;
	}
	
	public void buildSkeleton(int rowCount, int colCount){
		columns = new Header[colCount];
		rows = new NodePointer[rowCount];
		
		for(int i = 0; i< colCount;i++){
			columns[i] = new Header(i);
			columns[i].setLeft(h.getLeft());
			columns[i].setRight(h);
			h.getLeft().setRight(columns[i]);
			h.setLeft(columns[i]);
		}
		
		for(int i = 0; i< rowCount; i++){
			rows[i]=new NodePointer();
		}
		int test =0;
	}
	
	// r = string representation of row
	// row = the number of the rows
	public void appendRow(String r, int row) throws IndexOutOfBoundsException{
		if(r.length() > columns.length || row>=rows.length){
			throw new IndexOutOfBoundsException();
		}
		
		Node first =null;
		Node  t = null;
		
		for(int i = 0; i<r.length(); i++){
			if(r.substring(i, i+1) == "1"){
				if(first != null){
					t = new Node(row);
					t.setLeft(first.getLeft());
					t.setRight(first);
					first.getLeft().setRight(t);
					first.setLeft(t);
					t.setDown(columns[i]);
					t.setUp(columns[i].getUp());
					columns[i].getUp().setDown(t);
					columns[i].setUp(t);
					
					t.setHeader(columns[i]);
					t.getHeader().setSize(t.getHeader().getSize() + 1);
				}else{
					first = new Node(row);
					first.setHeader(columns[i]);
					first.getHeader().setSize(first.getHeader().getSize() + 1);
					first.setDown(columns[i]);
					first.setUp(columns[i].getUp());
					columns[i].getUp().setDown(first);
					columns[i].setUp(first);
					
					rows[row].setNode(first);
				}
			}
		}
	}

	public void appendRow(int[] pos, int row) throws IndexOutOfBoundsException{
		if(pos[pos.length -1]>=columns.length || row>= rows.length){
			throw new IndexOutOfBoundsException();
		}
		
		Node first = new Node(row);
		first.setHeader(columns[pos[0]]);
		first.getHeader().setSize(first.getHeader().getSize()+1);
		first.setDown(columns[pos[0]]);
		first.setUp(columns[pos[0]].getUp());
		columns[pos[0]].getUp().setDown(first);
		columns[pos[0]].setUp(first);
		rows[row].setNode(first);
		
		for(int i = 0; i<pos.length;i++){
			Node t = new Node(row);
			t.setLeft(first.getLeft());
			t.setRight(first);
			first.getLeft().setRight(t);
			first.setLeft(t);
			
			t.setDown(columns[pos[i]]);
			t.setUp(columns[pos[i]].getUp());
			columns[pos[i]].getUp().setDown(t);
			columns[pos[i]].setUp(t);
			
			t.setHeader(columns[pos[i]]);
			t.getHeader().setSize(t.getHeader().getSize() + 1);
		}
	}
	
	protected Header findMin(){
		int min = 2147483000;
		Header ret  = null;
		Header j = (Header)h.getRight();
		while(j!=h){
			if(j.getSize() < min){
				min = j.getSize();
				ret = j;
			}
			
			j =(Header)j.getRight();
		}
		
		return ret;
	}
	
	protected void coverColumn(int index){
		coverColumn(columns[index].getName());
	}
	
	protected void uncoverColumn(int index){
		uncoverColumn(columns[index].getName());
	}
	
	public void selectRow(int index){
		Node n = rows[index].getNode();
		sol.push(n);
		do{
			coverColumn(n.getHeader().getName());
			n = n.getRight();
		}while(n!= rows[index].getNode());
	}
	
	public void unselectRow(){
		if(sol.empty()) return;
		Node n  = ((Node)sol.pop()).getLeft();
		do{
			uncoverColumn(n.getHeader().getName());
			n = n.getLeft();
		}while(n!= rows[n.getRow()].getNode());
		uncoverColumn(n.getHeader().getName());					
	}
	
	public void unselectAllRows(){
		while(sol.size()>0){
			unselectRow();
		}
	}
	
	public int Solve(){
		sol.clear();
		totalSolution = 0;
		solveRecourse();
		unselectAllRows();
		
		return totalSolution;
	}
	
	protected void solveRecourse(){
		if(h.getRight().getHeader().getName() == h.getName()){
			totalSolution ++;
			return;
		}
		
		Header c = findMin();
		
		if(c.getSize() == 0) return; //bad solution;
		
		coverColumn(c.getName());
		
		Node r = c.getDown();
		Node j = null;
		while(r != c){
			sol.push(r);
			j = r.getRight();
			while(j != r ){
				coverColumn(j.getHeader().getName());
				j = j.getRight();
			}
			
			solveRecourse();
			r = (Node)sol.pop();
			c = r.getHeader();
			j = r.getLeft();
			while(j != r){
				uncoverColumn(j.getHeader().getName());
				j=j.getLeft();
			}
			r=r.getDown();			
		}
		uncoverColumn(c.getName());
	}
	
	
}
