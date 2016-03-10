package fr.unice.polytech.ogl.islba.model.resource;

import java.util.HashMap;
import java.util.Map;

/**
 * Store all the secondary resources that we know,
 * with what we need to create them
 *
 */
public enum SecondaryResource {
    GLASS("GLASS",
            new PrimaryResource[]{PrimaryResource.QUARTZ, PrimaryResource.WOOD},
            new double[]{10,5}),
    INGOT("INGOT",
            new PrimaryResource[]{PrimaryResource.ORE, PrimaryResource.WOOD},
            new double[]{5,5}),
    PLANK("PLANK",
            new PrimaryResource[]{PrimaryResource.WOOD},
            new double[]{0.25}),
    LEATHER("LEATHER",
            new PrimaryResource[]{PrimaryResource.FUR},
            new double[]{3}),
    RUM("RUM",
            new PrimaryResource[]{PrimaryResource.SUGAR_CANE, PrimaryResource.FRUITS},
            new double[]{10,1});
    
    private String name;
    private PrimaryResource[] component;
    private double[] amount;
    
    /**
     * A secondary resource is characterized by a
     * name, some components, and an average number in order
     * to produce one unity of it
     * @param name
     *          the name of the resource
     * @param component
     *          the table of all resources we need to create the resource
     * @param amount
     *          the amount of each resource we need to create one unity of it
     */
    private SecondaryResource(String name, PrimaryResource[] component, double[] amount){
        this.name=name;
        this.component=component;
        this.amount=amount;
    }
    
    /**
     * Return a Map which have as key
     * the PrimaryResources needed and as value
     * the amount
     * @return a Map containing the resources we need and in which amount
     */
    public Map<PrimaryResource,Double> getComponentsNeeded(){
        Map<PrimaryResource, Double> components = new HashMap<PrimaryResource, Double>(); 
        for(int i=0; i<this.component.length;i++){
            components.put(component[i], amount[i]);
        }
        return components;
    }
    
    
    /**
     * Return the component table which contains
     * all the Primary Resources we need to create the
     * resource
     * @return component, the PrimaryResource table
     */
    public PrimaryResource[] getComponent(){
        return this.component;
    }
    
    /**
     * Return the amount table which contains
     * the quantity in which we need the primary resources
     * in order to have on unity of this resource
     * @return amount, the table of amount of primary resources
     */
    public double[] getAmount(){
        return this.amount;
    }
    
    /**
     * Return the name of the Secondary Resource
     * @return name, the String which is the name of the Secondary Resource
     */
    public String toString(){
        return this.name;
    }
    
}
