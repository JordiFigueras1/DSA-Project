package edu.upc.dsa;

import edu.upc.dsa.models.Track;
import edu.upc.dsa.models.Usuario;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;

public interface GameManager {
    public Usuario addUser(String  mail, String username, String password);
    public Usuario addUser(Usuario u);
    public Usuario getUser (String mail);
    public List<Usuario> findAll();
    public void deleteUser(String id);
    public Usuario updateUser(Usuario u);

    public int size();

}
