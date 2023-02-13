import java.io.Serializable;

public class catanMessage implements Serializable {
    private String type;
    private String title;
    private int number;

    public catanMessage(String type, String title) {
        this.type = type;
        this.title = title;
        titleToNumber();
    }

    public String getType() {
        return type;
    }

    public void titleToNumber() {
        number = Integer.parseInt(title);
    }

    public int getNumber() {
        return number;
    }

}
