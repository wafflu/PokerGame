import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class Generics {
    @Test
    public void box(){
        Box<Fruit> sbox = new Box<>();
        sbox.add(new Apple());
        System.out.println(sbox.getList());
    }
}

class Box<T> {
    ArrayList<T> list = new ArrayList<>();

    void add(T item){
        list.add(item);
    }

    T get(int i){
        return list.get(i);
    }

    ArrayList<T> getList(){
        return list;
    }

    int size(){
        return list.size();
    }

    public String toString(){
        return list.toString();
    }
}

class Fruit {
    public String toString(){
        return "Fruit";
    }
}

class Apple extends Fruit{
    public String toString(){
        return "Apple";
    }
}

class Grape extends Fruit{
    public String toString(){
        return "Grape";
    }
}