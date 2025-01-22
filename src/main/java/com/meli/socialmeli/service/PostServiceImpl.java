package com.meli.socialmeli.service;


import com.meli.socialmeli.dto.request.PostDto;
import com.meli.socialmeli.dto.ProductDto;
import com.meli.socialmeli.entity.Post;
import com.meli.socialmeli.entity.Product;
import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.entity.User;
import com.meli.socialmeli.exception.BadRequestException;
import com.meli.socialmeli.dto.response.ResponseWrapperDto;
import com.meli.socialmeli.exception.ConflictException;
import com.meli.socialmeli.exception.NotFoundException;
import com.meli.socialmeli.exception.UnauthorizedException;
import com.meli.socialmeli.repository.IPostRepository;
import com.meli.socialmeli.repository.IProductRepository;
import com.meli.socialmeli.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImpl implements IPostService {

    private IPostRepository postRepository;
    private IProductRepository productRepository;
    private IUserRepository userRepository;

    public PostServiceImpl(
            IPostRepository postRepository,
            IProductRepository productRepository,
            IUserRepository userRepository
    ) {
        this.postRepository = postRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseWrapperDto addPost(PostDto post) {

        Optional<Seller> seller = this.userRepository.findSellerById(post.getUserId());

        if (seller.isEmpty()) {
            throw new NotFoundException("No se encontro un user con el id: " + post.getUserId());
        }

        ProductDto productDto = post.getProduct();
        Product productToSave = new Product(
                productDto.getProductId(),
                productDto.getProductName(),
                productDto.getType(),
                productDto.getBrand(),
                productDto.getColor(),
                productDto.getNotes()
        );

        Optional<Product> productSaved = this.productRepository.add(productToSave);

        if (productSaved.isEmpty()) {
            productSaved = this.productRepository.findById(productDto.getProductId());
        }

        Post postToSave = Post.builder()
                .date(post.getDate())
                .price(post.getPrice())
                .product(productSaved.get())
                .seller(seller.get())
                .hasPromo(false)
                .category(post.getCategory())
                .build();

        Optional<Post> postSaved = this.postRepository.add(postToSave);

        if (postSaved.isEmpty()) {
            throw new ConflictException("Error adding post, already exist");
        }

        return ResponseWrapperDto.builder().message("Success").build();
    }

    public ResponseWrapperDto createPromoPost(PostDto promoPostDto) {
        User user = userRepository.findById(promoPostDto.getUserId()).orElseThrow(() -> new NotFoundException("User not found"));
        if (!(user instanceof Seller)) {
            throw new UnauthorizedException("User is not a seller");
        }
        Product product = productRepository.add(
                Product.builder()
                        .id(promoPostDto.getProduct().getProductId())
                        .name(promoPostDto.getProduct().getProductName())
                        .brand(promoPostDto.getProduct().getBrand())
                        .color(promoPostDto.getProduct().getColor())
                        .notes(promoPostDto.getProduct().getNotes())
                        .type(promoPostDto.getProduct().getType())
                        .build()
        ).orElseThrow(() -> new ConflictException("Error, producto asociado a ese id ya existente"));


        Post post = Post.builder()
                .date(promoPostDto.getDate())
                .price(promoPostDto.getPrice())
                .product(product)
                .seller((Seller) user)
                .category(promoPostDto.getCategory())
                .hasPromo(true)
                .discount(promoPostDto.getDiscount())
                .build();

        if(postRepository.add(post).isEmpty())
            throw new ConflictException("Error adding post, already exist");

        return  ResponseWrapperDto.builder().message("Success").build();
    }
}
