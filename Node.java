import java.util.Scanner;

public class Node {
    private Integer informcao;
    private Node proximo;

    private Node anterior;
    public Node(){
        informcao = null;
        proximo = null;
        anterior = null;
    }

    public Integer getInformcao() {
        return informcao;
    }

    public void setInformcao(Integer informcao) {
        this.informcao = informcao;
    }

    public Node getProximo() {
        return proximo;
    }

    public void setProximo(Node proximo) {
        this.proximo = proximo;
    }

    public Node getAnterior() {
        return anterior;
    }

    public void setAnterior(Node anterior) {
        this.anterior = anterior;
    }

    public void Insert(Integer dado){

        Node now = this;
        now.setInformcao(dado);
        Node proximo_node = new Node();
        now.setProximo(proximo_node);
        proximo_node.anterior = now;

    }



    public void SelfRemove(){
        if(this.getProximo() != null && this.getAnterior() == null){

        }

        Node ant = this.getAnterior();
        if(this.getProximo() == null){
            System.out.println("Sem proximo");
            ant.setProximo(null);
        }else{
            ant.setProximo(this.getProximo());
        }

    }

    public void Imprime(){
        Node now = this;
        while(true){
            if(now.getProximo() == null){

                System.out.println(now.getInformcao());
                break;
            }
            System.out.printf(now.getInformcao() + "->");
            now = now.getProximo();
        }

    }
}
