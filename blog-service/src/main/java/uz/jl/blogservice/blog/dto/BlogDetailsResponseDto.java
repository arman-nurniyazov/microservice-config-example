package uz.jl.blogservice.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BlogDetailsResponseDto {
    private BlogDto blogDto;
    private BlogDetailsDto details;
    private List<CommentDto> comments;
}
