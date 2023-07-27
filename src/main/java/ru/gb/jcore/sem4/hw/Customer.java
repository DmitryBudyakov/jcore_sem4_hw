package ru.gb.jcore.sem4.hw;

public class Customer {
    private String fio;
    private int bithYear;
    private String phone;

    public Customer(String fio, int bithYear, String phone) {
        this.fio = fio;
        this.bithYear = bithYear;
        this.phone = phone;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getBithYear() {
        return bithYear;
    }

    public void setBithYear(int bithYear) {
        this.bithYear = bithYear;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Покупатель:[");
        sb.append(this.fio);
        sb.append(", ");
        sb.append(this.bithYear);
        sb.append(", ");
        sb.append(this.phone);
        sb.append("]");
        return sb.toString();
    }
}
