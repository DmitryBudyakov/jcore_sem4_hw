package ru.gb.jcore.sem4.hw;

public class Main {

    public static void main(String[] args) {
        // Инициализация покупателей
        Customer customer1 = new Customer("Иванов И.И.", 1955, "89101234567");
        Customer customer2 = new Customer("Андреев О.П.", 1990, "89067654321");
        Customer[] customers = {customer1, customer2};

        // Инициализация товаров
        Product chocolate = new Product("Шоколад", 100);
        Product water = new Product("Вода без газа", 50);
        Product milk = new Product("Молоко", 80);
        Product cheese = new Product("Сыр", 70);
        Product butter = new Product("Масло сливочное", 110);
        Product[] products = {chocolate, water, milk, cheese, butter};

        // Инициализация массива заказов
        Order[] orders = new Order[5];

        // -------------------
        // Несуществующий покупатель
        Customer unknownCustomer = new Customer("Иванов М.П.", 1976, "89011234567");
        // Несуществуюший товар
        Product unknownProduct = new Product("Хлеб черный", 35);
        // --------------------

        /*
            Проверка на исключения из makePurchase и их обработка
            В Main отлавливаем только CustomerException.
            ProductException и AmountException обрабатываем в классе Order в статическом методе makePurchase
         */
        Order order1;
        Order order2;
        Order order3;
        Order order4;
        Order order5;

        try {
            // правильный заказ
            order1 = Order.makePurchase(customers, customer1, products, butter, 2);

            // симуляция исключений
            // Product Exception - заказ с несуществующим товаром
//            order1 = Order.makePurchase(customers, customer1, products, unknownProduct, 2);
            // AmountException - заказ с большим количеством товара
//            order1 = Order.makePurchase(customers, customer1, products, butter, 101);
            // CustomerException - заказ с неверным покупателем
//            order1 = Order.makePurchase(customers, unknownCustomer, products, butter, 2);

            order2 = Order.makePurchase(customers, customer1, products, milk, 3);
            order3 = Order.makePurchase(customers, customer2, products, chocolate, 5);
            order4 = Order.makePurchase(customers, customer2, products, cheese, 2);
            order5 = Order.makePurchase(customers, customer2, products, water, 4);

        } catch (CustomerException e) {
            e.printStackTrace();
            throw new CustomerException("Приложение завершило работу. Bye!");
        }

        orders[0] = order1;
        orders[1] = order2;
        orders[2] = order3;
        orders[3] = order4;
        orders[4] = order5;

        // вывод кол-ва совершенных покупок
        System.out.println("Сделано покупок: " + countOrders(orders));

        // вывод заказов в консоль
        printOrders(orders);

    }


    /**
     * Подсчет числа совершенных покупок
     *
     * @param orders
     * @return
     */
    public static int countOrders(Order[] orders) {
        int count = 0;
        for (int i = 0; i < orders.length; i++) {
            if (!(orders[i] == null)) count++;
        }
        return count;
    }


    /**
     * Вывод в консоль всех заказов из массива заказов
     *
     * @param orders
     */
    public static void printOrders(Order[] orders) {
        for (Order order : orders) {
            if (order != null)
                System.out.println(order.toString());
        }

    }

    /**
     * Вывод в консоль одного заказа
     *
     * @param order
     */
    public static void printOrder(Order order) {
        System.out.println(order.toString());
    }
}
