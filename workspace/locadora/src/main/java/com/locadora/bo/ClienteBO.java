package com.locadora.bo;

import com.locadora.dao.ClienteDAO;
import com.locadora.to.ClienteTO;

import java.sql.SQLException;
import java.util.List;

public class ClienteBO {
    private final ClienteDAO clienteDAO = new ClienteDAO();

    public boolean saveOrUpdate(ClienteTO cliente, boolean isNew) throws SQLException {
        if (isNew) {
            return clienteDAO.save(cliente);
        } else {
            return clienteDAO.update(cliente);
        }
    }

    public boolean delete(int id) throws SQLException {
        return clienteDAO.delete(id);
    }

    public ClienteTO getById(int id) throws SQLException {
        return clienteDAO.getById(id);
    }

    public List<ClienteTO> getAll() throws SQLException {
        return clienteDAO.getAll();
    }
}
