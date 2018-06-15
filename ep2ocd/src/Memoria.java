import java.util.Iterator;
import java.util.LinkedList;
class Memoria {
    private LinkedList filaDeProcesso = new LinkedList();

    public void novoProcesso(String palavra){
        filaDeProcesso.addLast(palavra);
    }

    public String pegaProcesso(int endereco) {
        return (String)filaDeProcesso.get(endereco);
    }
}