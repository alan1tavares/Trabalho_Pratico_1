package ai.worlds.vacuum;

import java.util.Random;
import java.util.Vector;

public class TiagoAlanAgente1VacuumAgent extends VacuumAgent {
	/**
	 * Agente reativo simples
	 */

	@Override
	public void determineAction() {
		Vector p = (Vector) percept;

		// Acao quando enontra sujeira
		if (p.elementAt(1) == "dirt")
			action = "suck";

		// Acao de queando esta na casinha
		else if (p.elementAt(2) == "home") {
			int rand = new Random().nextInt(3);
			switch (rand) {
			case 0: action = "shut-off"; break;
			case 1: action = "turn right"; break;
			case 2:	action = "forward"; break;
			case 3:
				if (score <= -10)
					action = "shut-off";
				else
					action = "forward";
			}
		} // Fim da acao da casinha
		
		// Acao para batida
		else if (p.elementAt(0) == "bump"){
			int rand = new Random().nextInt(1);
			switch (rand) {
			case 0: action = "turn left"; break;
			case 1: action = "turn right";
			}
		}// Fim da acao para batida
		
		// Acao pro resto
		else{
			int rand = new Random().nextInt(6);
			switch (rand) {
			case 0: action = "forward"; break;
			case 1: action = "forward"; break;
			case 2: action = "forward"; break;
			case 3: action = "forward"; break;
			case 4: action = "forward"; break;
			case 5: action = "turn left"; break;
			case 6: action = "turn right";
			} //switch
		} // Fim da acha pro resto
	} // Fim do método
} // Fim da classe
