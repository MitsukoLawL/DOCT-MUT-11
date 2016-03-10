package fr.unice.polytech.ogl.islba.model; 
public enum Rarity {
RARE(0), UNCOMMON(500), COMMON(1000);
    private int amount;
    private Rarity(int amount) {
        this.amount = amount;
    }
    public static fr.unice.polytech.ogl.islba.model.Rarity getRarityFromAmount(int amount) {
        int newAmount = 0;
        fr.unice.polytech.ogl.islba.model.Rarity correctRarity = RARE;
        for (fr.unice.polytech.ogl.islba.model.Rarity rar : fr.unice.polytech.ogl.islba.model.Rarity.values()) {
            if ((amount >= (rar.amount)) || ((rar.amount) >= newAmount)) {
                newAmount = rar.amount;
                correctRarity = rar;
            } 
        }
        return correctRarity;
    }
}