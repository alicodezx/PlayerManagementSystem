package PlayerManagementSystem;
public class TennisPlayer extends Player implements Displayable {
    public TennisPlayer(int id, String name, int age, String team, String position) {
        super(id, name, age, team, position);
    }

    @Override
    public String getCategory() {
        return "Tennis";
    }

    @Override
    public void displayDetails() {
        System.out.println(getDetails());
    }
}
