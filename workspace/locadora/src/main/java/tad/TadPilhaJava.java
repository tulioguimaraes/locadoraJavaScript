package tad;

import java.util.Scanner;

/**
 * Classe representativa da estrutura de dados PILHA com suporte a elementos repetidos ou não repetidos.
 */
public class TadPilhaJava {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        System.out.println("--------------------------------------------");
        System.out.println("PROGRAMA PARA ESTUDO DO TAD PILHA - VERSÃO 1");
        System.out.println("--------------------------------------------");
        System.out.println("");
        System.out.print("Tamanho maximo da pilha: ");
        int qtd = scn.nextInt();

        System.out.print("Permitir repetição de elementos? (true/false): ");
        boolean permitirRepeticao = scn.nextBoolean();

        TadPilha pilha = new TadPilha(qtd, permitirRepeticao);

        while (true) {
            System.out.println("\n\n--------------------------------------------");
            System.out.println("PROGRAMA PARA ESTUDO DO TAD PILHA - VERSÃO 1");
            System.out.println("      Conteudo: numeros inteiros");
            System.out.println("      Capacidade:" + qtd + " elementos");
            System.out.println("--------------------------------------------");
            System.out.println("");
            System.out.println("0 - Encerrar");
            System.out.println("1 - Inserir um elemento");
            System.out.println("2 - Extrair um elemento");
            System.out.println("3 - Imprimir a pilha");
            System.out.println("4 - Localizar um elemento");
            System.out.println("");

            System.out.print("Opcao: ");
            int opc = scn.nextInt();

            if (opc == 0) {
                break;
            } else if (opc == 1) {
                System.out.print("Valor do elemento a inserir: ");
                int valor = scn.nextInt();
                if (pilha.push(valor)) {
                    System.out.println("Insercao bem sucedida.");
                } else {
                    System.out.println("Insercao falhou!!");
                }
            } else if (opc == 2) {
                if (pilha.pop()) {
                    System.out.println("Extracao bem sucedida. Valor do elemento = " + pilha.getRetorno());
                } else {
                    System.out.println("Extracao falhou.");
                }
            } else if (opc == 3) {
                System.out.println(pilha.print());
            } else if (opc == 4) {
                System.out.print("Valor a localizar: ");
                int valor = scn.nextInt();
                int posicao = pilha.localizar(valor);
                if (posicao != -1) {
                    System.out.println("Elemento encontrado na posição: " + posicao);
                } else {
                    System.out.println("Elemento não encontrado.");
                }
            }
        }
    }
}


