package dk.dtu.brainfuckcomp;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Brainfuck bf = new Brainfuck(7);
		
		String str;
		Scanner sc = new Scanner(System.in);
		
        System.out.println("Enter Brainfuck (Leave blank for \"Hello World!\"):");
		str = sc.nextLine();
		if (str.equals("")) {
			str = "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.";
		}
		
		sc.close();
				
		try {
			bf.parse(str);
		} catch (UnknownSymbolExeption e) {
			e.printStackTrace();
		}
		
		System.out.println(bf.toString());
		
		
	}
	
	
	
}
