package M06;

public class Athlete {
    private int id;
    private String name;
    private int sportId;

    public Athlete(int id, String name, int sportId) {
        this.id = id;
        this.name = name;
        this.sportId = sportId;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    @Override
    public String toString() {
        return "Athlete{id=" + id + ", name='" + name + "', sportId=" + sportId + "}";
    }
}
