
    public class MovimentoMRUV {
        private double s0;
        private double v0;
        private double a;

        public MovimentoMRUV(double s0, double v0, double a) {
            this.s0 = s0;
            this.v0 = v0;
            this.a = a;
        }

        public double getS0() {
            return s0;
        }

        public void setS0(double s0) {
            this.s0 = s0;
        }

        public double getV0() {
            return v0;
        }

        public void setV0(double v0) {
            this.v0 = v0;
        }

        public double getA() {
            return a;
        }

        public void setA(double a) {
            this.a = a;
        }

        public double calcularPosicao(double t) {
            return s0 + v0 * t + (a * t * t) / 2.0;
        }
    }

