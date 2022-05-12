package uz.jl.blogservice.blog;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import uz.jl.blogservice.client.BlogDetailsClient;
import uz.jl.blogservice.details.BlogDetailsDto;

@Service
@RequiredArgsConstructor
public class BlogDetailsResource {
    private final BlogDetailsClient blogDetailsClient;

    @HystrixCommand(
            fallbackMethod = "blogDetailsFallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            })

//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")},
//            fallbackMethod = "fallBackMethod",
//            threadPoolKey = "myServicePool",
//            threadPoolProperties = {
//                    @HystrixProperty(name = "coreSize", value = "40"),
//                    @HystrixProperty(name = "maxQueueSize", value = "10")
//            })
    public BlogDetailsDto getBlogDetails(@PathVariable Integer blogId) {
        return blogDetailsClient.get(blogId);
    }


    public BlogDetailsDto blogDetailsFallback(Integer id) {
        return null;
    }
}
