import java.util.Arrays;

public class WPM {
    private double[][] matrix;
    private double[] w;

    public WPM(double[][] x, double[] w) {
        matrix = new double[x.length][x[0].length];
        this.w = new double[x[0].length];
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++) {
                matrix[i][j] = x[i][j];
            }
            this.w[i] = w[i];
        }
    }

    public void execute() {
        normalize();
        double[] a = new double[matrix.length];
        Arrays.fill(a, 1);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                a[i] *= Math.pow(matrix[i][j], w[j]);
            }
        }

        Main.makeTopList(a);
    }

    private void normalize() {
        double max;
        for (int j = 0; j < matrix[0].length; j++) {
            max = -1;
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][j] > max)
                    max = matrix[i][j];
            }

            for (int i = 0; i < matrix.length; i++) {
                matrix[i][j] /= max;
            }
        }
    }


}
