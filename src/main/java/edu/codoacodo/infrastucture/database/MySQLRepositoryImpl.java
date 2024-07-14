package edu.codoacodo.infrastucture.database;

import edu.codoacodo.application.adapters.IRepository;
import edu.codoacodo.domain.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLRepositoryImpl implements IRepository {

    private Connection conexion;

    public MySQLRepositoryImpl() {
        this.conexion = DataBaseConnection.getConnection();
    }


    @Override
    public void saveUser(Usuario user) {

        String sql = "INSERT INTO users (username, password, email) VALUES(?, ?, ?)";

        try {
            PreparedStatement preparador = this.conexion.prepareStatement(sql);
            preparador.setString(1, user.getUsername() );
            preparador.setString(2, user.getPassword() );
            preparador.setString(3, user.getEmail() );

            preparador.executeUpdate();
            preparador.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
