package com.coupang.WEBPS001;

/**
 * Created by mindvalley on 15/05/2016.
 */
public class SingleInvestment {

    private String companyName;
    private int investment_amount;
    private int profit;

    public SingleInvestment(String companyName, int investment_amount, int profit) {
        this.companyName = companyName;
        this.investment_amount = investment_amount;
        this.profit = profit;
    }

    public int getProfit() {
        return profit;
    }

    public int getInvestment_amount() {
        return investment_amount;
    }

    public String getCompanyName() {
        return companyName;
    }

    public float getInvestmentRatio() {
        if (investment_amount == 0)
            return 0;
        return profit / (float) investment_amount;
    }

    @Override
    public String toString() {
        return companyName + " " + investment_amount + " " + profit;
    }
}
