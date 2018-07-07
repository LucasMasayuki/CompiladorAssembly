package ep2ocd;
import java.util.LinkedList;
class Memoria {
    private LinkedList<Processo> filaDeProcesso = new LinkedList<Processo>();
    private int linha = 0;
    private String enderecoTemporario;

    private String enderecoEmHexadecimal(int linha) {
    	return Integer.toHexString(linha);
    }

    public void novoProcesso(Object dados) {
        Processo processo = new Processo(enderecoEmHexadecimal(this.linha), dados);
        filaDeProcesso.addLast(processo);
        this.linha++;
    }
    
    public void novoProcessoSetandoEndereco(Object dados, int endereco) {
        Processo processo = new Processo(enderecoEmHexadecimal(endereco), dados);
        filaDeProcesso.addLast(processo);
        this.linha++;
    }

    public Processo getProcesso(int endereco) {
    	for (Processo processo : filaDeProcesso) {
    		if (processo.endereco.equals(enderecoEmHexadecimal(endereco))) {
    			return processo;
    		};
    	}
    	return null;
    }

    public int getLinha() {
        return this.linha;
    }
    
    public void setEnderecoTemporario(String endereco) {
    	
    	this.enderecoTemporario = endereco;
    }
    
    public int getEnderecoTemporario() {
    	return Integer.parseInt(this.enderecoTemporario, 16);
    }

    public void verificaEstadoDaMemoria() {
    	for (Processo processo : filaDeProcesso) {
    		Palavra palavra = (Palavra) processo.dados;
    		System.out.println("linha: " + processo.endereco + " palavra: " + palavra
    			.getOpcode() + palavra.getOperandoUm() + palavra.getOperandoDois());
    	}
    }
}