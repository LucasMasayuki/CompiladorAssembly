package ep2ocd;
import java.util.HashMap;
import java.util.Map;

class Uc {
    private int pc = 1;
    private Palavra ir;
    private Processo mbr;
    private int mar;
    private String ax = "0";
    private String bx = "0";
    private String cx = "0";
    private String dx = "0";
    private int flagE = 0;
    private int flagNe = 0;
    private int flagG = 0;
    private int flagGe = 0;
    private int flagL = 0;
    private int flagLe = 0;
    private Map<String, String> registradoresUc =  new HashMap<String, String>();

    private String Registradores[] = {
        "ax",
        "bx",
        "cx",
        "dx"
    };

    private Ula ula = new Ula();

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

    public Object[][] cicloDeBusca(Firmware firmware, Memoria memoria, int atual) {
    	String[] sinais = firmware.getSinaisDeControle(0);
    	String res = "";
    	for (int jota = 0; jota < sinais[atual].length(); jota++) {
	    	if (jota == 1 && sinais[atual].charAt(jota) == '1' && sinais[atual].charAt(jota + 1) == '1') {
    			this.mar = this.pc;
    		}
	    	if (jota == 19 && sinais[atual].charAt(jota) == '1') {
    			StringBuilder binario = new StringBuilder();
    			binario.append(sinais[atual].charAt(26));
    			binario.append(sinais[atual].charAt(27));
    			binario.append(sinais[atual].charAt(28));
    	    	res = binario.toString();
    			ula.setX(1);
    			ula.setY(pc);
    		}
	    	if (jota == 0 && sinais[atual].charAt(jota) == '1' && sinais[atual].charAt(20) == '1') {
    	    	System.out.print(res);
    			this.pc = ula.getResultado(res);
    		}
	
	    	if (jota == 22 && sinais[atual].charAt(jota) == '1' && sinais[atual].charAt(24) == '1') {
    			memoria.setEnderecoTemporario(mar);
    		} 
	    	if (jota == 23 && sinais[atual].charAt(jota) == '1' && sinais[atual].charAt(25) == '1') {
    			this.mbr = memoria.getProcesso(memoria.getEnderecoTemporario());
    		}
	    	if (jota == 4 && sinais[atual].charAt(jota) == '1' && sinais[atual].charAt(15) == '1' && sinais[atual].charAt(17) == '1') {
    			this.ir = this.mbr.palavra;
    		}
		}

    	String binarioIr;
    	String binarioMbr;

    	if (this.ir == null) {
    		binarioIr = "0";
    	} else {
    		binarioIr = this.ir.getPalavraCompleta();
    	}
    	
    	if (this.mbr == null) {
    		binarioMbr = "0";
    	} else {
    		binarioMbr = this.mbr.palavra.getPalavraCompleta();
    	}
   
		Object[][] dados = {
			{"pc" , this.pc },
			{"ir" , binarioIr },
			{"mbr" , binarioMbr },
			{"mar" , this.mar},
			{"ax" , this.ax},
			{"bx" , this.bx},
			{"cx" , this.cx},
			{"dx" , this.dx},
			{"x" , ula.getX()},
			{"y" , ula.getY()},
			{"flagE" , this.flagE},
			{"flagNe" , this.flagNe},
			{"flagG" , this.flagG},
			{"flagGe" , this.flagGe},
			{"flagL" , this.flagL},
			{"flagLe" , this.flagLe}
		};
		
		return dados;
    }
    
    public String cicloDeBuscaParaMostrarNaTela(Firmware firmware, Memoria memoria) {
    	String[] sinais = firmware.getSinaisDeControle(0);
    	StringBuilder builder = new StringBuilder();
 
    	for (int i = 0; i < sinais.length; i++) {
	    	for (int jota = 0; jota < sinais[i].length(); jota++) {
		    	if (jota == 1 && sinais[i].charAt(jota) == '1' && sinais[i].charAt(jota + 1) == '1') {
		    		builder.append("t1: mar <- pc  \n");
		    		builder.append("t1: x <- pc  \n");
		    		builder.append("t1: y <- 1  \n");
	    		}
		    	if (jota == 0 && sinais[i].charAt(jota) == '1' && sinais[i].charAt(20) == '1') {
		    		builder.append("t3: pc <- ula \n");
	    		} 
		    	if (jota == 22 && sinais[i].charAt(jota) == '1' && sinais[i].charAt(24) == '1') {
		    		builder.append("t3: memoria <- mar \n");
	    		} 
		    	if (jota == 23 && sinais[i].charAt(jota) == '1' && sinais[i].charAt(25) == '1') {
		    		builder.append("t4: mbr <- memoria \n");
	    		} 
		    	if (jota == 4 && sinais[i].charAt(jota) == '1' && sinais[i].charAt(15) == '1' && sinais[i].charAt(17) == '1') {
		    		builder.append("t5: ir <- mbr \n");
	    		}
			}
    	}
 
    	return builder.toString();
    }
}