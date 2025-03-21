package tad;

import java.util.Random;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @classe - TadVetor
 * @data --- 23/08/2022 - 16:02:58
 * @autor -- prof. Marcio Porto Feitosa
 */
public class TadVetorJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        int tamanho, minimo, maximo, vaga, repete;

        while (true) {

            System.out.println("================================================");
            System.out.println("      CONFIGURACOES INICIAIS DO VETOR");
            System.out.println("================================================");

            //* TAMANHO DO VETOR
            System.out.print("\nTamanho do vetor: ");
            tamanho = scn.nextInt();
            if (tamanho <= 0) {
                System.out.println("ERRO: Vetor deve ter tamanho maior que zero!!");
                continue;
            }

            //* VALORES MINIMO E MAXIMO
            System.out.print("\nValor minimo no vetor: ");
            minimo = scn.nextInt();
            System.out.print("\nValor maximo no vetor: ");
            maximo = scn.nextInt();
            if (minimo > maximo) {
                System.out.println("ERRO: Valor maximo deve ser maior que o valor minimo");
                continue;
            }

            //*  VALOR INDICATIVO DE POSICAO VAGA
            System.out.print("\nValor indicativo de posicao vaga: ");
            vaga = scn.nextInt();
            if (vaga >= minimo && vaga <= maximo) {
                System.out.println("ERRO: Valor indicativo de posicao vaga nao pode estar no intervalo de valores valido!!");
                continue;
            }

            //*  POSSIBILIDADE OU NAO DE REPETICAO
            System.out.print("\nPode haver repeticao de valores (1) ou nao (2)? ");
            repete = scn.nextInt();
            if (repete < 1 || repete > 2) {
                System.out.println("ERRO: Valor invalido. Informe 1 ou 2.");
                continue;
            }

            break;

        }

        TadVetor vetor = new TadVetor(tamanho, minimo, maximo, vaga, repete);

        while (true) {

            System.out.println("===================================================");
            System.out.println("            Estudo do TAD Vetor");
            System.out.println("===================================================");
            System.out.println("0 - Encerrar");
            System.out.println("1 - Atribuir um valor a determinada posição.");
            System.out.println("2 - Alterar o valor de determinada posição.");
            System.out.println("3 - Remover o valor de determinada posição (atribui o valor indicativo de posição vaga).");
            System.out.println("4 - Ler o conteúdo de uma posicao.");
            System.out.println("5 - Localizar um valor e retornar sua posição (se permitir repetição tem que retornar todos).");
            System.out.println("6 - Inserir na primeira posição vaga (busca no sentido 0 → N).");
            System.out.println("7 - Remover da última posição ocupada (busca no sentido 0 → N).");
            System.out.println("8 - Imprimir o conteúdo do vetor.");

            System.out.print("\nSua opcao: ");
            int opc = scn.nextInt();

            if (opc == 0) {
                break;
            } else if (opc == 1) {
                System.out.println("===   A T R I B U I C A O   ===");
                System.out.print("Valor: ");
                int valor = scn.nextInt();
                System.out.print("Posicao: ");
                int posicao = scn.nextInt();
                Utils.printRet(vetor.armazenar(valor, posicao));
            } else if (opc == 2) {
                System.out.println("===   A L T E R A C A O   ===");
                System.out.print("Valor: ");
                int valor = scn.nextInt();
                System.out.print("Posicao: ");
                int posicao = scn.nextInt();
                Utils.printRet(vetor.alterar(valor, posicao));
            } else if (opc == 3) {
                System.out.println("===   R E M O C A O   ===");
                System.out.print("Posicao a remover: ");
                int posicao = scn.nextInt();
                Utils.printRet(vetor.excluir(posicao));
            } else if (opc == 4) {
                System.out.println("===   C O N T E U D O   ===");
                System.out.print("Posicao a ler: ");
                int posicao = scn.nextInt();
                System.out.println("Posicao " + posicao + "contem " + vetor.ler(posicao));
            } else if (opc == 5) {
                System.out.println("===   L O C A L I Z A N D O   U M   V A L O R   ===");
                System.out.print("Valor a localizar: ");
                int valor = scn.nextInt();
                int[] valores = vetor.localizar(valor, 0);
                if (valores[0] == 0) {
                    System.out.println("\n\nVALOR NAO LOCALIZADO.\n\n");
                } else {
                    System.out.println("\n\n===  VALORES ENCONTRADOS NOS INDICES ABAIXO   ===");
                    for (int i = 1; i < valores.length; i++) {
                        if (valores[i] == 0) {
                            break;
                        }
                        System.out.print(valores[i] + " ");
                    }
                    System.out.println("\n" + valores[0] + " valor(es) encontrado(s).");
                }
            } else if (opc == 6) {
                System.out.println("===   INSERINDO NA PRIMEIRA POSICAO VAGA   ===");
                System.out.println("Valor a inserir: ");
                int valor = scn.nextInt();
                Utils.printRet(vetor.armazenar1Vaga(valor));
            } else if (opc == 7) {
                System.out.println("===   REMOVENDO DA ULTIMA POSICAO OCUPADA   ===");
                Utils.printRet(vetor.removerUltima());
            } else if (opc == 8) {
                for (int i = 0; i < tamanho; i++) {
                    System.out.println(i + " = " + vetor.ler(i));
                }
            } else if (opc == 99) {
                vetor.limparVetor();
                Random seed = new Random();
                for (int i = 0; i < tamanho; i++) {
                    vetor.armazenar(minimo + seed.nextInt(maximo + 1 - minimo), i);
                }
            }

        }

        System.out.println("\n\n---   F I M   ---");
        System.out.println("Obrigado e ate' a proxima.\n\n");

    }

}

