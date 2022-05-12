package uz.jl.blogdetailsservice.details;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import uz.jl.blogdetailsservice.blog.BlogDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/details")
public class BlogDetailsController {
    private final BlogDetailsRepository repository;
    private final RestTemplate restTemplate;
    private final Environment env;

    @GetMapping("/test")
    public Integer getPort() {
        return env.getProperty("server.port", Integer.class);
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public BlogDetailsDto get(@PathVariable Integer id) {
//        Thread.sleep(2000);
        BlogDetails blogDetails = repository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Blog post not found with id : %s".formatted(id));
        });
        return BlogDetailsDto
                .builder()
                .id(blogDetails.getId())
                .title(blogDetails.getTitle())
                .overview(blogDetails.getOverview())
                .body(blogDetails.getBody())
                .comments(List.of())
                .likeCount(12)
                .dislikeCount(0)
                .build();
    }

    @PostMapping
    public Integer create(@RequestBody BlogDetails blogDetails) {
        repository.save(blogDetails);
        BlogDto dto = BlogDto.builder()
                .title(blogDetails.getTitle())
                .blogId(blogDetails.getId())
                .build();
        restTemplate.postForObject("http://BLOG-SERVICE/api/v1/blog", dto, Integer.class);
        return blogDetails.getId();
    }

}
