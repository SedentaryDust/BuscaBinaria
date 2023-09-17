public class Pilha {
    private Lista_Encadeada pilha = new Lista_Encadeada();

    public Pilha(){
        pilha.setLista(new Node());
    }

    public Lista_Encadeada getPilha() {
        return pilha;
    }

    public void Insere(int dado){
        Node init = this.pilha.getLista();
        Node newInit = new Node();
        newInit.setInformcao(dado);
        init.setAnterior(newInit);
        newInit.setProximo(init);
        this.pilha.setLista(newInit);
        System.out.println("Item "+dado+ " Adicionado a pilha");

    }
    public int Pop(){
        int valorAtual = this.getPilha().getLista().getInformcao();
        pilha.setLista(this.pilha.getLista().getProximo());
        return valorAtual;
    }

    public void Remove(){
        System.out.println("O dado " + this.pilha.getLista().getInformcao()+ " removido da Pilha");
        this.pilha.RemoveItem();
        System.out.println();
    }

    public void Show() {
        this.pilha.getLista().Imprime();
    }

}
