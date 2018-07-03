package ep2ocd;

import java.util.HashMap;
import java.util.Map;

class Barramento{
	private Map<Integer, String> ligacoes =  new HashMap<Integer, String>();
	private boolean[] sai = {
			true,
			false,
			true,
			true,
			false,
			true,
			false,
			true,
			false,
			true,
			false,
			true,
			false,
			false,
			true,
			false,
			true,
			false,
			true,
			true,
			false,
			false,
			false,
			true,
			true,
			false,
	};

	private String[] componentes = {
			"pc",
			"ir",
			"mbr",
			"mar",
			"ax",
			"bx",
			"cx",
			"dx",
			"ula",
			"x",
			"memoria",
	};
	
	public Barramento() {
		this.ligacoes.put(1, this.componentes[0]);
		this.ligacoes.put(2, this.componentes[0]);
		this.ligacoes.put(3, this.componentes[3]);
		this.ligacoes.put(4, this.componentes[4]);
		this.ligacoes.put(5, this.componentes[4]);
		this.ligacoes.put(6, this.componentes[5]);
		this.ligacoes.put(7, this.componentes[5]);
		this.ligacoes.put(8, this.componentes[6]);
		this.ligacoes.put(9, this.componentes[6]);
		this.ligacoes.put(10, this.componentes[7]);
		this.ligacoes.put(11, this.componentes[8]);
		this.ligacoes.put(12, this.componentes[9]);
		this.ligacoes.put(13, this.componentes[9]);
		this.ligacoes.put(14, this.componentes[1]);
		this.ligacoes.put(15, this.componentes[1]);
		this.ligacoes.put(16, this.componentes[1]);
		this.ligacoes.put(17, this.componentes[1]);
		this.ligacoes.put(18, this.componentes[1]);
		this.ligacoes.put(19, this.componentes[11]);
		this.ligacoes.put(20, this.componentes[10]);
		this.ligacoes.put(21, this.componentes[10]);
		this.ligacoes.put(22, this.componentes[3]);
		this.ligacoes.put(23, this.componentes[4]);
		this.ligacoes.put(24, this.componentes[4]);
		this.ligacoes.put(25, this.componentes[12]);
		this.ligacoes.put(26, this.componentes[12]);
	}
}