package ensf409.androidcalculator;

/**
 * Computes the output of a given arithmetic expression
 * @author  Karan Bengali and Naveed Kawsar
 * @since   March 28, 2017
 * @version 1.0
 */
public class Calculator implements  AritmeticOperation{
    /**
     *
     * @param numOne    first number of the arithmetic expression
     * @param numTwo    second number of the arithmetic expression
     * @param operation arithmetic operation to be computed
     * @return          result of the computed arithmetic expression
     */
    float calculateResult(float numOne, float numTwo, char operation){
        float result;
        switch (operation){
            case ADDITION:
                result = numOne + numTwo;
                break;
            case SUBTRACTION:
                result = numOne - numTwo;
                break;
            case MULTIPLICATION:
                result = numOne*numTwo;
                break;
            case DIVISION:
                if(numTwo == 0) return 0;
                result = numOne/numTwo;
                break;
            default:
                result = 0;
        }
        return result;
    }
}
