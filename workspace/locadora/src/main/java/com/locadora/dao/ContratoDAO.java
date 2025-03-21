package com.locadora.dao;

import com.locadora.to.ClienteTO;
import com.locadora.to.ContratoTO;
import com.locadora.to.VeiculoTO;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContratoDAO {

    public void save(ContratoTO contrato) throws SQLException {
        String sql = "INSERT INTO CONTRATO (CLI_CODIGO, CON_DATAINICIO, CON_DATAFIM, CON_VALORCONTRATO) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, contrato.getCliente().getCliCodigo());
            stmt.setDate(2, new java.sql.Date(contrato.getConDataInicio().getTime()));
            stmt.setDate(3, new java.sql.Date(contrato.getConDataFim().getTime()));
            stmt.setDouble(4, contrato.getConValorContrato());
            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    contrato.setConCodigo(keys.getInt(1));
                }
            }
        }
    }

    public void update(ContratoTO contrato) throws SQLException {
        String sql = "UPDATE CONTRATO SET CLI_CODIGO = ?, CON_DATAINICIO = ?, CON_DATAFIM = ?, CON_VALORCONTRATO = ? WHERE CON_CODIGO = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, contrato.getCliente().getCliCodigo());
            stmt.setDate(2, new java.sql.Date(contrato.getConDataInicio().getTime()));
            stmt.setDate(3, new java.sql.Date(contrato.getConDataFim().getTime()));
            stmt.setDouble(4, contrato.getConValorContrato());
            stmt.setInt(5, contrato.getConCodigo());
            stmt.executeUpdate();
        }
    }

    public void delete(int conCodigo) throws SQLException {
        String sql = "DELETE FROM CONTRATO WHERE CON_CODIGO = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, conCodigo);
            stmt.executeUpdate();
        }
    }

    public ContratoTO getById(int conCodigo) throws SQLException {
        String sql = "SELECT CONTRATO.CON_CODIGO, CONTRATO.CON_DATAINICIO, CONTRATO.CON_DATAFIM, " +
                     "CONTRATO.CON_VALORCONTRATO, CLIENTE.CLI_CODIGO, CLIENTE.CLI_NOME " +
                     "FROM CONTRATO " +
                     "INNER JOIN CLIENTE ON CONTRATO.CLI_CODIGO = CLIENTE.CLI_CODIGO " +
                     "WHERE CONTRATO.CON_CODIGO = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, conCodigo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ContratoTO contrato = new ContratoTO();
                    contrato.setConCodigo(rs.getInt("CON_CODIGO"));
                    contrato.setConDataInicio(rs.getDate("CON_DATAINICIO"));
                    contrato.setConDataFim(rs.getDate("CON_DATAFIM"));
                    contrato.setConValorContrato(rs.getDouble("CON_VALORCONTRATO"));

                    ClienteTO cliente = new ClienteTO();
                    cliente.setCliCodigo(rs.getInt("CLI_CODIGO"));
                    cliente.setCliNome(rs.getString("CLI_NOME")); // Incluindo o nome do cliente
                    contrato.setCliente(cliente);

                    return contrato;
                }
            }
        }
        return null;
    }



    public List<ContratoTO> getAll() throws SQLException {
        String sql = "SELECT CONTRATO.CON_CODIGO, CONTRATO.CON_DATAINICIO, CONTRATO.CON_DATAFIM, " +
                     "CONTRATO.CON_VALORCONTRATO, CLIENTE.CLI_CODIGO, CLIENTE.CLI_NOME " +
                     "FROM CONTRATO " +
                     "INNER JOIN CLIENTE ON CONTRATO.CLI_CODIGO = CLIENTE.CLI_CODIGO";
        List<ContratoTO> contratos = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ContratoTO contrato = new ContratoTO();
                contrato.setConCodigo(rs.getInt("CON_CODIGO"));
                contrato.setConDataInicio(rs.getDate("CON_DATAINICIO"));
                contrato.setConDataFim(rs.getDate("CON_DATAFIM"));
                contrato.setConValorContrato(rs.getDouble("CON_VALORCONTRATO"));

                ClienteTO cliente = new ClienteTO();
                cliente.setCliCodigo(rs.getInt("CLI_CODIGO"));
                cliente.setCliNome(rs.getString("CLI_NOME")); // Incluindo o nome do cliente
                contrato.setCliente(cliente);

                contratos.add(contrato);
            }
        }
        return contratos;
    }


    public List<VeiculoTO> getListaVeiculos(int conCodigo) throws SQLException {
        String sql = "SELECT VEICULO.* FROM VEICULO INNER JOIN LOCACAO ON VEICULO.VEI_CODIGO = LOCACAO.VEI_CODIGO WHERE LOCACAO.CON_CODIGO = ?";
        List<VeiculoTO> veiculos = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, conCodigo);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    VeiculoTO veiculo = new VeiculoTO();
                    veiculo.setVeiCodigo(rs.getInt("VEI_CODIGO"));
                    veiculo.setVeiModelo(rs.getString("VEI_MODELO"));
                    veiculo.setVeiMarca(rs.getString("VEI_MARCA"));
                    veiculo.setVeiPlaca(rs.getString("VEI_PLACA"));
                    veiculo.setVeiValor(rs.getDouble("VEI_VALOR"));
                    veiculos.add(veiculo);
                }
            }
        }
        return veiculos;
    }
}
