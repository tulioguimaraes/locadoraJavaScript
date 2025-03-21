package com.locadora.dao;

import com.locadora.to.VeiculoTO;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO {

    public List<VeiculoTO> getAll() throws SQLException {
        List<VeiculoTO> listaVeiculos = new ArrayList<>();
        String sql = "SELECT VEI_CODIGO, VEI_MODELO, VEI_MARCA, VEI_PLACA, VEI_VALOR FROM VEICULO";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                VeiculoTO veiculo = new VeiculoTO();
                veiculo.setVeiCodigo(rs.getInt("VEI_CODIGO"));
                veiculo.setVeiModelo(rs.getString("VEI_MODELO"));
                veiculo.setVeiMarca(rs.getString("VEI_MARCA"));
                veiculo.setVeiPlaca(rs.getString("VEI_PLACA"));
                veiculo.setVeiValor(rs.getDouble("VEI_VALOR"));
                listaVeiculos.add(veiculo);
            }
        }
        return listaVeiculos;
    }

    public VeiculoTO getById(int veiCodigo) throws SQLException {
        VeiculoTO veiculo = null;
        String sql = "SELECT VEI_MODELO, VEI_MARCA, VEI_PLACA, VEI_VALOR FROM VEICULO WHERE VEI_CODIGO = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, veiCodigo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    veiculo = new VeiculoTO();
                    veiculo.setVeiModelo(rs.getString("VEI_MODELO"));
                    veiculo.setVeiMarca(rs.getString("VEI_MARCA"));
                    veiculo.setVeiPlaca(rs.getString("VEI_PLACA"));
                    veiculo.setVeiValor(rs.getDouble("VEI_VALOR"));
                }
            }
        }
        return veiculo;
    }

    public boolean save(VeiculoTO veiculo) throws SQLException {
        String sql = "INSERT INTO VEICULO (VEI_MODELO, VEI_MARCA, VEI_PLACA, VEI_VALOR) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, veiculo.getVeiModelo());
            stmt.setString(2, veiculo.getVeiMarca());
            stmt.setString(3, veiculo.getVeiPlaca());
            stmt.setDouble(4, veiculo.getVeiValor());

            return stmt.executeUpdate() > 0;
        }
    }
}
