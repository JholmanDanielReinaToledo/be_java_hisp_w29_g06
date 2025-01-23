package com.meli.socialmeli.runner;

import com.meli.socialmeli.entity.Post;
import com.meli.socialmeli.entity.Product;
import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.entity.User;
import com.meli.socialmeli.repository.IPostRepository;
import com.meli.socialmeli.repository.IProductRepository;
import com.meli.socialmeli.repository.ISellerRepository;
import com.meli.socialmeli.repository.IUserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class StartUp {

    private IPostRepository postRepository;
    private IUserRepository userRepository;
    private ISellerRepository sellerRepository;
    private IProductRepository productRepository;

    public StartUp(IPostRepository postRepository, IUserRepository userRepository, ISellerRepository sellerRepository, IProductRepository productRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
    }

    /**@Override
    public void run(String... args) throws Exception {
        loadRepositories();
    }**/

    @PostConstruct
    private void loadRepositories() {
        System.out.println("Loading inital data");
        // Creamos los usuarios
        List<User> users = List.of(
                new User(1, "Pepito", List.of()),
                new User(2, "Juanito", List.of()),
                new User(3, "Fermino", List.of()),
                new User(4, "Juanita", List.of())
        );

        // Guardamos los usuarios en el repositorio
        users.forEach(user -> userRepository.save(user));

        // Creamos los vendedores
        List<Seller> sellers = List.of(
                new Seller(1, "Don German", List.of(users.get(0), users.get(1))),
                new Seller(2, "El Paisa", List.of(users.get(1), users.get(2))),
                new Seller(3, "Panadería de la Esquina", List.of(users.get(2), users.get(3)))
        );

        // Guardamos los vendedores en el repositorio
        sellers.forEach(seller -> sellerRepository.save(seller));

        // Asignamos los vendedores a los usuarios
        users.get(0).setFollows(List.of(sellers.get(0), sellers.get(1)));
        users.get(1).setFollows(List.of(sellers.get(2)));

        // Creamos los productos
        Product product1 = Product.builder()
                .id(1)
                .name("Juego")
                .type("Gamer")
                .brand("Box")
                .color("Black")
                .notes("Notes this is a game for box")
                .build();

        Product product2 = Product.builder()
                .id(2)
                .name("Mesa")
                .type("Gamer")
                .brand("Desk")
                .color("White")
                .notes("Notes this is a desk white")
                .build();

        // Guardamos los productos
        productRepository.add(product1);
        productRepository.add(product2);

        // Creamos y guardamos las publicaciones
        List<Post> posts = List.of(
                Post.builder()
                        .id(1)
                        .date(LocalDate.now())
                        .price(1000.0)
                        .product(product1)
                        .seller(sellers.get(0))
                        .discount(0.1)
                        .hasPromo(true)
                        .category(100)
                        .build(),

                Post.builder()
                        .id(2)
                        .date(LocalDate.now())
                        .price(200.0)
                        .product(product1)
                        .seller(sellers.get(0))
                        .discount(0.0)
                        .hasPromo(false)
                        .category(100)
                        .build(),

                Post.builder()
                        .id(3)
                        .date(LocalDate.now())
                        .price(300.0)
                        .product(product2)
                        .seller(sellers.get(1))
                        .discount(0.2)
                        .hasPromo(true)
                        .category(200)
                        .build(),

                Post.builder()
                        .id(4)
                        .date(LocalDate.now())
                        .price(150.0)
                        .product(product1)
                        .seller(sellers.get(1))
                        .discount(0.0)
                        .hasPromo(false)
                        .category(100)
                        .build(),

                Post.builder()
                        .id(5)
                        .date(LocalDate.now())
                        .price(400.0)
                        .product(product2)
                        .seller(sellers.get(2))
                        .discount(0.15)
                        .hasPromo(true)
                        .category(200)
                        .build(),

                Post.builder()
                        .id(6)
                        .date(LocalDate.now())
                        .price(500.0)
                        .product(product1)
                        .seller(sellers.get(2))
                        .discount(0.1)
                        .hasPromo(true)
                        .category(100)
                        .build(),

                Post.builder()
                        .id(7)
                        .date(LocalDate.now())
                        .price(130.0)
                        .product(product2)
                        .seller(sellers.get(0))
                        .discount(0.05)
                        .hasPromo(false)
                        .category(200)
                        .build(),

                Post.builder()
                        .id(8)
                        .date(LocalDate.now())
                        .price(600.0)
                        .product(product1)
                        .seller(sellers.get(0))
                        .discount(0.20)
                        .hasPromo(true)
                        .category(100)
                        .build(),

                Post.builder()
                        .id(9)
                        .date(LocalDate.now())
                        .price(250.0)
                        .product(product2)
                        .seller(sellers.get(1))
                        .discount(0.0)
                        .hasPromo(false)
                        .category(200)
                        .build(),

                Post.builder()
                        .id(10)
                        .date(LocalDate.now())
                        .price(750.0)
                        .product(product1)
                        .seller(sellers.get(2))
                        .discount(0.1)
                        .hasPromo(true)
                        .category(100)
                        .build()
        );

        // Guardamos las publicaciones en el repositorio
        posts.forEach(post -> postRepository.add(post));
    }

}
