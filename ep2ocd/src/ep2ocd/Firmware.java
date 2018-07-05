package ep2ocd;
class Firmware {
    private final String sinaisDeControle[][] = 
    {
    	// ciclo de busca
    	{
    		"01100000000000000001000000101", 
    		"10000000000000000000110010000",
    		"00000000000000000000000101000",
    		"00001000000000010100000000000"
    	},
    	// Comando add ax,(numero)
        {
            "00000000000000001010000000000",
            "00000000000000100001000000001",
            "00000100000000000000100000000"
        },
        // Comando add bx,(numero)
        {
            "00000000000000001010000000000",
            "00000000000000100001000000001",
            "00000001000000000000100000000"
        },
        // Comando add cx,(numero/ registrador)
        {
            "00000000000000001010000000000",
            "00000000000000100001000000001",
            "00000000010000000000100000000"
        },
        // Comando add dx,(numero/ registrador)
        {
            "00000000000000001010000000000",
            "00000000000000100001000000001",
            "00000000010000000000100000000"
        },
        // Comando mov ax,(numero/registrador)***
        {
            "00000100000000100000000000000"
        },
        // Comando mov bx,(numero/registrador)***
        {
            "00000001000000100000000000000"
        },
        // Comando mov cx,(numeroo/registrador)***
        {
            "00000000010000100000000000000"
        },
        // Comando mov dx,(numero/registrador)***
        {
            "00000000000100100000000000000"
        },
        // Comando mov ax,(endereco)
        {
            "00100000000000100000000000000",
            "00000000000000000000100010000",
            "00000000000000000000000101000",
            "00001100000000000000000000000"
        },
        // Comando mov bx,(endereco)
        {
            "00100000000000100000000000000",
            "00000000000000000000100010000",
            "00000000000000000000000101000",
            "00001001000000000000000000000"
        },
        // Comando mov cx,(endereco)
        {
            "00100000000000100000000000000",
            "00000000000000000000100010000",
            "00000000000000000000000101000",
            "00001000010000000000000000000"
        },
        // Comando mov dx,(endereco)
        {
            "00100000000000100000000000000",
            "00000000000000000000100010000",
            "00000000000000000000000101000",
            "00001000000100000000000000000"
        },
    	// Comando sub ax,(numero/registrador)***
    	{
    		"00000000000000001010000000000", 
    		"00000000000000100001000000010",
    		"00000100000000000000100000000"
    	},
        // Comando sub bx,(numero/registrador)***
        {
            "00000000000000001010000000000", 
            "00000000000000100001000000010",
            "00000001000000000000100000000"
        },
        // Comando sub cx,(numero/registrador)***
        {
            "00000000000000001010000000000", 
            "00000000000000100001000000010",
            "00000000010000000000100000000"
        },
        // Comando sub dx,(numero/registrador)***
        {
            "00000000000000001010000000000", 
            "00000000000000100001000000010",
            "00000000000100000000100000000"
        },
    	// Comando  mul (registrador)
    	{
    		"00000010000000000001000000000", 
    		"00000000000000001010000000001",
    		"00000100000000000000100000000"
    	},
    	// Comando div (registrador)
    	{
    		"00000010000000000001000000000", 
    		"00000000000000001010000000011",
    		"00000100000000000000100000000"
    	},
        // Comando inc ax
        {
            "00000000000000001010000000101",
            "00000100000000000000100000000"
        },
        // Comando inc bx
        {
            "00000000000000001010000000101",
            "00000001000000000000100000000"
        },
        // Comando inc cx
        {
            "00000000000000001010000000101",
            "00000000010000000000100000000"
        },
        // Comando inc dx
        {
            "00000000000000001010000000101",
            "00000000000100000000100000000"
        },
        // Comando dec ax
        {
            "00000000000000001010000000111",
            "00000100000000000000100000000"
        },
        // Comando dec bx
        {
            "00000000000000001010000000111",
            "00000001000000000000100000000"
        },
        // Comando dec cx
        {
            "00000000000000001010000000111",
            "00000000010000000000100000000"
        },
        // Comando dec dx
        {
            "00000000000000001010000000111",
            "00000000000100000000100000000"
        },
        // Comando cmp
        {
            "00000000000000100001000000111",
            "00000000000000001010000000000"
        },
        // Comando jmp

        {
            "10000000000000001000000000000",
        },
    };

    public String[] getSinaisDeControle(int indice, Palavra ir) {

    	if (indice ==  0) {
    		return sinaisDeControle[indice];
    	}

    	System.out.print(indice);

    	Uc uc = new Uc();
    	if (ir.getOpcode() == "add") {
    		indice += Integer.parseInt(ir.getOperandoUm(), 2);
    		// como diferenciar numeros de enderecos
    	} else if (ir.getOpcode() == "mov") {
    		indice += 4;
    		if (uc.verificaSeUmRegistradorValido(ir.getOperandoUm())) {
    			indice += Integer.parseInt(ir.getOperandoUm(), 2);
    		}
    	} else if (ir.getOpcode() == "sub") {
    		indice += 10;
    		if (uc.verificaSeUmRegistradorValido(ir.getOperandoUm())) {
    			indice += Integer.parseInt(ir.getOperandoUm(), 2);
    		}
    	} else if (ir.getOpcode() == "mul") {
    		indice += 14;
    	} else if (ir.getOpcode() == "div") {
    		indice += 15;
    	} else if (ir.getOpcode() == "inc") {
    		indice += 16;
    		if (uc.verificaSeUmRegistradorValido(ir.getOperandoUm())) {
    			indice += Integer.parseInt(ir.getOperandoUm(), 2);
    		}
    	} else if (ir.getOpcode() == "dec") {
    		indice += 20;
    		if (uc.verificaSeUmRegistradorValido(ir.getOperandoUm())) {
    			indice += Integer.parseInt(ir.getOperandoUm(), 2);
    		}
    	} else if (ir.getOpcode() == "cmp") {
    		indice += 24;
    	} else if (ir.getOpcode() == "jmp") {
    		indice += 25;
    	}

        return this.sinaisDeControle[indice];
    }
    
    public String[] getSinaisDeControleParaMostrarNaTela(int indice, Firmware firmware, Memoria memoria, int atual) {
    	Uc uc = new Uc();
    	uc.cicloDeBusca(firmware, memoria, atual);
    	return getSinaisDeControle(indice, uc.getIr());
    }
    
    public int tamanhoSinal(int indice) {
    	return this.sinaisDeControle[indice].length;
    }
}