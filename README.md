# The Revenue Recognition Problem

IntelliJ Kotlin example from https://engineering.doximity.com/articles/untangling-the-business-logic-an-android-story

## Notes

- To simplify the implementation, use cases classes are singletons. They should be classes with public interfacesl and they should be injected were they are needed.
- `RevenueRecognition.isRecognizableBy()` could be a use case, but I think that would be an over design.
