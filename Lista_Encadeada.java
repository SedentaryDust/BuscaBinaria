import java.util.Objects;

public class Lista_Encadeada {
    private Node lista;

    public Node getLista() {
        return lista;
    }

    public void setLista(Node lista) {
        this.lista = lista;
    }

    public Lista_Encadeada(){
        lista = null;
    }

    public void RemoveItem(Integer dado){
        Node start = this.getLista();
        while(true){
            if(Objects.equals(start.getInformcao(), dado)){
                if(start.getAnterior() == null){
                    System.out.println("Nao tem anterioro ->"+ start.getProximo().toString());
                    this.setLista(start.getProximo());
                    start.getProximo().setAnterior(null);
                    break;
                }
                start.SelfRemove();
                break;
            }else{
                Node prox = start.getProximo();
                if(prox==null){
                    System.out.println("Dado nao encontrado");
                    break;
                }
                start = prox;
            }
        }
    }

    public void RemoveItem(){
        this.setLista(this.getLista().getProximo());

    }
}
