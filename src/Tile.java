import java.io.Serializable;
import java.util.*;

public class Tile implements Serializable {
    private final String type;
    private final int number;

    private String specialHarbor;//If a harbor is a special harbor what type

    public Tile(String t, int n)//Constructor
    {
        type = t;
        number = n;
        specialHarbor = "NOT"; //harbor defaulted to not a special harbor
    }

    public void setSpecialHarbor(String b) {//mutator for special harbor
        specialHarbor = b;
    }

    public String getType() {
        return type;
    }//accessor for type
    public int getNumber()
    {
        return number;
    }//accessor for number

    public String getSpecialHarbor() {
        return specialHarbor;
    }
}
