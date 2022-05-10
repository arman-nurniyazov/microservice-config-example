package uz.jl.blogservice.blog;

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
    private final RestTemplate restTemplate;
    private final Environment environment;

    @GetMapping("/test")
    public int getPort() {
        int port = restTemplate.getForObject("http://BLOG-DETAILS-SERVICE/api/v1/details/test", Integer.class);
        return port;
    }

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

        BlogDetailsDto detailsDto =
                restTemplate.getForObject("http://BLOG-DETAILS-SERVICE/api/v1/details/" + blogs.getBlogId(), BlogDetailsDto.class);
        return new DetailsDto(blogDto, detailsDto);
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
