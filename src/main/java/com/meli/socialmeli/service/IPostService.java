package com.meli.socialmeli.service;


import com.meli.socialmeli.dto.NumberOfProductsInSaleDto;
import com.meli.socialmeli.dto.PostDto;
import com.meli.socialmeli.dto.response.PostFromFollowedDto;
import com.meli.socialmeli.dto.response.ProductsOfSellerDto;
import com.meli.socialmeli.dto.response.ResponseWrapperDto;

public interface IPostService {
    NumberOfProductsInSaleDto getNumberOfProductsInSale(Integer userId);
    ProductsOfSellerDto listAllProductsInSaleOfSeller(Integer userId);
    ResponseWrapperDto addPost(PostDto post);
    ResponseWrapperDto createPromoPost(PostDto promoPostDto);
    PostFromFollowedDto getPostsFromFollowedUsers(Integer userId, Integer order);
}
