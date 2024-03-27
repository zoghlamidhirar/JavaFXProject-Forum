package services;

import models.User;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IService<User> {

    Connection cnx = MyDatabase.getInstance().getConnection();

    @Override
    public void add(User r) throws SQLException {

        String sql = "INSERT INTO user (id, nom) VALUES (?, ?)";

        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            pstmt.setInt(1, r.getId());
            pstmt.setString(2, r.getNom());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
        }
    }

    @Override
    public void update(User r) throws SQLException {
        try {
            String requete = "UPDATE user SET nom=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, r.getNom());
            pst.setInt(2, r.getId());
            pst.executeUpdate();
            System.out.println("Mise à jour avec succès");
        } catch (SQLException ex) {
            throw new SQLException("Erreur lors de la mise à jour : " + ex.getMessage());
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM user WHERE id = ?";
        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User deleted successfully!");
            } else {
                System.out.println("No user found with the given id.");
            }
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la suppression de l'utilisateur : " + e.getMessage());
        }
    }

    @Override
    public User getById(int id) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM user WHERE id = ?";
        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setNom(rs.getString("nom"));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la récupération de l'utilisateur : " + e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try (PreparedStatement pstmt = cnx.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la récupération des utilisateurs : " + e.getMessage());
        }
        return users;
    }
}
