package florida.es.simplecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Character.isDigit;

/**
 * Activity class that implements a simple calculator with two single digit numbers
 * e.g. (2+1), (4+5). Only a single operator is admitted in the calculation. The result of the
 * calculation is shown when the user clicks on the equal symbol
 */
public class Calculator extends AppCompatActivity implements View.OnClickListener {

    /**
     * Method employed to perform the math operations supported by the calculator
     * @param firstOperand The first number to be part of the operation (left operand)
     * @param secondOperand The second number to be part of the operation (right operand)
     * @param operator The type of operation to be carried out. Supported types: 'x', '%', '+', '-'
     * @return A decimal number as a result of carrying out the mathematical operation
     * @throws <tt>ArithmeticException</tt> if the operator selected is not permitted or if attempting to divide by zero
     */
    protected double calculate(int firstOperand, int secondOperand, char operator){
        //We use a switch operator to decide on the operation to be carried out
        switch(operator){
            case 'x' :
                return (double)firstOperand * secondOperand;
            case '%' :
                if(secondOperand == 0){ //Check for special case, when dividing by zero
                    throw new ArithmeticException("Attempted to divide by zero, which is not supported by the calculator");
                }
                return (double) firstOperand / secondOperand;
            case '+' :
                return firstOperand + secondOperand;
            case '-' :
                return firstOperand - secondOperand;
            default :
                throw new ArithmeticException("Operator " + operator + " not supported by the calculator");
        }
    }

    /**
     * Static array that contains the ids of the buttons in the calculator. Useful for iterating
     */
    protected static int[] mCalculatorButtonsIds = {R.id.bt_key_one, R.id.bt_key_two, R.id.bt_key_three,
            R.id.bt_key_four, R.id.bt_key_five, R.id.bt_key_six, R.id.bt_key_seven, R.id.bt_key_eight,
            R.id.bt_key_nine, R.id.bt_key_zero, R.id.bt_key_divide, R.id.bt_key_multiply, R.id.bt_key_plus,
            R.id.bt_key_minus, R.id.bt_key_equal, R.id.bt_key_clear};

    /**
     * Map used to organize the calculator buttons in a fast access and organized data structure
     */
    protected Map<Integer,Button> mCalculatorButtonsMap = null;

    /**
     * Text view employed to get a reference to the calculator's display (screen used to show operations
     * and results)
     */
    protected TextView mCalculatorDisplay = null;

    /**
     * Attributes employed to keep track of the numbers to operate with and the operation
     */
    protected Integer firstOperand = null;
    protected Integer secondOperand = null;
    protected Character operator = null;
    // Contador para registrar la posición del operator dentro del String
    protected Integer contador = null;
    /**
     * Method that gets a reference for each of the buttons in the calculator, and puts them
     * in a fast access map.
     */
    protected void getReferenceCalculatorButtons(){
        mCalculatorButtonsMap = new HashMap<Integer,Button>();
        for(int id : mCalculatorButtonsIds){
            Button calculatorButton = findViewById(id);
            mCalculatorButtonsMap.put(id,calculatorButton);
        }
    }

    // Método para comprobar si comprobar si una cadena de texto es un número entero
    public boolean isNumeric (String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        }catch (NumberFormatException nfe) {
            return false;
        }
    }
    /**
     * Method that is called whenever Android (re)creates the activity. It should be used to
     * populate the graphical interface.
     * @param savedInstanceState Bundle object that contains pairs of (key,value). It is passed
     * when the activity is recreated (e.g., changed orientation) to recover the previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Fill the activity with the contents specified in the XML layout file
        setContentView(R.layout.activity_calculator);

        //Get a reference to each of the buttons
        getReferenceCalculatorButtons();

        //Get a reference to the calculator display
        mCalculatorDisplay = findViewById(R.id.tv_calculator_display);
        //mCalculatorDisplay.setText("Calculadora");
        //mCalculatorDisplay.setText(mCalculatorButtonsMap.get(1).toString());

        /* Asignamos un onClickListener a los botones de la calculadora. Lo hacemos a través de
        *  un bucle for each con el método keySet*/
        for (int id : mCalculatorButtonsMap.keySet()) {
            findViewById(id).setOnClickListener(this);
        }

    }

    // Implementación del método onClick
    @Override
    public void onClick(View v) {

        // Capturamos el Id del View (vista) que ha generado el evento click y la asignamos al int viewId
        int viewId = v.getId();
        // Creamos un String que recibirá el texto (valor) del botón (vista) que hemos pulsado
        String textButton = mCalculatorButtonsMap.get(viewId).getText().toString();

        //VERSION CON SWITCH
        switch (textButton){

            // Si el usuario pulsa un DÍGITO
            case "1": case "2": case "3": case "4": case "5": case "6": case "7": case "8": case "9": case "0":
                // Comprobamos que el display no contiene la cadena "No" en caso que la contenga no hacemos nada, el usuario debe pulsar C para continuar
                if (mCalculatorDisplay.getText().toString().contains("No")){
                }
                // En el resto de casos agregamos el dígito pulsado al display
                else{
                    mCalculatorDisplay.append(textButton);
                }
                break;

            // Si el usuario pulsa un OPERADOR
            case "-": case "+" : case "x" : case "%" :
                // Si no hay ningún operador previo en el display y la cadena del display es un número entero:
                if (!mCalculatorDisplay.getText().toString().matches("[-+x%]") && isNumeric(mCalculatorDisplay.getText().toString())) {
                    // Asignamos al primer operador el entero de la cadena que hay en el display
                    firstOperand = Integer.parseInt(mCalculatorDisplay.getText().toString());
                    // Asignamos al segundo operador el último caracter recogido en la cadena textButton
                    operator = textButton.charAt(textButton.length()-1);
                    // Agregamos el operador a la cadena
                    mCalculatorDisplay.append(textButton);
                    // Asignamos a contador el valor de la longitud de la cadena actual
                    contador = mCalculatorDisplay.getText().length();
                }
                break;

            // Si el usuario pulsa IGUAL
            case "=" :
                // Si hay primer operando y operador pero el segundo operando es nulo
                if (firstOperand !=null && operator != null && secondOperand == null){
                    // En el caso de que el operador sea %, y la cadena desde el operador hasta el final sea sea un número y sea 0, mostramos el mensaje en el diplay
                    if (operator == '%' && isNumeric(mCalculatorDisplay.getText().subSequence(contador, mCalculatorDisplay.length()).toString())
                            && Integer.parseInt(mCalculatorDisplay.getText().subSequence(contador, mCalculatorDisplay.length()).toString()) == 0){
                                mCalculatorDisplay.setText("No puede dividir por '0'. C para continuar");
                    }
                    // En el caso de que la cadena desde el operador hasta el final sea un número entero:
                    else if (isNumeric(mCalculatorDisplay.getText().subSequence(contador, mCalculatorDisplay.length()).toString())){
                        // Asignamos la cadena al segundo operador
                        secondOperand = Integer.parseInt(mCalculatorDisplay.getText().subSequence(contador, mCalculatorDisplay.length()).toString());
                        // Pasamos el resultado del método calculate al display
                        mCalculatorDisplay.setText(Integer.toString((int)calculate(firstOperand, secondOperand, operator)));
                        // Reseteamos el segundo operador para poder trabajar con el resultado como primer operador
                        secondOperand = null;
                    }
                }
                break;

            // Si el usuario pulsa BOTÓN C lo reseteamos todo
            case "C" :
                mCalculatorDisplay.setText("");
                firstOperand = null;
                secondOperand = null;
                operator = null;
                if(contador != 0)
                    contador = 0;
                break;
        }
    }
}
