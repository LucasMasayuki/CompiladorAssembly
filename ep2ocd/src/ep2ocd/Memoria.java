package ep2ocd;
import java.util.LinkedList;
class Memoria {
    private LinkedList<Processo> filaDeProcesso = new LinkedList<Processo>(); 
    private int linha = 0;

    private String enderecoEmHexadecimal() {
    	return Integer.toHexString(linha);
    }

    public void novoProcesso(Palavra palavra) {
        Processo processo = new Processo(enderecoEmHexadecimal(), palavra);
        filaDeProcesso.addLast(processo);
        this.linha++;
    }

    public Processo getProcesso(int endereco) {
    	this.linha = 0;
    	for (Processo processo : filaDeProcesso) {
    		if (processo.endereco.equals(enderecoEmHexadecimal())) {
    			return processo;
    		};
    		linha++;
    	}
    	return null;
    }

    public int getLinha() {
        return this.linha;
    }

    public void verificaEstadoDaMemoria() {
    	for (Processo processo : filaDeProcesso) {
    		System.out.println("linha: " + processo.endereco + " palavra: " + processo.palavra
    			.getOpcode() + processo.palavra.getOperandoUm() + processo.palavra.getOperandoDois());
    	}
    }
}