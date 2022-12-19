package org.hmo.micro;

import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
@OpenAPIDefinition(
        info = @Info(
                title = "Book API",
                description = "Create books",
                version = "1.0",
                contact = @Contact(name = "@ahmethmo", email = "ahmethmo@gmail.com")
        ),
        externalDocs = @ExternalDocumentation(
                url = "https://github.com/ahmethmo",
                description = "GITHUB"
        ),
        tags = {
                @Tag(name = "api", description = "Public API"),
                @Tag(name = "books", description = "Interested in books")
        }
)
public class BookMicroservice {
}
