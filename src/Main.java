import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        double[][] x = {
                {0.1, 0.1, 0.1, 0.1, 0.1, 0.7, 0.1},
                {0.8, 0.85, 0.7, 0.9, 0.8, 0.8, 0.3},
                {0.82, 0.92, 0.85, 0.9, 0.98, 0.9, 0.2},
                {0.87, 0.9, 0.9, 0.9, 0.97, 0.92, 0.2},
                {0.89, 0.92, 0.83, 0.95, 0.9, 0.87, 0.4}
        };
        double[] w = {0.15, 0.2, 0.2, 0.25, 0.1, 0.05, 0.05};

        System.out.println("TOPSIS");
        new Topsis(x, w).execute();

        System.out.println("\n\nSAW");
        new SAW(x, w).execute();

        System.out.println("\n\nWPM");
        new WPM(x, w).execute();
    }

    public static void makeTopList(double[] result) {
        double max;
        int maxInd;
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 0; i < result.length; i++) {
            max = -1;
            maxInd = -1;
            for(int j = 0; j < result.length; j++) {
                if (result[j] > max && !arr.contains(j)) {
                    max = result[j];
                    maxInd = j;
                }
            }
            arr.add(maxInd);
        }

        System.out.println("Top:");
        for (Integer elem: arr)
            System.out.print(elem + 1 + " ");
    }
}
