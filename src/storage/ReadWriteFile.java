package storage;

import controller.ProductManager;
import model.Product;

import java.io.*;
import java.util.List;

public class ReadWriteFile implements IReadWriteData {
    List<Product> productList = ProductManager.productList;

    private static ReadWriteFile instance = null;

    private ReadWriteFile() {
    }

    public static ReadWriteFile getInstance() {
        if (instance == null) instance = new ReadWriteFile();
        return instance;
    }

    @Override
    public List<Product> readData() {
        try {
            File file = new File("data/products.csv");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String value;
            while ((value = bufferedReader.readLine()) != null) {
                String[] strings = value.split(",");
                Product product = new Product(strings[0], strings[1], Integer.parseInt(strings[2]), Integer.parseInt(strings[3]), strings[4]);
                productList.add(product);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("");
        }
        return productList;
    }

    @Override
    public void writeData(List<Product> books) {
        try {
            File file = new File("data/products.csv");
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            for (Product product : productList) {
                bufferedWriter.write(product.getProductId() + "," + product.getProductName() + "," + product.getProductPrice() + "," + product.getProductAmount() + "," + product.getProductDescribe() + "\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("");
        }
    }
}
