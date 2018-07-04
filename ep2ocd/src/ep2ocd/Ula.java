package ep2ocd;

import java.util.HashMap;
import java.util.Map;

class Ula {
    private int X;
    private int Y;
    private Map<String, String> operacao =  new HashMap<String, String>();
   
    public Ula() {
        operacao.put("000", "nada");
        operacao.put("001", "adicao");
        operacao.put("010", "subtracao");
        operacao.put("011", "divsao");
        operacao.put("100", "multiplicacao");
        operacao.put("101", "++");
        operacao.put("110", " - -");
        operacao.put("111", "comparação");
    }

    public void setY(int binario) {
        this.Y = binario;
    }

    public void setX(int binario) {
        this.X = binario;
    }
    
    public int getX() {
    	return this.X;
    }
    
    public int getY() {
    	return this.Y;
    }

    public int getResultado(String tipoOperacao) {
    	if (operacao.get(tipoOperacao).equals("++") || operacao.get(tipoOperacao).equals("--")) {
            return X + Y;
    	} else {
    		return 0;
    	}
    }
}