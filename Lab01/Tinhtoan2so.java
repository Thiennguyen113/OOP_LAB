//Example 2.2.5
import javax.swing.JOptionPane;
public class Tinhtoan2so {
    public static void main(String[] args) {
        String strNum1, strNum2;
        String strResults="";
        strNum1 = JOptionPane.showInputDialog(null, "Please input the first number: ","Input the first number",JOptionPane.INFORMATION_MESSAGE);
        strNum2 = JOptionPane.showInputDialog(null, "Please input the second number: ","Input the second number",JOptionPane.INFORMATION_MESSAGE);
        double num1= Double.parseDouble (strNum1);
        double num2= Double.parseDouble (strNum2);
        double sum = num1+num2;
        double difference = num1 - num2;
        double product = num1 * num2;
        strResults+="Sum = "+ sum;
        strResults+="\nDifference = "+ difference;
        strResults+="\nProduct = "+ product;
        if(num2!=0){
            double quotient =num1/num2;
            strResults+="\nQuotient = "+ quotient;
        }
        else{
            strResults+= "\nCannot divide by zero.";
        }
        JOptionPane.showMessageDialog(null, strResults, "Results", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}
