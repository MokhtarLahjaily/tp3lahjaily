package ma.emsi.lahjaily.tp3lahjaily.llm;


import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class LlmServiceFactory {

    @Produces // CDI va utiliser cette méthode pour @Inject GuideTouristique
    @ApplicationScoped
    public GuideTouristique createGuideTouristique() {

        // Lecture de la clé API (comme dans votre TP 2)
        String apiKey = System.getenv("GEMINI_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException(
                    "Erreur : variable d'environnement GEMINI_KEY absente ou vide."
            );
        }

        // Création du modèle Gemini (comme dans votre TP 2)
        ChatModel model = GoogleAiGeminiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gemini-2.5-flash")
                .temperature(0.7)
                .build();

        // Construction du service
        // Note: Pas besoin de .chatMemory() pour un service stateless
        return AiServices.builder(GuideTouristique.class)
                .chatModel(model)
                .build();
    }
}
