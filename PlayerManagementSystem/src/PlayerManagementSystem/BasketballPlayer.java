package PlayerManagementSystem;
public class BasketballPlayer extends Player implements Displayable {
    public BasketballPlayer(int id, String name, int age, String team, String position) {
        super(id, name, age, team, position);
    }

    @Override
    public String getCategory() {
        return "Basketball";
    }

    @Override
    public void displayDetails() {
        System.out.println(getDetails());
    }
}
