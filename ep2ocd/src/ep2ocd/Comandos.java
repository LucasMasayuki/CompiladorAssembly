package ep2ocd;
import java.util.Map;
import java.util.HashMap;
class Comandos {
    private Map<String, String> comandos =  new HashMap<String, String>();
    private String assemblyComandos[] = {
        "add",
        "mov",
        "sub",
        "mul",
        "div",
        "cmp",
        "inc",
        "jmp",
        "je",
        "jne",
        "jg",
        "jge",
        "jl",
        "jle"
    };

    public Comandos() {
        for (int i = 0; i < this.assemblyComandos.length; i++) {
            this.comandos.put(this.assemblyComandos[i], criaComandoBinario(i));
        }
    }

    private String criaComandoBinario(int decimal) {
        return Integer.toString(decimal, 2);
    } 

    public String getComandoBinario(String comando) {
        return this.comandos.get(comando);
    }

    public boolean umComandoValido(String comando) {
            for (int i = 0; i < this.assemblyComandos.length; i++) {
                if (comando.equals(this.assemblyComandos[i])) {
                    return true;
                }
            }
            return false;
    }

    public void imprimeTabelaOpcode() {
        System.out.println("|Tabela opcode|");
        for (Map.Entry<String,String> comando : comandos.entrySet()) {
            System.out.print("");
            System.out.println("|comando: " + comando.getKey() + " | binario: " + comando.getValue() + "|");
            System.out.print("");
        }
    } 
}