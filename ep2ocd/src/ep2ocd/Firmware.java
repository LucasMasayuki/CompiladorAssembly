package ep2ocd;
class Firmware {
    private final String sinaisDeControle[][] = 
    {
    	// ciclo de busca
    	{
    		"01100000000000000001000000101000", 
    		"10000000000000000000110010000000",
    		"00000000000000000000000101000000",
    		"00001000000000010100000000000000"
    	},
    	// Comando add ax,(numero)
        {
            "00000000000000001010000000000000",
            "00000000000000100001000000001000",
            "00000100000000000000100000000000"
        },
        // Comando add bx,(numero)
        {
            "00000000000000001010000000000000",
            "00000000000000100001000000001000",
            "00000001000000000000100000000000"
        },
        // Comando add cx,(numero/ registrador)
        {
            "00000000000000001010000000000000",
            "00000000000000100001000000001000",
            "00000000010000000000100000000000"
        },
        // Comando add dx,(numero/ registrador)
        {
            "00000000000000001010000000000000",
            "00000000000000100001000000001000",
            "00000000000100000000100000000000"
        },
        // Comando mov ax,(numero/registrador)***
        {
            "00000100000000100000000000000000"
        },
        // Comando mov bx,(numero/registrador)***
        {
            "00000001000000100000000000000000"
        },
        // Comando mov cx,(numeroo/registrador)***
        {
            "00000000010000100000000000000000"
        },
        // Comando mov dx,(numero/registrador)***
        {
            "00000000000100100000000000000000"
        },
        // Comando mov ax,(endereco)
        {
            "00100000000000100000000000000000",
            "00000000000000000000010010000000",
            "00000000000000000000000101000000",
            "00001100000000000000000000000000"
        },
        // Comando mov bx,(endereco)
        {
            "00100000000000100000000000000000",
            "00000000000000000000010010000000",
            "00000000000000000000000101000000",
            "00001001000000000000000000000000"
        },
        // Comando mov cx,(endereco)
        {
            "00100000000000100000000000000000",
            "00000000000000000000010010000000",
            "00000000000000000000000101000000",
            "00001000010000000000000000000000"
        },
        // Comando mov dx,(endereco)
        {
            "00100000000000100000000000000000",
            "00000000000000000000010010000000",
            "00000000000000000000000101000000",
            "00001000000100000000000000000000"
        },
        // Comando mov endereço, registrador
        {
            "00100000000000100000000000000000",
            "00000000000000000000010010000000"
        },
    	// Comando sub ax,(numero/registrador)***
    	{
    		"00000000000000001010000000000000", 
    		"00000000000000100001000000010000",
    		"00000100000000000000100000000000"
    	},
        // Comando sub bx,(numero/registrador)***
        {
            "00000000000000001010000000000000", 
            "00000000000000100001000000010000",
            "00000001000000000000100000000000"
        },
        // Comando sub cx,(numero/registrador)***
        {
            "00000000000000001010000000000000", 
            "00000000000000100001000000010000",
            "00000000010000000000100000000000"
        },
        // Comando sub dx,(numero/registrador)***
        {
            "00000000000000001010000000000000", 
            "00000000000000100001000000010000",
            "00000000000100000000100000000000"
        },
    	// Comando  mul (registrador)
    	{
    		"00000010000000000001000000000000", 
    		"00000000000000001010000000100000",
    		"00000100000000000000100000000000"
    	},
    	// Comando div (registrador)
    	{
    		"00000010000000000001000000000000", 
    		"00000000000000001010000000011000",
    		"00000100000000000000100000000000"
    	},
        // Comando inc ax
        {
            "00000000000000001010000000101000",
            "00000100000000000000100000000000"
        },
        // Comando inc bx
        {
            "00000000000000001010000000101000",
            "00000001000000000000100000000000"
        },
        // Comando inc cx
        {
            "00000000000000001010000000101000",
            "00000000010000000000100000000000"
        },
        // Comando inc dx
        {
            "00000000000000001010000000101000",
            "00000000000100000000100000000000"
        },
        // Comando dec ax
        {
            "00000000000000001010000000110000",
            "00000100000000000000100000000000"
        },
        // Comando dec bx
        {
            "00000000000000001010000000110000",
            "00000001000000000000100000000000"
        },
        // Comando dec cx
        {
            "00000000000000001010000000110000",
            "00000000010000000000100000000000"
        },
        // Comando dec dx
        {
            "00000000000000001010000000110000",
            "00000000000100000000100000000000"
        },
        // Comando cmp
        {
            "00000000000000100001000000111000",
            "00000000000000001010000000000000"
        },
        // Comando jmp

        {
            "10000000000000001000000000000001",
        },
        // Comando je

        {
            "10000000000000001000000000000010",
        },
        // Comando jne

        {
            "10000000000000001000000000000011",
        },
        // Comando jg

        {
            "10000000000000001000000000000110",
        },
        // Comando jge

        {
            "10000000000000001000000000000111",
        },
        // Comando jl

        {
            "10000000000000001000000000000100",
        },
        // Comando jle

        {
            "10000000000000001000000000000101",
        },
    };
    private Uc uc = new Uc();

    public String[] getSinaisDeControle(Palavra ir, boolean busca) {
    	int indice = 0;
    	if (busca) {
    		return sinaisDeControle[0];
    	}
 
    	if (ir.getOpcode().equals("1")) {
    		indice += Integer.parseInt(ir.getOperandoUm(), 2);
    	} else if (ir.getOpcode().equals("10")) {
    		indice += 5;
    		if (ir.op1eUmRegistrador) {
    			indice += Integer.parseInt(ir.getOperandoUm(), 2) - 1;
    			if (ir.op2eUmEndereco) {
    				indice++;
        			indice += Integer.parseInt(ir.getOperandoUm(), 2);
    			}
    		} else if (ir.op1eUmEndereco) {
    			indice += 11;
    		}
    	} else if (ir.getOpcode().equals("11")) {
    		indice += 14;
    		if (ir.op1eUmRegistrador) {
    			indice += Integer.parseInt(ir.getOperandoUm(), 2) - 1;
    		}
    	} else if (ir.getOpcode().equals("100")) {
    		indice += 18;
    	} else if (ir.getOpcode().equals("101")) {
    		indice += 19;
    	} else if (ir.getOpcode().equals("111")) {
    		indice += 20;
    		if (ir.op1eUmRegistrador) {
    			indice += Integer.parseInt(ir.getOperandoUm(), 2) - 1;
    		}
    	} else if (ir.getOpcode().equals("1000")) {
    		indice += 24;
    		if (uc.verificaPeloOpcode(ir.getOperandoUm())) {
    			indice += Integer.parseInt(ir.getOperandoUm(), 2);
    		}
    	} else if (ir.getOpcode().equals("110")) {
    		indice += 28;
    	} else if (ir.getOpcode().equals("1001")) {
    		indice += 29;
    	} else if (ir.getOpcode().equals("1010")) {
    		indice += 30;
    	} else if (ir.getOpcode().equals("1011")) {
    		indice += 31;
    	} else if (ir.getOpcode().equals("1100")) {
    		indice += 32;
    	} else if (ir.getOpcode().equals("1101")) {
    		indice += 33;
    	} else if (ir.getOpcode().equals("1110")) {
    		indice += 34;
    	} else if (ir.getOpcode().equals("1111")) {
    		indice += 35;
    	}

        return this.sinaisDeControle[indice];
    }
    
    public String[] getSinaisDeControleParaMostrarNaTela(Firmware firmware, Memoria memoria) {
    	uc.cicloDeBuscaDireto(firmware, memoria);
    	return getSinaisDeControle(uc.getIr(), false);
    }
    
    public int tamanhoSinal(int indice) {
    	return this.sinaisDeControle[indice].length;
    }
}