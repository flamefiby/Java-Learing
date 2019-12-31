/**
 *  Author: Dong Wu
 *  Time: 31 Dec 2019
 *  Purpose: A simple dealership system as an example for learning purpose to understanding
 *  class, packages and array list
 *  References: https://github.com/geektime-geekbang/LetsJava/tree/master/第二章%20Java面向对象编程/09.%20打造一个小超市
 */
package company;

import company.person.Customer;
import company.store.Dealership;
import company.store.Merchandise;

import java.util.Scanner;

public class RunDealership {
    public static void main(String[] args) {
        // new dealership
        Dealership dealership = new Dealership();
        // give the information to the dealership
        dealership.name = "5 Stars Dealership";
        dealership.address = "Barrie, ON";
        dealership.phoneNumber = "70550000099";
        dealership.parkingCount = 30;
        // set up there are 200 different merchandises in the dealership
        dealership.merchandises = new Merchandise[200];
        // make the sold merchandises are equal to the merchandises
        dealership.merchandiseSold = new int[dealership.merchandises.length];

        // make another array to store the merchandises
        Merchandise[] all = dealership.merchandises;

        for (int i = 0; i < all.length; i++) {
            // give the value to each merchandise
            Merchandise m = new Merchandise();
            m.name = "Merchandise" + i;
            m.count = 200;
            m.purchasePrice = Math.random() * 200;
            m.soldPrice = m.purchasePrice * (1 + Math.random());
            m.id = "ID" + i;
            // make they have the exactly same value
            all[i] = m;
        }
        // Greeting
        System.out.println("Welcome to the dealership!");
        // use the boolean variable to control the running of the dealership
        boolean open = true;
        Scanner in = new Scanner(System.in);
        while (open) {
            // print the detail information of dealership
            System.out.println("Welcome to " + dealership.name);
            System.out.println("Address: " + dealership.address);
            System.out.println("TEL: " + dealership.phoneNumber);
            System.out.println("Parking spot number: " + dealership.parkingCount);
            System.out.println("The sum of today incoming: " + dealership.incomingSum);
            System.out.println("We have " + dealership.merchandises.length + " services");

            Customer customer = new Customer();
            // give each customer a random ID, money, and either the car is breakdown or not
            customer.name = "Customer ID " + ((int) (Math.random() * 10000));
            customer.money = (1 + Math.random()) * 1000;
            customer.carBreakDown = Math.random() > 0.5;

            // if the customer drive to the store
            if (!customer.carBreakDown) {
                // give the number of parking spot if we still have available spot
                if (dealership.parkingCount > 0) {
                    System.out.println("Welcome to the " + dealership.name + ", Your parking spot number is " + dealership.parkingCount);
                    dealership.parkingCount--;
                } else {
                    // let customer know that the parking lot is currently full
                    System.out.println("Welcome to the " + dealership.name + ". The parking lot is currently full so please wait.");
                    continue;
                }
            } else {
                // arrange the customer to talk to supervisor if they have a breakdown car
                System.out.println("Welcome to the " + dealership.name + ", Your vehicle is in the service department, please talk to one of our supervisor");
            }
            // totalCost to save the data how much money did the customer spend in the store
            double totalCost = 0;
            while (true) {
                // let the customer choose the service
                System.out.println("We have " + all.length + " please input the service number that you want below");
                // get the input of the customer
                int index = in.nextInt();
                // input number < 0 will quit the system
                if (index < 0) {
                    break;
                }
                // if customer input wrong number (greater than the services the dealership have)
                if (index >= all.length) {
                    System.out.println("Sorry we do not have the service you selected please input the service number from 0 to " + (all.length - 1));
                    continue;
                }
                // to save the merchandise that customer select
                Merchandise m = all[index];
                // print out the service detail and ask the labour hour
                System.out.println("The service you selected is " + m.name + " the labour hour price is " + m.soldPrice + " please input the hour you need below");
                // get the customer input
                int numToBuy = in.nextInt();
                // check the value of the input
                if (numToBuy <= 0) {
                    System.out.println("The labour hour is invalid, please select the service again");
                    continue;
                }
                // check if the store has the labour hour
                if (numToBuy > m.count) {
                    System.out.println("Sorry, the service you selected is currently sold out, please continue to slect and talk to one of our supervisor when you finish");
                    continue;
                }
                // check if customer bring enough money
                if (numToBuy * m.soldPrice + totalCost > customer.money) {
                    System.out.println("Sorry the payment has been declined, please select different service or talk to one of our supervisor ");
                    continue;
                }
                // get the total cost of each customer
                totalCost = numToBuy * m.soldPrice;
                // delete  the labour hour
                m.count -= numToBuy;
                // get the selling information
                dealership.merchandiseSold[index] += numToBuy;

            }
            // calculate the balance of the customer
            customer.money -= totalCost;
            // if customer drive here add one parking spot
            if (!customer.carBreakDown) {
                dealership.parkingCount++;
            }
            // summary of the customer about the total cost in store
            System.out.println("Customer" + customer.name + " spend " + totalCost);
            // add the number to the dealership incoming
            dealership.incomingSum += totalCost;
            // check if the owner want to keep open or not
            System.out.println("Would you like to keep open?");
            // get the owner input
            open = in.nextBoolean();

        }
        // if owner choose close print out the summary of the dealership
        System.out.println("Dealership is close");
        System.out.println("The total turnover" + dealership.incomingSum + ". Please see the details below: ");

        for (int i = 0; i < dealership.merchandiseSold.length; i++) {
            Merchandise m = all[i];
            int numSold = dealership.merchandiseSold[i];
            if (numSold > 0) {
                double incomming = m.soldPrice * numSold;
                double netIncomming = (m.soldPrice - m.purchasePrice) * numSold;
                System.out.println(m.name + "has been sold" + numSold + ". The sales volume "
                        + incomming + ", and net profit is " + netIncomming);
            }
        }

    }
}
