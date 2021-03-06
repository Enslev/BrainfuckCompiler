package dk.dtu.brainfuckcomp;

import java.util.Scanner;

public class Brainfuck {
	
	private int[] arr;
	private int lenght;
	private int pointer;
	private Scanner sc = new Scanner(System.in);
	
	public Brainfuck(int n) {
		pointer = 0;
		lenght = n;
		arr = new int[n];
	}
	
	public void parse(String str) throws UnknownSymbolExeption {
		parseHelper(str, false);
	}
	
	private void parseHelper(String str, boolean loop) throws UnknownSymbolExeption {
		if (loop) {
			while (arr[pointer] != 0) {
				parseString(str);
			}			
		} else {
			parseString(str);
		}
	}
	
	private void parseString(String str) throws UnknownSymbolExeption {
		int loopMode = 0;
		String loopString = "";
		for (char a: str.toCharArray()) {
			if (loopMode>0 && (loopMode != 1 || a != ']')) {
				loopString += a;
			}
			
			if (a == '[') {
				loopMode++;
				continue;
			}
			
			if (a == ']') {
				loopMode--;
			}
			
			if (loopString.length() > 0 && loopMode == 0) {
				parseHelper(loopString, true);
				loopString = "";
				continue;
			}
			
			if (loopMode == 0) {
				parseChar(a);
			}
		}
	}
	
	private void parseChar(char a) throws UnknownSymbolExeption {
		switch (a) {
		case '>': 	incPointer();
					break;
		case '<':	decPointer(); 	
					break;
		case '+': 	incValue();
					break;
		case '-': 	decValue();
					break;
		case '.': 	printCurr();
					break;
		case ',': 	inputChar();
					break;
		default: 	throw new UnknownSymbolExeption();
		}
	}
	private void incValue() {
		arr[pointer] += 1;
	}
	
	private void decValue() {
		arr[pointer] -= 1;
	}
	
	private void incPointer() {
   		pointer += 1;
   		if (pointer >= lenght) {
   			throw new NullPointerException();
   		}
	} 
	
	private void decPointer() {
   		pointer -= 1;
   		if (pointer < 0) {
   			throw new NullPointerException();
   		}
	} 
	
	private void printCurr() {
		System.out.print((char) arr[pointer]);
	}

	private void inputChar() {
        System.out.println("Enter a Character:");
        String inp = sc.nextLine();
        char[] charArr = inp.toCharArray();
        if (charArr.length == 0) {
        	arr[pointer] = 10; // newline
        	return;
        }

        arr[pointer] = charArr[0];
	}
	
	public String toString() {
		String str = "\n[";
		
		for (int n: arr) {
			str += " " + n + " ";
		}
		
		str += "]\n  ";
		
		for (int i = 0; i < pointer; i++) {
			str += "  ";
			for (int j = 0; j < String.valueOf(arr[i]).length(); j++) {
				str += " ";
			}
		}
		return str += "^";
	}
 
}
