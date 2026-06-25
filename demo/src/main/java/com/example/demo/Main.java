package com.example.demo;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();
   static int jackpot = 100;
    public static void main(String[] args) {
        sc.useLocale(java.util.Locale.US);
        int bet;
        int winnings;
        int balance;
        int spins = 0;
        int wins = 0;
        int loses = 0;
        String[] row;
        System.out.println("*****************");
        System.out.println("Welcome to java slot machine\nIf you win you will get your bet multiplied!");
        System.out.println("Hit 3 STARS (⭐ ⭐ ⭐) to win the GRAND JACKPOT!");
        System.out.println("Tip: Enter 0 if you want to leave the game");
        System.out.println("*****************");
        System.out.print("How much balance you have? ");
        balance = sc.nextInt();
        while(balance>0){
            System.out.println("Grand Jackpot = $"+jackpot);
            System.out.println("Your balance = $"+balance);
            System.out.print("How much bet you want to pay? ");
            bet = sc.nextInt();
            if(bet == 0){
                System.out.println("Game Over");
                break;
            }
            if(bet <= 0 || bet > balance){
                System.out.println("Error! You can't bet that much");
                continue;
            }
            else {
                balance -= bet;
                spins++;
                System.out.println("Spinning...");
                row = printrow();
                winnings = payout(row,bet);
                balance += winnings;
                if(winnings > 0){
                    wins++;
                }
                else {
                    loses++;
                }
                System.out.println("*****************");
            }
        }
        if(balance <= 0){
            System.out.println("Game over. you ran out of money");
        }
        System.out.println("*===============================*");
        System.out.println("📋   Result List   📋");
        System.out.println("Total Spins Played : " + spins);
        System.out.println("Total Wins 🎉      : " + wins);
        System.out.println("Total Losses ❌    : " + loses);
        System.out.println("*=================================*");
        System.out.println("Thank you for playing!");
        sc.close();
    }
    static  String[] printrow(){
        String[] symbols = {"🍉","⭐","🔔","🍋","🍒"};
        String[] row = new String[3];
        for(int i = 0 ; i < 3 ; i++){
            row[i] = symbols[random.nextInt(5)];
        }
        System.out.println("*****************");
        System.out.println(row[0]+" | "+row[1]+" | "+row[2]);
        System.out.println("*****************");
        return row;
    }
    static int payout(String[] row,int bet){
        if(row[0].equals(row[1]) && row[1].equals(row[2])){
            int multiple;
            switch(row[0]){
                case "🍒" : multiple = 2; break;
                case "🍉" : multiple = 3; break;
                case "🍋" : multiple = 5; break;
                case "🔔" : multiple = 10; break;
                case "⭐" : multiple = 20; break;
                default : multiple = 1; break;
            }
            int totalpayout = bet*multiple;
             System.out.println("You won!");
             if(row[0].equals("⭐")){
                 System.out.println("You Hit The Jackpot!!!!");
                 System.out.println("Jackpot Bonus = $"+jackpot);
                 totalpayout += jackpot;
                 jackpot = 100;
             }

            System.out.println("your payout = $"+ totalpayout);
            return totalpayout;
        }
        else {
            int tax = (int) (bet*0.10);
            jackpot += (tax == 0) ? 1 : tax;
            System.out.println("You lose! You lost: $" + bet);
            return 0;
        }

    }
}