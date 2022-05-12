package uz.jl.blogservice.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import uz.jl.blogservice.details.BlogDetailsDto;

@FeignClient(name = "BLOG-DETAILS-SERVICE", path = "/api/v1/details")
public interface BlogDetailsClient {
    @GetMapping("/{id}")
    BlogDetailsDto get(@PathVariable Integer id);

}
