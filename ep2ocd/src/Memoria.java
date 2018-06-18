import java.util.Iterator;
import java.util.LinkedList;
class Memoria {
    private LinkedList<Processo> filaDeProcesso = new LinkedList<Processo>(); 
    private int linha = 0;

    private String enderecoEmHexadecimal() {
		return Integer.toString(linha, 16);
    }

    public void novoProcesso(Palavra palavra){
        Processo processo = new Processo(enderecoEmHexadecimal(), palavra);
        filaDeProcesso.addLast(processo);
        this.linha++;
    }

    public Processo pegaProcesso(int endereco) {
        return filaDeProcesso.get(endereco);
    }

    public void verificaEstadoDaMemoria() {
    	for (Processo processo : filaDeProcesso) {
    		System.out.println("linha: " + processo.endereco + "palavra: " + processo.palavra
    			.getOpcode() + processo.palavra.getOperandoUm() + processo.palavra.getOperandoDois());
    	}
    }
}