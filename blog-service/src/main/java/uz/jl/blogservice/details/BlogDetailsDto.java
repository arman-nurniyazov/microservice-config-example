package uz.jl.blogservice.details;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import lombok.*;

import java.util.List;


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

    private List<CommentDto> comments;

    private int likeCount;

    private int dislikeCount;
}
