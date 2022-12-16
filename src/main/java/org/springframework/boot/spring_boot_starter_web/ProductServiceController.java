package org.springframework.boot.spring_boot_starter_web;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import productpackage.Product;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DELL
 */
@RestController
public class ProductServiceController {
    
    private static Map<String, Product> productRepo = new HashMap<>();//deklarasi Hashmap productRepo
    static{
    
        //Inisialisasi kelas Product dengan nama honey
        Product honey = new Product();
        honey.setId("1");
        honey.setName("Honey");
        honey.setJumlah("8");
        honey.setHarga("Rp 20.000");
        productRepo.put(honey.getId(), honey);
        
        //Inisialisasi kelas Product dengan nama almond
        Product almond = new Product();
        almond.setId("2");
        almond.setName("Kacang Mente");
        almond.setJumlah("5");
        almond.setHarga("Rp 50.000");
        productRepo.put(almond.getId(), almond);
        
        //Inisialisasi kelas Product dengan nama mie
        Product mie = new Product();
        mie.setId("3");
        mie.setName("Mie");
        mie.setJumlah("10");
        mie.setHarga("Rp 30.000");
        productRepo.put(mie.getId(), mie);
        
        //Inisialisasi kelas Product dengan nama lidi
        Product lidi = new Product();
        lidi.setId("4");
        lidi.setName("Lidi");
        lidi.setJumlah("2");
        lidi.setHarga("Rp 40.000");
        productRepo.put(lidi.getId(), lidi);
        
        //Inisialisasi kelas Product dengan nama benedict
        Product benedict = new Product();
        benedict.setId("5");
        benedict.setName("Lidi");
        benedict.setJumlah("9");
        benedict.setHarga("Rp 80.000");
        productRepo.put(benedict.getId(), benedict);
    }
    
    @RequestMapping(value = "/products")
    public ResponseEntity<Object> getProducts()//deklarasi method getProducts()
    {
        //menampilkan semua objek di dalam productRepo dengan method GET
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Product product)//deklarasi method createProduct
    {
        
        if(productRepo.containsKey(product.getId()))//validasi jika productRepo sudah memiliki id yg diinput
        {
            //menampilkan keterangan "Product is Already Used"
            return new ResponseEntity<>("Product is Already Used", HttpStatus.OK);
        }
        else//validasi jika productRepo belum memiliki id yg diinput
        {
            productRepo.put(product.getId(), product);//menambahkan product baru ke dalam productRepo
            //menampilkan keterangan "Product is created successfully"
            return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
        }
    }
    
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    //deklarasi method updateProduct()
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
        
        if(!productRepo.containsKey(id))//validasi jika productRepo tidak memiliki id yg diinput
        {
            //menampilkan keterangan "Product is not Available"
            return new ResponseEntity<>("Product is not Available", HttpStatus.OK);
        }
        else//validasi jika productRepo memiliki id yg diinput
        {
            productRepo.remove(id);//hapus produk berdasarkan id yg diinput pada productRepo
            product.setId(id);//menambahkan lagi id yg sama ke dalam productRepo
            productRepo.put(id, product);//menambahkan product baru pada productRepo
            //menampilkan keterangan "Product is updated successsfully"
            return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
        }
    }
    
    
    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    //deklarasi method delete()
    public ResponseEntity<Object> delete(@PathVariable("id") String id, @RequestBody Product product) { 
        
        if(!productRepo.containsKey(id))//validasi jika productRepo tidak memiliki id yg diinput
        {
            //menampilkan keterangan "Product is not Available"
            return new ResponseEntity<>("Product is not Available", HttpStatus.OK);
        }
        else//validasi jika productRepo memiliki id yg diinput
        {
            productRepo.remove(id);//hapus produk berdasarkan id yg diinput pada productRepo
            //menampilkan keterangan "Product is deleted successsfully"
            return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
        }
       
    }
    
}
