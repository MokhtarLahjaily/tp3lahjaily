package ma.emsi.lahjaily.tp3lahjaily.llm;

import dev.langchain4j.service.SystemMessage;

public interface GuideTouristique {

    @SystemMessage("""
        Tu es un guide touristique expert.
        Pour le lieu (ville ou pays) suivant : {villeOuPays}, 
        tu dois fournir les 2 principaux endroits à visiter
        et le prix moyen d'un repas dans la devise locale.

        Tu DOIS répondre OBLIGATOIREMENT au format JSON suivant, et uniquement ce JSON:
        {
          "ville_ou_pays": "nom de la ville ou du pays",
          "endroits_a_visiter": ["endroit 1", "endroit 2"],
          "prix_moyen_repas": "<prix> <devise du pays>"
        }

        N'utilise pas Markdown dans ta réponse.
        """)
    String getInformations(String villeOuPays);
}