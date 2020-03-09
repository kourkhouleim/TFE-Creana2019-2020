package form;

import backend.Client;

import java.util.HashMap;
import java.util.Map;

public class Inscription {

//------------------------------------------------------ Acc�s aux donn�es du client ---------------------------------------------------------------------------------------------

        private static final String CHAMP_NOM ="nomClient";
        private static final String CHAMP_PRENOM ="prenomClient";
        private static final String CHAMP_EMAIL ="email";
        private static final String CHAMP_ADRESSE ="adresse";
        private static final String CHAMP_TEL ="numTelClient";
        private static final String CHAMP_CP ="codePostal";

        //----------------------------------------------------- D�finition de la map & r�sultat -------------------------------------------------------------------------------------------
        private String resultat; // Stocke si la connexion � r�ussie
        private Map<String, String> erreurs = new HashMap<String, String>();// stock les messages d'erreurs

        public String getResultat() {return resultat;}
        public Map<String, String> getErreurs(){return erreurs;}

        // ---------------------------------------------------- M�thode de connexion du client --------------------------------------------------------------------------------------------
        public Client connexionClient(HttpServletRequest requete) {
            String nomClient = getValeurChamp(requete, CHAMP_NOM);
            String prenomClient = getValeurChamp(requete, CHAMP_PRENOM);
            String email = getValeurChamp(requete, CHAMP_EMAIL);
            String adresseClient = getValeurChamp(requete, CHAMP_ADRESSE);
            String numTelClient = getValeurChamp(requete, CHAMP_TEL);
            Integer codePostal = Integer.parseInt(getValeurChamp(requete, CHAMP_CP));

            Client client = new Client();

            try {validationNomClient (nomClient);} // teste si le nom client est ok
            catch(Exception e) {setErreur(CHAMP_NOM,e.getMessage());} // sinon stock un message d'erreur
            client.setNomClient(nomClient); // si oui SET le nom

            try {validationPrenomClient (prenomClient);}
            catch(Exception e) {setErreur(CHAMP_PRENOM,e.getMessage());}
            client.setPrenomClient(prenomClient);

            try {validationEmail(email);}
            catch(Exception e) {setErreur(CHAMP_EMAIL,e.getMessage());}
            client.setEmail(email);

            try {validationAdresse(adresseClient);}
            catch(Exception e) {setErreur(CHAMP_ADRESSE,e.getMessage());}
            client.setAdresse(adresseClient);

            try {validationNumTel(numTelClient);}
            catch(Exception e) {setErreur(CHAMP_TEL,e.getMessage());}
            client.setNumTelClient(numTelClient);

            try {validationCP(codePostal);}
            catch(Exception e) {setErreur(CHAMP_CP,e.getMessage());}
            client.setCodePostal(codePostal);

            if(erreurs.isEmpty())
            {
                resultat = "Client ajouté";
            }
            else {
                resultat = "Echec de l'ajout";
            }
            return client;
        }

// ---------------------------------------------------------------------------- Test de validation -------------------------------------------------------------------------------

        private void validationNomClient ( String nomClient ) throws Exception {
            if (nomClient == null) {throw new Exception ("Veuillez entrer votre nom");}
        }
        private void validationPrenomClient ( String prenomClient ) throws Exception{
            if (prenomClient == null) {throw new Exception("Veuillez entrer votre prénom");}
        }
        private void validationEmail ( String email )throws Exception {
            if (email == null) {throw new Exception("Veuillez entrer une adresse mail");}
        }
        private void validationAdresse ( String adresse ) throws Exception {
            if (adresse == null) {throw new Exception("Veuillez entrer votre adresse");}
        }
        private void validationNumTel ( String numTel ) throws Exception {
            if (numTel == null) { throw new Exception("Veuillez entrer votre numéro de téléphone");}
        }
        private void validationCP ( Integer CP ) throws Exception {
            if (CP == null) { throw new Exception("Veuillez entrer votre code postal");}
        }

// -------------------------------------------------------- Ajoute un message d'erreur en fonction de l'erreur. ------------------------------------------------------------------

        private void setErreur (String champ, String message) {
            erreurs.put( champ, message);
        }

//-------------------------------- Fonction qui retourne null si un champ du formulaire est vide ou null , si pas renvoie la valeur du champ.--------------------------------------

        private static String getValeurChamp(HttpServletRequest requete, String nomChamp) {
            String valeur = requete.getParameter(nomChamp);
            if(valeur == null || valeur.trim().length() == 0) {
                return null;
            }
            else { return valeur.trim();}
        }
    }

}
