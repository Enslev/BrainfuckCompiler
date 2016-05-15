package dk.dtu.brainfuckcomp;

public class UnknownSymbolExeption extends Exception {
	private static final long serialVersionUID = 2829108695733179618L;

	public UnknownSymbolExeption() {
        super("Brainfuck symbol was not recognized");
    }
}
