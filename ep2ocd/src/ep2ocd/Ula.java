package ep2ocd;

import java.util.HashMap;
import java.util.Map;

class Ula {
    private String X;
    private String Y;
    private String tipo;
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
    
    public void setTipo(String tipo) {
    	this.tipo = tipo;
    }
    
    public String getX() {
    	return this.X;
    }
    
    public String getY() {
    	return this.Y;
    }
    
    public String getTipo() {
    	return this.tipo;
    }

    public int getResultado() {
		int xDecimal = Integer.parseInt(this.X, 2);
		int yDecimal = Integer.parseInt(this.Y, 2);
    	if (operacao.get(this.tipo).equals("++") || operacao.get(this.tipo).equals("adicao")) {
            return xDecimal + yDecimal;
    	} else if (operacao.get(this.tipo).equals("--") || operacao.get(this.tipo).equals("subtracao")) {
            return xDecimal - yDecimal;
    	} else if (operacao.get(this.tipo).equals("multiplicacao")) {
            return xDecimal * yDecimal;
    	} else if (operacao.get(this.tipo).equals("divsao")) {
            return xDecimal / yDecimal;
    	} else if (operacao.get(this.tipo).equals("comparacao")) {
    		
    	}
    	return 0;
    }
}