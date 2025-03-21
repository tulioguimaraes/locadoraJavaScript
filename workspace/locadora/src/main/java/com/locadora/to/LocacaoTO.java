package com.locadora.to;

public class LocacaoTO {
    private int locCodigo;
    private ContratoTO contrato;
    private VeiculoTO veiculo;

    // Construtores
    public LocacaoTO() {}

    public LocacaoTO(ContratoTO contrato, VeiculoTO veiculo) {
        this.contrato = contrato;
        this.veiculo = veiculo;
    }

    // Getters e Setters
    public int getLocCodigo() {
        return locCodigo;
    }

    public void setLocCodigo(int locCodigo) {
        this.locCodigo = locCodigo;
    }

    public ContratoTO getContrato() {
        return contrato;
    }

    public void setContrato(ContratoTO contrato) {
        this.contrato = contrato;
    }

    public VeiculoTO getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(VeiculoTO veiculo) {
        this.veiculo = veiculo;
    }

    @Override
    public String toString() {
        return "LocacaoTO{" +
                "locCodigo=" + locCodigo +
                ", contrato=" + contrato +
                ", veiculo=" + veiculo +
                '}';
    }
}
