package PlayerManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PlayerGUI extends JFrame {
    JTextField nameField, ageField, teamField, positionField;
    JComboBox<String> categoryBox;
    JTextArea displayArea;
    Connection conn;

    public PlayerGUI() {
        setTitle("Player Manager");
        setSize(550, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        nameField = new JTextField(10);
        ageField = new JTextField(5);
        teamField = new JTextField(10);
        positionField = new JTextField(10);

        String[] categories = {"Cricket", "Football", "Basketball", "Tennis"};
        categoryBox = new JComboBox<>(categories);

        displayArea = new JTextArea(12, 45);
        JButton insertBtn = new JButton("Insert");
        JButton deleteBtn = new JButton("Delete by Name");
        JButton displayBtn = new JButton("Display All");
        JButton searchBtn = new JButton("Search by Name");

        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Age:")); 
        add(ageField);
        add(new JLabel("Team:")); 
        add(teamField);
        add(new JLabel("Position:")); 
        add(positionField);
        add(new JLabel("Category:")); 
        add(categoryBox);
        add(insertBtn); 
        add(deleteBtn); 
        add(displayBtn); 
        add(searchBtn);
        add(new JScrollPane(displayArea));

        connectDB();

        insertBtn.addActionListener(e -> insertPlayer());
        deleteBtn.addActionListener(e -> deletePlayer());
        displayBtn.addActionListener(e -> displayPlayers());
        searchBtn.addActionListener(e -> searchPlayer());

        setVisible(true);
    }

    void connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/PlayerDB", "root", ""
            );
            System.out.println("Connected to database successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "DB Connection Failed: " + e.getMessage());
        }
    }

    void insertPlayer() {
        try {
            String query = "INSERT INTO players (name, age, category, team, position) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, nameField.getText());
            pst.setInt(2, Integer.parseInt(ageField.getText()));
            pst.setString(3, categoryBox.getSelectedItem().toString());
            pst.setString(4, teamField.getText());
            pst.setString(5, positionField.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Player Added!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Insert Error: " + e.getMessage());
        }
    }

    void deletePlayer() {
        try {
            String query = "DELETE FROM players WHERE name = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, nameField.getText());
            int rows = pst.executeUpdate();
            JOptionPane.showMessageDialog(this, rows + " Player(s) Deleted");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Delete Error: " + e.getMessage());
        }
    }

    void displayPlayers() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM players");
            displayArea.setText("");
            while (rs.next()) {
                displayArea.append(rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getInt("age") + " | " +
                        rs.getString("team") + " | " +
                        rs.getString("category") + " | " +
                        rs.getString("position") + "\n");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Display Error: " + e.getMessage());
        }
    }

    void searchPlayer() {
        try {
            String name = nameField.getText();
            String query = "SELECT * FROM players WHERE name = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            displayArea.setText("");

            if (rs.next()) {
                displayArea.append("Found Player:\n");
                displayArea.append(rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getInt("age") + " | " +
                        rs.getString("team") + " | " +
                        rs.getString("category") + " | " +
                        rs.getString("position") + "\n");
            } else {
                displayArea.setText("No player found with name: " + name);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Search Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new PlayerGUI();
    }
}
