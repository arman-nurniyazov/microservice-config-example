package uz.jl.blogservice.blog;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
