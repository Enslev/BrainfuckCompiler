package dk.dtu.brainfuckcomp;

import java.util.Scanner;

public class Main {

	private static final String HELLO = "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.";

	public static void main(String[] args) {
		int n;
		if (args.length == 0) {
			System.out.println("Length of Brainfuck array not specified. Defaulting to 8.");
			n = 8;
		} else {
			n = Integer.parseInt(args[0]);
		}
		
		Brainfuck bf = new Brainfuck(n);
		Scanner sc = new Scanner(System.in);
		
        System.out.println("Enter Brainfuck (Leave blank for \"Hello World!\"):");
        String str = sc.nextLine();
		if (str.equals("")) {
			str = HELLO;
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
