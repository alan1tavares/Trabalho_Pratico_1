package ai.worlds.vacuum;

import java.util.Vector;

import com.sun.xml.internal.ws.message.AttachmentUnmarshallerImpl;

public class TiagoAlanAgent2VacuumAgent extends VacuumAgent {
	/**
	 * Agente baseado em modelos
	 */

	// Tamanho do ambiente
	private int i = 0;
	private int j = 0;
	
	private int state = 1;
	private int state2 = 0;
	private int state3 = 1;
	private boolean andaUma = false;

	private boolean sabeI = false;
	private boolean sabeJ = false;

	private int[] posicaoAtual = { 1, 1 };

	@Override
	public void determineAction() {
		Vector p = (Vector) percept;
		//Acao quando encontra sujeira
		if(p.elementAt(1) == "dirt") action = "suck";
		
		else if(p.elementAt(0) != "bump" && state != 0 && state3 !=0){
			action = "forward";
			j++;
		}
		else if(p.elementAt(0)=="bump" && state != 0){
			state = 0;
			
		}
		else if(state==0 && i%2==0 && state2==0){
			action = "turn left";
			i++;
			andaUma = true;
			state2 = 1;
		}
		else if(state==0 && i%2!=0 && state2==0){
			action = "turn right";
			i++;
			andaUma = true;
			state2=1;
		}else if(andaUma){
			action = "forward";
			andaUma = false;
			if(p.elementAt(0) == "bump"){
				state3 = 0;
			}
		}
		else if(i%2!=0 && state2==1){
			action = "turn left";
			state2=0;
			state = 1;
			
		}
		else if(i%2==0 && state2==1){
			action = "turn right";
			state2=0;
			state = 1;
			
			
		}
		
		
		
		
		/*
		//Acao para saber a dimensao j{
		else if (!sabeJ){ 
			if(p.elementAt(0) == "bump"){
				j--;
				sabeJ = true;
				action = "turn left";
				lastAction = "turn left";
			} else{
				action = "forward";
				j++;
				lastAction = "forward";
			}
			posicaoAtual[1] = j;
			
		} //} Fim da acao para determinar o j
		
		// Acao para saber dimensao i
		else if(!sabeI){
			if(p.elementAt(0) == "bump"){
				i--;
				sabeI = true;
				action = "turn left";
				lastAction = "turn left";
			} else{
				action = "forward";
				lastAction = "forward";
				i++;
			}
			
			posicaoAtual[0] = i;
		}//} Fim da acao para saber a dimensao i
		
		// Conjunto de acoes para quando o numero de colunas for par
		else if(j%2 == 0){
			if(lastAction == "turn left"){
					action = "forward";
					lastAction = "forward";
			}
		}
		else{
			System.out.println("Caiu a que");
		}
		//System.out.println("i ->" + i +"\nj ->" + j);
		
	}*/
	}
}
