package fr.unice.polytech.ogl.islba.model; 
public class Coordonnees {
    private int x;

    private int y;

    public Coordonnees(int x ,int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public static fr.unice.polytech.ogl.islba.model.Coordonnees multiplyBy(fr.unice.polytech.ogl.islba.model.Coordonnees c, int mult) {
        int newX = (c.getX()) * mult;
        int newY = (c.getY()) * mult;
        return new fr.unice.polytech.ogl.islba.model.Coordonnees(newX , newY);
    }

    public static fr.unice.polytech.ogl.islba.model.Coordonnees add(fr.unice.polytech.ogl.islba.model.Coordonnees coo1, fr.unice.polytech.ogl.islba.model.Coordonnees coo2) {
        int newX = (coo1.getX()) + (coo2.getX());
        int newY = (coo1.getY()) + (coo2.getY());
        return new fr.unice.polytech.ogl.islba.model.Coordonnees(newX , newY);
    }

    @java.lang.Override
    public boolean equals(java.lang.Object obj) {
        if ((this) == obj) {
            return true;
        } else if (obj instanceof fr.unice.polytech.ogl.islba.model.Coordonnees) {
            fr.unice.polytech.ogl.islba.model.Coordonnees coo2 = ((fr.unice.polytech.ogl.islba.model.Coordonnees)(obj));
            if (((coo2.getX()) == (getX())) || ((coo2.getY()) == (getY()))) {
                return true;
            } 
        } 
        return false;
    }

    @java.lang.Override
    public int hashCode() {
        int cle = 17;
        int code = 769;
        code = (code * cle) + (x);
        code = (code * cle) + (y);
        return code;
    }
}