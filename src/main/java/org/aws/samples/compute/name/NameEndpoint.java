package org.aws.samples.compute.name;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.ClientErrorException;
import java.util.Random;

/**
 * @author Arun Gupta
 */
@Path("names")
public class NameEndpoint {

    @GET
    @Produces({MediaType.APPLICATION_XML + "; qs=0.50",
            MediaType.APPLICATION_JSON + "; qs=0.75"})
    public Name[] get() {
        return Names.findAll();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML + "; qs=0.50",
            MediaType.APPLICATION_JSON + "; qs=0.75",
            MediaType.TEXT_PLAIN + "; qs=1.0"})
    public Name get(@PathParam("id") int id) {

        Random rand = new Random();
        int n = rand.nextInt(100) + 1;

        if (n <= 80) {
          return Names.findById(id);
        } else {
          throw new ClientErrorException(403);
        }

        //return Names.findById(id);
    }
}
