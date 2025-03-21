package com.locadora.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.locadora.bo.ContratoBO;
import com.locadora.to.ContratoTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/contrato")
public class ManterContratoServlet extends HttpServlet {
    private final ContratoBO contratoBO = new ContratoBO();
    private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String idParam = req.getParameter("id");
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            if (idParam != null) {
                // Busca um contrato específico por ID
                int id = Integer.parseInt(idParam);
                ContratoTO contrato = contratoBO.getContratoById(id);

                if (contrato != null) {
                    resp.getWriter().write(gson.toJson(contrato));
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().write("{\"error\": \"Contrato não encontrado\"}");
                }
            } else {
                // Lista todos os contratos
                List<ContratoTO> contratos = contratoBO.listarContratos();
                resp.getWriter().write(gson.toJson(contratos));
            }
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"Erro ao buscar contratos: " + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Converter o JSON recebido para um objeto ContratoTO
            ContratoTO contrato = gson.fromJson(req.getReader(), ContratoTO.class);

            // Validações dos campos obrigatórios
            if (contrato.getCliente() == null || contrato.getCliente().getCliCodigo() <= 0) {
                throw new IllegalArgumentException("Código do cliente é obrigatório e deve ser maior que zero.");
            }
            if (contrato.getConDataInicio() == null) {
                throw new IllegalArgumentException("Data de início é obrigatória.");
            }
            if (contrato.getConDataFim() == null) {
                throw new IllegalArgumentException("Data de fim é obrigatória.");
            }
            if (contrato.getConValorContrato() <= 0) {
                throw new IllegalArgumentException("O valor do contrato deve ser maior que zero.");
            }

            // Salvar o contrato no banco de dados
            contratoBO.saveContrato(contrato, true);

            // Resposta de sucesso
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            resp.getWriter().write(gson.toJson(Map.of("message", "Contrato salvo com sucesso!")));
        } catch (IllegalArgumentException e) {
            // Retorna um erro 400 (Bad Request)
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.setContentType("application/json");
            resp.getWriter().write(gson.toJson(Map.of("error", e.getMessage())));
        } catch (SQLException e) {
            // Retorna um erro 500 (Internal Server Error)
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            resp.getWriter().write(gson.toJson(Map.of("error", "Erro ao salvar contrato: " + e.getMessage())));
        }
    }




    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Atualiza um contrato existente
            ContratoTO contrato = gson.fromJson(req.getReader(), ContratoTO.class);
            contratoBO.saveContrato(contrato, false);

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"message\": \"Contrato atualizado com sucesso!\"}");
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"error\": \"Erro ao atualizar contrato: " + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Exclui um contrato por ID
            String idParam = req.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\": \"ID do contrato não informado\"}");
                return;
            }

            int id = Integer.parseInt(idParam);
            contratoBO.deleteContrato(id);

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"message\": \"Contrato excluído com sucesso!\"}");
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"error\": \"Erro ao excluir contrato: " + e.getMessage() + "\"}");
        }
    }
 // Classes auxiliares para estruturar a resposta
    class Resposta {
        private String message;

        public Resposta(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    class RespostaErro {
        private String error;

        public RespostaErro(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }
    }
}
