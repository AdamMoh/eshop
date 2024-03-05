# Eshop Reflection

Link to the eshop app -> [Click Here!](https://adprogeshop-adammoheshop.koyeb.app/product/list)

## Reflection 1 - Module 1
The Java Springboot command states that MVC is used. This keeps the view and the models completely apart. We integrate the view and model with the controller since we apply the MVC paradigm. In terms of clean code, I've already started implementing some of the concepts, including giving variables meaningful names and making logic straightforward so that future developers may easily continue writing the code.  Because the frontend and backend are two distinct components, one benefit of MVC is that each may be built or modified independently. This strategy is a great example of concern separation.

## Reflection 2 - Module 2
1. Testing my features with positive and negative tests makes it easy to determine whether they are implemented correctly and to investigate any flaws that have not yet been discovered. The quantity of tests required is determined by the number of functions included in the application. Even 100% code coverage does not ensure that there are no defects in the code because mistakes can still be made during testing, in my opinion.

2. Given that the new test is performing comparable tasks, it may be repetitious. In order to incorporate the earlier setups and instance variables, we can refactor the new functional suites.

## Reflection 1 - Module 2
1. I got an issue when creating a unit test for the delete function. When running the test, I got an error all the time on the delete function unit test. After I investigated the function, I got slipped for the return value (a string); it should be the product, so I can use the `when` function to test it instead of `DoNothing` function.
2. Yes, I think it's already met the definition of CI/CD because there are already JUnit tests and SonarCloud for the CI. After that, we deploy it to koyeb for CD.

## Reflection 1 - Module 3
1. I have so far used the SRP (Single Responsibility Principle), which states that every module needs its own MVC in order to make code maintenance easier in the event that a bug comes during application testing. In this case, all of the structure—such as the model, repository, and services—is already separate for each module, with the exception of the controller, which currently serves three modules (Home, Car, and Product), DIP (Dependency Inversion Principle) is the second, in order to make the code flexible and use the separation of concerns approach, I create an interface for each repository so that every concrete implementation depends on the high-level modules. The concept prefers the use of abstraction over actual implementations, such as abstract classes and interfaces, Lastly, when generating a new Product, I used the Open-Close Principle (OCP) for the `Product` module. This allowed me to change the Product's id without modifying the original code. I believe the remaining S.O.L.I.D. principles have already been applied from the former tutorial.
2. There are a lot of advantages using SOLID principles, one of the advantage using SOLID principle is the code is more flexible and can easily understand for the next developer that want to continue the project. For example, the new developer can easily make a new implementation based on interfaces class without changing the current implementation.
3. The following developer finds it difficult to understand the disadvantages of violating to SOLID principles; for instance, they must understand the implementation that the prior developer had built and continuously request that the function that requires modification be modified. 