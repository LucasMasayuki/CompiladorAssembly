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
                    System.out.println("");
                    System.out.println("Coloque o comando em assembly que vc desejar");
                    StringBuilder comando;
                    String instrucao = entrada.next();
                    comando = new StringBuilder(instrucao);
                    compila(comando);
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

    public static void compila(StringBuilder comando) {
        Comandos comandosAssembly = new Comandos();
        StringBuilder instrucao = new StringBuilder();
        StringBuilder binario = new StringBuilder();
        int i = 0;
        instrucao.append("");
        binario.append("");
        while (!comandosAssembly.umComandoValido(instrucao.toString())) {
            instrucao.append(comando.charAt(i));
            i++;
        }

        binario.append(comandosAssembly.getComandoBinario(instrucao.toString()));

        comandosAssembly.imprimeTabelaOpcode();
    }
}