package telran.text;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.function.BinaryOperator;

import javax.sound.sampled.BooleanControl;

import org.hamcrest.StringDescription;

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

		return String.format("%1$s(%2$s%1$s)*",operandRE, operatorRE);
	}
	public static String operator() {
		return "\\s*([-+*/])\\s*";
	}
	//10, 10.1, 10.0, .1, 10., 0.1, 10.12340000) or Java variable 
	public static String operand() {
		//assumption: not unary operators
		return String.format("(%s|%s|%s)", operandDoubleWithDotRequired(), operandDoubleWithOutDotRequired(), javaVariableName());
	}
	
	public static String operandDoubleWithDotRequired() {
		//assumption: not unary operators
		return "(\\d+\\.\\d*|\\.\\d+)";
	}
	public static String operandDoubleWithOutDotRequired() {
		return "(\\d+)";
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

		boolean areAllWithOutDot = operands[0].matches(operandDoubleWithOutDotRequired());
		
		for(int i = 1; i < operands.length; i++) {
			res = mapOperations.get(operators[i]).apply(res, getDoubleOperandFromString(operands[i], mapVariables));
			if(areAllWithOutDot) {
				areAllWithOutDot = operands[i].matches(operandDoubleWithOutDotRequired());
			}
		}	
		if(areAllWithOutDot) {
			throw new IllegalArgumentException("Wrong arithmetic expression (there is no double value");
		}
		return res;
	}

	private static double getDoubleOperandFromString(String operandString, HashMap<String, Double> mapVariables) {
		Double operandDouble;
		
		if(operandString.matches(javaVariableName())) {
			if ((operandDouble = mapVariables.get(operandString)) == null) { 
				throw new NoSuchElementException("No such variable");
			}
		} else {
			operandDouble = Double.parseDouble(operandString);
		}
		return operandDouble;
	}
}

