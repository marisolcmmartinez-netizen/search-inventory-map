package com.pluralsight;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class StoreApp {
    public static HashMap<Integer, Product> inventory = new HashMap<Integer, Product>();

    public static void main(String[] args) {
        loadInventory();

        Scanner userInput = new Scanner(System.in);

        System.out.print("what item # are you interested in? ");
        int productId = userInput.nextInt();

        Product matchedProduct = inventory.get(productId);
        if (matchedProduct == null){
            System.out.println("we don't carry that product");
            return;
        }
        System.out.printf("We carry %s and the price is $%.2f\n",
                matchedProduct.getName(), matchedProduct.getPrice());
    }

    public static void loadInventory() {
        try {
            FileReader inventoryFile = new FileReader("src/main/resources/inventory.csv");
            BufferedReader inventoryReader = new BufferedReader(inventoryFile);

            String input;

            while ((input = inventoryReader.readLine()) != null){
                String[] productInfo = input.split("\\|");
                int id = Integer.parseInt(productInfo[0]);
                String name = productInfo[1];
                double price = Double.parseDouble(productInfo[2]);

                Product product = new Product(id,name,price);
                inventory.put(id,product);
            }
            inventoryReader.close();
        } catch (IOException e) {
            System.out.println("ERROR: Could not read the inventory file");
            e.printStackTrace();
        }
    }
}
