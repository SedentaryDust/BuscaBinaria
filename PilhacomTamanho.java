public class PilhacomTamanho extends Pilha{
    public int getSize(Node actual){
       if(actual.getProximo() == null){
           return 0;
       }
       return 1 + getSize(actual.getProximo());
    }
    public boolean isFull(int size){
       return this.getSize(this.getPilha().getLista()) == size;
    }
    @Override
    public void Show() {
        if(this.getPilha().getLista().getInformcao() == null){
            System.out.println("_");
        }else {
            super.Show();
        }
    }

    public boolean IsEmpty(){
        return this.getPilha().getLista().getInformcao() == null;
    }
    public int peek(){
        return this.getPilha().getLista().getInformcao();
    }
}
