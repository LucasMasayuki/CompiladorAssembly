import java.util.Iterator;
import java.util.LinkedList;
class Memoria {
    private LinkedList<Processo> filaDeProcesso = new LinkedList<Processo>(); 
    private int linha = 0;

    private String enderecoEmHexadecimal() {
		return Integer.toString(linha, 16);
    }

    public void novoProcesso(Palavra palavra){
    	Processo processo = new Processo(enderecoEmHexadecimal(linha), palavra);
        filaDeProcesso.addLast(processo);
        this.linha++;
    }

    public Processo pegaProcesso(int endereco) {
        return filaDeProcesso.get(endereco);
    }

    public void verificaEstadoDaMemoria() {
    	for (Processo processo : filaDeProcesso) {
    		String opcode = filaDeProcesso.palavra.getOp
    		System.out.println("linha: " + filaDeProcesso.linha + "palavra: " + filaDeProcesso.palavra
    			.getOpcode() + filaDeProcesso.palavra.getOperandoUm() + filaDeProcesso.palavra.getOperandoDois());
    	}
    }
}