package FFSSM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Plongeur extends Personne{
    
    private int niveau;
    private GroupeSanguin groupeSanguin;
    private ArrayList<Licence> mesLicences= new ArrayList<Licence>();

    public Plongeur(int niveau, GroupeSanguin groupeSanguin, String numeroINSEE, String nom, String prenom, String adresse, String telephone, LocalDate naissance) {
        super(numeroINSEE, nom, prenom, adresse, telephone, naissance);
        this.niveau = niveau;
        this.groupeSanguin = groupeSanguin;
    }
    
    public void ajouteLicence(Licence newLicence){
        mesLicences.add(newLicence);
    }

    public List<Licence> getMesLicences() {
        return mesLicences;
    }
    public Licence getLastLicence(){
        Licence lastLicence = mesLicences.get(mesLicences.size() - 1);
        return lastLicence;
    }
    
}
