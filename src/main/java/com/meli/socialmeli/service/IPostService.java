package com.meli.socialmeli.service;

import com.meli.socialmeli.dto.request.PostDto;
import com.meli.socialmeli.dto.response.ResponseWrapperDto;

import com.meli.socialmeli.dto.NumberOfProductsInSaleDto;

public interface IPostService {
    NumberOfProductsInSaleDto getNumberOfProductsInSale(Integer userId);
    ResponseWrapperDto addPost(PostDto post);
    ResponseWrapperDto createPromoPost(PostDto promoPostDto);
}
