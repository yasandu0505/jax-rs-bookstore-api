/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookstore.storage;

/**
 *
 * @author yasandu
 */

import com.bookstore.model.*;
import java.util.*;

public class DataStore {
    public static Map<Integer, Book> books = new HashMap<>();
    public static Map<Integer, Author> authors = new HashMap<>();
    public static Map<Integer, Customer> customers = new HashMap<>();
    public static Map<Integer, List<CartItem>> carts = new HashMap<>();
    public static Map<Integer, List<Order>> orders = new HashMap<>();
    
}
