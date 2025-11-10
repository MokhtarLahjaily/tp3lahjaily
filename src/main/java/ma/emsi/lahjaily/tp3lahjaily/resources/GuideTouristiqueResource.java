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


@Path("/guide")
@RequestScoped
public class GuideTouristiqueResource {

    @Inject
    private GuideTouristique guideService;

    @GET
    @Path("lieu/{ville_ou_pays}/{nb_lieux}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInfosAvecNombre(
            @PathParam("ville_ou_pays") String villeOuPays,
            @PathParam("nb_lieux") int nbLieux) {

        // 👇 On construit le message utilisateur ici
        String userRequest = String.format(
                "Donne-moi les %d principaux endroits à visiter pour %s, ainsi que le prix moyen d'un repas.",
                nbLieux, villeOuPays
        );

        try {
            // On passe la requête complète au service
            String jsonResponse = guideService.getInformations(userRequest);

            return Response.ok(jsonResponse)
                    .header("Access-Control-Allow-Origin", "*")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("lieu/{ville_ou_pays}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInfosDefaut(@PathParam("ville_ou_pays") String villeOuPays) {
        // L'ancien endpoint appelle le nouveau avec '2' par défaut
        return getInfosAvecNombre(villeOuPays, 2);
    }
}