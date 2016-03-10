package fr.unice.polytech.ogl.islba.model;

/** Represents differents Biome of a map**/
public enum Biome {
    OCEAN("OCEAN",new String[]{"FISH"}),
    LAKE("LAKE",new String[]{"FISH"}),
    SHRUBLAND("SHRUBLAND",new String[]{"FUR"}),
    TROPICAL_SEASONAL_FOREST("TROPICAL_SEASONAL_FOREST",new String[]{"WOOD","SUGAR_CANE","FRUITS"}),
    TROPICAL_RAIN_FOREST("TROPICAL_RAIN_FOREST",new String[]{"WOOD","SUGAR_CANE","FRUITS"}),
    BEACH("BEACH",new String[]{"QUARTZ"}),
    SUB_TROPICAL_DESERT("SUB_TROPICAL_DESERT",new String[]{"QUARTZ","ORE"}),
    TEMPERATE_DESERT("TEMPERATE_DESERT", new String[]{"QUARTZ","ORE"}),
    ALPINE("ALPINE",new String[]{"ORE","FLOWER"}),
    TAIGA("TAIGA",new String[]{"WOOD"}),
    TEMPERATE_DECIDUOUS_FOREST("TEMPERATE_DECIDUOUS_FOREST",new String[]{"WOOD"}),
    TEMPERATE_RAIN_FOREST("TEMPERATE_RAIN_FOREST", new String[]{"WOOD","FUR"}),
    MANGROVE("MANGROVE",new String[]{"WOOD","FLOWER"}),
    SNOW("SNOW",new String[]{}),
    GRASSLAND("GRASSLAND",new String[]{"FUR"}),
    TUNDRA("TUNDRA",new String[]{"FUR"}),
    GLACIER("GLACIER",new String[]{"FLOWER"});
    //each biome has a name
    private String biome;
    //each biome has some resources
    private String[] resources;

   /**
    * A biome has a name and resources
    * @param biome
    *           the name of the biome
    * @param res
    *           the table with all the resources the biome can contains
    */
   private Biome(String biome, String[]res){
	   this.biome = biome;
	   this.resources = res;
   }
    
   /**
    * return the name of the biome
    * @return the name of the biome
    */
   @Override
   public String toString(){
	   return this.biome;
   }
   
   /**
    * Return the resources that the biome can contains
    * @return resources, the table of resource the biome can contain
    */
   public String[] getResources(){
       return this.resources;
   }
   
   /**
    * Check if the Biome has a specific resource in it
    * @param res
    *           the name of the resource we want to verify if it is in this biome
    * @return true if the Biome contains this resource, false otherwise
    */
   public boolean hasResource(String res){
       for(String resource : this.resources)
           if(resource.equals(res)) return true;
       
       return false;
   }
   
}
