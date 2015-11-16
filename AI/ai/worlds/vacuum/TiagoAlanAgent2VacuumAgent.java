package ai.worlds.vacuum;

import java.util.Vector;

import com.sun.xml.internal.ws.message.AttachmentUnmarshallerImpl;

public class TiagoAlanAgent2VacuumAgent extends VacuumAgent {
	/**
	 * Agente baseado em modelos
	 */
	
	// Tamanho do ambiente
	private int i = 1;
	private int j = 1;
	
	private boolean sabeI = false;
	private boolean sabeJ = false;
	
	private int[] posicaoAtual = {1,1};
	
	private boolean[] direcao= {false, false, true, false};
	// 0 olhando pra cima
	// 1 olhando para baixo
	// 2  olhando para direita
	// 3 olhando para a esquerda
	
	@Override
	public void determineAction() {
		Vector p = (Vector) percept;
		//Acao quando encontra sujeira
		if(p.elementAt(1) == "dirt") action = "suck";
		
		//Acao para saber a dimensao j{
		else if (!sabeJ){ 
			if(p.elementAt(0) == "bump"){
				j--;
				sabeJ = true;
				action = "turn left";
				lastAction = "turn left";
				atualizarDirecao("cima");
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
				atualizarDirecao("esquerda");
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
		System.out.println("i ->" + i +"\nj ->" + j);
		
	}
	
	
	// Metodos privados que vao auxiliar na classe
	
	//Usar este metodo para atualizar a direcao
	private void atualizarDirecao(String direcao){
		switch (direcao) {
		case "cima":
			somenteEssaTrue(0);
			break;
		case "baixo":
			somenteEssaTrue(1);			
		case "direita":
			somenteEssaTrue(2);
			break;
		case "esquerda":
			somenteEssaTrue(3);
		}
	}
	
	private void somenteEssaTrue(int posicao){
		for (int i2 = 0; i2 < this.direcao.length; j++) {
			this.direcao[i2] = false;
			if(i2 == posicao) this.direcao[i2] = true;
		}
	}
	
	private int qualDirecao(){
		for (int i2 = 0; i2 < this.direcao.length; i2++) {
			if(this.direcao[i2]) return i2;
		}
	}
}
