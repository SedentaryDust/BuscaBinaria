import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class JogoTorreDeHanoi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao jogo Torre de Hanoi!");

        int tamanhoPilhas = solicitarTamanhoPilhas(scanner);
        boolean ordemCrescente = solicitarOrdemCrescente(scanner);

        Stack<Integer> pilha1 = new Stack<>();
        Stack<Integer> pilha2 = new Stack<>();
        Stack<Integer> pilha3 = new Stack<>();

        preencherPilhaInicial(pilha1, tamanhoPilhas);

        int jogadas = 0;

        while (true) {
            System.out.println("\nEstado atual das pilhas:");
            exibirPilhas(pilha1, pilha2, pilha3);

            int opcao = solicitarOpcao(scanner);

            if (opcao == 0) {
                System.out.println("Jogo encerrado. Obrigado por jogar!");
                break;
            } else if (opcao == 1) {
                jogadas++;
                if (movimentarPilhas(scanner, pilha1, pilha2, pilha3, ordemCrescente)) {
                    if (pilha3.size() == tamanhoPilhas) {
                        boolean ordenada = verificarOrdenacao(pilha3, ordemCrescente);
                        if (ordenada) {
                            System.out.println("Ordenação concluída em " + jogadas + " jogadas!");
                            break;
                        }
                    }
                }
            } else if (opcao == 2) {
                System.out.println("Resolvendo automaticamente...");
                resolverAutomaticamente(pilha1, pilha2, pilha3, tamanhoPilhas, ordemCrescente);
                exibirPilhas(pilha1, pilha2, pilha3);
                System.out.println("Ordenação concluída automaticamente.");

                break;
            } else {
                System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        }

        scanner.close();
    }

    public static int solicitarTamanhoPilhas(Scanner scanner) {
        System.out.print("Digite o tamanho das pilhas: ");
        return scanner.nextInt();
    }

    public static boolean solicitarOrdemCrescente(Scanner scanner) {
        System.out.print("Deseja organizar em ordem crescente (1) ou decrescente (0)? ");
        int escolha = scanner.nextInt();
        return escolha == 1;
    }

    public static void preencherPilhaInicial(Stack<Integer> pilha1, int tamanhoPilhas) {
        List<Integer> numeros = new ArrayList<>();
        for (int i = 1; i <= tamanhoPilhas; i++) {
            numeros.add(i);
        }
        Collections.shuffle(numeros);
        pilha1.addAll(numeros);
    }

    public static void exibirPilhas(Stack<Integer> pilha1, Stack<Integer> pilha2, Stack<Integer> pilha3) {
        System.out.println("Pilha 1: " + pilha1);
        System.out.println("Pilha 2: " + pilha2);
        System.out.println("Pilha 3: " + pilha3);
    }

    public static int solicitarOpcao(Scanner scanner) {
        System.out.println("\nOpções:");
        System.out.println("0 - Sair");
        System.out.println("1 - Movimentar");
        System.out.println("2 - Solução automática");
        System.out.print("Escolha uma opção: ");
        return scanner.nextInt();
    }

    public static boolean movimentarPilhas(Scanner scanner, Stack<Integer> pilhaOrigem, Stack<Integer> pilhaDestino, Stack<Integer> pilhaAuxiliar, boolean ordemCrescente) {
        System.out.print("Digite a pilha de origem (1, 2, ou 3): ");
        int origem = scanner.nextInt();

        System.out.print("Digite a pilha de destino (1, 2, ou 3): ");
        int destino = scanner.nextInt();

        Stack<Integer> origemPilha = selecionarPilha(origem, pilhaOrigem, pilhaDestino, pilhaAuxiliar);
        Stack<Integer> destinoPilha = selecionarPilha(destino, pilhaOrigem, pilhaDestino, pilhaAuxiliar);

        if (origemPilha.isEmpty()) {
            System.out.println("A pilha de origem está vazia. Movimento inválido.");
            return false;
        } else if (!destinoPilha.isEmpty() && (ordemCrescente && origemPilha.peek() > destinoPilha.peek() || !ordemCrescente && origemPilha.peek() < destinoPilha.peek())) {
            System.out.println("O disco a ser movido não pode ser colocado sobre um disco menor. Movimento inválido.");
            return false;
        } else {
            int disco = origemPilha.pop();
            destinoPilha.push(disco);
            return true;
        }
    }

    public static Stack<Integer> selecionarPilha(int pilhaNumero, Stack<Integer> pilha1, Stack<Integer> pilha2, Stack<Integer> pilha3) {
        switch (pilhaNumero) {
            case 1:
                return pilha1;
            case 2:
                return pilha2;
            case 3:
                return pilha3;
            default:
                throw new IllegalArgumentException("Número de pilha inválido.");
        }
    }

    public static boolean verificarOrdenacao(Stack<Integer> pilha, boolean ordemCrescente) {
        List<Integer> lista = new ArrayList<>(pilha);
        if (ordemCrescente) {
            for (int i = 0; i < lista.size() - 1; i++) {
                if (lista.get(i) > lista.get(i + 1)) {
                    return false;
                }
            }
        } else {
            for (int i = 0; i < lista.size() - 1; i++) {
                if (lista.get(i) < lista.get(i + 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void resolverAutomaticamente(Stack<Integer> pilhaOrigem, Stack<Integer> pilhaAuxiliar, Stack<Integer> pilhaDestino, int tamanhoPilhas, boolean ordemCrescente) {
        resolverHanoi(tamanhoPilhas, pilhaOrigem, pilhaAuxiliar, pilhaDestino, ordemCrescente);
    }

    public static void resolverHanoi(int n, Stack<Integer> origem, Stack<Integer> auxiliar, Stack<Integer> destino, boolean ordemCrescente) {
        if (n == 1) {
            moverDisco(origem, destino, ordemCrescente);
        } else {
            resolverHanoi(n - 1, origem, destino, auxiliar, ordemCrescente);
            moverDisco(origem, destino, ordemCrescente);
            resolverHanoi(n - 1, auxiliar, origem, destino, ordemCrescente);
        }
    }

    public static void moverDisco(Stack<Integer> origem, Stack<Integer> destino, boolean ordemCrescente) {
        if (!origem.isEmpty()) {
            int disco = origem.pop();
            if (destino.isEmpty() || (ordemCrescente && disco < destino.peek()) || (!ordemCrescente && disco > destino.peek())) {
                destino.push(disco);
            }
        }
    }
}
