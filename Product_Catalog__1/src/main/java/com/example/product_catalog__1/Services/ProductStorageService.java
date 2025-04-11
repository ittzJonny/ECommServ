package com.example.product_catalog__1.Services;

import com.example.product_catalog__1.DTO.UserDto;
import com.example.product_catalog__1.Exceptions.DoesNotExistException;
import com.example.product_catalog__1.Exceptions.InvalidIdException;
import com.example.product_catalog__1.Exceptions.MandatoryFieldException;
import com.example.product_catalog__1.Exceptions.ResourceAlreadyExists;
import com.example.product_catalog__1.Models.Category;
import com.example.product_catalog__1.Models.Products;
import com.example.product_catalog__1.Repos.CategoryRepo;
import com.example.product_catalog__1.Repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("pss")
@Primary
public class ProductStorageService extends ProductServiceInterface{

    @Autowired
    ProductRepo productRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<Products> getAllProducts() throws DoesNotExistException{
        List<Products> pList=productRepo.findAll();
        if (pList.isEmpty()) throw new DoesNotExistException("No products found");

        return pList;
    }

    @Override
    public Products getProductById(Long productId) throws InvalidIdException {

        return productRepo
                .findProductsById(productId)
                .orElseThrow(()-> new InvalidIdException("No Product found with requested id: " + productId));
    }

    @Override
    public Products getProductByUserRole(Long productId, UUID userId) throws DoesNotExistException {
        Products p=productRepo.findProductsById(productId).orElseThrow(()-> new DoesNotExistException("Product Does not Exist"));

        UserDto userDTO=restTemplate.getForEntity("http://AuthenticationService/user/{id}", UserDto.class,userId).getBody();

        if (userDTO!=null)
        {
            return p;
        }

        return null;
    }

    @Override
    public Products addProduct(Products product) throws ResourceAlreadyExists, MandatoryFieldException {

        if (product.getName()==null) throw  new MandatoryFieldException("Product name cannot be null");

        Optional<Products> productsOptional = productRepo.findProductsByName(product.getName());
        if (productsOptional.isPresent()) {
            throw new ResourceAlreadyExists("Product with same name already exists");
        }

        if (product.getCategory()!=null && product.getCategory().getName()!=null) {
            Optional<Category> categoryOptional = categoryRepo.findByName(product.getCategory().getName());
            Category categoryToBeInserted = categoryOptional.isPresent() ? categoryOptional.get() : product.getCategory();
            product.setCategory(categoryToBeInserted);
        }

        return productRepo.save(product);
    }

    @Override
    public Products replaceProduct(Products newProduct) throws DoesNotExistException, MandatoryFieldException {

        if (newProduct.getCategory()==null || newProduct.getCategory().getName()==null) {
            throw new MandatoryFieldException("Category name is a mandatory field ");
        }

        Products product=productRepo
                .findProductsById(
                        newProduct.getId()==null?0:newProduct.getId()
                ).orElseThrow(
                        ()-> new DoesNotExistException("No such Product Exists")
                );

        newProduct.setId(product.getId());

        Category category=categoryRepo
                .findByName(newProduct.getCategory().getName())
                .orElseThrow(
                        ()-> new DoesNotExistException("No such Category Exists ")
                );


        newProduct.setCategory(category);


        return productRepo.save(newProduct);

    }


    @Override
    public Products updatePartialProduct(String productName, Products productToUpdate) throws DoesNotExistException, MandatoryFieldException {
        Products product=productRepo
                .findProductsByName((productName))
                .orElseThrow(()-> new DoesNotExistException("Product does not exist: "+productName));

        System.out.println(productToUpdate+"======================================================================="+productToUpdate.getCategory());

        if (productToUpdate.getName()!=null)
        {
            product.setName(productToUpdate.getName());
        }

        if (productToUpdate.getDescription()!=null)
        {
            product.setDescription(productToUpdate.getDescription());
        }

        if (productToUpdate.getImageUrl()!=null)
        {
            product.setImageUrl(productToUpdate.getImageUrl());
        }

        if (productToUpdate.getPrice()!=null)
        {
            product.setPrice(productToUpdate.getPrice());
        }

        if (productToUpdate.getCategory()!=null)
        {
            Category categoryToBeStored= getCategoryToBeAdded(productToUpdate);
            product.setCategory(categoryToBeStored);
        }

        return productRepo.save(product);
    }


    private Category getCategoryToBeAdded(Products productToUpdate) throws MandatoryFieldException{

        if (productToUpdate.getCategory().getName()==null)
        {
            throw new MandatoryFieldException("Category name is Mandatory field");
        }

        Optional<Category> categoryOptional = categoryRepo
                    .findByName(productToUpdate.getCategory().getName());


        Category category=null;

        if (categoryOptional.isEmpty())
        {
            category= new Category();
            category.setName(productToUpdate.getCategory().getName());

            if (productToUpdate.getCategory().getDescription()!=null) {
                category.setDescription(productToUpdate.getCategory().getDescription());
            }
        }
        else {
            category=categoryOptional.get();
        }

        return category;
    }
}
