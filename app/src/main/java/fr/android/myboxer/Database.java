package fr.android.myboxer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Calendar;

public class Database {

    private Connection connection;
    private final String user = "postgres";
    private final String pass = "Passw0rd";
    private final String url = "jdbc:postgresql://10.0.2.2:5432/MyBoxer";
    private boolean status;

    public Database() {
        connect();
        System.out.println("Connected to database: " + status);
    }

    private void connect() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("org.postgresql.Driver");
                    connection = DriverManager.getConnection(url, user, pass);
                    status = true;
                    System.out.println("connected:" + status);
                } catch (Exception e) {
                    status = false;
                    System.out.print(e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
            this.status = false;
        }
    }

    public void save(Fight fight) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String sql = "INSERT INTO fight (nom1, age1, poids1, nom2, age2, poids2, date, gagne, lat, lng) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    java.sql.PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, fight.getOpposant1().getNom());
                    statement.setInt(2, fight.getOpposant1().getAge());
                    statement.setInt(3, fight.getOpposant1().getPoids());
                    statement.setString(4, fight.getOpposant2().getNom());
                    statement.setInt(5, fight.getOpposant2().getAge());
                    statement.setInt(6, fight.getOpposant2().getPoids());
                    statement.setDate(7, new java.sql.Date(fight.getDate().getTimeInMillis()));
                    statement.setBoolean(8, fight.isGagne());
                    statement.setDouble(9, fight.getLat());
                    statement.setDouble(10, fight.getLng());
                    statement.executeUpdate();
                    statement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public ArrayList<Fight> getAllFights(){
        ArrayList<Fight> fights = new ArrayList<>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String sql = "SELECT * FROM fight";
                    java.sql.PreparedStatement statement = connection.prepareStatement(sql);
                    java.sql.ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        Opposant opposant1 = new Opposant(resultSet.getString("nom1"), resultSet.getInt("age1"), resultSet.getInt("poids1"));
                        Opposant opposant2 = new Opposant(resultSet.getString("nom2"), resultSet.getInt("age2"), resultSet.getInt("poids2"));
                        Calendar date = Calendar.getInstance();
                        date.setTime(resultSet.getDate("date"));
                        boolean gagne = resultSet.getBoolean("gagne");
                        double lat = resultSet.getDouble("lat");
                        double lng = resultSet.getDouble("lng");
                        Fight fight = new Fight(opposant1, opposant2, date, gagne, lat, lng);
                        fights.add(fight);
                        }
                    statement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fights;
    }
}