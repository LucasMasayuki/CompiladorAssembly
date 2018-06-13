class Uc {
    private String PC;
    private String IR;
    private String MBR;
    private String MAR;
    private String AX;
    private String BX;
    private String CX;
    private String DX;
    private String Registradores[] = {
        "AX",
        "BX",
        "CX",
        "DX",
    };

    public Uc() {
        for (int i = 0; i < this.Registradores.length; i++) {
            this.comandos.put(this.Registradores[i], criaComandoBinario(i + 1));
        }
    }
}