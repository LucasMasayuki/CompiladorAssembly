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
    private Map<String, String> componentesUc =  new HashMap<String, String>();
    private String Registradores[] = {
        "AX",
        "BX",
        "CX",
        "DX",
        "MAR",
        "MBR",
        "IR",
        "PC",
        "AX",
        "BX",
        "CX",
        "DX"
    };
    
    private String criaComandoBinario(int decimal) {
        return Integer.toString(decimal, 2);
    }

    public Uc() {
        for (int i = 0; i < this.Registradores.length; i++) {
            this.componentesUc.put(this.Registradores[i], criaComandoBinario(i + 1));
        }
    }
    
    public String getComandoBinario(String componente) {
        return this.componentesUc.get(componente);
    }
}