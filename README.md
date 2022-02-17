# Market Price Handler
### Assumptions:
1. The timestamps provided start with days, not months (pattern: `dd-MM-yyyy HH:mm:ss:SSS`).
2. The timestamps can be converted to Unix time (they are all after 00:00:00 UTC on 1 January 1970).
3. Latest in "latest price" is understood as referring to timestamps, not positions in the feed.
4. Precision of the commissioned price should not be lost (unless too great to be handled by `BigDecimal` Java type).
5. There can be untruncated zeros.
6. A price may refer to a bid, an ask as well as both bid and ask (can be confusing but exactly as stated in the requirements).
7. Whitespaces from the input feed are not preserved in the output when commissions are applied.
8. The instrument could be an enum with values: EUR/USD, GBP/USD and
   EUR/JPY.
### Comments:
1. The subscriber listed in requirements is implemented in `MessageListener`. 
2. Commission logic is in `CommissionProcessor` (`ProcessorService` interface).
3. `LatestMarketData` can serve as a REST endpoint later on. Please note that `LatestMarketData` itself does not contain any commission logic.
### Execution
To run tests execute:
        ```
       ./gradlew clean test
        ```.
### Environment used

       java version "12" 2019-03-19
       Java(TM) SE Runtime Environment (build 12+33)                       
       Java HotSpot(TM) 64-Bit Server VM (build 12+33, mixed mode, sharing)

       Gradle wrapper 7.4
   	
       Windows 10