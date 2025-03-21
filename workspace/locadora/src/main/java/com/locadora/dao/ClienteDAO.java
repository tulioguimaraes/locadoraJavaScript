package com.locadora.dao;

import com.locadora.to.ClienteTO;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public boolean save(ClienteTO cliente) throws SQLException {
        String sql = "INSERT INTO cliente (cli_nome, cli_endereco, cli_telefone, cli_cpf) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getCliNome());
            stmt.setString(2, cliente.getCliEndereco());
            stmt.setString(3, cliente.getCliTelefone());
            stmt.setString(4, cliente.getCliCpf());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean update(ClienteTO cliente) throws SQLException {
        String sql = "UPDATE cliente SET cli_nome = ?, cli_endereco = ?, cli_telefone = ?, cli_cpf = ? WHERE cli_codigo = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getCliNome());
            stmt.setString(2, cliente.getCliEndereco());
            stmt.setString(3, cliente.getCliTelefone());
            stmt.setString(4, cliente.getCliCpf());
            stmt.setInt(5, cliente.getCliCodigo());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM cliente WHERE cli_codigo = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    public ClienteTO getById(int id) throws SQLException {
        String sql = "SELECT * FROM cliente WHERE cli_codigo = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ClienteTO cliente = new ClienteTO();
                    cliente.setCliCodigo(rs.getInt("cli_codigo"));
                    cliente.setCliNome(rs.getString("cli_nome"));
                    cliente.setCliEndereco(rs.getString("cli_endereco"));
                    cliente.setCliTelefone(rs.getString("cli_telefone"));
                    cliente.setCliCpf(rs.getString("cli_cpf"));
                    return cliente;
                }
            }
        }
        return null;
    }

    public List<ClienteTO> getAll() throws SQLException {
        String sql = "SELECT * FROM cliente";
        List<ClienteTO> clientes = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ClienteTO cliente = new ClienteTO();
                cliente.setCliCodigo(rs.getInt("cli_codigo"));
                cliente.setCliNome(rs.getString("cli_nome"));
                cliente.setCliEndereco(rs.getString("cli_endereco"));
                cliente.setCliTelefone(rs.getString("cli_telefone"));
                cliente.setCliCpf(rs.getString("cli_cpf"));
                clientes.add(cliente);
            }
        }
        return clientes;
    }
}
