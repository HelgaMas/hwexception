package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    ProductRepository repository = new ProductRepository();
    Book book1 = new Book(1, "Jane Eyre", 500, "Charlotte Bronte");
    Book book2 = new Book(2, "White Fang", 200, "Jack London");

    Smartphone phone1 = new Smartphone(3, "iphone 11", 100000, "Apple");
    Smartphone phone2 = new Smartphone(4, "Nokia 3310", 5000, "Nokia");

    @BeforeEach
    public void saveToAll() {
        repository.save(book1);
        repository.save(book2);
        repository.save(phone1);
        repository.save(phone2);
    }

    @Test
    public void shouldRemoveExisting() {
        Product product = repository.findById(3);
        repository.removeById(product.getId());

        Product[] expected = new Product[]{book1, book2, phone2};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveNotExisting() {
        assertThrows(NotFoundException.class, () -> {
            repository.removeById(7);
        });
    }
}