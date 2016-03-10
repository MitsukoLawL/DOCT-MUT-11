package fr.unice.polytech.ogl.islba.ia; 
public class FirstIA {
    private fr.unice.polytech.ogl.islba.model.Direction dirMove;

    private boolean beginning;

    private java.util.Map<java.lang.String, java.lang.Integer> exploitResources;

    private fr.unice.polytech.ogl.islba.command.Command c;

    private fr.unice.polytech.ogl.islba.model.EtatDeJeu etat;

    private int moveWithoutGlimpse;

    private boolean exploreThisCase;

    private int numberWithoutExploit;

    private boolean exploitPossible;

    public FirstIA() {
        exploitResources = new java.util.HashMap<java.lang.String, java.lang.Integer>();
        beginning = true;
        dirMove = fr.unice.polytech.ogl.islba.model.Direction.EAST;
        moveWithoutGlimpse = 20000;
        exploreThisCase = true;
        numberWithoutExploit = 20;
        exploitPossible = true;
    }

    public fr.unice.polytech.ogl.islba.command.Command takeDecision(fr.unice.polytech.ogl.islba.model.EtatDeJeu etatJeu, fr.unice.polytech.ogl.islba.model.Equipe equipe) {
        this.etat = etatJeu;
        this.c = null;
        if (((etat.getPA()) < (paForStop())) || (equipe.noMoreObjectives())) {
            c = new fr.unice.polytech.ogl.islba.command.Stop();
        } 
        if (beginning) {
            c = new fr.unice.polytech.ogl.islba.command.Land(1 , etat.getCreekID());
            beginning = false;
            return c;
        } 
        if ((c) == null) {
            c = transform();
        } 
        if ((c) == null) {
            c = explore();
        } 
        if ((c) == null) {
            c = exploit();
        } 
        if ((c) == null) {
            c = moveOrScout();
        } 
        if (((c) == null) && ((moveWithoutGlimpse) > 3)) {
            c = glimpse();
        } 
        if ((c) == null) {
            c = moveAfterGlimpse();
        } 
        if ((c) == null) {
            fr.unice.polytech.ogl.islba.model.Case next = etat.getMapMonde().getCase(fr.unice.polytech.ogl.islba.model.Coordonnees.add(dirMove.getCoo(), etat.getMapMonde().getCurrentCoo()));
            changeMoveWithoutGlimpse(dirMove.getNewDirection());
            dirMove = dirMove.getNewDirection();
            if ((next != null) && (!(next.isOnMap()))) {
                dirMove = dirMove.getNewDirection();
                next = etat.getMapMonde().getCase(fr.unice.polytech.ogl.islba.model.Coordonnees.add(dirMove.getCoo(), etat.getMapMonde().getCurrentCoo()));
            } 
            c = new fr.unice.polytech.ogl.islba.command.Move(dirMove);
            exploitResources = new java.util.HashMap<java.lang.String, java.lang.Integer>();
            exploitPossible = true;
            exploreThisCase = true;
        } 
        return c;
    }

    private fr.unice.polytech.ogl.islba.command.Command transform() {
        java.util.Map<fr.unice.polytech.ogl.islba.model.resource.SecondaryResource, java.lang.Integer> whatWeCanCreate = etat.getTeam().canCreate();
        if ((etat.getTeam().canCreate().size()) > 0) {
            java.util.Map<java.lang.String, java.lang.Integer> resourcesForTransform = new java.util.HashMap<java.lang.String, java.lang.Integer>();
            for (fr.unice.polytech.ogl.islba.model.resource.SecondaryResource res : whatWeCanCreate.keySet()) {
                int amountOfThisResource;
                for (int i = 0 ; i < (res.getComponent().length) ; i++) {
                    amountOfThisResource = ((int)((res.getAmount()[i]) * (whatWeCanCreate.get(res))));
                    if (amountOfThisResource <= 0)
                        amountOfThisResource = 1;
                    
                    resourcesForTransform.put(res.getComponent()[i].toString(), amountOfThisResource);
                }
                c = new fr.unice.polytech.ogl.islba.command.Transform(resourcesForTransform);
                break;
            }
        } 
        return c;
    }

    private fr.unice.polytech.ogl.islba.command.Command glimpse() {
        fr.unice.polytech.ogl.islba.model.Case nextCase;
        for (fr.unice.polytech.ogl.islba.model.Direction dir : fr.unice.polytech.ogl.islba.model.Direction.DIRECTIONS) {
            nextCase = etat.getMapMonde().getCase(fr.unice.polytech.ogl.islba.model.Coordonnees.add(dir.getCoo(), etat.getMapMonde().getCurrentCoo()));
            if ((nextCase == null) || ((nextCase.getNumberCaseGlimpse()) < 0)) {
                c = new fr.unice.polytech.ogl.islba.command.Glimpse(dir , 4);
                return c;
            } 
        }
        moveWithoutGlimpse = 0;
        return null;
    }

    private fr.unice.polytech.ogl.islba.command.Command moveOrScout() {
        fr.unice.polytech.ogl.islba.model.Case nextCase;
        for (fr.unice.polytech.ogl.islba.model.Direction dir : fr.unice.polytech.ogl.islba.model.Direction.DIRECTIONS) {
            nextCase = etat.getMapMonde().getCase(fr.unice.polytech.ogl.islba.model.Coordonnees.add(dir.getCoo(), etat.getMapMonde().getCurrentCoo()));
            if ((nextCase == null) || (!(nextCase.getScouted()))) {
                c = new fr.unice.polytech.ogl.islba.command.Scout(dir);
                return c;
            } 
            for (fr.unice.polytech.ogl.islba.model.resource.Resource res : nextCase.getRessources()) {
                if ((etat.getTeam().isObjective(res.getName())) && (!(nextCase.getMoveOn()))) {
                    changeMoveWithoutGlimpse(dir);
                    dirMove = dir;
                    c = new fr.unice.polytech.ogl.islba.command.Move(dirMove);
                    exploreThisCase = true;
                    exploitPossible = true;
                    exploitResources = new java.util.HashMap<java.lang.String, java.lang.Integer>();
                    return c;
                } 
            }
        }
        return null;
    }

    private fr.unice.polytech.ogl.islba.command.Command moveAfterGlimpse() {
        c = moveWithBiomes();
        if ((c) == null) {
            c = moveAfterGlimpseWater();
        } 
        return c;
    }

    private fr.unice.polytech.ogl.islba.command.Command moveAfterGlimpseWater() {
        fr.unice.polytech.ogl.islba.model.Coordonnees cooCase3 = fr.unice.polytech.ogl.islba.model.Coordonnees.add(etat.getMapMonde().getCurrentCoo(), fr.unice.polytech.ogl.islba.model.Coordonnees.multiplyBy(dirMove.getCoo(), 3));
        fr.unice.polytech.ogl.islba.model.Case nextCase3 = etat.getMapMonde().getCase(cooCase3);
        if ((nextCase3 == null) || (((nextCase3.isOnMap()) && (!(nextCase3.hasBiome(fr.unice.polytech.ogl.islba.model.Biome.OCEAN)))) && (!(nextCase3.hasBiome(fr.unice.polytech.ogl.islba.model.Biome.LAKE))))) {
            changeMoveWithoutGlimpse(dirMove);
            exploreThisCase = true;
            exploitPossible = true;
            exploitResources = new java.util.HashMap<java.lang.String, java.lang.Integer>();
            return new fr.unice.polytech.ogl.islba.command.Move(dirMove);
        } 
        for (fr.unice.polytech.ogl.islba.model.Direction dir : fr.unice.polytech.ogl.islba.model.Direction.DIRECTIONS) {
            cooCase3 = fr.unice.polytech.ogl.islba.model.Coordonnees.add(etat.getMapMonde().getCurrentCoo(), fr.unice.polytech.ogl.islba.model.Coordonnees.multiplyBy(dir.getCoo(), 3));
            nextCase3 = etat.getMapMonde().getCase(cooCase3);
            if (((((nextCase3 != null) && (nextCase3.isOnMap())) && (!(nextCase3.hasBiome(fr.unice.polytech.ogl.islba.model.Biome.OCEAN)))) && (!(nextCase3.hasBiome(fr.unice.polytech.ogl.islba.model.Biome.LAKE)))) && (!(nextCase3.getMoveOn()))) {
                changeMoveWithoutGlimpse(dir);
                dirMove = dir;
                c = new fr.unice.polytech.ogl.islba.command.Move(dirMove);
                exploreThisCase = true;
                exploitPossible = true;
                exploitResources = new java.util.HashMap<java.lang.String, java.lang.Integer>();
                return c;
            } 
        }
        return null;
    }

    private fr.unice.polytech.ogl.islba.command.Command moveWithBiomes() {
        int maxDist = 4;
        fr.unice.polytech.ogl.islba.model.Case nextCase;
        fr.unice.polytech.ogl.islba.model.Coordonnees nextCoo;
        fr.unice.polytech.ogl.islba.model.Coordonnees currentCoo = etat.getMapMonde().getCurrentCoo();
        for (int i = 1 ; i < maxDist ; i++) {
            for (fr.unice.polytech.ogl.islba.model.Direction dir : fr.unice.polytech.ogl.islba.model.Direction.DIRECTIONS) {
                nextCoo = fr.unice.polytech.ogl.islba.model.Coordonnees.multiplyBy(dir.getCoo(), i);
                nextCoo = fr.unice.polytech.ogl.islba.model.Coordonnees.add(nextCoo, currentCoo);
                nextCase = etat.getMapMonde().getCase(nextCoo);
                for (fr.unice.polytech.ogl.islba.model.Biome biome : fr.unice.polytech.ogl.islba.model.Biome.values()) {
                    if (((nextCase != null) && (nextCase.hasBiome(biome))) && (!(nextCase.getMoveOn()))) {
                        for (java.lang.String resource : biome.getResources()) {
                            if (etat.getTeam().isObjective(resource)) {
                                changeMoveWithoutGlimpse(dir);
                                dirMove = dir;
                                c = new fr.unice.polytech.ogl.islba.command.Move(dirMove);
                                exploitPossible = true;
                                exploreThisCase = true;
                                exploitResources = new java.util.HashMap<java.lang.String, java.lang.Integer>();
                                return c;
                            } 
                        }
                    } 
                }
            }
        }
        return null;
    }

    private void changeMoveWithoutGlimpse(fr.unice.polytech.ogl.islba.model.Direction newDir) {
        (moveWithoutGlimpse)++;
        if (!(newDir.equals(dirMove))) {
            moveWithoutGlimpse = 0;
        } 
    }

    private int paForStop() {
        if (beginning) {
            return 0;
        } 
        return ((java.lang.Math.abs(etat.getMapMonde().getCurrentCoo().getX())) + (java.lang.Math.abs(etat.getMapMonde().getCurrentCoo().getY()))) * 3;
    }

    private boolean checkResourceOnCase(fr.unice.polytech.ogl.islba.model.Case caseCheck) {
        for (fr.unice.polytech.ogl.islba.model.resource.Resource res : caseCheck.getRessources()) {
            if (etat.getTeam().isObjective(res.getName())) {
                return true;
            } 
        }
        return false;
    }

    private fr.unice.polytech.ogl.islba.command.Command exploit() {
        fr.unice.polytech.ogl.islba.model.Equipe team = etat.getTeam();
        fr.unice.polytech.ogl.islba.model.Rarity rarity;
        java.lang.String resourceName = "";
        fr.unice.polytech.ogl.islba.model.Case currentCase = etat.getMapMonde().getCurrentCase();
        int numberOfExploit = 0;
        boolean exploit = false;
        for (fr.unice.polytech.ogl.islba.model.resource.Resource res : currentCase.getRessources()) {
            exploit = false;
            if (team.isObjective(res.getName())) {
                resourceName = res.getName();
                rarity = team.getRarityOf(res.getName());
                numberOfExploit = (exploitResources.get(res.getName())) == null ? 0 : exploitResources.get(res.getName());
                if (((checkAmountAndCondOnCase(currentCase, "HIGH", "EASY", res.getName())) || (checkAmountAndCondOnCase(currentCase, "MEDIUM", "EASY", res.getName()))) || (checkAmountAndCondOnCase(currentCase, "HIGH", "FAIR", res.getName()))) {
                    exploit = true;
                } else if ((!(rarity.equals(fr.unice.polytech.ogl.islba.model.Rarity.COMMON))) && ((((checkAmountAndCondOnCase(currentCase, "MEDIUM", "FAIR", res.getName())) || (checkAmountAndCondOnCase(currentCase, "HIGH", "FAIR", res.getName()))) || (checkAmountAndCondOnCase(currentCase, "MEDIUM", "EASY", res.getName()))) || (checkAmountAndCondOnCase(currentCase, "HIGH", "EASY", res.getName())))) {
                    exploit = true;
                } else if (((!(rarity.equals(fr.unice.polytech.ogl.islba.model.Rarity.COMMON))) && (!(rarity.equals(fr.unice.polytech.ogl.islba.model.Rarity.UNCOMMON)))) && (numberOfExploit < 1)) {
                    exploit = true;
                } 
                if ((!exploit) && (numberOfExploit < 1)) {
                    (numberWithoutExploit)--;
                    if (((numberWithoutExploit) < 0) && (exploitPossible)) {
                        exploit = true;
                    } 
                } 
                if ((numberOfExploit < 1) && exploit) {
                    c = new fr.unice.polytech.ogl.islba.command.Exploit(resourceName);
                    numberOfExploit = 1;
                    exploitResources.put(resourceName, (numberOfExploit + 1));
                    break;
                } 
            } 
        }
        return c;
    }

    private fr.unice.polytech.ogl.islba.command.Command explore() {
        if ((checkResourceOnCase(etat.getMapMonde().getCurrentCase())) && (exploreThisCase)) {
            exploreThisCase = false;
            c = new fr.unice.polytech.ogl.islba.command.Explore();
        } 
        return c;
    }

    private boolean checkAmountAndCondOnCase(fr.unice.polytech.ogl.islba.model.Case caseChecked, java.lang.String amount, java.lang.String cond, java.lang.String res) {
        java.util.List<java.lang.String> resources = caseChecked.getResourcesWithAmountAndCond(amount, cond);
        if (resources.contains(res)) {
            return true;
        } 
        return false;
    }
}