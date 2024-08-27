package com.siam.storage;
import java.util.List;
import com.siam.enteties.Product;

public interface ProductStoringService {

    List<Product> loadProducts();

}