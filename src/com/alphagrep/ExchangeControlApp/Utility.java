package com.alphagrep.ExchangeControlApp;

import java.io.*;
import java.util.*;

public class Utility {


    public List<OrderRequest> parseRequestsFromFile(String fileName) {
        List<OrderRequest> requests = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                OrderRequest request = new OrderRequest();
                for (String part : parts) {
                    String[] keyValue = part.split(":");
                    if (keyValue[0].equals("RequestType")) {
                        request.setRequestType(keyValue[1]);
                    } else if (keyValue[0].equals("OrderID")) {
                        request.setOrderId(Long.valueOf(keyValue[1]));
                    } else if (keyValue[0].equals("Token")) {
                        request.setToken(keyValue[1]);
                    } else if (keyValue[0].equals("Symbol")) {
                        request.setSymbol(keyValue[1]);
                    } else if (keyValue[0].equals("Side")) {
                        request.setSide(keyValue[1]);
                    } else if (keyValue[0].equals("Price")) {
                        request.setPrice(Double.parseDouble(keyValue[1]));
                    } else if (keyValue[0].equals("Quantity")) {
                        request.setQuantity(Integer.valueOf(keyValue[1]));
                    } else if (keyValue[0].equals("QuantityFilled")) {
                        request.setQuantityFilled(Integer.valueOf(keyValue[1]));
                    } else if (keyValue[0].equals("DisclosedQnty")) {
                        request.setDisclosedQuantity(Integer.valueOf(keyValue[1]));
                    } else if (keyValue[0].equals("TimeStamp")) {
                        request.setTimestamp(Long.valueOf(keyValue[1]));
                    } else if (keyValue[0].equals("Duration")) {
                        request.setDuration(keyValue[1]);
                    } else if (keyValue[0].equals("OrderType")) {
                        request.setOrderType(keyValue[1]);
                    } else if (keyValue[0].equals("Account")) {
                        request.setAccount(keyValue[1]);
                    } else if (keyValue[0].equals("Exchange")) {
                        request.setExchange(Integer.valueOf(keyValue[1]));
                    } else if (keyValue[0].equals("NumCopies")) {
                        request.setNumCopies(Integer.valueOf(keyValue[1]));
                    } else {
                        throw new RuntimeException("Unknown Type");
                    }
                }
                requests.add(request);
            }
            return requests;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while Parsing");
        }

    }

    public OrderResponse setDefaultValueOfResponse(OrderRequest request) {
        OrderResponse response = new OrderResponse();
        LinkedHashMap<String, Object> responseMap = new LinkedHashMap<String, Object>();
        response.setOrderId(request.getOrderId());
        response.setSymbol(request.getSymbol());
        response.setSide(request.getSide());
        response.setPrice(request.getPrice());
        response.setQuantity(request.getQuantity());
        response.setAccountId("bJEROM");
        response.setErrorCode(0);
        response.setTimestamp(getTimeStamp());
        Random random = new Random();

        response.setExchangeOrderId(random.nextLong());
        response.setChildResponseType("NULL_RESPONSE_MIDDLE");
        response.setDuration(request.getDuration());
        return response;

    }

    public Long getTimeStamp() {
        long timestamp = System.currentTimeMillis();
        return Long.valueOf(timestamp);
    }

    public void generateResponse(List<OrderRequest> requests, RuleEngine ruleEngine, String responseFile) {
        try {
            cleanup();
            for (OrderRequest request : requests) {
                OrderResponse response = ruleEngine.rule(request);
                LinkedHashMap<String, Object> responseMap = new LinkedHashMap<String, Object>();
                responseMap.put("ResponseType", response.getResponseType());
                responseMap.put("OrderID", response.getOrderId());
                responseMap.put("Symbol", response.getSymbol());
                responseMap.put("Side", response.getSide());
                responseMap.put("Price", response.getPrice());
                responseMap.put("Quantity", response.getQuantity());
                responseMap.put("AccountID", response.getAccountId());
                responseMap.put("ErrorCode", response.getErrorCode());
                responseMap.put("TimeStamp", response.getTimestamp());
                responseMap.put("Exchange_Order_Id", response.getExchangeOrderId());
                responseMap.put("ChildResponseType", response.getChildResponseType());
                responseMap.put("Duration", response.getDuration());
                responseMap.put("ExchTs", response.getExchTs());

                for (String rs : response.getResponseType().split("#")) {
                    responseMap.put("ResponseType", rs);
                    StringBuilder sb = new StringBuilder();
                    for (Map.Entry<String, Object> entry : responseMap.entrySet()) {
                        sb.append("|");
                        sb.append(entry.getKey() + ":" + entry.getValue());
                    }
                    writeToFile(sb.toString().replaceFirst("\\|", ""), responseFile);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(String response, String file) {
        try {
            File f = new File(file);
            if (!f.exists() && !f.isDirectory()) {
                f.createNewFile();
            }
            FileWriter myWriter = new FileWriter(f, true);
            myWriter.write(response);
            myWriter.write("\n");
            myWriter.close();
            System.out.println(response);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void cleanup() {
        File f = new File("response.txt");
        if (f.exists()) {
            f.delete();
        }
    }

    public void compareResponse(String actualFile, String expectedFile) {
        try {
            int counter = 0;
            int difference = 0;
            BufferedReader file1 = new BufferedReader(new FileReader(actualFile));
            BufferedReader file2 = new BufferedReader(new FileReader(expectedFile));

            List<String> differences = new ArrayList<>();
            String line1 = file1.readLine();
            String line2 = file2.readLine();

            while (line1 != null || line2 != null) {
                counter ++;
                if (line1 == null || line2 == null || !line1.equals(line2)) {
                    difference++;
                    String line1Arr[] = line1.split("\\|");
                    String line2Arr[] = line2.split("\\|");
                    if (line1Arr.length != line2Arr.length) {
                        System.out.println("[ERROR] Difference between fields of response and golden source");
                        System.out.println("Response : " + line1);
                        System.out.println("Golden Source : " + line2);
                    } else {
                        for (int i=0; i<line1Arr.length; i++) {
                            if (!line1Arr[i].equals(line2Arr[i]) &&
                                    !(line1Arr[i].contains("TimeStamp") || line1Arr[i].contains("Exchange_Order_Id")
                                    || line1Arr[i].contains("ExchTs")))
                            {
                                System.out.println("############-------------------------------------------------############");
                                System.out.println("[ERROR] Difference between fields of response and golden source for response no. " + counter);
                                System.out.println("Response : " + line1Arr[i] + "\nSource : " + line2Arr[i]);
                            }
                        }
                    }
                }

                line1 = file1.readLine();
                line2 = file2.readLine();
            }

            if (difference == 0) {
                System.out.println("The files are identical.");
            }

            file1.close();
            file2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}



