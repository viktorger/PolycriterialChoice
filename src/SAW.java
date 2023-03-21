public class SAW {
    private double[][] matrix;

    public SAW(double[][] x, double[] w) {
        matrix = new double[x.length][x[0].length];
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++) {
                matrix[i][j] = x[i][j] * w[j];
            }
        }
    }

    public void execute() {
        normalize();
        double[] v = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            v[i] = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                v[i] += matrix[i][j];
            }
        }

        Main.makeTopList(v);
    }

    private void normalize() {
        double max;
        for (int j = 0; j < matrix[0].length; j++) {
            max = -1;
            for (int i = 0; i < matrix.length; i++) {
                if(matrix[i][j] > max)
                    max = matrix[i][j];
            }

            for (int i = 0; i < matrix.length; i++) {
                matrix[i][j] /= max;
            }
        }
    }
}
