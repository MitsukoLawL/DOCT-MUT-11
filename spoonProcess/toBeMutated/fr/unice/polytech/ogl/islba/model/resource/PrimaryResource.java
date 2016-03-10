package fr.unice.polytech.ogl.islba.model.resource;

/**
 * Store all the Primary Resources of the Island
 *
 */
public enum PrimaryResource {
    FISH(new Resource("FISH")), QUARTZ(new Resource("QUARTZ")),
    ORE(new Resource("ORE")), WOOD(new Resource("WOOD")),
    FRUITS(new Resource("FRUITS")), SUGAR_CANE(new Resource("SUGAR_CANE")),
    FLOWER(new Resource("FLOWER")), FUR(new Resource("FUR"));
    
    private Resource res;
    
    /**
     * A primary resource only has a name
     * @param name
     *      the name of the Primary Resource
     */
    private PrimaryResource(Resource res){
        this.res = res;
    }
    
    /**
     * The toString is just the name of the Primary Resource
     * @return the name of the Primary Resource
     */
    @Override
    public String toString(){
        return this.res.getName();
    }
}
