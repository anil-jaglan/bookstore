package com.bookstore.service.rest.api.adaptor.test;

import com.bookstore.common.enums.GenreEnum;
import com.bookstore.rest.transferobjects.BookInfoResponseTO;
import com.bookstore.rest.transferobjects.BookInvoiceTO;
import com.bookstore.service.rest.api.adaptor.BookStoreRestController;
import com.bookstore.service.services.BookStoreService;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookStoreRestControllerIntegrationTest {
    /**
     * MockMvc instance.
     */
    private MockMvc mvc;

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    BookStoreRestController bookStoreController;

    @MockBean
    private BookStoreService bookService;

    @Before
    public void setUp() {
        this.mvc = standaloneSetup(this.bookStoreController).build();
    }

    /**
     * 
     * Method to test add end point.
     * 
     * @throws Exception
     */
    @Test
    public void testBuy() throws Exception {
        when(bookService.buy(anyString(), anyInt())).thenReturn(createInvoiceData());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/book/ISBN001/buy?quantity=25").accept(
                MediaType.APPLICATION_JSON);
        mvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(jsonPath("$.data.invoiceId", is("INV-001")));
        mvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(jsonPath("$.data.invoiceValue", is(550.0)));
    }

    /**
     * 
     * Method to test search api.
     * 
     * @throws Exception
     */
    @Test
    public void testSearch() throws Exception {
        when(bookService.search(anyString())).thenReturn(createBooksData());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/book/ISBN001").accept(MediaType.APPLICATION_JSON);
        mvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(
                jsonPath("$.data[0].title", is("Half Girlfriend")));
    }

    /**
     * 
     * Method to test update end point.
     * 
     * @throws Exception
     */
    @Test
    public void testUpdate() throws Exception {
        doNothing().when(bookService).update(anyString(), anyInt());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/book/ISBN001?quantity=10").accept(
                MediaType.APPLICATION_JSON);
        mvc.perform(requestBuilder).andExpect(status().isOk());
    }

    /**
     * 
     * Method to mock books data.
     * 
     * @return
     */
    private Set<BookInfoResponseTO> createBooksData() {
        final Set<BookInfoResponseTO> books = new HashSet<>();
        final BookInfoResponseTO book = new BookInfoResponseTO();
        book.setIsbn("ISBN001");
        book.setAuthor("Chetan Bhagat");
        book.setPrice(99.0f);
        book.setTitle("Half Girlfriend");
        book.setGenre(GenreEnum.ROMANCE.getName());
        books.add(book);
        return books;
    }

    /**
     * 
     * Method to mock invoice data.
     * 
     * @return
     */
    private BookInvoiceTO createInvoiceData() {
        final BookInvoiceTO book = new BookInvoiceTO();
        book.setInvoiceId("INV-001");
        book.setInvoiceValue(550.f);
        book.setInvoiceDate("2018-10-29");
        return book;
    }

}
