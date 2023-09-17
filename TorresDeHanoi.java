import java.util.Stack;

public class TorresDeHanoi {
    public static void main(String[] args) {
        int numDiscos = 3; // Número de discos
        Stack<Integer> torreA = new Stack<>();
        Stack<Integer> torreB = new Stack<>();
        Stack<Integer> torreC = new Stack<>();

        // Inicializa a torre A com discos de tamanhos diferentes
        for (int i = numDiscos; i >= 1; i--) {
            torreA.push(i);
        }

        System.out.println("Estado inicial das Torres:");
        exibirTorres(torreA, torreB, torreC);

        // Chama a função de resolução das Torres de Hanói
        resolverTorresDeHanoi(numDiscos, torreA, torreB, torreC);

        System.out.println("Estado final das Torres:");
        exibirTorres(torreA, torreB, torreC);
    }

    public static void resolverTorresDeHanoi(int n, Stack<Integer> origem, Stack<Integer> auxiliar, Stack<Integer> destino) {
        if (n == 1) {
            moverDisco(origem, destino);
        } else {
            resolverTorresDeHanoi(n - 1, origem, destino, auxiliar);
            moverDisco(origem, destino);
            resolverTorresDeHanoi(n - 1, auxiliar, origem, destino);
        }
    }

    public static void moverDisco(Stack<Integer> origem, Stack<Integer> destino) {
        if (origem.isEmpty()) {
            destino.push(destino.isEmpty() ? 1 : destino.peek() + 1);
        } else if (destino.isEmpty() || origem.peek() < destino.peek()) {
            destino.push(origem.pop());
        } else {
            origem.push(destino.pop());
        }
    }

    public static void exibirTorres(Stack<Integer> torreA, Stack<Integer> torreB, Stack<Integer> torreC) {
        System.out.println("Torre A: " + torreA);
        System.out.println("Torre B: " + torreB);
        System.out.println("Torre C: " + torreC);
        System.out.println();
    }
}
