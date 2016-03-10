package fr.unice.polytech.ogl.islba.model.resource; 
public class Resource {
    private java.lang.String name;

    private java.lang.String amount;

    private java.lang.String difficulty;

    public Resource(java.lang.String name) {
        this(name, null, null);
    }

    public Resource(java.lang.String name ,java.lang.String amount ,java.lang.String difficulty) {
        this.name = name;
        this.amount = amount;
        this.difficulty = difficulty;
    }

    public java.lang.String getName() {
        return name;
    }

    public java.lang.String getAmount() {
        return this.amount;
    }

    public java.lang.String getDifficulty() {
        return this.difficulty;
    }

    public void setAmount(java.lang.String amount) {
        this.amount = amount;
    }

    public void setDifficulty(java.lang.String difficulty) {
        this.difficulty = difficulty;
    }

    public boolean equals(java.lang.Object obj) {
        if (obj instanceof fr.unice.polytech.ogl.islba.model.resource.Resource) {
            fr.unice.polytech.ogl.islba.model.resource.Resource res2 = ((fr.unice.polytech.ogl.islba.model.resource.Resource)(obj));
            return this.name.equals(res2.getName());
        } 
        return false;
    }

    @java.lang.Override
    public int hashCode() {
        int cle = 11;
        int code = 337;
        code = (code * cle) + (name.hashCode());
        return code;
    }
}