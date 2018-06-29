package ep2ocd;

public class Palavra {
	private String opcode;
	private String operandoUm;
	private String operandoDois;

	public Palavra(String opcode, String operandoUm) {
		this.opcode = opcode;
		this.operandoUm = operandoUm;
	}
	
	public Palavra(String opcode, String operandoUm, String operandoDois) {
		this.opcode = opcode;
		this.operandoUm = operandoUm;
		this.operandoDois = operandoDois;
	}

	public String getOpcode() {
		if (this.opcode == null) {
			return "";
		}
		return this.opcode;
	}

	public String getOperandoUm() {
		if (this.operandoUm == null) {
			return "";
		}
		return this.operandoUm;
	}

	public String getOperandoDois() {
		if (this.operandoDois == null) {
			return "";
		}
		return this.operandoDois;
	}
}
