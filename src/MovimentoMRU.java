public class MovimentoMRU {
    private double s0;
    private double v;

    public MovimentoMRU(double s0, double v) {
        this.s0 = s0;
        this.v = v;
    }

    public double getS0() {
        return s0;
    }

    public void setS0(double s0) {
        this.s0 = s0;
    }

    public double getV() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
    }

    public double calcularPosicao(double t) {
        return s0 + v * t;
    }
}