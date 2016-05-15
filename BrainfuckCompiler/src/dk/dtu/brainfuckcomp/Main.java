package dk.dtu.brainfuckcomp;

public class Main {
	
	
	public static void main(String[] args) {
		Brainfuck bf = new Brainfuck(7);
		
		String str = "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.";
				
		try {
			bf.parse(str);
		} catch (UnknownSymbolExeption e) {
			e.printStackTrace();
		}
		
		System.out.println(bf.toString());
		
		
	}
	
	
	
}
