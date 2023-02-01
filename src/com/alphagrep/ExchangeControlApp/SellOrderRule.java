package com.alphagrep.ExchangeControlApp;


/***
 * @Desciption : This Class contains rule for Sell Order
 * Rule : If request type is SELL then generate SELLORDER
 */
class SellOrderRule implements RuleI<OrderRequest, OrderResponse> {
    Utility utility = new Utility();
    private String requestType;
    public SellOrderRule(String requestType) {
        this.requestType = requestType;
    }

    public boolean matches(OrderRequest request) {
        return (request.getRequestType().equals("SELLORDER")) ;
    }

    public OrderResponse process(OrderRequest order) {
        System.out.println(this.getClass().getTypeName().substring(this.getClass().getTypeName().lastIndexOf(".")+1) + " satisfied");        OrderResponse response = utility.setDefaultValueOfResponse(order);
        response.setResponseType("NEW_ORDER_CONFIRM");
        response.setExchTs(utility.getTimeStamp());
        response.setChildResponseType("SELL_ORDER_MIDDLE");
        return response;
    }

}
