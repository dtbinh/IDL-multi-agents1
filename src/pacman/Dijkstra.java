package pacman;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import core.Agent;
import core.Direction;
import core.Environement;
import util.Data;

public class Dijkstra {

	private List<String> visited;
	private Environement env;
	private int i=0;
	
	public Environement calculateDistances(Environement env, int x, int y){
		this.env = env;
		this.visited = new ArrayList<String>();
		//this.env.setDistance(0, x, y);
		//setDistance(x,y,0);
		calculateDistance(x,y);
		return this.env;
	}
	
	private void setDistance(int x, int y, int distance){
		Direction[] directions = Direction.values();
		visited.add(Integer.toString(x)+","+Integer.toString(y));
		System.out.println(i++);
		//System.out.println("["+x+","+y+"] "+distance);
		int dist = distance +1;
		if(distance ==0){			
			this.env.setDistance(distance, x, y);
		}				
		
		for (int index = 0; index < directions.length; index++) {
			Direction d = directions[index];
			int nextX = x + d.getDeltaX();
			int nextY = y + d.getDeltaY();	
			if(((nextX < Data.size && nextX >= 0) && (nextY < Data.size && nextY >= 0)) 					
					&& (this.env.getDistance(nextX, nextY)== -1 || this.env.getDistance(nextX, nextY)>dist)){
				this.env.setDistance(dist, nextX, nextY);
				if(visited.contains(nextX+","+ nextY));
					visited.remove(nextX+","+ nextY);
				//System.out.println("["+nextX+","+nextY+"] : "+dist);
			}						
		}
		
		for (int index = 0; index < directions.length; index++) {
			Direction d = directions[index];
			int nextX = x + d.getDeltaX();
			int nextY = y + d.getDeltaY();
			//verifier que la nouvelle position existe, que n'etait pas visité
			if(((nextX < Data.size && nextX >= 0) && (nextY < Data.size && nextY >= 0)) 
					&& !visited.contains(nextX+","+nextY)){ 
				setDistance(nextX,nextY,this.env.getDistance(nextX, nextY));
			}
		}
		
	}
	
	private void calculateDistance(int targetX,int targetY){
		for(int x=0;x<Data.size; x++){
			for(int y=0;y<Data.size;y++){
				this.env.setDistance(distance(targetX,targetY,x,y), x, y);
			}
		}
	}
	
	private int distance(int x1, int y1, int x2, int y2) {
	    int dx = Math.abs(x2 - x1);
	    int dy = Math.abs(y2 - y1);

	    int min = Math.min(dx, dy);
	    int max = Math.max(dx, dy);

	    int diagonalSteps = min;
	    int straightSteps = max - min;

	    return (int)Math.sqrt(2) * diagonalSteps + straightSteps;
	}
}
