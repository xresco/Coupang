package com.coupang.WEBPS001;

/**
 * Created by mindvalley on 15/05/2016.
 */
public class ComplexInvestment {
    SingleInvestment investment_company_1;
    SingleInvestment investment_company_2;
    SingleInvestment investment_company_3;

    public ComplexInvestment() {
    }

    public ComplexInvestment(SingleInvestment i1, SingleInvestment i2, SingleInvestment i3) {
        investment_company_1 = i1;
        investment_company_2 = i2;
        investment_company_3 = i3;
    }

    public void copyValues(ComplexInvestment ci) {
        investment_company_1 = ci.investment_company_1;
        investment_company_2 = ci.investment_company_2;
        investment_company_3 = ci.investment_company_3;
    }

    public int calculateProfit() {
        try {
            return investment_company_1.getProfit() +
                    investment_company_2.getProfit() +
                    investment_company_3.getProfit();
        }catch (NullPointerException e){
            return 0;
        }
    }
}
