package ma.emsi.lahjaily.tp3lahjaily.resources;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.emsi.lahjaily.tp3lahjaily.llm.GuideTouristique;


@Path("/guide") // Accessible via /api/guide
@RequestScoped
public class GuideTouristiqueResource {

    @Inject // CDI injecte le service produit par votre Factory
    private GuideTouristique guideService;

    @GET
    @Path("lieu/{ville_ou_pays}") // Accessible via /api/guide/lieu/Maroc
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInfos(@PathParam("ville_ou_pays") String villeOuPays) {

        // Étape 1: Testez d'abord sans le LLM (comme demandé dans le TP)
        // String testJson = "{\"lieu_teste\": \"" + villeOuPays + "\"}";
        // return Response.ok(testJson).build();

        // Étape 2: Une fois le test OK, appelez le LLM
        try {
            String jsonResponse = guideService.getInformations(villeOuPays);

            // Retourner la réponse JSON brute du LLM
            return Response.ok(jsonResponse)
                    .header("Access-Control-Allow-Origin", "*") // Pour le test HTML optionnel
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            // En cas d'erreur avec le LLM
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }
}
