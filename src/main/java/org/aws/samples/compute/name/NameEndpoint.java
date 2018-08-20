package org.aws.samples.compute.name;

import com.amazonaws.xray.AWSXRay;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.ClientErrorException;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Arun Gupta
 */
@Path("names")
public class NameEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(NameHandler.class);

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

        logger.info("Random is: " + n + " IsEven is:" + isEven);

        if (n <= 80) {
          return Names.findById(id);
        } else {
          if ((n % 2) == 0) {
            throw new ServiceUnavailableException("Service not available", 30L);
          } else {
            throw new ClientErrorException(403);
          }
        }

        //return Names.findById(id);
    }
}
