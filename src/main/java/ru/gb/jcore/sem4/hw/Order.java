package ru.gb.jcore.sem4.hw;

import java.util.HashMap;

public class Order {
    static int orderNumber;
    private Customer customer;
    private Product product;
    private int amount;
    private int orderID;

    public Order(Customer customer, Product product, int amount) {
        this.customer = customer;
        this.product = product;
        this.amount = amount;
        this.orderID = ++orderNumber;
    }

    /**
     * Совершение покупки с проверкой покупателя, товара, кол-ва товара
     * @param customers массив авторизованных покупателей
     * @param customer покупатель, совершающий покупку
     * @param products массив всех доступных товаров
     * @param product покупаемый товар
     * @param amount покупаемое количество товара
     * @return метод возвращает заказ (покупатель, товар, единиц товара)
     * @throws CustomerException
     */
    public static Order makePurchase(Customer[] customers, Customer customer, Product[] products, Product product, int amount) throws
            CustomerException {

        // обработка ProductException
        try {
            if (!isProductExists(products, product))
                throw new ProductException("Товар: " + product.toString() + " - не существует. Покупка отменяется.");
        } catch (ProductException e){
            e.printStackTrace();
            return null;
        }

        // обработка AmountException
        try {
            if (amount >= 100 || amount <= 0) {
                StringBuilder sb = new StringBuilder("Товар:[");
                sb.append(product.toString());
                sb.append("] - ");
                sb.append(amount);
                sb.append("ед.: нереальное кол-во товара. Будет продана 1ед. товара.");
                throw new AmountException(sb.toString());
            }
        } catch (AmountException e) {
            e.printStackTrace();
            amount = 1;
        }

        // при исключении CustomerException пробрасываем его наверх в вызываемый метод
        if (!isCustomerExists(customers, customer))
            throw new CustomerException(customer.toString() + " не существует.");


        return new Order(customer, product, amount);
    }

    /**
     * Проверка валидности покупателя
     * @param customers массив авторизованных покупателей
     * @param checkCustomer проверяемый покупатель
     * @return true | false
     */
    public static boolean isCustomerExists(Customer[] customers, Customer checkCustomer) {
        int count = 0;
        for (Customer c : customers) {
            if (c.equals(checkCustomer)) count++;
        }
        return (count > 0) ? true : false;
    }

    /**
     * Проверка существования товара в базе
     * @param products массив товаров
     * @param checkProduct проверяемый товар
     * @return true | false
     */
    public static boolean isProductExists(Product[] products, Product checkProduct) {
        int count = 0;
        for (Product p : products) {
            if (p.equals(checkProduct)) count++;
        }
        return (count > 0) ? true : false;
    }


    @Override
    public String toString() {
        String title = String.format("Покупка #%s:", orderID);
        StringBuilder sb = new StringBuilder(title + System.lineSeparator());
        for (int i = 0; i < title.length(); i++) {
            sb.append("-");
        }
        sb.append(System.lineSeparator());
        sb.append(customer.toString());
        sb.append(System.lineSeparator());
        sb.append(product.toString());
        sb.append(" - ");
        sb.append(this.amount);
        sb.append("шт.");
        sb.append(System.lineSeparator());
        return sb.toString();
    }
}
