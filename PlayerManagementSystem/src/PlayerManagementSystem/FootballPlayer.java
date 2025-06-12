package PlayerManagementSystem;
public class FootballPlayer extends Player implements Displayable {
    public FootballPlayer(int id, String name, int age, String team, String position) {
        super(id, name, age, team, position);
    }

    @Override
    public String getCategory() {
        return "Football";
    }

    @Override
    public void displayDetails() {
        System.out.println(getDetails());
    }
}
