package com.alphagrep.ExchangeControlApp;

/***
 * @Desciption : This Class contains rule for Sell Order
 * Rule : If request type is DUPLICATE then generate REJECT
 */
class DuplicateOrderRule implements RuleI<OrderRequest, OrderResponse> {
    Utility utility = new Utility();
    private String requestType;
    public DuplicateOrderRule(String requestType) {
        this.requestType = requestType;
    }

    public boolean matches(OrderRequest request) {
        return (request.getRequestType().equals("DUPLICATEORDER")) ;
    }

    public OrderResponse process(OrderRequest order) {
        System.out.println(this.getClass().getTypeName().substring(this.getClass().getTypeName().lastIndexOf(".")+1) + " satisfied");        OrderResponse response = utility.setDefaultValueOfResponse(order);
        response.setResponseType("REJECT_ORDER_CONFIRM");
        response.setExchTs(utility.getTimeStamp());
        response.setChildResponseType("DUPLICATE_ORDER_REJECT_MIDDLE");
        response.setErrorCode(1);
        response.setErrorMessage("DUPLICATE ORDER REJECT");
        return response;
    }

}
