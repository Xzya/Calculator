package calculator.xzya.ro.calculator;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.LinkedList;


public class MainActivity extends ActionBarActivity {

    private android.support.v7.widget.Toolbar toolbar;
    private TextView expression;
    private TextView result;
    private TextView one;
    private TextView two;
    private TextView three;
    private TextView four;
    private TextView five;
    private TextView six;
    private TextView seven;
    private TextView eight;
    private TextView nine;
    private TextView zero;
    private TextView plus;
    private TextView minus;
    private TextView multiply;
    private TextView divide;
    private TextView pow;
    private TextView square_root;
    private TextView factorial;
    private TextView dot;
    private TextView left_parentheses;
    private TextView right_parentheses;
    private TextView backspace;
    private TextView erase;
    private TextView equals;
    private TextView answer;
    private TextView change_sign;
    private TextView shift;
    private TextView sin;
    private TextView cos;
    private TextView tan;
    private TextView log;

    private Postfix postfix;
    private String ans;
    private LinkedList<String> input;
    private boolean shiftPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);

        postfix = new Postfix();
        input = new LinkedList<String>();

        expression = (TextView) findViewById(R.id.expression);
        result = (TextView) findViewById(R.id.result);
        one = (TextView) findViewById(R.id.one);
        two = (TextView) findViewById(R.id.two);
        three = (TextView) findViewById(R.id.three);
        four = (TextView) findViewById(R.id.four);
        five = (TextView) findViewById(R.id.five);
        six = (TextView) findViewById(R.id.six);
        seven = (TextView) findViewById(R.id.seven);
        eight = (TextView) findViewById(R.id.eight);
        nine = (TextView) findViewById(R.id.nine);
        zero = (TextView) findViewById(R.id.zero);
        plus = (TextView) findViewById(R.id.plus);
        minus = (TextView) findViewById(R.id.subtract);
        multiply = (TextView) findViewById(R.id.multiply);
        divide = (TextView) findViewById(R.id.divide);
        pow = (TextView) findViewById(R.id.pow);
        square_root = (TextView) findViewById(R.id.square_root);
        factorial = (TextView) findViewById(R.id.factorial);
        dot = (TextView) findViewById(R.id.dot);
        backspace = (TextView) findViewById(R.id.backspace);
        erase = (TextView) findViewById(R.id.erase);
        equals = (TextView) findViewById(R.id.equals);
        change_sign = (TextView) findViewById(R.id.change_sign);
        answer = (TextView) findViewById(R.id.answer);
        shift = (TextView) findViewById(R.id.shift);
        sin = (TextView) findViewById(R.id.sin);
        cos = (TextView) findViewById(R.id.cos);
        tan = (TextView) findViewById(R.id.tan);
        log = (TextView) findViewById(R.id.log);
        left_parentheses = (TextView) findViewById(R.id.left_parentheses);
        right_parentheses = (TextView) findViewById(R.id.right_parentheses);

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("1");
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("2");
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("3");
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("4");
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("5");
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("6");
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("7");
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("8");
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("9");
            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("0");
            }
        });

        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!input.isEmpty()) {
                    if (!input.peekLast().contains(".")){
                        addElement(".");
                    }
                } else {
                    addElement(".");
                }
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("+");
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("-");
            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("*");
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("/");
            }
        });

        pow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("^");
            }
        });

        square_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFunctionHelper("sqrt");
            }
        });

        factorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("!");
            }
        });

        left_parentheses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("(");
            }
        });

        right_parentheses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement(")");
            }
        });

        erase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.clear();
                expression.setText("");
                result.setText("");
            }
        });

        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!input.isEmpty()) {
                    if (Checker.isNumeric(input.peekLast())) {
                        String temp = input.removeLast();
                        temp = temp.substring(0, temp.length() - 1);
                        if (!temp.isEmpty()) {
                            input.addLast(temp);
                        }
                    } else {
                        input.removeLast();
                    }
                    StringBuilder tempText = new StringBuilder();
                    for (String s : input) {
                        tempText.append(s);
                    }
                    expression.setText(tempText.toString());
                }
            }
        });

        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ans != null) {
                    if (!input.isEmpty()) {
                        if (!Checker.isNumeric(input.peekLast())) {
                            input.addLast(ans);
                            setExpressionText();
                        }
                    } else {
                        input.addLast(ans);
                        setExpressionText();
                    }
                }
            }
        });

        change_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!input.isEmpty() && Checker.isNumeric(input.peekLast())) {
                    Double temp = Double.parseDouble(input.removeLast());
                    temp *= -1;
                    input.addLast(temp.toString());
                    setExpressionText();
                }
            }
        });

        shift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shiftPressed == false) {
                    shiftPressed = true;

                    sin.setText(R.string.sin2);
                    cos.setText(R.string.cos2);
                    tan.setText(R.string.tan2);
                    log.setText(R.string.log2);

                } else {
                    shiftPressed = false;

                    sin.setText(R.string.sin1);
                    cos.setText(R.string.cos1);
                    tan.setText(R.string.tan1);
                    log.setText(R.string.log1);
                }
            }
        });

        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shiftPressed) {
                    addFunctionHelper("asin");
                } else {
                    addFunctionHelper("sin");
                }
            }
        });

        cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shiftPressed) {
                    addFunctionHelper("acos");
                } else {
                    addFunctionHelper("cos");
                }
            }
        });

        tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shiftPressed) {
                    addFunctionHelper("atan");
                } else {
                    addFunctionHelper("tan");
                }
            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shiftPressed) {
                    addFunctionHelper("log2");
                } else {
                    addFunctionHelper("log");
                }
            }
        });

        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!input.isEmpty()) {
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            final String tempResult = postfix.evaluate(input);
                            if (!tempResult.equals("ERROR") && !tempResult.equals("NaN")) {
                                ans = tempResult.toString();
                            }

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    result.setText(tempResult);
                                }
                            });
                        }
                    });
                    t.start();
                }
            }
        });


    }

    private void addFunctionHelper(String element){
        if (!input.isEmpty()){
            if (Checker.isNumeric(input.peekLast()) || input.peekLast().equals(")")){
                addElement("*");
                addElement(element);
                addElement("(");
            } else {
                addElement(element);
                addElement("(");
            }
        } else {
            addElement(element);
            addElement("(");
        }
    }

    private void addElement(final String element) {
        if (!input.isEmpty()) {
            if ((Checker.isNumeric(element) || element.equals(".")) && (Checker.isNumeric(input.peekLast()) || input.peekLast().equals("."))) {
                String token = input.removeLast();
                token += element;
                input.addLast(token);
            } else if (Checker.isOperator(element) && Checker.isOperator(input.peekLast())) {
                input.removeLast();
                input.addLast(element);
            } else {
                input.addLast(element);
            }
        } else {
            input.addLast(element);
        }

        setExpressionText();
    }

    private void setExpressionText() {
        StringBuilder text = new StringBuilder();
        for (String s : input) {
            text.append(s);
        }
        expression.setText(text.toString());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
