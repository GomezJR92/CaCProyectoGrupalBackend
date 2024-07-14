package edu.codoacodo.application.adapters;

import edu.codoacodo.domain.models.Usuario;

public interface IRepository {

    void saveUser(Usuario user);
    Usuario findByUsername(String username);
    void deleteUser(int id);
}

