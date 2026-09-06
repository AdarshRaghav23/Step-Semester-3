import java.util.*;

public class BMICalculator {
    static String getBmiStatus(double bmi) {
        if (bmi < 18.5) return "Underweight";
        if (bmi < 25) return "Normal";
        if (bmi < 30) return "Overweight";
        return "Obese";
    }

    static void printReport(double[] h, double[] w) {
        for (int i = 0; i < h.length; i++) {
            double bmi = w[i] / (h[i] * h[i]);
            System.out.printf("Person %d | Height: %.2f | Weight: %.1f | BMI: %.2f | %s%n",
                    i + 1, h[i], w[i], bmi, getBmiStatus(bmi));
        }
    }

    public static void main(String[] args) {
        double[] height = {1.75, 1.60, 1.70};
        double[] weight = {70, 90, 65};

        printReport(height, weight);
    }
}