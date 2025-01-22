package com.meli.socialmeli.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.socialmeli.dto.PostDto;
import com.meli.socialmeli.dto.ProductDto;
import com.meli.socialmeli.entity.Post;
import com.meli.socialmeli.entity.Product;
import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.entity.User;
import com.meli.socialmeli.exception.BadRequestException;
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
    public PostDto addPost(PostDto post) {

        Optional<User> seller = this.userRepository.findById(post.getUserId());

        if (seller.isEmpty()) {
            throw new BadRequestException("No se encontro un user con el id: " + post.getUserId());
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
            throw new BadRequestException("Error adding post");
        }

        Post postToSave = new Post(
                null,
                post.getDate(),
                post.getPrice(),
                productSaved.get(),
                (Seller) seller.get(),
                null,
                false,
                post.getCategory()
        );

        Optional<Post> postSaved = this.postRepository.add(postToSave);

        if (postSaved.isEmpty()) {
            throw new BadRequestException("Error adding post");
        }

        return post;
    }
}
