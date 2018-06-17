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
                    int continuar = 0;
                    Memoria memoria = new Memoria();
                    while (continuar != 1) {
                        System.out.println("");
                        System.out.println("Coloque o comando em assembly que vc desejar");
                        StringBuilder comando;
                        String instrucao = entrada.next();
                        comando = new StringBuilder(instrucao);
                        compila(comando, memoria);
                        System.out.println("");
                        System.out.println("Deseja colocar mais um comando ?");
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");
                        continuar = entrada.nextInt();
                    }

                    memoria.verificaEstadoDaMemoria();

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

    public static void compila(StringBuilder comando, Memoria memoria) {
        Comandos comandosAssembly = new Comandos();
        Uc uc = new Uc();
        StringBuilder instrucao = new StringBuilder();
        String opcode;
        String operandoUm;
        String operandoDois;
        int i = 0;

        instrucao.append("");

        while (!comandosAssembly.umComandoValido(instrucao.toString())) {
            instrucao.append(comando.charAt(i));
            i++;
        }

        opcode = comandosAssembly.getComandoBinario(instrucao.toString());

        comandosAssembly.imprimeTabelaOpcode();
        
        instrucao = new StringBuilder();
        
        while (comando.charAt(i) != ',') {
        	instrucao.append(comando.charAt(i));
            i++;
        }

        if (uc.verificaSeUmRegistradorValido(instrucao.toString())) {
            operandoUm = uc.getComandoBinario(instrucao.toString());
        } else {
            operandoUm = instrucao.toString();
        }

        if (comando.length() == i - 1) {
            Palavra palavra = new Palavra(opcode, operandoUm);
        } else {
            while (comando.charAt(i) != i - 1){
                instrucao.append(comando.charAt(i));
                i++;
            }
            Palavra palavra = new Palavra(opcode, operandoUm, operandoDois);
        }
        memoria.novoProcesso(palavra);
    }
}