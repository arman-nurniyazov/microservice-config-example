package uz.jl.blogservice.blog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.jl.blogservice.blog.dto.BlogDto;
import uz.jl.blogservice.blog.dto.BlogDetailsResponseDto;
import uz.jl.blogservice.blog.dto.BlogDetailsDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository repository;
    private final BlogDetailsService blogDetailsService;


    public Integer create(BlogDto dto) {
        Blogs blog = Blogs.builder()
                .title(dto.getTitle())
                .blogId(dto.getBlogId())
                .build();
        repository.save(blog);
        return blog.getId();
    }

    public Blogs get(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public List<BlogDto> getAll() {
        return repository.findAll().stream()
                .map(BlogDto::toDto)
                .toList();
    }

    public BlogDetailsResponseDto getBlogWithDetails(Integer id) {
        Blogs blogs = get(id);
        BlogDto blogDto = BlogDto.builder()
                .id(blogs.getId())
                .title(blogs.getTitle())
                .blogId(blogs.getBlogId())
                .build();
        BlogDetailsDto detailsDto = blogDetailsService.getBlogDetails(blogs.getBlogId());
        // BlogDetailsDto detailsDto = blogDetailsService.getBlogDetails(blogs.getBlogId());
        return new BlogDetailsResponseDto(blogDto, detailsDto, null);
    }
}
