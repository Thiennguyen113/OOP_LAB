//Example 2.2.6
import javax.swing.JOptionPane;

public class EquationSolver {
    public static void main(String[] args) {
        String[] options = {"Linear Equation", "System of Equations", "Quadratic Equation"};
        int choice = JOptionPane.showOptionDialog(null, "Choose an option:", "Equation Solver",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                solveLinearEquation();
                break;
            case 1:
                solveLinearSystem();
                break;
            case 2:
                solveQuadraticEquation();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid choice!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void solveLinearEquation() {
        String stra = JOptionPane.showInputDialog(null, "Please input a: ", "Input a", JOptionPane.INFORMATION_MESSAGE);
        String strb = JOptionPane.showInputDialog(null, "Please input b: ", "Input b", JOptionPane.INFORMATION_MESSAGE);

        double a = Double.parseDouble(stra);
        double b = Double.parseDouble(strb);

        if (a == 0) {
            JOptionPane.showMessageDialog(null, (b == 0) ? "Infinite solutions." : "No solution.", "Result", JOptionPane.INFORMATION_MESSAGE);
        } else {
            double x = -b / a;
            JOptionPane.showMessageDialog(null, "Solution: x = " + x, "Result", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void solveLinearSystem() {
        String stra11 = JOptionPane.showInputDialog(null, "Please input a11: ", "Input a11", JOptionPane.INFORMATION_MESSAGE);
        String stra12 = JOptionPane.showInputDialog(null, "Please input a12: ", "Input a12", JOptionPane.INFORMATION_MESSAGE);
        String strb1 = JOptionPane.showInputDialog(null, "Please input b1: ", "Input b1", JOptionPane.INFORMATION_MESSAGE);
        String stra21 = JOptionPane.showInputDialog(null, "Please input a21: ", "Input a21", JOptionPane.INFORMATION_MESSAGE);
        String stra22 = JOptionPane.showInputDialog(null, "Please input a22: ", "Input a22", JOptionPane.INFORMATION_MESSAGE);
        String strb2 = JOptionPane.showInputDialog(null, "Please input b2: ", "Input b2", JOptionPane.INFORMATION_MESSAGE);

        double a11 = Double.parseDouble(stra11);
        double a12 = Double.parseDouble(stra12);
        double b1 = Double.parseDouble(strb1);
        double a21 = Double.parseDouble(stra21);
        double a22 = Double.parseDouble(stra22);
        double b2 = Double.parseDouble(strb2);

        double D = a11 * a22 - a21 * a12;
        double D1 = b1 * a22 - b2 * a12;
        double D2 = a11 * b2 - a21 * b1;

        if (D != 0) {
            double x1 = D1 / D;
            double x2 = D2 / D;
            JOptionPane.showMessageDialog(null, "Solution: x1 = " + x1 + ", x2 = " + x2, "Result", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, (D1 == 0 && D2 == 0) ? "Infinite solutions." : "No solution.", "Result", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void solveQuadraticEquation() {
        String stra = JOptionPane.showInputDialog(null, "Please input a: ", "Input a", JOptionPane.INFORMATION_MESSAGE);
        String strb = JOptionPane.showInputDialog(null, "Please input b: ", "Input b", JOptionPane.INFORMATION_MESSAGE);
        String strc = JOptionPane.showInputDialog(null, "Please input c: ", "Input c", JOptionPane.INFORMATION_MESSAGE);

        double a = Double.parseDouble(stra);
        double b = Double.parseDouble(strb);
        double c = Double.parseDouble(strc);

        if (a == 0) {
            JOptionPane.showMessageDialog(null, "This is not a quadratic equation. Solving as a linear equation.", "Warning", JOptionPane.WARNING_MESSAGE);
            if (b != 0) {
                double x = -c / b;
                JOptionPane.showMessageDialog(null, "Solution: x = " + x, "Result", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, (c == 0) ? "Infinite solutions." : "No solution.", "Result", JOptionPane.INFORMATION_MESSAGE);
            }
            return;
        }

        double delta = b * b - 4 * a * c;

        if (delta > 0) {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            JOptionPane.showMessageDialog(null, "Two distinct solutions: x1 = " + x1 + ", x2 = " + x2, "Result", JOptionPane.INFORMATION_MESSAGE);
        } else if (delta == 0) {
            double x = -b / (2 * a);
            JOptionPane.showMessageDialog(null, "One double root: x = " + x, "Result", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No real solution.", "Result", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
