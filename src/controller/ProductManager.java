package controller;

import model.Product;
import storage.IReadWriteData;
import storage.ReadWriteFile;
import views.Client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static views.Client.inputEditProduct;
import static views.Client.inputRemoveProduct;

public class ProductManager {
    IReadWriteData readWriteData = ReadWriteFile.getInstance();
    public static List<Product> productList = new ArrayList<>();

    public void displayProduct() {
        for (Product a : productList) {
            System.out.println(a);
        }
    }

    public void addProduct(Scanner scanner) {
        Product product = new Client().creatNewProduct(scanner);
        productList.add(product);
    }

    public void editProduct(Scanner scanner) {
        inputEditProduct(scanner, productList);
    }

    public void removeProduct(Scanner scanner) {
        inputRemoveProduct(scanner, productList);
    }

    public List<Product> sortByIncreasePrice() {
        Collections.sort(productList);
        return productList;
    }

    public List<Product> sortByDecreasePrice() {
        Collections.reverse(productList);
        return productList;
    }

    public void searchMaxPrice() {
        List<Product> productSortList = sortByIncreasePrice();
        System.out.println(productSortList.get((productSortList.size() - 1)));
    }

    public List<Product> readFileProductList() {
        productList = readWriteData.readData();
        return productList;
    }

    public void writeFileProductList() {
        readWriteData.writeData(productList);
    }
}
