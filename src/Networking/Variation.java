package Networking;

import java.util.ArrayList;

public class Variation {
	private int variationNr;
	private int correctNumbers=0;
	private ArrayList<Integer> selectedNumbers=new ArrayList<>();
	private String responseEmail;
	
	public Variation() {
		
	}
	
	//Konstruktors, kas pievieno skaitlus
	public Variation(ArrayList<Integer> choosedNumbers) {
		selectedNumbers=new ArrayList<Integer>(choosedNumbers);
	}
	
	//Konstruktors, kas pievieno skaitlus un variacijas numuru
	public Variation(ArrayList<Integer> choosedNumbers, int variationNr) {
		selectedNumbers=new ArrayList<Integer>(choosedNumbers);
		this.variationNr=variationNr;
	}
	
	//Kopesanas konstruktors
	public Variation(Variation newVariation) {
		this.variationNr=newVariation.variationNr;
		this.selectedNumbers=newVariation.selectedNumbers;
		this.responseEmail=newVariation.responseEmail;
	}
	
	public int getVariationNr() {
		return variationNr;
	}
	
	public int getCorrectNumbers() {
		return correctNumbers;
	}
	
	public String getEmail() {
		return responseEmail;
	}
	
	public ArrayList<Integer> getSelectedNumbers() {
		return selectedNumbers;
	}
	
	public void setVariationNr(int variationNr) {
		this.variationNr=variationNr;
	}
	
	public void setCorrectNumbers(int correctNumbers) {
		this.correctNumbers=correctNumbers;
	}
	
	public void setEmail(String email){
        responseEmail=email;
    }
}
