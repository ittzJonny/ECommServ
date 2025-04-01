package com.example.product_catalog__1.Services;

import com.example.product_catalog__1.DTO.SortParams;
import com.example.product_catalog__1.DTO.SortType;
import com.example.product_catalog__1.Exceptions.DoesNotExist;
import com.example.product_catalog__1.Models.Products;
import com.example.product_catalog__1.Repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchJPAService implements SearchServiceInterface {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Page<Products> searchProducts(String searchTerm, int pageNumber, int pageSize, List<SortParams> sortParams) {

        searchTerm="%"+String.join("%", searchTerm.split("\\s+"))+"%";

        PageRequest pageRequest = this.createPageRequest(pageNumber, pageSize, sortParams);

        Page<Products> productsList= productRepo.findProductsByNameLike( searchTerm, pageRequest);

//        System.out.println("------------------ Pg No: "+pageNumber+" PageSize "+pageSize);

        if (productsList.isEmpty())
        {
            throw new DoesNotExist("No products found");
        }

        return productsList;
    }

    private PageRequest createPageRequest(int pageNumber, int pageSize, List<SortParams> sortParams) {

        Sort sort=this.settingSortsfromParams(sortParams);

        if (sort!=null) return PageRequest.of(pageNumber, pageSize, sort);

        return PageRequest.of(pageNumber, pageSize);
    }

    private Sort settingSortsfromParams(List<SortParams> sortParams) {
        Sort sort=null;

        if (sortParams==null)
        {
            return sort;
        }

        for (int i=0;i<sortParams.size();i++) {

            SortParams params=sortParams.get(i);

            if (i==0)
            {
                sort=Sort.by(params.getSortCriteria());
                if (params.getSortType().equals(SortType.DESC))
                {
                    sort=sort.descending();
                }
            }

            sort=sort.and(Sort.by(params.getSortCriteria()));
            if (params.getSortType().equals(SortType.DESC))
            {
                sort=sort.descending();
            }

        }

        return sort;
    }
}
