package tad;

import java.util.Scanner;

public class TorreDeHanoi {

    // Método recursivo para resolver o problema da Torre de Hanoi
    private static void resolverTorreDeHanoi(int n, char origem, char destino, char auxiliar) {
        if (n == 1) {
            System.out.println("Mover disco 1 de " + origem + " para " + destino);
            return;
        }

        // Passo 1: Mover n-1 discos da origem para o auxiliar
        resolverTorreDeHanoi(n - 1, origem, auxiliar, destino);

        // Passo 2: Mover o disco restante da origem para o destino
        System.out.println("Mover disco " + n + " de " + origem + " para " + destino);

        // Passo 3: Mover os n-1 discos do auxiliar para o destino
        resolverTorreDeHanoi(n - 1, auxiliar, destino, origem);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Jogo da Torre de Hanoi");
        System.out.print("Digite o número de discos: ");
        int numeroDeDiscos = scanner.nextInt();

        System.out.println("\nSequência de movimentos:");
        resolverTorreDeHanoi(numeroDeDiscos, 'A', 'C', 'B');

        scanner.close();
    }
}
