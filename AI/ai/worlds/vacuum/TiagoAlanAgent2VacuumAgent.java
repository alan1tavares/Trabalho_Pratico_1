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
	
	private int count = 0;
	private int count2 = 0;
	
	private boolean andaUma = false;

	private boolean coringa = true;
	
	private boolean sabeI = false;
	private boolean sabeJ = false;

	private int[] posicaoAtual = { 1, 1 };

	@Override
	public void determineAction() {
		Vector p = (Vector) percept;
		//Acao quando encontra sujeira
		
		if(p.elementAt(1) == "dirt") action = "suck";
		
		//Anda enquanto nao tiver obstaculos
		else if(p.elementAt(0) != "bump" && state != 0){
			action = "forward";
			j++;
			
		}
		//para no momento do obstaculo
		else if(p.elementAt(0)=="bump" && state != 0){
			state = 0;
			if(p.elementAt(2)=="home"){
				action="shut-off";
			}
			
		}
		//primeira rotação caso a linha for par
		else if(state==0 && i%2==0 && state2==0 && coringa==true){
			action = "turn left";
			i++;
			andaUma = true;
			state2 = 1;
			
			
		}
		//primeira rotação caso a linha for impar
		else if(state==0 && i%2!=0 && state2==0 && coringa==true){
			action = "turn right";
			i++;
			andaUma = true;
			state2=1;
			
			
		}
		//sobe apenas uma vez
		else if(andaUma && coringa==true){
			action = "forward";
			
			
			andaUma = false;
			
		}
		//segunda rotação caso a linha for impar 
		else if(i%2!=0 && state2==1){
			if(p.elementAt(0)=="bump"){
				
				state2= 1;
				count2++;
				action = "turn left";
				if(count2==2){
					state2=0;
					state=1;
					count2=0;
					coringa = false;
				}
			}
			else{
				action = "turn left";
				
				//seta state2 para 0, para fazer apenas uma rotação
				state2=0;
				
				//seta state em 1 para continuar com os forward
				state = 1;
				
				
			}
			
			
		}
		//segunda rotação caso a linha for par
		else if(i%2==0 && state2==1 && coringa==true){
			if(p.elementAt(0)=="bump"){
				state2=1;
				count++;
				action = "turn right";
				if(count==3){
					state2=0;
					state=1;
					count=0;
				}
			}
			else{
				action = "turn right";
				//seta state2 para 0, para fazer apenas uma rotação
				state2=0;
				
				//seta state em 1 para continuar com os forward
				state = 1;
				
				
			}
			
			
		}

		
		
		
	}
}