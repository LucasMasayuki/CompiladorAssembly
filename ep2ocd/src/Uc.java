import java.util.HashMap;
import java.util.Map;

class Uc {
    private String PC;
    private String IR;
    private String MBR;
    private String MAR;
    private String AX;
    private String BX;
    private String CX;
    private String DX;
    private Map<String, String> registradoresUc =  new HashMap<String, String>();
    private String Registradores[] = {
        "AX",
        "BX",
        "CX",
        "DX"
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
            if (this.Registradores[i] == supostoRegistrador) {
                return true;
            }
        }
        return false;
    }
}