package edu.codoacodo.application.services;

import edu.codoacodo.application.adapters.IRepository;
import edu.codoacodo.domain.models.Usuario;
import edu.codoacodo.infrastucture.database.MySQLRepositoryImpl;

public class UsuarioService implements IRepository {

    private final IRepository repository = new MySQLRepositoryImpl();

    @Override
    public void saveUser(Usuario user) {

        repository.saveUser(user);
    }

    @Override
    public Usuario findByUsername(String username) {
        return repository.findByUsername(username);
    }


}
