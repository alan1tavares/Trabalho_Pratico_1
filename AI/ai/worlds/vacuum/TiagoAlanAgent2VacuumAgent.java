package ai.worlds.vacuum;

import java.util.Vector;

public class TiagoAlanAgent2VacuumAgent extends VacuumAgent {
	/**
	 * Agente baseado em modelos
	 */
	
	// Tamanho do ambiente
	private int i;
	private int j;
	
	private boolean sabeI = false;
	private boolean sabeJ = false;
	
	private int[] posicaoAtual = {0,0};
	
	
	@Override
	public void determineAction() {
		Vector p = (Vector) percept;
		//Acao quando encontra sujeira
		if(p.elementAt(1) == "dirt") action = "suck";
		
		//Acao para saber a dimensao i{
		else if (!sabeI){ 
			action = "forward";
			if(p.elementAt(0) == "bump") sabeI = true;
			else if(lastAction == "forward") i++;
			lastAction = "forward";
		}//}Fim das acoes para saber o i
		
		else if(true);
		
		//Acao para sabe a dimensao j{
		else if (!sabeJ){ 
			if(lastAction == "bump") sabeI = true;
			else{
				action = "forward";
				j++;
			}
		}//}Fim das acoes para saber o j
		else{
			System.out.println("Nada");
		}
		System.out.println(i);
		
	}


}
