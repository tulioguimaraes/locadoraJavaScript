package com.locadora.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;
import com.locadora.bo.ClienteBO;
import com.locadora.to.ClienteTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cliente")
public class ManterClienteServlet extends HttpServlet {
    private final ClienteBO clienteBO = new ClienteBO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String idParam = req.getParameter("id");
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            if (idParam != null) {
                // Buscar cliente por ID
                int id = Integer.parseInt(idParam);
                ClienteTO cliente = clienteBO.getById(id);

                if (cliente != null) {
                    resp.getWriter().write(new Gson().toJson(cliente));
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().write("{\"error\": \"Cliente não encontrado\"}");
                }
            } else {
                // Buscar todos os clientes
                List<ClienteTO> clientes = clienteBO.getAll();
                resp.getWriter().write(new Gson().toJson(clientes));
            }
        } catch (SQLException | NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Ler os dados do cliente do corpo da requisição
            ClienteTO cliente = new Gson().fromJson(req.getReader(), ClienteTO.class);

            boolean isNew = cliente.getCliCodigo() == 0;
            boolean success = clienteBO.saveOrUpdate(cliente, isNew);

            if (success) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("{\"message\": \"Cliente salvo com sucesso\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\": \"Erro ao salvar cliente\"}");
            }
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Atualizar os dados do cliente
            ClienteTO cliente = new Gson().fromJson(req.getReader(), ClienteTO.class);

            boolean success = clienteBO.saveOrUpdate(cliente, false);

            if (success) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("{\"message\": \"Cliente atualizado com sucesso\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\": \"Erro ao atualizar cliente\"}");
            }
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String idParam = req.getParameter("id");

            if (idParam != null) {
                int id = Integer.parseInt(idParam);
                boolean success = clienteBO.delete(id);

                if (success) {
                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.getWriter().write("{\"message\": \"Cliente excluído com sucesso\"}");
                } else {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    resp.getWriter().write("{\"error\": \"Erro ao excluir cliente\"}");
                }
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\": \"ID do cliente não fornecido\"}");
            }
        } catch (SQLException | NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}
