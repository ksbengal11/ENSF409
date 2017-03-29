package ensf409.androidcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;

/**
 * Handles button presses and input errors
 * @author  Karan Bengali and Naveed Kawsar
 * @since   March 28, 2017
 * @version 1.0
 */
public class CalculatorActivity extends Activity {
    /**
     * valueOne - first value of the arithmetic operation
     * valueTwo - second value of the arithmetic operation
     */
    float valueOne, valueTwo;

    /**
     * Variable indicating the type of arithmetic operation (i.e + - / *)
     */
    char operation;

    /**
     * Prevents users from pressing the operations, equals, and dot button twice
     * operationFlag - True if any of the operation buttons have been pressed
     * equalFlag - True if equal button has been pressed
     * dotFlag - True if dot button has been pressed
     */
    boolean operationFlag, equalFlag, dotFlag;

    /**
     * Object calculator which computes the result of our arithmetic operation
     */
    Calculator calc = new Calculator();

    /**
     * Stores user entry
     */
    String displayText;

    /**
     * Displays arithmetic expression and error messages
     */
    TextView message;

    /**
     * Displays user entry and computed result
     */
    EditText edt1;

    /**
     * Buttons for dial pad and arithmetic operations
     */
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,
           btnDOT, btnADD,btnSUB,btnMUL, btnDIV, btnEqual, btnClear;

    /**
     * Display the calculator gui. Ties button, text view, and edit text
     * variables to their respective ids.
     * @param savedInstanceState Reference to the Bundle object that
     *                           is passed into the onCreate method
     *                           of every Android activity
     */
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_calculator);

        operationFlag = false;
        equalFlag = false;
        dotFlag = false;

        message     = (TextView) findViewById(R.id.txt1);
        edt1        = (EditText) findViewById(R.id.edt1);
        btn0        = (Button) findViewById(R.id.button0);
        btn1        = (Button) findViewById(R.id.button1);
        btn2        = (Button) findViewById(R.id.button2);
        btn3        = (Button) findViewById(R.id.button3);
        btn4        = (Button) findViewById(R.id.button4);
        btn5        = (Button) findViewById(R.id.button5);
        btn6        = (Button) findViewById(R.id.button6);
        btn7        = (Button) findViewById(R.id.button7);
        btn8        = (Button) findViewById(R.id.button8);
        btn9        = (Button) findViewById(R.id.button9);
        btnADD      = (Button) findViewById(R.id.buttonAdd);
        btnSUB      = (Button) findViewById(R.id.buttonSub);
        btnMUL      = (Button) findViewById(R.id.buttonMult);
        btnDIV      = (Button) findViewById(R.id.buttonDiv);
        btnEqual    = (Button) findViewById(R.id.buttonEqual);
        btnDOT      = (Button) findViewById(R.id.buttonDot);
        btnClear    = (Button) findViewById(R.id.buttonClear);

        addListeners();
    }

    /**
     * Clears the display area and sets arithmetic operation
     * variables to zero
     */
    public void reset(){
        edt1.setText("");
        valueOne = 0;
        valueTwo = 0;
        operationFlag = false;
    }

    /**
     * Displays error message if an invalid activity is caught
     */
    public void errorMessage(){
        displayText = "Invalid input "+ edt1.getText()+""+". Renter numbers";
        message.setText(displayText);
    }

    /**
     * Parses the display area for the first value of the arithmetic
     * operation.
     * Prints and error if an invalid entry is detected
     */
    public void displayOperation(){
        if(!operationFlag){
            String inputMessage = edt1.getText() + "";
            dotFlag = false;
            if(isFloat(inputMessage)){
                valueOne = Float.parseFloat(edt1.getText() + "");
                operationFlag = true;
                edt1.setText("");
                message.setText(inputMessage + " " + String.valueOf(operation));
                equalFlag = false;
            }else{
                errorMessage();
                reset();
            }
        }else{
            errorMessage();
            reset();
        }
    }

    /**
     * Checks if the input is a float
     * @param   s input string
     * @return  true if the input is a float, false otherwise
     */
    public boolean isFloat(String s){
        try{
            Float.parseFloat(s);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    /**
     * Button activity listeners. Displays user input and computation result.
     */
    public void addListeners(){
        btn0.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                displayText = edt1.getText()+"0";
                edt1.setText(displayText);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                displayText = edt1.getText()+"1";
                edt1.setText(displayText);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                displayText = edt1.getText()+"2";
                edt1.setText(displayText);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                displayText = edt1.getText()+"3";
                edt1.setText(displayText);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                displayText = edt1.getText()+"4";
                edt1.setText(displayText);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                displayText = edt1.getText()+"5";
                edt1.setText(displayText);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                displayText = edt1.getText()+"6";
                edt1.setText(displayText);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                displayText = edt1.getText()+"7";
                edt1.setText(displayText);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                displayText = edt1.getText()+"8";
                edt1.setText(displayText);
            }
        });
        btn9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                displayText = edt1.getText()+"9";
                edt1.setText(displayText);
            }
        });
        btnDOT.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!dotFlag){
                    displayText = edt1.getText()+".";
                    edt1.setText(displayText);
                    dotFlag = true;
                }else{
                    message.setText("Invalid input " + edt1.getText()+ "" +". Renter numbers");
                    edt1.setText("");
                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                edt1.setText("");
                valueOne = 0;
                valueTwo = 0;
                operationFlag = false;
                equalFlag = false;
                dotFlag = false;
                message.setText("");
            }
        });
        btnADD.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                operation = '+';
                displayOperation();
            }
        });
        btnMUL.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                operation = '*';
                displayOperation();
            }
        });
        btnDIV.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                operation = '/';
                displayOperation();
            }
        });
        btnSUB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                operation = '-';
                displayOperation();
            }
        });
        btnEqual.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!equalFlag){
                    operationFlag = false;
                    String inputMessage = edt1.getText() + "";
                    displayText = " " + inputMessage;
                    message.setText(displayText);
                    if(isFloat(inputMessage)){
                        valueTwo = Float.parseFloat(inputMessage);
                        valueOne = calc.calculateResult(valueOne, valueTwo, operation);
                        edt1.setText(String.valueOf(valueOne));
                        message.setText(String.valueOf(valueOne));
                        equalFlag = true;
                    }else{
                        errorMessage();
                        reset();
                    }
                }else{
                    errorMessage();
                    reset();
                }
            }
        });
    }
}
