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
}
