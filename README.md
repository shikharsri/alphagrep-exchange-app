# Application Requirement
We need you to implement an application which –
1. Receives the incoming requests
2. Parses the requests
3. Evaluates the configured rules
4. Send the response back as per the rule outcome. The rule can be configured to send more
than one response to a request.

Condition : Avoid usage of any 3rd party libraries/packages


# Architecture Decision
Below are the implementation steps of above requirement

**Receiving incoming requests:**  The application can use a TCP socket or a shared memory mechanism to receive incoming requests. Alternatively, it can also read requests from a file.

**Parsing requests:** The application can use regular expressions or a dedicated library to parse the incoming requests and extract relevant information, such as OrderID, Symbol, Price, etc using the Pojo Classes

**Evaluating rules:** The application can use a rule engine, such as Drools or PyRule, to evaluate the configured rules against the parsed request. The rule engine can be configured to match certain criteria and generate specific responses. 
**Since usage of Third Party libraries are avoided, we can Java Generics to create Rule engine**

**Sending responses:** The application can use the same transport mechanism as the one used to receive requests to send responses. The rule engine can be configured to send more than one response to a request.

**Compare Response:** A script can be written to compare the received responses with the responses stored in a golden copy source. Any difference can be highlighted in a file, email, or over the console using the scripting language.

# Techs and Strategies used
**Language:** We decided to use Java to solve the same. Reasons being 
- Java is faster to execute.  
- Have more robust JDBC connection to database which can be added in future
- Have supporting libraries like SpringBoot, Drools etc which can be used quickly to implement the WebService and rules engine
We have used Java 8  to compile this but and Java higher version can be used.

**Build Tool:** : We have not used any Build tool like Maven or Gradle to import other libraries . But same can be added for better Dependency management.

**Rule Engine:** We had options of Drools and other rule engine but to avoid third party library uses, we are going with *Java Generics*.

**Networking mechanism:** We have shared data over file for ease in prototype stage. Same can be enhanced to exchange data over Webservice using Springboot, through a common database to even store history or via TCP connections.

# Framework explanation

- **Main Class:** The ***ExchangeControlApp class is the main class of the application, which receives incoming requests, parses them, evaluates the rules, sends the responses back to the clients, and compares the responses with a golden copy.***
- **Source File:** "response.txt" is the source file for the application
- **Golden Source:** For comparision purpose the Expected Valid Response is stored in "golden_response.txt" file
- **Rule Engine:** All the rules will be getting registered using this file. It has role to process all the rules in order.
- **Rule:** Rules has be configured in different Java Generics classes like Price Rule, ExactPrice Rule, Quantity Rule etc
- **Utility:** Utility class is used to stored all functional helping methods required while execution.
- **OrderRequest and OrderResponse:** Contains Pojo classes for Request and Response after parsing.

# Execution Guidline
- Import the project as Java project
- Please run the ExchangeControlApp class using Java to start the application.
It will
- Start the application
- Get Request from "request.txt" file and parses the same.
- Apply Rules on the request using 'RuleEngine' 
- Send the response to "response.txt" file
- Compare response from "golden_respnse.txt" file and prints the difference on console

# Rule Engine

## Rules introduces
- **Quantity Rule:** If the qty of an order is multiple of x then generate NEW_ORDER_CONFIRM
- **Quantity Rule Not Matching:** If the qty of an order is not multiple of x then reject
- **Symbol Rule:**  If price is greater than x for symbol xyz then reject
- **Price Rule:** If price is 123 then generate reject
- **SellOrder Rule:** If Request Type is SELLORDER then response type will be SELL
- **Duplicate Order Rule:** If Request Type is DUPLICATEORDER then response type will be REJECT


**For ease Console Output is also published in "consoleOutput.txt" file.**

Point to note :
- For comparision, few differences are ignored like TimeStamp, Exchange_Order_Id, ExchTs as these are runtime values and will get change with each execution.




