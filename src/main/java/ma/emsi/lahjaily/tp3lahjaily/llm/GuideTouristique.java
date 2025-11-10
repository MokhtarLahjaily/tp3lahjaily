package ma.emsi.lahjaily.tp3lahjaily.llm;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage; // 👈 IMPORTEZ CECI

public interface GuideTouristique {

    @SystemMessage("""
        Tu es un guide touristique expert.
        Tu fournis des informations sur un lieu (ville ou pays) que l'utilisateur va te donner.

        Tu DOIS répondre OBLIGATOIREMENT au format JSON suivant, et uniquement ce JSON:
        {
          "ville_ou_pays": "nom de la ville ou du pays",
          "endroits_a_visiter": ["endroit 1", "endroit 2", ...],
          "prix_moyen_repas": "<prix> <devise du pays>"
        }

        N'utilise pas Markdown dans ta réponse.
        """)
        // La méthode prend maintenant la requête complète de l'utilisateur
    String getInformations(@UserMessage String userRequest);
}