package uz.jl.blogservice.blog;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import uz.jl.blogservice.details.BlogDetailsDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/blog")
public class BlogController {
    private final BlogService service;
    private final BlogDetailsResource resource;
    private final Environment environment;


    @GetMapping
    public List<BlogDto> getAll() {
        return service.getAll()
                .stream()
                .map(BlogDto::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public DetailsDto get(@PathVariable Integer id) {
        Blogs blogs = service.get(id);

        BlogDto blogDto = BlogDto.builder()
                .id(blogs.getId())
                .title(blogs.getTitle())
                .blogId(blogs.getBlogId())
                .build();

        BlogDetailsDto detailsDto = resource.getBlogDetails(blogs.getBlogId());
        return new DetailsDto(blogDto, detailsDto, null);
    }



    public BlogDetailsDto fallbackForGet(Integer id) {
        return null;
    }

    @PostMapping
    public Integer create(@RequestBody BlogDto dto) {
        Blogs blogs = Blogs.builder()
                .title(dto.getTitle())
                .blogId(dto.getBlogId())
                .build();
        return service.create(blogs);
    }

}
