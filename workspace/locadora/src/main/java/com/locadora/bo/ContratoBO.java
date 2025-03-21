package com.locadora.bo;

import com.locadora.dao.ContratoDAO;
import com.locadora.dao.LocacaoDAO;
import com.locadora.to.ContratoTO;
import com.locadora.to.LocacaoTO;
import com.locadora.to.VeiculoTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContratoBO {
    private final ContratoDAO contratoDAO = new ContratoDAO();
    private final LocacaoDAO locacaoDAO = new LocacaoDAO();

    public void saveContrato(ContratoTO contrato, boolean novo) throws SQLException {
        // Garante que a lista de veículos esteja inicializada
        if (contrato.getListaVeiculos() == null) {
            contrato.setListaVeiculos(new ArrayList<>());
        }

        if (novo) {
            contratoDAO.save(contrato);
            for (VeiculoTO veiculo : contrato.getListaVeiculos()) {
                LocacaoTO locacao = new LocacaoTO(contrato, veiculo);
                locacaoDAO.save(locacao);
            }
        } else {
            locacaoDAO.deleteByContrato(contrato.getConCodigo());
            contratoDAO.update(contrato);
            for (VeiculoTO veiculo : contrato.getListaVeiculos()) {
                LocacaoTO locacao = new LocacaoTO(contrato, veiculo);
                locacaoDAO.save(locacao);
            }
        }
    }

    public List<ContratoTO> listarContratos() throws SQLException {
        return contratoDAO.getAll();
    }

    public ContratoTO getContratoById(int conCodigo) throws SQLException {
        ContratoTO contrato = contratoDAO.getById(conCodigo);
        contrato.setListaVeiculos(contratoDAO.getListaVeiculos(conCodigo));
        return contrato;
    }

    public void deleteContrato(int conCodigo) throws SQLException {
        locacaoDAO.deleteByContrato(conCodigo);
        contratoDAO.delete(conCodigo);
    }
}
