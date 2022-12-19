package org.hmo.micro;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Instant;

@Path("/api/books")
@Tag(name = "Book REST Endpoint")
public class BookResource {

    private static final Jsonb jsonb = JsonbBuilder.create();
    @RestClient
    NumberProxy proxy;

    @Inject
    Logger logger;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new book", description = "Creates a Book with an ISBN number")
    @Retry(maxRetries = 1, delay = 1000)
    @Fallback(fallbackMethod = "fallingbackOnCreatingABook")
    public Response createABook(Book book) {
        Book result = new Book();
        result.isbn13=proxy.generateIsbnNumbers().isbn13;
        result.title=book.title;
        result.author=book.author;
        result.genre=book.genre;
        result.yearOfPublication=book.yearOfPublication;
        result.creationDate= Instant.now();
        logger.info("Book created : "+result);
        return Response.ok(result).build();
    }

    public Response fallingbackOnCreatingABook(Book book) throws FileNotFoundException {
        Book result = new Book();
        result.isbn13="Will be set later";
        result.title=book.title;
        result.author=book.author;
        result.genre=book.genre;
        result.yearOfPublication=book.yearOfPublication;
        result.creationDate= Instant.now();
        saveBookOnDisk(result);
        logger.warn("Book saved on disk : "+result);
        return Response.status(206).entity(result).build();
    }

    private void saveBookOnDisk(Book book) throws FileNotFoundException {
        String bookJson = jsonb.toJson(book);
        System.out.println(bookJson);
        try (PrintWriter out = new PrintWriter("target/book-"+Instant.now().toEpochMilli()+".json")){
            out.println(jsonb.toJson(book));
        }
    }
}
