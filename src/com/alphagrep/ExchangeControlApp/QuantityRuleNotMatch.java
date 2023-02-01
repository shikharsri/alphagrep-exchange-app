package com.alphagrep.ExchangeControlApp;

/***
 * @Desciption : This Class contains rule for Quantity
 * Rule : If the qty of an order is not multiple of x then generate REJECT
 */
class QuantityRuleNotMatch implements RuleI<OrderRequest, OrderResponse> {
    Utility utility = new Utility();
    private int multiple;

    public QuantityRuleNotMatch(int multiple) {
        this.multiple = multiple;
    }

    public boolean matches(OrderRequest request) {
        return request.getQuantity() % multiple != 0;
    }

    public OrderResponse process(OrderRequest order) {
        System.out.println(this.getClass().getTypeName().substring(this.getClass().getTypeName().lastIndexOf(".")+1) + " satisfied");        OrderResponse response = utility.setDefaultValueOfResponse(order);
        response.setResponseType("REJECT");
        response.setExchTs(utility.getTimeStamp());
        response.setChildResponseType("CANCEL_ORDER_REJECT_MIDDLE");
        return response;
    }
}


