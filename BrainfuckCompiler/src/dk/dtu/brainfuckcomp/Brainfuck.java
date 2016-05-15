package dk.dtu.brainfuckcomp;

import java.io.IOException;

public class Brainfuck {
	
	private int[] arr;
	private int lenght;
	private int pointer;
	
	public Brainfuck(int n) {
		pointer = 0;
		lenght = n;
		arr = new int[n];
	}
	
	public void parse(String str) throws UnknownSymbolExeption {
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
				parseLoop(loopString);
				loopString = "";
				continue;
			}
			
			if (loopMode == 0) {
				parseChar(a);
			}
		}
	}
	
	private void parseLoop(String str) throws UnknownSymbolExeption {
		int loopMode = 0;
		String loopString = "";
		while (arr[pointer] != 0) {
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
					parseLoop(loopString);
					loopString = "";
					continue;
				}
				
				if (loopMode == 0) {
					parseChar(a);
				}
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
        try {
            arr[pointer] = System.in.read();
        }
        catch (IOException e){
            System.out.println("Error reading from user");
        }
	}
	
	public String toString() {
		String str = "[";
		
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
