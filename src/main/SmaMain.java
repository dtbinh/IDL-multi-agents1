package main;

import pacman.SmaPacman;
import particules.SmaBilles;
import util.Data;
import wator.SMAwator;

public class SmaMain {

  public static void main(String[] args) {

	/*
	 * *************************************************************************
	 * *** Recuperation des arguments
	 ****************************************************************************/

	if (args.length == 0) {
	  printUsage(0);
	} else {

	  String nomTP = args[0];

	  switch (nomTP) {
	  case "billes":
		if (args.length < 6) {
		  printUsage(1);
		} else {
		  Data.tp = 1;
		  Data.size = Integer.parseInt(args[1]); // integer
		  Data.grilleVisible = Boolean.valueOf(args[2]); // true
		  Data.equite = Boolean.valueOf(args[3]); // true
		  Data.tours = Integer.parseInt(args[4]); // integer
		  Data.nombreAgents = Integer.parseInt(args[5]); // integer
		  Data.vitesse = 100;
		  SmaBilles sma = new SmaBilles();
		  sma.init();
		  try {
			sma.run();
		  } catch (InterruptedException e) {
			e.printStackTrace();
		  }
		}
		break;
	  case "wator":
		if (args.length < 10) {
		  printUsage(2);
		} else {
		  Data.tp = 2;
		  Data.size = Integer.parseInt(args[1]); // integer
		  Data.grilleVisible = Boolean.valueOf(args[2]); // true
		  Data.equite = Boolean.valueOf(args[3]); // true
		  Data.tours = Integer.parseInt(args[4]); // integer
		  Data.nombrePoissons = Integer.parseInt(args[5]); // integer
		  Data.seedPoisson = Integer.parseInt(args[6]); /// integer
		  Data.nombreRequins = Integer.parseInt(args[7]);
		  Data.seedRequin = Integer.parseInt(args[8]);
		  Data.longeviteRequin = Integer.parseInt(args[9]);
		  Data.vitesse = 100;
		  SMAwator sma2 = new SMAwator();
		  sma2.init();
		  sma2.run();
		}
		break;
	  case "pacman":
		if (args.length < 10) {
		  printUsage(3);
		} else {
		  Data.tp = 3;
		  Data.isGameOver = false;
		  Data.size = Integer.parseInt(args[1]); // integer
		  Data.grilleVisible = Boolean.valueOf(args[2]); // true
		  Data.equite = Boolean.valueOf(args[3]); // true
		  Data.nombrePoursuiveurs = Integer.parseInt(args[4]);
		  Data.vitesseAvatar = Integer.parseInt(args[5]);
		  Data.vitessePoursuiveur = Integer.parseInt(args[6]);
		  Data.pourcentageBlocs = Integer.parseInt(args[7]);
		  Data.afficherDistances = Boolean.valueOf(args[8]);
		  Data.avatarTorique = Boolean.valueOf(args[9]);
		  Data.vitesse = 100;
		  SmaPacman sma = new SmaPacman();
		  sma.init();
		  sma.run();
		}
		break;
	  default:
		printUsage(0);
		break;
	  }
	}

//	// Le Système multi agents a lancer
//	Data.tp = 3;
//
//	Data.size = 50;
//	Data.tours = 1000;
//	Data.vitesse = 100;
//	Data.grilleVisible = false;
//	Data.equite = true;
//
//	try {
//	  // Donnees en fonction du systeme
//	  if (Data.tp == 1) {
//		Data.nombreAgents = 100;
//		SmaBilles sma = new SmaBilles();
//		sma.init();
//		sma.run();
//	  }
//	  if (Data.tp == 2) {
//		Data.nombreAgents = 0;
//
//		Data.nombrePoissons = 170;
//		Data.nombreRequins = 85;
//
//		Data.seedPoisson = 5;
//		Data.seedRequin = 6;
//
//		Data.longeviteRequin = 8;
//		// Summary.setOutputFile("C:\\Users\\AnaGissel\\Desktop\\wator.txt");
//
//		SMAwator sma2 = new SMAwator();
//		sma2.init();
//		sma2.run();
//	  }
//	  if (Data.tp == 3) {
//		Data.isGameOver = false;
//
//		Data.nombreAgents = 0;
//		Data.pourcentageBlocs = 3;
//		Data.nombrePoursuiveurs = 1;
//
//		Data.afficherDistances = false;
//		Data.avatarTorique = false;
//
//		SmaPacman sma = new SmaPacman();
//		sma.init();
//		sma.run();
//	  }
//	} catch (InterruptedException e) {
//	  e.printStackTrace();
//	}
  }

  public static void printUsage(Integer tp) {
	switch (tp) {
	case 1:
	  System.out.println("\n**********************************************");
	  System.out.println("\n************ USAGE - TP PARTICULES ***********");
	  System.out.println("\n**********************************************");
	  System.out.println("\n\n java -jar billes [grid_size] [grid_visibility] [equity] [nb_tours] [nb_agents]");
	  System.out.println("\n\nFor example :");
	  System.out.println("\n\t java -jar billes 50 false true 5000 100");
	  System.out.println("\n\n******************************************");
	  break;
	case 2:
	  System.out.println("\n*****************************************");
	  System.out.println("\n************ USAGE - TP WATOR ***********");
	  System.out.println("\n*****************************************");
	  System.out.println(
	      "\n\n java -jar wator [grid_size] [grid_visibility] [equity] [nb_tours] [nb_poissons] [seed_poissons] [nb_requins] [seed_requins] [longevite_requins]");
	  System.out.println("\n\nFor example :");
	  System.out.println("\n\t java -jar wator 50 false true 5000 170 5 85 6 8");
	  System.out.println("\n\n******************************************");
	  break;
	case 3:
	  System.out.println("\n******************************************");
	  System.out.println("\n************ USAGE - TP PACMAN ***********");
	  System.out.println("\n******************************************");
	  System.out.println(
	      "\n\n java -jar pacman [grid_size] [grid_visibility] [equity] [nb_fantomes] [vitesse_avatar] [vitesse_fantomes] [pourcentage_blocs] [distances] [avatar_torique]");
	  System.out.println("\n\nFor example :");
	  System.out.println("\n\t java -jar pacman 50 false true 3 1 2 3 false false");
	  System.out.println("\n\n******************************************");
	  break;
	default:
	  System.out.println("\n******************************");
	  System.out.println("\n************ USAGE ***********");
	  System.out.println("\n******************************");
	  System.out.println("\n\nBILLES:  java -jar billes [grid_size] [grid_visibility] [equity] [nb_tours] [nb_agents]");
	  System.out.println(
	      "\n\nWATOR :  java -jar wator [grid_size] [grid_visibility] [equity] [nb_tours] [nb_poissons] [seed_poissons] [nb_requins] [seed_requins] [longevite_requins]");
	  System.out.println(
	      "\n\nPACMAN:  java -jar pacman [grid_size] [grid_visibility] [equity] [nb_fantomes] [vitesse_avatar] [vitesse_fantomes] [pourcentage_blocs] [distances] [avatar_torique]");
	  System.out.println("\n\n******************************************");
	  break;
	}
  }

}
