package ep2ocd;

public class Palavra {
	private String opcode;
	private String operandoUm;
	private String operandoDois;
	private boolean eUmRegistrador;

	public Palavra(String opcode, String operandoUm, boolean eUmRegistrador) {
		this.opcode = opcode;
		this.operandoUm = operandoUm;
		this.eUmRegistrador = eUmRegistrador;
	}
	
	public Palavra(String opcode, String operandoUm, String operandoDois, boolean eUmRegistrador) {
		this.opcode = opcode;
		this.operandoUm = operandoUm;
		this.operandoDois = operandoDois;
		this.eUmRegistrador = eUmRegistrador;
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
	
	public boolean getEUmRegistrador() {
		return this.eUmRegistrador;
	}
	
	public String getPalavraCompleta() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.opcode);
		builder.append(this.operandoUm);
		builder.append(this.operandoDois);
		return builder.toString();
	}
}
