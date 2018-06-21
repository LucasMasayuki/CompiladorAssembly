class InterpretadorAssembly {

    public static void compila(String entrada, Memoria memoria, Uc uc, Comandos comandosAssembly) {
        Palavra palavra;
        StringBuilder instrucao = new StringBuilder();
        StringBuilder comando = new StringBuilder(entrada);
        String opcode;
        String operandoUm;
        String operandoDois = "";
        int i = 0;

        instrucao.append("");

        while (!comandosAssembly.umComandoValido(instrucao.toString())) {
            instrucao.append(comando.charAt(i));
            i++;
        }

        opcode = comandosAssembly.getComandoBinario(instrucao.toString());

        comandosAssembly.imprimeTabelaOpcode();
        
        instrucao = new StringBuilder();
        
        i++;
        
        while ((comando.length() - 1) != i) {
        	if (comando.charAt(i) != ',') {
        		break;
        	}
        	instrucao.append(comando.charAt(i));
            i++;
        }

        if (uc.verificaSeUmRegistradorValido(instrucao.toString())) {
            operandoUm = uc.getComandoBinario(instrucao.toString());
        } else {
            operandoUm = instrucao.toString();
        }

        if (comando.length() == i - 1) {
            palavra = new Palavra(opcode, operandoUm);
        } else {
        	i++;
            while (comando.length() != i){
                instrucao.append(comando.charAt(i));
                i++;
            }
            palavra = new Palavra(opcode, operandoUm, operandoDois);
        }
        memoria.novoProcesso(palavra);
        memoria.verificaEstadoDaMemoria();
    }
}