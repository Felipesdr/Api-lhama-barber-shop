package com.lhamacorp.apibarbershop.utilities;

import java.util.Collection;
import java.util.Random;

public class RandomPicker {

    private static Random random = new Random();
    public static <T> T getRandomElementFromCollection(Collection<T> collection){

        if(collection == null || collection.isEmpty()){
            throw new IllegalArgumentException("The collection cannot be empty!");
        }

        int randomIndex = random.nextInt(collection.size());
        int i = 0;
        for(T element : collection){
            if (i == randomIndex){
                return element;
            }
            i++;
        }
        throw new IllegalStateException("Something went wrong while picking a random element.");
    }
}
