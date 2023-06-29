package telran.text;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.function.BinaryOperator;

public class Strings {
	static HashMap<String, BinaryOperator<Double>> mapOperations;
	static {
		mapOperations = new HashMap<>();
		mapOperations.put("-", (a, b) -> a - b);
		mapOperations.put("+", (a, b) -> a + b);
		mapOperations.put("*", (a, b) -> a * b);
		mapOperations.put("/", (a, b) -> a / b);
	}
	public static String javaVariableName() {

		return "([a-zA-Z$][\\w$]*|_[\\w$]+)";
	}
	public static String zero_300() {

		return "[1-9]\\d?|[1-2]\\d\\d|300|0";
	}
	public static String ipV4Octet() {
		//TODO
		//positive number from 0 to 255 and leading zeros are allowed
		return  "([01]?\\d\\d?|2([0-4]\\d|5[0-5]))";
	}
	public static String ipV4() {
		String octetRegex = ipV4Octet();
		return String.format("(%s\\.){3}%1$s",octetRegex);
	}

	public static String arithmeticExpression() {
		String operandRE = operand();
		String operatorRE = operator();
		return String.format("%1$s(%2$s%1$s)*",operandRE, operatorRE );
	}
	public static String operator() {
		return "\\s*([-+*/])\\s*";
	}
	//0, 10.1, 10.0, .1, 10., 0.1, 10.12340000) or Java variable 
	public static String operand() {
		//assumption: not unary operators
		return "(\\d+\\.\\d*|\\.\\d+|0|" +javaVariableName()+ ")";
	}


	public static boolean isArithmeticExpression(String expression) {
		expression = expression.trim();
		return expression.matches(arithmeticExpression());
	}

	public static double computeExpression(String expression, HashMap<String, Double> mapVariables) {

		if (!isArithmeticExpression(expression)) {
			throw new IllegalArgumentException("Wrong arithmetic expression");
		}

		expression = expression.replaceAll("\\s+", "");
		String[] operands = expression.split(operator());
		String [] operators = expression.split(operand());
		double res = getDoubleOperandFromString(operands[0], mapVariables);

		for(int i = 1; i < operands.length; i++) {
			res = mapOperations.get(operators[i]).apply(res, getDoubleOperandFromString(operands[i], mapVariables));
		}	
		return res;
	}

	private static double getDoubleOperandFromString(String operandString, HashMap<String, Double> mapVariables) {
		double operandDouble;
		
		if(operandString.matches(javaVariableName())) {
			if (!mapVariables.containsKey(operandString)) {
				throw new NoSuchElementException("No such variable");
			}
			operandDouble = mapVariables.get(operandString);
		} else {
			operandDouble = Double.parseDouble(operandString);
		}
		return operandDouble;
	}
}
