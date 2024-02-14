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