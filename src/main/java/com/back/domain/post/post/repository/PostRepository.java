package com.back.domain.post.post.repository;

import com.back.domain.post.post.dto.Post;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostRepository {

    @Select("""
            <script>
            select
            * from post
            </script>
            """)
    List<Post> findAll();


    @Select("""
            <script>
            select
            * from post
            where id = #{id}
            </script>
            """)

    Post findById(int id);

    @Insert("""
            <script>
            insert into post
            set createDate = NOW(),
            modifyDate = NOW(),
            title = #{title},
            content = #{content}
            </script>
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int create(Post post);
}
