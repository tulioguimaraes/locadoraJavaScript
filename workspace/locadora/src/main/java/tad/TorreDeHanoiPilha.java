package tad;

import java.util.Scanner;
import java.util.Stack;

public class TorreDeHanoiPilha {

    // Método iterativo para resolver o problema da Torre de Hanoi
    private static void resolverTorreDeHanoiIterativo(int n) {
        char origem = 'A';
        char destino = 'C';
        char auxiliar = 'B';

        Stack<int[]> pilha = new Stack<>();
        pilha.push(new int[] {n, origem, destino, auxiliar});

        while (!pilha.isEmpty()) {
            int[] topo = pilha.pop();
            int discos = topo[0];
            char origemAtual = (char) topo[1];
            char destinoAtual = (char) topo[2];
            char auxiliarAtual = (char) topo[3];

            if (discos == 1) {
                System.out.println("Mover disco 1 de " + origemAtual + " para " + destinoAtual);
            } else {
                // Empilhar os passos na ordem inversa para simular a recursão
                pilha.push(new int[] {discos - 1, auxiliarAtual, destinoAtual, origemAtual});
                pilha.push(new int[] {1, origemAtual, destinoAtual, auxiliarAtual});
                pilha.push(new int[] {discos - 1, origemAtual, auxiliarAtual, destinoAtual});
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Jogo da Torre de Hanoi");
        System.out.print("Digite o número de discos: ");
        int numeroDeDiscos = scanner.nextInt();

        System.out.println("\nSequência de movimentos:");
        resolverTorreDeHanoiIterativo(numeroDeDiscos);

        scanner.close();
    }
}
