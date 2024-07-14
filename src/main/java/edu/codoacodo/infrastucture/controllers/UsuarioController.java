package edu.codoacodo.infrastucture.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.codoacodo.application.services.UsuarioService;
import edu.codoacodo.domain.models.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users")
public class UsuarioController extends HttpServlet {
    private ObjectMapper mapper;
    private UsuarioService service;

    public UsuarioController() {
        this.mapper = new ObjectMapper();
        this.service = new UsuarioService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Usuario usuario = mapper.readValue(req.getInputStream(), Usuario.class);
        service.saveUser(usuario);
        resp.setStatus(200);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");

        if (username != null) {
            Usuario usuario = service.findByUsername(username);
            if (usuario != null) {
                resp.setStatus(200);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");

                resp.getWriter().write(mapper.writeValueAsString(usuario));
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("usuario no encontrado");
            }
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idString = req.getParameter("id");

        if (idString != null && !idString.isEmpty()) {
            int id = Integer.parseInt(idString);
            service.deleteUser(id);
            resp.setStatus(200);
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("ID DE USUARIO INVALIDO");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idString = req.getParameter("id");
        String newUsername = req.getParameter("username");
        if (idString != null && !idString.isEmpty() && newUsername != null && !newUsername.isEmpty()) {
            int id = Integer.parseInt(idString);
            Usuario usuario = usuarioService.findById(id);
            if (usuario != null) {
                usuario.setUsername(newUsername);
                usuarioService.updateUser(id, usuario);
                resp.setStatus(200);
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("Usuario no encontrado");
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Datos invalidos");
        }
    }
}



