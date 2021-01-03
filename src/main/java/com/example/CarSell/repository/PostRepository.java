package com.example.CarSell.repository;

import com.example.CarSell.domain.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
