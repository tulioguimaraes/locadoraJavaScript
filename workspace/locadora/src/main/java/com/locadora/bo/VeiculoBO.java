package com.locadora.bo;

import com.locadora.dao.VeiculoDAO;
import com.locadora.to.VeiculoTO;

import java.sql.SQLException;
import java.util.List;

public class VeiculoBO {
    private final VeiculoDAO veiculoDAO = new VeiculoDAO();

    public List<VeiculoTO> getAll() throws SQLException {
        return veiculoDAO.getAll();
    }

    public VeiculoTO getById(int veiCodigo) throws SQLException {
        return veiculoDAO.getById(veiCodigo);
    }

    public boolean save(VeiculoTO veiculo) throws SQLException {
        return veiculoDAO.save(veiculo);
    }
}
