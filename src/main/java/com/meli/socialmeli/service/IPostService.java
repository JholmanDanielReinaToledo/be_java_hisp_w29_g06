package com.meli.socialmeli.service;

import com.meli.socialmeli.dto.PostDto;
import com.meli.socialmeli.dto.response.ResponseWrapperDto;

public interface IPostService {
    PostDto addPost(PostDto post);
    ResponseWrapperDto createPromoPost(PostDto promoPostDto);
}
