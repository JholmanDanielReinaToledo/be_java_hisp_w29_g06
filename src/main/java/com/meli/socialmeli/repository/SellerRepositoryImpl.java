package com.meli.socialmeli.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class SellerRepositoryImpl implements ISellerRepository{
    
    private List<Seller> sellers;

    public SellerRepositoryImpl() {  // Constructor
        sellers = new ArrayList<>();
        loadSampleData();  // Llama a un método para cargar datos de prueba
    }

    private void loadSampleData() {
        Seller seller1 = new Seller(1, "Vendedor1");
        Seller seller2 = new Seller(2, "Vendedor2");
        seller1.addFollower(new User(3, "Usuario1"));
        seller1.addFollower(new User(4, "Usuario2"));
        seller2.addFollower(new User(5, "Usuario3"));

        // Agregar los vendedores a la lista
        sellers.add(seller1);
        sellers.add(seller2);
    }


    @Override
    public Optional<Seller> findById(Integer id) {
        return this.sellers.stream().filter(seller -> seller.getId().equals(id)).findFirst();
    }
}
