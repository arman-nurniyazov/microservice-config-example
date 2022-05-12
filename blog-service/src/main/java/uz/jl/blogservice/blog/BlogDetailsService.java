package uz.jl.blogservice.blog;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import uz.jl.blogservice.client.BlogDetailsClient;
import uz.jl.blogservice.blog.dto.BlogDetailsDto;

@Service
@RequiredArgsConstructor
public class BlogDetailsService {
    private final BlogDetailsClient blogDetailsClient;

    @HystrixCommand(
            fallbackMethod = "blogDetailsFallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            })
    public BlogDetailsDto getBlogDetails(@PathVariable Integer blogId) {
        return blogDetailsClient.get(blogId);
    }

    public BlogDetailsDto blogDetailsFallback(Integer id) {
        return BlogDetailsDto
                .builder()
                .overview("No content available")
                .build();
    }
}
