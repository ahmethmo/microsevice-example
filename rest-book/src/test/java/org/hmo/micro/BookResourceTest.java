package org.hmo.micro;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;

@QuarkusTest
public class BookResourceTest {

    private static final Jsonb jsonb = JsonbBuilder.create();

    @Test
    public void generateIsbnNumbers() {
        Book book = new Book();
        book.isbn13="deneme";
        book.title="t";
        book.author="a";
        book.genre="g";
        book.yearOfPublication=2023;

        given()
            .contentType(ContentType.JSON)
            .body(jsonb.toJson(book))
        .when()
            .post("/api/books")
        .then()
            .statusCode(200)
        .body("title",is("t"))
        .body("author",is("a"))
        .body("genre",is("g"))
        .body("year_of_publication",is(2023))
        .body("creation_date",startsWith("20"));
    }

}
