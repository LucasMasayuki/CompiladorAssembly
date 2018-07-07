package ep2ocd;
import java.util.HashMap;
import java.util.Map;

class Uc {
    private String pc = "0";
    private Palavra ir;
    private Processo mbr;
    private String mar = "0";
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
    private String res = "";
    private String[] listaFlags = {
    	"flagE",
    	"flagNe",
    	"flagL",
    	"flagLe",
    	"flagG",
    	"flagGe",
    };

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
    
    public Palavra getIr() {
    	return this.ir;
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
    
    public boolean verificaPeloOpcode(String binario) {
    	for (Map.Entry<String,String> registradores : registradoresUc.entrySet()) {
            if (registradores.getValue() == binario) {
            	return true;
            }
        }
    	return false;
    }
    
    public int getIndex(String comando) {
    	return 1;
    }

    public Object[][] cicloDeBusca(Firmware firmware, Memoria memoria, int atual) {
    	String[] sinais = firmware.getSinaisDeControle(this.ir, true);
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
    			ula.setX(Integer.toBinaryString(1));
    			ula.setY(Integer.toBinaryString(Integer.parseInt(pc, 16)));
    			ula.setTipo(res);
    		}
	    	if (jota == 0 && sinais[atual].charAt(jota) == '1' && sinais[atual].charAt(20) == '1') {
    			this.pc = ula.incrementaPc();
    		}
	
	    	if (jota == 21 && sinais[atual].charAt(jota) == '1' && sinais[atual].charAt(24) == '1') {
    			memoria.setEnderecoTemporario(this.mar);
    		} 
	    	if (jota == 23 && sinais[atual].charAt(jota) == '1' && sinais[atual].charAt(25) == '1') {
    			this.mbr = memoria.getProcesso(memoria.getEnderecoTemporario());
    		}
	    	if (jota == 4 && sinais[atual].charAt(jota) == '1' && sinais[atual].charAt(15) == '1' && sinais[atual].charAt(17) == '1') {
    			this.ir = (Palavra) this.mbr.dados;
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
    		Palavra palavra = (Palavra) this.mbr.dados;
    		binarioMbr = palavra.getPalavraCompleta();
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
    
    public StringBuilder cicloDeBuscaParaMostrarNaTela(Firmware firmware, Memoria memoria) {
    	String[] sinais = firmware.getSinaisDeControle(this.ir, true);
    	StringBuilder builder = new StringBuilder();
 
    	for (int i = 0; i < sinais.length; i++) {
	    	for (int jota = 0; jota < sinais[i].length(); jota++) {
		    	if (jota == 1 && sinais[i].charAt(jota) == '1' && sinais[i].charAt(jota + 1) == '1') {
		    		builder.append("t1: mar <- pc  \n");
		    		builder.append("t1: x <- pc  \n");
		    		builder.append("t1: y <- 1  \n");
	    		}
		    	if (jota == 0 && sinais[i].charAt(jota) == '1' && sinais[i].charAt(20) == '1') {
		    		builder.append("t2: pc <- ula \n");
	    		} 
		    	if (jota == 22 && sinais[i].charAt(jota) == '1' && sinais[i].charAt(24) == '1') {
		    		builder.append("t2: memoria <- mar \n");
	    		} 
		    	if (jota == 23 && sinais[i].charAt(jota) == '1' && sinais[i].charAt(25) == '1') {
		    		builder.append("t3: mbr <- memoria \n");
	    		} 
		    	if (jota == 4 && sinais[i].charAt(jota) == '1' && sinais[i].charAt(15) == '1' && sinais[i].charAt(17) == '1') {
		    		builder.append("t4: ir <- mbr \n");
	    		}
			}
    	}
 
    	return builder;
    }
    
    public void cicloDeBuscaDireto(Firmware firmware, Memoria memoria) {
     	String[] sinais = firmware.getSinaisDeControle(this.ir, true);
     	for (int i = 0; i < sinais.length; i++) {
 	    	for (int jota = 0; jota < sinais[i].length(); jota++) {
		    	if (jota == 1 && sinais[i].charAt(jota) == '1' && sinais[i].charAt(jota + 1) == '1') {
 	    			this.mar = this.pc;
 	    		}
 		    	if (jota == 19 && sinais[i].charAt(jota) == '1') {
 	    			StringBuilder binario = new StringBuilder();
 	    			binario.append(sinais[i].charAt(26));
 	    			binario.append(sinais[i].charAt(27));
 	    			binario.append(sinais[i].charAt(28));
	    	    	res = binario.toString();
 	    			ula.setX(Integer.toBinaryString(1));
 	    			ula.setY(pc);
 	    			ula.setTipo(res);
 	    		}
 		    	if (jota == 0 && sinais[i].charAt(jota) == '1' && sinais[i].charAt(20) == '1') {
 	    			this.pc = ula.incrementaPc();
 	    		}
		    	if (jota == 21 && sinais[i].charAt(jota) == '1' && sinais[i].charAt(24) == '1') {
 	    			memoria.setEnderecoTemporario(this.mar);
 	    		} 
 		    	if (jota == 23 && sinais[i].charAt(jota) == '1' && sinais[i].charAt(25) == '1') {
 	    			this.mbr = memoria.getProcesso(memoria.getEnderecoTemporario());
	    		}
 		    	if (jota == 4 && sinais[i].charAt(jota) == '1' && sinais[i].charAt(15) == '1' && sinais[i].charAt(17) == '1') {
 	    			this.ir = (Palavra) this.mbr.dados;
 	    		}
 			}
     	}
     }

    public StringBuilder cicloDeExecucaoParaMostrarNaTela(Firmware firmware, Memoria memoria) {
    	StringBuilder builder = new StringBuilder();
	    	String[] sinaisDeControle = firmware.getSinaisDeControleParaMostrarNaTela(firmware, memoria);
	    	for (int i = 0; i < sinaisDeControle.length; i++) {
	    		switch (sinaisDeControle[i]) {
	    			case "00000000000000001010000000000000":
			    		builder.append("t1: x <- irp1  \n");
			    		break;
	
	    			case "00000000000000100001000000001000":
			    		builder.append("t2: y <- irp2  \n");
			    		break;
	
	    			case "00000100000000000000100000000000":
			    		builder.append("t3 :ax <- ula  \n");
			    		break;
	
	    			case "00000001000000000000100000000000":
			    		builder.append("t3: bx <- ula  \n");
			    		break;
	
	    			case "00000000010000000000100000000000":
			    		builder.append("t3: cx <- ula  \n");
			    		break;
	
	    			case "00000000000100000000100000000000":
			    		builder.append("t3: dx <- ula  \n");
			    		break;
	
	    			case "00000100000000100000000000000000":
			    		builder.append("t1: ax <- irp2  \n");
			    		break;
	
	    			case "00000001000000100000000000000000":
			    		builder.append("t1: bx <- irp2  \n");
			    		break;
	
	    			case "00000000010000100000000000000000":
			    		builder.append("t1: cx <- irp2  \n");
			    		break;
	
	    			case "00000000000100100000000000000000":
			    		builder.append("t1: dx <- irp2  \n");
			    		break;
			    		
	    			case "00100000000000100000000000000000":
			    		builder.append("t1: mar <- irp2  \n");
			    		break;

	    			case "00000000000000000000010010000000":
			    		builder.append("t2: memoria <- mar  \n");
			    		break;
			    		
	    			case "00000000000000000000000101000000":
			    		builder.append("t3: mbr <- memoria  \n");
			    		break;
			    	
	    			case "00001100000000000000000000000000":
			    		builder.append("t4: ax <- mbr  \n");
			    		break;
			    		
	    			case "00001001000000000000000000000000":
			    		builder.append("t4: bx <- mbr  \n");
			    		break;
			    		
	    			case "00001000010000000000000000000000":
			    		builder.append("t4: cx <- mbr  \n");
			    		break;
			    		
	    			case "00001000000100000000000000000000":
			    		builder.append("t4: dx <- mbr  \n");
			    		break;
			    		
	    			case "00000000000000100001000000010000":
			    		builder.append("t1: y <- irp1  \n");
			    		break;
			    	
	    			case "00000010000000000001000000000000":
			    		builder.append("t1: y <- ax  \n");
			    		break;
			    		
	    			case "00000000000000001010000000100000":
			    		builder.append("t2: x <- irp1  \n");
			    		break;
			    		
	    			case "00000000000000001010000000011000":
			    		builder.append("t2: x <- irp1  \n");
			    		break;
			    		
	    			case "00000000000000001010000000101000":
			    		builder.append("t1: x <- irp1  \n");
			    		break;
			    		
	    			case "00000000000000001010000000110000":
			    		builder.append("t1: x <- irp1  \n");
			    		break;
			    		
	    			case "00000000000000100001000000111000":
			    		builder.append("t2: x <- irp2  \n");
			    		break;
			    		
	    			case "10000000000000001000000000000001":
			    		builder.append("t1: pc <- irp1  \n");
			    		break;
			    		
	    			case "10000000000000001000000000000010":
			    		builder.append("t1: pc <- irp1  \n");
			    		break;
			    		
	    			case "10000000000000001000000000000011":
			    		builder.append("t1: pc <- irp1  \n");
			    		break;
			    		
	    			case "10000000000000001000000000000100":
			    		builder.append("t1: pc <- irp1  \n");
			    		break;
			 
	    			case "10000000000000001000000000000101":
			    		builder.append("t1: pc <- irp1  \n");
			    		break;
			    	
	    			case "10000000000000001000000000000110":
			    		builder.append("t1: pc <- irp1  \n");
			    		break;
			    		
	    			case "10000000000000001000000000000111":
			    		builder.append("t1: pc <- irp1  \n");
			    		break;
	    		}	
	    	}
    	return builder;
    }
    
    public Object[][] cicloDeExecucao(Firmware firmware, int indice, int atual, Memoria memoria) {
    	String[] sinaisDeControle = firmware.getSinaisDeControle(this.ir, false);
    	
    	if (atual >= sinaisDeControle.length) {
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
        		Palavra palavra = (Palavra) this.mbr.dados;
        		binarioMbr = palavra.getPalavraCompleta();
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
   
		switch (sinaisDeControle[atual]) {
			case "00000000000000001010000000000000":
				ula.setX(verificaSeEUmRegistrador(this.ir.getOperandoUm()));
	    		break;

			case "00000000000000100001000000001000":
				ula.setY(verificaSeEUmRegistrador(this.ir.getOperandoDois()));
				ula.setTipo("001");
	    		break;

			case "00000100000000000000100000000000":
	    		this.ax = ula.getResultado();
	    		break;

			case "00000001000000000000100000000000":
	    		this.bx = ula.getResultado();
	    		break;

			case "00000000010000000000100000000000":
	    		this.cx = ula.getResultado();
	    		break;

			case "00000000000100000000100000000000":
	    		this.dx = ula.getResultado();
	    		break;

			case "00000100000000100000000000000000":
				this.ax = verificaSeEUmRegistrador(this.ir.getOperandoDois());
	    		break;

			case "00000001000000100000000000000000":
				this.bx = verificaSeEUmRegistrador(this.ir.getOperandoDois());
	    		break;

			case "00000000010000100000000000000000":
				this.cx = verificaSeEUmRegistrador(this.ir.getOperandoDois());
	    		break;

			case "00000000000100100000000000000000":
				this.dx = verificaSeEUmRegistrador(this.ir.getOperandoDois());
	    		break;
				
			case "00100000000000100000000000000000":
				if (this.ir.op1eUmEndereco) {
					memoria.novoProcessoSetandoEndereco(this.ir.getOperandoDois(), Integer.parseInt(this.ir.getOperandoUm(), 16));
					this.mar = this.ir.getOperandoUm();
				} else {
		    		this.mar = Integer.toHexString(Integer.parseInt(this.ir.getOperandoDois(), 2));
				}
	    		break;

			case "00000000000000000000010010000000":
	    		memoria.setEnderecoTemporario(this.mar);
	    		break;
	    		
			case "00000000000000000000000101000000":
	    		this.mbr = memoria.getProcesso(memoria.getEnderecoTemporario());
	    		break;
	    	
			case "00001100000000000000000000000000":
	    		this.ax = (String) this.mbr.dados;
	    		break;
	    		
			case "00001001000000000000000000000000":
	    		this.bx = (String) this.mbr.dados;
	    		break;
	    		
			case "00001000010000000000000000000000":
	    		this.cx = (String) this.mbr.dados;
	    		break;
	    		
			case "00001000000100000000000000000000":
	    		this.dx = (String) this.mbr.dados;
	    		break;
	    		
			case "00000000000000100001000000010000":
	    		ula.setY(this.ir.getOperandoUm());
	    		ula.setTipo("010");
	    		break;
	    	
			case "00000010000000000001000000000000":
	    		ula.setY(this.ax);
	    		break;
	    		
			case "00000000000000001010000000100000":
	    		ula.setX(verificaSeEUmRegistrador(this.ir.getOperandoUm()));
	    		ula.setTipo("010");
	    		break;
	    		
			case "00000000000000001010000000011000":
	    		ula.setX(verificaSeEUmRegistrador(this.ir.getOperandoUm()));
	    		ula.setTipo("011");
	    		break;
	    		
			case "00000000000000001010000000101000":
	    		ula.setX(verificaSeEUmRegistrador(this.ir.getOperandoUm()));
	    		ula.setTipo("101");
	    		break;
	    		
			case "00000000000000001010000000110000":
	    		ula.setX(verificaSeEUmRegistrador(this.ir.getOperandoUm()));
	    		ula.setTipo("110");
	    		break;
	    		
			case "00000000000000100001000000111000":
	    		ula.setX(verificaSeEUmRegistrador(this.ir.getOperandoDois()));
	    		ula.setTipo("111");
	    		int[] flagsEstado = ula.getFlagCodition();
	    		for (int i = 0; i < listaFlags.length; i++) {
	    			if (listaFlags[i] == "flagE") {
	    				this.flagE = flagsEstado[i];
	    			} else if (listaFlags[i] == "flagNe") {
	    				this.flagNe = flagsEstado[i];
	    			} else if (listaFlags[i] == "flagL") {
	    				this.flagL = flagsEstado[i];
	    			} else if (listaFlags[i] == "flagLe") {
	    				this.flagLe = flagsEstado[i];
	    			} else if (listaFlags[i] == "flagG") {
	    				this.flagG = flagsEstado[i];
	    			} else if (listaFlags[i] == "flagGe") {
	    				this.flagGe = flagsEstado[i];
	    			}
	    		}
	    		break;
	    		
			case "10000000000000001000000000000001":
		    	this.pc = verificaSeEUmRegistrador(this.ir.getOperandoUm());
	    		break;
	    		
			case "10000000000000001000000000000010":
				if (this.flagE == 1) {
		    		this.pc = verificaSeEUmRegistrador(this.ir.getOperandoUm());
				}
	    		break;
	    		
			case "10000000000000001000000000000011":
				if (this.flagNe == 1) {
		    		this.pc = verificaSeEUmRegistrador(this.ir.getOperandoUm());
				}
	    		break;
	    		
			case "10000000000000001000000000000100":
				if (this.flagL == 1) {
		    		this.pc = verificaSeEUmRegistrador(this.ir.getOperandoUm());
				}
	    		break;
	 
			case "10000000000000001000000000000101":
				if (this.flagLe == 1) {
		    		this.pc = verificaSeEUmRegistrador(this.ir.getOperandoUm());
				}
	    		break;
	    	
			case "10000000000000001000000000000110":
				if (this.flagG == 1) {
		    		this.pc = verificaSeEUmRegistrador(this.ir.getOperandoUm());
				}
	    		break;
	    		
			case "10000000000000001000000000000111":
				if (this.flagGe == 1) {
		    		this.pc = verificaSeEUmRegistrador(this.ir.getOperandoUm());
				}
	    		break;
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
    		Palavra palavra = (Palavra) this.mbr.dados;
    		binarioMbr = palavra.getPalavraCompleta();
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
    
    public String verificaSeEUmRegistrador(String op) {
		String dado = "";
		for (Map.Entry<String,String> registradores : registradoresUc.entrySet()) {
            if (registradores.getKey() == "ax" && registradores.getValue() == op) {
            	dado = this.ax;
            } else if (registradores.getKey() == "bx" && registradores.getValue() == op) {
            	dado = this.bx;
            } else if (registradores.getKey() == "cx" && registradores.getValue() == op) {
            	dado = this.cx;
            } else if (registradores.getKey() == "dx" && registradores.getValue() == op) {
            	dado = this.dx;
            }
		} if (dado.isEmpty()) {
			dado = op;
		}
		return dado;
    }
}