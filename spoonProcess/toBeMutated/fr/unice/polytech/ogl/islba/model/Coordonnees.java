package fr.unice.polytech.ogl.islba.model;

import fr.unice.polytech.ogl.islba.model.Coordonnees;

/**
 * Representation of (x,y) coordinates.
 *
 */
public class Coordonnees {
    //the x in (x,y)
    private int x;
    //the y in (x,y)
    private int y;
    
    /**
     * Constructor of a Coordonnees (x,y)
     * @param x
     * @param y
     */
    public Coordonnees(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Get the y in (x,y)
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * get the x in (x,y)
     * @return the x
     */
    public int getX() {
        return x;
    }
    
    /**
     * Multiply this by mult.
     * @param mult
     *          the number of times we want to multiply this
     * @return the new Coordonnees we obtain after multiply it
     */
    public static Coordonnees multiplyBy(Coordonnees c, int mult){
        int newX = c.getX() * mult;
        int newY = c.getY() * mult;
        return new Coordonnees(newX,newY);
    }
    
    /**
     * Do the addition between coo1 and coo2
     * @param coo2
     * @param coo1
     *      the Coordonnees we want to do the addition with coo2
     * @return coo1+coo2 as a new Coordonnees
     */
    public static Coordonnees add(Coordonnees coo1, Coordonnees coo2){
        int newX = coo1.getX() + coo2.getX();
        int newY = coo1.getY() + coo2.getY();
        return new Coordonnees(newX, newY);
    }

    /**
     * Equals method of Coordonnees.
     * @return true if it's the same object, false otherwise
     */
    @Override
    public boolean equals(Object obj){
        if(this==obj){
            return true;
        }else if(obj instanceof Coordonnees){
            Coordonnees coo2=(Coordonnees)obj;
            if(coo2.getX()==this.getX() && coo2.getY()==this.getY()){
                return true;
            }
        }
        return false;
    }

    /**
     * The hashcode of Coordonnees
     * @return the hashcode
     */
    @Override
    public int hashCode(){
        int cle = 17;
        int code = 769;
        code = code * cle + x;
        code = code * cle + y;
        return code;
    }
}
