package com.meli.socialmeli.service;

import com.meli.socialmeli.dto.NumberOfProductsInSaleDto;

public interface IPostService {
    NumberOfProductsInSaleDto getNumberOfProductsInSale(Integer userId);
}
