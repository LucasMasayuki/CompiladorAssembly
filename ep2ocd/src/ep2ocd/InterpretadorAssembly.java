package ep2ocd;

class InterpretadorAssembly {

    public static void compila(String entrada, Memoria memoria, Uc uc, Comandos comandosAssembly) {
        Palavra palavra;
        StringBuilder instrucao = new StringBuilder();
        StringBuilder comando = new StringBuilder(entrada);
        String opcode;
        String operandoUm = "";
        String operandoDois = "";
        int i = 0;
        int converte = 0;

        instrucao.append("");

        while (!comandosAssembly.umComandoValido(instrucao.toString())) {
            instrucao.append(comando.charAt(i));
            i++;
        }

        opcode = comandosAssembly.getComandoBinario(instrucao.toString());

        comandosAssembly.imprimeTabelaOpcode();
        
        instrucao = new StringBuilder();
        i++;
        
        while ((comando.length()) != i) {
        	if (comando.charAt(i) == ',') {
                i++;
        		break;
        	}
        	instrucao.append(comando.charAt(i));
            i++;
        }
        
        boolean eUmRegistrador = false;
        boolean eEndereco = false;

        if (uc.verificaSeUmRegistradorValido(instrucao.toString())) {
            operandoUm = uc.getComandoBinario(instrucao.toString());
            eUmRegistrador = true;
        } else {
        	if (instrucao.charAt(0) == '[') {
            	converte = (int) Long.parseLong(instrucao.toString().substring(1,instrucao.length() - 1), 16);
            	eEndereco = true;
        	} else {
        		converte = (int) Long.parseLong(instrucao.toString(), 16);
        	}
            operandoUm = Integer.toString(converte, 2);
        }

        instrucao = new StringBuilder();

        if (comando.length() == i) {
            palavra = new Palavra(opcode, operandoUm, eUmRegistrador, eEndereco);
        } else {
            while (comando.length() != i){
                instrucao.append(comando.charAt(i));
                i++;
            }

            if (uc.verificaSeUmRegistradorValido(instrucao.toString())) {
            	operandoDois = uc.getComandoBinario(instrucao.toString());
            } else {
            	if (instrucao.charAt(0) == '[') {
                	converte = (int) Long.parseLong(instrucao.toString().substring(1,instrucao.length() - 1), 16);
                	eEndereco = true;
            	} else {
                	converte = (int) Long.parseLong(instrucao.toString(), 16);
            	}
            	operandoDois = Integer.toString(converte, 2);
            }

            palavra = new Palavra(opcode, operandoUm, operandoDois, eUmRegistrador, eEndereco);
        }

        memoria.novoProcesso(palavra);
        memoria.verificaEstadoDaMemoria();
    }
}