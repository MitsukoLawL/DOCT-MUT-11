package fr.unice.polytech.ogl.islba.model; 
public class Equipe {
    private int MAXMEN;

    private fr.unice.polytech.ogl.islba.model.Objective objective;

    private int men;

    private java.util.Map<fr.unice.polytech.ogl.islba.model.resource.Resource, java.lang.Integer> inventory;

    public Equipe(int maxMen ,java.util.Map<java.lang.String, java.lang.Integer> objectives) {
        if ((maxMen <= 0) || (objectives == null)) {
            throw new java.lang.IllegalArgumentException("maxMen should be positive in Equipe and the objectives Map shouldn't be null.");
        } 
        this.MAXMEN = maxMen;
        this.objective = new fr.unice.polytech.ogl.islba.model.Objective(objectives);
        this.men = 0;
        inventory = new java.util.HashMap<fr.unice.polytech.ogl.islba.model.resource.Resource, java.lang.Integer>();
    }

    public void landMen(int nbMen) {
        this.men = nbMen;
        if ((this.men) > (MAXMEN)) {
            this.men = MAXMEN;
        } else if ((men) <= 0) {
            this.men = 1;
        } 
    }

    public void loseMen(int nbMen) {
        this.men -= nbMen;
        this.MAXMEN -= nbMen;
    }

    public boolean isObjective(java.lang.String resource) {
        fr.unice.polytech.ogl.islba.model.resource.Resource res = new fr.unice.polytech.ogl.islba.model.resource.Resource(resource);
        if (objective.isObjective(res)) {
            if ((inventory.get(res)) == null)
                return true;
            
            return (inventory.get(res)) < (objective.getObjective(res));
        } 
        return false;
    }

    public boolean isInInventory(java.lang.String resource) {
        fr.unice.polytech.ogl.islba.model.resource.Resource res = new fr.unice.polytech.ogl.islba.model.resource.Resource(resource);
        return inventory.containsKey(res);
    }

    public int getAmountInInventory(java.lang.String resource) {
        fr.unice.polytech.ogl.islba.model.resource.Resource res = new fr.unice.polytech.ogl.islba.model.resource.Resource(resource);
        java.lang.Integer amount = inventory.get(res);
        if (amount != null) {
            return amount;
        } 
        return 0;
    }

    public void exploitResource(java.lang.String resource, int amount) {
        fr.unice.polytech.ogl.islba.model.resource.Resource res = new fr.unice.polytech.ogl.islba.model.resource.Resource(resource);
        int newAmount = amount;
        if (inventory.containsKey(res)) {
            newAmount += inventory.get(res);
        } 
        inventory.put(res, newAmount);
        objective.updateObjectives(inventory);
    }

    public void removeResource(java.lang.String resource, int amount) {
        fr.unice.polytech.ogl.islba.model.resource.Resource res = new fr.unice.polytech.ogl.islba.model.resource.Resource(resource);
        int oldAmount = inventory.get(res);
        if ((oldAmount - amount) <= 0) {
            inventory.remove(res);
        } else {
            inventory.put(res, (oldAmount * amount));
        }
    }

    public boolean noMoreObjectives() {
        for (fr.unice.polytech.ogl.islba.model.resource.Resource res : objective.getObjectives().keySet()) {
            if ((!(inventory.containsKey(res))) || ((inventory.get(res)) < (objective.getObjectives().get(res)))) {
                return false;
            } 
        }
        return true;
    }

    public fr.unice.polytech.ogl.islba.model.Rarity getRarityOf(java.lang.String resource) {
        fr.unice.polytech.ogl.islba.model.resource.Resource res = new fr.unice.polytech.ogl.islba.model.resource.Resource(resource);
        if (!(isObjective(resource)))
            return null;
        
        return this.objective.getRarityOf(res);
    }

    public java.util.Map<fr.unice.polytech.ogl.islba.model.resource.SecondaryResource, java.lang.Integer> canCreate() {
        java.util.Map<fr.unice.polytech.ogl.islba.model.resource.SecondaryResource, java.lang.Integer> whatWeNeed;
        java.util.Map<fr.unice.polytech.ogl.islba.model.resource.SecondaryResource, java.lang.Integer> whatWeNeedClean = new java.util.HashMap<fr.unice.polytech.ogl.islba.model.resource.SecondaryResource, java.lang.Integer>();
        java.util.Map<fr.unice.polytech.ogl.islba.model.resource.SecondaryResource, java.lang.Integer> whatWeCanCreate = new java.util.HashMap<fr.unice.polytech.ogl.islba.model.resource.SecondaryResource, java.lang.Integer>();
        whatWeNeed = whatWeNeed();
        for (fr.unice.polytech.ogl.islba.model.resource.SecondaryResource res : whatWeNeed.keySet()) {
            fr.unice.polytech.ogl.islba.model.resource.PrimaryResource[] resourcesNeeded = res.getComponent();
            double[] amount = res.getAmount();
            int i;
            for (i = 0 ; i < (resourcesNeeded.length) ; i++) {
                fr.unice.polytech.ogl.islba.model.resource.Resource resource = new fr.unice.polytech.ogl.islba.model.resource.Resource(resourcesNeeded[i].toString());
                if ((!(inventory.containsKey(resource))) || ((inventory.get(resource)) < (amount[i]))) {
                    break;
                } 
            }
            if (i == (resourcesNeeded.length)) {
                whatWeNeedClean.put(res, whatWeNeed.get(res));
            } 
        }
        for (fr.unice.polytech.ogl.islba.model.resource.SecondaryResource res : whatWeNeedClean.keySet()) {
            fr.unice.polytech.ogl.islba.model.resource.PrimaryResource[] resourcesNeeded = res.getComponent();
            double[] amount = res.getAmount();
            int i;
            java.util.List<java.lang.Integer> amounts = new java.util.ArrayList<java.lang.Integer>();
            for (i = 0 ; i < (resourcesNeeded.length) ; i++) {
                int montant = ((int)((inventory.get(new fr.unice.polytech.ogl.islba.model.resource.Resource(resourcesNeeded[i].toString()))) * (amount[i])));
                amounts.add(montant);
            }
            int min = amounts.get(0);
            for (int montant : amounts) {
                if (montant < min)
                    min = montant;
                
            }
            if (min > (whatWeNeedClean.get(res)))
                min = whatWeNeedClean.get(res);
            
            if (min > 0) {
                whatWeCanCreate.put(res, min);
            } 
        }
        return whatWeCanCreate;
    }

    public java.util.Map<fr.unice.polytech.ogl.islba.model.resource.SecondaryResource, java.lang.Integer> whatWeNeed() {
        java.util.Map<fr.unice.polytech.ogl.islba.model.resource.SecondaryResource, java.lang.Integer> secondaryObjectives = objective.getSecondaryResourcesObjectives();
        java.util.Map<fr.unice.polytech.ogl.islba.model.resource.SecondaryResource, java.lang.Integer> whatWeNeed = new java.util.HashMap<fr.unice.polytech.ogl.islba.model.resource.SecondaryResource, java.lang.Integer>();
        for (fr.unice.polytech.ogl.islba.model.resource.SecondaryResource res : secondaryObjectives.keySet()) {
            fr.unice.polytech.ogl.islba.model.resource.Resource resource = new fr.unice.polytech.ogl.islba.model.resource.Resource(res.toString());
            if (!(inventory.containsKey(resource))) {
                whatWeNeed.put(res, secondaryObjectives.get(res));
            } else if ((inventory.get(resource)) < (secondaryObjectives.get(res))) {
                whatWeNeed.put(res, ((secondaryObjectives.get(res)) / (inventory.get(resource))));
            } 
        }
        return whatWeNeed;
    }

    public int getMAXMEN() {
        return MAXMEN;
    }

    public int getMen() {
        return men;
    }

    public java.util.Map<fr.unice.polytech.ogl.islba.model.resource.Resource, java.lang.Integer> getObjectives() {
        return objective.getObjectives();
    }

    public java.util.Map<fr.unice.polytech.ogl.islba.model.resource.Resource, java.lang.Integer> getInventory() {
        return inventory;
    }

    @java.lang.Override
    public boolean equals(java.lang.Object obj) {
        if (obj instanceof fr.unice.polytech.ogl.islba.model.Equipe) {
            fr.unice.polytech.ogl.islba.model.Equipe equipe2 = ((fr.unice.polytech.ogl.islba.model.Equipe)(obj));
            return (((equipe2.getMAXMEN()) == (this.MAXMEN)) && (equipe2.getObjectives().equals(getObjectives()))) && ((equipe2.getMen()) == (this.men));
        } 
        return false;
    }

    @java.lang.Override
    public int hashCode() {
        int cle = 11;
        int code = 337;
        code = (code * cle) * (MAXMEN);
        code = (code - cle) * (objective.hashCode());
        code = (code / cle) * (men);
        code = (code / cle) * (inventory.hashCode());
        return code;
    }
}