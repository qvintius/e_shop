package mainpackage.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mainpackage.models.Image;
import mainpackage.models.Product;
import mainpackage.repositories.ProductRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;
    private final ModelMapper mapper;

    public List<Product> listProducts(String title){
        if (title != null) return productRepo.findByTitle(title);//можно найти объявление по заголовку
        return productRepo.findAll();
    }

    public Product getProductById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    public void saveProduct(Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3){//загрузка фото
        Image image1;
        Image image2;
        Image image3;
        if(file1.getSize() != 0) {//если фото загружено
            image1 = mapper.map(file1, Image.class);
            image1.setPreviewImage(true);
            product.addImageToProduct(image1);
        }
        if(file2.getSize() != 0) {
            image2 = mapper.map(file2, Image.class);
            product.addImageToProduct(image2);
        }
        if(file3.getSize() != 0) {
            image3 = mapper.map(file3, Image.class);;
            product.addImageToProduct(image3);
        }
        log.info("Saving new Product. Title: {}; Author: {}", product.getTitle(), product.getAuthor());
        Product productFromDb = productRepo.save(product);//сохранить в бд
        if (productFromDb.getImages().size()>0)//пользователь может не добавить ни одного фото
            productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());//установить id фото превью получив товар, если есть фото (первое загруженное фото будет превью)


        productRepo.save(product);//обновить товар с установленным id фото превью
    }


    public void deleteProduct(Long id){
        productRepo.deleteById(id);
    }



}
