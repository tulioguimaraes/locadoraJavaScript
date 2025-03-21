package tad;

public class TadVetor {

    public TadVetor(int tamanho, int minimo, int maximo, int vaga, int repete) {
        this.tamanho = tamanho;
        this.minimo = minimo;
        this.maximo = maximo;
        this.vaga = vaga;
        this.repete = repete;

        this.dados = new int[this.tamanho];
        if (this.vaga != 0) {
            for (int i = 0; i < this.tamanho; i++) {
                this.dados[i] = this.vaga;
            }
        }
    }

    private final int tamanho;
    private final int minimo;
    private final int maximo;
    private final int vaga;
    private final int repete;  // 0 = nao repete; 1 = pode ter repeticao

    private int[] dados;

    /**
     * <br> ================================================================
     * <br> ARMAZENA UM NOVO VALOR EM DETERMINADA POSICAO
     * <br> ================================================================
     * <br> Retorna os seguintes codigos:
     * <br> 0 - armazenamento bem sucedido
     * <br> 1 - valor fora da faixa valida
     * <br> 2 - posicao nao existente no vetor
     * <br> 3 - posicao ocupada (para armazenar e' necessario estar vaga)
     * <br> 4 - caso nao permita repeticao e ja' existe o valor
     *
     * @param valor
     * @param posicao
     * @return
     */
    public int armazenar(int valor, int posicao) {

        if (!this.valorValido(valor)) {  // valor valido?
            return 1;
        }

        if (!this.posicaoValida(posicao)) {  // posicao valida?
            return 2;
        }

        if (this.dados[posicao] != this.vaga) {  // posicao esta' vaga?
            return 3;
        }

        if (!this.podeRepetir()) {  // se nao pode repetir -> ver se ja' existe no vetor
            int[] existe = this.localizar(valor, 0);
            if (existe[0] > 0) {
                return 4;
            }

        }

        this.dados[posicao] = valor;  // ok ate' aqui -> armazenar

        return 0;

    }

    /**
     * <br> ================================================================
     * <br> ALTERA O VALOR DE DETERMINADA POSICAO
     * <br> ================================================================
     * <br> Retorna os seguintes codigos:
     * <br> 0 - alteracao bem sucedida
     * <br> 1 - valor fora da faixa valida
     * <br> 2 - posicao nao existente no vetor
     * <br> 3 - posicao vaga (para alterar e' necessario estar alocada)
     * <br> 4 - caso nao permita repeticao e ja' existe o valor
     *
     * @param valor
     * @param posicao
     * @return
     */
    public int alterar(int valor, int posicao) {

        if (!this.valorValido(valor)) {  // valor valido?
            return 1;
        }

        if (!this.posicaoValida(posicao)) {  // posicao valida?
            return 2;
        }

        if (this.dados[posicao] == this.vaga) {  // posicao contem um valor valido?
            return 3;
        }

        if (!this.podeRepetir()) {  // se nao pode repetir -> ver se ja' existe no vetor
            int[] existe = this.localizar(valor, 0);
            if (existe[0] > 0) {
                return 4;
            }

        }

        this.dados[posicao] = valor;  // ok ate' aqui -> armazenar

        return 0;

    }

    /**
     *
     * <br> ================================================================
     * <br> EXCLUI O VALOR DE DETERMINADA POSICAO
     * <br> ================================================================
     * <br> Retorna os seguintes codigos:
     * <br> 0 - exclusao bem sucedida
     * <br> 2 - posicao nao existente no vetor
     * <br> 3 - posicao ja' esta' vaga (para excluir e' necessario estar
     * alocada)
     *
     * @param posicao
     * @return
     */
    public int excluir(int posicao) {

        if (!this.posicaoValida(posicao)) {  // posicao valida?
            return 2;
        }

        if (this.dados[posicao] == this.vaga) {  // posicao contem um valor valido?
            return 3;
        }

        this.dados[posicao] = this.vaga;

        return 0;

    }

    /**
     * <br> ====================================================================
     * <br> L&Ecirc; UMA POSI&Ccedil;&Atilde;O NO VETOR
     * <br> ====================================================================
     *
     * @param posicao
     * @return O valor contido na posicao; se a posicao estiver vaga, retorna o
     * valor que indica posicao vaga.
     */
    public int ler(int posicao) {

        if (!this.posicaoValida(posicao)) {  // posicao valida?
            return 2;
        }

        return this.dados[posicao];

    }

    /**
     * <br> Localiza um determinado valor no vetor.
     *
     * @param valor Valor a ser localizado
     * @param nPrimeiros Contabilizar apenas o "n" primeiros; 0 = contabiliza
     * todos
     * @return Um vetor com:
     * <li> posicao 0 = quantidade de elementos encontrados
     * <li> demais posicoes = indices desses elementos no vetor
     */
    public int[] localizar(int valor, int nPrimeiros) {

        int[] res = new int[this.tamanho];

        if (!this.valorValido(valor)) {
            return res;
        }

        if (this.repete == 0) {
            nPrimeiros = 1;
        }

        for (int i = 0; i < this.tamanho; i++) {
            if (this.dados[i] == valor) {
                res[0]++;
                res[res[0]] = i;
                if (res[0] == nPrimeiros) {
                    break;
                }
            }
        }

        return res;
    }

    /**
     * <br> ====================================================================
     * <br> Armazenar na primeira posicao vaga no sentido 0->N
     * <br> ====================================================================
     *
     * @param valor
     * @return Vide Utils.printRet(...)
     * @see
     */
    public int armazenar1Vaga(int valor) {

        if (!this.valorValido(valor)) {  // valor valido?
            return 1;
        }

        for (int i = 0; i < this.tamanho; i++) {
            if (this.dados[i] == this.vaga) {
                int res = this.armazenar(valor, i);
                return res;
            }
        }

        return -1;

    }

    /**
     * <br> ====================================================================
     * <br> Remover da ultima posicao ocupada no sentido 0->N
     * <br> ====================================================================
     *
     * @return Vide Utils.printRet(...)
     */
    public int removerUltima() {

        for (int i = this.tamanho - 1; i >= 0; i--) {
            if (this.dados[i] != this.vaga) {
                return this.excluir(i);
            }
        }

        return -1;

    }

    private boolean valorValido(int valor) {
        return valor >= this.minimo && valor <= this.maximo;
    }

    private boolean posicaoValida(int posicao) {
        return posicao >= 0 && posicao < this.tamanho;
    }

    private boolean podeRepetir() {
        return this.repete == 1;
    }
    
    public void limparVetor(){
        for (int i = 0; i < tamanho; i++){
            this.dados[i] = this.vaga;
        }
    }

}
