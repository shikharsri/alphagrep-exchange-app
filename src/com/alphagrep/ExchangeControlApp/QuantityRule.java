package com.alphagrep.ExchangeControlApp;

/***
 * @Desciption : This Class contains rule for Quantity
 * Rule : If the qty of an order is multiple of x then generate NEW_ORDER_CONFIRM
 */
class QuantityRule implements RuleI<OrderRequest, OrderResponse> {
    Utility utility = new Utility();
    private int multiple;

    public QuantityRule(int multiple) {
        this.multiple = multiple;
    }

    public boolean matches(OrderRequest request) {
        return request.getQuantity() % multiple == 0;
    }

    public OrderResponse process(OrderRequest order) {
        System.out.println(this.getClass().getTypeName().substring(this.getClass().getTypeName().lastIndexOf(".")+1) + " satisfied");
        OrderResponse response = utility.setDefaultValueOfResponse(order);
        response.setResponseType("NEW_ORDER_CONFIRM");
        response.setChildResponseType("NULL_RESPONSE_MIDDLE");
        return response;
    }
}


