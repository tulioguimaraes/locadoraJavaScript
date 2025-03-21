package tad;

public class Utils {

    private static final String cab = "\n\nOperacao NAO executada.";
    private static final String rab = "\n\n";

    public static void printRet(int codigo) {

        if (codigo == -1) {
            System.out.println(cab + " Elemento inexistente." + rab);
        } else if (codigo == 0) {
            System.out.println("Operacao BEM sucedida" + rab);
        } else if (codigo == 1) {
            System.out.println(cab + " Valor fora da faixa prevista." + rab);
        } else if (codigo == 2) {
            System.out.println(cab + " Posicao fora das fronteiras do vetor." + rab);
        } else if (codigo == 3) {
            System.out.println(cab + " A posicao ja' contem o previsto pela operacao"
                    + "('ocupada' em caso de inclusao ou 'vaga' em caso de remocao)." + rab);
        } else if (codigo == 4) {
            System.out.println(cab + " Valor ja' existente (repeticao nao permitida)." + rab);
        }

    }

}
