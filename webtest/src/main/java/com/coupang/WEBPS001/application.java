package com.coupang.WEBPS001;

import java.util.Random;

public class application {

    public static void main(String[] args) {
        String[] companies = {"A", "B", "C"};
        int max_investment = 200;
        int[][] arr = new int[max_investment+1][4];
        Random random = new Random();

        ComplexInvestment max_complex_investment = new ComplexInvestment();


        for (int i = 1; i < max_investment+1; i++) {
            arr[i][0] = i;
            arr[i][1] = i + random.nextInt(4 * i + 1);
            arr[i][2] = i + random.nextInt(4 * i + 1);
            arr[i][3] = i + random.nextInt(4 * i + 1);
            System.out.println(arr[i][0] + " " + arr[i][1] + " " + arr[i][2] + " " + arr[i][2] + " ");
        }

        for (int i = 0; i < max_investment+1; i++) {
            for (int j = 0; j < max_investment+1; j++) {
                for (int k = 0; k < max_investment+1; k++) {
                    if (arr[i][0] + arr[j][0] + arr[k][0] == max_investment) {
                        SingleInvestment single_investment1 = new SingleInvestment(companies[0], arr[i][0], arr[i][1]);
                        SingleInvestment single_investment2 = new SingleInvestment(companies[1], arr[j][0], arr[j][2]);
                        SingleInvestment single_investment3 = new SingleInvestment(companies[2], arr[k][0], arr[k][3]);

                        ComplexInvestment tmp_ci = new ComplexInvestment(single_investment1,
                                single_investment2,
                                single_investment3);
                        if (tmp_ci.calculateProfit() > max_complex_investment.calculateProfit()) {
                            max_complex_investment.copyValues(tmp_ci);
                        }
                    }
                }
            }
        }

        System.out.println(max_complex_investment.investment_company_1);
        System.out.println(max_complex_investment.investment_company_2);
        System.out.println(max_complex_investment.investment_company_3);

        int total_profit = max_complex_investment.investment_company_1.getProfit() +
                max_complex_investment.investment_company_2.getProfit() +
                max_complex_investment.investment_company_3.getProfit();
        System.out.println("Maximum Profit: " + total_profit / 100.0 + " million won");


    }
}
