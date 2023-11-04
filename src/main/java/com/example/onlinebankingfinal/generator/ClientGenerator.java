package com.example.onlinebankingfinal.generator;

import com.example.onlinebankingfinal.model.Client;
import com.github.javafaker.Faker;

public class ClientGenerator {
    private final Faker faker;

    public ClientGenerator() {
        this.faker = new Faker();
    }

    public Client generateClient() {
        String firstName = faker.name().lastName();
        String lastName = faker.name().firstName();
        String taxCode = faker.idNumber().valid();
        String phone = faker.phoneNumber().cellPhone();
        String address= faker.address().fullAddress();
        String email = faker.internet().emailAddress();

        return new Client(firstName, lastName, taxCode, phone, address, email);
    }
}
