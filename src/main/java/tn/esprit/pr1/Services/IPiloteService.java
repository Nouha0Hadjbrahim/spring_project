package tn.esprit.pr1.Services;

import tn.esprit.pr1.entities.Pilote;

import java.util.List;

public interface IPiloteService {
    String addPilote(Pilote p);
    void updatePilotRankings();
    List<Pilote> listPilotes();
    Pilote getPiloteById(Long id);
    Pilote updatePilote(Pilote pilote);
    void deletePilote(Long id);
    Pilote affecterPiloteAEquipe(String libelleP, String libelleEq);
}
