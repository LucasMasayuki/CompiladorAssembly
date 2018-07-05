package ep2ocd;

import java.util.HashMap;
import java.util.Map;

class Ula {
    private String X;
    private String Y;
    private Map<String, String> operacao =  new HashMap<String, String>();
   
    public Ula() {
        operacao.put("000", "nada");
        operacao.put("001", "adicao");
        operacao.put("010", "subtracao");
        operacao.put("011", "divsao");
        operacao.put("100", "multiplicacao");
        operacao.put("101", "++");
        operacao.put("110", " - -");
        operacao.put("111", "comparacao");
    }

    public void setY(String binario) {
        this.Y = binario;
    }

    public void setX(String binario) {
        this.X = binario;
    }
    
    public String getX() {
    	return this.X;
    }
    
    public String getY() {
    	return this.Y;
    }

    public int getResultado(String tipoOperacao) {
    	if (operacao.get(tipoOperacao).equals("++") || operacao.get(tipoOperacao).equals("--")) {
    		int xDecimal = Integer.parseInt(this.X, 2);
    		int yDecimal = Integer.parseInt(this.Y, 2);
            return xDecimal + yDecimal;
    	} else {
    		return 0;
    	}
    }
}