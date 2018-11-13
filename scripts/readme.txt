Bookstore application is developed using SpringBoot.

It provides REST api interfaces to interact with book store for adding, search and buying books.

Technology stack:
1. SpringBoot
2. DB - MySQL
3. Log4j, Logback classic

Setup: 
1. Application will be required to build using maven from main directory book-store.
   Executable jar will be created in book-store/book-store-deploy/target.
2. Once build is successful, go to bookstore/scripts folder.
3. Change database connection URL and username password accordingly in start-book-store.cmd (.sh for linux systems) file.
4. Create new schema with BOOK_STORE name in database.
5. Application will start on 8080 port by default, port can also be changed in start-book-store.cmd (.sh for linux systems) file.
6. Execute start-book-store.cmd (.sh for linux systems) file to start service.
7. Logs will be created in book-store/logs directory.

Pre-requisite:
- Drop all tables from BOOK_DATASTORE.

Functionality convered:
1. Add a book.
2. Search a book by isbn, author or title.
3. Buy a book.
4. Update book quantity.
5. Check book media coverage by ISBN. (To test media coverage, save a book with 'est' title,without quotes)

--------------------------------------------------------------------------------------------
Add a book:
Request: POST
URL: http://localhost:8080/book
ContentType: application/json
Request Body: 
{
	"isbn" : "ISBN543212345",
	"title": "Harry Potter",
	"author": "J K. Rowling",
	"price": 23.5,
	"totalCopies": 200,
	"genre": "Fantasy",
	"publishDate": "2005-01-01",
	"desc": "Harry Potter is a series of fantasy novels written by British author J. K. Rowling. The novels chronicle the lives of a young wizard, Harry Potter, and his friends Hermione Granger and Ron Weasley"
}
--------------------------------------------------------------------------------------------
Purchase a book:
Request: POST
URL: http://localhost:8080/book/ISBN019283/buy?quantity=100

--------------------------------------------------------------------------------------------
Update book book quantity:
Request: PUT
URL: http://localhost:8080/book/ISBN543212345/?quantity=200

--------------------------------------------------------------------------------------------
Get book media coverage:
Request: GET
URL: http://localhost:8080/book/ISBN543212345/media

--------------------------------------------------------------------------------------------
Search a book matching with ISBN, Title or Author:
Request: GET
URL: http://localhost:8080/book/Harry
or
URL: http://localhost:8080/book/ISBN543212345

--------------------------------------------------------------------------------------------