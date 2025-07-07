package inicio;

import vista.MenuPrincipal;
import vista.swing.VInicial;

public class Main {
	public static void main(String[] args) {
		
		if(args.length > 0 && args[0].equalsIgnoreCase("grafico"))
			new VInicial();
		else
			new MenuPrincipal();
	}
}
