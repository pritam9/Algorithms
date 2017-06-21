package nQueensRandomRestart;
public class Queen {
	private int row;
	private int column;
	private int nValue;
	
	public Queen(int r, int c,int nValue){
		row = r;
		column  = c;
		this.nValue=nValue;
	}
	
	public boolean canAttack(Queen q){
		boolean canAttack=false;
		
		//test rows and columns
		if(row==q.getRow() || column==q.getColumn())
			canAttack=true;
		//test diagonal
		else if(Math.abs(column-q.getColumn()) == Math.abs(row-q.getRow()))
			canAttack=true;
			
		return canAttack;
	}
	
	public void moveDown(int spaces){
		row+=spaces;
		int size = nValue-1;
		//bound check/reset
		if(row>size && row%size!=0){
			row=(row%size)-1;
		}
		else if(row>size && row%size==0){
			row=size;
		}
	}
	
	public void setRow(int r){
		row = r;
	}
	
	public int getRow(){
		return row;
	}
	public void setColumn(int c){
		column = c;
	}
	
	public int getColumn(){
		return column;
	}
	
	public String toString(){
		return "("+row+", "+column+")";
	}
}
