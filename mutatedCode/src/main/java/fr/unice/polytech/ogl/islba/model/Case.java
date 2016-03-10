package fr.unice.polytech.ogl.islba.model; 
public class Case {
    private java.util.List<fr.unice.polytech.ogl.islba.model.resource.Resource> ressources;

    private java.util.Map<java.lang.String, java.lang.Double> biomes;

    private int numberCaseGlimpse;

    private boolean moveOn;

    private boolean scouted;

    public Case() {
        ressources = new java.util.ArrayList<>();
        biomes = new java.util.HashMap<java.lang.String, java.lang.Double>();
        scouted = false;
        moveOn = false;
        numberCaseGlimpse = -1;
    }

    public java.util.List<fr.unice.polytech.ogl.islba.model.resource.Resource> getRessources() {
        return ressources;
    }

    public void reinitializeRessources() {
        ressources = new java.util.ArrayList<fr.unice.polytech.ogl.islba.model.resource.Resource>();
    }

    public void addRessource(fr.unice.polytech.ogl.islba.model.resource.Resource newRes) {
        ressources.add(newRes);
    }

    public boolean hasRessource(java.lang.String ressource) {
        for (int i = 0 ; i < (ressources.size()) ; i++) {
            if (ressources.get(i).getName().equals(ressource)) {
                return true;
            } 
        }
        return false;
    }

    public java.util.List<java.lang.String> getResourcesWithAmountAndCond(java.lang.String amount, java.lang.String cond) {
        java.util.List<java.lang.String> possibleResources = new java.util.ArrayList<java.lang.String>();
        java.lang.String amountRes;
        java.lang.String condRes;
        for (fr.unice.polytech.ogl.islba.model.resource.Resource res : ressources) {
            amountRes = res.getAmount();
            condRes = res.getDifficulty();
            if ((((amountRes != null) || (condRes != null)) && (amountRes.equals(amount))) || (condRes.equals(cond))) {
                possibleResources.add(res.getName());
            } 
        }
        return possibleResources;
    }

    public void addBiome(java.lang.String biome, java.lang.Double percentage) {
        this.biomes.put(biome, percentage);
    }

    public boolean hasBiome(fr.unice.polytech.ogl.islba.model.Biome biome) {
        if (biomes.containsKey(biome.toString())) {
            return true;
        } 
        return false;
    }

    public double getBiomePercentage(fr.unice.polytech.ogl.islba.model.Biome biome) {
        if (!(hasBiome(biome))) {
            return -1;
        } else {
            return biomes.get(biome.toString());
        }
    }

    public int getNumberCaseGlimpse() {
        return numberCaseGlimpse;
    }

    public void setNumberCaseGlimpse(int n) {
        numberCaseGlimpse = n;
    }

    public boolean getMoveOn() {
        return moveOn;
    }

    public void setMoveOn(boolean newMoveOn) {
        this.moveOn = newMoveOn;
    }

    public boolean getScouted() {
        return scouted;
    }

    public void setScouted(boolean newScouted) {
        this.scouted = newScouted;
    }

    public boolean isOnMap() {
        return true;
    }

    public void removeResource(java.lang.String ressource) {
        this.ressources.remove(new fr.unice.polytech.ogl.islba.model.resource.Resource(ressource));
    }
}