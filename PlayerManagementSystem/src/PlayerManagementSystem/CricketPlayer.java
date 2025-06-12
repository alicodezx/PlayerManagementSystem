package PlayerManagementSystem;

public class CricketPlayer extends Player implements Displayable {
    public CricketPlayer(int id, String name, int age, String team, String position) {
        super(id, name, age, team, position);
    }

    @Override
    public String getCategory() {
        return "Cricket";
    }

    @Override
    public void displayDetails() {
        System.out.println(getDetails());
    }
}
