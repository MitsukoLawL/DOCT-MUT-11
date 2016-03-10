package fr.unice.polytech.ogl.islba.model.resource;

import java.util.HashMap;

/**
 * This class permits to find the correct resource (between Primary and Secondary type)
 * from a String
 */

public class ResourcesType{
    // map a String to a PrimaryResource
    private HashMap<String, PrimaryResource> primaryResources;
    // map a String to a SecondaryResource
    private HashMap<String, SecondaryResource> secondaryResources;

    /**
     * initialize the maps.
     */
    public ResourcesType(){
        primaryResources = new HashMap<String, PrimaryResource>();
        secondaryResources = new HashMap<String, SecondaryResource>();
        
        for(PrimaryResource res : PrimaryResource.values()) {
            primaryResources.put(res.toString(), res);
        }
        
        for(SecondaryResource res : SecondaryResource.values()) {
            secondaryResources.put(res.toString(), res);
        }
    }

    /**
     * Find the PrimaryResource which matches with the String resource
     * @param resource
     *          The word to look up.
     * @return The PrimaryResource corresponding, null if it doesn't exist
     */
    public PrimaryResource getPrimaryResource(String resource){
        PrimaryResource res = primaryResources.get(resource.toUpperCase());
        return res;
    }
    
    /**
     * Find the SecondaryResource which matches with the String resource
     * @param resource
     *          The word to look up.
     * @return The SecondaryResource corresponding, null if it doesn't exist
     */
    public SecondaryResource getSecondaryResource(String resource){
        SecondaryResource res = secondaryResources.get(resource.toUpperCase());
        return res;
    }
    
    /**
     * Check if the String matches a PrimaryResource 
     * @param aString
     *              a String which can correspond to a PrimaryResource
     * @return true if it matches, false otherwise.
     */
    public boolean isPrimaryResource(String aString){
        return primaryResources.containsKey(aString.toUpperCase());
    }
    
    /**
     * Check if the String matches a SecondaryResource 
     * @param aString
     *              a String which can correspond to a SecondaryResource
     * @return true if it matches, false otherwise.
     */
    public boolean isSecondaryResource(String aString){
        return secondaryResources.containsKey(aString.toUpperCase());
    }
}