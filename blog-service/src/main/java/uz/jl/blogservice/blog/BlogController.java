package uz.jl.blogservice.blog;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import uz.jl.blogservice.blog.dto.BlogDto;
import uz.jl.blogservice.blog.dto.BlogDetailsResponseDto;
import uz.jl.blogservice.client.BlogDetailsClient;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/blog")
public class BlogController {
    private final BlogService service;

    @GetMapping
    public List<BlogDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public BlogDetailsResponseDto get(@PathVariable Integer id) {
        return service.getBlogWithDetails(id);
    }

    @PostMapping
    public Integer create(@RequestBody BlogDto dto) {
        return service.create(dto);
    }

}
