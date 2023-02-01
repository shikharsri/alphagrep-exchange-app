package com.alphagrep.ExchangeControlApp;

/***
 * @Desciption : This Class contains rule for Price
 * Rule : If price is 123 then generate reject
 */
class PriceRule implements RuleI<OrderRequest, OrderResponse> {

    Utility utility = new Utility();
    private int price;
    private String symbol;
    public PriceRule(int price, String symbol) {
        this.price = price;
        this.symbol = symbol;
    }

    public boolean matches(OrderRequest request) {
        return (request.getPrice() > price && request.getSymbol().contains(symbol)) ;
    }

    public OrderResponse process(OrderRequest order) {
        System.out.println(this.getClass().getTypeName().substring(this.getClass().getTypeName().lastIndexOf(".")+1) + " satisfied");        OrderResponse response = utility.setDefaultValueOfResponse(order);
        response.setResponseType("REJECT");
        response.setExchTs(utility.getTimeStamp());
        response.setChildResponseType("CANCEL_ORDER_REJECT_MIDDLE");
        return response;
    }

}
