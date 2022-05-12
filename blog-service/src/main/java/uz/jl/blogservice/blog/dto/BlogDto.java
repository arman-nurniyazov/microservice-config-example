package uz.jl.blogservice.blog.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.jl.blogservice.blog.Blogs;

@Builder
@Getter
@Setter
public class BlogDto {
    private Integer id;
    private String title;
    private Integer blogId;

    public static BlogDto toDto(Blogs blogs) {
        return new BlogDto(blogs.getId(), blogs.getTitle() , blogs.getBlogId());
    }
}
