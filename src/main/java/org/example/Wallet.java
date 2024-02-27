package org.example;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.HashMap;

public class Wallet {
    private String owner;
    private ArrayList<String> cards;
    private HashMap<Integer, Integer> moneys;
    private HashMap<Integer, Integer> coins;
    int[] coinTypes = {100, 500, 1000};
    int[] moneyTypes = {1000, 2000, 5000, 10000, 20000, 50000, 100000};

    public Wallet(String owner) {
        this.owner = owner;
        this.cards = new ArrayList<>();
        this.moneys = new HashMap<>();
        for (int moneyType : moneyTypes){
            this.moneys.put(moneyType, 0);
        }
        this.coins = new HashMap<>();
        for(int coinType : coinTypes) {
            this.coins.put(coinType, 0);
        }
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public ArrayList<String> getCards(){
        return this.cards;
    }

    public void addCard(String card){
        this.cards.add(card);
    }

    public void removeCard(String card){
        this.cards.remove(card);
    }

    public HashMap<Integer,Integer> getCoins(){
        return coins;
    }

    private boolean isValidType(int coinOrMoney, int[] types){
        for(int coinOrMoneyType : types){
            if(coinOrMoney == coinOrMoneyType){
                return true;
            }
        }
        return false;
    }

    public void addCoin(Integer coin){
        if(isValidType(coin,coinTypes)){
            int currentValue = this.coins.get(coin);
            this.coins.put(coin, currentValue+1);
        }
    }

    public void takeCoin(Integer coin){
        if(isValidType(coin,coinTypes)){
            int currentValue = this.coins.get(coin);
            if (currentValue > 0){
                this.coins.put(coin, currentValue-1);
            }
        }
    }

    public HashMap<Integer, Integer> getMoneys(){
        return moneys;
    }

    public void addMoney(Integer money){
        if(isValidType(money,moneyTypes)){
            int currentValue = this.moneys.get(money);
            this.moneys.put(money, currentValue+1);
        }
    }

    public void takeMoney(Integer money){
        if(isValidType(money,moneyTypes)){
            int currentValue = this.moneys.get(money);
            if (currentValue > 0){
                this.moneys.put(money, currentValue-1);
            }
        }
    }

    public int getMoneyAvailable(){
        return this.calculateCoins() + this.calculateMoneys();
    }

    public int calculateCoins(){
        int total = 0;
        for(int key : coins.keySet()){
            total += key*this.coins.get(key);
        }
        return total;
    }

    public int calculateMoneys(){
        int total = 0;
        for(int key : moneys.keySet()){
            total += key*this.moneys.get(key);
        }
        return total;
    }

    public static void main(String[] args){
        Wallet myWallet = new Wallet("Caca");
        myWallet.addMoney(10000);
        myWallet.addMoney(5000);
        myWallet.addCoin(500);
        myWallet.addCard("Credit Card");
        myWallet.addCard("KTM");
        myWallet.removeCard("Credit Card");
        myWallet.takeCoin(500);
        myWallet.takeMoney(10000);
        System.out.println("Jumlah uang lembaran di dompet: " + myWallet.getMoneys());
        System.out.println("Jumlah uang koin di dompet: " + myWallet.getCoins());
        System.out.println("Kartu di dompet: " + myWallet.getCards());
        System.out.println("Total uang di dompet: " + myWallet.getMoneyAvailable());
    }
}
