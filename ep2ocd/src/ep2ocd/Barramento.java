package ep2ocd;

class Barramento{
	// primeiro porta - 1, segundo componente, terceiro verifica se entra true ou sai false, externo false , interno true
	private Object[][] caminhos = {
		{
			0, "pc", true, true
		},
		{
			1, "pc", false, true
		},
		{
			2, "mar", true, true
		},
		{
			3, "mbr", true, true
		},
		{
			4, "mbr", false, true
		},
		{
			5, "ax", true, true
		},
		{
			6, "ax", false, true
		},
		{
			7, "bx", true, true
		},
		{
			8, "bx", false, true
		},
		{
			9, "cx", true, true
		},
		{
			10, "cx", false, true
		},
		{
			11, "dx", true, true
		},
		{
			12, "dx", false, true
		},	
		{
			13, "ir", true, true
		},	
		{
			14, "ir", false, true
		},	
		{
			15, "ir", true, true
		},	
		{
			16, "ir", false, true
		},	
		{
			17, "ir", true, true
		},	
		{
			18, "x", true, true
		},
		{
			19, "y", true, true
		},	
		{
			20, "ula", false, true
		},	
		{
			21, "mar", false, true
		},	
		{
			22, "mbr", false, true
		},	
		{
			23, "mbr", true, true
		},	
		{
			24, "memoria", true, true
		},	
		{
			25, "memoria", false, true
		},	
		
	};
	
	public Object[] getCaminhos(int indice) {
		return this.caminhos[indice];
	}
	
	public Barramento() {
	}
}