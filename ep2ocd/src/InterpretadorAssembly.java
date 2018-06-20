import java.util.*;
class InterpretadorAssembly {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 2) {

            System.out.println("Bem vindo ao compilador assembly 2018");
            System.out.println("1 - iniciar");
            System.out.println("2 - Sair ");
            opcao = entrada.nextInt();

            switch (opcao) {

                case 1:
                    int continuar = 1;
                    Scanner input = new Scanner(System.in);
                    while (continuar == 1) {
                        System.out.println("");
                        System.out.println("Coloque o comando em assembly que vc desejar");
                        String instrucao = input.nextLine();
                        compila(instrucao);
                        System.out.println("");
                        System.out.println("Deseja colocar mais um comando ?");
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");
                        continuar = entrada.nextInt();
                    }

                    break;

                case 2:

                    System.out.println("Finalizando o programa");
                    break;

                default:

                    System.out.println("Este nao e uma opcao valida");
                    break;
            }
            
        }
    }

    public static void compila(String entrada) {
        Palavra palavra;
        Uc uc = new Uc();
        Memoria memoria = new Memoria();
        Comandos comandosAssembly = new Comandos();
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