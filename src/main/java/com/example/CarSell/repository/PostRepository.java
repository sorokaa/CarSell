package com.example.CarSell.repository;

import com.example.CarSell.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findByMainInfoStartsWith(String mainInfo);
    long deleteByMainInfo(String mainInfo);
}
