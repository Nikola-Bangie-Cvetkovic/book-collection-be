package com.app.api.tools;

import com.app.api.entity.Book;

import java.util.List;

public class Tools {
    public static void printLogo() {
        System.out.println("\t******************************\n");
        System.out.println("\t\t::::::   ::::::   ::::::");
        System.out.println("\t\t  ::     ::       ::");
        System.out.println("\t\t  ::     ::       ::::");
        System.out.println("\t\t  ::     ::       ::");
        System.out.println("\t\t::::::   ::::::   ::::::");
        System.out.println("\n\t******************************\n");
    }

    public static String toJson(List list, int size) {
        int counter = 0;
        String toJson = "";
        toJson += "[";
        for (Object obj : list) {
            toJson += obj.toString();
            if (counter < size - 1) {
                toJson += ",";
            }
            counter++;
        }
        toJson += "]";
        return toJson;
    }
}
