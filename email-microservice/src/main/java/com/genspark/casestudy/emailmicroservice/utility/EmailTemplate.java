package com.genspark.casestudy.emailmicroservice.utility;

public class EmailTemplate {

    public static final String TEXT = """
            The Following is a receipt of your recent order with us.\s

            Order date: %s
            Order Confirmation#: %s
            Customer Name: %s
            Customer Email: %s
            Customer Address: %s
            Order Item Name: %s
            Order Item Qty: %s
            Order Item Price: $%s
            Order Total: $%s
            """;
}
