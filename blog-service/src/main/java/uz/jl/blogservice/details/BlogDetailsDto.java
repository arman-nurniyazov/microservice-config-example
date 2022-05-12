package uz.jl.blogservice.details;

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

    private int likeCount;

    private int dislikeCount;
}
