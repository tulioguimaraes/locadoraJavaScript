package tad;

/**
 * Classe representativa da estrutura de dados PILHA com suporte a elementos repetidos ou não repetidos.
 */
public class TadPilha {
    private final int tamanho;
    private int topo;
    private int[] pilha;
    private boolean permitirRepeticao;

    private int retorno;

    public TadPilha(int tam, boolean permitirRepeticao) {
        this.tamanho = tam;
        this.topo = -1;
        this.pilha = new int[tam];
        this.permitirRepeticao = permitirRepeticao;
    }

    public int getRetorno() {
        return retorno;
    }

    public boolean full() {
        return this.topo == this.tamanho - 1;
    }

    public int qtd() {
        return this.topo + 1;
    }

    public boolean empty() {
        return this.qtd() == 0;
    }

    public boolean push(int valor) {
        if (!this.full()) {
            if (!permitirRepeticao && localizar(valor) != -1) {
                return false; // Valor já existe e repetição não permitida
            }
            this.topo += 1;
            this.pilha[this.topo] = valor;
            return true;
        } else {
            return false;
        }
    }

    public boolean pop() {
        if (this.empty()) {
            return false;
        }
        this.retorno = this.pilha[this.topo];
        this.topo--;
        return true;
    }

    public int getTopo() {
        return this.topo;
    }

    public int localizar(int valor) {
        for (int i = 0; i <= this.topo; i++) {
            if (this.pilha[i] == valor) {
                return i;
            }
        }
        return -1; // Não encontrado
    }

    public String print() {
        String ret = "";
        if (this.empty()) {
            return "A pilha esta' vazia";
        }
        ret += "A pilha tem " + this.qtd() + " elementos.\n";
        ret += "Topo está na posição: " + this.topo + "\n";
        for (int i = 0; i <= this.topo; i++) {
            ret += this.pilha[i] + " ";
        }
        return ret;
    }
}

