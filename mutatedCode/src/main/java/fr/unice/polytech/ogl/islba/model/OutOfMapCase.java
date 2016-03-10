package fr.unice.polytech.ogl.islba.model;

/**
 * This class represent a case which is out of the map
 * @author Cédric Lallemand
 *
 */
public class OutOfMapCase extends Case {

	public OutOfMapCase(){
	    super();
	}
	
	
	/**
	 * return true if the case in on the map, it this class the case is never on the map
	 */
	@Override
	public boolean isOnMap(){
		return false;
	}
}
