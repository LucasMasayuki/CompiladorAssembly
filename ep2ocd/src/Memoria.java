import java.util.Iterator;
import java.util.LinkedList;
class Memoria {
    private LinkedList filaDeProcesso = new LinkedList();

    public void novoProcesso(String posicaoNaMemoria){
        filaDeProcesso.addLast(posicaoNaMemoria);
    }

    public String pegaProcesso() {
        return filaDeProcesso.removeLast();
    }
}