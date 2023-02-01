package com.alphagrep.ExchangeControlApp;

import java.util.*;

public class ExchangeControlApp {


    public static void main(String[] args) {

        Utility utility = new Utility();
        String requestFile = "requests.txt";
        String responseFile = "response.txt";
        String goldenFile = "golden_response.txt";
        List<OrderRequest> requests = utility.parseRequestsFromFile(requestFile);


            RuleEngine ruleEngine = new RuleEngine();
            ruleEngine
                    .registerRule(new SellOrderRule("SELLORDER"))
                    .registerRule(new DuplicateOrderRule("DUPLICATEORDER"))
                    .registerRule(new PriceRule(158, "IFEU"))
                    .registerRule(new SymbolRule("IFEU"))
                    .registerRule(new QuantityRuleNotMatch(10))
                    .registerRule(new QuantityRule(5))
                    .registerRule(new ExactPriceRule(157));


            utility.generateResponse(requests, ruleEngine, responseFile);
            utility.compareResponse(responseFile, goldenFile);






    }


}
