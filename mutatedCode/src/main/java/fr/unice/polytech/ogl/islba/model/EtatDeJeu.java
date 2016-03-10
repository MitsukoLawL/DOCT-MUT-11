package fr.unice.polytech.ogl.islba.model;


import java.util.Map;

/**
 * Classe gardant un historique des cases déjà visités avec
 * les informations connues sur ces cases
 */
public class EtatDeJeu {

    //la map, on accède à chaque case via ses coordonnées x y
	private MapMonde map;
	//le nombre de paMax
	private Integer paMax;
	//le nombre de pa actuels
	private Integer pa;
	//l'id de la crique sur laquelle on veut débarquer
	private String creekID;
	//l'équipe présente sur l'île
	private Equipe team;

	/**
	 * Contructeur de la classe, crée une carte vide (aucun case n'as encore été visité)
	 */
	public EtatDeJeu(){
        map = new MapMonde();
		pa = null;
	}
	
	/**
	 * Contructor to create a new EtatDeJeu object with
	 * a certain amount of action point, a starting creek,
	 * an amount of men and the objectives
	 */
	public EtatDeJeu(int pa, String creekID, int men, Map<String, Integer> objective){
		map = new MapMonde();
		this.pa = pa;
		this.paMax=pa;
		this.creekID = creekID;
		team=new Equipe(men, objective);
	}

    public MapMonde getMapMonde() {
        return map;
    }

    /**
     * Enlève au nombre de PA actuels un certains nombre de PA mis en param
     * @param pa
     *             la nombre de PA qu'on souhaite enlevé
     */
    public void reducePA(int pa){
        this.pa-=pa;
    }

	/**
	 * 
	 * @return le nombre de PA restant
	 */
	public int getPA(){ 
	    return pa;
	}
	
	/**
     * 
     * @return le nombre de PA max
     */
    public int getPAMax(){ 
        return paMax;
    }
	
	/**
	 * Get the creek we want to land
	 * @return creekID
	 */
	public String getCreekID() {
		return creekID;
	}


	
	/**
	 * Get the Equipe team
	 * @return team
	 */
	public Equipe getTeam(){
	    return team;
	}
	
	/**
	 * Set paMax to a new value
	 * @param paMax
	 *         the new paMax
	 */
	public void setPAMax(int paMax){
	    this.paMax  = paMax;
	}
	
	/**
	 * Set the PA to a new value
	 * @param pa
	 */
	public void setPA(int pa){
		this.pa  = pa;
	}
	


}
