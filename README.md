# Mocking examples using Mockk (Kotlin)
This repository contains a dozen or so mocking examples using [Mockk](https://github.com/mockk/mockk) library.   
Repository contains only basic examples which are mostly used in day to day work. For more advanced topics please check official [Mockk documentation](https://mockk.io/)

All examples can be found in test directory: `src/test/kotlin`

Topics covered in examples: 
* types of Mocks (MockK, SpyK, RelaxedMock)
* object and static class mocking
* partial matching (matchers)
* hierarchical mocking
* slot capturing
* verification (verify block)
* private method mocking
* constructor mocking

Also, I highly recommend reading [Unraveling MockK’s black magic](https://chao2zhang.medium.com/unraveling-mockks-black-magic-e725c61ed9dd) article in some point of your journey with Mockk.  
It will allow you to understand how Mockk works underneath. This should help you to write better tests/mocks and quickly find a root cause of failing tests.
