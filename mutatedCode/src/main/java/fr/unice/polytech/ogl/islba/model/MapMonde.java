package fr.unice.polytech.ogl.islba.model;


import java.util.HashMap;
import java.util.Map;

/**
 * Pour améliorer notre modèle, il faudrait faire
 * une classe mappemonde contenant le hashmap de la carte
 * et toutes les méthodes qui y sont associées.
 */
public class MapMonde {
    private Map<Coordonnees, Case> map;
    //les coordonnées de l'équipe courante sur la map
    private Coordonnees currentCoo;

    public MapMonde() {
        this.map = new HashMap<Coordonnees,Case>();
    }

    /**
     * Add a Case in the map
     * @param x
     *         the x coordinate of the new Case
     * @param y
     *         the y coordinate of the new Case
     * @param c
     *         the new Case
     */
    public void addCaseMap(int x, int y,Case c){
        map.put(new Coordonnees(x,y),c);
    }

    /**
     * Add a Case in the map
     * @param coo
     *             the coordinates of the new Case
     * @param c
     *             the new Case
     */
    public void addCaseMap(Coordonnees coo,Case c){
        map.put(coo,c);
    }


    /**
     * Return a Case of the map
     * in function of the x and y param
     * @param x
     *          an int which is the x coordinate
     * @param y
     *          an int which is the y coordinate
     * @return the Case in (X,Y)
     */
    public Case getCase(int x, int y){
        return map.get(new Coordonnees(x,y));
    }

    /**
     * Return a Case of the map
     * in function of a coordinate
     * @param coord
     *          the coordinate where the Case that we want is
     * @return the wanted Case
     */
    public Case getCase(Coordonnees coord){
        return map.get(coord);

    }

    /**
     * Get the current coordinates of the team
     * @return currentCoo
     */
    public Coordonnees getCurrentCoo(){
        return currentCoo;
    }

    /**
     * Retourne la map
     * @return map
     */
    public Map<Coordonnees, Case> getMap() {
        return map;
    }

    /**
     * Get the current Case of the team
     * @return the Case where the team is
     */
    public Case getCurrentCase(){
        return map.get(currentCoo);
    }

    /**
     * Set the current coordinates to a new value
     * @param coo
     */
    public void setCurrentCoo(Coordonnees coo){
        this.currentCoo=coo;
    }

    /**
     * Set the current coordinates to a new value
     * @param x
     * @param y
     */
    public void setCurrentCoo(int x, int y) {
        this.currentCoo=new Coordonnees(x,y);

    }
}
