package fr.unice.polytech.ogl.islba.model.resource;

/**
 * Define a ressource by is name, and store amount and difficulty to exploit
 * @author Cédric Lallemand
 *
 */
public class Resource {
	private String name;
	private String amount;
	private String difficulty;
	
	/**
	 * Constructor of the class, create a resource with the name pass in parameter
	 * and put the amount and the difficulty to exploit at null.
	 * @param name
	 */
	public Resource(String name){
	    this(name, null, null);
	}
	
	/**
	 * Contructor of the class, create a resource with an amount and a difficulty to exploit
	 * @param name - name of the resource
	 * @param amount - amount of the resource
	 * @param difficulty - difficulty to exploit the resource
	 */
	public Resource(String name, String amount, String difficulty){
		this.name = name;
		this.amount= amount;
		this.difficulty = difficulty;
	}

	/**
	 * Return the name of the resource
	 * @return
	 */
    public String getName() {
        return name;
    }
    
    /**
     * Return the amount of the resource : low, medium or high
     * @return
     */
    public String getAmount(){
    	return this.amount;
    }
    
    /**
     * Return the difficulty to exploit the resource low, medium or high
     * @return
     */
    public String getDifficulty(){
    	return this.difficulty;
    }

    /**
     * Set a new amount to the resource
     * @param amount
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * Set a new difficulty to the resource
     * @param difficulty
     */
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    
    /**
     * Method equals of the class Resource
     */
    public boolean equals(Object obj){
    	if(obj instanceof Resource){
            Resource res2=(Resource) obj;
            return this.name.equals(res2.getName());
        }
        return false;
    }

    /**
     * The hashcode of Resource
     * @return the hashcode
     */
    @Override
    public int hashCode(){
        int cle = 11;
        int code = 337;
        code = code * cle + name.hashCode();
        return code;
    }
    
}
