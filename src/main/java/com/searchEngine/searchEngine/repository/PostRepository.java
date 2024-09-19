package com.searchEngine.searchEngine.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.searchEngine.searchEngine.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{
    @Query(value = "SELECT * FROM post_record ORDER BY id DESC LIMIT :limit", nativeQuery = true)
    List<Post> findLatestPosts(@Param("limit") int limit);
    Optional<Post> findByTitle(String title);

    
}
