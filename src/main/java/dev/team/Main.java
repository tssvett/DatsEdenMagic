package dev.team;

public class Main {
    public static void main(String[] args) {
        String token = System.getenv().get("TOKEN");
        System.out.println(token);
    }
}
