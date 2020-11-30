/**
 * @(#) Moniteur.java
 */
package FFSSM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Moniteur extends Personne {

    public int numeroDiplome;
    public List<Embauche> embauche = new ArrayList<>();

    public Moniteur(String numeroINSEE, String nom, String prenom, String adresse, String telephone, LocalDate naissance, int numeroDiplome) {
        super(numeroINSEE, nom, prenom, adresse, telephone, naissance);
        this.numeroDiplome = numeroDiplome;
    }

    /**
     * Si ce moniteur n'a pas d'embauche, ou si sa dernière embauche est terminée,
     * ce moniteur n'a pas d'employeur.
     * @return l'employeur actuel de ce moniteur sous la forme d'un Optional
     */
    public Optional<Club> employeurActuel() {
         // TODO: Implémenter cette méthode
//          public Optional<T> maméthode() {
//       // Si vide pas de rerour
//        return   Optional.empty()
            if (embauche.isEmpty()){
                return Optional.empty();
            }
            else if (embauche.get(embauche.size()-1).estTerminee()== true){
                return Optional.empty();
            }
//		// sinon
//		   return 	Optional.of("" le résultat de votre instruction ici); /
            else {
                return Optional.of(embauche.get(embauche.size()-1).getEmployeur());
            }

    }
    
    /**
     * Enregistrer une nouvelle embauche pour cet employeur
     * @param employeur le club employeur
     * @param debutNouvelle la date de début de l'embauche
     */
    public void nouvelleEmbauche(Club employeur, LocalDate debutNouvelle) {
        if (embauche.isEmpty()){
            embauche.add(new Embauche(debutNouvelle, this, employeur));
        }
        else {
            if (embauche.get(embauche.size()-1).estTerminee()== true){
                embauche.add(new Embauche(debutNouvelle, this, employeur));
            }
            else {
            throw new UnsupportedOperationException("La dernière embauche n'est pas terminée");
            }
        }
    }

    public List<Embauche> emplois() {
        return embauche;
    }
    public Embauche getLastEmbauche(){
        return embauche.get(embauche.size()-1);
    }

}
