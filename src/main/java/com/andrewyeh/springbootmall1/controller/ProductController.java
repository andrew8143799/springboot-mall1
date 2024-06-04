package com.andrewyeh.springbootmall1.controller;

import com.andrewyeh.springbootmall1.constant.ProductCategory;
import com.andrewyeh.springbootmall1.dto.ProductQueryParam;
import com.andrewyeh.springbootmall1.dto.ProductRequest;
import com.andrewyeh.springbootmall1.model.Product;
import com.andrewyeh.springbootmall1.service.ProductService;
import com.andrewyeh.springbootmall1.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class ProductController {



    @Autowired
    private ProductService productService;

    //查詢商品列表(指定條件)
    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getProducts(//查詢條件 filtering
                                                     @RequestParam (required = false) ProductCategory category,
                                                     @RequestParam (required = false) String search,
                                                     //根據欄位排序(預設值為created_date)
                                                     @RequestParam (defaultValue = "created_date") String orderBy,
                                                     //排序方法(asc or desc)
                                                     @RequestParam (defaultValue = "desc") String sort,
                                                     //分頁 (limit: 取得幾筆商品數據 offset:要跳過多少筆數據)
                                                     @RequestParam (defaultValue = "5") @Max(1000) @Min(0) Integer limit,
                                                     @RequestParam (defaultValue = "0") @Min(0) Integer offset){

        ProductQueryParam productQueryParam = new ProductQueryParam();
        productQueryParam.setCategory(category);
        productQueryParam.setSearch(search);
        productQueryParam.setOrderBy(orderBy);
        productQueryParam.setSort(sort);
        productQueryParam.setLimit(limit);
        productQueryParam.setOffset(offset);

        //取得productList(將存放在page當中，再回傳)
        List<Product> productList =  productService.getProducts(productQueryParam);

        //取得 product 總比數
        Integer total = productService.countProducts(productQueryParam);

        //分頁responseBody的值，存放在Page類別中
        Page<Product> page = new Page();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        //將productList以Json陣列回傳前端
        page.setResults(productList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    //使用id查詢商品
    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){

        Product product = productService.getProductById(productId);

        if(product != null){
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid
                                                     ProductRequest productRequest){

        Integer productId =  productService.createProduct(productRequest);

        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest){

        Product product = productService.getProductById(productId);
        //check if product exists
        if(product == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {

            productService.updateProduct(productId, productRequest);

            Product updatedProduct = productService.getProductById(productId);

            return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
        }

    }
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer productId){


        productService.deleteById(productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }


}
