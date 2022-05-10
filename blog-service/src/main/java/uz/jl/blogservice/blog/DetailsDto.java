package uz.jl.blogservice.blog;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import uz.jl.blogservice.details.BlogDetailsDto;

@Getter
@Setter
@AllArgsConstructor
public class DetailsDto {
    private BlogDto blogDto;
    private BlogDetailsDto details;
}
