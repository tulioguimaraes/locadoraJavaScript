package com.locadora.servlet;

import com.google.gson.Gson;
import com.locadora.bo.VeiculoBO;
import com.locadora.to.VeiculoTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/veiculo")
public class ManterVeiculoServlet extends HttpServlet {
    private final VeiculoBO veiculoBO = new VeiculoBO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String idParam = req.getParameter("id");
            resp.setContentType("application/json");

            if (idParam != null) {
                int id = Integer.parseInt(idParam);
                VeiculoTO veiculo = veiculoBO.getById(id);
                resp.getWriter().write(new Gson().toJson(veiculo));
            } else {
                List<VeiculoTO> veiculos = veiculoBO.getAll();
                resp.getWriter().write(new Gson().toJson(veiculos));
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            VeiculoTO veiculo = new Gson().fromJson(req.getReader(), VeiculoTO.class);
            boolean success = veiculoBO.save(veiculo);

            if (success) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("{\"message\": \"Veículo salvo com sucesso\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\": \"Erro ao salvar veículo\"}");
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}
