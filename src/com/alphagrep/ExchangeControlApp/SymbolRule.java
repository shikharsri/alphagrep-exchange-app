package com.alphagrep.ExchangeControlApp;

/***
 * @Desciption : This Class contains rule for Price
 * Rule : If price is greater than x for symbol xyz then reject
 */
class SymbolRule implements RuleI<OrderRequest, OrderResponse> {
    Utility utility = new Utility();
    private String symbol;

    public SymbolRule(String symbol) {
        this.symbol = symbol;
    }

    public boolean matches(OrderRequest request) {
        return request.getSymbol().contains(symbol);
    }

    public OrderResponse process(OrderRequest order) {
        System.out.println(this.getClass().getTypeName().substring(this.getClass().getTypeName().lastIndexOf(".") + 1) + " satisfied");
        OrderResponse response = utility.setDefaultValueOfResponse(order);
        response.setResponseType("NEW_ORDER_CONFIRM#TRADE_CONFIRM");
        return response;
    }
}