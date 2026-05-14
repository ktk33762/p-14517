package com.back.domain.post.post.repository;

import com.back.domain.post.post.dto.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostRepository {


    List<Post> findAll();

    Post findById(int id);

    List<Post> findAllOrdered(
            @Param("orderBy") String orderBy,
            @Param("orderByDirection") String orderByDirection
    );


    @Options(useGeneratedKeys = true, keyProperty = "id")
    int create(Post post);

    void createV2(String title,  String content);

    int getLastInsertId();

    void deleteById(int id);

    int update(
            @Param("id") int id,
            @Param("title") String title,
            @Param("content") String content
    );

    List<Post> search(
            @Param("kwType") String kwType,
            @Param("kw") String kw
    );

    int deleteByIds(@Param("ids") List<Integer> ids);
}
