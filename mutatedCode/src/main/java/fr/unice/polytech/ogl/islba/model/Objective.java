package fr.unice.polytech.ogl.islba.model; 
public class Objective {
    private java.util.Map<fr.unice.polytech.ogl.islba.model.resource.Resource, java.lang.Integer> objectives;

    private java.util.Map<fr.unice.polytech.ogl.islba.model.Rarity, java.util.List<fr.unice.polytech.ogl.islba.model.resource.Resource>> objectivesByRarity;

    private java.util.Map<fr.unice.polytech.ogl.islba.model.resource.PrimaryResource, java.lang.Integer> objectivesAsPrimaryResources;

    private java.util.Map<fr.unice.polytech.ogl.islba.model.resource.SecondaryResource, java.lang.Integer> secondaryResourcesObjectives;

    private fr.unice.polytech.ogl.islba.model.resource.ResourcesType bank;

    public Objective(java.util.Map<java.lang.String, java.lang.Integer> obj) {
        this.objectives = new java.util.HashMap<fr.unice.polytech.ogl.islba.model.resource.Resource, java.lang.Integer>();
        this.objectivesByRarity = new java.util.HashMap<fr.unice.polytech.ogl.islba.model.Rarity, java.util.List<fr.unice.polytech.ogl.islba.model.resource.Resource>>();
        this.objectivesAsPrimaryResources = new java.util.HashMap<fr.unice.polytech.ogl.islba.model.resource.PrimaryResource, java.lang.Integer>();
        this.secondaryResourcesObjectives = new java.util.HashMap<fr.unice.polytech.ogl.islba.model.resource.SecondaryResource, java.lang.Integer>();
        this.bank = new fr.unice.polytech.ogl.islba.model.resource.ResourcesType();
        for (fr.unice.polytech.ogl.islba.model.Rarity r : fr.unice.polytech.ogl.islba.model.Rarity.values()) {
            objectivesByRarity.put(r, new java.util.ArrayList<fr.unice.polytech.ogl.islba.model.resource.Resource>());
        }
        fr.unice.polytech.ogl.islba.model.resource.Resource resource;
        int amount;
        java.util.List<fr.unice.polytech.ogl.islba.model.resource.Resource> resources;
        for (java.lang.String res : obj.keySet()) {
            resource = new fr.unice.polytech.ogl.islba.model.resource.Resource(res);
            amount = obj.get(res);
            if (amount > 0) {
                objectives.put(resource, amount);
                fillObjectivesByRarity(resource, amount);
                fillObjectivesAsPrimaryResources(res, amount);
                if (bank.isSecondaryResource(res)) {
                    secondaryResourcesObjectives.put(bank.getSecondaryResource(res), amount);
                } 
            } 
        }
    }

    private void fillObjectivesByRarity(fr.unice.polytech.ogl.islba.model.resource.Resource resource, int amount) {
        if (bank.isSecondaryResource(resource.getName())) {
            fr.unice.polytech.ogl.islba.model.resource.SecondaryResource r2 = bank.getSecondaryResource(resource.getName());
            for (fr.unice.polytech.ogl.islba.model.resource.PrimaryResource r1 : r2.getComponentsNeeded().keySet()) {
                addToTheObjectivesByRarity(new fr.unice.polytech.ogl.islba.model.resource.Resource(r1.toString()), ((int)(amount * (r2.getComponentsNeeded().get(r1)))));
            }
        } 
        addToTheObjectivesByRarity(resource, amount);
    }

    private void addToTheObjectivesByRarity(fr.unice.polytech.ogl.islba.model.resource.Resource resource, int amount) {
        fr.unice.polytech.ogl.islba.model.resource.PrimaryResource prim = bank.getPrimaryResource(resource.getName());
        int ancientAmount = (objectivesAsPrimaryResources.get(prim)) != null ? objectivesAsPrimaryResources.get(prim) : 0;
        fr.unice.polytech.ogl.islba.model.Rarity ancientRar = fr.unice.polytech.ogl.islba.model.Rarity.getRarityFromAmount(ancientAmount);
        int toRemove = -1;
        for (int i = 0 ; i < (objectivesByRarity.get(ancientRar).size()) ; i++) {
            if (objectivesByRarity.get(ancientRar).get(i).equals(resource)) {
                toRemove = i;
                break;
            } 
        }
        if (toRemove > (-1)) {
            objectivesByRarity.get(ancientRar).remove(toRemove);
        } 
        fr.unice.polytech.ogl.islba.model.Rarity rar = fr.unice.polytech.ogl.islba.model.Rarity.getRarityFromAmount((amount + ancientAmount));
        java.util.List<fr.unice.polytech.ogl.islba.model.resource.Resource> resources = objectivesByRarity.get(rar);
        resources.add(resource);
        objectivesByRarity.put(rar, resources);
    }

    private void fillObjectivesAsPrimaryResources(java.lang.String res, int amount) {
        if (bank.isSecondaryResource(res)) {
            java.util.Map<fr.unice.polytech.ogl.islba.model.resource.PrimaryResource, java.lang.Double> secondaryRes = bank.getSecondaryResource(res).getComponentsNeeded();
            for (fr.unice.polytech.ogl.islba.model.resource.PrimaryResource primeRes : secondaryRes.keySet()) {
                addResourceToObjectiveAsPrimaryResources(primeRes, ((int)((secondaryRes.get(primeRes)) * amount)));
            }
        } else if (bank.isPrimaryResource(res)) {
            addResourceToObjectiveAsPrimaryResources(bank.getPrimaryResource(res), amount);
        } 
    }

    private void addResourceToObjectiveAsPrimaryResources(fr.unice.polytech.ogl.islba.model.resource.PrimaryResource res, int amount) {
        int newAmount = amount;
        if (objectivesAsPrimaryResources.containsKey(res)) {
            newAmount += objectivesAsPrimaryResources.get(res);
        } 
        objectivesAsPrimaryResources.put(res, newAmount);
    }

    public boolean isObjective(fr.unice.polytech.ogl.islba.model.resource.Resource res) {
        return (objectives.containsKey(res)) && (objectivesAsPrimaryResources.containsKey(bank.getPrimaryResource(res.getName())));
    }

    public void updateObjectives(java.util.Map<fr.unice.polytech.ogl.islba.model.resource.Resource, java.lang.Integer> inventory) {
        objectivesAsPrimaryResources.clear();
        for (fr.unice.polytech.ogl.islba.model.resource.Resource resource : objectives.keySet()) {
            if ((inventory.get(resource)) == null) {
                fillObjectivesAsPrimaryResources(resource.getName(), objectives.get(resource));
            } else if ((inventory.get(resource)) < (objectives.get(resource))) {
                fillObjectivesAsPrimaryResources(resource.getName(), ((objectives.get(resource)) - (inventory.get(resource))));
            } 
        }
    }

    public fr.unice.polytech.ogl.islba.model.Rarity getRarityOf(fr.unice.polytech.ogl.islba.model.resource.Resource resource) {
        for (fr.unice.polytech.ogl.islba.model.Rarity r : fr.unice.polytech.ogl.islba.model.Rarity.values()) {
            if (objectivesByRarity.get(r).contains(resource)) {
                return r;
            } 
        }
        return null;
    }

    public java.util.Map<fr.unice.polytech.ogl.islba.model.resource.Resource, java.lang.Integer> getObjectives() {
        return objectives;
    }

    public int getObjective(fr.unice.polytech.ogl.islba.model.resource.Resource res) {
        int amount = 0;
        if (objectives.containsKey(res))
            amount += objectives.get(res);
        
        if (objectivesAsPrimaryResources.containsKey(bank.getPrimaryResource(res.getName())))
            amount += objectivesAsPrimaryResources.get(bank.getPrimaryResource(res.getName()));
        
        return amount;
    }

    public java.util.Map<fr.unice.polytech.ogl.islba.model.resource.PrimaryResource, java.lang.Integer> getObjectiveAsPrimaryResources() {
        return objectivesAsPrimaryResources;
    }

    public java.util.Map<fr.unice.polytech.ogl.islba.model.Rarity, java.util.List<fr.unice.polytech.ogl.islba.model.resource.Resource>> getObjectivesByRarity() {
        return objectivesByRarity;
    }

    public java.util.Map<fr.unice.polytech.ogl.islba.model.resource.SecondaryResource, java.lang.Integer> getSecondaryResourcesObjectives() {
        return secondaryResourcesObjectives;
    }
}