package Networking;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ClientApp {
	public static void main(String[] args) {
		int variationNr=1;
		int variationCounter; //Cik bus aizpilditi
		int variationsManually; //Cik bus aizpilditi varianti manuali
		ArrayList<Variation> userVariations; //Saraksts ar lietotaja variantiem
		Variation userVariation;
		int choosedNumber;
		ArrayList<Integer> choosedNumbers=new ArrayList<>(); //Saraksts ar izveletiem skaitliem
		Scanner scanner=new Scanner(System.in);
		System.out.println("==== >> BINGO loterija << ====");
		
		//Izvelas cik variantus
		do {
			System.out.print("Cik variantus velaties: ");
			variationCounter=scanner.nextInt();
		}while(variationCounter<0);
		userVariations=new ArrayList<>(variationCounter);
		
		//Izvelas variantus, lai aizpilditu to manuali
		do {
			System.out.print("Cik variantus velaties, lai aizpilditu manuali: ");
			variationsManually=scanner.nextInt();
		}while(variationsManually>variationCounter);
		
		//Aizpilda manualas variacijas
		for(int i=0; i<variationsManually; i++) {
			while(choosedNumbers.size()<5) {
				System.out.print("Ludzu, aizpildiet ar skaitliem no 1-35: ");
				choosedNumber=scanner.nextInt();
				//Parbauda, vai nav jau sis skaitlis
				if(choosedNumber>=1 && choosedNumber<=35) {
					if(choosedNumbers.contains(choosedNumber)) {
						System.out.println("");
						System.out.println("== Skaitlis jau izvelets ==");
						System.out.println("");
					}
					else {
						choosedNumbers.add(choosedNumber);
					}
				}
				else {
					System.out.println("");
					System.out.println("== Nepareizs skaitlis, izvelaties skaitli no 1-35 ==");
					System.out.println("");
				}
			}
			
			//Izveido variaciju
			userVariation=new Variation(choosedNumbers, variationNr++);
			userVariations.add(userVariation);
			choosedNumbers.clear();
			System.out.println("");
			System.out.println("== Skaitli apstiprinati ==");
			System.out.println("");
		}
		
		//Aizpilda atlikosos variantus ar gadijuma skaitliem
		Random random=new Random();
		for(int i=0; i<(variationCounter-variationsManually); i++) {
			while(choosedNumbers.size()<5){
				choosedNumber=random.nextInt(35)+1;
				choosedNumbers.add(choosedNumber);
			}
			
			//Izveido variaciju
			userVariation=new Variation(choosedNumbers, variationNr++);
			userVariations.add(userVariation);
			choosedNumbers.clear();
		}
		
		//Pieprasa epastu
		String email;
		System.out.print("Ludzu, ievadiet savu epastu, kur rezultati tiks nosutiti: ");
		email=scanner.next();
		for(Variation var : userVariations) {
			var.setEmail(email);
		}
		System.out.println("");
		System.out.println("== Epasts saglabats ==");
		System.out.println("");
		
		//Aizver skeneri
		scanner.close();
		
		//Starte klientu
		KryoClient kryoclient=new KryoClient();
		
		//Suta variacijas uz serveri
		for(Variation variation : userVariations) {
			kryoclient.client.sendTCP(variation);
		}
		if(variationCounter==1) {
			System.out.println("== "+variationCounter+" variants aizsutits uz serveri ==");
		}
		else {
			System.out.println("== "+variationCounter+" varianti aizsutiti uz serveri ==");
		}
	}
}
