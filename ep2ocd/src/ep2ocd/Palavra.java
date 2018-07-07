package ep2ocd;

public class Palavra {
	private String opcode;
	private String operandoUm;
	private String operandoDois;
	boolean op1eUmRegistrador = false;
	boolean op2eUmRegistrador = false;
    boolean op1eUmEndereco = false;
	boolean op2eUmEndereco = false;

	public Palavra(String opcode, String operandoUm, boolean op1eUmRegistrador, boolean op1eUmEndereco) {
		this.opcode = opcode;
		this.operandoUm = operandoUm;
		this.op1eUmRegistrador = op1eUmRegistrador;
		this.op1eUmEndereco = op1eUmEndereco;
        System.out.println(op1eUmRegistrador);
	}
	
	public Palavra(String opcode, String operandoUm, String operandoDois, boolean op1eUmRegistrador, boolean op1eUmEndereco, boolean op2eUmEndereco, boolean op2eUmRegistrador) {
		this.opcode = opcode;
		this.operandoUm = operandoUm;
		this.operandoDois = operandoDois;
		this.op1eUmRegistrador = op1eUmRegistrador;
		this.op1eUmEndereco = op1eUmEndereco;
		this.op2eUmRegistrador = op2eUmRegistrador;
		this.op2eUmEndereco = op2eUmEndereco;
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
	
	public String getPalavraCompleta() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.opcode);
		builder.append(this.operandoUm);
		builder.append(this.operandoDois);
		return builder.toString();
	}
}
