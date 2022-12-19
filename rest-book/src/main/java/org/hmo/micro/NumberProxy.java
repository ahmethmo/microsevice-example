package org.hmo.micro;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "number.proxy")
@Produces(MediaType.APPLICATION_JSON)
@Path("/api/numbers")
public interface NumberProxy {

    @GET
    IsbnThirteen generateIsbnNumbers();
}