package uz.jl.blogservice.blog;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import uz.jl.blogservice.details.BlogDetailsDto;
import uz.jl.blogservice.details.CommentDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DetailsDto {
    private BlogDto blogDto;
    private BlogDetailsDto details;
    private List<CommentDto> comments;
}
