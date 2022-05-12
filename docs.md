```
 @HystrixCommand(commandProperties = {
         @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")},
         fallbackMethod = "fallBackMethod",
         threadPoolKey = "myServicePool",
         threadPoolProperties = {
                 @HystrixProperty(name = "coreSize", value = "40"),
                 @HystrixProperty(name = "maxQueueSize", value = "10")
         })
```