package FFSSM;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author becke
 */
public class FFSSM_JUnitTest {
    
    Moniteur untel;
    Plongeur george;
    Plongeur pierre;
    Plongee plongee1;
    Club diving;
    Site site;
    Licence licence;
    Licence licence2;
    
    public FFSSM_JUnitTest() {
    }
    
    @BeforeEach
    public void setUpClass() {
        site = new Site("La plage","c'est beau");
        untel = new Moniteur("1234", "Blanc", "Jean", "2 rue", "060606", LocalDate.of(2000, 2, 26), 2);
        george = new Plongeur(1, GroupeSanguin.APLUS, "1345", "Plante", "george", "3 rue", "070707", LocalDate.of(2000, 3, 20) );
        pierre = new Plongeur(2, GroupeSanguin.AMOINS, "135", "feuille", "pierre", "4 rue", "090909", LocalDate.of(2003, 5, 12) );
        plongee1 = new Plongee(site, untel, LocalDate.of(2020, 5, 12), 20, 15);
        diving = new Club(untel, "diving", "020202");
        licence = new Licence(pierre,"123323", LocalDate.of(2020, 4, 12), 2, diving);
        licence2 = new Licence(pierre,"1233234U", LocalDate.of(2020, 4, 20), 2, diving);
    }
    
    @Test
	public void testAjouteLicence() {
                pierre.ajouteLicence(licence);
		assertEquals(licence, pierre.getLastLicence(),"La licence n'est pas bien ajouté");
                pierre.ajouteLicence(licence2);
                assertEquals(licence2, pierre.getLastLicence(),"La licence ne correspond pas à la dernière ajoutée");
                List<Licence>  mesLicences = new ArrayList<Licence>();
                mesLicences.add(licence);
                mesLicences.add(licence2);
                assertEquals(mesLicences, pierre.getMesLicences(),"La liste des licences ne correspond pas");
	}
        
        @Test
	public void testLicenceEstValide() {
                
		assertFalse(licence.estValide(LocalDate.of(2021, 4, 13)));
                assertTrue(licence.estValide(LocalDate.of(2021, 4, 10)));
	}
        
        @Test
	public void testEmbaucheMoniteur() {
                
                assertEquals(Optional.empty(), untel.employeurActuel(),"La liste n'est pas bien initialisée");
		untel.nouvelleEmbauche(diving, LocalDate.of(2021, 4, 10));
                assertEquals(Optional.of(diving), untel.employeurActuel(),"Un nouvel enseignant doit avoir 0 heures prévues");
                try {
			untel.nouvelleEmbauche(diving, LocalDate.of(2022, 4, 10));
			// Si on arrive ici, il n'y a pas eu d'exception, échec
			fail();
		} catch (Exception e) {
			// Si on arrive ici, il y a eu une exception, c'est ce qui est attendu
		}
                untel.getLastEmbauche().terminer(LocalDate.MIN);
                assertTrue(untel.getLastEmbauche().estTerminee());
                assertEquals(Optional.empty(), untel.employeurActuel(),"L'embauche terminé pb");
                untel.nouvelleEmbauche(diving, LocalDate.of(2022, 4, 10));
                
	}
        @Test
	public void testPlongee() {
                pierre.ajouteLicence(licence);
                george.ajouteLicence(licence2);
                plongee1.ajouteParticipant(george);
		plongee1.ajouteParticipant(pierre);
                List<Plongeur> plongeurs = new ArrayList<>();
                plongeurs.add(george);
                plongeurs.add(pierre);
                assertEquals(plongeurs, plongee1.listePlongeur,"L'embauche terminé pb");
                assertTrue(plongee1.estConforme());
                
	}
        @Test
        public void testPlongee2() {
                pierre.ajouteLicence(licence);
                george.ajouteLicence(new Licence(pierre,"1233234U", LocalDate.of(202, 4, 20), 2, diving));
                plongee1.ajouteParticipant(george);
		plongee1.ajouteParticipant(pierre);
                List<Plongeur> plongeurs = new ArrayList<>();
                plongeurs.add(george);
                plongeurs.add(pierre);
                assertEquals(plongeurs, plongee1.listePlongeur,"L'embauche terminé pb");
                assertFalse(plongee1.estConforme());
                
	}
        @Test
        public void testClub() {

                pierre.ajouteLicence(licence);
                george.ajouteLicence(new Licence(pierre,"1233234U", LocalDate.of(202, 4, 20), 2, diving));
                plongee1.ajouteParticipant(george);
		plongee1.ajouteParticipant(pierre);
                diving.organisePlongee(plongee1);
                assertEquals(plongee1, diving.plongees.get(0));
                List<Plongee> plongee = new ArrayList<>();
                plongee.add(plongee1);
                assertEquals(plongee, diving.plongeesNonConformes());
                
	}

    
}
