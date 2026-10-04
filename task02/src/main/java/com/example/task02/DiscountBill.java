package com.example.task02;

public class DiscountBill extends Bill {
    private int discount;

    public DiscountBill (int discount){
        this.discount = discount;
    }

    public int getDiscount(){
        return discount;
    }

    public long getDiscountSum() {
        long fullPrice = getPrice();
        return fullPrice * discount / 100;
    }

    @Override
    public long getPrice(){
        long fullPrice = super.getPrice();
        return fullPrice - fullPrice * discount / 100;
    }

}
