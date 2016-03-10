package fr.unice.polytech.ogl.islba.model;

import fr.unice.polytech.ogl.islba.model.Coordonnees;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents 4 towards cardinal
 */
public enum Direction {
    
	NORTH("N", new Coordonnees(0,1)),
	SOUTH("S", new Coordonnees(0,-1)),
	EAST("E", new Coordonnees(1,0)),
	WEST("W", new Coordonnees(-1,0));

	public static ArrayList<Direction> DIRECTIONS = new ArrayList<Direction>(Arrays.asList(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST));

	/**
	 * Code to use direction on the island
	 */
	private String direction;
	private Coordonnees coo;
	
	private Direction(String dir, Coordonnees coo){
		this.direction = dir;
		this.coo=coo;
	}
	
	/**
	 * Get the Direction as a String
	 * @return the direction as a string
	 */
	public String getDirection(){
		return direction;
	}
	
	/**
	 * Get the Coordonnees of the Direction we choose
	 * @return the coordonnees of the Direction
	 */
	public Coordonnees getCoo(){
	    return coo;
	}
	
	/**
	 * Sometimes the Direction where we want to do an action
	 * allows to access at a Case that is not in the map.
	 * Because of that, we do a 90° rotation.
	 * In the ArrayList DIRECTIONS, it's as if we take the next Direction
	 * in it
	 */
	public Direction getNewDirection(){
	    int indice = Direction.DIRECTIONS.indexOf(this);
	    Direction dir = this;
	    //we take the next indice to have the next Direction
	    indice++;
	    //if indice is not a correct indice (out of bounds), we say it's 0 now.
	    if(indice>=Direction.DIRECTIONS.size()) indice=0;
	    //if d is in the array, we take the next Direction
	    if(indice>-1){
	        dir = Direction.DIRECTIONS.get(indice);
	    }
	    return dir;
	}
	
	@Override
	public String toString(){
		return direction;
	}
}
