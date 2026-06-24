## Rates app

Your task is to create a single screen app that allows to buy BTC.
Final states for the screen see in the same folder.

### Requirements

- Allow only digits in amount input.
- Review layout XML and propose UI performance improvements.
- Review IdempotencyKeyCache, which is going to be used in multithreading application.
- Add a test for the logic of interacting with service.

### Extra requirements

- Add validation: no more than 100 BTC is allowed to be bought
  (disable button for invalid input).
- Allow decimal input and in
  CryptoExchangeViewModel#onAmountChanged
  convert decimal input to minors, where 1 BTC is ...
    - Example: if user enters 0.12, we send to server
      amount = 12_000_000.

### Notes

The overall focus points are: architecture, quality and code clarity.

It should be possible to launch the app and verify the result.

You can use UI pattern of your choice: MVVM, MVP, MVI.

Coroutines / RxJava usage is up to you, code template supports both approaches.

There aren't any DI libraries added to the project,
manual DI is sufficient for this assignment.

Project already contains some code to build on top of it /
change if needed, just please keep RatesService and its ...
should be treated as a remote data source.