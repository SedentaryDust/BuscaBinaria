import java.util.Random;
import java.util.Scanner;

public class TDE02 {

    public static void main(String[] args) {
        PilhacomTamanho pilha1;
        PilhacomTamanho pilha2 = new PilhacomTamanho();
        PilhacomTamanho pilha3 = new PilhacomTamanho();
        Scanner in = new Scanner(System.in);
        System.out.println("Qual o tamanho da Pilha:");
        String input = in.nextLine();
        pilha1 = ShuffleMainStack(Integer.parseInt(input));
        pilha1.Show();
        PilhacomTamanho[] regra = {pilha1,pilha2,pilha3};
        boolean result = game(regra);

        if(result){
            System.out.println("Ganhou!");
        }


    }
    public static boolean isOver(PilhacomTamanho p1 , PilhacomTamanho p2 , int size){
        return p1.isFull(size) || p2.isFull(size);
    }
    public static boolean game(PilhacomTamanho[] regra){
        Scanner in = new Scanner(System.in);
        int size = regra[0].getSize(regra[0].getPilha().getLista());
        boolean breakOut = regra[1].isFull(size) || regra[2].isFull(size);
        boolean out = false;

        while(!breakOut){
            System.out.println("(1) Movimentar\n(2)Sair\n(3)SolucaoAutomatica");
            String way = in.nextLine();
            switch (Integer.parseInt(way)) {
                case 1 -> {
                    System.out.println("Desempilhar qual: ");
                    String p1 = in.nextLine();
                    int Desempilhar = Integer.parseInt(p1);
                    System.out.println("Empilhar qual: ");
                    String p2 = in.nextLine();
                    int Empilhar = Integer.parseInt(p2);
                    jogada(regra, Desempilhar, Empilhar);
                }
                case 2 -> out = true;
                case 3 -> Automatic(regra[0],regra[1],regra[2], size);
                default -> System.out.println("Nenhuma Opcao selecionada");
            }
            if(out){
                breakOut = true;
            }else {
                breakOut = isOver(regra[1], regra[2], size);

            }
            System.out.print("Pilha 0: ");
            regra[0].Show();
            System.out.print("Pilha 1: ");
            regra[1].Show();
            System.out.print("Pilha 2: ");
            regra[2].Show();

        }
        return breakOut;
    }

    public static void jogada(PilhacomTamanho[] regra , int Desempilhar , int Empilhar){

        if(regra[Desempilhar-1].IsEmpty()){
            System.out.println("Impossivel Desempilhar");
        }else if(regra[Empilhar-1].IsEmpty()){
            regra[Empilhar-1].Insere(regra[Desempilhar-1].Pop());
        }
        else if (regra[Empilhar-1].peek() < regra[Desempilhar-1].peek()) {
            System.out.println("Impossivel Impilhar");
        }else {
            regra[Empilhar - 1].Insere(regra[Desempilhar - 1].Pop());
        }
    }
    public static PilhacomTamanho ShuffleMainStack(int size){
        Random rnd = new Random();
        PilhacomTamanho pilha1 = new PilhacomTamanho();
        for (int i = 0; i < size ; i++){
            pilha1.Insere(rnd.nextInt(1,100));
        }
        return pilha1;
    }

    public static void move(PilhacomTamanho origem , PilhacomTamanho destino){
        if(!origem.IsEmpty()){
            if(destino.IsEmpty() || origem.peek() < destino.peek()){
                destino.Insere(origem.Pop());
            }
        }
    }

    public static void Automatic(PilhacomTamanho origem ,PilhacomTamanho aux, PilhacomTamanho destino , int size){
        if(size == 1){
            move(origem , destino);
        } else {
            Automatic(origem,destino,aux,size-1);
            move(origem , destino);
            Automatic(aux,origem,destino,size-1);
        }
        }

}
