package fr.unice.polytech.ogl.islba.model;

public enum Rarity {
    RARE(0), UNCOMMON(500), COMMON(1000);
    
    //minimum amount to be considered at this rarity
    private int amount;
    
    /**
     * A rarity need the amount needed to be classify as Rare, uncommon, or whatever
     * @param amount
     */
    private Rarity(int amount){
        this.amount=amount;
    }
    
    /**
     * Get the correct Rarity from the amount
     * we pass in parameter
     * @param amount
     *          the amount we want to check which Rarity it is linked
     * @return the Rarity linked to the amount passed in parameter
     */
    public static Rarity getRarityFromAmount(int amount){
        int newAmount=0;
        Rarity correctRarity=Rarity.RARE;
        for(Rarity rar : Rarity.values()){
            if(amount>=rar.amount && rar.amount>=newAmount){
                newAmount=rar.amount;
                correctRarity=rar;
            }
        }
        return correctRarity;
    }
}
