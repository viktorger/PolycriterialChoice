import java.util.ArrayList;

public class Topsis {
    private final double[][] matrix;
    private double[] worst;
    private double[] best;

    // including weights
    public Topsis(double[][] x, double[] w) {
        matrix = new double[x.length][x[0].length];
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++) {
                matrix[i][j] = x[i][j] * w[j];
            }
        }
    }

    public void execute() {
        normalize();
        findWorst();
        findBest();
        findSquaredDistances();
    }

    private void normalize() {
        double squaredSum = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                squaredSum += matrix[i][j] * matrix[i][j];
            }
        }
        double avgSqrSum = Math.sqrt(squaredSum);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] /= avgSqrSum;
            }
        }
    }

    private void findWorst() {
        worst = new double[matrix[0].length];
        double min;
        for (int j = 0; j < matrix[0].length; j++) {
            min = matrix[0][j];
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][j] < min)
                    min = matrix[i][j];
            }
            worst[j] = min;
        }
    }

    private void findBest() {
        best = new double[matrix[0].length];
        double max;
        for (int j = 0; j < matrix[0].length; j++) {
            max = matrix[0][j];
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][j] > max)
                    max = matrix[i][j];
            }
            best[j] = max;
        }
    }

    private void findSquaredDistances() {
        double[] avgSqrDistanceBest = new double[matrix.length];
        double[] avgSqrDistanceWorst = new double[matrix.length];

        for (int i = 0; i < avgSqrDistanceBest.length; i++) {
            avgSqrDistanceBest[i] = 0;
            avgSqrDistanceWorst[i] = 0;
        }

        for(int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                avgSqrDistanceBest[i] += Math.pow(matrix[i][j] - best[j], 2);
                avgSqrDistanceWorst[i] += Math.pow(matrix[i][j] - worst[j], 2);
            }
            avgSqrDistanceBest[i] = Math.sqrt(avgSqrDistanceBest[i]);
            avgSqrDistanceWorst[i] = Math.sqrt(avgSqrDistanceWorst[i]);


        }

        double[] result = new double[matrix.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = avgSqrDistanceWorst[i] / (avgSqrDistanceBest[i] + avgSqrDistanceWorst[i]);
        }

        Main.makeTopList(result);
    }
}
