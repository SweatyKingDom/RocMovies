package com.ndduroc.rocmovies.Repositories;

import com.ndduroc.rocmovies.Entity.Movie;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository {

    public static final String URL = "jdbc:mysql://phpmyadmin.test/rocmovies";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "pwsio";

    public List<Movie> findAll() {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movie";
        
        try { 
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rocmovies", "root", "pwsio");
            Statement statement = connection.createStatement();
            System.out.println("findAll() method not implemented yet");

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                // Movie movie = new Movie(
                //     rs.getInt("idMovie"),
                //     rs.getString("title"),
                //     Style.ACTION,
                //     rs.getInt("productionYear"),
                //     rs.getString("reference"),
                //     rs.getString("description")
                // );
                // movies.add(movie);
            }

        }catch (SQLException e) {
            e.printStackTrace(); 
        }
        return movies;
    }
}