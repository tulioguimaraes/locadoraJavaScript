package com.locadora.dao;

import com.locadora.to.LocacaoTO;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LocacaoDAO {
    public void save(LocacaoTO locacao) throws SQLException {
        String sql = "INSERT INTO LOCACAO (CON_CODIGO, VEI_CODIGO) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, locacao.getContrato().getConCodigo());
            stmt.setInt(2, locacao.getVeiculo().getVeiCodigo());
            stmt.executeUpdate();
        }
    }

    public void deleteByContrato(int conCodigo) throws SQLException {
        String sql = "DELETE FROM LOCACAO WHERE CON_CODIGO = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, conCodigo);
            stmt.executeUpdate();
        }
    }
}
