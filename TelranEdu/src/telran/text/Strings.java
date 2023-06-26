package telran.text;

import javax.naming.spi.DirStateFactory.Result;

public class Strings {
public static String javaVariableName() {
	
	return "[a-zA-Z$][\\w$]*|_[\\w$]+";
}
public static String zero_300() {
	
	return "[1-9]\\d?|[1-2]\\d\\d|300|0";
}
public static String ipV4Octet() {
	//positive number from 0 to 255 and leading zeros are allowed
	return "(000|\\d{1,2}|[01]\\d{2}|2[0-4]\\d|25[0-5])";
}
public static String ipV4() {
	//four ipV4 octets separated by dot 123.123.255.01
	String resultString = ipV4Octet();
	for(int i = 1; i <= 3; i ++) {
		resultString += "." + ipV4Octet();
	}
	return resultString;
}

}