package PlayerManagementSystem;

public abstract class Player {
    protected int id;
    protected String name;
    protected int age;
    protected String team;
    protected String position;

    public Player(int id, String name, int age, String team, String position) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.team = team;
        this.position = position;
    }

    public abstract String getCategory();

    public String getDetails() {
        return id + " | " + name + " | " + age + " | " + team + " | " + getCategory() + " | " + position;
    }

    public String getPosition() {
        return position;
    }
}
