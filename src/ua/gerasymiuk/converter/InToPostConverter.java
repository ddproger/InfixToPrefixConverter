package ua.gerasymiuk.converter;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by gerasymiuk on 05.10.17.
 */
public class InToPostConverter {
    private char[] infixExpression;
    private Map<Character,Integer> priority = new HashMap<>();
    private  char[] postfixExpression;
    public InToPostConverter(String infixExpression){
        this.infixExpression = infixExpression.toCharArray();
        this.priority.put('+',0);
        this.priority.put('-',0);
        this.priority.put('*',1);
        this.priority.put('/',1);
        this.priority.put('(',2);
    }
    public String getPostfixExpression(){
        int size = infixExpression.length;
        StringBuilder builder = new StringBuilder();
        Stack<Character> operatorStack = new Stack<>();
        for (int i = 0; i < size; i++) {
            char ch = infixExpression[i];
            switch (ch){
                case '+':
                case '-':
                    if(!operatorStack.isEmpty())
                        builder.append(operatorStack.pop());
                    operatorStack.push(ch);
                    break;
                case '*':
                case '/':
                    if(!operatorStack.isEmpty()&&priority.get(operatorStack.peek())==1){
                        builder.append(operatorStack.pop());
                    }else if(!operatorStack.isEmpty()&&operatorStack.peek()=='('){
                        operatorStack.pop();
                    }
                    operatorStack.push(ch);
                    break;
                case '(': operatorStack.push('('); break;
                case ')':
                    builder.append(operatorStack.pop());
                    break;
                default:
                    //if(ch>='0'&&ch<='9')
                        builder.append(ch);
                    break;
            }

        }
        while (!operatorStack.isEmpty()){
            builder.append(operatorStack.pop());
        }
        String res = builder.toString().replace("(","");
        postfixExpression = res.toCharArray();
        return res;
    }
    public int getResult(){
        Stack<Integer> numbers = new Stack<>();
        if(postfixExpression == null){
            getPostfixExpression();
        }
        int size = postfixExpression.length;
        for (int i = 0; i < size; i++) {
            char ch = postfixExpression[i];
            Integer res = 0;
            try {
                switch (ch) {
                    case '+':
                        res = numbers.pop() + numbers.pop();
                        break;
                    case '-':
                        res = numbers.pop() * (-1) + numbers.pop();
                        break;
                    case '*':
                        res = numbers.pop() * numbers.pop();
                        break;
                    case '/':
                        res = (int) Math.pow(numbers.pop(), -1) * numbers.pop();
                        break;
                    default:
                        res = -1;
                        numbers.push(Integer.parseInt(ch + ""));
                        break;
                }
            }catch (Exception e){
                System.out.println("Wrong format of expression");
                return 0;
            }
            if(res!=-1)numbers.push(res);
        }
        if(numbers.isEmpty())return 0;
        else return numbers.pop();
    }
}
