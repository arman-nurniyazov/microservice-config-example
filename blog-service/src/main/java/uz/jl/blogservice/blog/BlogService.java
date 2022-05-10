package uz.jl.blogservice.blog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository repository;


    public Integer create(Blogs blog) {
        repository.save(blog);
        return blog.getId();
    }

    public Blogs get(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public List<Blogs> getAll() {
        return repository.findAll();
    }

}
