package com.back.domain.post.post.repository;

import com.back.domain.post.post.dto.Post;
import org.apache.ibatis.annotations.*;

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

    @Select("""
    <script>
    SELECT * 
    FROM post
        <if test="orderBy != null and orderBy != ''">
            ORDER BY
            <choose>
                <when test="orderBy == 'title'">title</when>
                <when test="orderBy == 'createDate'">createDate</when>
                <when test="orderBy == 'modifyDate'">modifyDate</when>
            </choose>
        </if>
        <if test="orderByDirection != null and orderByDirection.toLowerCase() == 'desc'">
            DESC
        </if>
    </script>
    """)
    List<Post> findAllOrdered(
            @Param("orderBy") String orderBy,
            @Param("orderByDirection") String orderByDirection
    );

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

    @Insert("""
    <script>
    INSERT INTO post
    set createDate = NOW(),
    modifyDate = NOW(),
    title = #{title},
    content = #{content}
    </script>
    """)
    void createV2(String title,  String content);

    @Select("""
            <script>
            select last_insert_id()
            </script>
            """)
    int getLastInsertId();

    @Delete("""
            <script>
            delete from post
            where id = #{id}
            </script>
            """)
    void deleteById(int id);

    @Update("""
        <script>
            UPDATE post
            <set> 
                modifyDate = NOW(),
                <if test="title != null and title != ''"> 
                    title = #{title}
                </if>
                <if test="content != null and content != ''">
                    content = #{content}
                </if>
            </set>        
            <where>
                <if test="id != null and id > 0">
                    id = #{id}
                </if>
            </where>
        </script>
    """)
    int update(
            @Param("id") int id,
            @Param("title") String title,
            @Param("content") String content
    );

    @Select("""
    <script>
        select * 
        from post
        <where>
            <choose>
                <when test="kwType == 'title'">
                    title LIKE CONCAT('%', #{kw}, '%')
                </when>
                <when test="kwType == 'content'">
                   content LIKE CONCAT('%', #{kw}, '%')
                </when>
                <otherwise>
                    (
                        title LIKE CONCAT('%', #{kw}, '%')
                        OR
                        content LIKE CONCAT('%', #{kw}, '%')
                    )
                </otherwise>
            </choose>
         </where>
      </script>
    """)
    List<Post> search(
            @Param("kwType") String kwType,
            @Param("kw") String kw
    );


}
