package uz.jl.blogdetailsservice.blog;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BlogDto {
    private String title;
    private Integer blogId;
}
