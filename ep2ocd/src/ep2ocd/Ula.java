package ep2ocd;
class Ula {
    private int X;
    private int Y;

    public void setY(int binario) {
        this.Y = binario;
    }

    public void setX(int binario) {
        this.X = binario;
    }
    
    public int getX() {
    	return this.X;
    }
    
    public int getY() {
    	return this.Y;
    }

    public int getResultado(String tipoOperacao) {
    	if (tipoOperacao == "soma") {
            return X + Y;
    	} else {
    		return 0;
    	}
    }
}