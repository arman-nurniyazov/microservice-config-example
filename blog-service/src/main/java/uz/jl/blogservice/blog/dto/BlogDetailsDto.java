package uz.jl.blogservice.blog.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogDetailsDto {

    private Integer id;

    private String title;

    private String overview;

    private String body;

    private Integer likeCount;

    private Integer dislikeCount;
}
