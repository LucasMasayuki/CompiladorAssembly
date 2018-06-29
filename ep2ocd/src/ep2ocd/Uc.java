package ep2ocd;
import java.util.HashMap;
import java.util.Map;

class Uc {
    private String pc;
    private String ir;
    private String mbr;
    private String mar;
    private String ax;
    private String bx;
    private String cx;
    private String dx;
    private Map<String, String> registradoresUc =  new HashMap<String, String>();
    private String Registradores[] = {
        "ax",
        "bx",
        "cx",
        "dx"
    };

    public Uc() {
        for (int i = 0; i < this.Registradores.length; i++) {
            this.registradoresUc.put(this.Registradores[i], criaComandoBinario(i + 1));
        }
    }
    
    private String criaComandoBinario(int decimal) {
        return Integer.toString(decimal, 2);
    }

    public String getComandoBinario(String componente) {
        return this.registradoresUc.get(componente);
    }

    public boolean verificaSeUmRegistradorValido(String supostoRegistrador) {
        for (int i = 0; i < this.Registradores.length; i++) {
            if (this.Registradores[i].equals(supostoRegistrador)) {
                return true;
            }
        }
        return false;
    }
}