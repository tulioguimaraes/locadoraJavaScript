package com.locadora.to;

import java.util.Date;
import java.util.List;

public class ContratoTO {
    private int conCodigo;
    private ClienteTO cliente;
    private Date conDataInicio;
    private Date conDataFim;
    private double conValorContrato;
    private List<VeiculoTO> listaVeiculos;

    // Getters e Setters
    public int getConCodigo() {
        return conCodigo;
    }

    public void setConCodigo(int conCodigo) {
        this.conCodigo = conCodigo;
    }

    public ClienteTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteTO cliente) {
        this.cliente = cliente;
    }

    public Date getConDataInicio() {
        return conDataInicio;
    }

    public void setConDataInicio(Date conDataInicio) {
        this.conDataInicio = conDataInicio;
    }

    public Date getConDataFim() {
        return conDataFim;
    }

    public void setConDataFim(Date conDataFim) {
        this.conDataFim = conDataFim;
    }

    public double getConValorContrato() {
        return conValorContrato;
    }

    public void setConValorContrato(double conValorContrato) {
        this.conValorContrato = conValorContrato;
    }

    public List<VeiculoTO> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(List<VeiculoTO> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }
}
